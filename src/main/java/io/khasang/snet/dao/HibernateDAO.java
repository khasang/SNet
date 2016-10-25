package io.khasang.snet.dao;

import io.khasang.snet.entity.AbstractEntity;

import java.util.List;

public interface HibernateDAO<Element extends AbstractEntity> {

    /*
    * Adds new object into ORM
    * */
    void add(Element element);

    /* Gets one of the existed objects
     * by key value
     */
    Element get(Element element);

    /* Edit object that already exist
    * */
    void edit(Element element);

    /* Delete's one of the existed objects
     * by key value
     */
    void delete(Element element);

    /* Gets all objects in table
    * */
    List<Element> getAll();
}
