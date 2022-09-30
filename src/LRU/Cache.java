package LRU;

import java.util.Optional;

/**
 * @author zkx
 * 缓存接口
 */
public interface Cache<K, V> {
    boolean put(K key, V value);

    Optional<V> get(K key);

    int size();

    boolean isEmpty();

    void clear();
}
