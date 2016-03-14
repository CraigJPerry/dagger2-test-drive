package com.craigjperry.dagger2.entities.datastorage;

import com.google.common.base.Optional;

import java.util.Collection;

public interface Repository<K, V> {
    V add();
    Optional<V> find(K id);
    Collection<V> findAll();
    V update(V id);
    V delete(K id);
}
