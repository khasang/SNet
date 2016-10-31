package io.khasang.snet.dao.impl;


import io.khasang.snet.dao.EmployeeDAO;
import io.khasang.snet.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Employee.class);
        return (List<Employee>) criteria.list();
    }
}
