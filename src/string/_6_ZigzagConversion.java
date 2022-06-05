package string;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 * 
 * 题目描述：将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *         比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *              P   A   H   N
 *              A P L S I I G
 *              Y   I   R
 *         之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。 
 *         请你实现这个将字符串进行指定行数变换的函数： 
 *           string convert(string s, int numRows); 
 *           
 * 限制条件：
 *  （1）1 <= s.length <= 1000
 *  （2）s 由英文字母（小写和大写）、',' 和 '.' 组成
 *  （3）1 <= numRows <= 1000         
 *  
 * 
 * 示例：
 *  示例 1
 *      输入：s = "PAYPALISHIRING", numRows = 3
 *      输出："PAHNAPLSIIGYIR"
 *      
 *  示例 2
 *      输入：s = "PAYPALISHIRING", numRows = 4
 *      输出："PINALSIGYAHRPI"
 *      解释：
 *          P     I    N
 *          A   L S  I G
 *          Y A   H R
 *          P     I
 *          
 *  示例 3
 *      输入：s = "A", numRows = 1
 *      输出："A"
 *      
 */
public class _6_ZigzagConversion {

    public static void main(String[] args) {
        // test case1, output: "PAHNAPLSIIGYIR"
//        String s = "PAYPALISHIRING";
//        int numRows = 3;
//        String result = "PAHNAPLSIIGYIR";
        
        // test case2, output: "PINALSIGYAHRPI"
//        String s = "PAYPALISHIRING";
//        int numRows = 4;
//        String result = "PINALSIGYAHRPI";
        
        // test case3, output: "PINALSIGYAHRPI"
        String s = "A";
        int numRows = 1;
        String result = "A";
        
        
        _6Solution solution = new _6Solution();
        
        System.out.println(result.equals(solution.convert(s, numRows)));
    }
    
}

/**
 * 本题直接按照题目的意思进行模拟即可。
 * 思路：
 *  （1）创建 numRows 个 builder，一个 builder 对应一行
 *  （2）遍历字符串 s ，首先竖着放进 builders 中，然后斜着放进 builders 中
 *  （3）拼接 builders 中的字符串，得到最终的结果
 *  
 */
class _6Solution {
    
    public String convert(String s, int numRows) {
        if (numRows <= 0) {
            return "";
        }
        
        // 创建 numRows 个 builder，一个 builder 对应一行
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; ++i) {
            builders[i] = new StringBuilder();
        }
        
        // 遍历字符串 s
        int idx = 0;
        while (idx < s.length()) {
            // 逐行放进 builder 中
            for (int i = 0; i < builders.length && idx < s.length(); ++i) {
                builders[i].append(s.charAt(idx++));
            }
            
            // 斜着放进 builder 
            for (int i = builders.length - 2; i > 0 && idx < s.length(); --i) {
                builders[i].append(s.charAt(idx++));
            }
        }
        
        // 将 Z 字型的字符串逐行放进 result 中
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < builders.length; ++i) {
            result.append(builders[i].toString());
        }
        
        return result.toString();
    }
    
}
