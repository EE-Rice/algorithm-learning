package learn.queue_stack;

import java.util.Deque;
import java.util.LinkedList;

// 测试链接：https://leetcode.cn/problems/design-circular-deque/
public class MyDeque {

    // 实际内部就是双向链表
    // 常数操作慢
    // 提交测试时构造方法名改成MyCircularDeque
    class MyCircularDeque1 {

        public Deque<Integer> deque = new LinkedList<>();
        public int size;
        public int limit;

        public MyCircularDeque1(int k) {
            size = 0;
            limit = k;
        }

        public boolean insertFront(int val) {
            if (isFull()) {
                return false;
            } else {
                deque.offerFirst(val);
                size++;
                return true;
            }
        }

        public boolean insertLast(int val) {
            if (isFull()) {
                return false;
            } else {
                deque.offerLast(val);
                size++;
                return true;
            }
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            } else {
                deque.pollFirst();
                size--;
                return true;
            }
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            } else {
                deque.pollLast();
                size--;
                return true;
            }
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            } else {
                return deque.peekFirst();
            }
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            } else {
                return deque.peekLast();
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }
    }


    // 固定数组实现双向队列
    class MyCircularDeque2 {

        public int[] deque;
        // l, r 标记队列头尾元素
        public int l, r, size, limit;

        public MyCircularDeque2(int k) {
            deque = new int[k];
            l = r = size = 0;
            limit = k;
        }

        public boolean insertFront(int val) {
            if (isFull()) {
                return false;
            } else  {
                if (isEmpty()) {
                    l = r = 0;
                    deque[0] = val;
                } else  {
                    l = l == 0 ? (limit - 1) : (l - 1);
                    deque[l] = val;
                }
                size++;
                return true;
            }
        }

        public boolean insertLast(int val) {
            if (isFull()) {
                return false;
            } else   {
                if (isEmpty()) {
                    l = r = 0;
                    deque[0] = val;
                } else {
                    r = (r == limit - 1) ? 0 : (r + 1);
                    deque[r] = val;
                }
                size++;
                return true;
            }
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            } else {
                l = (l == limit -1) ? 0 : (l + 1);
                size--;
                return true;
            }
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }  else {
                r = r == 0 ? (limit - 1) : (r - 1);
                size--;
                return true;
            }
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            } else {
                return deque[l];
            }
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }  else {
                return deque[r];
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
