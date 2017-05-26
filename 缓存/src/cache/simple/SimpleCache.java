package cache.simple;

import java.util.*;

/**
 * 简单的缓存map. 其中的元素如果一定时间内没有访问, 则会被删除.
 */

public class SimpleCache<K, V> {

    // TODO: 2017/5/25 0025  用Map为什么还用了一个Queue数据结构？？
    //使用队列是使元素按照过期时间排序，最新的元素排在后面，越前面的元素是越快过期的。

    private long liveTime;
    private int limit;
    private Map<K, Entity<K, V>> map;
    private Queue<K, V> queue;

    public SimpleCache(long liveTime) {
        this(liveTime, 0);
    }

    public SimpleCache(long liveTime, int limit) {
        this.liveTime = liveTime;
        this.limit = limit > 0 ? limit : 0;
        int initialCapacity = limit == 0 ? 16 : (int) (limit * 0.8);
        this.map = new HashMap<>(initialCapacity);
        this.queue = new Queue<>();
    }

    /**
     * 将指定key和value添加到缓存中. 如果该key已经存在, 则旧值被替换.
     *
     * @return 与key关联的旧值. 如果key没有任何映射关系,则返回 null.
     */
    public synchronized V add(K key, V value) {

        //先删除掉过时的元素
        removeTimeOut();

        Entity<K, V> entity = new Entity<>(key, value, liveTime);
        Entity<K, V> oldEntity = map.put(key, entity);

        if (oldEntity != null) {//删除以前key值存在的Entity
            queue.remove(oldEntity);
        }
        queue.append(entity);

        // map的容量大于了最大的容量了，删除一个元素
        if (limit != 0 && map.size() > limit) {
            removeFirst();
        }

        return oldEntity == null ? null : oldEntity.getValue();
    }

    /**
     * 获取与指定key关联的元素
     */
    public synchronized V get(K key) {
        removeTimeOut();
        Entity<K, V> entity = map.get(key);
        if (entity != null) {
            access(entity);//更新该元素的过期时间, 以及在队列中的位置
            return entity.getValue();
        }
        return null;
    }

    /**
     * 清空缓冲区
     */
    public synchronized void clear() {
        map.clear();
        queue.clear();
    }

    /**
     * 获取缓存区中的元素个数
     */
    public synchronized int size() {
        removeTimeOut();
        return map.size();
    }

    /**
     * 访问指定元素. 更新该元素的过期时间, 以及在队列中的位置
     */
    private synchronized void access(Entity<K, V> entity) {
        queue.remove(entity);
        entity.updateTimeout(liveTime);
        queue.append(entity);
    }

    /**
     * 移除所有超时的元素，遍历队列
     */
    private synchronized void removeTimeOut() {

        Entity<K, V> head = queue.getFirst();
        while (head != null && head.isTimeout()) {
            removeFirst();
            head = queue.getFirst();
        }
    }

    /**
     * 移除队列中第一个元素, 并删除map中对应的元素. 注意, 调用该方法之前需要保证缓冲区中有元素
     */
    private synchronized void removeFirst() {
        map.remove(queue.removeFirst().getKey());
    }

    /**
     * 当队列中没有元素时，head=tail=null;
     * 当队列中只有一个元素时，head=tail!=null
     *
     * @param <K>
     * @param <V>
     */
    private static class Queue<K, V> {
        private Entity<K, V> head;
        private Entity<K, V> tail;

        public Queue() {
            head = null;
            tail = null;
        }

        /**
         * 获得第一个元素.
         *
         * @return 第一个元素. 如果没有元素, 则返回null.
         */
        public Entity<K, V> getFirst() {
            return head;
        }

        /**
         * 向队列末尾添加元素.
         *
         * @param entity 需要添加的元素
         */
        public void append(Entity<K, V> entity) {
            if (head == null) {//当队列为空时
                head = entity;
                tail = entity;
                entity.prev = null;
            } else {//当队列中存在元素时
                entity.prev = tail;
                tail.next = entity;
                tail = entity;
            }
            entity.next = null;
        }

        /**
         * 移除第一个元素.
         *
         * @return 第一个元素. 如果没有元素, 则返回null.
         */
        public Entity<K, V> removeFirst() {
            // 没有元素
            if (head == null) {
                return null;
            }
            Entity<K, V> entity = head;

            // 只有一个元素
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = entity.next;
                head.prev = null;
            }
            return entity;
        }

        /**
         * 从队列中移除指定的节点，该节点已经确定在队列中
         */
        public Entity<K, V> remove(Entity<K, V> entity) {
            //只有一个节点
            if (head == tail) {
                head = null;
                tail = null;
                return entity;
            }

            if (entity.prev != null) {//entity不是head
                entity.prev.next = entity.next;
            } else {
                head = entity.next;
            }

            if (entity.next != null) {//entity不是tail
                entity.next.prev = entity.prev;
            } else {
                tail = entity.prev;
            }
            return entity;
        }

        public void clear() {
            head = null;
            tail = null;
        }

    }

    public static class Entity<K, V> {
        private K key;
        private V value;
        private long timeout;
        Entity<K, V> next;
        Entity<K, V> prev;

        public Entity(K key, V value, long liveTime) {
            this.key = key;
            this.value = value;
            updateTimeout(liveTime);
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public long getTimeout() {
            return timeout;
        }

        public boolean isTimeout() {
            return timeout > System.currentTimeMillis();
        }

        void updateTimeout(long liveTime) {
            this.timeout = System.currentTimeMillis() + liveTime;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((key == null) ? 0 : key.hashCode());
            result = prime * result + (int) (timeout ^ (timeout >>> 32));
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Entity other = (Entity) obj;
            if (key == null) {
                if (other.key != null)
                    return false;
            } else if (!key.equals(other.key))
                return false;
            if (timeout != other.timeout)
                return false;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "Entity [key=" + key + ", value=" + value + ", timeout=" + timeout + "]";
        }

    }

}
