package backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/permutations-ii/
 * 
 * 题目描述：给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 限制条件：
 *  （1）1 <= nums.length <= 8
 *  （2）-10 <= nums[i] <= 10

 * 示例：
 *  示例 1
 *      输入：nums = [1,1,2]
 *      输出：
 *          [[1,1,2],
 *           [1,2,1],
 *           [2,1,1]]
 *           
 *  示例 2
 *      输入：nums = [1,2,3]
 *      输出：
 *          [[1,2,3],
 *           [1,3,2],
 *           [2,1,3],
 *           [2,3,1],
 *           [3,1,2],
 *           [3,2,1]]
 */
public class _47_PermutationsII {

    public static void main(String[] args) {
        // test case1
//        int[] nums = {1, 1, 2};
        
        // test case2
        int[] nums = {1, 2, 3};
        
        _47Solution1 solution = new _47Solution1();
        
        
        System.out.println(solution.permuteUnique(nums));
    }
}

/**
 * 实现方式一：使用 Set 去重
 *       2
 *      / \
 *     1   1
 *    /     \
 *   1       1
 *   图中两条路径的结果是重复的，需要去重
 */
class _47Solution1 {
    private List<List<Integer>> resultList = null;
    private Set<String> set = new HashSet<>();
    private boolean[] flags = null;
    private int[] nums = null;
    
    private void getPermuteUnique(List<Integer> list) {
        if (list.size() == nums.length) {
            String listStr = list.toString();
            if (!set.contains(listStr)) {
                resultList.add(new ArrayList<>(list));
                set.add(listStr);
            }
            return;
        }
        
        for (int i = 0; i < nums.length; ++i) {
            if (!flags[i]) {
                flags[i] = true;
                list.add(nums[i]);
                getPermuteUnique(list);
                list.remove(list.size() - 1);
                flags[i] = false;
            }
        }
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        this.resultList = new ArrayList<>();
        this.flags = new boolean[nums.length];
        this.nums = nums;
        
        getPermuteUnique(new ArrayList<>());
        
        return resultList;
    }
}


/**
 * 实现方式一：使用剪枝策略去重
 *       2
 *      / \
 *     1   1
 *    /     \
 *   1       1
 */
class _47Solution2 {
    
    List<List<Integer>> resultList = null;
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        resultList = new ArrayList<>();
        
        return resultList;
    }
}