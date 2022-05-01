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
//        int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
        
        // test case2, output: [1]
//        int[] nums = { 1, 1, 2 };
        
        // test case3, output: []
        int[] nums = { 1 };
        
        
//        _442Solution1 solution = new _442Solution1();
        
//        _442Solution2 solution = new _442Solution2();
        
        _442Solution3 solution = new _442Solution3();
        
        
        
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


/**
 * 解法三：将 nums 中的数字视作下标，然后通过原地修改 nums 来标记是否重复，具体思路如下：
 *       遍历 nums，对于 nums 中的数字 i：
 *          如果是首次访问，则 nums[i] > 0 （因为 nums 中的数字范围是 1~n）。之后，将 nums[i] 变成相反数，即 nums[i] = -nums[i]，
 *       从而将 nums[i] 变成负数，标记数字 i 已经被访问过。
 *          如果是第二次访问，则 nums[i] < 0，这是因为在第一次访问时已经将 nums[i] 更改成相反数。能够第二次访问到 i，则说明 i 重复出现，
 *       因此需要保存 i。
 *       但是，在遍历的过程中需要注意的是：
 *          （1）因为第一次访问时会将数字变成负数，所以在后续访问 nums 中的数字时，会出现负数。为了将数字恢复成原始状态，需要对 i 取绝对值；
 *          （2）因为 nums 中的数字范围是 1~n，所以需要减 1 才能变成下标。
 */
class _442Solution3 {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (null == nums || nums.length == 0) {
            return list;
        }
        
        for (int i : nums) {
            // 将 nums 中数字 i 当成下标，需要 -1。另外，因为会将 nums 中的数字变成负数，所以还需要 abs 取绝对值，从而将 i 恢复成原始值。
            int idx = Math.abs(i) - 1; 
            
            if (nums[idx] < 0) {
                // nums[idx] < 0，说明在第一次访问 idx + 1 时，已经将 nums[idx] 更改成负数了，所以此时是第二次访问到 idx + 1
                list.add(idx + 1);
            } else {
                nums[idx] = -nums[idx]; // 第一次访问 idx + 1，则将 nums[idx] 变成负数，标记 idx + 1 已经访问过
            }
        }
        
        return list;
    }
    
}