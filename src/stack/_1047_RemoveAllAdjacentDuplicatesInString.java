package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 * 
 * 题目描述：给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *         在 S 上反复执行重复项删除操作，直到无法继续删除。
 *         在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 
 * 限制条件：
 *  （1）1 <= S.length <= 20000
 *  （2）S 仅由小写英文字母组成
 *  
 * 示例：
 *  输入："abbaca"
 *  输出："ca"
 *  解释：例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
 *       之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *
 */
public class _1047_RemoveAllAdjacentDuplicatesInString {

    public static void main(String[] args) {
        // test case1, output: "ca"
        String s = "abbaca";
        
        // test case2, output: "a"
//        String s = "aaaaaaaaa";
                
//        _1047Solution1 solution = new _1047Solution1();
        
        _1047Solution2 solution = new _1047Solution2();
        
        
        System.out.println(solution.removeDuplicates(s));
    }
}

/**
 * 解法一：直接使用 Stack 解决，思路如下：
 *       遍历字符串 s
 *          如果当前字符和栈顶元素一样，则弹出栈顶元素，跳过当前字符，然后继续遍历下一个字符
 *          否则，将当前字符压入栈中，继续遍历下一个字符
 */
class _1047Solution1 {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); ++i) {
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                stack.pop(); // 当前字符和栈顶字符相等，则从栈中弹出相等的字符
            } else {
                stack.push(s.charAt(i)); // 栈为空 或 当前字符和栈顶字符不相等，则将当前字符压入栈中
            }
        }
        
        // 将栈中的元素拼接成字符串
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        
        return builder.reverse().toString(); // 栈弹出的元素顺序和实际顺序相反，因此需要反转
    }
}

/**
 * 解法二：直接使用 StringBuilder 模拟栈中，避免解法一后面遍历栈
 */
class _1047Solution2 {
    
    public String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (!stack.isEmpty() && stack.charAt(stack.length() - 1) == s.charAt(i)) {
                stack.deleteCharAt(stack.length() - 1); // 当前字符和栈顶字符相等，则从栈中弹出相等的字符
            } else {
                stack.append(s.charAt(i)); // 栈为空 或 当前字符和栈顶字符不相等，则将当前字符压入栈中
            }
        }
        
        return stack.toString();
    }
}