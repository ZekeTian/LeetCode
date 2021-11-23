package array;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * 题目描述：
 * 
 * 
 * 条件限制：
 *  （1）nums1.length == m, nums2.length == n
 *  （2）0 <= m <= 1000, 0 <= n <= 1000
 *  （3）1 <= m + n <= 2000
 *  （4）-10^6 <= nums1[i], nums2[i] <= 10^6
 * 
 * 示例：
 *  示例 1
 *      输入：nums1 = [1,3], nums2 = [2]
 *      输出：2.00000
 *      解释：合并数组 = [1,2,3] ，中位数 2
 *  
 *  示例 2
 *      输入：nums1 = [1,2], nums2 = [3,4]
 *      输出：2.50000
 *      解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *  
 */
public final class _4_MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        // test case 1, output: 2.00000
//        int[] nums1 = {1, 3}, nums2 = {2};
        
        // test case 2, output: 2.50000
        int[] nums1 = {1, 2}, nums2 = {3, 4};

        _4Solution1 solution = new _4Solution1();
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
        
    }
}

/**
 * 解法一：暴力法。
 *      先将两个有序数组合并成一个有序数组 nums，然后再根据数组 nums 长度的奇偶性取中位数。
 */
class _4Solution1 {
    
    // 将 nums1，nums2 数组按照升序合并到 nums 数组中
    private void merge(int[] nums1, int i, int[] nums2, int j, int[] nums, int k) {
        if (i < nums1.length && j >= nums2.length) {
            nums[k] = nums1[i];
            merge(nums1, i + 1, nums2, j, nums, k + 1);
            return;
        }
        
        if (i >= nums1.length && j < nums2.length) {
            nums[k] = nums2[j];
            merge(nums1, i, nums2, j + 1, nums, k + 1);
            return;
        }
        
        if (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                nums[k] = nums1[i];
                merge(nums1, i + 1, nums2, j, nums, k + 1);
            } else {
                nums[k] = nums2[j];
                merge(nums1, i, nums2, j + 1, nums, k + 1);
            }
            return;
        }
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        if (0 == nums.length) {
            return 0.0;
        }
        
        merge(nums1, 0, nums2, 0, nums, 0);
        int mid = nums.length / 2;
        if (0 == nums.length % 2) {
            return (nums[mid - 1] + nums[mid]) / 2.0;
        } else {
            return nums[mid];
        }
    }
}

