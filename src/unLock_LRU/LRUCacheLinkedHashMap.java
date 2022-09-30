package unLock_LRU;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zkx
 */
public class LRUCacheLinkedHashMap<K,V> extends LinkedHashMap<K,V> {
        //缓存最大容量
        private final int maxCapacity;
        //负载因子
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;

        //读写锁
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public LRUCacheLinkedHashMap(int maxCapacity) {

        super(maxCapacity,DEFAULT_LOAD_FACTOR,false);
        this.maxCapacity = maxCapacity;
    }

    /**判断最后一个元素**/
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > maxCapacity;
    }

    @Override
    public void clear() {
        try {
            this.lock.writeLock().lock();
            super.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        try{
            this.lock.writeLock().lock();
            return super.put(key, value);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public V get(Object key) {
        try{
            this.lock.readLock().lock();
            return super.get(key);
        } finally {
            this.lock.readLock().unlock();
        }

    }

    @Override
    public boolean containsKey(Object key) {
        try {
            this.lock.readLock().lock();
            return super.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
    }
    public Collection<Map.Entry<K, V>> getAll() {
        try {
            lock.readLock().lock();
            return new ArrayList<Map.Entry<K, V>>(super.entrySet());
        } finally {
            lock.readLock().unlock();
        }
    }

}
