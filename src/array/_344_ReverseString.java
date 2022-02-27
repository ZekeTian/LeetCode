package array;

/**
 * https://leetcode.com/problems/reverse-string/
 * 
 * 题目描述：编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 *        不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *        
 * 限制条件：
 *  （1）1 <= s.length <= 10^5
 *  （2）s[i] 都是 ASCII 码表中的可打印字符
 * 
 * 示例：
 *  输入：s = ["h","e","l","l","o"]
 *  输出：["o","l","l","e","h"]
 * 
 */
public class _344_ReverseString {

    public static void main(String[] args) {
        char[] s = { 'h', 'e', 'l', 'l', 'o' };
        
        _344Solution solution = new _344Solution();
        solution.reverseString(s);
        
        System.out.println(s);
    }
}

class _344Solution {
    
    public void reverseString(char[] s) {
        if (null == s || s.length == 0) {
            return;
        }
        
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            ++left;
            --right;
        }
    }
}
