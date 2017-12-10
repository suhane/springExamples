package com.acme.webservice.utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    private JsonUtils(){}

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.warn("Exception in mapping to json", e);
            return "";
        }
    }

    public static <T> T fromJson(String str, TypeReference<T> ref, T defaultValue) {
        if(StringUtils.isBlank(str)) {
            return defaultValue;
        }

        try {
            return OBJECT_MAPPER.readValue(str, ref);
        } catch (Exception e) {
            LOGGER.warn("Exception in mapping from json {}", str, e);
            return defaultValue;
        }
    }

    public static String toEncodedParams(HashMap<String, String> params){

        StringBuilder sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0){
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                            .append(URLEncoder.encode(params.get(key),"UTF-8"));


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }
        return sbParams.toString();
    }

    public static <T> T fromJson(String str, Class<T> clazz, T defaultValue) {
        if(StringUtils.isBlank(str)) {
            return defaultValue;
        }

        try {
            return OBJECT_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            LOGGER.warn("Exception in mapping from json {}", str, e);
            return defaultValue;
        }
    }
}
