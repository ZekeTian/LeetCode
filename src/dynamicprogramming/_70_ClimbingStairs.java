package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * 
 * 题目描述：爬一个 n 阶的台阶。每次可以爬 1 阶或者 2 阶，请问有多少种方式可以爬到台阶顶部。
 *
 * 限制条件：1 <= n <= 45
 * 
 * 示例：
 *  输入：n = 2
 *  输出：2。第一种是：爬 1 阶再爬 1 阶；第二种是：直接爬 2 阶。
 */
public class _70_ClimbingStairs {

    public static void main(String[] args) {
        // test case 1, output: 1
        int n = 2;

        // test case 2, output: 3
        //        int n = 3;

        //        _70Solution1 solution = new _70Solution1();

        //        _70Solution2 solution = new _70Solution2();

        _70Solution3 solution = new _70Solution3();

        System.out.println(solution.climbStairs(n));

    }
}

/**
 * 解法一：仅使用简单的递归实现
 */
class _70Solution1 {

    private int climb(int n) {
        if (1 == n) {
            return 1;
        }
        if (2 == n) {
            return 2;
        }

        // 当 n > 2 时，有两种爬法，一种是爬 1 阶，另一种是爬 2 阶
        return climb(n - 1) + climb(n - 2);
    }

    public int climbStairs(int n) {
        return climb(n);
    }
}

/**
 * 解法二：使用 递归 + 记忆化搜索 实现（自顶向下）
 */
class _70Solution2 {

    // memo[i] 表示爬到第 i 阶可能的爬法
    private int[] memo;

    private int climb(int n) {
        if (1 == n) {
            return 1;
        }

        if (2 == n) {
            return 2;
        }

        if (-1 == memo[n]) {
            memo[n] = climb(n - 1) + climb(n - 2);
        }

        return memo[n];
    }

    public int climbStairs(int n) {

        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return climb(n);
    }
}

/**
 * 解法三：使用动态归化实现（自底向上）
 */
class _70Solution3 {

    // memo[i] 表示爬到第 i 阶可能的爬法
    private int[] memo;

    public int climbStairs(int n) {
        if (n < 2) {
            return 1;
        }
        
        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[1] = 1;
        memo[2] = 2;

        for (int i = 3; i <= n; ++i) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }

        return memo[n];
    }
}
