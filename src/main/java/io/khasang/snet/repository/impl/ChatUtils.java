package io.khasang.snet.repository.impl;

import io.khasang.snet.repository.AbstractRegistrySearcher;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/* Data access object for working with Chat entities
* allows queries by user for chat table and check registry
* */
@Repository
@Transactional
public class ChatUtils implements AbstractRegistrySearcher <Chat, User> {

    private SessionFactory sessionFactory;

    public ChatUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Chat chat) {
        Session session = getCurrentSession();
        session.save(chat);
        session.flush();
    }

    @Override
    public Chat get(Chat chat) {
        Session session = getCurrentSession();
        return session.get(Chat.class,chat.getID());
    }

    @Override
    public void edit(Chat chat) {
        throw new UnsupportedOperationException("Edit operations with chat are mot supported");
    }

    @Override
    public void delete(Chat chat) {
        Session session = getCurrentSession();
        Chat report = session.get(Chat.class, chat.getID());
        session.delete(report);
        session.flush();
    }

    @Override
    public List<Chat> getAll(Chat chat) {
        throw new UnsupportedOperationException("Quering all chats entities directly not allowed.");
    }
    /* Taking @param user, gives opportunity for search
    * in ChatRegistryUnit table unit with id of current user,
    * summary given all chats which id equals of id chat in selected registry rows
    * @return list of founded chats for given user */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Chat> getListSearched(User user) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select ch FROM io.khasang.snet.entity.Chat ch, " +
                "io.khasang.snet.entity.ChatRegistryUnit r where ch.id = r.chat.id and r.user.id =:id_user");
        query.setParameter("id_user",user.getID());
        return (List<Chat>) query.list();
    }

    @Override
    public Chat getExistedDialog(User first, User second) {
        List<ChatRegistryUnit> firstUserChats = getUsersRegistryUnits(first);
        List<ChatRegistryUnit> secondUserChats = getUsersRegistryUnits(second);

        firstUserChats.addAll(secondUserChats);

        Chat result = dialogSearcher(firstUserChats);
        if (result != null) result = this.get(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    private List<ChatRegistryUnit> getUsersRegistryUnits(User user) {
        Session session = getCurrentSession();
        Query query = session.createQuery(
                "select r from io.khasang.snet.entity.ChatRegistryUnit r where r.user.id =:user_id");
        query.setParameter("user_id",user.getID());
        return (List<ChatRegistryUnit>) query.list();
    }

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

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
