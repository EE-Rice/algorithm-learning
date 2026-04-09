package learn.list;

// 根据给定数字划分数组
public class PartitionList {
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

    // 测试链接：https://leetcode.cn/problems/partition-list/
    class Solution {

        public static ListNode partition(ListNode head, int x) {
            ListNode leftHead = null, leftTail = null; // <x 的区域
            ListNode rightHead = null, rightTail = null; // >=x 的区域
            ListNode next = null;

            while (head != null) {
                // 6  5     3 4 2 1 1 7  x = 4
                // h  next
                next = head.next; // 记录未划分部分当前head的下一个结点
                head.next = null; // 断链

                if (head.val < x) {
                    if (leftHead == null) {
                        leftHead = head;
                    } else {
                        leftTail.next = head;
                    }
                    leftTail = head;
                } else {
                    if (rightHead == null) {
                        rightTail = head;
                    } else  {
                        rightHead.next = head;
                    }
                    rightTail = head;
                }
                head = next;
            }

            // <x 的区域没有结点
            if (leftHead == null) {
                return rightHead;
            }

            // <x 的区域有结点
            leftTail.next = rightHead;
            return leftHead;
        }
    }
}
