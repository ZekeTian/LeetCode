package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * 题目描述：给定一个只包含 2-9 的字符串，返回这些数字可能组成的所有字符串组合。
 *  数字与字符的映射关系如下：
 *  2: "abc"
 *  3: "def"
 *  4: "ghi"
 *  5: "jkl"
 *  6: "mno"
 *  7: "pqrs"
 *  8: "tuv"
 *  9: "wxyz"
 *  
 * 
 * 条件限制：
 *  （1） 0 <= digits.length <= 4
 *  （2） digits[i] 是一个范围在 2-9 之间的数字
 */
public class _17_LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        // test case 1
        String digits = "23";

        // test case 2
        //        String digits = "";

        // test case 3
        //        String digits = "2";

        _17Solution solution = new _17Solution();

        System.out.println(solution.letterCombinations(digits));
    }
}

class _17Solution {
    private String[] letterMapping = { " ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    private List<String> result = new ArrayList<String>();

    public List<String> letterCombinations(String digits) {

        if (null == digits || 0 == digits.length()) {
            return result;
        }

        letterCombinations(digits, 0, "");

        return result;
    }

    /**
     * 递归枚举出所有可能的组合。方式一，最后一层进入递归。
     * @param digits        原始输入的字符串
     * @param index         当前待处理的字符在 digits 中的下标
     * @param combination   digits 中 [0, index) 个字符组合的字符串
     */
    private void letterCombinations(String digits, int index, String combination) {
        if (index == digits.length()) {
            // 递归到底，保存结果
            result.add(combination);
            return;
        }

        char c = digits.charAt(index);
        for (char c2 : letterMapping[c - '0'].toCharArray()) {
            letterCombinations(digits, index + 1, combination + c2);
        }
    }

    /**
     * 递归枚举出所有可能的组合。方式二，最后一层不再进入递归，而是直接通过循环组合出所有可能的结果。
     * @param digits        原始输入的字符串
     * @param index         当前待处理的字符在 digits 中的下标
     * @param combination   digits 中 [0, index) 个字符组合的字符串
     */
    private void letterCombinations2(String digits, int index, String combination) {
        if (index == digits.length() - 1) {
            // 递归到最后一个字符，然后组合所有可能的结果并保存
            char c = digits.charAt(index);
            String mapping = letterMapping[c - '0'];

            for (char c2 : mapping.toCharArray()) {
                result.add(combination + c2);
            }

            return;
        }

        char c = digits.charAt(index);
        String mapping = letterMapping[c - '0'];

        for (char c2 : mapping.toCharArray()) {
            letterCombinations(digits, index + 1, combination + c2);
        }
    }
}
