package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * 题目描述：给定一棵二叉树的根顶点，返回这棵二叉树的中序遍历结果
 * 
 * 条件限制：
 *  （1）二叉树中节点的数量的范围：[0, 100]
 *  （2）-100 <= Node.val <= 100
 * 
 */
public class _94_BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.right = node2;
        node2.left = node3;

        //        TreeNode root = null;

        //        _94Solution1 solution = new _94Solution1();
        _94Solution2 solution = new _94Solution2();

        System.out.println(solution.inorderTraversal(root));
    }
}

/**
 * 解法一，递归实现
 */
class _94Solution1 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        if (null == root) {
            return result;
        }

        inorderTraversal(root, result);

        return result;
    }

    private void inorderTraversal(TreeNode root, List<Integer> result) {
        if (null == root) {
            return;
        }

        // 左、根、右
        inorderTraversal(root.left, result);

        result.add(root.val);

        inorderTraversal(root.right, result);
    }
}

/**
 * 解法二，使用非递归方式实现。此方法不是常规的经典非递归实现，而是采用模拟系统栈的方式实现，思路更加通用，可用于先、中、后三种任意遍历方式。
 */
class _94Solution2 {
    private class Command {
        String operation; // 命令对应的操作，在遍历二叉树中操作中，只含有两种：（1）go：按照先、中、后相应顺序处理节点  （2）：print：访问节点，打印节点或保存节点数据
        TreeNode data; // 数据

        public Command(String operation, TreeNode data) {
            this.operation = operation;
            this.data = data;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        if (null == root) {
            return result;
        }

        Stack<Command> stack = new Stack<Command>(); // 模拟系统栈
        Command command = new Command("go", root);
        stack.push(command);

        // 模拟系统栈取命令执行的过程
        while (!stack.isEmpty()) {
            Command popCommand = stack.pop();

            if ("go".equals(popCommand.operation)) {
                // 对节点进行操作。因为是中序遍历，所以节点的访问顺序是：左、根、右。但是又因为栈先进后出的特点，所以命令入栈的顺序应该为：右、根、左
                if (null != popCommand.data.right) {
                    stack.push(new Command("go", popCommand.data.right));
                }

                // 根节点即为 popCommand.data，因为该节点已经入栈处理后，所以此时对该节点的操作应该是访问操作
                stack.push(new Command("print", popCommand.data));

                if (null != popCommand.data.left) {
                    stack.push(new Command("go", popCommand.data.left));
                }
            } else {
                if (!"print".equals(popCommand.operation)) {
                    throw new IllegalArgumentException("Illegal Operation");
                }
                result.add(popCommand.data.val);
            }
        }

        return result;
    }
}
