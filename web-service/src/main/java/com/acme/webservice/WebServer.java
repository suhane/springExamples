package com.acme.webservice;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
public class WebServer {

    @Autowired
    private Environment env;

    public static void main(String args[]){
        SpringApplication.run(WebServer.class,args);
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        PropertiesFactoryBean propertiesFactory = new PropertiesFactoryBean();
        propertiesFactory
                .setLocation(new ClassPathResource("hibernate.properties"));
        propertiesFactory.afterPropertiesSet();
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("com.acme.webservice");
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(propertiesFactory.getObject());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("webService.db.driver"));
        dataSource.setUrl("jdbc:mysql://" + env.getProperty("webService.db.host") + ":"
                + env.getProperty("webService.db.port") + "/"
                + env.getProperty("webService.db.name"));
        dataSource.setUsername(env.getProperty("webService.db.username"));
        dataSource.setPassword(env.getProperty("webService.db.password"));
        return dataSource;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(
            SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
