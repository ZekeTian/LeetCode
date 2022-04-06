package math;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/palindrome-number/
 * 
 * 题目描述：给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *         回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *         例如，121 是回文，而 123 不是。
 *         
 * 限制条件：-2^31 <= x <= 2^31 - 1
 * 
 * 示例：
 *  示例 1
 *      输入：x = 121
 *      输出：true
 *      
 *  示例 2
 *      输入：x = -121
 *      输出：false
 *      解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *      
 *  示例 3
 *      输入：x = 10
 *      输出：false
 *      解释：从右向左读, 为 01 。因此它不是一个回文数。
 * 
 */
public class _9_PalindromeNumber {

    public static void main(String[] args) {
        // test case1, outptu: true
        int x = 121;
        
        // test case2, outptu: false
//        int x = -121;
        
        // test case3, outptu: false
//        int x = 10;
        
//        _9Solution1 solution = new _9Solution1();
        
//        _9Solution2 solution = new _9Solution2();
        
        _9Solution3 solution = new _9Solution3();
        
        
        System.out.println(solution.isPalindrome(x));
    }
}

/**
 * 解法一；转换成字符串，然后用 StringBuilder 的 reverse 实现
 */
class _9Solution1 {
    
    public boolean isPalindrome(int x) {
        StringBuilder builder = new StringBuilder(x + "");
        
        return builder.toString().equals(builder.reverse().toString());
    }
}

/**
 * 解法二：使用栈
 */
class _9Solution2 {
    
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false; // 如果是负数，则一定不可能是回文数
        }
        
        // 提取出 x 中的每一位数，放进栈中
        int num = x;
        Stack<Integer> stack = new Stack<>();
        while (num != 0) {
            stack.push(num % 10);
            num /= 10;
        }
        
        // 再次提取 x 中的每一位数，然后与栈中数进行比较，判断是否相同。
        // x 提取数字的顺序是逆序，而栈中的顺序是顺序。如果顺序和逆序一样，则说明是回文数。
        num = x;
        while (!stack.isEmpty()) {
            if (stack.pop() != (num % 10)) {
                return false;
            }
            num /= 10;
        }
        
        return true;
    }
}


/**
 * 解法三：使用双指针
 */
class _9Solution3 {
    
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        
        // 提取出 x 中的每一位数，存储进列表中
        List<Integer> list = new ArrayList<>();
        int num = x;
        while (num != 0) {
            list.add(num % 10);
            num /= 10;
        }
        
        // 使用双指针进行碰撞
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left) != list.get(right)) {
                return false;
            }
            ++left;
            --right;
        }
        
        return true;
    }
}