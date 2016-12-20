package io.khasang.snet.repository.messaging;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.repository.AbstractRegistrySearcher;
import io.khasang.snet.repository.userauth.UserDAO;
import org.apache.log4j.Logger;

/* Utility class for creating test samples */
class MessagingSuite {
    // Creates chat's sample with AbstractRegistrySearcher instance
    static Chat getSampleChat(
            Logger LOGGER,
            AbstractRegistrySearcher<Chat, User> chatUtils, String desc) {
        Chat chat = new Chat(desc);
        chatUtils.add(chat);
        LOGGER.debug(String.format("Saved new chat entity: [%s]", chat));
        return chat;
    }

    // Creates chat's sample with ChatUtils instance
    static Chat getSampleChat(
            Logger LOGGER, ChatUtils chatUtils, String desc) {
        Chat chat = new Chat(desc);
        chatUtils.saveChat(chat);
        LOGGER.debug(String.format("Saved new chat entity: [%s]", chat));
        return chat;
    }

    // Creates user's sample
    static User getSampleUser(Logger LOGGER, UserDAO userDAO, String login) {
        User user = new User();
        user.setLogin(login);
        userDAO.addUser(user);
        LOGGER.debug(String.format("Saved new user entity: [%s]", user.getLogin()));
        return user;
    }
}
