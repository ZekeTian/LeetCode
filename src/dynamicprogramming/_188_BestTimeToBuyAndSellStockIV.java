package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * 
 * 题目描述：给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *        设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *        注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 
 * 限制条件：
 *  （1）0 <= k <= 100
 *  （2）0 <= prices.length <= 1000
 *  （3）0 <= prices[i] <= 1000
 * 
 * 示例：
 *  示例 1
 *      输入：k = 2, prices = [2,4,1]
 *      输出：2
 *      解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *      
 *  示例 2
 *      输入：k = 2, prices = [3,2,6,5,0,3]
 *      输出：7
 *      解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *          随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
 *          
 */
public class _188_BestTimeToBuyAndSellStockIV {

    public static void main(String[] args) {
        // test case1, output: 2
//        int k = 2;
//        int[] prices = { 2, 4, 1 };
        
        // test case2, output: 7
        int k = 2;
        int[] prices = { 3, 2, 6, 5, 0, 3 };
        
        
        _188Solution solution = new _188Solution();
        
        
        System.out.println(solution.maxProfit(k, prices));
    }
}

/**
 * 动态规划，状态转移同第 123 道题，只不过此时第三维的状态数量是 k + 1，而不是 3
 * 状态转移过程如下：
 *  
 *      /↘                      /↘                         /↘                       /↘
 *  无股票，交易 j - 1 次 ——买股票——→ 有股票，交易 j 次 ——卖股票——→ 无股票，交易 j 次 ——买股票——→ 有股票，交易 j + 1 次 ——卖股票——→……
 *  memo[i][0][j] = Math.max(memo[i - 1][0][j], memo[i - 1][1][j] + prices[i]);
 *  memo[i][1][j] = Math.max(memo[i - 1][1][j], memo[i - 1][0][j - 1] - prices[i]);
 *  
 */
class _188Solution {
    
    public int maxProfit(int k, int[] prices) {
        if (null == prices || 0 == prices.length || k <= 0) {
            return 0;
        }
        
        
        // memo[i][j][k]，数组各维的含义：i，表示第 i 天；j，表示是否有股票，只有 0、1 两种取值；k，表示交易 k 次
        int[][][] memo = new int[prices.length][2][k + 1];
        
        memo[0][0][0] = 0;
        Arrays.fill(memo[0][1], -prices[0]);
        memo[0][1][0] = 0;
        
        
        for (int i = 1; i < prices.length; ++i) {
            for (int j = 1; j <= k; ++j) {
                memo[i][0][j] = Math.max(memo[i - 1][0][j], memo[i - 1][1][j] + prices[i]); // 当前无股票
                memo[i][1][j] = Math.max(memo[i - 1][1][j], memo[i - 1][0][j - 1] - prices[i]); // 当前有股票
            }
        }
        
        int max = 0;
        for (int i = 0; i <= k; ++i) {
            max = Math.max(memo[prices.length - 1][0][i], max);
        }
        
        return max;
    }
}

