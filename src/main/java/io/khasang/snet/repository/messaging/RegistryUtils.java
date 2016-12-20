package io.khasang.snet.repository.messaging;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;

import java.util.List;

/* Interface for work with
* chat-user registry */
public interface RegistryUtils {
    // Saves new registry unit
    void saveRegistry(ChatRegistryUnit registry);
    // Get existed registry by chat and user
    ChatRegistryUnit getRegistryByChatAndUser(Chat chat, User user);
    // Takes list of registries user's that been given
    List<ChatRegistryUnit> getRegistriesByUser(User user);
    // Removes registry, by it's chat and user
    void deleteRegistry(Chat chat, User user);

}
