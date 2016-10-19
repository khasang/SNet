package io.khasang.snet.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

@Component
public class QueryHandler {
    private JdbcTemplate jdbcTemplate;

    public QueryHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int executeUpdate(PreparedStatementCreator preparedStatementCreator) {
        try {
            return jdbcTemplate.update(preparedStatementCreator);
        } catch (Exception exc) {
            return -1;
        }
    }

    public void execute(String sql) throws Exception {
        jdbcTemplate.execute(sql);
    }


}
