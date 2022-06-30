package string;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 * 
 * 题目描述：给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 10^5
 *  （2）s 由小写英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入: s = "aba"
 *      输出: true
 *      
 *  示例 2
 *      输入: s = "abca"
 *      输出: true
 *      解释: 你可以删除c字符。
 *      
 *  示例 3
 *      输入: s = "abc"
 *      输出: false
 * 
 */
public class _680_ValidPalindromeII {

    public static void main(String[] args) {
        // test case1, output: true
//        String s = "aba";
        
        // test case2, output: true
//        String s = "abca";
        
        // test case3, output: false
        String s = "abc";
        
        
        _680Solution1 solution = new _680Solution1();
        
        
        System.out.println(solution.validPalindrome(s));
    }
    
}


/**
 * 解法一：递归实现
 */
class _680Solution1 {
    
    private int count = 0; // 字符串 s 中，两个字符不相等的次数。在本题中，因为最多可以删除一个字符，所以 count 最大为 1
    
    // 判断 s[start...end] 是否为回文串
    private boolean validPalindrome(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        
        if (s.charAt(start) == s.charAt(end)) {
            return validPalindrome(s, start + 1, end - 1); // s[start] 和 s[end] 相等，则缩小字符串 s 的长度，继续判断是否为回文串
        }
        
        // s[start] 和 s[end] 不相等时，则 count + 1
        ++count;
        if (count > 1) {
            return false; // 不相等的次数已经大于 1，则不能继续通过删除操作让字符串 s 成为回文串
        }
        
        // s[start] 和 s[end] 不相等，且不相等的次数只是一次，则可以删除 s[start] 或删除 s[end]，然后继续判断是否为回文串
        return validPalindrome(s, start + 1, end) // 删除 s[start] 
                || validPalindrome(s, start, end - 1); // 删除 s[end]
    }
    
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1);
    }
    
}

