package dynamicprogramming;

/**
 * https://leetcode.com/problems/coin-change-2/
 * 
 * 题目描述：给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 *        请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 *        假设每一种面额的硬币有无限个。题目数据保证结果符合 32 位带符号整数。
 * 
 * 限制条件：
 *  （1）1 <= coins.length <= 300
 *  （2）1 <= coins[i] <= 5000
 *  （3）coins 中的所有值 互不相同
 *  （4）0 <= amount <= 5000
 * 
 * 示例：
 *  示例 1
 *      输入：amount = 5, coins = [1, 2, 5]
 *      输出：4
 *      解释：有四种方式可以凑成总金额：
 *      5=5
 *      5=2+2+1
 *      5=2+1+1+1
 *      5=1+1+1+1+1
 *      
 *  示例 2
 *      输入：amount = 3, coins = [2]
 *      输出：0
 *      解释：只用面额 2 的硬币不能凑成总金额 3 。
 *      
 *  示例 3
 *      输入：amount = 10, coins = [10] 
 *      输出：1
 */
public class _518_CoinChange2 {

    public static void main(String[] args) {
        // test case 1, output: 4
//        int amount = 5;
//        int[] coins = {1, 2, 5};
        
        // test case 2, output: 0
//        int amount = 3;
//        int[] coins = {2};
        
        // test case 3, output: 1
        int amount = 10;
        int[] coins = {10}; 
        
        _518Solution solution = new _518Solution();
        
        System.out.println(solution.change(amount, coins));
    }
}

/**
 * 动态规划求解
 * 
 * 此题中只要求组合个数，并且组合的顺序是无序的，即：2 + 2 + 1 和 1 + 2 + 2 是一样的。
 * 为了保证组合顺序是无序的，所以要注意代码中的两层 for 循环的内外顺序。
 * 
 * 示例：amount = 3, coins = [1, 2, 5]
 * 第一种循环写法（背包容量在外，物品在内）：有顺序
 * for (int a = 1; a <= amount; ++a) {
 *     for (int c : coins) {
 *         if (a >= c) {
 *             memo[a] += memo[a - c];
 *         }
 *     }
 * }
 * 示例计算过程：
 * a = 0: 1
 * a = 1: 1
 * a = 2: memo[1] + memo[0] = 1 + 1 = 2
 *   1 + 1
 *   2
 * a = 3: memo[2] + memo[1] = 2 + 1 = 3
 *  1 + 1 + 1
 *  1 + 2
 *  2 + 1
 * 当 a = 3 时，1 + 2 和 2 + 1 是重复的
 * 
 * 示例：amount = 3, coins = [1, 2, 5]
 * 第二种循环写法（背包容量在内，物品在外）：无顺序要求
 * for (int c : coins) {
 *     for (int a = c; a <= amount; ++a) {
 *         memo[a] += memo[a - c];
 *     }
 * }
 * c = 1: 
 *   a = 1: 1
 *   a = 2: 1，即组合形式为：1 + 1
 *   a = 3: 1，即组合形式为：1 + 1 + 1
 * c = 2:
 *   a = 2: memo[2] + memo[0] = 1 + 1 = 2，即组合形式为：2、1 + 1
 *   a = 3: memo[3] + memo[1] = 1 + 1 = 2，即组合形式为：2 + 1、1 + 1 + 1
 * c = 5: 内层循环无计算过程
 */
class _518Solution {
    public int change(int amount, int[] coins) {
        int[] memo = new int[amount + 1];
        memo[0] = 1;
        
        for (int c : coins) { // 为了使得结果是无序的， coins 要放在外层遍历
            for (int a = c; a <= amount; ++a) {
                memo[a] += memo[a - c];
            }
        }
        
        return memo[amount];
    }
}

