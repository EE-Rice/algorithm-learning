package learn.list;

import java.util.HashSet;

public class IntersectionOfTwoLinkedListsUsingHash {
    // 单链表
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public ListNode getIntersectionNode(ListNode h1, ListNode h2) {
        if  (h1 == null || h2 == null) {
            return null;
        }

        HashSet<ListNode> set = new HashSet<>();

        // 遍历链表1，所有节点地址存入HashSet中
        ListNode cur = h1;
        while (cur != null) {
            set.add(cur); // 存的是节点引用（地址），不是val
            cur = cur.next;
        }

        // 遍历链表2，第一个在set中出现的即为相交点
        cur = h2;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }
}
