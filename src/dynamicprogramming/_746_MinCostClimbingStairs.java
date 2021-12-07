package dynamicprogramming;

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
        
        _746Solution1 solution = new _746Solution1();
        
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
