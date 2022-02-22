package array;

/**
 * https://leetcode.com/problems/valid-palindrome/
 * 
 * 题目描述：给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *        说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 限制条件：
 *  （1）1 <= s.length <= 2 * 105
 *  （2）字符串 s 由 ASCII 字符组成
 *  
 * 示例：
 *  示例 1
 *       输入: "A man, a plan, a canal: Panama"
 *       输出: true
 *       解释："amanaplanacanalpanama" 是回文串
 *  
 *  示例 2
 *      输入: "race a car"
 *      输出: false
 *      解释："raceacar" 不是回文串
 *      
 */
public class _125_ValidPalindrome {
    
    public static void main(String[] args) {
        // test case1, output: true
//        String s = "A man, a plan, a canal: Panama";
        
        // test case2, output: false
        String s = "race a car";
        
        
        _125Solution solution = new _125Solution();
        
        
        System.out.println(solution.isPalindrome(s));
    }
}


class _125Solution {
    
    private boolean isDigital(char ch) {
        return (ch >= '0' && ch <= '9');
    }
    
    private boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
    
    private char toLower(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char)(ch + 'a' - 'A');
        }
        
        return ch;
    }
    
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < s.length() && !isDigital(s.charAt(left)) && !isLetter(s.charAt(left))) {
                ++left;
            }
            
            while (right >= 0 && !isDigital(s.charAt(right)) && !isLetter(s.charAt(right))) {
                --right;
            }
            
            if (left >= s.length() && right < 0) {
                break;
            }
            if (toLower(s.charAt(left)) != toLower(s.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }
        
        return true;
    }
}
