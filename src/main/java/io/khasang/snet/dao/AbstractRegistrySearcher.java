package io.khasang.snet.dao;

import io.khasang.snet.entity.AbstractEntity;

import java.util.Collection;

public interface AbstractRegistrySearcher<Entity extends AbstractEntity, KeyEntity>
        extends AbstractCRUD<Entity> {
    Collection<Entity> getListSearched(KeyEntity keyEntity);
}
