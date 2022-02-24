package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-closest/
 * 
 * 题目描述：给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 *        返回这三个数的和。
 *        假定每组输入只存在恰好一个解。
 *        
 * 限制条件：
 *  （1）3 <= nums.length <= 1000
 *  （2）-1000 <= nums[i] <= 1000
 *  （3）-10^4 <= target <= 10^4
 *  
 * 示例
 *  示例 1
 *      输入：nums = [-1,2,1,-4], target = 1
 *      输出：2
 *      解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *      
 *  示例 2
 *      输入：nums = [0,0,0], target = 1
 *      输出：0
 *      
 */
public class _16_3SumClosest {

    public static void main(String[] args) {
        // test case1, output: 2
        int[] nums = { -1, 2, 1, -4 };
        int target = 1;
        
        // test case2, output: 0
//        int[] nums = { 0, 0, 0 };
//        int target = 1;
        
        // test case2, output: 2
//        int[] nums = { 1, 1, 1, 0 };
//        int target = -100;
        
        _16Solution solution = new _16Solution();
        
        System.out.println(solution.threeSumClosest(nums, target));
    }
}

/**
 * 思路：同第 15 题的 3Sum，双指针思想
 */
class _16Solution {
    
    public int threeSumClosest(int[] nums, int target) {
        if (null == nums || nums.length < 3) {
            return 0;
        }
        
        int minDif = Integer.MAX_VALUE;
        int closestSum = target;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 相等，则跳过，省略重复的数
            }
            
            int left = i + 1, right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    while (left < right && nums[left] == nums[left + 1]) {
                        ++left; // 省略重复的数
                    }
                    ++left;
                } else {
                    while (left < right && nums[right] == nums[right - 1]) {
                        --right; // 省略重复的数
                    }
                    --right;
                }
                
                if (minDif > Math.abs(sum - target)) {
                    minDif = Math.abs(sum - target);
                    closestSum = sum;
                }
            }
        }
        
        return closestSum;
    }
}
