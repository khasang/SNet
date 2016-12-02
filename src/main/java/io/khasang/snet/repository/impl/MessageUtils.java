package io.khasang.snet.repository.impl;

import io.khasang.snet.repository.AbstractCRUD;
import io.khasang.snet.entity.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MessageUtils implements AbstractCRUD<Message> {

    private SessionFactory sessionFactory;

    @Autowired
    public MessageUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Message message) {
        Session session = getCurrentSession();
        session.save(message);
        session.flush();
    }

    @Override
    public Message get(Message message) {
        Session session = getCurrentSession();
        return session.get(Message.class,message.getID());
    }

    @Override
    public void edit(Message message) {
        throw new UnsupportedOperationException("Can't edit messages");
    }

    @Override
    public void delete(Message message) {
        Session session = getCurrentSession();
        Message report = session.get(Message.class, message.getID());
        session.delete(report);
        session.flush();
    }

    /* Gives all messages, which have chat field equals
    * given @param message's field
    * @return list of available messages */
    @Override
    @SuppressWarnings("unchecked")
    public List<Message> getAll(Message message) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.Message m where m.chat = :chat");
        query.setParameter("chat", message.getChat());
        return (List<Message>) query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
