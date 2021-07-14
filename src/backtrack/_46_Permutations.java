package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 * 
 * 题目描述：给定一个整数数组 nums，返回这些整数所有可能的组合。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 6
 *  （2）-10 <= nums[i] <= 10
 *  （3）nums 中的整数都是唯一的
 * 
 * 示例：
 *  Input: nums = [1,2,3]
 *  Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class _46_Permutations {

    public static void main(String[] args) {
        // test case 1
        //        int[] nums = { 1, 2, 3 };

        // test case 2
        //        int[] nums = { 0, 1 };

        // test case 3
        int[] nums = { 1 };

        _46Solution solution = new _46Solution();

        System.out.println(solution.permute(nums));
    }
}

class _46Solution {

    /**
     * 存储最终的结果
     */
    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();

    /**
     * 标记 nums 中的数字是否使用过，如果使用过，则为 true；否则，为 false。
     */
    private boolean used[];

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];

        permute(nums, new ArrayList<Integer>());

        return resultList;
    }

    /**
     * 以递归的方式组合所有的可能
     * @param nums      所有可能的数字
     * @param result    组合得到的一个结果
     */
    private void permute(int[] nums, List<Integer> result) {

        if (result.size() == nums.length) {
            resultList.add(new ArrayList<Integer>(result));
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            // 判断 nums[i] 是否使用过
            if (!used[i]) {
                // 回溯，递归向下走的阶段
                result.add(nums[i]); // nums[i] 没有使用过，则将其加入到 result，并将其标记成使用过
                used[i] = true;

                permute(nums, result);

                // 回溯，递归向上走（往回走）的阶段，将状态恢复到递归之前
                result.remove(result.size() - 1);
                used[i] = false;
            }
        }
    }
}
