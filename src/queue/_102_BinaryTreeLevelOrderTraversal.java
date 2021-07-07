package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * 
 * 题目描述：给定一棵二叉树的根节点，返回该树的层序遍历顺序（从左到右，自顶向下一层接一层遍历）
 * 
 * 条件限制：
 * （1）树中节点数量的范围：[0, 2000]
 * （2）-1000 <= node.val <= 1000
 *
 */
public class _102_BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        // test case 1
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        root.left = node1;
        root.right = node2;

        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        node2.left = node3;
        node2.right = node4;

        // test case 2
        //        TreeNode root = new TreeNode(1);

        // test case 3
        //        TreeNode root = null;

        _102Solution1 solution = new _102Solution1();
        System.out.println(solution.levelOrder(root));
    }
}

/**
 * 解法一，在每层最后一个元素处创建一个新的 list ，用于存储下一层的元素
 */
class _102Solution1 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> finalResult = new ArrayList<List<Integer>>();

        if (null == root) {
            return finalResult;
        }

        int pollCount = 0; // 用于统计每层中队列已经出队的元素个数
        int currentLeveElemlnum = 1; // 用于记录当前层的元素个数。初始时，第 0 层为根节点，个数为 1
        int nextLevelElemNum = 0; // 用于记录下一层的元素个数
        int level = 0; // 当前层的层号
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>(); // 辅助队列

        queue.add(root);
        finalResult.add(new ArrayList<Integer>());

        while (!queue.isEmpty()) {
            TreeNode head = queue.poll();
            ++pollCount;
            finalResult.get(level).add(head.val); // 将元素加入到当前层中

            // 左右子节点入队列
            if (null != head.left) {
                queue.add(head.left);
                ++nextLevelElemNum;
            }

            if (null != head.right) {
                queue.add(head.right);
                ++nextLevelElemNum;
            }

            if (pollCount == currentLeveElemlnum /* 当前层已经遍历完 */
                    && 0 != nextLevelElemNum /* 存在下一层 */ ) {
                // 当前层已经遍历完，则需要添加新的 List（即添加新的一层）、重置计数器
                finalResult.add(new ArrayList<Integer>()); // 添加新的一层（此种解法，是在每层最后一个元素处添加新的 list）
                currentLeveElemlnum = nextLevelElemNum; // 当前层已经遍历完，需要继续遍历下一层
                ++level; // 层号增加
                // 重置计算器
                nextLevelElemNum = 0;
                pollCount = 0;
            }
        }

        return finalResult;
    }
}

/**
 * 解法二，在每层第一个元素处创建一个新的 list ，用于存储当前层元素
 */
class _102Solution2 {
    /**
     * 二元组类
     */
    private class Pair<T1, T2> {
        T1 first;
        T2 second;

        Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> finalResult = new ArrayList<List<Integer>>();

        if (null == root) {
            return finalResult;
        }

        // 辅助队列，元素类型是二元组。二元组中，第一个元素是二叉树中的节点，第二个元素是节点对应的层号
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();
        queue.add(new Pair<TreeNode, Integer>(root, 0));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> head = queue.poll();
            TreeNode node = head.first; // 当前节点
            int level = head.second; // 当前层的层号

            if (level == finalResult.size()) { // 当前层的第一个元素，需要创建一个新的 list 来存储当前层的元素
                finalResult.add(new ArrayList<Integer>());
            }
            finalResult.get(level).add(node.val); // 将当前元素添加到当前层中

            // 左右子节点入队列
            if (null != node.left) {
                queue.add(new Pair<TreeNode, Integer>(node.left, level + 1));
            }
            if (null != node.right) {
                queue.add(new Pair<TreeNode, Integer>(node.right, level + 1));
            }
        }

        return finalResult;
    }
}
