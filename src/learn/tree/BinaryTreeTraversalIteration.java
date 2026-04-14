package learn.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeTraversalIteration {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void preOrder(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.val + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
            System.out.println();
        }
    }

    // 中序遍历非递归版
    public static void inOrder(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    // 没有左树，即叶子结点弹出
                    // 非叶子结点：左树压栈弹出完毕，自己再弹出
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right; // 非叶子结点：处理右子树
                }
            }
            System.out.println();
        }
    }

    // 后序遍历打印所有节点，非递归版
    // 用两个栈的方法
    // 中->collect,左->stack,右->stack,stack.head->collect
    // 入collect的顺序：中右左
    public static void posOrderTwoStacks(TreeNode head) {
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            Stack<TreeNode> collect = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                collect.push(head);
                if (head.left != null) {
                    stack.push(head.left);
                }
                if (head.right != null) {
                    stack.push(head.right);
                }
            }
            while (!collect.isEmpty()) {
                System.out.print(collect.pop().val + " ");
            }
            System.out.println();
        }
    }

    // 后序遍历非递归版
    // 只使用一个栈
    public static void posOrderOneStack(TreeNode h) {
        if (h != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(h);

            // 如果始终没有打印过节点，h就一直是头节点
            // 一旦打印过节点，h变成打印节点
            // 此后h的含义：上一次打印的节点
            while (!stack.isEmpty()) {
                TreeNode cur = stack.peek();
                if (cur.left != null
                        && h != cur.left
                        && h != cur.right) {
                    // 有左树且左树没有处理器过
                    stack.push(cur.left);
                } else if (cur.right != null
                        && h != cur.right) {
                    // 有右树且没处理过
                    stack.push(cur.right);
                } else {
                    System.out.print(cur.val + " ");
                    h = stack.pop();
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        System.out.println("先序遍历非递归版");
        preOrder(head);

        System.out.println("中序遍历非递归版");
        inOrder(head);

        System.out.println("后序遍历非递归版");
        System.out.println("2个栈实现");
        posOrderTwoStacks(head);

        System.out.println("1个栈实现");
        posOrderOneStack(head);
    }

    /// ///////////////////////////////////////////////////////////////
    /// 用1个栈完成先序遍历
    /// 测试链接：https://leetcode.cn/problems/binary-tree-preorder-traversal
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            if (root != null) {
                Stack<TreeNode> stack = new Stack<>();
                stack.push(root);
                while (!stack.isEmpty()) {
                    root = stack.pop();
                    ans.add(root.val);
                    if (root.right != null) {
                        stack.push(root.right);
                    }
                    if (root.left != null) {
                        stack.push(root.left);
                    }
                }
            }
            return ans;
        }
    }

    /// 用一个栈完成中序遍历
    /// 测试链接：https://leetcode.cn/problems/binary-tree-inorder-traversal
    public  static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    ans.add(root.val);
                    root = root.right;
                }
            }
        }
        return ans;
    }

    /// 用2个栈完成后序遍历
    /// 测试链接：https://leetcode.cn/problems/binary-tree-postorder-traversal
    /// 提交时改函数名为postorderTraversal
    public static List<Integer> postorderTraversalTwoStacks(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            Stack<TreeNode> collect = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                collect.push(root);
                if (root.left != null) {
                    stack.push(root.left);
                }
                if (root.right != null) {
                    stack.push(root.right);
                }
            }
            while (!collect.isEmpty()) {
                ans.add(collect.pop().val);
            }
        }
        return ans;
    }

    /// 用2个栈完成后序遍历
    /// 测试链接：https://leetcode.cn/problems/binary-tree-postorder-traversal
    public static List<Integer> postorderTraversalOneStack(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode cur = stack.peek();
                if (cur.left != null
                        &&cur.left != root
                        && cur.right != root) {
                    stack.push(cur.left);
                } else if (cur.right != null
                        &&cur.right != root) {
                    stack.push(cur.right);
                } else {
                    ans.add(cur.val);
                    root = stack.pop();
                }
            }
        }
        return ans;
    }

}

