package array;

/**
 * https://leetcode.com/problems/add-strings/
 * 
 * 题目描述：给定两个用字符串表示的非负整数 num1 和 num2，返回 num1、num2 之和（用字符串表示）。
 *        不能使用处理大数的内置库，如 BigInteger，也不能将字符串转换成整数。
 *  
 * 条件限制：
 *  （1）1 <= num1.length, num2.length <= 10^4
 *  （2）num1、num2 只包含整数
 *  （3）num1、num2 除 0 之外，最前面不会含有 0，即不会出现  00123 这种形式
 *  
 * 示例：
 *  Input: num1 = "11", num2 = "123"
 *  Output:  "134"
 *
 */
public class _415_AddStrings {

    public static void main(String[] args) {
        // test case1, output: 134
//        String num1 = "11";
//        String num2 = "123";
        
        // test case2, output: 0
//        String num1 = "0";
//        String num2 = "0";
        
        
        // test case3, output: 10398
        String num1 = "999";
        String num2 = "9399";
        
        _415Solution1 solution = new _415Solution1();
        
        System.out.println(solution.addStrings(num1, num2));
    }
}

/**
 * 解法一：使用一个指针模拟竖式相加
 */
class _415Solution1 {
    public String addStrings(String num1, String num2) {
        String shortNum = null;
        String longNum = null;
        
        if (num1.length() < num2.length()) {
            shortNum = num1;
            longNum = num2;
        } else {
            shortNum = num2;
            longNum = num1;
        }
        
        int idx = 0;
        int slen = shortNum.length(); // 较小数的长度
        int llen = longNum.length(); // 较大数的长度
        boolean flag = false; // 标记是否进位
        StringBuilder builder = new StringBuilder();
        
        for (idx = 0; idx < slen; ++idx) {
            int s = shortNum.charAt(slen - 1 - idx) - '0';
            int l = longNum.charAt(llen - 1 - idx) - '0';
            
            int sum = (flag) ? (s + l + 1): (s + l);
            
            if (sum <= 9) {
                flag = false;
                builder.insert(0, sum);
            } else {
                flag = true;
                builder.insert(0, (sum - 10));
            }
        }
        
        for (idx = idx; idx < llen; ++idx) {
            int l = longNum.charAt(llen - 1 - idx) - '0';
            int sum = (flag) ? (l + 1) : l;
            
            if (sum <= 9) {
                flag = false;
                builder.insert(0, sum);
            } else {
                flag = true;
                builder.insert(0, (sum - 10));
            }
        }
        
        if (flag) { // 最终还需要进一位，因为最多进 1 ，所以在前面补 1 即可
            builder.insert(0, 1);
        }
        
        return builder.toString();
    }
}
