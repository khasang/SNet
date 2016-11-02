package io.khasang.snet.entity;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.entity.common.EntityBasicCRUDTestSuite;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.util.Generator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;

/**
 * Basic tests for entity Chat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class ChatTest {

    @Autowired
    private AbstractRegistrySearcher<Chat,User> chatCRUD;

    @Autowired
    private Generator<Chat> generator;

    private EntityBasicCRUDTestSuite<Chat> suite;

    @Before
    public void setUp() {
        if (suite==null) suite = new EntityBasicCRUDTestSuite<>(chatCRUD);
    }

    @Test
    public void equalsTest() {
        Chat first = generator.create();
        Chat same = first;                                 // Не хочет работать через new Chat(first.getDescription())
        Chat different = generator.create();

        System.out.println(first);
        System.out.println(same);
        System.out.println(different);
        assertTrue("Failed equals test", suite.testEquals(first,same,different));
    }

    @Test
    public void saveLoadTest() {
        Chat one = generator.create();
        Chat another = suite.testSaveAndLoad(one);
        assertEquals("Failed saving and load: object must equals", one, another);
    }

    @Test
    public void deleteTest() {
        Chat one = generator.create();
        assertNull("Failed delete test: returned Chat must be null", suite.testDelete(one));
    }

    @Test
    public void listTest() {
        HashSet<Chat> chats = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            chats.add(generator.create());
        }

        assertEquals("Failed packet test: returned quatity must be zero", 0,suite.testForLists(chats,null));
    }
}
