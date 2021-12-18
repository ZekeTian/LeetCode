package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * 
 * 题目描述：给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *        解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *        
 * 限制条件：
 *  （1）1 <= nums.length <= 10
 *  （2）-10 <= nums[i] <= 10
 *  （3）nums 中的所有元素 互不相同
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [1,2,3]
 *      输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *      
 *  示例 2
 *      输入：nums = [0]
 *      输出：[[],[0]]
 *
 */
public class _78_Subsets {

    public static void main(String[] args) {
        // test case1, output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//        int[] nums = { 1, 2, 3 };
        
        // test case2, output: [[],[0]]
        int[] nums = { 0 };
        
        _78Solution solution = new _78Solution();
        
        
        System.out.println(solution.subsets(nums));
    }
}


class _78Solution {
    
    private List<List<Integer>> resultList = null;
    private int[] nums = null;
    
    // 从 nums[index...len-1] 中选择长度为 n 的子集，然后放进 list 中
    private void getSubsets(int n, int index, List<Integer> list) {
        if (n == list.size()) {
            resultList.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = index; i < nums.length; ++i) {
            list.add(nums[i]);
            getSubsets(n, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        this.resultList = new ArrayList<>();
        this.nums = nums;
        
        for (int i = 0; i <= nums.length; ++i) {
            getSubsets(i, 0, new ArrayList<Integer>());
        }

        return resultList;
    }
}

