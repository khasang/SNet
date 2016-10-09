package io.khasang.snet.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class SQLConnector {
    private final static String url = "jdbc:postgresql://localhost:5432/test_database?user=root&password=root";
    private static JdbcTemplate jdbcTemplateInstance;

    private static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url);
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplateInstance==null) jdbcTemplateInstance = new JdbcTemplate(getDataSource());
        return jdbcTemplateInstance;
    }
}
