package io.khasang.snet.dao;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.entity.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, HibernateConfig.class, WebConfig.class})
public class EmployeeDAOTest {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    @Transactional
    @Rollback(true)
    public void testSizeAfterAddEmployee() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        Employee employee = new Employee();
        employee.setAge(25);
        employee.setCity("Some City");
        employee.setName("Some name");
        employee.setSalary(1000);
        employeeDAO.addEmployee(employee);
        List<Employee> employeesNew = employeeDAO.getAllEmployees();
        Assert.assertEquals(employees.size(),employeesNew.size()-1);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllEmployeesAfterAddFewEmployee() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        Employee employee1 = new Employee();
        employee1.setAge(25);
        employee1.setCity("Some City1");
        employee1.setName("Some name1");
        employee1.setSalary(1500);
        employeeDAO.addEmployee(employee1);
        Employee employee2 = new Employee();
        employee2.setAge(25);
        employee2.setCity("Some City2");
        employee2.setName("Some name2");
        employee2.setSalary(1700);
        employeeDAO.addEmployee(employee2);
        employees.add(employee1);
        employees.add(employee2);
        List<Employee> employeesNew = employeeDAO.getAllEmployees();
        Assert.assertEquals(employees,employeesNew);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void testRemoveEmployee() {

        Employee employee = new Employee();
        employee.setAge(25);
        employee.setCity("Some City");
        employee.setName("Some name");
        employee.setSalary(1000);
        employeeDAO.addEmployee(employee);
        // get list
        List<Employee> employees = employeeDAO.getAllEmployees();

        int index =-100;
        // search id of employee
        for (Employee emp :employees) {
            if (emp.getName().equals(employee.getName()) && emp.getAge()== employee.getAge() && emp.getSalary()== employee.getSalary()){
                index=emp.getId();
                return;
            }
        }

        Assert.assertNotEquals(-100,index);
        //employeeDAO.deleteEmployee(index);
        Assert.assertNull(employeeDAO.getAllEmployees().get(index));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetEmployee() {
        Employee employee = new Employee();
        employee.setAge(25);
        employee.setCity("Some City");
        employee.setName("Some name");
        employee.setSalary(1000);
        employeeDAO.addEmployee(employee);
        // get list
        List<Employee> employees = employeeDAO.getAllEmployees();

        int index =-100;
        // search id of employee
        for (Employee emp :employees) {
            if (emp.getName().equals(employee.getName()) && emp.getAge()== employee.getAge() && emp.getSalary()== employee.getSalary()){
                index=emp.getId();
                break;
            }
        }

        Assert.assertNotEquals(-100,index);
        employee.setId(index);
        //Assert.assertEquals(employee, employeeDAO.getEmployee(index));
    }

}