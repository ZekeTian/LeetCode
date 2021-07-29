package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * 
 * 题目描述：给定一个整数数组 nums，返回这个数组中最长的严格递增子序列的长度（严格是指后面的要大于前面的，不能等于）。
 *        一个子序列可以通过删除原数组中的一些元素（或不删除元素）并且不改变元素顺序得到。如：[3,6,2,7] 是 [0,3,1,6,2,2,7] 的一个子序列。
 *        
 * 条件限制；
 *      （1）1 <= nums.length <= 2500
 *      （2）-10^4 <= nums[i] <= 10^4
 *      
 * 示例：
 *      Input: nums = [10,9,2,5,3,7,101,18]
 *      Output: 4
 *      解释：最长的子序列是 [2,3,7,101]
 */
public class _300_LongestIncreasingSubsequence {

    public static void main(String[] args) {
        // test case1, output: 4
        int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };

        // test case2, output: 4
        //        int[] nums = { 0, 1, 0, 3, 2, 3 };

        // test case3, output: 1
        //        int[] nums = { 7, 7, 7, 7, 7, 7, 7 };

        //        _300Solution1 solution = new _300Solution1();

        _300Solution2 solution = new _300Solution2();

        //        _300Solution3 solution = new _300Solution3();

        System.out.println(solution.lengthOfLIS(nums));
    }
}

/**
 * 解法一：仅使用 递归 的方式解决（递归采用回溯的写法）
 */
class _300Solution1 {

    private int[] nums;
    private List<Integer> lcs;

    // nums[cur] 是当前正在处理的元素
    private int expand(int cur) {
        if (cur == nums.length) {
            // System.out.println(lcs);
            return lcs.size();
        }

        // 不放 nums[cur]
        int res = expand(cur + 1);

        // 放 nums[cur]
        if ((lcs.size() == 0) /* 初始时，子序列为空，直接放 nums[cur] */
                || (lcs.size() > 0 && nums[cur] > lcs.get(lcs.size() - 1)) /* 子序列不为空，则判断是否能构成升序 */ ) {

            lcs.add(nums[cur]); // 回溯，递归向下走，将 nums[cur] 放进 lcs 中
            res = Math.max(res, expand(cur + 1));
            lcs.remove(lcs.size() - 1); // 回溯，递归往回走，将 nums[cur] 从 lcs 中去掉
        }

        return res;
    }

    public int lengthOfLIS(int[] nums) {
        this.nums = nums;
        lcs = new ArrayList<Integer>();

        return expand(0);
    }
}

/**
 * 解法二：使用 递归 + 记忆化搜索 的方式解决
 */
class _300Solution2 {

    public int lengthOfLIS(int[] nums) {

        return 0;
    }
}

/**
 * 解法三：使用 动态规划 的方式解决
 */
class _300Solution3 {

    // memo[i] 表示以 i 号元素结尾的序列的长度
    private int[] memo;

    public int lengthOfLIS(int[] nums) {

        memo = new int[nums.length];
        Arrays.fill(memo, 1); // 初始时，所有元素可以自己单独成为一个序列，所以序列长度默认为 1

        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    memo[i] = Math.max(memo[j] + 1, memo[i]);
                }
            }
        }

        int res = -1;
        for (int i : memo) {
            res = Math.max(res, i);
        }

        return res;
    }
}
