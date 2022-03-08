package math;

/**
 * https://leetcode.com/problems/implement-rand10-using-rand7/
 * 
 * 题目描述：给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
 *         你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
 *         每个测试用例将有一个内部参数 n，即你实现的函数 rand10() 在测试时将被调用的次数。请注意，这不是传递给 rand10() 的参数。
 *         
 * 限制条件：1 <= n <= 10^5
 * 
 * 示例：
 *  示例 1
 *      输入: 1
 *      输出: [2]
 *      
 *  示例 2
 *      输入: 2
 *      输出: [2,8]
 *      
 *  示例 3
 *      输入: 3
 *      输出: [3,8,10]
 * 
 */
public class _470_ImplementRand10UsingRand7 {

    public static void main(String[] args) {
        int n = 1;
        
//        _470Solution1 solution = new _470Solution1();

        _470Solution2 solution = new _470Solution2();
        
        for (int i = 0; i < n; ++i) {
            System.out.println(solution.rand10());
        }
    }
}

/**
 * 解法一：(randX() - 1) * Y + randY() 可以均匀生成 1~XY 之间的数字，然后再使用拒绝采样，得到所求随机数。
 */
class _470Solution1 {
    
    private int rand7() {
        return (int)(Math.random() * 6 + 1);
    }
    
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            if (num <= 40) { // 因为所求是 rand10 ，所以需要是 10 的倍数，这样 % 10 之后，每个数的概率是一样的。
                             // 这里的 40 可以是 30、20、10，但是越大越好，因为越小的话，那么拒绝的越多，效率越低。
                return num % 10 + 1;
            }
        }
    }
}


class _470Solution2 {
    
    private int rand7() {
        return (int)(Math.random() * 6 + 1);
    }
    
    public int rand10() {
        // 先生成 1~5
        int a = rand7();
        while (a > 5) {
            a = rand7();
        }
        
        // 然后生成 1~6 
        int b = rand7();
        while (b > 6) {
            b = rand7();
        }
        
        // 当 b 是偶数时，返回值的范围是：[1,5] + 5 * 0 = [1,5]；当 b 是奇数时，返回值的范围是：[1,5] + 5 * 1 = [6,10]。
        return a + 5 * (b % 2);  
    }
}
