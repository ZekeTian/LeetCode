package dynamicprogramming;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * 
 * 题目描述：
 * 
 * 
 * 限制条件：
 *  （1）1 <= prices.length <= 5 * 10^4
 *  （2）1 <= prices[i] < 5 * 10^4
 *  （3）0 <= fee < 5 * 10^4
 * 
 * 示例：
 *  示例 1
 *      输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 *      输出：8
 *      解释：能够达到的最大利润:  
 *          在此处买入 prices[0] = 1
 *          在此处卖出 prices[3] = 8
 *          在此处买入 prices[4] = 4
 *          在此处卖出 prices[5] = 9
 *          总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 *
 *  示例 2
 *      输入：prices = [1, 3, 7, 5, 10, 3], fee = 3
 *      输出：6
 */
public class _714_BestTimeToBuyAndSellStockWithTransactionFee {

    public static void main(String[] args) {
        // test case1, output: 8
//        int[] prices = { 1, 3, 2, 8, 4, 9 };
//        int fee = 2;

        // test case2, output: 6
        int[] prices = { 1, 3, 7, 5, 10, 3 };
        int fee = 3;
        
        _714Solution solution = new _714Solution();
        
        
        System.out.println(solution.maxProfit(prices, fee));
    }
}

/**
 * 动态规划，思路和第 122 道题一样，只不过在每次卖出时需要再额外支付手续费用
 */
class _714Solution {
    
    public int maxProfit(int[] prices, int fee) {
        if (null == prices || 0 == prices.length) {
            return 0;
        }
        
        int[][] memo = new int[prices.length][2];
        memo[0][0] = 0;
        memo[0][1] = -prices[0];
        
        for (int i = 1; i < prices.length; ++i) {
            memo[i][0] = Math.max(memo[i - 1][0], memo[i - 1][1] + prices[i] - fee); // 卖出股票，在赚钱的同时还需要支付手续费
            memo[i][1] = Math.max(memo[i - 1][1], memo[i - 1][0] - prices[i]); // 买入股票，需要支付股票钱
        }
        
        return Math.max(memo[prices.length - 1][0], memo[prices.length - 1][1]);
    }
}

