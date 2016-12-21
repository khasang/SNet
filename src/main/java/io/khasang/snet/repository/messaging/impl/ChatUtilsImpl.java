package io.khasang.snet.repository.messaging.impl;

import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import io.khasang.snet.repository.DataUtils;
import io.khasang.snet.repository.messaging.ChatUtils;
import io.khasang.snet.repository.messaging.RegistryUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/* Implementation of io.khasang.snet.repository.messaging.ChatUtils
* based on io.khasang.snet.repository.DataUtils provides obtain objects
* from db, using ancestor's generic method, respectively, decreasing amount
 * of code that will written
 * */
@Repository
@Transactional
public class ChatUtilsImpl extends DataUtils<Chat> implements ChatUtils {

    private RegistryUtils registryUtils;

    @Autowired
    public ChatUtilsImpl(SessionFactory sessionFactory, RegistryUtils registryUtils) {
        super(Chat.class, sessionFactory);
        this.registryUtils = registryUtils;
    }

    @Override
    public void saveChat(Chat chat) {
        this.add(chat);
    }

    @Override
    public Chat findChatById(Serializable id) {
        return this.get(id);
    }

    @Override
    public void deleteChatById(Serializable id) {
        Chat willRemoved = this.get(id);
        this.delete(willRemoved);
    }

    /* Get list of chats for current user */
    @Override
    public List<Chat> getUsersChatsById(User user) {
        List<Chat> result = new ArrayList<>();
        List<ChatRegistryUnit> units = registryUtils.getRegistriesByUser(user);
        units.forEach(registryUnit -> result.add(this.get(registryUnit.getChat().getID())));
        return result;
    }

    /* Returns dialog of two users, if exist, otherwise returns null
    * @param first and @param second user's that (probably) have dialog
    * */
    @Override
    public Chat getDialogByIds(User first, User second) {
        List<ChatRegistryUnit> usersRegistries = registryUtils.getRegistriesByUser(first);
        usersRegistries.addAll(registryUtils.getRegistriesByUser(second));

        Chat result = dialogSearcher(usersRegistries);
        if (result != null)
            result = this.get(result.getID());

        return result;
    }

    /* Takes as parameter list registries, and search dialog */
    private Chat dialogSearcher(List<ChatRegistryUnit> original) {
        Map<Chat, List<User>> discusses = new HashMap<>();

        for (ChatRegistryUnit registryUnit : original) {
            if (discusses.containsKey(registryUnit.getChat())) {
                discusses.get(registryUnit.getChat()).add(registryUnit.getUser());
            } else {
                discusses.put(registryUnit.getChat(), new ArrayList<>());
                discusses.get(registryUnit.getChat()).add(registryUnit.getUser());
            }

        }

        for (Map.Entry<Chat, List<User>> chatListEntry : discusses.entrySet()) {
            if (chatListEntry.getValue().size()==2) {
                return chatListEntry.getKey();
            }
        }
        return null;
    }
}
