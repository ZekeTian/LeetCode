package math;

/**
 * https://leetcode.com/problems/excel-sheet-column-title/
 * 
 * 题目描述：给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *         例如：
 *          A -> 1
 *          B -> 2
 *          C -> 3
 *          ...
 *          Z -> 26
 *          AA -> 27
 *          AB -> 28 
 *          ...
 * 
 * 限制条件：1 <= columnNumber <= 2^31 - 1
 * 
 * 示例：
 *  示例 1
 *      输入：columnNumber = 1
 *      输出："A"
 *      
 *  示例 2
 *      输入：columnNumber = 28
 *      输出："AB"
 *      
 *  示例 3
 *      输入：columnNumber = 701
 *      输出："ZY"
 * 
 */
public class _168_ExcelSheetColumnTitle {

    public static void main(String[] args) {
        // test case1, output: "A"
//        int columnNumber = 1;
        
        // test case2, output: "AB"
//        int columnNumber = 28;
        
        // test case2, output: "ZY"
        int columnNumber = 701;
        
        
        _168Solution solution = new _168Solution();
        
        System.out.println(solution.convertToTitle(columnNumber));
    }
}

/**
 * 该题本质上是一个 10 进制转换成 26 进制的问题。
 * 但是，由于 26 进制的数字范围是 [0, 25]，而题中 A-Z 的范围是 [1, 26]。
 * 所以转换成 26 进制之后，实际上相当于将 A-Z 左移一位（即减 1）。
 * 因此，在计算的时候，待转换的数字 columnNumber 也应当左移一位（即减 1）。
 */
class _168Solution {
    public String convertToTitle(int columnNumber) {
        int num = 26; // 转换成 26 进制 
        StringBuilder builder = new StringBuilder();
        
        while (columnNumber > 0) {
            // 由于 26 进制的数字范围是 [0, 25]，而题中 A-Z 的范围是 [1, 26]。
            // 所以转换成 26 进制之后，实际上相当于将 A-Z 左移一位（即减 1）。
            // 与此同时，对应的 columnNumber 也应当左移一位（即减 1）。
            --columnNumber; // 左移一位
            builder.append((char) ('A' + columnNumber % num));
            columnNumber /= num;
        }
        
        return builder.reverse().toString();
    }
}
