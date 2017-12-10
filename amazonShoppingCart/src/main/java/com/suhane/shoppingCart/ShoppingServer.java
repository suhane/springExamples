package com.suhane.shoppingCart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@EnableAutoConfiguration(exclude = VelocityAutoConfiguration.class)
@PropertySource("classpath:local.properties")
@ComponentScan
@EnableTransactionManagement
public class ShoppingServer {

    @Autowired
    private Environment env;

    public static void main(String s[]){

       SpringApplication.run(ShoppingServer.class,s);

    }


    @Bean
    public SessionFactory sessionFactory() throws IOException {
        PropertiesFactoryBean propertiesFactory = new PropertiesFactoryBean();
        propertiesFactory
                .setLocation(new ClassPathResource("hibernate.properties"));
        propertiesFactory.afterPropertiesSet();
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("com.suhane.shoppingCart.model");
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(propertiesFactory.getObject());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("shoppingCart.db.driver"));
        dataSource.setUrl("jdbc:mysql://" + env.getProperty("shoppingCart.db.host") + ":"
                + env.getProperty("shoppingCart.db.port") + "/"
                + env.getProperty("shoppingCart.db.name"));
        dataSource.setUsername(env.getProperty("shoppingCart.db.username"));
        dataSource.setPassword(env.getProperty("shoppingCart.db.password"));
        return dataSource;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
