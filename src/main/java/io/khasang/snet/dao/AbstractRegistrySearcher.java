package io.khasang.snet.dao;

import io.khasang.snet.entity.AbstractEntity;
import io.khasang.snet.entity.userauth.User;

import java.util.Collection;

/* This interface allows extend possibility of
* basic CRUD classes manage objects. That one
* can provide search in table, by key entity */
public interface AbstractRegistrySearcher<Entity extends AbstractEntity, KeyForSearching>
        extends AbstractCRUD<Entity> {

    Collection<Entity> getListSearched(KeyForSearching key);

    Entity getExistedDialog(KeyForSearching first, KeyForSearching second);
}
