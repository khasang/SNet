package io.khasang.snet.entity;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.util.Generator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, HibernateConfig.class})
public class RegistryTest {

    @Autowired
    private AbstractCRUD<ChatRegistryUnit> registryCRUD;

    @Autowired
    private AbstractRegistrySearcher<Chat, User> chatCRUD;

    @Autowired
    private AbstractCRUD<User> userCRUD;

    @Autowired
    private Generator<Chat> chatGenerator;

    private Chat chat;
    private User user;

    @Before
    public void addChat() {
        if (chat==null) {
            chat = chatGenerator.create();
            chatCRUD.add(chat);
        }
        if (user==null) {
            user = new User();
            userCRUD.add(user);
        }
    }


    @Test
    public void saveTest() {
        ChatRegistryUnit original = new ChatRegistryUnit();
        original.setChat(chat);
        original.setUser(user);
        registryCRUD.add(original);

        ChatRegistryUnit founded = registryCRUD.get(original);
        assertEquals(original, founded);

        registryCRUD.add(original);
        List<ChatRegistryUnit> registryUnits = registryCRUD.getAll(null);
        assertEquals(1,registryUnits.size());
    }


    @Test
    public void gettingListTest() {
        User anotherUser = new User();
        userCRUD.add(anotherUser);

        int amountEntities = 10;
        List<Chat> chats = new ArrayList<>(amountEntities);
        for (int i = 0; i < amountEntities; i++) {
            Chat c = chatGenerator.create();
            chatCRUD.add(c);
            chats.add(c);
        }

        for (int i = 0; i < chats.size(); i++) {
            registryCRUD.add(new ChatRegistryUnit(chats.get(i), anotherUser));
        }

        Collection<Chat> firstUsersChat = chatCRUD.getListSearched(user);
        assertNotEquals(amountEntities,firstUsersChat.size());

        Collection<Chat> anotherUserChat = chatCRUD.getListSearched(anotherUser);
        assertEquals(amountEntities,anotherUserChat.size());

        assertTrue(anotherUserChat.containsAll(chats));
    }
}
