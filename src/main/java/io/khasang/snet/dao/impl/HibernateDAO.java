package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.HibernateDAO;
import io.khasang.snet.entity.CityLocation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HibernateDAO implements HibernateDAO<CityLocation, Long> {

    private SessionFactory sessionFactory;

    public HibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(CityLocation cityLocation) {
        Session session = getCurrentSession();
        session.save(cityLocation);
    }

    @Override
    public CityLocation get(Long key) {
        Session session = getCurrentSession();
        return session.get(CityLocation.class,key);
    }

    @Override
    public void edit(CityLocation cityLocation) {
        Session session = getCurrentSession();
        CityLocation oldLocation = session.get(CityLocation.class,cityLocation.getId());
        oldLocation.setCityName(cityLocation.getCityName());
        oldLocation.setLocation(cityLocation.getLocation());
        session.save(oldLocation);
    }

    @Override
    public void delete(Long key) {
        Session session = getCurrentSession();
        CityLocation location = session.get(CityLocation.class, key);
        session.delete(location);
        session.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CityLocation> getAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM io.khasang.snet.entity.CityLocation");
        return (List<CityLocation>) query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
