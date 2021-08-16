package dynamicprogramming;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * 
 * 题目描述：给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *       你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *       返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *       
 * 限制条件：
 *  （1）1 <= prices.length <= 10^5
 *  （2）0 <= prices[i] <= 10^4
 *  
 * 示例：
 *  示例 1：
 *  Input: [7,1,5,3,6,4]
 *  Output: 5
 *  解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *      
 *  示例 2：
 *  Input: [7,6,4,3,1]
 *  Output: 0
 */
public class _121_BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        // test case1, output: 5
//        int[] prices = { 7, 1, 5, 3, 6, 4 };

        // test case2, output: 0
        int[] prices = { 7, 6, 4, 3, 1 };
        
        
        _121Solution1 solution = new _121Solution1();
        
        System.out.println(solution.maxProfit(prices));
    }
}

/**
 * 解法一：动态规划
 */
class _121Solution1 {
    public int maxProfit(int[] prices) {
        int minPrices = prices[0]; // 存储过去时间的最低股票价，也就是最佳的股票购入价格，便于每天计算收益
        int[] mem = new int[prices.length]; // 存储每天能够获取的最大利润，mem[i] 表示第 i 天能够获取的最大利润

        mem[0] = 0;

        for (int i = 1; i < prices.length; ++i) {
            minPrices = Math.min(minPrices, prices[i]);
            mem[i] = Math.max(mem[i - 1], prices[i] - minPrices); // 当天有两种状态，第一种是不卖股票，第二种是卖股票。比较两种状态，取最大值即为当天最大利润 
        }

        return mem[prices.length - 1];
    }
}
