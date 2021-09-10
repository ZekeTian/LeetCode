package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringJoiner;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 题目描述：给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * 
 * 条件限制：
 *  （1）1 <= preorder.length <= 3000
 *  （2）inorder.length == preorder.length
 *  （3）-3000 <= preorder[i], inorder[i] <= 3000
 *  （4）preorder 和 inorder 均无重复元素 （此限制很关键，有了该条件之后才能在中序遍历的顺序中确定根顶点的下标位置）
 *  （5）inorder 均出现在 preorder
 *  （6）preorder 保证为二叉树的前序遍历序列
 *  （7）inorder 保证为二叉树的中序遍历序列
 * 
 * 示例：
 *                  3
 *                /  \
 *              9    20
 *                   /   \
 *                 15   7
 *   Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *   Output: [3,9,20,null,null,15,7] 层序遍历结果
 */
public class _105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

    private class Pair {
        TreeNode node;
        int level;

        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    private void levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        LinkedList<Pair> queue = new LinkedList<>();

        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int level = pair.level;
            TreeNode node = pair.node;

            if (level >= resultList.size()) {
                resultList.add(new ArrayList<Integer>());
            }
            resultList.get(level).add(node.val);
            
            if (null != node.left) {
                queue.add(new Pair(node.left, level + 1));
            }
            
            if (null != node.right) {
                queue.add(new Pair(node.right, level + 1));
            }
        }

        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < resultList.size(); ++i) {
            ArrayList<Integer> result = resultList.get(i);
            for (int j = 0; j < result.size(); ++j) {
                joiner.add(result.get(j) + "");
            }
        }

        System.out.println("[" + joiner + "]");
    }

    public static void main(String[] args) {
        // test case, output: [3,9,20,null,null,15,7]
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };

        _105Solution solution = new _105Solution();

        new _105_ConstructBinaryTreeFromPreorderAndInorderTraversal().levelOrder(solution.buildTree(preorder, inorder));
    }
}

/**
 * 具体思路：根据先序（根左右）和中序（左根右）的特点，先通过先序确定根顶点，然后在中序的遍历顺序列表中找到根顶点，
 *                  之后根据根顶点将中序遍历顺序列表分成左右子树两部分。最后，根据中序列表中左右子树区间的长度计算出先序
 *                  列表中左右子树的范围，也就是确定出先序列表中左右子树，这样就可以再继续对左右子树进行递归构建。
 */
class _105Solution {

    private int[] preorder = null; // 先序遍历顺序列表
    private HashMap<Integer, Integer> inorderIndexMap = new HashMap<>(); // 中序遍历顺序列表中各个顶点对应的下标，key：顶点，value：下标

    // 区间 [preL, preR] 表示 preorder 中正在处理的区间，区间 [inL, inR] 表示 inorder 中正在处理的区间 
    private TreeNode buildTree(int preL, int preR, int inL, int inR) {
        if (preL > preR) {
            return null;
        }

        int rootVal = preorder[preL]; // 根据先序取出根
        TreeNode root = new TreeNode(rootVal);
        int r = inorderIndexMap.get(rootVal); // 取出中序列表中根顶点对应的下标 

        // 中序（左根右）: inL, .... , r-1, r, r + 1, ..., inR
        // 先序（根左右）: preL, preL+1, ..., x2, x2 + 1, ..., preR
        // 根据中序左子树区间的长度计算出 x2，从而确定先序列表中左右子树区间的范围
        // x2 - (preL+1) = r-1 - inL  --> x2 = r - 1 - inL + preL + 1 = r - inL + preL
        root.left = buildTree(preL + 1, r - inL + preL, inL, r - 1);
        root.right = buildTree(r - inL + preL + 1, preR, r + 1, inR);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;

        for (int i = 0; i < inorder.length; ++i) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildTree(0, preorder.length - 1, 0, inorder.length - 1);
    }
}
