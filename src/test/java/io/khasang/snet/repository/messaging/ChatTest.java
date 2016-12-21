package io.khasang.snet.repository.messaging;

import io.khasang.snet.config.AppContext;
import io.khasang.snet.config.HibernateConfig;
import io.khasang.snet.config.application.WebConfig;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.repository.userauth.UserDAO;
import static io.khasang.snet.repository.messaging.MessagingSuite.*;
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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppContext.class, HibernateConfig.class, WebConfig.class})
public class ChatTest {
    private static final Logger LOGGER = Logger.getLogger(ChatTest.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ChatUtils chatUtils;

    @Autowired
    private RegistryUtils registryUtils;

    /* Test for chat's saving, loading and deleting */
    @Test
    @Rollback
    @Transactional
    public void loadingTest() {
        Chat chat = getSampleChat(LOGGER, chatUtils, "Loading Test Chat");
        assertEquals(chat, chatUtils.findChatById(chat.getID()));

        chatUtils.deleteChatById(chat.getID());
        LOGGER.debug(String.format("Removing chat [%s] from DataBase", chat));
        chat = chatUtils.findChatById(chat.getID());
        LOGGER.debug(String.format("Chat from DataBase must be null, factual: %s", chat));
        assertNull(chat);
    }

    @Test
    @Rollback
    @Transactional
    public void gettingListByUser() {
        User first = getSampleUser(LOGGER, userDAO, "List Test User #1");
        User another = getSampleUser(LOGGER, userDAO, "List Test User #2");
        List<Chat> originalChats = getListSamples(LOGGER, chatUtils, "List Test Original Chat", 5);
        List<Chat> anotherChats = getListSamples(LOGGER, chatUtils, "List Test Another Chat", 5);

        originalChats.forEach(chat -> registryUtils.saveRegistry(new ChatRegistryUnit(chat, first)));
        anotherChats.forEach(chat -> registryUtils.saveRegistry(new ChatRegistryUnit(chat, another)));

        List<Chat> loadedChats = chatUtils.getUsersChatsById(first);
        assertTrue(loadedChats.containsAll(originalChats));
    }

    @Test
    @Rollback
    @Transactional
    public void dialogTest() {
        // Setting up test
        User first = getSampleUser(LOGGER, userDAO, "Dialog Test User #1");
        User second = getSampleUser(LOGGER, userDAO, "Dialog Test User #2");
        User stranger1 = getSampleUser(LOGGER, userDAO, "Dialog Test Stranger #1");
        User stranger2 = getSampleUser(LOGGER, userDAO, "Dialog Test Stranger #2");
        User stranger3 = getSampleUser(LOGGER, userDAO, "Dialog Test Stranger #3");
        List<Chat> chats = getListSamples(LOGGER, chatUtils, "Dialog Test Chat", 20);

        // Set up test situation within database
        setUpTestSituation(first, second, new User[]{stranger1, stranger2, stranger3, first, second}, chats);


        // Sanity check
        assertTrue("Sanity check failed",
                sanityCheck(chats, first, second, new User[]{ stranger1, stranger2, stranger3 }));

        /* Finally testing 'getDialogByIds' method,
         using created and checked test data */
        Chat probablyDialog = chatUtils.getDialogByIds(first, second);
        assertTrue(checkDialog(probablyDialog, first, second, new User[]{stranger1, stranger2, stranger3}));
    }

    /* Checked presence at least one dialog between first and second users */
    private boolean sanityCheck(List<Chat> chats, User first, User second, User[] others) {
        for (Chat chat : chats) {
            if (checkDialog(chat, first, second, others)) return true;
        }
        return false;
    }

    /* Setting up set up, creates random chat, and exactly one dialog
    * @param first: test user #1
    * @param second: test user #2
    * @param users: all test users
    * @param chats: all test chats  */
    private void setUpTestSituation(User first, User second, User[] users, List<Chat> chats) {
        Random random = new Random(47);
        int needle = random.nextInt(chats.size()); // Index of target chat

        for (int i = 0; i < chats.size()-1; i++) {
            registryUtils.saveRegistry(
                    new ChatRegistryUnit(
                            chats.get(i),
                            users[random.nextInt(users.length)]
                    ));
        }
        Chat targetChat = getSampleChat(LOGGER, chatUtils, "Target Chat");
        registryUtils.saveRegistry(new ChatRegistryUnit(targetChat, first));
        registryUtils.saveRegistry(new ChatRegistryUnit(targetChat, second));
        chats.add(needle, targetChat);
        LOGGER.debug(String.format(
                "Test situation created, %s number in chat list: %d",
                targetChat, needle));
    }

    /* Checks for presence dialog between 2 users, and
     * absence same dialog in registries of other users.
     * @param chat: testing dialog
     * @param first: first testing user
     * @param second : second testing user
     * @param strangers: other users  */
    private boolean checkDialog(Chat chat, User first, User second, User[] strangers) {
        long[] firstIds = listRegistry2Array(registryUtils.getRegistriesByUser(first));
        LOGGER.debug(String.format("Chats of the first user %s", Arrays.toString(firstIds)));

        long[] secondIds = listRegistry2Array(registryUtils.getRegistriesByUser(second));
        LOGGER.debug(String.format("Chats of the first user %s", Arrays.toString(firstIds)));

        // Checking lack of target chat in registries of 'strangers'
        for (User stranger : strangers) {
            long[] ids = listRegistry2Array(registryUtils.getRegistriesByUser(stranger));
            if (Arrays.binarySearch(ids, chat.getID()) >= 0) return false;
        }

        // Checking presence of chat in registries both target users
        return !(
                (Arrays.binarySearch(firstIds, chat.getID()) < 0) &&
                        (Arrays.binarySearch(secondIds, chat.getID()) < 0));
    }

    /* Create array of chat's from given list of registries
    * @param units: registry units of one user
    * @return array of chat's ids of someone user */
    private long[] listRegistry2Array (List<ChatRegistryUnit> units) {
        long[] result = new long[units.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = units.get(i).getChat().getID();
        }
        Arrays.sort(result);
        return result;
    }

}
