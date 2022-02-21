package set;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/happy-number/
 * 
 * 题目描述：编写一个算法来判断一个数 n 是不是快乐数。
 *        「快乐数」 定义为：
 *          （1）对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 *          （2）然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 *          （3）如果这个过程 结果为 1，那么这个数就是快乐数。
 *        如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *        
 * 限制条件：1 <= n <= 2^31 - 1
 * 
 * 示例：
 *  示例 1
 *      输入：n = 19
 *      输出：true
 *      解释：
 *          1^2 + 9^2 = 82
 *          8^2 + 2^2 = 68
 *          6^2 + 8^2 = 100
 *          1^2 + 0^2 + 0^2 = 1
 * 
 *  示例 2
 *      输入：n = 2
 *      输出：false
 *      解释：
 *          2^2 = 4
 *          4^2 = 16
 *          6^2 + 1^2 = 37
 *          7^2 + 3^2 = 58
 *          8^2 + 5^2 = 89
 *          9^2 + 8^2 = 145
 *          5^2 + 4^2 + 1^2 = 42
 *          2^2 + 4^2 = 20
 *          0^2 + 2^2 = 4
 */
public class _202_HappyNumber {

    public static void main(String[] args) {
        // test case1, output: true
//        int n = 19;
        
        // test case2, output: false
        int n = 2;
        
        
        _202Solution solution = new _202Solution();
        
        System.out.println(solution.isHappy(n));
        
    }
}

class _202Solution {
    
    // 返回 n 中每个数字的平方和
    private int sumSquare(int n) {
        int sum = 0;
        
        while (n != 0) {
            int r = n % 10;
            sum = sum + r * r;
            n /= 10;
        }
        
        return sum;
    }
    
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        
        while (n != 1) {
            n = sumSquare(n);
            
            if (set.contains(n)) {
                return false; // 包含 n ，则说明重复了，会进入无限循环，返回 false
            }
            set.add(n);
        }
        
        return true;
    }
}

