package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * 
 * 题目描述：给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *         请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *         
 * 限制条件：
 *  （1）0 <= nums.length <= 10^5
 *  （2）-10^9 <= nums[i] <= 10^9
 *  
 * 示例：
 *  示例 1
 *      输入：nums = [100,4,200,1,3,2]
 *      输出：4
 *      
 *  示例 2
 *      输入：nums = [0,3,7,2,5,8,4,6,0,1]
 *      输出：9
 *
 */
public class _128_LongestConsecutiveSequence {

    public static void main(String[] args) {
        // test case1, outptu: 4
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        
        // test case2, outptu: 9
//        int[] nums = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        
        // test case3, outptu: 3
//        int[] nums = { 1, 2, 1, 0 };
        
        // test case4, outptu: 1
//        int[] nums = { 1 };
        
        // test case5, outptu: 0
//        int[] nums = {};
        
//        _128Solution1 solution = new _128Solution1();
        
        _128Solution2 solution = new _128Solution2();
        
        
        System.out.println(solution.longestConsecutive(nums));
        
    }
}

/**
 * 解法一：使用排序
 */
class _128Solution1 {
    
    public int longestConsecutive(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        int maxLen = 1, len = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] - nums[i - 1] == 1) {
                ++len;
            } else if (nums[i] - nums[i - 1] > 1) {
                len = 1; // 不连续，此时 nums[i] 是新子串的第一个元素
            }
            
            maxLen = Math.max(maxLen, len);
        }
        
        return maxLen;
    }
    
}

/**
 * 解法二：使用 Set （实现方式一，额外使用一个集合记录已经访问过的元素）
 *       （1）先使用集合 set 存储 nums 中的数字
 *       （2）遍历 nums ，然后从当前数字 i 向两边扩展，直到 set 中不存在，从而可以找到连续的子串
 */
class _128Solution2 {
    
    public int longestConsecutive(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>(); // 存储 nums 中的元素
        for (int i : nums) {
            set.add(i);
        }
        
        Set<Integer> visited = new HashSet<>(); // 记录已经访问过的元素，避免重复访问
        int maxLen = 0;
        for (int i : nums) {
            if (!visited.contains(i)) {
                // 从 i 向两边扩展
                int len = 1;
                // 从 i 出发，向左边寻找，直到 set 中不包含
                int n = i - 1;
                while (!visited.contains(n) && set.contains(n)) {
                    ++len;
                    visited.add(n);
                    --n;
                }
                
                // 从 i 出发，向右边寻找，直到 set 中不包含
                n = i + 1;
                while (!visited.contains(n) && set.contains(n)) {
                    ++len;
                    visited.add(n);
                    ++n;
                }
                
                maxLen = Math.max(maxLen, len);
            }
        }
        
        return maxLen;
    }
}

