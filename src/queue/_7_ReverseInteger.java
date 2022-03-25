package queue;

import java.util.Collections;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/reverse-integer/
 * 
 * 题目描述：给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *         如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *         假设环境不允许存储 64 位整数（有符号或无符号）。
 * 
 * 限制条件：-2^31 <= x <= 2^31 - 1
 * 
 * 示例：
 *  示例 1
 *      输入：x = 123
 *      输出：321
 *      
 *  示例 2
 *      输入：x = -123
 *      输出：-321
 *      
 *  示例 3
 *      输入：x = 120
 *      输出：21
 *      
 *  示例 4
 *      输入：x = 0
 *      输出：0
 *
 */
public class _7_ReverseInteger {

    public static void main(String[] args) {
        // test case1, output: 321
//        int x = 123;
        
        // test case2, output: -321
//        int x = -123;
        
        // test case3, output: 21
//        int x = 120;
        
        // test case4, output: 0
//        int x = 0;
        
        // test case5, output: 0
//        int x = Integer.MIN_VALUE;
        
        // test case6, output: 0
        int x = Integer.MAX_VALUE;
        
//        _7Solution1 solution = new _7Solution1();
        
//        _7Solution2 solution = new _7Solution2();
        
        _7Solution3 solution = new _7Solution3();
        
        
        System.out.println(solution.reverse(x));
    }
}

/**
 * 解法一：转换字符串，然后反转字符串
 */
class _7Solution1 {
    
    public int reverse(int x) {
        String reverseNumStr = new StringBuilder(x + "").reverse().toString();
        if (x < 0) {
            reverseNumStr = reverseNumStr.substring(0, reverseNumStr.length() - 1); // 去掉负号
        }
        long reverseNum = (x < 0 ? -1 : 1) * Long.parseLong(reverseNumStr); // 为避免反转后溢出，使用 Long。同时，在前面加上正负号
        if (reverseNum > Integer.MAX_VALUE || reverseNum < Integer.MIN_VALUE) {
            return 0;
        }
        
        return (int) reverseNum;
    }
}

/**
 * 解法二：使用队列 
 */
class _7Solution2 {
    
    // 获取 num 对应的队列（队列的顺序即为 num 反转后的顺序）
    private LinkedList<Integer> getQueue(int num) {
        LinkedList<Integer> queue = new LinkedList<>();
        if (num == 0) {
            queue.add(0);
        }
        
        while (num != 0) {
            queue.addLast(num % 10);
            num /= 10;
        }
        
        return queue;
    }
    
    // num1 和 num2 的长度一样时，比较 num1 和 num2，如果 num1 大于 num2，则返回 true
    private boolean isGreater(LinkedList<Integer> num1, LinkedList<Integer> num2) {
        num1 = new LinkedList<>(num1); // 复制一份，避免修改原始的 num
        num2 = new LinkedList<>(num2);
        
        while (!num1.isEmpty() && !num2.isEmpty()) {
            int n1 = num1.poll();
            int n2 = num2.poll();
            if (n1 < n2) {
                return false;
            } else if (n1 > n2) {
                return true;
            }
        }
        
        return false; // num1 和 num2 相等
    }
    
    // num1 和 num2 的长度一样时，比较 num1 和 num2，如果 num1 小于 num2，则返回 true
    private boolean isLess(LinkedList<Integer> num1, LinkedList<Integer> num2) {
        num1 = new LinkedList<>(num1);
        num2 = new LinkedList<>(num2);
        
        while (!num1.isEmpty() && !num2.isEmpty()) {
            int n1 = num1.poll();
            int n2 = num2.poll();
            if (n1 < n2) {
                return true;
            } else if (n1 > n2) {
                return false;
            }
        }
        
        return false;
    }
    
    public int reverse(int x) {
        LinkedList<Integer> maxQueue = getQueue(Integer.MAX_VALUE); // int 类型数据的最大值对应的队列 
        LinkedList<Integer> minQueue = getQueue(Integer.MIN_VALUE); // int 类型数据的最小值对应的队列
        LinkedList<Integer> numQueue = getQueue(x);
        // 因为最大值、最小值不需要反转，所以需要将 maxQueue、minQueue 中的顺序调整回去
        Collections.reverse(minQueue);
        Collections.reverse(maxQueue);
        
        // 判断反转后的 num 是否比最大值大
        if (x > 0 && numQueue.size() == maxQueue.size() && isGreater(numQueue, maxQueue)) {
            return 0; // 反转后，比最大值大，则返回 0
        }
        
        if (x < 0 && numQueue.size() == minQueue.size() && isLess(numQueue, minQueue)) {
            return 0; // 反转后，比最小值小，则返回 0
        }
        
        // x 反转后，在 int 的范围内，则计算出具体的数字
        int num = 0;
        while (!numQueue.isEmpty()) {
            num = num * 10 + numQueue.poll();
        }
        
        return num;
    }
}

/**
 * 解法三：不使用额外存储空间，直接用循环解决
 */
class _7Solution3 {
    
    public int reverse(int x) {
        int reverseNum = 0;
        
        while (x != 0) {
            if (reverseNum > Integer.MAX_VALUE / 10 || reverseNum < Integer.MIN_VALUE / 10) {
                return 0; // 反转后溢出，则返回 0
            }
            
            // 从 x 中提取最后一位数字 r
            int r = x % 10;
            x /= 10;
            
            // 将 r 添加到 reverseNum 中
            reverseNum = reverseNum * 10 + r;
        }
        
        return reverseNum;
    }
}
