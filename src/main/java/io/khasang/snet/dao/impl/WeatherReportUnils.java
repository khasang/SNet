package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.HibernateDAO;
import io.khasang.snet.entity.WeatherReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class WeatherReportUnils  implements HibernateDAO<WeatherReport> {

    private SessionFactory sessionFactory;

    public WeatherReportUnils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(WeatherReport weatherReport) {
        Session session = getCurrentSession();
        session.save(weatherReport);
    }

    @Override
    public WeatherReport get(WeatherReport weatherReport) {
        Session session = getCurrentSession();
        return session.get(WeatherReport.class,weatherReport.getID());
    }

    @Override
    public void edit(WeatherReport weatherReport) {
        Session session = getCurrentSession();
        WeatherReport oldWeatherReport = session.get(WeatherReport.class,weatherReport.getID());
        oldWeatherReport.setCityName(weatherReport.getCityName());
        oldWeatherReport.setHighestTemp(weatherReport.getHighestTemp());
        oldWeatherReport.setLowesrTemp(weatherReport.getLowesrTemp());
        oldWeatherReport.setTimeStamp(weatherReport.getTimeStamp());
        session.save(oldWeatherReport);
    }

    @Override
    public void delete(WeatherReport weatherReport) {
        Session session = getCurrentSession();
        WeatherReport report = session.get(WeatherReport.class, weatherReport.getID());
        session.delete(report);
        session.flush();
    }

    @Override
    public List<WeatherReport> getAll() {
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM io.khasang.snet.entity.WeatherReport");
        return (List<WeatherReport>) query.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
