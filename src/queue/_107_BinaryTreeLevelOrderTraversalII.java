package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * 
 * 题目描述：给定一棵二叉树的根节点，返回该树的层序遍历顺序（从左到右，自底向上一层接一层遍历）
 * 
 * 条件限制：
 * （1）树中节点数量的范围：[0, 2000]
 * （2）-1000 <= node.val <= 1000
 *
 */
public class _107_BinaryTreeLevelOrderTraversalII {

    public static void main(String[] args) {
        // test case 1
        //        TreeNode root = new TreeNode(3);
        //        TreeNode node1 = new TreeNode(9);
        //        TreeNode node2 = new TreeNode(20);
        //        root.left = node1;
        //        root.right = node2;
        //
        //        TreeNode node3 = new TreeNode(15);
        //        TreeNode node4 = new TreeNode(7);
        //        node2.left = node3;
        //        node2.right = node4;

        // test case 2
        //                TreeNode root = new TreeNode(1);

        // test case 3
        TreeNode root = null;

        _107Solution solution = new _107Solution();
        System.out.println(solution.levelOrderBottom(root));
    }
}

class _107Solution {
    private class Pair {
        TreeNode node; // 二叉树中的节点
        int level; // 节点对应的层号

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    /**
     * 结合队列和栈实现
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (null == root) {
            return result;
        }

        // 通过队列获得自顶向下的层序
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

        // 用栈翻转队列得到的层序，从而获得自底向上的层序
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        for (List<Integer> list : result) {
            stack.push(list);
        }
        result.clear();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }
}