package string;

/**
 * https://leetcode.com/problems/multiply-strings/
 * 
 * 题目描述：给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *        注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *        
 * 限制条件：
 *  （1）1 <= num1.length, num2.length <= 200
 *  （2）num1 和 num2 只能由数字组成。
 *  （3）num1 和 num2 都不包含任何前导零，除了数字0本身。
 *
 * 示例：
 *  示例 1
 *      输入: num1 = "2", num2 = "3"
 *      输出: "6"
 *      
 *  示例 2
 *      输入: num1 = "123", num2 = "456"
 *      输出: "56088"
 * 
 */
public class _43_MultiplyStrings {

    public static void main(String[] args) {
        // test case1, outptu: 56088
//        String num1 = "123", num2 = "456";
        
        // test case2, outptu: 0
//        String num1 = "0", num2 = "456";
        
        // test case3, outptu: 2040
        String num1 = "5", num2 = "408";
        
        _43Solution solution = new _43Solution();
        
        System.out.println(solution.multiply(num1, num2));
    }
}

class _43Solution {
    
    private String add(String str1, String str2) {
        int i = 0, j = 0;
        
        StringBuilder builder = new StringBuilder();
        int carray = 0;
        while (i < str1.length() || j < str2.length()) {
            int num1 = 0, num2 = 0;
            if (i < str1.length()) {
                num1 = str1.charAt(i) - '0';
                ++i;
            }
            if (j < str2.length()) {
                num2 = str2.charAt(j) - '0';
                ++j;
            }
            int sum = num1 + num2 + carray;
            builder.append(sum % 10);
            carray = sum / 10;
        }
        
        if (carray != 0) {
            builder.append(carray);
        }
        
        return builder.toString();
    }
    
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || "0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        
        // 模拟竖式相乘
        String res = "";
        for (int i = num1.length() - 1; i >= 0; --i) {
            StringBuilder builder = new StringBuilder(); // 存储当前结果
            int carray = 0;
            
            // 为当前结果补 0，从而保证当前结果和上一次的结果是对齐的，从而便于后面相加
            for (int j = 0; j < num1.length() - 1 - i; ++j) {
                builder.append("0");
            }
            // 计算 num1 当前位与 num2 的乘积
            for (int j = num2.length() - 1; j >= 0; --j) {
                int a = num1.charAt(i) - '0';
                int b = num2.charAt(j) - '0';
                int c = a * b + carray;
                builder.append(c % 10);
                carray = c / 10;
            }
            
            if (carray != 0) {
                builder.append(carray);
            }
            
            // 将之前的结果和当前计算的结果累加
            res = add(res, builder.toString());
        }
        
        return new StringBuilder(res).reverse().toString();
    }
}