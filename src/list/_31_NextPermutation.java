package list;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/next-permutation/
 * 
 * 题目描述：实现获取下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 *        如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。必须原地修改，只允许使用额外常数空间。
 *        
 * 条件限制：
 *  （1）1 <= nums.length <= 100
 *  （2）0 <= nums[i] <= 100
 *  
 * 示例：
 *      示例 1
 *      Input：nums = [1,2,3]
 *      Output: [1,3,2]
 *      
 *      示例 2
 *      Input：nums = [3,2,1]
 *      Output: [1,2,3]
 */
public class _31_NextPermutation {

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1};
        
        _31Solution solution = new _31Solution();
        
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}


class _31Solution {
    public void nextPermutation(int[] nums) {
        int cur = nums.length - 2;
        // 先用 cur 找到第一个小于后面数的数
        while (cur >= 0 && nums[cur] >= nums[cur + 1]) {
            --cur;
        }

        if (cur < 0) { // nums 降序排列，此时 nums 表示的数是最大值（降序），下一个排列是最小值（升序），将其反转一下即可，在这里直接调用 sort ，将数组进行升序排序，从而实现反转效果。
            Arrays.sort(nums);
            return;
        }

        // 再从后面向前遍历，找到第一个大于 cur 的数，然后交换两个数
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] > nums[cur]) {
                int tmp = nums[i];
                nums[i] = nums[cur];
                nums[cur] = tmp;

                break;
            }
        }

        // 对 cur 后面部分的数进行排序
        Arrays.sort(nums, cur + 1, nums.length); // 反转 cur 后面部分的数字。因为 [cur+1, nums.len-1] 是降序，所以反转的话，改成升序即可。 
    }
}
