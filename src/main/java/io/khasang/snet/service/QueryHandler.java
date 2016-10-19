package io.khasang.snet.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

/* Low level sql query handler */
@Component
public class QueryHandler {
    private JdbcTemplate jdbcTemplate;

    public QueryHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* @param implementation of PreparedStatementCreator
    * @return row affected or -1 in case of failure
    * */
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
