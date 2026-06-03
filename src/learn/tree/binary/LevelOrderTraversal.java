package learn.tree.binary;

import java.util.*;

// 二叉树的层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
public class LevelOrderTraversal {

    // 不提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 提交时把方法名改为levelOrder，此方法为普通bfs，此题不推荐
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            HashMap<TreeNode, Integer> levels = new HashMap<>();
            queue.add(root);
            levels.put(root, 0);
            while (!queue.isEmpty()) {
                TreeNode cur = queue.poll();
                int level = levels.get(cur);
                if (ans.size() == level) {
                    ans.add(new ArrayList<>());
                }
                ans.get(level).add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                    levels.put(cur.left, level + 1);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    levels.put(cur.right, level + 1);
                }
            }
        }
        return ans;
    }
}
