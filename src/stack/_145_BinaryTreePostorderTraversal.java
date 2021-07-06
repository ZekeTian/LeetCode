package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * 
 * 题目描述：给定一棵二叉树的根顶点，返回这棵二叉树的后序遍历结果
 * 
 * 条件限制：
 *  （1）二叉树中节点的数量的范围：[0, 100]
 *  （2）-100 <= Node.val <= 100
 *
 */
public class _145_BinaryTreePostorderTraversal {

    public static void main(String[] args) {
        //        TreeNode root = new TreeNode(1);
        //        TreeNode node2 = new TreeNode(2);
        //        TreeNode node3 = new TreeNode(3);
        //
        //        root.right = node2;
        //        node2.left = node3;

//        TreeNode root = null;
        
        TreeNode root = new TreeNode(1);

        //        _145Solution1 solution = new _145Solution1();

        _145Solution2 solution = new _145Solution2();

        System.out.println(solution.postorderTraversal(root));
    }
}

/**
 * 解法一，递归实现
 */
class _145Solution1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        postorderTraversal(root, result);

        return result;
    }

    private void postorderTraversal(TreeNode root, List<Integer> result) {
        if (null == root) {
            return;
        }

        // 左、右、根
        postorderTraversal(root.left, result);
        postorderTraversal(root.right, result);
        result.add(root.val);
    }
}

/**
 * 解法二，非递归实现
 *
 */
class _145Solution2 {
    private class Command {
        String operation; // 命令的操作，在遍历二叉树的场景下，操作只有两种：（1）go，按照先（中、后）顺序的逻辑处理节点；（2）print，访问节点
        TreeNode data; // 命令需要的数据

        Command(String operation, TreeNode data) {
            this.operation = operation;
            this.data = data;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (null == root) {
            return result;
        }

        Stack<Command> stack = new Stack<Command>(); // 模拟系统栈
        stack.push(new Command("go", root));

        while (!stack.isEmpty()) {
            Command popCommand = stack.pop();

            if ("go".equals(popCommand.operation)) {
                // 访问节点 左右根
                stack.push(new Command("print", popCommand.data));

                if (null != popCommand.data.right) {
                    stack.push(new Command("go", popCommand.data.right));
                }

                if (null != popCommand.data.left) {
                    stack.push(new Command("go", popCommand.data.left));
                }
            } else {
                if (!"print".equals(popCommand.operation)) {
                    throw new IllegalArgumentException("Illegal Operation!");
                }
                result.add(popCommand.data.val);
            }
        }

        return result;
    }
}
