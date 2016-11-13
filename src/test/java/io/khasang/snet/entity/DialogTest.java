package io.khasang.snet.entity;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.dao.userauth.UserDAO;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.util.Generator;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class DialogTest {

    private static final Logger LOG = Logger.getLogger(DialogTest.class);

    @Autowired
    private AbstractCRUD<ChatRegistryUnit> registryCRUD;

    @Autowired
    private AbstractRegistrySearcher<Chat, User> chatCRUD;

    @Autowired
    private Generator<Chat> chatGenerator;

    @Autowired
    private UserDAO userDAO;

    @Test
    @Rollback
    @Transactional
    public void findDialog() {
        List<Chat> chatList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        Random random = new Random(47);

        for (int i = 0; i < 10; i++) {
            Chat chat = chatGenerator.create();
            chatList.add(chat);
        }
        LOG.debug(String.format("Added %d chats into list", chatList.size()));

        for (int i = 0; i < 4; i++) {
            userList.add(new User());
        }
        LOG.debug(String.format("Added %d users into list", userList.size()));

        for (Chat chat : chatList) {
            chatCRUD.add(chat);
        }
        LOG.debug("All chats added into database");


        for (User user : userList) {
            userDAO.addUser(user);
        }
        LOG.debug("All users added into database");

        for (Chat chat : chatList) {
            registryCRUD.add(new ChatRegistryUnit(chat, userList.get(random.nextInt(4))));
        }
        LOG.debug("All chats and users registered into chat registry");

        User first = new User();
        userDAO.addUser(first);
        LOG.debug(String.format("First user added into database with id:%d",first.getID()));

        User second = new User();
        userDAO.addUser(second);
        LOG.debug(String.format("Second user added into database with id:%d",second.getID()));

        int tmp = random.nextInt(10);
        registryCRUD.add(new ChatRegistryUnit(chatList.get(tmp), first));
        // If you comment string below chat will failed
        registryCRUD.add(new ChatRegistryUnit(chatList.get(tmp), second));
        LOG.debug(String.format("First and second user registered into database with chat:%d",chatList.get(tmp).getID()));

        assertEquals(chatList.get(tmp), chatCRUD.getExistedDialog(first, second));
    }
}
