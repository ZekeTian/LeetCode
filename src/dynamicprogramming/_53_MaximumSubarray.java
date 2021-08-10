package dynamicprogramming;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * 
 * 题目描述：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 条件限制：
 *  （1）1 <= nums.length <= 3 * 10^4
 *  （2）-10^5 <= nums[i] <= 10^5
 * 
 */
public class _53_MaximumSubarray {

    public static void main(String[] args) {
        // test case 1, output: 6
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

        // test case 2, output: -1
        //        int[] nums = { -1 };

        _53Solution solution = new _53Solution();

        System.out.println(solution.maxSubArray(nums));
    }
}

/**
 * 使用动态规划
 * f(i) 表示以第 i 个数结尾的子序列的和，对于第 i 个数而言，其有如下两种状态：
 *  （1）将第 i 个数放进前 i-1 个数组成的子序列中
 *  （2）第 i 个数不放进前 i-1 个数组成的子序列中，单独作为一个子序列（即第 i 个数作为新子序列的起点）
 * 因此，f(i) = max{f(i-1) + nums[i], nums[i]}
 */
class _53Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;

        for (int i : nums) {
            curSum = Math.max(curSum + i, i); // 放 nums[i] 和不放 nums[i] 两种状态进行比较，取较大值 
            maxSum = Math.max(maxSum, curSum);// 比较当前的 sum 和最大的 sum，更新最大的 sum
        }

        return maxSum;
    }
}