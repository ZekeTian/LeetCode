package dynamicprogramming;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/
 * 
 * 题目描述：给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * 
 * 限制条件：1 <= n <= 19
 * 
 * 示例：
 *  示例 1
 *    1      1         2         3      3
 *     \      \       / \       /      /
 *      3      2     1   3     2      1
 *      /       \             /        \
 *     2         3           1          2
 *      输入：n = 3
 *      输出：5
 *      
 *  示例 2
 *      输入：n = 1
 *      输出：1
 */
public class _96_UniqueBinarySearchTrees {

    public static void main(String[] args) {
        // test case 1, output: 5
//        int n = 3;
        
        // test case 2, output: 1
        int n = 1;
        
        
        _96Solution1 solution = new _96Solution1();
        
        System.out.println(solution.numTrees(n));
    }
}

/**
 * 解法一：递归实现
 */
class _96Solution1 {
    
    private int getNumTrees(int n) {
        if (0 == n || 1 == n) {
            return 1;
        }
        
        int res = 0;
        for (int i = 1; i <= n; ++i) { // 以第 i 个元素为根顶点，然后 [1...i-1] 作为左子树部分，[i+1...n] 作为右子树部分
            res = res + getNumTrees(i - 1) * getNumTrees(n - i); // 左右子树的结果集作笛卡尔积即可得到 n 的最终结果
        }
        
        return res;
    }
    
    public int numTrees(int n) {
        if (n < 0) {
            return 0;
        }
        return getNumTrees(n);
    }
}

