package array;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * 
 * 题目描述：给你一个字符串 s，找到 s 中最长的回文子串。
 * 
 * 条件限制：
 *  （1）1 <= s.length <= 1000
 *  （2）s 仅由数字和英文字母（大写和/或小写）组成
 *
 * 示例：
 *  示例 1
 *   Input：s = "babad"
 *   Output："bab"        
 *      "aba" 同样是符合题意的答案。
 *  
 *  示例 2
 *   Input：s = "cbbd"
 *   Output："bb"
 */
public class _5_LongestPalindromicSubstring {

    public static void main(String[] args) {
        // test case 1, output: "bab"
//        String s = "babad";
        
        // test case 2, output: "bb"
//        String s = "cbbd";
        
        // test case 3, output: "a"
        String s = "a";
        
        _5Solution solution = new _5Solution();
        
        System.out.println(solution.longestPalindrome(s));
    }
}

/**
 * 由中心向两边扩展，寻找回文字符串
 */
class _5Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        int maxLen = 0;
        
        // 尝试将 s 中每个位置的字符作为中心，然后向两边扩展，得到回文字符串
        for (int i = 0; i < s.length(); ++i) {
            int left = i;
            int right = i;
            
            // 从中心位置向左扩展，直到找到不等于 s[i] 的字符为止
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                --left;
            }
            // 从中心位置向右扩展
            while (right < s.length() && s.charAt(right) == s.charAt(i)) {
                ++right;
            }
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }
            
            if (maxLen < (right - left - 1)) {
                maxLen = right - left - 1;
                start = left + 1;
                end = right - 1;
            }
        }
        
        return s.substring(start, end + 1);
    }
}

