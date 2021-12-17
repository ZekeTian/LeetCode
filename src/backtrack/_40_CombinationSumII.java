package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-ii/
 * 
 * 题目描述： 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *         candidates 中的每个数字在每个组合中只能使用一次。注意：解集不能包含重复的组合。
 *
 * 限制条件：
 *  （1）1 <= candidates.length <= 100
 *  （2）1 <= candidates[i] <= 50
 *  （3）1 <= target <= 30
 * 
 * 示例：
 *  示例 1
 *      输入: candidates = [10,1,2,7,6,1,5], target = 8
 *      输出:
 *          [
 *           [1,1,6],
 *           [1,2,5],
 *           [1,7],
 *           [2,6]
 *          ]
 *  
 *  示例 2
 *      输入: candidates = [2,5,2,1,2], target = 5
 *      输出:
 *          [
 *           [1,2,2],
 *           [5]
 *          ]
 * 
 */
public class _40_CombinationSumII {

    public static void main(String[] args) {
        // test case1, output: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
//        int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
//        int target = 8;
        
        // test case2, output: [[1, 2, 2], [5]]
        int[] candidates = { 2, 5, 2, 1, 2 };
        int target = 5;
        
        _40Solution solution = new _40Solution();
        
        System.out.println(solution.combinationSum2(candidates, target));
    }
}

/**
 * 回溯法解法，需要特别解决的地方是：
 * （1）数字不能重复使用
 *    解决方法：使用一个布尔数组进行标记，避免数字重复使用。
 *    
 * （2）不能有重复
 *   示例：candidates = [ 1, 2, 2 ], target = 3
 *    1）因为是组合形式，所以无序，即相同数字的不同顺序实际上是重复的。如 [1, 2] 和 [2, 1] 是重复的。
 *      解决方法：在递归方法的参数中加入一个下标 index，从而使得每次都是向后选择，而不会从头选择（即不让 [2,1] 这种情况出现）。
 *      
 *    2）待选择的数字数组中有重复的数字，则可能会因为这些重复数字造成重复。如 [1, 2]， 2 可以是第 1 个 2 也可以是第 2 个 2。
 *      解决方法：对待选择的数字数组进行排序，然后在 for 循环中添加以下判断
 *           if (i > 0 && candidates[i - 1] == candidates[i] && !flags[i - 1]) 
 *      因为是从前向后处理，所以在处理 candidates[i] 时，则之前的数组 candidates[0...i-1] 必定处理了，但是可能正在处理中，也可能处理完毕。
 *      如果 flags[i - 1] 为 false，则说明 candidates[i - 1] 已经处理完毕（已经发生重置）。同时，若 candidates[i - 1] == candidates[i]，
 *      则说明 candidates[i] 代表的数字已经处理完毕，既然已经处理完毕，则可以跳过。
 */
class _40Solution {
    
    private List<List<Integer>> resultList = null;
    private int[] candidates = null;
    private boolean[] flags = null; // 数字不能重复使用，用 flags 进行标记
    
    private void getCombinationSum(int index, int target, List<Integer> list) {
        if (target < 0) {
            return;
        }
        
        if (0 == target) {
            resultList.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = index; i < candidates.length; ++i) { // 从 index 处开始，保证最终结果是组合形式的，不会有重复
            if (i > 0 && candidates[i - 1] == candidates[i] && !flags[i - 1]) {
                continue; // 因为 candidates 中有重复数字，进行特殊处理，避免结果造成重复
            }
            
            if (!flags[i]) {
                flags[i] = true;
                list.add(candidates[i]);
                getCombinationSum(i + 1, target - candidates[i], list);
                list.remove(list.size() - 1);
                flags[i] = false;
            }
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // candidates 中有重复数字，对其进行排序，使得相同的数字在一起
        this.resultList = new ArrayList<>();
        this.candidates = candidates;
        this.flags = new boolean[candidates.length];
        
        getCombinationSum(0, target, new ArrayList<>());
        
        return resultList;
    }
}
