package io.khasang.snet.repository.profile;

import io.khasang.snet.entity.profile.Profile;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery(" UPDATE io.khasang.snet.entity.profile.Profile SET aboutMe = :aboutMe," +
                "city = :city, interests = :interests, name = :name," +
                "surname = :surname WHERE login = :userLogin");
        query.setParameter("aboutMe", profile.getAboutMe());
        query.setParameter("city", profile.getCity());
        query.setParameter("interests", profile.getInterests());
        query.setParameter("name", profile.getName());
        query.setParameter("surname", profile.getSurname());
        query.setParameter("userLogin", profile.getLogin());
        int result = query.executeUpdate();

    }

    @Override
    public void updateAvatar(String avatarName, String userLogin) {
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery(" UPDATE io.khasang.snet.entity.profile.Profile " +
                "SET avatar = :avatar WHERE login = :userLogin");
        query.setParameter("avatar",avatarName);
        query.setParameter("userLogin",userLogin);
        int result = query.executeUpdate();
    }

    @Override
    public Profile getProfileByUserLogin(String userLogin) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Profile.class);
        criteria.add(Restrictions.eq("login",userLogin));
        return (Profile) criteria.uniqueResult();
    }

    @Override
    public List<Profile> getSameProfile(String userLogin) {
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.profile.Profile u where u.login != :login");
        query.setParameter("login",userLogin);
        query.setMaxResults(5);
        return (List<Profile>) query.list();
    }

    @Override
    public List<Profile> getProfileLike(String userLoginLike, String userLogin) {
        Session session =  sessionFactory.getCurrentSession();
        Query query = session.createQuery("from io.khasang.snet.entity.profile.Profile u where u.login != :login and u.login like lower(:firstName)");
        query.setParameter("login",userLogin);
        query.setString("firstName", "%" + userLoginLike + "%");
        query.setMaxResults(20);
        return (List<Profile>) query.list();
    }

}
