package io.khasang.snet.model;

import io.khasang.snet.model.tables.Company;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by elimental on 09.10.2016.
 */
public class CompanyCrud {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CompanyCrud(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public String tableCreation() {
        try {
            jdbcTemplate.execute("CREATE TABLE COMPANY(\n" +
                    "   ID INT PRIMARY KEY     NOT NULL,\n" +
                    "   NAME           TEXT    NOT NULL,\n" +
                    "   AGE            INT     NOT NULL,\n" +
                    "   ADDRESS        VARCHAR(50),\n" +
                    "   SALARY         REAL\n" +
                    ");");
            return "TABLE CREATED";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    public String insert() {
        try {
            jdbcTemplate.execute("INSERT INTO COMPANY VALUES (5,'Masha',38,'Leningrad',10000)");
            jdbcTemplate.execute("INSERT INTO COMPANY VALUES (6,'Vitya',38,'Leningrad',10000)");
            jdbcTemplate.execute("INSERT INTO COMPANY VALUES (7,'Petya',38,'Leningrad',10000)");
            jdbcTemplate.execute("INSERT INTO COMPANY VALUES (8,'Armen',38,'Leningrad',10000)");
            jdbcTemplate.execute("INSERT INTO COMPANY VALUES (9,'Sergey',38,'Leningrad',10000)");
            jdbcTemplate.execute("INSERT INTO COMPANY VALUES (10,'Mitya',38,'Leningrad',10000)");
            return "INSERTED";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    public String truncate() {
        try {
            jdbcTemplate.execute("TRUNCATE TABLE COMPANY");
            return "TRUNCATED";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }

    public String selectAll() {
        String sql = "SELECT * FROM COMPANY";
        List<Company> list = namedParameterJdbcTemplate.query(sql, new CompanyMapper());
        String result = "";
        for (Company company : list) {
            result = result + company.toString() + "<br>";
        }
        return result;
    }


    private static final class CompanyMapper implements RowMapper<Company> {
        @Override
        public Company mapRow(ResultSet resultSet, int i) throws SQLException {
            Company company = new Company();
            company.setId(resultSet.getInt("ID"));
            company.setName(resultSet.getString("NAME"));
            company.setAge(resultSet.getInt("AGE"));
            company.setAddress(resultSet.getString("ADDRESS"));
            company.setSalary(resultSet.getDouble("SALARY"));
            return company;
        }
    }

}

