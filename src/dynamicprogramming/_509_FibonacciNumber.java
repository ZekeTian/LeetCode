package dynamicprogramming;

/**
 * https://leetcode.com/problems/fibonacci-number/
 * 
 * 题目描述：斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *          F(0) = 0，F(1) = 1
 *          F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 *        给你 n ，请计算 F(n) 。
 *        
 * 限制条件： 0 <= n <= 30
 * 
 * 示例：
 *  示例 1
 *      输入：2
 *      输出：1
 *      解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 *  
 *  示例 2
 *      输入：3
 *      输出：2
 *      解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 *      
 *  示例 3
 *      输入：4
 *      输出：3
 *      解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 */
public class _509_FibonacciNumber {

    public static void main(String[] args) {
        // test case 1, output: 1
//        int n = 2;
        
        // test case 2, output: 2
//        int n = 3;
        
        // test case 3, output: 3
        int n = 4;
        
        _509Solution1 solution = new _509Solution1();
        
        System.out.println(solution.fib(n));
    }
}

/**
 * 解法一：递归实现
 */
class _509Solution1 {
        
    private int getFib(int n) {
        if (n < 0) {
            return 0;
        }
        
        if (0 == n || 1 == n) {
            return n;
        }
        
        return getFib(n - 1) + getFib(n - 2);
    }
    
    public int fib(int n) {
        return getFib(n);
    }
}

