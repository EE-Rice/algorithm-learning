package learn.data_structure;

import java.util.HashMap;

public class LRU {

    // 测试链接 : https://leetcode.cn/problems/lru-cache/
    class LRUCache {

        class DoubleNode {
            public int key;
            public int val;
            public DoubleNode last;
            public DoubleNode next;

            public DoubleNode(int k, int v) {
                key = k;
                val = v;
            }
        }

        // 双向链表
        class DoubleList {
            private DoubleNode head;
            private DoubleNode tail;

            public DoubleList() {
                head = null;
                tail = null;
            }

            // 新进一个节点
            public void addNode(DoubleNode newNode) {
                if (newNode == null) {
                    return;
                }
                if (head == null) { // 链表为空的情况
                    head = tail = newNode;
                } else {
                    tail.next = newNode;
                    newNode.last = tail;
                    tail = newNode;
                }
            }

            // 节点被访问，将节点移到尾部，即变成新的tail
            public void moveNodeToTail(DoubleNode node) {
                if (tail == node) {
                    return;
                }
                if (head == node) { // 头节点移到尾部
                    head = node.next;
                    head.last = null;
                } else { // 中间节点：调整前后连接关系
                    node.last.next = node.next;
                    node.next.last = node.last;
                }
                node.last = tail;
                node.next = null;
                tail.next = node;
                tail = node;
            }

            // 移除头节点，比如链表Cache满了
            public DoubleNode removeHead() {
                if (head == null) {
                    return null;
                }
                DoubleNode ans = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = ans.next;
                    ans.next = null;
                    head.last = null;
                }
                return ans;
            }

        }

        private HashMap<Integer, DoubleNode> keyNodeMap; // 哈希表，用来判断是否存在节点

        private DoubleList nodeList; // 链表

        private final int capacity;

        public LRUCache(int cap) {
            keyNodeMap = new HashMap<>();
            nodeList = new DoubleList();
            capacity = cap;
        }

        public int get(int key) {
            if (keyNodeMap.containsKey(key)) {
                DoubleNode ans = keyNodeMap.get(key);
                nodeList.moveNodeToTail(ans); // 访问了就要更新
                return ans.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (keyNodeMap.containsKey(key)) { // 节点本来就在链表中，更新值和访问记录
                DoubleNode node = keyNodeMap.get(key);
                node.val = value;
                nodeList.moveNodeToTail(node);
            } else  {
                if (keyNodeMap.size() == capacity) {
                    keyNodeMap.remove(nodeList.removeHead().key); // 如果链表满了，在链表和哈希表都要对应删去节点
                }
                DoubleNode newNode = new DoubleNode(key, value);
                keyNodeMap.put(key, newNode);
                nodeList.addNode(newNode);
            }
        }

    }

}
