package array;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * 
 * 题目描述：整数数组 nums 按升序排列，数组中的值互不相同 。在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了旋转，
 *        使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 *        例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 *        给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 
 * 
 *
 */
public class _33_SearchInRotatedSortedArray {

    public static void main(String[] args) {
        // test case1, output: 4
        //        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        //        int target = 0;

        // test case2, output: -1
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 3;

        _33Solution1 solution = new _33Solution1();

        int idx = solution.search(nums, target);

        System.out.println(idx);
    }
}

/**
 * 解法一：直接遍历
 */
class _33Solution1 {
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                return i;
            }
        }

        return -1;
    }
}