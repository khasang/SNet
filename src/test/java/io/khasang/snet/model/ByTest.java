package io.khasang.snet.model;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.application.WebConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class,})
public class ByTest {
    @Autowired
    By by;




    @Test
    public void getByMessageTest() {
        assertEquals("Something going wrong", "By", by.getByMessage());
    }

    @Test
    public void getUsersMessages(){
//        assertNotNull(new MessageSnet());
//        assertEquals("Hello!", messageSnet.getAllMessages);
    }
}
