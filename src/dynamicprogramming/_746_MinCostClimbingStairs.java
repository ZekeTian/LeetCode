package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 * 
 * 题目描述：数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
 *        每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 *        请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 *        
 * 限制条件：
 *  （1）cost 的长度范围是 [2, 1000]
 *  （2）cost[i] 将会是一个整型数据，范围为 [0, 999]
 * 
 * 示例：
 *  示例 1
 *      输入：cost = [10, 15, 20]
 *      输出：15
 *      解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 *      
 *  示例 2
 *      输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 *      输出：6
 *      解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 *      
 */
public class _746_MinCostClimbingStairs {
    
    public static void main(String[] args) {
        // test case 1, output: 15
//        int[] cost = {10, 15, 20};
        
        // test case 2, output: 6
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        
//        _746Solution1 solution = new _746Solution1();

//        _746Solution2 solution = new _746Solution2();

        _746Solution3 solution = new _746Solution3();
        
        
        System.out.println(solution.minCostClimbingStairs(cost));
    }
}

/**
 * 解法一：递归实现
 */
class _746Solution1 {
    
    private int[] cost = null;
    
    // 获取从 cost[index] 处开始的最小代价
    private int getMinCost(int index) {
        if (index >= cost.length) {
            return 0;
        }
        
        int res = getMinCost(index + 1) + cost[index]; // 爬一阶楼梯
        res = Math.min(res, getMinCost(index + 2) + cost[index]); // 爬两阶楼梯
        
        return res;
    }
    
    public int minCostClimbingStairs(int[] cost) {
        this.cost = cost;
        
        return Math.min(getMinCost(0), getMinCost(1));
    }
}

/**
 * 解法二：递归 + 记忆化
 */
class _746Solution2 {
    private int[] memo = null;
    private int[] cost = null;
    
    private int getMinCost(int index) {
        if (index >= cost.length) {
            return 0;
        }
        
        if (-1 != memo[index]) {
            return memo[index];
        }
        
        int res = getMinCost(index + 1) + cost[index];
        res = Math.min(res, getMinCost(index + 2) + cost[index]);
        memo[index] = res;
        
        return res;
    }
    
    public int minCostClimbingStairs(int[] cost) {
        this.cost = cost;
        this.memo = new int[cost.length];
        Arrays.fill(memo, -1);
        
        return Math.min(getMinCost(0), getMinCost(1));
    }
}

/**
 * 解法三：自底向上动态规划
 */
class _746Solution3 {
    public int minCostClimbingStairs(int[] cost) {
        if (null == cost || cost.length == 0) {
            return 0;
        }
        if (1 == cost.length) {
            return cost[0];
        }
        
        int[] memo = new int[cost.length + 1];
        memo[cost.length] = 0;
        memo[cost.length - 1] = cost[cost.length - 1];
        
        for (int i = cost.length - 2; i >= 0; --i) {
            memo[i] = Math.min(memo[i + 2], memo[i + 1]) + cost[i]; 
        }
        
        return Math.min(memo[0], memo[1]);
    }
}
