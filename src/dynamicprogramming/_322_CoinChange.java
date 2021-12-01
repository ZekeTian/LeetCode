package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 * 
 * 题目描述：给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *        计算并返回可以凑成总金额所需的最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *        你可以认为每种硬币的数量是无限的。
 * 
 * 限制条件：
 *  （1）1 <= coins.length <= 12
 *  （2）1 <= coins[i] <= 231 - 1
 *  （3）0 <= amount <= 104
 * 
 * 示例：
 *  示例 1
 *      输入：coins = [1, 2, 5], amount = 11
 *      输出：3
 *      解释：11 = 5 + 5 + 1
 *  
 *  示例 2
 *      输入：coins = [2], amount = 3
 *      输出：-1
 *  
 *  示例 3
 *      输入：coins = [1], amount = 2
 *      输出：2
 */
public class _322_CoinChange {
    
    public static void main(String[] args) {
        // test case 1, output: 3
        int[] coins = {1, 2, 5};
        int amount = 11;
        
        // test case 2, output: -1
//        int[] coins = {2};
//        int amount = 3;
        
        // test case 3, output: 2
//        int[] coins = {1};
//        int amount = 2;
        
        _322Solution1 solution = new _322Solution1();
        
        System.out.println(solution.coinChange(coins, amount));
    }
}

/**
 * 解法一：递归 + 记忆化
 */
class _322Solution1 {
    
    private int[] memo = null;
    private int[] coins = null;
    
    private int getCoinChange(int amount) {
        if (amount < 0) {
            return -1;
        }
        if (0 == amount) {
            memo[amount] = 0;
            return 0;
        }
        
        if (0 != memo[amount]) {
            return memo[amount];
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; ++i) {
            int res = getCoinChange(amount - coins[i]);
            if (-1 != res) {
                min = Math.min(min, res + 1); // amount - coins[i] 能够找零成功，与当前的最小值进行比较，然后取两者中的较小值 
            }
        }
        
        if (min == Integer.MAX_VALUE) { 
            min = -1; // 上面循环中的 if 无法进入，则说明当前 amount 无法找零成功
        }
        memo[amount] = min;
        return min;
    }
    
    public int coinChange(int[] coins, int amount) {
        this.memo = new int[amount + 1];
        this.coins = coins;
        
        return getCoinChange(amount);
    }
}

/**
 * 解法二：自底向上动态规划
 */
class _322Solution2 {
    
    public int coinChange(int[] coins, int amount) {
        if (coins.length < 0 || amount < 0) {
            return -1;
        }
        
        if (0 == amount) {
            return 0;
        }
        
        Arrays.sort(coins); // 对 coins 进行升序排序，方便处理
        if (amount < coins[0]) {
            return -1; // 最小的零钱都比 amount 大，则无法找零
        }
        
        int[] memo = new int[amount + 1];
        for (int i = 1; i < coins[0]; ++i) {
            memo[i] = -1;
        }
        memo[coins[0]] = 1;
        
        for (int i = coins[0] + 1; i <= amount; ++i) {
            int min = Integer.MAX_VALUE;
            for (int c : coins) {
                if (c > i) { // 因为已经排序，所以后面的零钱均大于 i，无法继续找零
                    break;
                }
                if (-1 != memo[i - c]) { // 可以找零，则取较小值
                    min = Math.min(min, memo[i - c] + 1);
                }
            }
            if (min == Integer.MAX_VALUE) {
                min = -1; // 当前 i 对应的金额无法继续找零，所以 min 置为 -1
            }
            memo[i] = min;
        }
        
        return memo[amount];
    }
}
