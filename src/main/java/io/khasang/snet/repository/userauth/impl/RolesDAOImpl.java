package io.khasang.snet.repository.userauth.impl;

import io.khasang.snet.repository.userauth.RolesDAO;
import io.khasang.snet.entity.userauth.Roles;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RolesDAOImpl implements RolesDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public RolesDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRoles(Roles roles) {
        sessionFactory.getCurrentSession().save(roles);
    }

    @Override
    public void deleteRolesById(int id) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(getRolesById(id));
        session.flush();
    }

    @Override
    public void deleteRolesByName(String role) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(getRolesByName(role));
        session.flush();
    }

    @Override
    public Roles getRolesById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Roles.class);
        criteria.add(Restrictions.eq("id", id));
        return (Roles) criteria.uniqueResult();
    }

    @Override
    public Roles getRolesByName(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Roles.class);
        criteria.add(Restrictions.eq("role", name));
        return (Roles) criteria.uniqueResult();
    }
}
