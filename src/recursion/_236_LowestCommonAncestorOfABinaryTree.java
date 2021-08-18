package recursion;

import java.util.LinkedHashSet;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 
 * 题目描述：给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *        最近公共祖先的定义为：对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 *        
 * 条件限制：
 *  （1）树中节点数目的范围：[2, 10^5]
 *  （2）-10^9 <= Node.val <= 10^9
 *  （3）所有 Node.val 互不相同
 *  （3）p != q
 *  （4）p 和 q 均存在于给定的二叉树中
 *
 * 示例：
 *                  3 
 *                /   \
 *               5     1
 *              /  \  / \
 *             6   2  0  8
 *                / \
 *               7   4
 *   顶点 5、1 的最近公共祖先是 3；顶点 5、4 的最近公共祖先是 5
 */
public class _236_LowestCommonAncestorOfABinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        root.left = node5;
        root.right = node1;
        
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        node5.left = node6;
        node5.right = node2;
        
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        node1.left = node0;
        node1.right = node8;
        
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        node2.left = node7;
        node2.right = node4;
        
        _236Solution1 solution = new _236Solution1();
        
        // test case1, output: 3
        TreeNode p = node5;
        TreeNode q = node1;
        
        // test case2, output: 5
//        TreeNode p = node5;
//        TreeNode q = node4;
        
        System.out.println(solution.lowestCommonAncestor(root, p, q).val);
    }
}

/**
 * 解法一：分别记录根顶点到 p、q 的路径（路径是自底向上记录），然后找两条路径中的第一个公共顶点，即为最近公共祖先。
 * “找两条路径中的第一个公共顶点”，这个过程中一是要解决“找”，二是要保证“第一”。因此，要使用一种查度速度快、能够保证插入顺序的数据结构，即 LinkedHashSet(LinkedHashMap)
 */
class _236Solution1 {
    
    // 在以 root 为根顶点的二叉树中生成 root 到 find 的路径，生成后并返回路径
    private LinkedHashSet<TreeNode> generatePath(TreeNode root, TreeNode find) {
        if (null == root) {
            return null;
        }
        
        if (root == find) {
            LinkedHashSet<TreeNode> path = new LinkedHashSet<>();
            path.add(root);
            return path;
        }
        
        LinkedHashSet<TreeNode> path = generatePath(root.left, find);
        if (null != path) {
            path.add(root); 
            return path; // 在左子树中找到了 find 节点，则无需看右子树
        }
        
        path = generatePath(root.right, find);
        if(null != path) {
            path.add(root);
        }
        return path;
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        // 生成根顶点到 p、q 两个顶点的路径
        LinkedHashSet<TreeNode> pathP = generatePath(root, p);
        LinkedHashSet<TreeNode> pathQ = generatePath(root, q);
        
        
        // 寻找 pathP、pathQ 中的第一个公共顶点，并返回
        for (TreeNode node : pathP) {
            if (pathQ.contains(node)) {
                return node;
            }
        }
        
        return null;
    }
}
