package LRU;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zkx
 */
public class LRUCache<K, V> implements Cache<K, V> {
    //缓存容量
    private int size;
    //节点的map，根据
    private Map<K, LinkedListNode<CacheElement<K, V>>> linkedListNodeMap;
    private DoublyLinkedList<CacheElement<K, V>> doublyLinkedList;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public LRUCache(int size) {
        this.size = size;
        //线程安全map
        this.linkedListNodeMap = new ConcurrentHashMap<>(size);
        //自己实现的双向链表
        this.doublyLinkedList = new DoublyLinkedList<>();
    }

    @Override
    public boolean put(K key, V value) {
        this.lock.writeLock().lock();
        try {
            CacheElement<K, V> item = new CacheElement<K, V>(key, value);
            LinkedListNode<CacheElement<K, V>> newNode;
            //如果新节点的值，在map里面，说明在队列里面，那么将它移动到队列头部
            if (this.linkedListNodeMap.containsKey(key)) {
                LinkedListNode<CacheElement<K, V>> node = this.linkedListNodeMap.get(key);
                newNode = doublyLinkedList.updateAndMoveToFront(node, item);
            } else {
            //如果不存在，就需要更新缓存，先判断是否满了，满了的话
                if (this.size() >= this.size) {
                    this.evictElement();
                }
                //将新节点加入进去队列中。
                newNode = this.doublyLinkedList.add(item);
            }
            //如果当前的
            if (newNode.isEmpty()) {
                return false;
            }
            this.linkedListNodeMap.put(key, newNode);
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    @Override
    public Optional<V> get(K key) {
        this.lock.readLock().lock();
        try {
            LinkedListNode<CacheElement<K, V>> linkedListNode = this.linkedListNodeMap.get(key);
            if (linkedListNode != null && !linkedListNode.isEmpty()) {
                linkedListNodeMap.put(key, this.doublyLinkedList.moveToFront(linkedListNode));
                return Optional.of(linkedListNode.getElement().getValue());
            }
            return Optional.empty();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public int size() {
        this.lock.readLock().lock();
        try {
            return doublyLinkedList.size();
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        this.lock.writeLock().lock();
        try {
            linkedListNodeMap.clear();
            doublyLinkedList.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    //缓存替换
    private boolean evictElement() {
        this.lock.writeLock().lock();
        try {
            //取出最近最不长使用的元素，剃齿
            LinkedListNode<CacheElement<K, V>> linkedListNode = doublyLinkedList.removeTail();
            //如果为空，说明，出现错误
            if (linkedListNode.isEmpty()) {
                return false;
            }
            //并且map去除元素
            linkedListNodeMap.remove(linkedListNode.getElement().getKey());
            return true;
        } finally {
            this.lock.writeLock().unlock();
        }
    }
}