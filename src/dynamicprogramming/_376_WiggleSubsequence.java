package dynamicprogramming;

/**
 * https://leetcode.com/problems/wiggle-subsequence/
 * 
 * 题目描述：如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。
 *        仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 *        例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 *        相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *        子序列可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 *        给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度
 *        
 * 限制条件：
 *  （1）1 <= nums.length <= 1000
 *  （2）0 <= nums[i] <= 1000
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [1,7,4,9,2,5]
 *      输出：6
 *      解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
 *  
 *  示例 2
 *      输入：nums = [1,17,5,10,13,15,10,5,16,8]
 *      输出：7
 *      解释：这个序列包含几个长度为 7 摆动序列。其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
 * 
 */
public class _376_WiggleSubsequence {
    
    public static void main(String[] args) {
        // test case 1, output: 6
//        int[] nums = {1, 7, 4, 9, 2, 5};
        
        // test case 2, output: 7
        int[] nums = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        
//        _376Solution1 solution = new _376Solution1();
        
        _376Solution2 solution = new _376Solution2();
        
        
        System.out.println(solution.wiggleMaxLength(nums));
    }
}

/**
 * 解法一：自底向上动态规划
 */
class _376Solution1 {
    public int wiggleMaxLength(int[] nums) {
        int[] up = new int[nums.length]; // 表示最后一个元素是上升摆动序列
        int[] down = new int[nums.length]; // 表示最后一个元素是下降摆动序列
        up[0] = 1;
        down[0] = 1;
        
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1); // 上升，则更新 up 数组。更新数组 up 有两种方式，一种是保持不变，另一种是 nums[i] 接在下降摆动序列
                down[i] = down[i - 1]; // 下降摆动序列保持不变
            } else if (nums[i] < nums[i - 1]) {
                down[i] = Math.max(down[i - 1], up[i - 1] + 1);
                up[i] = up[i - 1];
            } else { // 前后相等，则 up、down 数组维持不变
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        
        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }
}

/**
 * 解法二：贪心算法
 */
class _376Solution2 {
    
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        
        int preDiff = nums[1] - nums[0];
        int res = (preDiff == 0 ? 1 : 2); // 如果前两个数是相等的，则只算一个
        
        for (int i = 2; i < nums.length; ++i) {
            int curDiff = nums[i] - nums[i - 1];
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) { // 峰型，满足摆动要求
                preDiff = curDiff;
                ++res;
            }
        }
        
        return res;
    }
}
