package dynamicprogramming;

/**
 * https://leetcode.com/problems/palindromic-substrings/
 * 
 * 题目描述：给你一个字符串 s ，请你统计并返回这个字符串中回文子串的数目。回文字符串 是正着读和倒过来读一样的字符串。
 *        子字符串是字符串中的由连续字符组成的一个序列。具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *        
 * 限制条件：
 *  （1）1 <= s.length <= 1000
 *  （2）s 由小写英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入：s = "abc"
 *      输出：3
 *      解释：三个回文子串: "a", "b", "c"
 *      
 *  示例 2
 *      输入：s = "aaa"
 *      输出：6
 *      解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class _647_PalindromicSubstrings {

    public static void main(String[] args) {
        // test case 1, output: 3
//        String s = "abc";
        
        // test case 2, output: 6
        String s = "aaa";
        
        _647Solution1 solution = new _647Solution1();
        
        System.out.println(solution.countSubstrings(s));
    }
}

/**
 * 解法一：暴力解法。尝试所有可能的子串，然后判断子串是否是回文。
 */
class _647Solution1 {
    
    // 判断字符串 str 是否为回文
    private boolean isPalindromic(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
    
    public int countSubstrings(String s) {
        int num = 0;
        
        for (int i = 0; i < s.length(); ++i) {
            // 以 i 结尾，然后尝试截取长度为 j 的子串 （子串长度不可能超过 i + 1）
            for (int j = 1; j <= i + 1; ++j) {
                if (isPalindromic(s.substring(i + 1 - j, i + 1))) {
                    ++num;
                }
            }
        }
        
        return num;
    }
}
