package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/rotate-array/
 * 
 * 题目描述：给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 10^5
 *  （2）-2^31 <= nums[i] <= 2^31 - 1
 *  （3）0 <= k <= 10^5
 * 
 * 示例：
 *  示例 1
 *      输入: nums = [1,2,3,4,5,6,7], k = 3
 *      输出: [5,6,7,1,2,3,4]
 *      解释:
 *          向右轮转 1 步: [7,1,2,3,4,5,6]
 *          向右轮转 2 步: [6,7,1,2,3,4,5]
 *          向右轮转 3 步: [5,6,7,1,2,3,4]
 *          
 *  示例 2
 *      输入：nums = [-1,-100,3,99], k = 2
 *      输出：[3,99,-1,-100]
 *      解释: 
 *          向右轮转 1 步: [99,-1,-100,3]
 *          向右轮转 2 步: [3,99,-1,-100]
 * 
 */
public class _189_RotateArray {

    public static void main(String[] args) {
        // test case1, output: [5,6,7,1,2,3,4]
//        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
//        int k = 3;

        // test case1, output: [3,99,-1,-100]
        int[] nums = { -1, -100, 3, 99 };
        int k = 2;

//        _189Solution1 solution = new _189Solution1();
        
//        _189Solution2 solution = new _189Solution2();
        
        _189Solution3 solution = new _189Solution3();
        
        solution.rotate(nums, k);

        System.out.println(Arrays.toString(nums));
        
    }
}


/**
 * 解法一：暴力法，逐个移动模拟轮转过程。
 *
 */
class _189Solution1 {
    
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        
        for (int i = 0; i < k; ++i) {
            int tmp = nums[nums.length - 1]; // 记录最后一个位置
            // 向后移动，空出第一个位置
            for (int j = nums.length - 1; j > 0; --j) {
                nums[j] = nums[j - 1];
            }
            // 将原来最后一个元素移动到第一个位置
            nums[0] = tmp;
        }
    }
}

/**
 * 解法二：使用额外空间存储
 *       在解法一中，一次移动一个，会非常耗时。实际上，可以使用一个新数组存储需要转移的数字，然后统一移动。
 *       如：nums = [1,2,3,4,5,6,7], k = 3
 *       实际上相当于是将 [5,6,7] 整体移动数组的最前面，所以可以用新数组存储 [5,6,7]，
 *       然后再将原来数组中的数字向后移动 3 位，得到 [1,2,3,1,2,3,4]，
 *       最后再将 [5,6,7] 放到该数组的最前面，得到 [5,6,7,1,2,3,4]
 */
class _189Solution2 {
    
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] tmpArr = new int[k]; // 临时的数组，用于存储需要向前移动的数字
        
        // 将需要向前移动的数字存储进 tmpArr 中
        for (int i = nums.length - k, j = 0; i < nums.length; ++i, ++j) {
            tmpArr[j] = nums[i];
        }
        
        // 将 nums 中的数字向后移动
        for (int i = nums.length - k - 1; i >= 0; --i) {
            nums[i + k] = nums[i];
        }
        
        // 将 tmpArr 放进 nums 的最前面
        for (int i = 0; i < tmpArr.length; ++i) {
            nums[i] = tmpArr[i];
        }
    }
    
}

/**
 * 解法三：使用三次反转
 *        如：nums = [1,2,3,4,5,6,7], k = 3，得到 [5,6,7,1,2,3,4]。
 *        可以将 nums = [1,2,3,4,5,6,7] 分成 [1,2,3,4], [5,6,7] 两部分。
 *        先统一对 nums 进行反转，得到 [7,6,5,4,3,2,1]。其中，[1,2,3,4] 变成 [4,3,2,1]，[5,6,7] 变成 [7,6,5]。
 *        然后再对 [7,6,5] 这一部分进行反转，得到 [5,6,7,4,3,2,1]。
 *        最后对 [4,3,2,1] 这一部分进行反转，得到 [5,6,7,1,2,3,4]，即为最终结果。
 *        
 *        图示过程：
 *                          ------>-->  得到  -->------>
 *        整体反转           <--<------
 *        前面一部分反转      --><------
 *        后面一部分反转      -->------>
 */
class _189Solution3 {
    
    // 对 nums[left...right] 部分进行反转
    private void reverse(int[] nums, int left, int right) {
        int l = left, r = right;
        while (l < r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            ++l;
            --r;
        }
    }
    
    // ------>-->  得到  -->------>
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        // 整体反转，从 ------>--> 得到 <--<------
        reverse(nums, 0, nums.length - 1);
        // 反转前面一部分，从 <--<------ 得到 --><------
        reverse(nums, 0, k - 1);
        // 反转后面一部分，从 --><------ 得到 -->------>
        reverse(nums, k, nums.length - 1);
    }
}