package unLock_LRU;

import java.util.*;

/**
 * @author zkx
 */
public class LRUCache<K,V> {


    private final int cacheSize;
    private Hashtable<K,CacheNode<K,V>> nodes;//缓存容器
    private int currentSize;
    private CacheNode<K,V> first;//链表头
    private CacheNode<K,V> last;//链表尾

    /**
     * 链表节点
     * @author Administrator
     *
     */

    class CacheNode<K,V>{
        CacheNode<K,V> prev;//前一节点
        CacheNode<K,V> next;//后一节点
        V value;//值
        K key;//键
        CacheNode() {
        }

        public CacheNode<K,V> getPrev() {
            return prev;
        }

        public void setPrev(CacheNode<K,V> prev) {
            this.prev = prev;
        }

        public CacheNode<K,V> getNext() {
            return next;
        }

        public void setNext(CacheNode<K,V> next) {
            this.next = next;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }
    }
    public LRUCache(int i) {
        currentSize = 0;
        cacheSize = i;
        nodes = new Hashtable(i);//缓存容器
    }

    public void findAll() {
        CacheNode<K,V> node = first;
        while (node != null) {
            System.out.print("k = " + node.key + " v = " + node.value);
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 获取缓存中对象
     * @param key
     * @return
     */
    public V get(K key) {
        CacheNode<K,V> node = nodes.get(key);
        if (node != null) {
            moveToHead(node);
            return node.value;
        } else {
            return null;
        }
    }

    /**
     * 添加缓存
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        CacheNode<K,V> node = nodes.get(key);

        if (node == null) {
            //缓存容器是否已经超过大小.
            if (currentSize >= cacheSize) {
                if (last != null)//将最少使用的删除
                    nodes.remove(last.key);
                removeLast();
            } else {
                currentSize++;
            }

            node = new CacheNode();
        }
        node.value = value;
        node.key = key;
        //将最新使用的节点放到链表头，表示最新使用的.
        moveToHead(node);
        nodes.put(key, node);
    }
    /**
     * 将缓存删除
     */
    public CacheNode<K,V> remove(K key) {
        CacheNode<K,V> node = nodes.get(key);
        if (node != null) {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
            if (last == node)
                last = node.prev;
            if (first == node)
                first = node.next;
        }
        return node;
    }
    public void clear() {
        first = null;
        last = null;
    }
    /**
     * 删除链表尾部节点
     *  表示 删除最少使用的缓存对象
     */
    private void removeLast() {
        //链表尾不为空,则将链表尾指向null. 删除连表尾（删除最少使用的缓存对象）
        if (last != null) {
            if (last.prev != null)
                last.prev.next = null;
            else
                first = null;
            last = last.prev;
        }
    }

    /**
     * 移动到链表头，表示这个节点是最新使用过的
     */
    private void moveToHead(CacheNode<K,V> node) {
        if (node == first)
            return;
        //如果有前驱结点，那么就将前驱结点的值指向他的下一个节点
        if (node.prev != null)
            node.prev.next = node.next;
        //  如果它的next不为空，就将他的下一个家电的prev指向他的前驱结点
        if (node.next != null)
            node.next.prev = node.prev;
        //如果说这和节点是最后一个节点，那么最后一个节点指向他的前驱节点
        if (last == node)
            last = node.prev;
        //如果第一个节点不为空，那么就将当前节点的next指向第一个节点，第一个节点的前驱指向现在的节点
        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        //然后first指向现在的节点
        first = node;
        //first的前驱节点指向null
        node.prev = null;
        //如果last为空了，那么last = first
        if (last == null)
            last = first;
    }

}
