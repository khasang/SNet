package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.Chat;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ChatUtils implements AbstractCRUD<Chat> {

    private SessionFactory sessionFactory;

    public ChatUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Chat chat) {
        Session session = getCurrentSession();
        session.save(chat);
    }

    @Override
    public Chat get(Chat chat) {
        Session session = getCurrentSession();
        return session.get(Chat.class,chat.getID());
    }

    @Override
    public void edit(Chat chat) {

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
        Query query = session.createQuery("FROM io.khasang.snet.entity.Chat");
        return (List<Chat>) query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
