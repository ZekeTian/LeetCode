package math;

/**
 * https://leetcode.com/problems/nth-digit/
 * 
 * 题目描述：给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位上的数字。
 * 
 * 限制条件：1 <= n <= 2^31 - 1
 * 
 * 示例：
 *  示例 1
 *      输入：n = 3
 *      输出：3
 *      
 *  示例 2
 *      输入：n = 11
 *      输出：0
 *      解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *      
 */
public class _400_NthDigit {

    public static void main(String[] args) {
        // test case1, output: 3
//        int n = 3;
        
        // test case2, output: 0
        int n = 11;
        
        _400Solution solution = new _400Solution();
        
        
        System.out.println(solution.findNthDigit(n));
    }
}

/**
 * 和剑指 offer 的第 44 题一样，利用数学规律求解。
 */
class _400Solution {
    
    public int findNthDigit(int n) {
        int numLen = 1; // 整数长度
        long start = 1; // 长度为 numLen 的起始整数
        long count = 9; // 长度为 numLen 的整数的个数
        
        while (n > count) {
            n -= count;
            ++numLen;
            start *= 10;
            count = 9 * start * numLen; // 长度为 numLen 的整数，组成的数字序列包含的数字个数
        }
        
        long num = start + (n - 1) / numLen; // 第 n 位数字所属的整数
        
        return (num + "").charAt((n - 1) % numLen) - '0';
    }
    
}
