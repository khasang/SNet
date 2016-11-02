package io.khasang.snet.entity;

import io.khasang.snet.config.AppConfig;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.dao.userauth.AuthRulesDAO;
import io.khasang.snet.dao.userauth.RolesDAO;
import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.Roles;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.util.Generator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private AuthRulesDAO authRulesDAO;

    @Autowired
    private RolesDAO rolesDAO;

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
    /* Creation ADMIN user account */
    @Test
    public void createPrimeEntry() {
        String login = "admin";
        String passwd = "admin";
        String primeRole = "ROLE_ADMIN";

        /* Creation admin account */
        User admin = new User();
        admin.setLogin(login);
        admin.setPassword(new BCryptPasswordEncoder().encode(passwd));
        userCRUD.add(admin);

        /* Creation admin role */
        Roles prime = new Roles();
        prime.setRole(primeRole);
        rolesDAO.addRoles(prime);

        /* Creation of admin authorisation rule */
        AuthRules rule = new AuthRules();
        rule.setUser_id(admin.getID());
        rule.setRole_id(prime.getId());
        authRulesDAO.addAuthRules(rule);

    }


    @Test
    @Rollback
    @Transactional
    public void saveTest() {
        ChatRegistryUnit original = new ChatRegistryUnit();
        original.setChat(chat);
        original.setUser(user);
        registryCRUD.add(original);

        ChatRegistryUnit founded = registryCRUD.get(original);
        assertEquals(original, founded);

        // passed just if registry table not exists any rows
        registryCRUD.add(original);
        List<ChatRegistryUnit> registryUnits = registryCRUD.getAll(null);
        assertEquals(1,registryUnits.size());
    }


    /* This test require tables without
    * any rows */
    @Test
    @Rollback
    @Transactional
    public void gettingListTest() {
        User anotherUser = new User();
        userCRUD.add(anotherUser);

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
