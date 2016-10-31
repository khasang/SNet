package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.ChatRegistryUnit;
import io.khasang.snet.entity.userauth.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class RegistryUtils implements AbstractCRUD<ChatRegistryUnit> {

    private SessionFactory sessionFactory;

    public RegistryUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(ChatRegistryUnit entity) {
        Session session = getCurrentSession();
        ChatRegistryUnit duplicateUnit = this.get(entity);
        if (duplicateUnit==null) {
            session.save(entity);
            session.flush();
        }
    }

    @Override
    public ChatRegistryUnit get(ChatRegistryUnit entity) {
        Session session = getCurrentSession();
        Query getByUserAndChat =
                session.createQuery(
                        "select r from io.khasang.snet.entity.ChatRegistryUnit r " +
                                "where r.chat =:chat_id and r.user =:user_id");
        getByUserAndChat.setParameter("chat_id",entity.getChat());
        getByUserAndChat.setParameter("user_id",entity.getUser());
        return (ChatRegistryUnit) getByUserAndChat.uniqueResult();
    }

    @Override
    public void edit(ChatRegistryUnit entity) {
        throw new UnsupportedOperationException("Edit operation for RegistryUnit elements are not allowed");
    }

    @Override
    public void delete(ChatRegistryUnit entity) {
        Session session = getCurrentSession();
        ChatRegistryUnit unit = this.get(entity);
        session.delete(unit);
        session.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ChatRegistryUnit> getAll(ChatRegistryUnit entity) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.ChatRegistryUnit");
        return query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
