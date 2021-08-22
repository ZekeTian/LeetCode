package array;

/**
 * https://leetcode.com/problems/binary-search/
 * 
 * 题目描述：给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 
 * 条件限制：
 *  （1）可以假设 nums 中的所有元素是不重复的
 *  （2）n 将在 [1, 10000]之间
 *  （3）nums 的每个元素都将在 [-9999, 9999]之间
 */
public class _704_BinarySearch {

    public static void main(String[] args) {
        // test case1, output: 4
//        int[] nums = { -1, 0, 3, 5, 9, 12 };
//        int target = 9;
        
        // test case2, output: -1
        int[] nums = { -1, 0, 3, 5, 9, 12 };
        int target = 2;
        
        _704Solution solution = new _704Solution();
        
        System.out.println(solution.search(nums, target));
    }
}

class _704Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;

            if (target == nums[mid]) {
                return mid;
            }

            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}