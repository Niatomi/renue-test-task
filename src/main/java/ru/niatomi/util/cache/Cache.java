package ru.niatomi.util.cache;

import java.util.Optional;

/**
 * @author niatomi
 */
public interface Cache<K, V> {

    void put(K key, V value);

    Optional<V> get(K key);

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

}
