package dynamicprogramming;

import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/ugly-number-ii/
 * 
 * 题目描述：给你一个整数 n ，请你找出并返回第 n 个 丑数 。
 *         丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 *         
 * 限制条件：1 <= n <= 1690
 *  
 * 示例：
 *  示例 1
 *      输入：n = 10
 *      输出：12
 *      解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
 *      
 *  示例 2
 *      输入：n = 1
 *      输出：1
 *      解释：1 通常被视为丑数。
 *
 */
public class _264_UglyNumberII {

    public static void main(String[] args) {
        // test case1, output: 12
//        int n = 10;
        
        // test case2, output: 1
        int n = 1;
        
        
//        _264Solution1 solution = new _264Solution1();
        
        _264Solution2 solution = new _264Solution2();
        
        
        System.out.println(solution.nthUglyNumber(n));
        
    }
    
}

/**
 * 解法一：使用 TreeSet 
 *       后面的丑数实际上可以由前面的丑数生成，例如：
 *          初始时只有 1，然后乘以 2、3、5，得到 1、[2、3、5]。其中，[2、3、5] 表示未处理的数字；
 *          然后根据 2 再扩展，即 2 乘以 2、3、5，得到 1、2、[3、4、5、6、10]。其中，[3、4、5、6、10] 表示未处理的数字；
 *          然后根据 3 再扩展，即 3 乘以 2、3、5，得到 1、2、3、[4、5、6、9、10、15]。其中，[4、5、6、9、10、15] 表示未处理的数字；
 *          ...
 *       从上面的过程可知，每次都是从未处理的数字中取出第一个数字（即最小的数字），然后进行扩展。
 *       但是，需要额外注意的是，列表是有序、无重复的，因此可以考虑使用 TreeSet 存储。
 */
class _264Solution1 {
    public int nthUglyNumber(int n) {
        Set<Long> set = new TreeSet<>(); // 使用 TreeSet ，一方面确保无重复，另一方面方便取最小的数字进行扩展。
        set.add(1L);
        
        long num = 0;
        for (int i = 0; i < n; ++i) {
            num = set.iterator().next(); // 取出最小的数字，然后进行扩展
            // 由 num 扩展 set。因为在进行乘法运算时，中间的结果可能会越界，所以在前面将 set 定义成 long 类型，避免越界。
            set.add(num * 2);
            set.add(num * 3);
            set.add(num * 5);
            
            set.remove(num); // 因为 num 已经处理过，所以需要将其删除
        }
        
        return (int) num;
    }
}


/**
 * 解法二：动态规划（三指针）
 *        创建一个 memo 数组，其中 memo[i] 表示第 i 个丑数。 
 *        memo 数组中，每个数字都可以乘以 2、3、5。为了区分哪些数字已经乘以了 2、3、5，哪些数字没有乘以 2、3、5，
 *        我们使用三个指针 p2, p3, p5 分别表示。
 *          memo[1 ... p2-1] 表示已经乘以了 2
 *          memo[1 ... p3-1] 表示已经乘以了 3
 *          memo[1 ... p5-1] 表示已经乘以了 5
 *        每次生成一个新的丑数时，都用 memo[p2] * 2，memo[p3] * 3，memo[p5] * 5，然后取三个数字中的最小值作为新的丑数。
 *        之后，再根据新的丑数更新 p2、p3、p5 的位置。
 *        
 *        实际上，该过程也相当于把 memo 数组复制成三个，然后 p2、p3、p5 各自负责遍历一个数组，然后再将三个数组中当前值的最小值
 *        添加到 memo 中。整体的过程类似于归并排序。
 */
class _264Solution2 {
    
    public int nthUglyNumber(int n) {
        int[] memo = new int[n + 1]; // memo[i] 表示第 i 个丑数
        memo[1] = 1;
        
        int p2 = 1; // memo[1 ... p2-1] 表示已经乘以了 2
        int p3 = 1; // memo[1 ... p3-1] 表示已经乘以了 3
        int p5 = 1; // memo[1 ... p5-1] 表示已经乘以了 5
        
        // 逐个生成丑数
        for (int i = 2; i <= n; ++i) {
            // p2、p3、p5 对应的数字分别乘以 2、3、5，得到新的数字
            int num2 = memo[p2] * 2;
            int num3 = memo[p3] * 3;
            int num5 = memo[p5] * 5;
            
            // 从新数字 num2、num3、num5 中选择最小的数字作为第 i 个丑数
            int num = Math.min(num2, Math.min(num3, num5));
            memo[i] = num;
            
            // 更新 p2、p3、p5 的位置
            if (num == num2) {
                ++p2; // 由 memo[p2] 可以得到 num，则需要更新 p2
            }
            if (num == num3) {
                ++p3;
            }
            if (num == num5) {
                ++p5;
            }
        }
        
        return memo[n];
    }
    
}

