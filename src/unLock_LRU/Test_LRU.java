package unLock_LRU;

/**
 * @author zkx
 */
public class Test_LRU {
    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache
                = new LRUCache<Integer, Integer>(5);
        lruCache.put(1,1);
        lruCache.findAll();

        lruCache.put(2,2);
        lruCache.findAll();
        lruCache.put(3,3);
        lruCache.findAll();
        lruCache.put(4,4);
        lruCache.findAll();
        lruCache.put(5,5);
        lruCache.findAll();
        lruCache.put(6,6);
//        System.out.println(lruCache.get(2));
        lruCache.findAll();
        lruCache.put(4,4);
        lruCache.findAll();
    }
}
