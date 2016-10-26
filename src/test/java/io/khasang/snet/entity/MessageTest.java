package io.khasang.snet.entity;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.common.EntityBasicCRUDTestSuite;
import io.khasang.snet.util.Generator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * There basic CRUD test for message
 * entities
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class MessageTest {
    @Autowired
    private AbstractCRUD<Message> dataUtilMesseges;

    @Autowired
    private Generator<Message> generator;

    private EntityBasicCRUDTestSuite<Message> suite;

    @Before
    public void setUp() {
        if (suite==null) suite = new EntityBasicCRUDTestSuite<>(dataUtilMesseges);
    }

    @Test
    public void equalsTest() {
        Message first = generator.create();
        Message same = new Message(
                first.getSender(),
                first.getReceiver(),
                first.getBody(),
                first.getStamp()
        );
        Message different = generator.create();

        assertTrue("Failed equals test", suite.testEquals(first,same,different));
    }

    @Test
    public void saveLoadTest() {
        Message one = generator.create();
        Message another = suite.testSaveAndLoad(one);
        assertEquals("Failed saving and load: object must equals", one, another);
    }

    @Test
    public void udateTest() {
        Message one = generator.create();
        Message edited = generator.create();
        edited = suite.testUpdate(one,edited);
        assertNotEquals("Failed updating: object must differs", one, edited);
    }

    @Test
    public void deleteTest() {
        Message one = generator.create();
        assertNull("Failed delete test: returned Chat must be null", suite.testDelete(one));
    }

    @Test
    public void listTest() {
        HashSet<Message> chats = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            chats.add(generator.create());
        }

        assertEquals("Failed packet test: returned quatity must be zero", 0,suite.testForLists(chats));
    }
}
