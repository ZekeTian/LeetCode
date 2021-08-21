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
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 0;

        // test case2, output: -1
        //        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        //        int target = 3;

        //        _33Solution1 solution = new _33Solution1();

        _33Solution2 solution = new _33Solution2();

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

/**
 * 解法二：二分搜索
 */
class _33Solution2 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int mid = 0;

        if (0 == len) {
            return -1;
        }

        while (left <= right) {
            mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判断 mid 左右两边哪边有序 
            if (nums[0] <= nums[mid]) {
                // 左边有序，即 [0, mid] 之间有序
                if (target >= nums[0] && target < nums[mid]) { //判断 target 是否在有序区间 [0, mid] 内
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右边有序，即 [mid, len - 1] 之间有序
                if (target > nums[mid] && target <= nums[len - 1]) { //判断 target 是否在有序区间 [mid, len - 1] 内
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
