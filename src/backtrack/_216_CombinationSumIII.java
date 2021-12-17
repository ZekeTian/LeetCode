package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum-iii/
 * 
 * 题目描述：找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *  
 *  
 * 限制条件：
 *  （1）所有数字都是正整数。
 *  （2）解集不能包含重复的组合。
 *  
 * 示例：
 *  示例 1
 *      输入: k = 3, n = 7
 *      输出: [[1,2,4]]
 *      
 *  示例 2
 *      输入: k = 3, n = 9
 *      输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 */
public class _216_CombinationSumIII {

    public static void main(String[] args) {
        // test case1, output: [[1, 2, 4]]
//        int k = 3, n = 7;
        
        // test case2, output: [[1, 2, 6], [1, 3, 5], [2, 3, 4]]
        int k = 3, n = 9;
        
        _216Solution solution = new _216Solution();
        
        System.out.println(solution.combinationSum3(k, n));
    }
}

/**
 * 回溯法。
 */
class _216Solution {
    
    private List<List<Integer>> resultList = null;
    private boolean[] flags = new boolean[10];
    
    private void getCombinationSum(int k, int n, int index, List<Integer> list) {
        if (0 == k && 0 == n) {
            resultList.add(new ArrayList<>(list));
            return;
        }
        
        if (0 == k || 0 == n || n < 0) {
            return;
        }
        
        for (int i = index; i <= 9; ++i) {
            if (!flags[i]) {
                flags[i] = true;
                list.add(i);
                getCombinationSum(k - 1, n - i, i + 1, list);
                list.remove(list.size() - 1);
                flags[i] = false;
            }
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        this.resultList = new ArrayList<>();
        
        getCombinationSum(k, n, 1, new ArrayList<>());
        
        return resultList;
    }
}
