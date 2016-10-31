package io.khasang.snet.dao;

import io.khasang.snet.entity.Employee;

import java.util.List;


public interface EmployeeDAO {

    void addEmployee(Employee employee);

    List<Employee> getAllEmployees();
}
