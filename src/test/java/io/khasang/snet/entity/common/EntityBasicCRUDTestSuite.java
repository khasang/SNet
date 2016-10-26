package io.khasang.snet.entity.common;

import io.khasang.snet.dao.AbstractCRUD;
import io.khasang.snet.entity.AbstractEntity;

import java.util.Collection;
import java.util.HashSet;

/**
 * This test utility for entities
 * For using this you need:
 * 1) Implement in your entity AbstractEntity interface
 * 2) Implement in your DAO class AbstractCRUD interface
 * Also you can create generator, for quick generating your entities
 */
public class EntityBasicCRUDTestSuite<Entity extends AbstractEntity> {

    private AbstractCRUD<Entity> utils;

    public EntityBasicCRUDTestSuite(AbstractCRUD<Entity> utils) {
        this.utils = utils;
    }

    /* Test for equals of entity
    * @param first: original object
    * @param same: object indentical original one
    * @param another: object that differs from original
    * @return false if equals wrong
    * */
    public boolean testEquals(Entity first, Entity same, Entity another) {
        if (!first.equals(same)) return false;
        return !first.equals(another);
    }

    /* Testing for saving and loading entity from db
    * @param entity: object that you want to test
    * @return object loaded from db
    * if loaded differs from whose that was given
     * you should fail test
    * */
    public Entity testSaveAndLoad(Entity entity) {
        utils.add(entity);
        return utils.get(entity);
    }

    /* Testing of edit NOT exists object
    * given in argument object will wrote in db
    * @param original: your original object
    * @param edited: edited object
    * @return your edited object, with original ID
    * if it's will equals original, or not equals edited object
    * you should fail test
    * */
    @SuppressWarnings("unchecked")
    public Entity testUpdate(Entity original, Entity edited) {
        utils.add(original);
        edited.setID(original.getID());
        utils.edit(edited);
        return utils.get(edited);
    }

    /* Testing of delete NOT exists object
    * given in argument object will wrote
    * in db and consequently will removed
    * @param entity your object for testing
    * @return Entity object, normally equals null
    * if returned object would not null you should fail test
    * */
    public Entity testDelete(Entity entity) {
        utils.add(entity);
        utils.delete(entity);
        return utils.get(entity);
    }

    /* Test for save vary of objects, and load that after
    * @param collection of object already generated
    * @return amount of object whose retain after
    * all loaded from db Collection was removed
    * if not equals 0 you should fail test
    * */
    public int testForLists(Collection<Entity> entities) {
        Collection<Entity> afterSerialization = new HashSet<>();
        for (Entity entity : entities) {
            utils.add(entity);
            afterSerialization.add(entity);
        }

        /* getting all object from db, remove they from our
           row of objects */
        Collection<Entity> deSerialized = utils.getAll();
        afterSerialization.removeAll(deSerialized);

        return afterSerialization.size();
    }
}
