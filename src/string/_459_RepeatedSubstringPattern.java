package string;

/**
 * https://leetcode.com/problems/repeated-substring-pattern/
 * 
 * 题目描述：给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 10^4
 *  （2）s 由小写英文字母组成
 *
 * 示例：
 *  示例 1
 *      输入: s = "abab"
 *      输出: true
 *      解释: 可由子串 "ab" 重复两次构成。
 *  
 *  示例 2
 *      输入: s = "aba"
 *      输出: false
 *  
 *  示例 3
 *      输入: s = "abcabcabcabc"
 *      输出: true
 *      解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
 *  
 */
public class _459_RepeatedSubstringPattern {

    public static void main(String[] args) {
        // test case1, output: true
//        String s = "abab";
       
        // test case2, output: false
//        String s = "aba";
        
        // test case2, output: true
        String s = "abcabcabcabc";
        
        
        _459Solution solution = new _459Solution();
        
        
        System.out.println(solution.repeatedSubstringPattern(s));
    }
}

/**
 * 直接模拟，一个个尝试。
 * 因为子串 pattern 一定是从下标 0 开始，所以可以从 0 开始，之后截取长度为 1、2、3、... n 的字符串作为 pattern。
 * 然后，在字符串中去匹配该 pattern，看是否能够匹配完。如果能够匹配完，则说明 pattern 是合法有效的，所以返回 true。
 */
class _459Solution {
    
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        
        for (int i = 1; i < s.length(); ++i) {
            // 将 s[0...i-1] 作为 pattern，如果 s 的长度不是 pattern 长度的整数倍，则 pattern 一定无效
            if (s.length() % i != 0) {
                continue; // 结束此次循环，扩大 pattern
            }
            
            int j = i; // s 中的下标，用来和 pattern 进行比较
            int k = 0; // pattern 的下标
            for (j = i; j < s.length(); ++j) {
                if (s.charAt(j) != s.charAt(k)) {
                    break; // 无法匹配该 pattern 
                }
                if ((++k) >= i) {
                    k = 0; // 已经匹配完一次 pattern，则从头开始继续匹配 pattern
                }
            }
            
            if (j >= s.length()) {
                return true; // 字符串 s 能够匹配 pattern 多次
            }
        }
        
        return false;
    }
}
