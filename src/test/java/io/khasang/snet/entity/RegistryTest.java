package io.khasang.snet.entity;

import io.khasang.snet.config.AppContext;
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
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppContext.class, WebConfig.class, HibernateConfig.class})
public class RegistryTest {

    @Autowired
    private AbstractCRUD<ChatRegistryUnit> registryCRUD;

    @Autowired
    private AbstractRegistrySearcher<Chat, User> chatCRUD;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Generator<Chat> chatGenerator;

    private Chat chat;
    private User user;

    @Test
    @Rollback
    @Transactional
    public void saveTest() {
        chat = chatGenerator.create();
        chatCRUD.add(chat);

        user = new User();
        userDAO.addUser(user);

        ChatRegistryUnit original = new ChatRegistryUnit();
        original.setChat(chat);
        original.setUser(user);
        registryCRUD.add(original);

        ChatRegistryUnit founded = registryCRUD.get(original);
        assertEquals(original, founded);

    }


    /* This test require tables without
    * any rows */
    @Test
    @Rollback
    @Transactional
    public void gettingListTest() {
        chat = chatGenerator.create();
        chatCRUD.add(chat);

        user = new User();
        userDAO.addUser(user);

        User anotherUser = new User();
        userDAO.addUser(anotherUser);

        // write few chats
        int amountEntities = 10;
        List<Chat> chats = new ArrayList<>(amountEntities);
        for (int i = 0; i < amountEntities; i++) {
            Chat c = chatGenerator.create();
            chatCRUD.add(c);
            chats.add(c);
        }

        // sign this chats with user in registry
        for (Chat chat1 : chats) {
            registryCRUD.add(new ChatRegistryUnit(chat1, anotherUser));
        }

        // getting all chat for user, must be not equals of amount written chats
        Collection<Chat> firstUsersChat = chatCRUD.getListSearched(user);
        assertNotEquals(amountEntities,firstUsersChat.size());

        // getting all chat for anotherUser, must be equals of amount written chats
        Collection<Chat> anotherUserChat = chatCRUD.getListSearched(anotherUser);
        assertEquals(amountEntities,anotherUserChat.size());

        // comparing obtained list of chats, and original one
        assertTrue(anotherUserChat.containsAll(chats));
    }
}
