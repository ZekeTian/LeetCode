package math;

/**
 * https://leetcode.com/problems/count-primes/
 * 
 * 题目描述：给定整数 n ，返回所有小于非负整数 n 的质数的数量。
 * 
 * 限制条件：0 <= n <= 5 * 10^6
 * 
 * 示例：
 *  示例 1
 *      输入：n = 10
 *      输出：4
 *      解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *      
 *  示例 2
 *      输入：n = 0
 *      输出：0
 *    
 *  示例 3
 *      输入：n = 1
 *      输出：0
 * 
 */
public class _204_CountPrimes {

    public static void main(String[] args) {
        // test case1, output: 4
//        int n = 10;
        
        // test case2, output: 0
//        int n = 0;
        
        // test case3, output: 0
        int n = 1;
        
        _204Solution1 solution = new _204Solution1();
        
        
        System.out.println(solution.countPrimes(n));
        
    }
    
}


/**
 * 解法一；直接枚举
 */
class _204Solution1 {
    
    // 判断 n 是否为素数
    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime(i)) {
                ++count;
            }
        }

        return count;
    }
    
}