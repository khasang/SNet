package io.khasang.snet.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import io.khasang.snet.entity.AbstractEntity;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

/* This abstract DAO class provides basic functions
* with entities (CRUD). For use this class, entity used in DAO
* must implements io.khasang.snet.entity.AbstractEntity
* */
public abstract class DataUtils<Entity extends AbstractEntity> {

    private SessionFactory sessionFactory;
    // Entity type mark
    private Class<Entity> typeMark;

    protected DataUtils(Class<Entity> type, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.typeMark = type;
    }

    /* Saving entity in database */
    protected void add(Entity entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        session.flush();
    }

    /* Getting existed entity by id
    * To use this method, your entity's id must implements java.io.Serializable
    * @param id - java.io.Serializable object
    * @return - founded entity
    * */
    protected Entity get(Serializable id) {
        return sessionFactory.getCurrentSession().get(typeMark, id);
    }

    /* Getting existed entity by single criterion
    * To use this method, create criterion, use org.hibernate.criterion.Restrictions
    * @param criterion - searching parameter
    * @return - founded entity
    * */
    @SuppressWarnings("unchecked")
    protected Entity get(Criterion criterion) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(typeMark);
        criteria.add(criterion);
        return (Entity) criteria.uniqueResult();
    }

    /* Getting existed entity by few criterions
    * To use this method, create criterion, use org.hibernate.criterion.Restrictions
    * @param criterions - array of searching parameters
    * @return - founded entity
    * */
    @SuppressWarnings("unchecked")
    protected Entity get(Criterion... criterions) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(typeMark);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return (Entity) criteria.uniqueResult();
    }

    /* Updating existed entity object */
    protected void update(Entity entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
        session.flush();
    }

    /* Deleting existed entity object */
    protected void delete(Entity entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
        session.flush();
    }

    /* Creates list of all entities, that have current type
     * @return list of Entities
     * */
    @SuppressWarnings("unchecked")
    protected List<Entity> getList() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(typeMark);
        return (List<Entity>) criteria.list();
    }

    /* Creates list of all entities, that have current type,
     * and satisfy given criterion
     * @param criterion - searching parameter
     * @return list of Entities
     * */
    @SuppressWarnings("unchecked")
    protected List<Entity> getList(Criterion criterion) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(typeMark);
        criteria.add(criterion);
        return (List<Entity>) criteria.list();
    }

    /* Creates list of all entities, that have current type,
     * and satisfy given criterions
     * @param criterions - searching parameters
     * @return list of Entities
     * */
    @SuppressWarnings("unchecked")
    protected List<Entity> getList(Criterion... criterions) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(typeMark);
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        return (List<Entity>) criteria.list();
    }

    /* Creates SimpleExpression object,
     * contains given name and value
     * @param name - property name
     * @param value - property value
     * @return SimpleExpression object
     * */
    protected Criterion createEqualsExpression(String name, Object value) {
        return Restrictions.eq(name, value);
    }
}
