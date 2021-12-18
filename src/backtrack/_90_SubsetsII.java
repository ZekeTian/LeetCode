package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/
 *
 * 题目描述：给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *        解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *        
 * 限制条件：
 *  （1）1 <= nums.length <= 10
 *  （2）-10 <= nums[i] <= 10
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [1,2,2]
 *      输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *      
 *  示例 2
 *      输入：nums = [0]
 *      输出：[[],[0]]
 *  
 */
public class _90_SubsetsII {

    public static void main(String[] args) {
        // test case1, output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
//        int[] nums = { 1, 2, 2 };
        
        // test case2, output: [[], [0]]
        int[] nums = { 0 };
        
        _90Solution solution = new _90Solution();
        
        System.out.println(solution.subsetsWithDup(nums));
    }
    
}


class _90Solution {
    
    private List<List<Integer>> resultList = null;
    private int[] nums = null;
    private boolean[] flags = null;
    
    private void getSubsets(int n, int index, List<Integer> list) {
        if (n == list.size()) {
            resultList.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = index; i < nums.length; ++i) {
            if (i > 0 && nums[i - 1] == nums[i] && !flags[i - 1]) { // 因为 nums 中有重复元素，需要进行去重
                continue;
            }
            
            if (!flags[i]) {
                flags[i] = true;
                list.add(nums[i]);
                getSubsets(n, i + 1, list);
                list.remove(list.size() - 1);
                flags[i] = false;
            }
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.resultList = new ArrayList<>();
        this.nums = nums;
        this.flags = new boolean[nums.length];
        
        for (int i = 0; i <= nums.length; ++i) {
            Arrays.fill(flags, false);
            getSubsets(i, 0, new ArrayList<>());
        }
        
        return resultList;
    }
}
