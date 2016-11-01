package io.khasang.snet.controller;

import io.khasang.snet.entity.Employee;
import io.khasang.snet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class RestEmployeesController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addEmployee(@RequestBody Employee employee, HttpServletResponse response) {
        try {
            employeeService.addEmployee(employee);
            return employee;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error adding Employee: " + e.getMessage();
        }
    }

}
