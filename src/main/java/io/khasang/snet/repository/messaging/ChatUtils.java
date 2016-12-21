package io.khasang.snet.repository.messaging;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.userauth.User;

import java.io.Serializable;
import java.util.List;

/* Interface for working with chats */
public interface ChatUtils {
    // Saves chat's entities into database
    void saveChat(Chat chat);
    // Find some chat by id
    Chat findChatById(Serializable id);
    // Deletes existed chat by id
    void deleteChatById(Serializable id);
    // Get all chats of some user
    List<Chat> getUsersChatsById(User user);
    // Get existed dialog of two users
    Chat getDialogByIds(User first, User second);
}
