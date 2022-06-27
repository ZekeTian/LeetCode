package math;

/**
 * https://leetcode.com/problems/convert-a-number-to-hexadecimal/
 * 
 * 题目描述：给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 * 
 * 限制条件：
 *  （1）十六进制中所有字母(a-f)都必须是小写。
 *  （2）十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；
 *      对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
 *  （3）给定的数确保在32位有符号整数范围内。
 *  （4）不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 *  
 * 示例：
 *  示例 1
 *      输入: 26
 *      输出: "1a"
 *  
 *  示例 2
 *      输入: -1
 *      输出: "ffffffff"
 *
 */
public class _405_ConvertANumberToHexadecimal {

    public static void main(String[] args) {
        // test case1, output: "1a"
//        int num = 26;
        
        // test case2, output: "ffffffff"
        int num = -1;
        
//        _405Solution1 solution = new _405Solution1();
        
//        _405Solution2 solution = new _405Solution2();
        
        _405Solution3 solution = new _405Solution3();
        
        
        System.out.println(solution.toHex(num));
    }
    
}

/**
 * 解法一：直接调用 Integer.toHexString();
 */
class _405Solution1 {
    
    public String toHex(int num) {
        return Integer.toHexString(num);
    }
    
}

/**
 * 解法二：遍历 num 的二进制串，然后四个一组形成一个十六进制数
 */
class _405Solution2 {
    
    public String toHex(int num) {
        // 获得 num 对应的二进制串
        String bits = Integer.toBinaryString(num);
        
        // 遍历 bits，四个一组形成一个十六进制数
        StringBuilder builder = new StringBuilder(); // 存储最终的结果
        int i = bits.length() - 1; // 在 bits 中，从后向前遍历
        while (i >= 0) {
            // 一次遍历四个
            int a = 0; // 四个二进制位对应的整数
            int b = 1; // 第 j 位对应的值 2^j 
            for (int j = 0; j < 4 && i >= 0; ++j, --i) {
                if (bits.charAt(i) == '1') {
                    a += b;
                }
                b *= 2;
            }
            if (a <= 9) {
                builder.append(a); // 如果 a <= 9，则直接将数字形式存储进去即可
            } else {
                builder.append((char) ('a' + a - 10)); // 如果 a > 9，则需要将 a 转换成对应的字母形式
            }
        }

        return builder.reverse().toString();
    }
    
}

/**
 * 解法三：核心思想和解法二一样，将 num 中四个二进制位作为一组，然后形成一个十六进制数。
 *       只不过在具体实现时，解法三是使用位运算实现。
 */
class _405Solution3 {
    
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        while (num != 0) {
            int a = (num & 15); // 取出 num 中最右边的 4 位二进制。因为只取 4 位，所以和 1111（即十进制的 15）进行 “&” 操作即可。
            if (a <= 9) {
                builder.append(a);
            } else {
                builder.append((char) ('a' + a - 10));
            }
            
            num >>>= 4; // 向右移 4 位，即丢弃已经计算的 4 位
        }
        
        return builder.reverse().toString();
    }
    
}


