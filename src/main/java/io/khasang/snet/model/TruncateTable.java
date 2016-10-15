package io.khasang.snet.model;

import org.springframework.jdbc.core.JdbcTemplate;

public class TruncateTable {
    private JdbcTemplate jdbcTemplate;

    public TruncateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TruncateTable() {
    }

    public String truncate() {
        try {
            jdbcTemplate.execute("TRUNCATE TABLE COMPANY");
            return "TRUNCATED";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

}
