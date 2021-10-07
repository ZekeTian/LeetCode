package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 
 * 题目描述：给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *        如果数组中不存在目标值 target，返回 [-1, -1]。
 * 
 * 条件限制：
 *  （1）0 <= nums.length <= 10^5
 *  （2）-10^9 <= nums[i] <= 10^9
 *  （3）nums 是一个非递减数组
 *  （4）-10^9 <= target <= 10^9
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [5,7,7,8,8,10], target = 8
 *      输出：[3,4]
 *  
 *  示例 2
 *      输入：nums = [5,7,7,8,8,10], target = 6
 *      输出：[-1,-1]
 *
 */
public class _34_FindFirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        // test case 1, output: [3, 4]
        int[] nums = { 5, 7, 7, 8, 8, 10 };
        int target = 8;

        // test case 2, output: [-1, -1]
        //        int[] nums = { 5, 7, 7, 8, 8, 10 };
        //        int target = 6;

        // test case 3, output: [-1, -1]
        //        int[] nums = { 5 };
        //        int target = 6;

        // test case 4, output: [-1, -1]
        //        int[] nums = {};
        //        int target = 6;

        //        _34Solution1 solution = new _34Solution1();
        
        _34Solution2 solution = new _34Solution2();
        int[] range = solution.searchRange(nums, target);

        System.out.println(Arrays.toString(range));

    }

}

/**
 * 解法一：利用循环实现
 */
class _34Solution1 {

    public int[] searchRange(int[] nums, int target) {
        int lower = -1;
        int upper = -1;

        for (int i = 0; i < nums.length; ++i) {
            if (target == nums[i]) {
                if (-1 == lower) {
                    lower = i; // 下边界（即左边界）只能赋值一次，通过 lowwer 的值来判断，如果是 -1 ，则没有被赋值过，说明 nums[i] 是第一个等于 target 的数 
                }

                upper = i; // 上边界（即右边界）不断赋值，最终 upper 即为最后一个等于 target 的数所在的位置
            }
        }

        return new int[] { lower, upper };
    }
}

/**
 * 解法二：利用二分搜索实现，相当于 C++ 中的 upper_bound 和 lower_bound 实现。
 */
class _34Solution2 {

    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) { // 求下边界（即左边界）时，则右指针不断向左逼近，直到左右指针碰撞时结束
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 因为是下边界（即左边界），所以检查左指针的值，即检查 nums[left] 是否等于 target，如果等于，则 target 存在于数组中，left 即为所求下标。
        // 注意，要先提前检查下标是否越界。
        int res = (left < nums.length && nums[left] == target) ? left : -1;
        return res;
    }

    private int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target >= nums[mid]) { // 因为是上边界（即右边界），则当 target = nums[mid] 时，左指针向右逼近，直到左右指针碰撞
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 因为是上边界（即右边界），所以检查右指针，即检查 nums[right] 是否等于 target，如果等于，则 target 存在于数组中，right 即为所求下标。
        // 需要注意的是：当 target 不存在于数组时， right 可能会下标越界，所以在判断相等时，先检查下标是否越界（极端情况下，例如 nums 是空数组，此时 right = -1）
        int res = (right >= 0 && nums[right] == target) ? right : -1;
        return res;
    }

    public int[] searchRange(int[] nums, int target) {
        int lower = lowerBound(nums, target);
        int upper = upperBound(nums, target);

        return new int[] { lower, upper };
    }
}
