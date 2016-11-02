package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.CityLocation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CityLocationUtils implements AbstractCRUD<CityLocation> {

    private SessionFactory sessionFactory;

    public CityLocationUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(CityLocation cityLocation) {
        Session session = getCurrentSession();
        session.save(cityLocation);
    }

    @Override
    public CityLocation get(CityLocation cityLocation) {
        Session session = getCurrentSession();
        return session.get(CityLocation.class,cityLocation.getID());
    }

    @Override
    public void edit(CityLocation cityLocation) {
        Session session = getCurrentSession();
        CityLocation oldLocation = session.get(CityLocation.class,cityLocation.getID());
        oldLocation.setCityName(cityLocation.getCityName());
        oldLocation.setLocation(cityLocation.getLocation());
        session.save(oldLocation);
    }

    @Override
    public void delete(CityLocation cityLocation) {
        Session session = getCurrentSession();
        CityLocation location = session.get(CityLocation.class, cityLocation.getID());
        session.delete(location);
        session.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CityLocation> getAll(CityLocation cityLocation) {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM io.khasang.snet.entity.CityLocation");
        return (List<CityLocation>) query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
