package string;

/**
 * https://leetcode.com/problems/excel-sheet-column-number/
 * 
 * 题目描述：给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
 *         A -> 1
 *         B -> 2
 *         C -> 3
 *         ...
 *         Z -> 26
 *         AA -> 27
 *         AB -> 28 
 *         ...
 *         
 * 限制条件：
 *  （1）1 <= columnTitle.length <= 7
 *  （2）columnTitle 仅由大写英文组成
 *  （3）columnTitle 在范围 ["A", "FXSHRXW"] 内
 *  
 * 示例：
 *  示例 1
 *      输入: columnTitle = "A"
 *      输出: 1
 *      
 *  示例 2
 *      输入: columnTitle = "AB"
 *      输出: 28
 *  
 *  示例 3
 *      输入: columnTitle = "ZY"
 *      输出: 701
 *      
 *      
 */
public class _171_ExcelSheetColumnNumber {

    public static void main(String[] args) {
        // test case1, output: 1
//        String columnTitle = "A";
        
        // test case2, output: 28
//        String columnTitle = "AB";
        
        // test case3, output: 701
        String columnTitle = "ZY";
        
        
        _171Solution solution = new _171Solution();
        
        System.out.println(solution.titleToNumber(columnTitle));
    }
    
}

/**
 * 该题相当于是 26 进制转换成普通的 10 进制数字
 */
class _171Solution {
    
    public int titleToNumber(String columnTitle) {
        int num = 0;
        int a = 1; // 每位的倍数
        
        for (int i = columnTitle.length() - 1; i >= 0; --i) {
            int b = columnTitle.charAt(i) - 'A' + 1; // 将当前字符转换成对应的数字
            num = num + b * a;
            a *= 26;
        }
        
        return num;
    }
    
}


