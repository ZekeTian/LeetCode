package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/combination-sum-iv/
 * 
 * 题目描述：给你一个由不同整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *        题目数据保证答案符合 32 位整数范围。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 200
 *  （2）1 <= nums[i] <= 1000
 *  （3）nums 中的所有元素 互不相同
 *  （4）1 <= target <= 1000
 * 
 * 示例：
 *   示例 1
 *      输入：nums = [1,2,3], target = 4
 *      输出：7
 *      解释：
 *      所有可能的组合为：
 *      (1, 1, 1, 1)
 *      (1, 1, 2)
 *      (1, 2, 1)
 *      (1, 3)
 *      (2, 1, 1)
 *      (2, 2)
 *      (3, 1)
 *      请注意，顺序不同的序列被视作不同的组合。
 *   
 *   示例 2
 *      输入：nums = [9], target = 3
 *      输出：0
 */
public class _377_CombinationSumIV {

    public static void main(String[] args) {
        // test case 1, output: 7
//        int[] nums = {1, 2, 3};
//        int target = 4;
        
        // test case 2, output: 0
        int[] nums = {9};
        int target = 3;
        _377Solution1 solution = new _377Solution1();
        
        System.out.println(solution.combinationSum4(nums, target));
    }
}

/**
 * 解法一：递归 + 记忆化
 */
class _377Solution1 {
    
    private int[] nums = null;
    private int[] memo = null;
    
    // 获取 target 所有可能组合的个数
    private int getCombinationSum(int target) {
        if (target < 0) {
            return 0;
        }
        if (0 == target) { // 原始 target 组合完成，获得一种组合情况
            return 1;
        }
        if (nums[0] > target) { // nums 中所有的数都比 target 大，因此无法组合得到 target
            memo[target] = 0;
            return 0;
        }
        
        // 在计算之前，判断是否已经存储过结果
        if (-1 != memo[target]) {
            return memo[target];
        }
        
        int res = 0;
        for (int n : nums) {
            res += getCombinationSum(target - n);
        }
        memo[target] = res;
        
        return res;
    }
    
    
    public int combinationSum4(int[] nums, int target) {
        this.memo = new int[target + 1];
        Arrays.fill(memo, -1);
        Arrays.sort(nums);
        this.nums = nums;
        
        return getCombinationSum(target);
    }
    
}

