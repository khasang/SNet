package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MessageUtils implements AbstractCRUD<Message> {

    private SessionFactory sessionFactory;

    public MessageUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Message message) {
        Session session = getCurrentSession();
        session.save(message);
    }

    @Override
    public Message get(Message message) {
        Session session = getCurrentSession();
        return session.get(Message.class,message.getID());
    }

    @Override
    public void edit(Message message) {

    }

    @Override
    public void delete(Message message) {
        Session session = getCurrentSession();
        Message report = session.get(Message.class, message.getID());
        session.delete(report);
        session.flush();
    }

    @Override
    public List<Message> getAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM io.khasang.snet.entity.Message");
        return (List<Message>) query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
