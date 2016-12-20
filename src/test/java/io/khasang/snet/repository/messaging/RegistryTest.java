package io.khasang.snet.repository.messaging;

import io.khasang.snet.config.AppContext;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.repository.AbstractRegistrySearcher;
import io.khasang.snet.repository.userauth.UserDAO;
import org.apache.log4j.Logger;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppContext.class, HibernateConfig.class, WebConfig.class})
public class RegistryTest {
    private static final Logger LOGGER = Logger.getLogger(RegistryTest.class);

    @Autowired
    private RegistryUtils registryUtils;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AbstractRegistrySearcher<Chat, User> chatUtils;

    /* Test for saving and load entity */
    @Test
    @Rollback
    @Transactional
    public void saveTest() {
        // Creating test entities
        Chat chat = saveTestChat("Saving Test Chat");
        User user = saveTestUser("Saving Test User");
        ChatRegistryUnit registry = new ChatRegistryUnit(chat, user);
        registryUtils.saveRegistry(registry);
        LOGGER.debug(String.format("Saved new registry entity: [%s]", registry));

        // Assert if saved test entity differ from load
        assertEquals(registry, registryUtils.getRegistryByChatAndUser(chat, user));
    }

    /* Filter and load test entities of one user */
    @Test
    @Rollback
    @Transactional
    public void obtainListTest() {
        // Creating test entities
        User user1 = saveTestUser("Listing Test User #1");
        User user2 = saveTestUser("Listing Test User #2");
        List<Chat> chats = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            chats.add(saveTestChat(String.format("Listing test chat #%d",i+1)));
        }

        chats.forEach((chat -> registryUtils.saveRegistry(new ChatRegistryUnit(chat, user1))));
        chats.forEach((chat -> registryUtils.saveRegistry(new ChatRegistryUnit(chat, user2))));
        LOGGER.debug("Saved registries entity");

        // Load list registries of user1 only
        List<ChatRegistryUnit> registry = registryUtils.getRegistriesByUser(user1);
        LOGGER.debug(String.format("Obtained registry entities: [%s]", registry));
        assertTrue(registry.size() == chats.size());
    }

    @Test
    @Rollback
    @Transactional
    public void deletingTest() {
        // Creating test entities
        User user = saveTestUser("Deleting Test User");
        Chat chat = saveTestChat("Deleting Test Chat");
        registryUtils.saveRegistry(new ChatRegistryUnit(chat, user));
        LOGGER.debug("Saved test registry entity.");

        registryUtils.deleteRegistry(chat, user);
        LOGGER.debug("Removed test registry entity.");

        ChatRegistryUnit registry = registryUtils.getRegistryByChatAndUser(chat, user);
        LOGGER.debug(String.format("Registry from DataBase must be null, factual: %s", registry));
        assertNull(registry);
    }

    private Chat saveTestChat(String desc) {
        Chat chat = new Chat(desc);
        chatUtils.add(chat);
        LOGGER.debug(String.format("Saved new chat entity: [%s]", chat));
        return chat;
    }

    private User saveTestUser(String login) {
        User user = new User();
        user.setLogin(login);
        userDAO.addUser(user);
        LOGGER.debug(String.format("Saved new user entity: [%s]", user.getLogin()));
        return user;
    }
}
