package learn.queue_stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ConvertQueueAndStack {

    // 用两个栈实现队列
    // 测试链接：https://leetcode.cn/problems/implement-queue-using-stacks/
    class MyQueue {

        public Stack<Integer> in;
        public Stack<Integer> out;

        public MyQueue() {
            in = new Stack<Integer>();
            out = new Stack<Integer>();
        }

        // 倒数据
        // 从in栈，把数据倒入out栈
        // 1）out空了，才能倒数据
        // 2）如果倒数据，in必须倒完
        // 单个操作时间复杂度是O(1)
        private void inToOut() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }

        public void push(int x) {
            in.push(x);
            inToOut();
        }

        public int pop() {
            inToOut();
            return out.pop();
        }

        // peek() 返回栈顶部元素，不弹出
        public int peek() {
            inToOut();
            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    /// ///////////////////////////////////////////////////////////////////////////////////////
    // 测试链接：https://leetcode.cn/problems/implement-stack-using-queues/
    // 用队列实现栈
    class MyStack {

        Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<Integer>();
        }

        // 单个操作时间复杂度O(n)
        public void push(int x) {
            int n = queue.size();
            queue.offer(x);
            for (int i = 0; i < n; i++) {
                queue.offer(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
