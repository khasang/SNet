package io.khasang.snet.entity;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.dao.userauth.UserDAO;
import io.khasang.snet.entity.common.EntityBasicCRUDTestSuite;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.util.Generator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;

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
    private AbstractRegistrySearcher<Chat, User> chatCRUD;

    @Autowired
    private Generator<Message> messageGenerator;

    @Autowired
    private Generator<Chat> chatGenerator;

    private EntityBasicCRUDTestSuite<Message> suite;
    private Chat chat;
    private User user;

    @Before
    public void setUp() {
        if (suite==null) suite = new EntityBasicCRUDTestSuite<>(dataUtilMessages);
        if (chat==null) {
            this.chat = chatGenerator.create();
            this.chat.setID(1L);
            this.user = null;
            this.chat = chatCRUD.get(this.chat);
            if (this.chat==null) {
                this.chat = chatGenerator.create();
                chatCRUD.add(this.chat);
            }
        }
    }

    @Test
    @Rollback
    @Transactional
    public void equalsTest() {
        Message first = messageGenerator.create();
        first.setSender(user);
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
    @Rollback
    @Transactional
    public void savingTest() {
        Message message = messageGenerator.create();
        message.setChat(chat);
        message.setSender(user);
        Message another = suite.testSaveAndLoad(message);
        assertEquals(message, another);
    }

    @Test
    @Rollback
    @Transactional
    public void deletingTest() {
        Message message = messageGenerator.create();
        message.setChat(chat);
        message.setSender(user);
        assertNull(suite.testDelete(message));
    }

    @Test
    @Rollback
    @Transactional
    public void gettingListTest() {
        Collection<Message> messages = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Message message = messageGenerator.create();
            message.setChat(chat);
            message.setSender(user);
            messages.add(message);
        }

        Message chimerical = messageGenerator.create();
        chimerical.setChat(chat);
        assertEquals(messages.size(),suite.testForLists(messages,chimerical));
    }
}
