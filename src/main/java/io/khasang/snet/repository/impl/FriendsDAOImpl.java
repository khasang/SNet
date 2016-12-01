package io.khasang.snet.repository.impl;

import io.khasang.snet.repository.FriendsDAO;
import io.khasang.snet.entity.Friends;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FriendsDAOImpl implements FriendsDAO{

    private SessionFactory sessionFactory;

    @Autowired
    public  FriendsDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public void saveFriend(Friends friend) {
        sessionFactory.getCurrentSession().save(friend);
    }

    @Override
    public void updateFriend(Friends friend) {
        sessionFactory.getCurrentSession().update(friend);
    }

    @Override
    public List<Friends> getFriendsList(long userId) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Friends.class);
        criteria.add(Restrictions.eq("idInUsers", userId));
        criteria.add(Restrictions.eq("approved",true));
        return (List<Friends>) criteria.list();
    }

    @Override
    public List<Friends> getInviteList(long friendId) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Friends.class);
        criteria.add(Restrictions.eq("idInFriends", friendId));
        criteria.add(Restrictions.eq("approved",false));
        return (List<Friends>) criteria.list();
    }

    @Override
    public List<Friends> getSendedInviteList(long userId) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Friends.class);
        criteria.add(Restrictions.eq("idInUsers", userId));
        criteria.add(Restrictions.eq("approved",false));
        return (List<Friends>) criteria.list();
    }

    @Override
    public Friends getFriendLine(long id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Friends.class);
        criteria.add(Restrictions.eq("id", id));
        return (Friends) criteria.uniqueResult();
    }

    @Override
    public Friends getInviteLine(long user, long friend) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Friends.class);
        criteria.add(Restrictions.eq("idInUsers", user));
        criteria.add(Restrictions.eq("idInFriends", friend));
        return (Friends) criteria.uniqueResult();
    }
}
