package cache.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 王萍
 * @date 2018/2/28 0028
 */
public class LRUCacheWithLinkedHashMap {

    private int capacity;
    private Map<Integer, Integer> cache;

    public LRUCacheWithLinkedHashMap(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            // 定义put后的移除规则，大于容量就删除eldest
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else
            return -1;
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }
}
