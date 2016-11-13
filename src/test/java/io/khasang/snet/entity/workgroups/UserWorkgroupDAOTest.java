package io.khasang.snet.entity.workgroups;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;

import io.khasang.snet.dao.workgroups.UserWorkgroupDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.List;

/*
* Basic tests for entity User Workgroup test
*/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class UserWorkgroupDAOTest {

    @Autowired
    UserWorkgroupDAO userWorkgroupDAO;

/*    @Test
    public void testAddUserWorkgroup(){
        UserWorkgroupsId userWorkgroupsId = new UserWorkgroupsId();
        userWorkgroupsId.setWorkgroupId(11);
        userWorkgroupsId.setUserId(8);
        UserWorkgroups userWorkgroups = new UserWorkgroups();
        userWorkgroups.setAdmin(true);
        userWorkgroups.setPrKey(userWorkgroupsId);
        userWorkgroupDAO.addUserWorkgroups(userWorkgroups);

    }

    @Test
    public void getWorkgroupsByUser() {
       List<Long> list =  userWorkgroupDAO.getUsersByWorkgroup(9);

        for (Long l : list){
            System.out.println(l);
        }

    }*/


}
