package dynamicprogramming;


/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 
 * 题目描述：给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 *        设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *        注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *        
 * 限制条件：
 *  （1）1 <= prices.length <= 3 * 10^4
 *  （2）0 <= prices[i] <= 104
 * 
 * 示例：
 *  示例 1
 *      输入: prices = [7,1,5,3,6,4]
 *      输出: 7
 *      解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *           随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 *  示例 2
 *      输入: prices = [1,2,3,4,5]
 *      输出: 4
 *      解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *           注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *  
 *  示例 3
 *      输入: prices = [7,6,4,3,1]
 *      输出: 0
 *      解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class _122_BestTimeToBuyAndSellStockII {

    public static void main(String[] args) {
        // test case1, output: 7
//        int[] prices = {7, 1, 5, 3, 6, 4};
        
        // test case2, output: 4
//        int[] prices = {1, 2, 3, 4, 5};
        
        // test case3, output: 0
        int[] prices = {7, 6, 4, 3, 1};
        
        _122Solution solution = new _122Solution();
        
        
        System.out.println(solution.maxProfit(prices));
    }
}

/**
 * 动态规划
 * 第 i 天的状态分为两种：
 *  （1）无股票：可以维持现状，也可以购买股票 
 *  （1）有股票：可以维持现状，也可以卖出股票 
 * 以上两种状态的转换过程如下：
 *  
 *   /↘           /↘
 *  无股票 -购买股票→ 有股票
 *     ↖           ↙
 *      ——卖出股票——
 */
class _122Solution {
    
    public int maxProfit(int[] prices) {
        if (null == prices || 0 == prices.length) {
            return 0;
        }
        
        int[][] memo = new int[prices.length][2];
        memo[0][0] = 0; // 无股票
        memo[0][1] = -prices[0]; // 有股票 
        
        for (int i = 1; i < prices.length; ++i) {
            memo[i][0] = Math.max(memo[i - 1][0], memo[i - 1][1] + prices[i]); // 在不买股票、卖股票两种情况之间取较大值
            memo[i][1] = Math.max(memo[i - 1][1], memo[i - 1][0] - prices[i]); // 在不卖出股票、买股票两种情况之间取较大值
        }
        
        return Math.max(memo[prices.length - 1][0], memo[prices.length - 1][1]);
    }
}
