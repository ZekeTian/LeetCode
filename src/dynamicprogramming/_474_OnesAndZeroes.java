package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/ones-and-zeroes/
 *
 */
public class _474_OnesAndZeroes {

    public static void main(String[] args) {
        // test case 2, output: 2
        String[] strs = {"10","0","1"};
        int m = 1;
        int n = 1;
        
        _474Solution1 solution = new _474Solution1();
        
        System.out.println(solution.findMaxForm(strs, m, n));
    }
}

/**
 * 解法一：递归
 */
class _474Solution1 {
    
    private class Pair {
        int one = 0;
        int zero = 0;
        
        Pair(int one, int zero) {
            this.one = one;
            this.zero = zero;
        }
    }
    
    private String[] strs = null;
    private Map<String, Pair> map = null;
    private int[] memo = null;

    // 统计 str 数组各个字符串中 0、1 的个数
    private Map<String, Pair> countStrs(String[] str) {
        Map<String, Pair> map = new HashMap<>();
        for (String s : strs) {
            int one = 0;
            int zero = 0;
            for (int i = 0; i < s.length(); ++i) {
                if ('1' == s.charAt(i)) {
                    ++one;
                } else {
                    ++zero;
                }
            }
            map.put(s, new Pair(one, zero));
        }
        
        return map;
    }
    
    // 处理字符串 strs[index]
    private int tryFindMaxForm(int index, int m, int n) {
        if (index >= strs.length || m < 0 || n < 0) {
            return 0;
        }
        
        Pair pair = map.get(strs[index]);
        int res = 0;
        if (pair.zero <= m && pair.one <= n) {
            res = tryFindMaxForm(index + 1, m - pair.zero, n - pair.one) + 1; // 选择字符串 strs[index] 
        }
        res = Math.max(res, tryFindMaxForm(index + 1, m, n)); // 选择字符串 strs[index]
        memo[index] = res;
        
        return res;
    }
    
    public int findMaxForm(String[] strs, int m, int n) {
        if (0 == strs.length) {
            return 0;
        }
        this.strs = strs;
        this.map = countStrs(strs);
        this.memo = new int[strs.length];
        Arrays.fill(memo, -1);
        
        return tryFindMaxForm(0, m, n);
    }
}