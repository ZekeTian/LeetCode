package greedy;

/**
 * https://leetcode.com/problems/is-subsequence/
 * 
 * 题目描述：给定两个字符串 s 和 t，如果 s 是 t 的子序列，则返回 true；否则，返回 false。
 *        一个字符串的子序列可以通过删除原字符串的一些字符得到，注意：不能改变字符的相对位置，一个字符串是自己本身的子序列。
 * 
 * 条件限制：
 *  （1）0 <= s.length <= 100
 *  （2）0 <= t.length <= 10^4
 *  （3）s 和 t 仅包含小写的英文字符
 * 
 * 示例：
 *  示例 1：
 *      Input: s = "abc", t = "ahbgdc"
 *      Output: true
 *      
 *  示例 2:
 *      Input: s = "axc", t = "ahbgdc"
 *      Output: false
 */
public class _392_IsSubsequence {
    
    public static void main(String[] args) {
        // test case1, true
        String s = "abc";
        String t = "ahbgdc";
        
        // test case2, false
//        String s = "axc";
//        String t = "ahbgdc";
        
//      _392Solution1 solution = new _392Solution1();
        
        _392Solution2 solution = new _392Solution2();
        
        
        System.out.println(solution.isSubsequence(s, t));
    }
}

/**
 * 解法一：贪心算法
 */
class _392Solution1 {
    public boolean isSubsequence(String s, String t) {
        int sIdx = 0;
        int tIdx = 0;
        
        while (sIdx < s.length() && tIdx < t.length()) {
            if (s.charAt(sIdx) == t.charAt(tIdx)) {
                ++sIdx;
            }
            ++tIdx;
        }
        
        return sIdx == s.length();
    }
}

/**
 * 解法二：动态规划（编辑距离问题）
 */
class _392Solution2 {
    
    public boolean isSubsequence(String s, String t) {
        int[][] memo = new int[s.length() + 1][t.length() + 1]; // memo[i][j] 表示字符串 s[0...i-1] 与 t[0...j-1] 的公共子序列的长度
        
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 1; j <= t.length(); ++j) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1; // s[i - 1] = t[j - 1]，则在之前长度的基础上加上当前字符（即 +1）
                } else {
                    memo[i][j] = memo[i][j - 1]; // s[i - 1] != t[j - 1]，则字符串 t 删除 t[j - 1]，删除后的结果即为当前的结果 
                }
            }
        }
        
        return memo[s.length()][t.length()] == s.length();
    }
}
