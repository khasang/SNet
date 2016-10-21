package io.khasang.snet.dao.impl;

import io.khasang.snet.dao.HibernateDAO;
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
public class PicturesDateUtil implements HibernateDAO<Picture,Long> {
    private final SessionFactory sessionFactory;

    public PicturesDateUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Picture picture) {
        this.getCurrentSession().save(picture);
    }

    @Override
    public Picture get(Long id) {
        Criteria criteria = this.getCurrentSession().createCriteria(Picture.class);
        criteria.add(Restrictions.eq("id",id));
        return (Picture) criteria.uniqueResult();
    }

    @Override
    public void edit(Picture picture) {
        Picture oldOne = this.getCurrentSession().get(Picture.class, picture.getId());
        oldOne.setDescription(picture.getDescription());
        oldOne.setImageBody(picture.getImageBody());
        this.getCurrentSession().update(oldOne);
    }

    @Override
    public void delete(Long id) {
        final Session session = this.getCurrentSession();
        Picture picture = this.get(id);
        session.delete(picture);
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
