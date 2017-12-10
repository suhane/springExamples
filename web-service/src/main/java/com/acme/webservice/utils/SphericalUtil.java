package com.acme.webservice.utils;

import com.acme.webservice.rest.types.Coordinates;

import java.util.List;

import static com.acme.webservice.utils.MathUtil.*;
import static java.lang.Math.*;

public class SphericalUtil {

    private SphericalUtil() {}

    /**
     * Returns the heading from one Coordinates to another Coordinates. Headings are
     * expressed in degrees clockwise from North within the range [-180,180).
     * @return The heading in degrees clockwise from north.
     */
    public static double computeHeading(Coordinates from, Coordinates to) {
        // http://williams.best.vwh.net/avform.htm#Crs
        double fromLat = toRadians(from.getLat());
        double fromLng = toRadians(from.getLng());
        double toLat = toRadians(to.getLat());
        double toLng = toRadians(to.getLng());
        double dLng = toLng - fromLng;
        double heading = atan2(
                sin(dLng) * cos(toLat),
                cos(fromLat) * sin(toLat) - sin(fromLat) * cos(toLat) * cos(dLng));
        return wrap(toDegrees(heading), -180, 180);
    }

    /**
     * Returns the Coordinates resulting from moving a distance from an origin
     * in the specified heading (expressed in degrees clockwise from north).
     * @param from     The Coordinates from which to start.
     * @param distance The distance to travel.
     * @param heading  The heading in degrees clockwise from north.
     */
    public static Coordinates computeOffset(Coordinates from, double distance, double heading) {
        distance /= EARTH_RADIUS;
        heading = toRadians(heading);
        // http://williams.best.vwh.net/avform.htm#LL
        double fromLat = toRadians(from.getLat());
        double fromLng = toRadians(from.getLng());
        double cosDistance = cos(distance);
        double sinDistance = sin(distance);
        double sinFromLat = sin(fromLat);
        double cosFromLat = cos(fromLat);
        double sinLat = cosDistance * sinFromLat + sinDistance * cosFromLat * cos(heading);
        double dLng = atan2(
                sinDistance * cosFromLat * sin(heading),
                cosDistance - sinFromLat * sinLat);
        return new Coordinates(toDegrees(asin(sinLat)), toDegrees(fromLng + dLng));
    }

    /**
     * Returns the location of origin when provided with a Coordinates destination,
     * meters travelled and original heading. Headings are expressed in degrees
     * clockwise from North. This function returns null when no solution is
     * available.
     * @param to       The destination Coordinates.
     * @param distance The distance travelled, in meters.
     * @param heading  The heading in degrees clockwise from north.
     */
    public static Coordinates computeOffsetOrigin(Coordinates to, double distance, double heading) {
        heading = toRadians(heading);
        distance /= EARTH_RADIUS;
        // http://lists.maptools.org/pipermail/proj/2008-October/003939.html
        double n1 = cos(distance);
        double n2 = sin(distance) * cos(heading);
        double n3 = sin(distance) * sin(heading);
        double n4 = sin(toRadians(to.getLat()));
        // There are two solutions for b. b = n2 * n4 +/- sqrt(), one solution results
        // in the latitude outside the [-90, 90] range. We first try one solution and
        // back off to the other if we are outside that range.
        double n12 = n1 * n1;
        double discriminant = n2 * n2 * n12 + n12 * n12 - n12 * n4 * n4;
        if (discriminant < 0) {
            // No real solution which would make sense in Coordinates-space.
            return null;
        }
        double b = n2 * n4 + sqrt(discriminant);
        b /= n1 * n1 + n2 * n2;
        double a = (n4 - n2 * b) / n1;
        double fromLatRadians = atan2(a, b);
        if (fromLatRadians < -PI / 2 || fromLatRadians > PI / 2) {
            b = n2 * n4 - sqrt(discriminant);
            b /= n1 * n1 + n2 * n2;
            fromLatRadians = atan2(a, b);
        }
        if (fromLatRadians < -PI / 2 || fromLatRadians > PI / 2) {
            // No solution which would make sense in Coordinates-space.
            return null;
        }
        double fromLngRadians = toRadians(to.getLng()) -
                atan2(n3, n1 * cos(fromLatRadians) - n2 * sin(fromLatRadians));
        return new Coordinates(toDegrees(fromLatRadians), toDegrees(fromLngRadians));
    }

