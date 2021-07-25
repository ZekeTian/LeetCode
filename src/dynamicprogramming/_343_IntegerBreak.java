package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/integer-break/
 * 
 * 题目描述：给定一个整数 n，将 n 分解成 k 个正整数（即这 k 个正整数的和为 n），其中 k >= 2。
 *       求出一种分解方式，使得 k 个整数的乘积最大，最后返回得到的最大的乘积。 
 * 
 * 限制条件：2 <= n <= 58
 * 
 * 示例 1：
 *  输入：n = 2
 *  输出：1
 *  解释： 2 = 1 + 1， 1 x 1 = 1
 * 
 * 示例 2:
 *  输入：n = 10
 *  输出：36
 *  解释：10 = 3 + 3 + 4， 3 x 3 x 4 = 36
 */
public class _343_IntegerBreak {

    public static void main(String[] args) {
        // test case 1, output: 1
        //        int n = 2;

        // test case 2, output: 36
        int n = 10;

        //        _343Solution1 solution = new _343Solution1();
        //        _343Solution2 solution = new _343Solution2();
        _343Solution3 solution = new _343Solution3();

        System.out.println(solution.integerBreak(n));
    }
}

/**
 * 解法一：仅使用 递归 的方式解决
 */
class _343Solution1 {

    private int breakInteger(int n) {
        if (1 == n) {
            return 1;
        }

        int res = -1; // 存储当前 n 拆分后最大的乘积
        for (int i = 1; i < n; ++i) {
            // 将 n 拆分 (i, n-i) 成两个数
            res = Math.max(res, i * (n - i));

            // 将 n 拆分 (i, n-i) 成两部分，但是 n-i 继续拆分
            res = Math.max(res, i * breakInteger(n - i));
        }

        return res;
    }

    public int integerBreak(int n) {
        return breakInteger(n);
    }
}

/**
 * 解法二：使用 递归 + 记忆化搜索 的方式解决
 */
class _343Solution2 {

    // memo[i] 表示 i 拆分后的最大的乘积
    private int[] memo;

    private int breakInteger(int n) {
        if (1 == n) {
            memo[1] = 1;
            return 1;
        }

        int res = -1;
        for (int i = 1; i < n; ++i) {
            // 拆分成 (i, n-i) 两个数
            res = Math.max(res, i * (n - i));

            // 拆分成 (i, n-i) 两部分， n-i 继续拆分
            if (memo[n - i] == 0) {
                memo[n - i] = breakInteger(n - i);
            }
            res = Math.max(res, i * memo[n - i]);
        }

        memo[n] = res;
        return res;
    }

    public int integerBreak(int n) {
        memo = new int[n + 1];

        return breakInteger(n);
    }
}

/**
 * 解法三：使用 动态规划 的方式解决
 */
class _343Solution3 {

    // memo[i] 表示 i 拆分后最大的乘积
    private int[] memo;

    public int integerBreak(int n) {

        memo = new int[n + 1];
        memo[1] = memo[2] = 1;

        for (int i = 3; i <= n; ++i) {
            int res = -1;
            for (int j = 1; j < i; ++j) {
                // 将 i 拆分成 (j, i - j ) 两个数
                res = Math.max(res, j * (i - j));

                // 将 i 拆分成 (j, i - j ) 两部分, i-j 继续拆分
                res = Math.max(res, j * memo[i - j]);
            }
            memo[i] = res;
        }

        return memo[n];
    }
}
