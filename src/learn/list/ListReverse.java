package learn.list;

import java.util.List;

public class ListReverse {

    // 单链表结点
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 反转单链表测试链接：https://leetcode.cn/problems/reverse-linked-list/
    class  Solution {
        public static ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode next = null;

            while (head != null) {
                next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }
    }

    // 双链表结点
    public static class DoubleListNode {
        public int value;
        public DoubleListNode last;
        public DoubleListNode next;
        public DoubleListNode(int v) {
            this.value = v;
        }
    }

    // 反转双链表
    public static DoubleListNode reverseDoubleList(DoubleListNode head) {
        if (head == null || head.next == null) return head;

        DoubleListNode pre = null;
        DoubleListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;

    }
}
