package bit;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 * 
 * 题目描述：编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 *         （1）请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，
 *             并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 *         （2）在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *         
 * 进阶：如果多次调用这个函数，你将如何优化你的算法？
 * 
 * 限制条件：输入必须是长度为 32 的 二进制串
 * 
 * 示例：
 *  示例 1
 *      输入：00000000000000000000000000001011
 *      输出：3
 *      解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *      
 *  示例 2
 *      输入：00000000000000000000000010000000
 *      输出：1
 *      解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 *      
 *  示例 3
 *      输入：11111111111111111111111111111101
 *      输出：31
 *      解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *      
 */
public class _191NumberOf1Bits {

    public static void main(String[] args) {
        // test case1, output: 3
//        int n = Integer.parseUnsignedInt("00000000000000000000000000001011", 2);
        
        // test case2, output: 1
//        int n = Integer.parseUnsignedInt("00000000000000000000000010000000", 2);
        
        // test case3, output: 31
        int n = Integer.parseUnsignedInt("11111111111111111111111111111101", 2);
        
        
//        _191Solution1 solution = new _191Solution1();
        
        _191Solution2 solution = new _191Solution2();

        
        System.out.println(solution.hammingWeight(n));
    }
}

/**
 * 解法一：将数字转换成二进制位的字符串，然后数 1 的个数
 */
class _191Solution1 {
    
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        String bits = Integer.toBinaryString(n);
        
        int count = 0;
        for (int i = 0; i < bits.length(); ++i) {
            if (bits.charAt(i) == '1') {
                ++count;
            }
        }
        
        return count;
    }
}

/**
 * 解法二：使用位运算
 *       每次只看数字对应的二进制位串的最后一位，具体做法：num & 1。
 *       如果 num 对应的二进制位串的最后一位是 1，则 num & 1 = 1，以此计算 1 的个数。
 *       统计了一位之后，再将 num 向右移一位，从而继续统计下一位。
 *       不过，需要注意的是，因为题目中是无符号整数，所以右移需要使用 >>>（无符号右移），而不是 >>
 */
class _191Solution2 {
    
 // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                ++count; // 判断在 n 对应的二进制位串中，最后一位是否为 1
            }
            n >>>= 1; // 将 n 右移一位，从而将二进制位串中的最后一位丢弃
        }
        
        return count;
    }
}
