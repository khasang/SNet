package io.khasang.snet.dao;

import io.khasang.snet.entity.AbstractEntity;

import java.util.List;

public interface AbstractCRUD<Entity extends AbstractEntity> {

    /*
    * Adds new object into ORM
    * */
    void add(Entity entity);

    /* Gets one of the existed objects
     * by key value
     */
    Entity get(Entity entity);

    /* Edit object that already exist
    * */
    void edit(Entity entity);

    /* Delete's one of the existed objects
     * by key value
     */
    void delete(Entity entity);

    /* Gets all objects in table
    * */
    List<Entity> getAll();
}
