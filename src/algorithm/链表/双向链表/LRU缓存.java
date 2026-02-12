package algorithm.链表.双向链表;

import java.util.HashMap;
import java.util.Map;

public class LRU缓存 {
    /**
     * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。
     * 缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。
     * 当缓存被填满时，它应该删除最近最少使用的项目。
     * <p>
     * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
     * <p>
     * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
     * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
     */
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4
        System.out.println("ok");
    }
}

class LRUCache {
    class DListNode {
        int val;
        int key;
        DListNode pre;
        DListNode next;

        public DListNode() {
        }

        public DListNode(int val, int key) {
            this.val = val;
            this.key = key;
        }
    }

    int cap;
    int size;
    Map<Integer, DListNode> map;
    DListNode head;
    DListNode tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.cap = capacity;
        map = new HashMap<>();
        head = new DListNode();
        tail = new DListNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            DListNode node = map.get(key);

            // 使用之后，将节点移动到头部 (先移除原有关系，再放到头部)
            removeNode(node);
            putHead(node);

            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        DListNode node;
        // 有当前节点的话，需要移除当前节点并放到头部
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = value;
            removeNode(node);
        } else {
            // 没有当前节点的话，需要新增当前节点，并放到头部
            size++;
            node = new DListNode(value, key);
            map.put(key, node);
            // 当新增的时候超长，就需要移除末尾节点
            if (size > cap) {
                DListNode needRemove = tail.pre;
                removeNode(needRemove);
                map.remove(needRemove.key);
                size--;
            }
        }
        // 将节点放到头部
        putHead(node);
    }

    // 移除当前节点
    public void removeNode(DListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    // 添加节点到头部
    public void putHead(DListNode node) {
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
    }
}
