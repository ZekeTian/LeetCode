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
        
        
        _264Solution1 solution = new _264Solution1();
        
        
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



