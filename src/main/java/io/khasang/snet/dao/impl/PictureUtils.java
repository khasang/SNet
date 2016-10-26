package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.Picture;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class implements DAO for
 * Picture entities
 */
@Repository
@Transactional
public class PictureUtils implements AbstractCRUD<Picture> {
    private final SessionFactory sessionFactory;

    public PictureUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Picture picture) {
        this.getCurrentSession().save(picture);
    }

    @Override
    public Picture get(Picture picture) {
        Criteria criteria = this.getCurrentSession().createCriteria(Picture.class);
        criteria.add(Restrictions.eq("id",picture.getID()));
        return (Picture) criteria.uniqueResult();
    }

    @Override
    public void edit(Picture picture) {
        Picture oldOne = this.getCurrentSession().get(Picture.class, picture.getID());
        oldOne.setDescription(picture.getDescription());
        oldOne.setImageBody(picture.getImageBody());
        this.getCurrentSession().update(oldOne);
    }

    @Override
    public void delete(Picture picture) {
        final Session session = this.getCurrentSession();
        session.delete(picture);
        session.flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Picture> getAll() {
        Criteria criteria = this.getCurrentSession().createCriteria(Picture.class);
        return (List<Picture>) criteria.list();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
