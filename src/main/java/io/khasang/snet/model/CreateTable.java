package io.khasang.snet.model;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CreateTable {
    private JdbcTemplate jdbcTemplate;

    public CreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CreateTable() {
    }

    public String tableCreation() {
        try {
            jdbcTemplate.execute("CREATE TABLE COMPANY(\n" +
                    "   ID INT PRIMARY KEY     NOT NULL,\n" +
                    "   NAME           TEXT    NOT NULL,\n" +
                    "   AGE            INT     NOT NULL,\n" +
                    "   ADDRESS        CHAR(50),\n" +
                    "   SALARY         REAL\n" +
                    ");");
            return "table created";
        } catch (Exception e) {
            return "Error: "  + e;
        }
    }

    // add some record in table
    public String insert() {
        try {
            jdbcTemplate.update("INSERT INTO COMPANY VALUES (100,'Студия \"WebSiteProm\"',5,'Фрунзенский район',43535.3)");
            jdbcTemplate.update("INSERT INTO COMPANY VALUES (101,'АСТИ',22,'Россия, Санкт-Петербург, Центральный район',500)");
            jdbcTemplate.update("INSERT INTO COMPANY VALUES (102,'Lidnet',45,'Россия, Санкт-Петербург, Центральный район',100000.45)");
            jdbcTemplate.update("INSERT INTO COMPANY VALUES (103,'Студия Дифферент',2,'Невский район',300045)");
            jdbcTemplate.update("INSERT INTO COMPANY VALUES (104,'ПРО Движение',10,'197341, Россия, Санкт-Петербург, Приморский район',4545.43)");
            jdbcTemplate.update("INSERT INTO COMPANY VALUES (105,'A-Position',23,'191002, Россия, Санкт-Петербург, Центральный район',2455)");
            jdbcTemplate.update("INSERT INTO COMPANY VALUES (106,'Студия \"ХОД\"',56,'Россия, Санкт-Петербург, Приморский район',666)");
            jdbcTemplate.update("INSERT INTO COMPANY VALUES (107,'Лакси',33,'94295, Россия, Санкт-Петербург, Выборгский район',77777)");
            return "INSERTED";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    // show all records of table
    public List<Company> selectAll() {
        String sql = "SELECT * FROM COMPANY";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Company.class));
    }

}
