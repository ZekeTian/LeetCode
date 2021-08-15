package queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 *
 * 题目描述：给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 
 * 示例：
 *          3
 *         / \
 *        9  20
 *          /  \
 *         15   7
 *  锯齿形层序遍历
 *      3
 *      20,9
 *      15,7
 */
public class _103_BinaryTreeZigzagLevelOrderTraversal {

    public static void main(String[] args) {
        // test case 1, output: [[3], [20, 9], [15, 7]]
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

        // test case 2, output: [[1]]
        //                TreeNode root = new TreeNode(1);

        // test case 3, output: []
        TreeNode root = null;

        _103Solution solution = new _103Solution();
        System.out.println(solution.zigzagLevelOrder(root));
    }
}

/**
 * 先获得正常的层序，然后根据层数的奇偶性反转
 */
class _103Solution {
    private class Pair {
        TreeNode node;
        int level;

        public Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (null == root) {
            return resultList;
        }

        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            TreeNode node = poll.node;
            int level = poll.level;

            if (level == resultList.size()) {
                resultList.add(new ArrayList<>());
            }
            resultList.get(level).add(node.val);

            if (null != node.left) {
                queue.add(new Pair(node.left, level + 1));
            }
            if (null != node.right) {
                queue.add(new Pair(node.right, level + 1));
            }
        }

        for (int i = 0; i < resultList.size(); ++i) {
            // 获得正常的层序遍历结果后，将奇数层的结果进行反转
            if (i % 2 != 0) {
                List<Integer> result = resultList.get(i);
                Collections.reverse(result);
            }
        }

        return resultList;
    }
}
