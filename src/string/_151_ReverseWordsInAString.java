package string;

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
        String s = "  Bob    Loves  Alice   ";
        
        _151Solution1 solution = new _151Solution1();
        
        System.out.println(solution.reverseWords(s));
    }
}

/**
 * 解法一：使用 String.split 分割出单词，然后反转
 */
class _151Solution1 {
    public String reverseWords(String s) {
        StringJoiner joiner = new StringJoiner(" ");
        s = s.replaceFirst("^\\s+", ""); // 删除前导空格
        String[] split = s.split("\\s+");
        
        for (int i = split.length - 1; i >= 0; --i) {
            joiner.add(split[i]);
        }
        
        return joiner.toString();
    }
}
