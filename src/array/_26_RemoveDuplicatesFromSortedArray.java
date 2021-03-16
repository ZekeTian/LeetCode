package array;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * 
 * 题目描述：给定一个排好序的数组 nums，在原数组中同删除重复元素，从而使得数组中每个元素只出现一次，并且返回数组新的长度
 * 		     不要创建一个新的数组，必须要在原始输入的数组上进行修改，空间复杂度应该为 O(1)。另外，不需要考虑新数组长度之后的元素。
 */
public class _26_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        _26Solution solution = new _26Solution();
        int[] nums = { 1, 1, 2, 2 }; // {1, 1, 2, 2, 3}; // {0,0,1,1,1,2,2,3,3,4}; 
        int len = solution.removeDuplicates(nums);

        for (int i = 0; i < len; ++i) {
            System.out.println(nums[i]);
        }
    }

}

class _26Solution {
    public int removeDuplicates(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }

        int k = 1; // nums 中的 [0,k) 之间无重复元素

        for (int i = k; i < nums.length; ++i) {
            // 判断 nums[i] 是否可以放进去（即是否可以放在 nums[k] 处）
            // 当 nums[i] 与 nums 中 [0,k) 之间的元素全都不相同时，才能将 nums[i] 放进去
            // 因为数组有序（题目给定的是升序），则当 nums[i] > nums[k-1] 时，即可保证 nums[i] 大于 nums 中 [0,k) 之间所有的元素（即保证 nums[i] 不会与前面的重复）
            if (nums[i] > nums[k - 1]) {
                nums[k] = nums[i];
                ++k;
            }
        }

        return k;
    }
}