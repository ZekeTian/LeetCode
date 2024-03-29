package dynamicprogramming;

import java.util.Arrays;

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
        int n = 3;
        
        // test case 2, output: 1
//        int n = 1;
        
        
//        _96Solution1 solution = new _96Solution1();

//        _96Solution2 solution = new _96Solution2();
        
        _96Solution3 solution = new _96Solution3();
        
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

/**
 * 解法二：递归 + 记忆化
 */
class _96Solution2 {
    
    private int[] memo = null;
    
    private int getNumTrees(int n) {
        if (0 == n || 1 == n) {
            memo[n] = 1;
            return 1;
        }
        
        if (-1 != memo[n]) {
            return memo[n];
        }
        
        int res = 0;
        for (int i = 1; i <= n; ++i) {
            res = res + getNumTrees(i - 1) * getNumTrees(n - i);
        }
        memo[n] = res;
        
        return res;
    }
    
    public int numTrees(int n) {
        if (n < 0) {
            return 0;
        }
        this.memo = new int[n + 1]; // memo[i] 存储 i 个节点所能组成的二叉树的数量
        Arrays.fill(memo, -1);
        
        return getNumTrees(n);
    }
}

/**
 * 解法三：自底向上动态规划
 */
class _96Solution3 {
    
    public int numTrees(int n) {
        int[] memo = new int[n + 1];
        memo[0] = memo[1] = 1;
        
        for (int i = 2; i <= n; ++i) {
            // 计算长度为 i 的结果
            int res = 0;
            for (int j = 1; j <= i; ++j ) {
                res = res + memo[j - 1] * memo[i - j]; // 以 j 为根顶点，然后 [1...j-1] 作为左子树、[j+1...i] 作为右子树
            }
            memo[i] = res;
        }
        
        return memo[n];
    }
}
