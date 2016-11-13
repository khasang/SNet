package io.khasang.snet.entity.workgroups;


import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.workgroups.WorkgroupDAO;
import io.khasang.snet.dao.workgroups.WorkgroupNewsDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* Basic tests for entity Workgroup
*/

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class WorkGroupDAOTest {

    @Autowired
    WorkgroupDAO workgroupDAO;

    @Autowired
    WorkgroupNewsDAO workgroupNewsDAO;

    @Test
    public void addWorkgroupNews(){
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        WorkgroupNews workgroupNews = new WorkgroupNews();
        workgroupNews.setWorkgroupId(165);
        workgroupNews.setDescription("ляля 222");
        workgroupNews.setTitle("заголовок 2222");
        workgroupNews.setNewsDate(date.format(new Date()));
        workgroupNewsDAO.addWorkgroupNews(workgroupNews);
    }



    @Test
    @Transactional
    @Rollback(true)
    public void testGetWorkgroupList(){
        for (int i = 0; i < 4; i++) {
            workgroupDAO.addWorkgroup(new Workgroup());
        }
        List<Workgroup> workgroupList =workgroupDAO.getAllWorkgroupList();
        Assert.assertNotEquals("",0,workgroupList.size());
        List<Long> wgIdList = new ArrayList<>();

        for (Workgroup wg: workgroupList) {
           wgIdList.add(wg.getId());

        }
        List<Workgroup> expectedList= workgroupDAO.getWorkgroupListByIdList(wgIdList);
        Assert.assertEquals("Not equals",workgroupList,expectedList);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testJustSout(){
        //System.out.println(workgroupDAO.getAllDepartments().toString());

        for (Workgroup wg: workgroupDAO.getDependentGroups(2)) {
            System.out.println(wg.getTitle() + " ,desc: " + wg.getDescription());
        }
    }
}
