package learn.list;

import java.util.List;

// 返回两个无环链表相交的第一个节点
// 测试链接 : https://leetcode.cn/problems/intersection-of-two-linked-lists/
public class IntersectionOfTwoLinkedLists {

    // 提交时不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    // 提交如下的方法
    public static ListNode getIntersectionNode(ListNode h1, ListNode h2) {
        if (h1 == null || h2 == null) {
            return null;
        }
        ListNode a = h1, b = h2;
        int diff = 0;
        while (a != null) {
            a = a.next;
            diff++;
        }
        while (b != null) {
            b = b.next;
            diff--;
        }
        if (a != b) {
            return null;
        }

        // 复用：a为长链表，b为短链表
        if (diff >= 0) {
            a = h1;
            b = h2;
        } else {
            a = h2;
            b = h1;
        }
        diff = Math.abs(diff); // 取绝对值
        while (diff-- != 0) {
            a = a.next; // 长链表先走diff步
        }
        while (a != b) { // 再次相等的时候，即相交节点
            a = a.next;
            b = b.next;
        }
        return a;
    }
}
