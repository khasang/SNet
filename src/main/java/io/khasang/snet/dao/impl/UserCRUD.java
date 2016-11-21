package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.userauth.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/* This addition dao implemented for test
* purposes can be replaced in main dao class
* or implemented fully */

@Repository
@Transactional
public class UserCRUD implements AbstractCRUD<User> {
    private final SessionFactory sessionFactory;

    public UserCRUD(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User entity) {
        Session session = getCurrentSession();
        session.save(entity);
        session.flush();
    }

    @Override
    public User get(User entity) {
        return null;
    }

    @Override
    public void edit(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> getAll(User entity) {
        return null;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
