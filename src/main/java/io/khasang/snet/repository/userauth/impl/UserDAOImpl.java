package io.khasang.snet.repository.userauth.impl;

import io.khasang.snet.repository.userauth.UserDAO;
import io.khasang.snet.entity.userauth.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public User getUserById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public List<User> getAllUsers(String login) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(User.class);
        criteria.add(Restrictions.ne("login", login));
        return (List<User>) criteria.list();
    }

    @Override
    public List<User> getUsersByIdList(List<Long> idList) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.userauth.User u where u.id in :idlist");
        List<Integer> intList = new ArrayList<>();

        for (Long lon:idList) {
            intList.add(Math.toIntExact(lon));
        }

        if (idList.size()==0){
            query.setParameter("idlist",0);
        }
        else {
            query.setParameterList("idlist", intList);
        }
        return (List<User>) query.list();
    }

    @Override
    public List<User> getUsersNotInIdList(List<Long> idList) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.userauth.User u where u.id not in :idlist");
        List<Integer> intList = new ArrayList<>();

        for (Long lon:idList) {
            intList.add(Math.toIntExact(lon));
        }

        if (idList.size()==0){
            query.setParameter("idlist",0);
        }
        else {
            query.setParameterList("idlist", intList);
        }
        return (List<User>) query.list();
    }
}
