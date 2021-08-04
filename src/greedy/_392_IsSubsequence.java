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
//        String s = "abc";
//        String t = "ahbgdc";
        
        // test case2, false
        String s = "axc";
        String t = "ahbgdc";
        
        _392Solution solution = new _392Solution();
        System.out.println(solution.isSubsequence(s, t));
    }
}


class _392Solution {
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


