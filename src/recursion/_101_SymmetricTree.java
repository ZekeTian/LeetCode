package recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * 
 * 题目描述：给定一个二叉树，检查它是否是镜像对称的。
 * 
 * 条件限制：
 *  （1）树中顶点数量的范围：[1, 1000]
 *  （2）-100 <= Node.val <= 100
 * 
 * 示例：
 *  示例 1
 *           1
 *         /   \
 *        2     2
 *       / \   / \
 *      3  4  4   3
 *  上面这棵二叉树即为一棵镜像对称的二叉树。
 *  
 *  示例 2
 *          1
 *        /   \
 *        2    2
 *         \    \
 *         3    3
 *  上面这棵二叉树不是镜像二叉树。
 *
 */
public class _101_SymmetricTree {

    public static void main(String[] args) {
        // test case1, output: true
        //        TreeNode root = new TreeNode(1);
        //        TreeNode node21 = new TreeNode(2);
        //        TreeNode node22 = new TreeNode(2);
        //        root.left = node21;
        //        root.right = node22;
        //        TreeNode node31 = new TreeNode(3);
        //        TreeNode node41 = new TreeNode(4);
        //        node21.left = node31;
        //        node21.right = node41;
        //        TreeNode node32 = new TreeNode(3);
        //        TreeNode node42 = new TreeNode(4);
        //        node22.left = node42;
        //        node22.right = node32;

        // test case2, output: false
        TreeNode root = new TreeNode(1);
        TreeNode node21 = new TreeNode(2);
        TreeNode node22 = new TreeNode(2);
        root.left = node21;
        root.right = node22;
        TreeNode node31 = new TreeNode(3);
        TreeNode node32 = new TreeNode(3);
        node21.right = node31;
        node22.right = node32;

        //        _101Solution1 solution = new _101Solution1();
        _101Solution2 solution = new _101Solution2();

        System.out.println(solution.isSymmetric(root));
    }
}

/**
 * 解法一：使用递归，左右子树同时分别进行遍历。
 *      当左子树处理左节点时，右子树处理右节点；当左子树处理右节点时，右子树处理左节点。
 */
class _101Solution1 {

    // left：左子树当前正在处理的节点，right：右子树当前正在处理的节点
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (null == left && null == right) { // 左右子树对应的节点都为 null，则依然对称，返回 true，不需要再进行后面的操作
            return true;
        }

        if (null == left || null == right) { // 左右子树中有一个为 null，有一个不为 null，则不对称，直接返回
            return false;
        }

        return (left.val == right.val) /* 判断左右子树对应的节点是否相等，如果相等则继续判断左右子树的左右节点对称性 */
                && isSymmetric(left.left, right.right) /* 当左子树处理左节点时，右子树处理右节点 */
                && isSymmetric(left.right, right.left); /* 当左子树处理右节点时，右子树处理左节点 */
    }

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }

        return isSymmetric(root.left, root.right);
    }
}

/**
 * 解法二：使用层序遍历。分别对左右子树进行层序遍历，然后左子树每层正序遍历，右子树每层倒序遍历，如果遍历时，两个一样，则继续处理下一层。
 */
class _101Solution2 {

    private class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    // 层序遍历
    private void levelOrder(TreeNode root, List<List<String>> list) {
        if (null == root) {
            return;
        }

        LinkedList<Pair> queue = new LinkedList<>();
        queue.addLast(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            TreeNode node = poll.node;
            int level = poll.level;

            if (level >= list.size()) {
                list.add(new ArrayList<>());
            }

            if (null == node) {
                list.get(level).add("#"); // null 节点，用 "#" 代替
            } else {
                list.get(level).add(node.val + ""); // 非 null 节点，则直接用节点的值即可
                queue.addLast(new Pair(node.left, level + 1));
                queue.addLast(new Pair(node.right, level + 1));
            }
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }

        // 获得左右子树的层序遍历结果
        List<List<String>> leftList = new ArrayList<>();
        List<List<String>> rightList = new ArrayList<>();
        levelOrder(root, leftList);
        levelOrder(root, rightList);

        // 判断左右子树的层数是否一样
        if (leftList.size() != rightList.size()) {
            return false;
        }

        for (int i = 0; i < leftList.size(); ++i) {
            List<String> left = leftList.get(i); // 左子树第 i 层的节点
            List<String> right = rightList.get(i); // 右子树第 i 层的节点

            // 判断左右子树第 i 层的节点数量是否一样
            if (left.size() != right.size()) {
                return false;
            }

            // 左子树第 i 层正序遍历，右子树第 i 层倒序遍历，然后逐个比较，如果不相等，则不对称
            int l = 0, r = right.size() - 1;
            while (l < left.size() && r >= 0) {
                if (!left.get(l++).equals(right.get(r--))) {
                    return false;
                }
            }
        }

        return true;
    }
}
