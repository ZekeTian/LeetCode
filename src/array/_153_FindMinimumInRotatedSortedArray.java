package array;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * 
 * 题目描述：已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 *        若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 *        若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 *        注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *        给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *        你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *        
 * 限制条件：
 *  （1）n == nums.length
 *  （2）1 <= n <= 5000
 *  （3）-5000 <= nums[i] <= 5000
 *  （4）nums 中的所有整数 互不相同
 *  （5）nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *  
 * 示例
 *  示例 1
 *      输入：nums = [3,4,5,1,2]
 *      输出：1
 *      解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *  
 *  示例 2
 *      输入：nums = [4,5,6,7,0,1,2]
 *      输出：0
 *      解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 *
 */
public class _153_FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        // test case1, output: 1
//        int[] nums = { 3, 4, 5, 1, 2 }; 
        
        // test case2, output: 0
//        int[] nums = { 4, 5, 6, 7, 0, 1, 2 }; 
        
        // test case3, output: 1
//        int[] nums = { 3, 1, 2 };
        
        // test case4, output: 1
        int[] nums = { 1 };
        
        _153Solution solution = new _153Solution();
        
        
        System.out.println(solution.findMin(nums));
    }
}

/**
 * 解题思路类似于第 33 题
 */
class _153Solution {
    
    public int findMin(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[mid] && nums[right] >= nums[mid]) { // [left...right] 完全有序，则 nums[left] 是最小的数字
                return nums[left];
            } else if (nums[right] < nums[mid]) { // [left...mid] 有序，[mid...right] 无序，最小值在 (mid...right] 之间
                left = mid + 1;
            } else if (nums[left] > nums[mid]) { // [left...mid] 无序，[mid...right] 有序，最小值在 [left...mid] 之间
                right = mid;
            }
        }
        
        return nums[left];
    }
    
}