    /**
     * Returns the Coordinates which lies the given fraction of the way between the
     * origin Coordinates and the destination Coordinates.
     * @param from     The Coordinates from which to start.
     * @param to       The Coordinates toward which to travel.
     * @param fraction A fraction of the distance to travel.
     * @return The interpolated Coordinates.
     */
    public static Coordinates interpolate(Coordinates from, Coordinates to, double fraction) {
        // http://en.wikipedia.org/wiki/Slerp
        double fromLat = toRadians(from.getLat());
        double fromLng = toRadians(from.getLng());
        double toLat = toRadians(to.getLat());
        double toLng = toRadians(to.getLng());
        double cosFromLat = cos(fromLat);
        double cosToLat = cos(toLat);

        // Computes Spherical interpolation coefficients.
        double angle = computeAngleBetween(from, to);
        double sinAngle = sin(angle);
        if (sinAngle < 1E-6) {
            return from;
        }
        double a = sin((1 - fraction) * angle) / sinAngle;
        double b = sin(fraction * angle) / sinAngle;

        // Converts from polar to vector and interpolate.
        double x = a * cosFromLat * cos(fromLng) + b * cosToLat * cos(toLng);
        double y = a * cosFromLat * sin(fromLng) + b * cosToLat * sin(toLng);
        double z = a * sin(fromLat) + b * sin(toLat);

        // Converts interpolated vector back to polar.
        double lat = atan2(z, sqrt(x * x + y * y));
        double lng = atan2(y, x);
        return new Coordinates(toDegrees(lat), toDegrees(lng));
    }

    /**
     * Returns distance on the unit sphere; the arguments are in radians.
     */
    private static double distanceRadians(double lat1, double lng1, double lat2, double lng2) {
        return arcHav(havDistance(lat1, lat2, lng1 - lng2));
    }

    /**
     * Returns the angle between two Coordinatess, in radians. This is the same as the distance
     * on the unit sphere.
     */
    static double computeAngleBetween(Coordinates from, Coordinates to) {
        return distanceRadians(toRadians(from.getLat()), toRadians(from.getLng()),
                toRadians(to.getLat()), toRadians(to.getLng()));
    }

    /**
     * Returns the distance between two Coordinatess, in meters.
     */
    public static double computeDistanceBetween(Coordinates from, Coordinates to) {
        return computeAngleBetween(from, to) * EARTH_RADIUS;
    }

    /**
     * Returns the length of the given path, in meters, on Earth.
     */
    public static double computeLength(List<Coordinates> path) {
        if (path.size() < 2) {
            return 0;
        }
        double length = 0;
        Coordinates prev = path.get(0);
        double prevLat = toRadians(prev.getLat());
        double prevLng = toRadians(prev.getLng());
        for (Coordinates point : path) {
            double lat = toRadians(point.getLat());
            double lng = toRadians(point.getLng());
            length += distanceRadians(prevLat, prevLng, lat, lng);
            prevLat = lat;
            prevLng = lng;
        }
        return length * EARTH_RADIUS;
    }

    /**
     * Returns the area of a closed path on Earth.
     * @param path A closed path.
     * @return The path's area in square meters.
     */
    public static double computeArea(List<Coordinates> path) {
        return abs(computeSignedArea(path));
    }

    /**
     * Returns the signed area of a closed path on Earth. The sign of the area may be used to
     * determine the orientation of the path.
     * "inside" is the surface that does not contain the South Pole.
     * @param path A closed path.
     * @return The loop's area in square meters.
     */
    public static double computeSignedArea(List<Coordinates> path) {
        return computeSignedArea(path, EARTH_RADIUS);
    }

    /**
     * Returns the signed area of a closed path on a sphere of given radius.
     * The computed area uses the same units as the radius squared.
     * Used by SphericalUtilTest.
     */
    static double computeSignedArea(List<Coordinates> path, double radius) {
        int size = path.size();
        if (size < 3) { return 0; }
        double total = 0;
        Coordinates prev = path.get(size - 1);
        double prevTanLat = tan((PI / 2 - toRadians(prev.getLat())) / 2);
        double prevLng = toRadians(prev.getLng());
        // For each edge, accumulate the signed area of the triangle formed by the North Pole
        // and that edge ("polar triangle").
        for (Coordinates point : path) {
            double tanLat = tan((PI / 2 - toRadians(point.getLat())) / 2);
            double lng = toRadians(point.getLng());
            total += polarTriangleArea(tanLat, lng, prevTanLat, prevLng);
            prevTanLat = tanLat;
            prevLng = lng;
        }
        return total * (radius * radius);
    }

    /**
     * Returns the signed area of a triangle which has North Pole as a vertex.
     * Formula derived from "Area of a spherical triangle given two edges and the included angle"
     * as per "Spherical Trigonometry" by Todhunter, page 71, section 103, point 2.
     * See http://books.google.com/books?id=3uBHAAAAIAAJ&pg=PA71
     * The arguments named "tan" are tan((pi/2 - latitude)/2).
     */
    private static double polarTriangleArea(double tan1, double lng1, double tan2, double lng2) {
        double deltaLng = lng1 - lng2;
        double t = tan1 * tan2;
        return 2 * atan2(t * sin(deltaLng), 1 + t * cos(deltaLng));
    }
}

