package cache.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王萍
 * @date 2018/2/28 0028
 * LRU 最近最少使用算法
 */
public class LRUCacheWithHashMap {

    class Node {
        Node pre;
        Node next;
        Integer key;
        Integer val;

        Node(Integer k, Integer v) {
            key = k;
            val = v;
        }
    }

    private Map<Integer, Node> map = new HashMap<>();

    //head和tail两个都是new Node(null, null)，且不会改变
    private final Node head = new Node(null, null);
    private final Node tail = new Node(null, null);
    private int cap;

    public LRUCacheWithHashMap(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node n = map.get(key);
        //找到之后将其放到末尾去
        if (n != null) {
            //前驱的next置为后驱
            n.pre.next = n.next;
            //后驱的pre置为前驱
            n.next.pre = n.pre;
            appendTail(n);
            return n.val;
        }
        return -1;
    }

    public void set(int key, int value) {
        Node n = map.get(key);
        // 存在的话
        if (n != null) {
            //更新值
            n.val = value;
            map.put(key, n);

            n.pre.next = n.next;
            n.next.pre = n.pre;
            appendTail(n);
            return;
        }
        //不存在就添加

        //检查，大小是否满了，满了就删除head后的第一个节点
        if (map.size() == cap) {
            Node tmp = head.next;
            head.next = head.next.next;
            head.next.pre = head;
            map.remove(tmp.key);
        }
        //然后添加节点到末尾
        n = new Node(key, value);
        // youngest node append taill
        appendTail(n);
        map.put(key, n);
    }

    //添加到尾部
    private void appendTail(Node n) {
        n.next = tail;
        n.pre = tail.pre;
        tail.pre.next = n;
        tail.pre = n;
    }
}
