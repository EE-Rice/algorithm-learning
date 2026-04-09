package learn.queue_stack;

import java.util.LinkedList;
import java.util.Queue;

class QueueStackAndCircularQueue {

    // 直接用java内部的实现
    // 其实内部就是双向链表，常数操作慢
    public static class Queue1 {

        // java中的双向链表LinkedList
        // 单向链表就足够了
        public Queue<Integer> queue = new LinkedList<>();

        // 调用任何方法之前，先调用这个方法来判断队列是否为空
        public boolean isEmpty() {
            return queue.isEmpty();
        }

        // 向队列中加入num，加到尾巴
        public void offer(int num) {
            queue.offer(num);
        }

        // 从队列拿，从头拿
        public int poll() {
            return queue.poll();
        }

        // 返回队列头元素，不拿出
        public int peek() {
            return queue.peek();
        }

        // 返回队列里目前有几个数
        public int size() {
            return queue.size();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////
    /// 实际更常见的写法，常数时间好
    /// 如果确定加入操作的总次数不超过n，则可以用
    /// 一般会有一个明确数据量，所以这是最常用的方式
    public static class Queue2 {

        public int[] queue;
        public int l;
        public int r;

        // 加入操作的总次数的上限是多少，一定要明确
        public Queue2(int n) {
            queue = new int[n];
            l = 0;
            r = 0;
        }

        // 调用任何方法之前，先调用这个方法来判断队列是否为空
        public boolean isEmpty() {
            return l == r;
        }

        // r是即将加入队列应该放的位置
        // l ... r-1 r
        // [l, r)
        public void offer(int num) {
            queue[r++] = num;
        }

        public int poll() {
            return queue[l++];
        }

        public int head() {
            return queue[l];
        }

        public int tail() {
            return queue[r - 1];
        }

        public int size() {
            return r - l;
        }
    }

    /// /////////////////////////////////////////////////////////
    /// 栈
    /// 实际写法，明确数据量
    /// 发生弹出操作后，空间可以复用
    public static class Stack2 {

        public int[] stack;
        public int size;

        // 同时在栈里的元素不会超过n
        public Stack2(int n) {
            stack = new int[n];
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(int num) {
            stack[size++] = num;
        }

        public int pop() {
            return stack[--size];
        }

        // 只是问一下栈顶元素
        public int peek() {
            return stack[size - 1];
        }

        public int size() {
            return size;
        }
    }

    /// /////////////////////////////////////////////////////////
    /// 设计循环队列
    /// 用size和limit来控制头（l）尾（r）
    /// 测试链接: https://leetcode.cn/problems/design-circular-queue
    class MyCircularQueue {

        public int[] queue;

        // r是待入队元素存放位置
        public int l, r, size, limit;

        // 同时在队列的数字个数，不能超过k
        public MyCircularQueue(int k) {
            queue = new int[k];
            l = r = size = 0;
            limit = k;
        }

        // 入队
        // 如果队列满了，什么也不做，返回false
        // 如果队列没满，加入value，返回true
        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            } else {
                queue[r] =  value;
                // r++，如果结束了，跳回0
                r = r == limit - 1 ? 0 : (r + 1);
                size++;
                return true;
            }
        }

        // 出队
        // 如果队列空了，什么也不做，返回false
        // 如果队列非空，弹出头部数字，返回true（返回成功）
        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            } else {
                // l++，结束了，跳回0
                l = l == limit - 1 ? 0 : (l + 1);
                size--;
                return true;
            }
        }

        // 返回队列头部数字，不弹出！
        // 如果没有数字返回-1
        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return queue[l];
        }

        // 返回队列尾部数字
        public int Rear() {
            if (isEmpty()) {
                return -1;
            } else {
                int last = r == 0 ? (limit - 1) : (r - 1);
                return queue[last];
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }

}
