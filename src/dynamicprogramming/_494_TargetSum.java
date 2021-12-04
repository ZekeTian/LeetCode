package dynamicprogramming;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/target-sum/
 * 
 * 题目描述：给你一个整数数组 nums 和一个整数 target 。向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *        例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 *        返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *        
 * 限制条件：
 *  （1）1 <= nums.length <= 20
 *  （2）0 <= nums[i] <= 1000
 *  （3）0 <= sum(nums[i]) <= 1000
 *  （4）-1000 <= target <= 1000
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [1,1,1,1,1], target = 3
 *      输出：5
 *      解释：一共有 5 种方法让最终目标和为 3 。
 *      -1 + 1 + 1 + 1 + 1 = 3
 *      +1 - 1 + 1 + 1 + 1 = 3
 *      +1 + 1 - 1 + 1 + 1 = 3
 *      +1 + 1 + 1 - 1 + 1 = 3
 *      +1 + 1 + 1 + 1 - 1 = 3
 *      
 *  示例 2
 *      输入：nums = [1], target = 1
 *      输出：1
 */
public class _494_TargetSum {

    public static void main(String[] args) {
        // test case 1, output: 5
        int[] nums = {1,1,1,1,1};
        int target = 3;
        
        // test case 2, output: 1
//        int[] nums = {1};
//        int target = 1;
        
        // test case 3, output: 256
//        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
//        int target = 1;
        
//        _494Solution1 solution = new _494Solution1();

        _494Solution2 solution = new _494Solution2();
        
        System.out.println(solution.findTargetSumWays(nums, target));
    }
}

/**
 * 解法一：递归 + 记忆化
 */
class _494Solution1 {
    
    private ArrayList<HashMap<Integer, Integer>> memo = null;
    private int[] nums = null;
    
    // 返回数组 nums[0...index] 组成和为 target 的组合个数
    private int tryFindTargetSumWays(int index, int target) {
        if (index < 0) { // index = 0 时结束递归，但是要对 nums[0] = target = 0 这种情况进行特殊处理，因为 +0、-0 在此题中是不同的
            return (0 == target ? 1 : 0);
        }
        
        if ( -1 != memo.get(index).getOrDefault(target, -1)) {
            return memo.get(index).get(target);
        }
        
        // 尝试 nums[index] 为正负数时的两种情况
        int res = tryFindTargetSumWays(index - 1, target + nums[index])
                + tryFindTargetSumWays(index - 1, target - nums[index]);
        
        memo.get(index).put(target, res);
        
        return res;
    }
    
    public int findTargetSumWays(int[] nums, int target) {
        this.memo = new ArrayList<HashMap<Integer, Integer>>();
        for (int i = 0; i < nums.length; ++i) {
            memo.add(new HashMap<Integer, Integer>());
        }
        this.nums = nums;
        
        return tryFindTargetSumWays(nums.length - 1, target);
    }
}

/**
 * 解法二：自底向上动态规划
 */
class _494Solution2 {
    
    public int findTargetSumWays(int[] nums, int target) {
        // 计算出 nums 数组的元素之和
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (Math.abs(target) > sum) {
            return 0;
        }
        
        int[][] memo = new int[nums.length][2 * sum + 1]; // memo[i][j] 表示 nums[0...i] 数组组成和为 j 的组合个数
        // 初始化。因为 nums[0] 有正负两种情况，所以初始化时有两种选择
        memo[0][nums[0] + sum] = 1; // nums[0] 选择负
        memo[0][-nums[0] + sum] = 1; // nums[0] 选择正
        if (0 == nums[0]) { // 因为 nums[0] = 0 时， +0、-0 无法区分，所以单独处理
            memo[0][sum] = 2; // 当 nums[0] = 0 时，+0 和 -0 是两种不同的情况，因此 memo[0][sum] 设置为 2
        }
        
        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j <= 2 * sum; ++j) {
                int res = 0;
                if (j - nums[i] >= 0) {
                    res += memo[i - 1][j - nums[i]]; // nums[i] 选择正时
                }
                if (j + nums[i] <= 2 * sum) {
                    res += memo[i - 1][j + nums[i]]; // nums[i] 选择负时
                }
                memo[i][j] = res;
            }
        }

        return memo[nums.length - 1][target + sum];
    }
}
