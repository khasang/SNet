package io.khasang.snet.model;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DeleteTable {
    private JdbcTemplate jdbcTemplate;

    public DeleteTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DeleteTable() {
    }

    //delete table Company
    public String delete() {
        try {
            jdbcTemplate.execute("DROP TABLE COMPANY");
            return "Table was deleted";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

}
