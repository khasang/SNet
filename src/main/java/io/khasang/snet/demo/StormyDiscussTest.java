package io.khasang.snet.demo;

import io.khasang.snet.repository.AbstractCRUD;
import io.khasang.snet.repository.AbstractRegistrySearcher;
import io.khasang.snet.repository.userauth.AuthRulesDAO;
import io.khasang.snet.repository.userauth.RolesDAO;
import io.khasang.snet.repository.userauth.UserDAO;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.Message;
import io.khasang.snet.entity.userauth.AuthRules;
import io.khasang.snet.entity.userauth.Roles;
import io.khasang.snet.entity.userauth.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;

/**
 * Use this test for create imitation of
 * lively, heated & stormy discuss
 * Generates few users, few chats and
 * vary of message
 */
@Component
public class StormyDiscussTest {
    private UserDAO userDAO;
    private AbstractRegistrySearcher<Chat, User> chatCRUD;
    private AbstractCRUD<Message> dataUtilMessages;
    private AbstractCRUD<ChatRegistryUnit> registryCRUD;
    private AuthRulesDAO authRulesDAO;
    private RolesDAO rolesDAO;

    @Autowired
    public StormyDiscussTest(UserDAO userDAO,
                             AbstractRegistrySearcher<Chat, User> chatCRUD,
                             AbstractCRUD<Message> dataUtilMessages,
                             AbstractCRUD<ChatRegistryUnit> registryCRUD,
                             AuthRulesDAO authRulesDAO, RolesDAO rolesDAO) {
        this.userDAO = userDAO;
        this.chatCRUD = chatCRUD;
        this.dataUtilMessages = dataUtilMessages;
        this.registryCRUD = registryCRUD;
        this.authRulesDAO = authRulesDAO;
        this.rolesDAO = rolesDAO;
    }

    private Roles userRole;
    private BCryptPasswordEncoder passwordEncoder;




    /* Creation of USER role if it's not exist
    * */
    private void createUserRole() {
        String roleUser = "ROLE_USER";
        userRole = rolesDAO.getRolesByName(roleUser);
        if (userRole==null) {
            userRole = new Roles();
            userRole.setRole(roleUser);
            rolesDAO.addRoles(userRole);
        }
    }


    public void createDialogs() {
        createUserRole();
        User neo = this.createUser("Neo", "mrAnderson");
        User morpheus = this.createUser("Morpheus", "morpheus");
        User smith = this.createUser("Agent Smith", "smith");
        User oracle = this.createUser("Oracle","piphia");

        Chat hotel = createChat("Old Hotel", morpheus, neo);
        Chat police = createChat("Police", smith, neo);
        Chat kitchen = createChat("Kitchen", oracle, neo);

        // in police
        sendMessage(neo, police, "You can't scare me with this Gestapo crap. I know my rights. I want my phone call.");
        sendMessage(smith, police, "Tell me, Mr. Anderson, what good is a phone call if you're unable to speak…");
        sendMessage(neo, police, "…");

        // in abandoned hotel
        sendMessage(morpheus, hotel, "You have the look of a man who accepts what he sees because he is " +
                "expecting to wake up. Ironically, this is not far from the truth. Do you believe in fate, Neo?");
        sendMessage(neo, hotel, "No.");
        sendMessage(morpheus, hotel, "Why not?");
        sendMessage(neo, hotel, "Because I don't like the idea that I'm not in control of my life.");
        sendMessage(morpheus, hotel, " I know exactly what you mean. Let me tell you why you're here. You're here " +
                "because you know something. What you know you can't explain. But you feel it. You've felt it your " +
                "entire life. That there's something wrong with the world. You don't know what it is but it's there, " +
                "like a splinter in your mind driving you mad. It is this feeling that has brought you to me. " +
                "Do you know what I'm talking about?");
        sendMessage(neo, hotel, "The Matrix?");
        sendMessage(morpheus, hotel, "Do you want to know what IT is? The Matrix is everywhere. It is all around us, " +
                "even now in this very room. You can see it when you look out your window or when you turn on your " +
                "television. You can feel it when you go to work, when you go to church, when you pay your taxes. " +
                "It is the world that has been pulled over your eyes to blind you from the truth.");
        sendMessage(neo, hotel, "What truth?");
        sendMessage(morpheus, hotel, "That you are a slave, Neo. Like everyone else you were born into bondage, " +
                "born into a prison that you cannot smell or taste or touch. A prison for your mind… Unfortunately, " +
                "no one can be told what the Matrix is. You have to see it for yourself. This is your last chance. " +
                "After this there is no turning back. You take the blue pill, the story ends, you wake up in your " +
                "bed and believe whatever you want to believe. You take the red pill, you stay in Wonderland, " +
                "and I show you how deep the rabbit hole goes…");

        // on oracle kitchen
        sendMessage(oracle, kitchen, "I'd ask you to sit down, but your not going to anyway. " +
                "And don't worry about the vase.");
        sendMessage(neo, kitchen, "What vase?");
        sendMessage(oracle, kitchen, "That vase.");
        sendMessage(neo, kitchen, "I'm sorry.");
        sendMessage(oracle, kitchen, "I said don't worry about it. I'll get one of my students to fix it.");
        sendMessage(neo, kitchen, "How did you know?");
        sendMessage(oracle, kitchen, "What's really going to bake your noodle later on is, " +
                "would you still have broken it if I hadn't said anything.");

    }

    private User createUser(String login, String passwd) {
        if (passwordEncoder==null) passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(passwd));
        userDAO.addUser(user);

        AuthRules authRule = new AuthRules();
        authRule.setRole_id(userRole.getId());
        authRule.setUser_id(user.getID());
        authRulesDAO.addAuthRules(authRule);

        return user;
    }

    private Chat createChat(String description, User... users) {
        Chat chat = new Chat(description);
        chatCRUD.add(chat);
        for (User user : users) {
            registryCRUD.add(new ChatRegistryUnit(chat, user));
        }
        return chat;
    }

    private void sendMessage(User sender, Chat chat, String body) {
        Message message = new Message(sender,chat,body,new GregorianCalendar());
        dataUtilMessages.add(message);
    }
}
