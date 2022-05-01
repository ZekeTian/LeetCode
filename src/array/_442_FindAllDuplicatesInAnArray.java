package array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * 
 * 题目描述：给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。
 *         请你找出所有出现 两次 的整数，并以数组形式返回。
 *         你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 *         
 * 限制条件：
 *  （1）n == nums.length
 *  （2）1 <= n <= 10^5
 *  （3）1 <= nums[i] <= n
 *  （4）nums 中的每个元素出现 一次 或 两次
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [4,3,2,7,8,2,3,1]
 *      输出：[2,3]
 *      
 *  示例 2
 *      输入：nums = [1,1,2]
 *      输出：[1]
 *  
 *  示例 3
 *      输入：nums = [1]
 *      输出：[]
 *
 */
public class _442_FindAllDuplicatesInAnArray {

    public static void main(String[] args) {
        // test case1, output: [2,3]
        int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
        
        // test case2, output: [1]
//        int[] nums = { 1, 1, 2 };
        
        // test case3, output: []
//        int[] nums = { 1 };
        
        
//        _442Solution1 solution = new _442Solution1();
        
        _442Solution2 solution = new _442Solution2();
        
        
        
        System.out.println(solution.findDuplicates(nums));
    }
}

/**
 * 解法一：使用一个长度为 n+1 的辅助 boolean 数组
 */
class _442Solution1 {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        
        boolean[] flags = new boolean[nums.length + 1];
        for (int i : nums) {
            if (flags[i]) {
                list.add(i);
            } else {
                flags[i] = true;
            }
        }
        
        return list;
    }
}


/**
 * 解法二：使用 Set 
 */
class _442Solution2 {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return list;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                list.add(i);
            } else {
                set.add(i);
            }
        }
        
        return list;
    }
    
}