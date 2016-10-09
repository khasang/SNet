package io.khasang.snet.model;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectFromTable {
    private JdbcTemplate jdbcTemplate;

    public SelectFromTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public SelectFromTable() {
    }

    public String selectRowsFromTableInArray() {
        String sql = "SELECT * FROM company WHERE salary > 60000;";
        List<Company> companySelectedRows = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        StringBuilder selectedArrayToString = new StringBuilder();
        //формирование массива из результата возвращаемого методом select
        try {
            for (Map row : rows) {
                Company company = new Company();
                company.setId((int)(row.get("id")));
                company.setName((String) (row.get("name")));
                company.setAge((int)(row.get("age")));
                company.setAddress((String) (row.get("address")));
                company.setSalary((float)(row.get("salary")));
                companySelectedRows.add(company);
            }
            //преобразование массива в строку для вывода на странице
            for (Company company : companySelectedRows) {
                selectedArrayToString.append(company.getName()).append(" ");
                selectedArrayToString.append(company.getAge()).append(" ");
                selectedArrayToString.append(company.getAddress()).append(" ");
                selectedArrayToString.append(company.getSalary()).append(" ");
            }
            return selectedArrayToString.toString();
//            return "rows selected: "+companySelectedRows.size();
        } catch (Exception e) {
            return "Error: "  + e;
        }
    }
}