package dynamicprogramming;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * 题目描述：给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格。
 *        ​设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *        （1）你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *        （2）卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 
 * 限制条件：
 *  （1）1 <= prices.length <= 5000
 *  （2）0 <= prices[i] <= 1000
 *  
 * 示例：
 *  输入: [1,2,3,0,2]
 *  输出: 3
 *  解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class _309_BestTimeToBuyAndSellStockWithCooldown {

    public static void main(String[] args) {
        // test case, output: 3
        int[] prices = {1, 2, 3, 0, 2};
        
        _309Solution solution = new _309Solution();
        
        System.out.println(solution.maxProfit(prices));
    }
}

/**
 * 动态规划，此题的动态规划过程有三种状态，状态之间的转移过程如下:
 * 无股票，可买股票       有股票，可卖股票
 *  /↘                 /↘
 *   0 —— -prices[i] →  1
 *    ↖                / +prices[i]
 *     \              ↙
 *           2    
 *         冷冻期
 *  状态 0：表示第 i 天无股票，但是可以购买股票（转移到状态 1）；也可以维持原状，不购买（依然保持在状态 0）。
 *  状态 1：表示第 i 天有股票，可以卖出股票（转移到状态 2）；也可以维持原状，不卖出（依然保持在状态 1）。
 *  状态 2：表示第 i 天处于冷冻期，什么都不能做，只能等到下一天。又因为没有股票，所以转移到状态 0。
 */
class _309Solution {
    
    public int maxProfit(int[] prices) {
        int len = prices.length;
        // 状态存储数组，有三种状态，具体如下：
        // memo[i][0] 表示第 i 天无股票，但是可以购买股票；也可以维持原状，不购买
        // memo[i][1] 表示第 i 天有股票，可以卖出股票；也可以维持原状，不卖出
        // memo[i][2] 表示第 i 天处于冷冻期，什么都不能做
        int[][] memo = new int[prices.length][3];
        // 初始化
        memo[0][0] = 0;
        memo[0][1] = -prices[0];  // 有股票，则需要购买第 0 天的股票 prices[0]。因为需要购买，所以利润为负。
        memo[0][2] = 0;
        
        for (int i = 1; i < len; ++i) {
            memo[i][0] = Math.max(memo[i - 1][0], memo[i - 1][2]);
            memo[i][1] = Math.max(memo[i - 1][1], memo[i - 1][0] - prices[i]);
            memo[i][2] = memo[i - 1][1] + prices[i];
        }
        
        return Math.max(Math.max(memo[len - 1][0], memo[len - 1][1]),
                memo[len - 1][2]);
    }
}