package array;

/**
 * https://leetcode-cn.com/problems/sqrtx/
 *
 * 题目描述：实现 int sqrt(int x) 函数。计算并返回 x 的平方根，其中 x 是非负整数。由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 
 * 示例：
 *  示例 1：
 *    Input: 4
 *    Output: 2 
 *  
 *  示例 2：
 *    Input: 8
 *    Output: 2
 *    解释：8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 */
public class _69_Sqrtx {

    public static void main(String[] args) {
        // test case1, output: 2
        //        int x = 4;

        // test case2, output: 2
        int x = 8;

//        _69Solution1 solution = new _69Solution1();
        _69Solution2 solution = new _69Solution2();

        System.out.println(solution.mySqrt(x));
    }
}

/**
 * 解法一：使用 Math.sqrt() 
 */
class _69Solution1 {
    public int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }
}

/**
 * 解法二：使用二分查找的思想解决。
 *      此题相当于是在 0, 1, 2, 3, ..., x-1, x 的数组中寻找一个数，该数的平方小于 x ，这个数是最后一个满足条件的数。再往后面一个，数字的平方会大于 x。
 */
class _69Solution2 {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int mid = 0;
        int res = 0;

        while (left <= right) {
            mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            if (square == x) {
                return mid;
            }

            if (square < x) {
                left = mid + 1;
                res = mid; // 记录每一个小于 x 的 mid，当循环结束时，res 即为最后一个满足 res*res < x 的数
            } else {
                right = mid - 1;
            }
        }

        return res;
    }
}
