package io.khasang.snet.repository.messaging;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.userauth.User;

import java.io.Serializable;
import java.util.List;

public interface ChatUtils {
    void saveChat(Chat chat);
    Chat findChatById(Serializable id);
    void deleteChatById(Serializable id);
    List<Chat> getUsersChatsById(User user);
    Chat getDialogByIds(User first, User second);
}
