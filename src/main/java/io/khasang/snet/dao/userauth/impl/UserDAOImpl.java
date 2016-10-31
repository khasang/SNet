package io.khasang.snet.dao.userauth.impl;

import io.khasang.snet.dao.userauth.UserDAO;
import io.khasang.snet.entity.Post;
import io.khasang.snet.entity.userauth.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserByName(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.eq("login", name));
        return (User) criteria.uniqueResult();
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
