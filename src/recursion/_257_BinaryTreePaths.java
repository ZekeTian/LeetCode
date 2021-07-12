package recursion;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-paths/
 *
 * 题目描述：给定一棵二叉树的根顶点，返回所有从根顶点到叶子顶点的路径（顺序可以是任意的）。
 * 
 * 条件限制：
 *  （1）二叉树中顶点的数量范围：[1, 100]
 *  （2）-100 <= Node.val <= 100
 */
public class _257_BinaryTreePaths {

    public static void main(String[] args) {
        // test case 1
        //        TreeNode root = new TreeNode(1);
        //        TreeNode node2 = new TreeNode(2);
        //        TreeNode node3 = new TreeNode(3);
        //        TreeNode node5 = new TreeNode(5);
        //        
        //        root.left = node2;
        //        root.right = node3;
        //        node2.left = node5;

        // test case 2
        TreeNode root = new TreeNode(1);

        _257Solution solution = new _257Solution();

        //        System.out.println(solution.binaryTreePaths(root)); // test case 1: ["1->2->5","1->3"]
        System.out.println(solution.binaryTreePaths(root)); // test case 2: ["1"]
    }

}

class _257Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (null == root.left && null == root.right) {
            List<String> pathList = new ArrayList<String>();
            pathList.add("" + root.val);
            return pathList;
        }

        List<String> pathList = new ArrayList<String>();
        if (null != root.left) {
            List<String> leftPathList = binaryTreePaths(root.left);
            for (String path : leftPathList) {
                pathList.add(root.val + "->" + path);
            }
        }

        if (null != root.right) {
            List<String> rightPathList = binaryTreePaths(root.right);
            for (String path : rightPathList) {
                pathList.add(root.val + "->" + path);
            }
        }

        return pathList;
    }
}
