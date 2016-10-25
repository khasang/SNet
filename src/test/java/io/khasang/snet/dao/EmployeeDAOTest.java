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

//    @Test
//    @Transactional
//    @Rollback(true)
//    public void testSizeAfterAddEmployee() {
//
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void testGetAllEmployeesAfterAddFewEmployee() {
//
//    }

}