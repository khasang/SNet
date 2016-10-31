package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.dao.AbstractRegistrySearcher;
import io.khasang.snet.entity.Chat;
import io.khasang.snet.entity.userauth.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

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
        Session session = getCurrentSession();
        Query query = session.createQuery("select ch FROM io.khasang.snet.entity.Chat ch where ");
        return (List<Chat>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Chat> getListSearched(User user) {
        Session session = getCurrentSession();
        Query query = session.createQuery("select ch FROM io.khasang.snet.entity.Chat ch, " +
                "io.khasang.snet.entity.ChatRegistryUnit r where ch.id = r.chat.id and r.user.id =:id_user");
        query.setParameter("id_user",user.getID());
        return (List<Chat>) query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
