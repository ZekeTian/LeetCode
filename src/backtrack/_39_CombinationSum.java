package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 * 
 * 题目描述：给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的所有不同组合，
 *        并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *        candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 *        对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 
 * 限制条件：
 *  （1）1 <= candidates.length <= 30
 *  （2）1 <= candidates[i] <= 200
 *  （3）candidate 中的每个元素都 互不相同
 *  （4）1 <= target <= 500
 * 
 * 示例：
 *  示例 1：
 *      输入：candidates = [2,3,6,7], target = 7
 *      输出：[[2,2,3],[7]]
 *      解释：
 *          2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 *          7 也是一个候选， 7 = 7 。仅有这两种组合。
 *          
 *  示例 2
 *      输入: candidates = [2,3,5], target = 8
 *      输出: [[2,2,2,2],[2,3,3],[3,5]]
 *      
 *  示例 3
 *      输入: candidates = [2], target = 1
 *      输出: []
 *      
 *  示例 4
 *      输入: candidates = [1], target = 1
 *      输出: [[1]]
 *      
 *  示例 5
 *      输入：输入: candidates = [1], target = 2
 *      输出: [[1,1]]
 *
 */
public class _39_CombinationSum {

    public static void main(String[] args) {
        // test case1, output: [[2, 2, 3], [7]]
//        int[] candidates = { 2, 3, 6, 7 };
//        int target = 7;
        
        // test case2, output: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
//        int[] candidates = { 2, 3, 5 };
//        int target = 8;
        
        // test case3, output: []
        int[] candidates = { 2 };
        int target = 1;
        
        _39Solution solution = new _39Solution();
        
        System.out.println(solution.combinationSum(candidates, target));
    }
}

/**
 * 回溯法，需要注意的是：为了能够重复选择某个元素，同时也为了避免最终结果有重复列表，在传入 index 时需要进行特殊处理。
 *
 */
class _39Solution {
    
    private List<List<Integer>> resultList = null;
    private int[] candidates = null;
    
    private void getCombinationSum(int sum, int index, List<Integer> list) {
        if (0 == sum) {
            resultList.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = index; i < candidates.length; ++i) { // 从 index 处开始选择，避免从头选择产生重复
            if (sum >= candidates[i]) {
                list.add(candidates[i]);
                getCombinationSum(sum - candidates[i], i, list); // 因为一个元素可以重复选择，所以下次依然从 candidates[i] 处开始选择
                list.remove(list.size() - 1);
            }
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        resultList = new ArrayList<>();
        this.candidates = candidates;
        
        getCombinationSum(target, 0, new ArrayList<>());
        
        return resultList;
    }
}
