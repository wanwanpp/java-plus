package queue.threadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Node<E> {
    private E value;
    private Node<E> next;

    public Node(E e) {
        value = e;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getNext() {
        return next;
    }
}

public class TwoLockLinkQueue<E> {

    private Lock headLock = new ReentrantLock();
    private Lock tailLock = new ReentrantLock();
    private Node<E> headNode;
    private Node<E> tailNode;

    public TwoLockLinkQueue() {
        //使用了一个哨兵节点，所以真正poll时取出的是第二个节点（哨兵节点后一个节点）的value。
//        Node<E> sentinelNode = new Node<E>(null);
//        headNode = sentinelNode;
//        tailNode = sentinelNode;

        //不使用哨兵
        headNode = null;
        tailNode = null;
    }

    //不使用哨兵
    public boolean offer(E e) {
        if (e == null) {
            return false;
        }
        Node<E> newNode = new Node<>(e);
        tailLock.lock();
        try {
            if (tailNode != null) {
                tailNode.setNext(newNode);
            } else {
                headNode = newNode;
            }
            tailNode = newNode;
        } finally {
            tailLock.unlock();
        }
        return true;
    }
    public E pool() {
        headLock.lock();
        try {
            if (headNode == null) return null;
            else {
                Node<E> now = this.headNode;
                this.headNode = now.getNext();
                if (headNode == null) {
                    tailNode = null;
                }
                return now.getValue();
            }
        } finally {
            headLock.unlock();
        }
    }

//    使用哨兵
//public boolean offer( E e ) {
//    if(e == null) {
//        return false;
//    }
//    Node<E> newNode = new Node<E>(e);
//    tailLock.lock();
//    try {
//        tailNode.setNext(newNode);
//        tailNode = newNode;
//    } finally {
//        tailLock.unlock();
//    }
//    return true;
//}
//  public E pool() {
//        headLock.lock();
//        try {
//            Node<E> newHeadNode = headNode.getNext();
//            if (newHeadNode == null) {
//                return null;
//            } else {
//                headNode = newHeadNode;
//                return newHeadNode.getValue();
//            }
//        } finally {
//            headLock.unlock();
//        }
//    }

    static class TestClass {
        static AtomicInteger count = new AtomicInteger(0);
        static TwoLockLinkQueue<Integer> queue = new TwoLockLinkQueue<Integer>();

        public static void main(String[] args) {

            final int offerCount = 5;
            final int threadCount = 5;

            class task1 implements Runnable {
                private TwoLockLinkQueue<Integer> queue;

                public task1(TwoLockLinkQueue<Integer> queue) {
                    this.queue = queue;
                }

                public void run() {
                    for (int i = 0; i < offerCount; i++) {
                        int value = count.addAndGet(1);
                        queue.offer(value);
                        System.out.println("[" + Thread.currentThread() +
                                "]" + "offer:" + value);
                    }
                }
            }

            class task2 implements Runnable {
                private TwoLockLinkQueue<Integer> queue;

                public task2(TwoLockLinkQueue<Integer> queue) {
                    this.queue = queue;
                }

                public void run() {
                    while (!Thread.interrupted()) {
                        Integer value = queue.pool();
                        if (value != null)
                            System.out.println("[" + Thread.currentThread() +
                                    "]" + "poll:" + value);
                    }
                }
            }
            ExecutorService pool = Executors.newCachedThreadPool();
            for (int i = 0; i < threadCount; i++) {
                pool.execute(new task1(queue));
                pool.execute(new task2(queue));
            }
            pool.shutdown();

            try {
                pool.awaitTermination(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e1) {
            }
            pool.shutdownNow();
        }
    }
}