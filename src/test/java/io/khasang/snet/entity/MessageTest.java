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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

/**
 * There basic CRUD test for message
 * entities
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class MessageTest {

    @Autowired
    private AbstractCRUD<Message> dataUtilMessages;

    @Autowired
    private AbstractCRUD<Chat> chatAbstractCRUD;

    @Autowired
    private Generator<Message> messageGenerator;

    @Autowired
    private Generator<Chat> chatGenerator;

    private EntityBasicCRUDTestSuite<Message> suite;
    private Chat chat;

    @Before
    public void setUp() {
        if (suite==null) suite = new EntityBasicCRUDTestSuite<>(dataUtilMessages);
        if (chat==null) {
            this.chat = chatGenerator.create();
            chatAbstractCRUD.add(chat);
        }
    }

    @Test
    public void equalsTest() {
        Message first = messageGenerator.create();
        Message same = new Message(
                first.getSender(),
                first.getChat(),
                first.getBody(),
                first.getStamp()
        );
        Message another = messageGenerator.create();
       assertTrue(suite.testEquals(first,same,another));
    }

    @Test
    public void savingTest() {
        Message message = messageGenerator.create();
        message.setChat(chat);
        Message another = suite.testSaveAndLoad(message);
        assertEquals(message, another);
    }

    @Test
    public void deletingTest() {
        Message message = messageGenerator.create();
        message.setChat(chat);
        assertNull(suite.testDelete(message));
    }

    @Test
    public void gettingListTest() {
        Collection<Message> messages = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Message message = messageGenerator.create();
            message.setChat(chat);
            messages.add(message);
        }

        Message chimerical = messageGenerator.create();
        chimerical.setChat(chat);
        assertEquals(messages.size(),suite.testForLists(messages,chimerical));
    }
}
