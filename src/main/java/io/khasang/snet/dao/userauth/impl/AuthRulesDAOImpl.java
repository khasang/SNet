package io.khasang.snet.dao.userauth.impl;

import io.khasang.snet.dao.userauth.AuthRulesDAO;
import io.khasang.snet.entity.userauth.AuthRules;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AuthRulesDAOImpl implements AuthRulesDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public AuthRulesDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addAuthRules(AuthRules authRules) {
        sessionFactory.getCurrentSession().save(authRules);
    }

    @Override
    public void deleteAllUserAuthRules(int user_id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(AuthRules.class);
        criteria.add(Restrictions.eq("user_id", user_id));
        List<AuthRules> list=(List<AuthRules>) criteria.list();

        final Session session = sessionFactory.getCurrentSession();

        for (AuthRules ar: list) {
            session.delete(ar);
        }
        session.flush();
    }

    @Override
    public void deleteAllRoleAuthRules(int role_id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(AuthRules.class);
        criteria.add(Restrictions.eq("role_id", role_id));
        List<AuthRules> list=(List<AuthRules>) criteria.list();

        final Session session = sessionFactory.getCurrentSession();

        for (AuthRules ar: list) {
            session.delete(ar);
        }
        session.flush();
    }

    @Override
    public List<AuthRules> getAllUserAuthRules(int user_id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(AuthRules.class);
        criteria.add(Restrictions.eq("user_id", user_id));
       return (List<AuthRules>) criteria.list();
    }
}
