package learn.list.container;

import java.util.HashMap;

public class CopyListWithRandomPointerUsingHash {
    // 不要提交这个类
    public static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node(int v) {
            val = v;
        }
    }

    // 提交如下的方法
    public static Node copyRandomList(Node head) {
        if (head == null) return null;

        // key: 原节点, value: 对应的新节点
        HashMap<Node, Node> map = new HashMap<>();

        // 第一遍：创建所有新节点，建立原节点→新节点的映射
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        // 第二遍：设置新节点的 next 和 random
        cur = head;
        while (cur != null) {
            Node copy = map.get(cur);
            copy.next = map.get(cur.next);
            copy.random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }
}
