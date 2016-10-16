package io.khasang.snet.service;

import io.khasang.snet.entity.CityLocation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityLocationService implements DataUtility<CityLocation, Long> {

    private SessionFactory sessionFactory;

    public CityLocationService(SessionFactory sessionFactory) {
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
    }

    @Override
    @SuppressWarnings("unchekced")
    public List<CityLocation> getAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM io.khasang.snet.entity.CityLocation");
        return query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
