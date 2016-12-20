package io.khasang.snet.repository.messaging;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;

import java.io.Serializable;
import java.util.List;

public interface RegistryUtils {
    void saveRegistry(ChatRegistryUnit registry);
    ChatRegistryUnit getRegistryByChatAndUser(Chat chat, User user);
    List<ChatRegistryUnit> getRegistriesByUser(User user);
    void deleteRegistry(Chat chat, User user);

}
