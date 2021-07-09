package recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

public class _226_InvertBinaryTree {

    public static void main(String[] args) {
        // test case 1
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);

        root.left = node2;
        root.right = node7;
        node2.left = node1;
        node2.right = node3;
        node7.left = node6;
        node7.right = node9;

        _226Solution solution = new _226Solution();
        TreeNode invertRoot = solution.invertTree(root);
        System.out.println(solution.levelOrder(invertRoot));
    }
}

class _226Solution {
    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * 层序遍历二叉树，获得遍历结果，主要用于检查反转二叉树是否成功。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (null == root) {
            return result;
        }

        LinkedList<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            TreeNode node = poll.node;
            int level = poll.level;

            if (level == result.size()) {
                result.add(new ArrayList<Integer>());
            }

            result.get(level).add(node.val);
            if (null != node.left) {
                queue.add(new Pair(node.left, level + 1));
            }
            if (null != node.right) {
                queue.add(new Pair(node.right, level + 1));
            }
        }

        return result;
    }

    private class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}
