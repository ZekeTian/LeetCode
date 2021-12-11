package dynamicprogramming;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * 
 * 题目描述：给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *        子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *        
 * 限制条件：
 *  （1）1 <= s.length <= 1000
 *  （2）s 仅由小写英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入：s = "bbbab"
 *      输出：4
 *      解释：一个可能的最长回文子序列为 "bbbb" 。
 *      
 *  示例 2
 *      输入：s = "cbbd"
 *      输出：2
 *      解释：一个可能的最长回文子序列为 "bb" 。
 * 
 */
public class _516_LongestPalindromicSubsequence {

    public static void main(String[] args) {
        // test case 1, output: 4
        String s = "bbbab";
        
        // test case 2, output: 2
//        String s = "cbbd";
        
        
        _516Solution solution = new _516Solution();
        
        
        System.out.println(solution.longestPalindromeSubseq(s));
    }
}

class _516Solution {
    
    public int longestPalindromeSubseq(String s) {
        int[][] memo = new int[s.length()][s.length()];
        
        // 从下向上、从左向右计算（因为计算 memo[i][j] 时需要先知道 memo[i + 1][j - 1]、memo[i + 1][j]、memo[i][j - 1]）
        for (int i = s.length() - 1; i >= 0; --i) {
            for (int j = i; j < s.length(); ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    int len = j - i + 1; // 计算 s[i...j] 的长度
                    // 如果 s[i...j] 长度 <= 2，则是特殊情况，直接赋值即可；否则，是一般情况，需要根据前面的结果进行计算（因为有 s[i]、s[j] 两个字符，所以 +2）
                    memo[i][j] = (len <= 2 ? len : memo[i + 1][j - 1] + 2);
                } else {
                    // s[i] != s[j] 时，尝试只加 s[i]（即 s[i+1...j]） 或 s[j]（即 s[i...j-1]），然后比较两个的大小
                    memo[i][j] = Math.max(memo[i + 1][j], memo[i][j - 1]); 
                }
            }
        }
        
        return memo[0][s.length() - 1];
    }
}