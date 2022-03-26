package recursion;

/**
 * https://leetcode.com/problems/powx-n/
 * 
 * 题目描述：实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）
 * 
 * 限制条件：
 *  （1）-100.0 < x < 100.0
 *  （2）-2^31 <= n <= 2^31-1
 *  （3）-10^4 <= x^n <= 10^4
 * 
 * 示例
 *  示例 1
 *      输入：x = 2.00000, n = 10
 *      输出：1024.00000
 *  
 *  示例 2
 *      输入：x = 2.10000, n = 3
 *      输出：9.26100
 *  
 *  示例 3
 *      输入：x = 2.00000, n = -2
 *      输出：0.25000
 *      解释：2-2 = 1/22 = 1/4 = 0.25
 *
 */
public class _50_Pow {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // test case1, output: 1024.00000
//        double x = 2.0;
//        int n = 10;
        
        // test case2, output: 9.26100
//        double x = 2.1;
//        int n = 3;
        
        // test case3, output: 0.25000
        double x = 2.0;
        int n = -2;
        
        
        _50Solution solution = new _50Solution();
        
        
        System.out.println(solution.myPow(x, n));
        
    }

}

class _50Solution {
    
    private double pow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        
        double res = pow(x, n / 2);
        if (n % 2 == 0) {
            return res * res;
        }
        
        return x * res * res; // n 为奇数，则需要先提取一个 x 出来，然后再对 res 求平方
    }
    
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1.0 / pow(x, -n); // n 为负数时，则将其转换成正数，然后再计算
        }
        
        return pow(x, n);
    }
}

