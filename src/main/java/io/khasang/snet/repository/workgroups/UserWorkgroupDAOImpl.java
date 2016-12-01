package io.khasang.snet.repository.workgroups;

import io.khasang.snet.entity.workgroups.UserWorkgroups;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserWorkgroupDAOImpl implements UserWorkgroupDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public UserWorkgroupDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUserWorkgroups(UserWorkgroups userWorkgroupDAO) {
        sessionFactory.getCurrentSession().save(userWorkgroupDAO);
    }

    @Override
    public void updateUserWorkgroups(UserWorkgroups userWorkgroupDAO) {
        sessionFactory.getCurrentSession().update(userWorkgroupDAO);

    }

    @Override
    public void deleteUserWorkgroups(UserWorkgroups userWorkgroupDAO) {
        final Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().delete(userWorkgroupDAO);
        session.flush();
    }

    @Override
    public List<Long> getWorkgroupsByUser(long userId) {
        final Session session = sessionFactory.getCurrentSession();
        Query getWorkgroupsId =
                session.createQuery(
                        "select w.prKey.workgroupId from io.khasang.snet.entity.workgroups.UserWorkgroups w " +
                                " where w.prKey.userId = :userId");
        getWorkgroupsId.setParameter("userId", userId);
        return (List<Long>) getWorkgroupsId.list();

    }

    @Override
    public List<Long> getUsersByWorkgroup(long workGroupId) {
        final Session session = sessionFactory.getCurrentSession();
        Query getWorkgroupsId =
                session.createQuery(
                        "select w.prKey.userId from io.khasang.snet.entity.workgroups.UserWorkgroups w " +
                                " where w.prKey.workgroupId = :workGroupId");
        getWorkgroupsId.setParameter("workGroupId", workGroupId);
        return (List<Long>) getWorkgroupsId.list();
    }

}
