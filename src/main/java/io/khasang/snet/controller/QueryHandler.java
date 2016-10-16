package io.khasang.snet.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

@Component
public class QueryHandler {
    private JdbcTemplate jdbcTemplate;

    public QueryHandler(JdbcTemplate jdbcTemplate) {
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
