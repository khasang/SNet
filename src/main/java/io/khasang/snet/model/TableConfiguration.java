package io.khasang.snet.model;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.sql.Struct;

public class TableConfiguration {
    private JdbcTemplate jdbcTemplate;

    public TableConfiguration(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TableConfiguration() {
    }

    public String employeesTableCreation() {
        try {
            jdbcTemplate.execute("CREATE TABLE EMPLOYEES(\n" +
                    "   ID INT PRIMARY KEY     NOT NULL,\n" +
                    "   NAME           TEXT    NOT NULL,\n" +
                    "   AGE            INT     NOT NULL,\n" +
                    "   CITY        CHAR(50),\n" +
                    "   SALARY         REAL\n" +
                    ");");
            return "table employees created";
        } catch (Exception e) {
            return "Error: "  + e;
        }
    }

    public String citiesTableCreation() {
        try {
            jdbcTemplate.execute("CREATE TABLE CITIES(\n" +
                    "   ID INT PRIMARY KEY     NOT NULL,\n" +
                    "   CITY_NAME           TEXT    NOT NULL,\n" +
                    "   COUNTRY            TEXT     NOT NULL,\n" +
                    "   POPULATION        INT NOT NULL\n" +
                    ");");
            return "table cities created";
        } catch (Exception e) {
            return "Error: "  + e;
        }
    }

    public String insertEmployees() {
        try {
            jdbcTemplate.update("INSERT INTO EMPLOYEES VALUES (100,'Петров',20,'Москва',43535.3)");
            jdbcTemplate.update("INSERT INTO EMPLOYEES VALUES (101,'Иванов',22,'Санкт-Петербург',500)");
            jdbcTemplate.update("INSERT INTO EMPLOYEES VALUES (102,'Сидоров',45,'Лондон',100000.45)");
            jdbcTemplate.update("INSERT INTO EMPLOYEES VALUES (103,'Кузнецов',32,'Москва',300045)");
            jdbcTemplate.update("INSERT INTO EMPLOYEES VALUES (104,'Александров',27,'Санкт-Петербург',4545.43)");
            return "INSERTED EMPLOYEES";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    public String insertCities() {
        try {
            jdbcTemplate.update("INSERT INTO CITIES VALUES (100,'Москва','Россия',100000)");
            jdbcTemplate.update("INSERT INTO CITIES VALUES (101,'Санкт-Петербург','Россия',80000)");
            jdbcTemplate.update("INSERT INTO CITIES VALUES (102,'Лондон','Англия',90000)");
            return "INSERTED CITIES";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    public String selectEmployeesByCountry(String counry) {
        StringBuilder sb = new StringBuilder();
        jdbcTemplate.query(
                "SELECT * FROM EMPLOYEES WHERE city in (select city_name from cities where country = ?);", new Object[]{counry},
                (rs, rowNum) ->
                        new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("city"), rs.getDouble("salary"))
        ).forEach(employee -> sb.append(employee.toString()));
        return sb.toString();
    }
}
