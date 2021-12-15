package dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/ones-and-zeroes/
 *
 * 题目描述：给你一个二进制字符串数组 strs 和两个整数 m 和 n。
 *        请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *        如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *        
 * 限制条件：
 *  （1）1 <= strs.length <= 600
 *  （2）1 <= strs[i].length <= 100
 *  （3）strs[i] 仅由 0 和 1 组成
 *  （4）1 <= m, n <= 100
 * 
 * 示例：
 *  示例 1
 *      输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 *      输出：4
 *      解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 *          其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 *  
 *  示例 2
 *      输入：strs = ["10", "0", "1"], m = 1, n = 1
 *      输出：2
 *      解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 */
public class _474_OnesAndZeroes {

    public static void main(String[] args) {
        // test case 1, output: 4
//        String[] strs = {"10", "0001", "111001", "1", "0"};
//        int m = 5;
//        int n = 3;
        
        // test case 2, output: 2
        String[] strs = {"10","0","1"};
        int m = 1;
        int n = 1;
        
//        _474Solution1 solution = new _474Solution1();
        
//        _474Solution2 solution = new _474Solution2();

        _474Solution3 solution = new _474Solution3();
        
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

/**
 * 解法二：递归 + 记忆化
 */
class _474Solution2 {
    
    private class Pair {
        int one = 0;
        int zero = 0;
        
        Pair(int one, int zero) {
            this.one = one;
            this.zero = zero;
        }
    }
    
    private String[] strs = null;
    private int[][][] memo = null;
    private Map<String, Pair> map = null;
    
    // 统计数组 strs 中各个字符串 0、1 字符的个数
    private Map<String, Pair> countStrs() {
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
    
    // 获取处理 strs[index] 时， m 个 0、 n 个 1 情况下的结果  
    private int tryFindMaxForm(int index, int m, int n) {
        if (index >= strs.length || m < 0 || n < 0) {
            return 0;
        }
        if (-1 != memo[index][m][n]) {
            return memo[index][m][n];
        }
        
        int res = 0;
        Pair pair = map.get(strs[index]);
        if (m >= pair.zero && n >= pair.one) {
            res = tryFindMaxForm(index + 1, m - pair.zero, n - pair.one) + 1; // 选择 strs[index]
        }
        res = Math.max(res, tryFindMaxForm(index + 1, m, n)); // 不选择 strs[index]
        memo[index][m][n] = res;
        
        return res;
    }
    
    public int findMaxForm(String[] strs, int m, int n) {
        this.strs = strs;
        this.map = countStrs();
        this.memo = new int[strs.length][m + 1][n + 1]; // memo[i][j][k] 表示在处理 strs[i...len-1] 时，j 个 0、 k 个 1 情况下的结果  
        
        for (int i = 0; i < strs.length; ++i) {
            for (int j = 0; j <= m; ++j) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        
        return tryFindMaxForm(0, m, n);
    }
}

/**
 * 解法三：自底向上动态规划
 */
class _474Solution3 {
    
    private class Pair {
        int one = 0;
        int zero = 0;
        
        Pair(int one, int zero) {
            this.one = one;
            this.zero = zero;
        }
    }
    
    // 统计数组 strs 中各个字符串 0、1 字符的个数
    private Map<String, Pair> countStrs(String[] strs) {
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
    
    public int findMaxForm(String[] strs, int m, int n) {
        Map<String, Pair> map = countStrs(strs);
        int[][][] memo = new int[strs.length][m + 1][n + 1]; // memo[i][j][k] 表示在处理 strs[i...len-1] 时，j 个 0、 k 个 1 情况下的结果  
        
        // 根据最后一个进行初始化
        Pair pair = map.get(strs[strs.length - 1]);
        for (int j = 0; j <= m; ++j) {
            for (int k = 0; k <= n; ++k) {
                if (j >= pair.zero && k >= pair.one) {
                    memo[strs.length - 1][j][k] = 1;
                } else {
                    memo[strs.length - 1][j][k] = 0;
                }
            }
        }
        
        for (int i = strs.length - 2; i >= 0; --i) {
            pair = map.get(strs[i]);
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= n; ++k) {
                    int res = memo[i + 1][j][k]; // 不选择 strs[i]
                    if (j >= pair.zero && k >= pair.one) { // 选择 strs[i]
                        res = Math.max(res, memo[i + 1][j - pair.zero][k - pair.one] + 1);
                    }
                    memo[i][j][k] = res;
                }
            }
        }
        
        
        return memo[0][m][n];
    }
}
