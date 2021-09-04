package string;

import java.util.Stack;
import java.util.StringJoiner;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * 
 * 题目描述：给你一个字符串 s ，逐个翻转字符串中的所有单词。单词是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的单词分隔开。
 *        请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 *        说明：
 *          （1）输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 *          （2）翻转后单词间应当仅用一个空格分隔。
 *          （3）翻转后的字符串中不应包含额外的空格。
 * 
 * 条件限制：
 *  （1）1 <= s.length <= 10^4
 *  （2）s 包含英文大小写字母、数字和空格 ' '
 *  （3）s 中 至少存在一个单词
 *  
 * 示例：
 *      示例 1
 *      Input: s = "the sky is blue"
 *      Output: "blue is sky the"
 *      
 *      示例 2
 *      Input: s = "  Bob    Loves  Alice   "
 *      Output: "Alice Loves Bob"
 * 
 */
public class _151_ReverseWordsInAString {

    public static void main(String[] args) {
        // test case 1, output: "blue is sky the"
        //        String s = "the sky is blue";

        // test case 2, output: "Alice Loves Bob"
        //        String s = "  Bob    Loves  Alice   ";

        // test case 3, output: "c bb aa"
        String s = "  aa bb c   ";

        //        _151Solution1 solution = new _151Solution1();
        _151Solution2 solution = new _151Solution2();

        System.out.println(solution.reverseWords(s));
    }
}

/**
 * 解法一：使用 String.split 分割出单词，然后反转
 */
class _151Solution1 {
    public String reverseWords(String s) {
        StringJoiner joiner = new StringJoiner(" ");
        s = s.trim(); // 删除前面和后面的空格
        String[] split = s.split("\\s+");

        for (int i = split.length - 1; i >= 0; --i) {
            joiner.add(split[i]);
        }

        return joiner.toString();
    }
}

/**
 * 解法二：循环分割出单词，然后通过栈反转
 */
class _151Solution2 {
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        int start = 0;
        // 分割出单词
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);

            if (ch != ' ') {
                if (i > 0 && s.charAt(i - 1) == ' ') { // 当前字符不是空格，但是前一个字符是空格，则当前字符是单词的首字符
                    start = i;
                }
                if (i == s.length() - 1) {
                    stack.push(s.substring(start, s.length())); // 到达最后一个字符，将最后一个单词分割出来
                }
            } else {
                if (i > 0 && s.charAt(i - 1) != ' ') { // 当前字符是空格，但是前一个字符不是空格，则当前字符是单词后的字符
                    stack.push(s.substring(start, i));
                }
            }
        }

        // 通过栈反转单词
        StringJoiner joiner = new StringJoiner(" ");
        while (!stack.isEmpty()) {
            joiner.add(stack.pop());
        }

        return joiner.toString();
    }
}
