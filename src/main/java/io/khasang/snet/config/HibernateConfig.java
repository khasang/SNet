package io.khasang.snet.config;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.impl.ChatUtils;
import io.khasang.snet.dao.impl.MessageUtils;
import io.khasang.snet.dao.impl.PictureUtils;
import io.khasang.snet.dao.impl.WeatherReportUnils;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.Message;
import io.khasang.snet.entity.Picture;
import io.khasang.snet.entity.WeatherReport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Hibernate config - settings at hibernate.properties file
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"io.khasang.snet.config"})
@PropertySource(value = {"classpath:hibernate.properties"})
@ComponentScan({"io.khasang.snet.config"})
public class HibernateConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("io.khasang.snet.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public AbstractCRUD<WeatherReport> weatherReportCRUD() {
        return new WeatherReportUnils(sessionFactory().getObject());
    }

    @Bean(name = "picturesUtils")
    public AbstractCRUD<Picture> pictureAbstractCRUD() {
        return new PictureUtils(sessionFactory().getObject());
    }

    @Bean
    public AbstractCRUD<Chat> chatAbstractCRUD(){
        return new ChatUtils(sessionFactory().getObject());
    }

    @Bean
    public AbstractCRUD<Message> dataUtilMesseges(){
        return new MessageUtils(sessionFactory().getObject());
    }


}

