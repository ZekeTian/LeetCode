package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * 
 * 题目描述：给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * 
 * 进阶：请你设计时间复杂度为 O(n) 的算法解决本问题
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 10^4
 *  （2）-10^4 <= nums[i] <= 10^4
 *  （3）nums 已按 非递减顺序 排序
 *
 * 示例：
 *  示例 1
 *      输入：nums = [-4,-1,0,3,10]
 *      输出：[0,1,9,16,100]
 *      解释：平方后，数组变为 [16,1,0,9,100]
 *           排序后，数组变为 [0,1,9,16,100]
 *  
 *  示例 2
 *      输入：nums = [-7,-3,2,3,11]
 *      输出：[4,9,9,49,121]
 *      
 */
public class _977_SquaresOfASortedArray {

    public static void main(String[] args) {
        // test case1, output: [0,1,9,16,100]
//        int[] nums = { -4, -1, 0, 3, 10 };
        
        // test case2, output: [4,9,9,49,121]
        int[] nums = { -7, -3, 2, 3, 11 };
        
        
//        _977Solution1 solution = new _977Solution1();
        
        _977Solution2 solution = new _977Solution2();
        
        System.out.println(Arrays.toString(solution.sortedSquares(nums)));
    }
    
}


/**
 * 解法一：排序
 */
class _977Solution1 {
    
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        
        for (int i = 0; i < nums.length; ++i) {
            res[i] = nums[i] * nums[i];
        }
        
        Arrays.sort(res);
        
        return res;
    }
    
}


/**
 * 解法二：双指针
 *       因为原始数组 nums 是有序的（非递减），如果 nums 中全部为正数的话，那么最大值一定在最右边。
 *       但是，实际上 nums 中有负数，所以最大值会在最左边或最右边。因此，可以使用双指针从两边向中间
 *       逼近。具体思路如下：
 *       （1）创建一个和 nums 一样长的数组 res，用于存储最终的结果
 *       （2）创建 i、j 两个指针，i 从 nums[0] 向右遍历，j 从 nums[nums.length - 1] 向左遍历
 *           创建指针 k，用于遍历 res，存储结果
 *       （3）返回 res
 */
class _977Solution2 {
    
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        
        for (int i = 0, j = nums.length - 1, k = res.length - 1; i <= j; --k) {
            int left = nums[i] * nums[i]; // 左指针对应数字的平方值
            int right = nums[j] * nums[j]; // 右指针对应数字的平方值
            if (left < right) { // 右边的值更大，则将 right 放进 res 中，并且右指针左移
                res[k] = right;
                --j;
            } else { // 左边的值更大，则将 left 放进 res 中，并且左指针右移
                res[k] = left;
                ++i;
            }
        }
        
        return res;
    }
    
}
