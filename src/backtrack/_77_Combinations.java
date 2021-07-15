package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 * 
 * 题目描述：给定两个整数 n 和 k，返回所有由 [1, n] 之间的数字组成的长度为 k 的组合。
 * 
 * 限制条件：
 *  （1） 1 <= n <= 20
 *  （2） 1 <= k <= n
 * 
 * 示例：
 *  Input：n = 4, k = 2
 *  Output： [[2,4], [3,4], [2,3], [1,2], [1,3], [1,4]]
 * 
 */
public class _77_Combinations {

    public static void main(String[] args) {
        // test case 1
        //        int n = 4, k = 2;

        // test case 2
        //        int n = 1, k = 1;

        // test case 3（测试剪枝）
        int n = 8, k = 6;

        _77Solution solution = new _77Solution();

        System.out.println(solution.combine(n, k));
        System.out.println(solution.count);
    }
}

class _77Solution {
    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();

    /**
     * 用于统计 listAllCombination 和 listAllCombination1 的递归调用次数，测试剪枝效果
     */
    public static int count = 0;

    /**
     * 在 [start, n] 之间选择数进行组合，combination 是已经获得的组合
     * 
     * @param n             n 个整数
     * @param k             组合结果的长度
     * @param start         当前在 [0, n] 区间选择的起点
     * @param combination   组合的结果
     */
    private void listAllCombination0(int n, int k, int start, List<Integer> combination) {
        if (combination.size() == k) {
            resultList.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = start; i <= n; ++i) {
            ++count;
            combination.add(i);
            listAllCombination0(n, k, i + 1, combination);
            combination.remove(combination.size() - 1);
        }
    }

    // 剪枝版本实现1
    private void listAllCombination1(int n, int k, int start, List<Integer> combination) {
        // 原来循环的结束条件是 i <= n ，但是实际上如果一开始， [start, n] 之间的数字不能选满 k 个，则不需要进行递归组合
        // 因为现在已经选择了 combination.size() 个，所以至少还需要 (k - combination.size()) 个，即至少要循环 (k - combination.size()) 次
        // 又因为会循环 (n - start + 1) 次，所以 (n - start + 1) >= (k - combination.size())
        if ((n - start + 1) < (k - combination.size())) {
            return; // 不能选满 k 个数
        }

        if (combination.size() == k) {
            resultList.add(new ArrayList<Integer>(combination));
            return;
        }

        for (int i = start; i <= n; ++i) {
            ++count;
            combination.add(i);
            listAllCombination1(n, k, i + 1, combination);
            combination.remove(combination.size() - 1);
        }
    }
    
    // 剪枝版本实现2（最优）
    private void listAllCombination(int n, int k, int start, List<Integer> combination) {
        if (combination.size() == k) {
            resultList.add(new ArrayList<Integer>(combination));
            return;
        }

        // 原来循环的结束条件是 i <= n ，但是实际上如果一开始， [start, n] 之间的数字不能选满 k 个，则不需要进行递归组合
        // 因为现在已经选择了 combination.size() 个，所以至少还需要 (k - combination.size()) 个，即 [i, n] 的长度至少是 (k - combination.size())
        // 转换成公式，即为：(n - i + 1) >= (k - combination.size()) --> n - k + combination.size() + 1 >= i
        for (int i = start; i <= n - k + combination.size() + 1; ++i) {
            ++count;
            combination.add(i);
            listAllCombination(n, k, i + 1, combination);
            combination.remove(combination.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || k > n) {
            return resultList;
        }

        listAllCombination0(n, k, 1, new ArrayList<Integer>());

        return resultList;
    }
}
