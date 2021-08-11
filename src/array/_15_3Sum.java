package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
 * 
 * 题目描述：给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0？请你找出所有和为 0 且不重复的三元组。
 *        注意：答案中不可以包含重复的三元组
 *        
 * 限制条件：
 *  （1）0 <= nums.length <= 3000
 *  （2）-10^5 <= nums[i] <= 10^5
 *  
 * 示例：
 *  Input: nums = [-1,0,1,2,-1,-4]
 *  Output: [[-1,-1,2],[-1,0,1]]
 *  
 */
public class _15_3Sum {

    public static void main(String[] args) {
        // test case 1, output: [[-1,-1,2],[-1,0,1]]
        int[] nums = { -1, 0, 1, 2, -1, -4 };

        // test case 2, output: []
        //        int[] nums = {};

        _15Solution solution = new _15Solution();

        System.out.println(solution.threeSum(nums));
    }
}

/**
 * 先排序，固定一个数，然后在一个区间内使用双指针的方法寻找剩余的两个数 
 */
class _15Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums); // 升序排序，便于后面去重和提前结束

        for (int i = 0; i < nums.length; ++i) {
            // 将 nums[i] 作为 a，然后在 nums[i, len-1] 之间寻找 b、c 使得 a + b + c = 0
            if (nums[i] > 0) { // 如果 nums[i] > 0，则后面的数相加必定 > 0，不可能 = 0（因为，此时 nums 已经经过升序排序了）
                return resultList;
            }

            if (i > 0 && nums[i] == nums[i - 1]) { // 如果 nums[i] 与前面一个相同，则继续找一下，避免产生重复结果
                continue;
            }

            // 在 nums[i, len-1] 之间寻找 b、c 使得 a + b + c = 0，使用对撞指针方法
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (0 == sum) {
                    // 当 sum = 0，得到一个新结果
                    List<Integer> result = new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[left]);
                    result.add(nums[right]);
                    resultList.add(result);

                    // 避免下次产生重复的结果，left 指针需要移动到一个不等于当前值的位置上（即移到一个新值上）
                    while (left < right 
                            && nums[left + 1] == nums[left] /* 下一个值与当前值相等，则继续移动 left */) {
                        ++left;
                    }
                    left -= 1; // 将 left 移动到新值的位置上

                    // 和 left 一样，right 也要移动到一个新值上
                    while (left < right && nums[right - 1] == nums[right]) {
                        --right;
                    }
                    right -= 1;
                }

                if (sum > 0) { // sum 过大，right 向前移动，从而调小整体的值 
                    --right;
                }

                if (sum < 0) { // sum 过小，left 向后移动，从而调大整体的值
                    ++left;
                }
            }
        }

        return resultList;
    }
}
