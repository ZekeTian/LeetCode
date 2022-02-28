package array;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * 
 * 题目描述：给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 *        元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 3 * 10^5
 *  （2）s 由 可打印的 ASCII 字符组成
 * 
 * 示例：
 *  输入：s = "hello"
 *  输出："holle"
 *  
 */
public class _345_ReverseVowelsOfAString {

    public static void main(String[] args) {
        String s = "hello";
        
        _345Solution solution = new _345Solution();
        
        System.out.println(solution.reverseVowels(s));
    }
}


class _345Solution {
    
    private boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'A' || ch == 'e' || ch == 'E' || ch == 'i' || ch == 'I'
             || ch == 'o' || ch == 'O' || ch == 'u' || ch == 'U');
    }
    
    public String reverseVowels(String s) {
        if (null == s || s.length() == 0) {
            return s;
        }
        
        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            while (left < arr.length && !isVowel(arr[left])) {
                ++left;
            }
            while (right >= 0 && !isVowel(arr[right])) {
                --right;
            }
            
            if (left >= right) {
                break; // 已经交换完，结束循环
            }
            
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            ++left;
            --right;
        }
        
        return new String(arr);
    }
}
