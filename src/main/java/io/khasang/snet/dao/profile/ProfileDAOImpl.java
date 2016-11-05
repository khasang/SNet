package io.khasang.snet.dao.profile;

import io.khasang.snet.entity.profile.Profile;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
    public void addProfile(Profile profile) {
        sessionFactory.getCurrentSession().save(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        sessionFactory.getCurrentSession().update(profile);

    }

    @Override
    public Profile getProfileByUserLogin(String userLogin) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Profile.class);
        criteria.add(Restrictions.eq("login",userLogin));
        return (Profile) criteria.uniqueResult();
    }

}
