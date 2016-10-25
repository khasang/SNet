package io.khasang.snet.util;

import io.khasang.snet.dao.HibernateDAO;
import io.khasang.snet.entity.AbstractEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cresh on 25.10.16.
 */
public class EntitiesTest<Entity extends AbstractEntity> {

    private HibernateDAO<Entity> utils;

    public EntitiesTest(HibernateDAO<Entity> utils) {
        this.utils = utils;
    }

    /* Test for equals of entity
    * @return false if equals wrong
    * */
    public boolean testEquals(Entity first, Entity same, Entity another) {
        if (!first.equals(same)) return false;
        return !first.equals(another);
    }

    public Entity testSaveAndLoad(Entity entity) {
        utils.add(entity);
        return utils.get(entity);
    }

    @SuppressWarnings("unchecked")
    public Entity testUpdate(Entity original, Entity edited) {
        utils.add(original);
        edited.setID(original.getID());
        utils.edit(edited);
        return utils.get(edited);
    }

    public Entity testDelete(Entity entity) {
        utils.add(entity);
        utils.delete(entity);
        return utils.get(entity);
    }

    public int testForLists(Collection<Entity> entities) {
        Collection<Entity> afterSerialization = new HashSet<>();
        for (Entity entity : entities) {
            utils.add(entity);
            afterSerialization.add(entity);
        }

        Collection<Entity> deSerialized = utils.getAll();
        afterSerialization.removeAll(deSerialized);

        return afterSerialization.size();
    }
}
