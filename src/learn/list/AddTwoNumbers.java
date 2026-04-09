package learn.list;

public class AddTwoNumbers {

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

    // 测试链接：https://leetcode.cn/problems/add-two-numbers/
    public static ListNode  addTwoNumbers(ListNode h1, ListNode h2) {
        // ans:返回的结点，一确定就不动了
        // cur:往后挂结点
        ListNode ans = null, cur = null;
        int carry = 0; // 当前位的进位

        for (int sum, val;
                h1 != null || h2 != null; // 终止条件
                h1 = h1 == null ? null : h1.next, // 每一步跳转
                h2 = h2 == null ? null : h2.next
        ) {

            sum = (h1 == null ? 0 : h1.val)
                    + (h2 == null ? 0 : h2.val)
                    + carry;

            val = sum % 10;
            carry = sum / 10;

            if (ans == null) {
                ans = new ListNode(val);
                cur = ans;
            } else {
                cur.next = new ListNode(val);
                cur = cur.next;
            }
        }
        if (carry == 1) {
             cur.next = new ListNode(carry);
        }
        return ans;
    }
}