package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.ProfileDAO;
import io.khasang.snet.entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProfileDAOImpl implements ProfileDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public ProfileDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void fillProfile(Profile profile) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(profile);

    }

    @Override
    public Profile getProfileByUserId(Long userId) {
        Session session =this.sessionFactory.getCurrentSession();
        Profile profile = (Profile) session.load(Profile.class, userId);
        return profile;
    }
}
