package io.khasang.snet.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

@Component
public class SQLQueryHandler {
    private JdbcTemplate jdbcTemplate;

    public SQLQueryHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer executeUpdate(PreparedStatementCreator preparedStatementCreator) {
        try {
            return jdbcTemplate.update(preparedStatementCreator);
        } catch (Exception exc) {
            return -1;
        }
    }


}
