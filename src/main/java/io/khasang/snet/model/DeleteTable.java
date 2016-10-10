package io.khasang.snet.model;

import org.springframework.jdbc.core.JdbcTemplate;

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
            jdbcTemplate.update("DROP TABLE COMPANY");
            return "Table was deleted";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    //delete record by Id
    public String deleteRecord(int recordId) {
        try {
            jdbcTemplate.update("DELETE FROM COMPANY WHERE id = ?", recordId );
            return "Record id = " + recordId +" was deleted!!" ;
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

}
