package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/4sum/
 * 
 * 题目描述：给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 *        请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 *        （1）0 <= a, b, c, d < n
 *        （2）a、b、c 和 d 互不相同
 *        （3）nums[a] + nums[b] + nums[c] + nums[d] == target
 *        
 * 限制条件：
 *  （1）1 <= nums.length <= 200
 *  （2）-10^9 <= nums[i] <= 10^9
 *  （3）-10^9 <= target <= 10^9
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [1,0,-1,0,-2,2], target = 0
 *      输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *  
 *  示例 2
 *      输入：nums = [2,2,2,2,2], target = 8
 *      输出：[[2,2,2,2]]
 *
 */
public class _18_4Sum {

    public static void main(String[] args) {
        // test case1, output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//        int[] nums = { 1, 0, -1, 0, -2, 2 }; 
//        int target = 0;
        
        // test case2, output: [[2,2,2,2]]
//        int[] nums = { 2, 2, 2, 2, 2 }; 
//        int target = 8;
        
        // test case3, output: [[0,0,0,1000000000]]
        int[] nums = { 0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000 }; 
        int target = 1000000000;
        
        
        _18Solution solution = new _18Solution();
        
        
        System.out.println(solution.fourSum(nums, target));
    }
}

/**
 * 思路同第 15 题的 3sum 一样，同样是固定几个数，然后剩余两个数，使用双指针寻找
 */
class _18Solution {
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (null == nums || nums.length < 4) {
            return resultList;
        }
        
        // 对 nums 排序，方便使用双指针
        Arrays.sort(nums);
        
        // 分别使用 i、j 固定两个数，然后剩余两个数的和使用双指针求解
        for (int i = 0; i < nums.length - 3; ++i) { // nums[i] 固定第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 当前数与前一个数相同，则继续下一个数，避免产生重复结果
            }
            
            // 固定 nums[i] 时，可得到的四个数之和的最小值
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break; // 当前的最小值比 target 还大，则后面的四数之和一定比 target 还大，因此固定 nums[i] 无解，直接结束
            }
            
            // 固定 nums[i] 时，可得到的四个数之和的最大值
            if ((long) nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
                continue; // 当前最大值比 target 还小，则固定 nums[i] 无解，直接下一次循环
            }
            
            for (int j = i + 1; j < nums.length - 2; ++j) { // 用 nums[j] 固定第二个数
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 当前数与前一个数相同，则继续下一个数，避免产生重复结果
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break; // 最小值比 target 大
                }
                if ((long) nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
                    continue; // 最大值比 target 大
                }
                
                // 使用双指针寻找剩余的两个数
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        resultList.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            ++left; // 如果 nums[left] == nums[left + 1]，则继续向右移动，直到遇到一个不相等的数，避免产生重复结果
                        }
                        ++left;
                        while (right > left && nums[right] == nums[right - 1]) {
                            --right; // 遇到相同的数，则继续向左移动，避免产生重复结果
                        }
                        --right;
                    } else if (sum < target) {
                        ++left;
                    } else {
                        --right;
                    }
                }
            }
        }
        
        return resultList;
    }
}


