package map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/max-points-on-a-line/
 *
 * 题目描述：给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * 
 * 限制条件：
 *  （1）1 <= points.length <= 300
 *  （2）points[i].length == 2
 *  （3）-10^4 <= xi, yi <= 10^4
 *  （4）points 中的所有点 互不相同
 *  
 * 示例：
 *  输入：points = [[1,1],[2,2],[3,3]]
 *  输出：3
 *  
 */
public class _149_MaxPointsOnALine {

    public static void main(String[] args) {
        // test case1, output: 3
//        int[][] points = {{ 1, 1 }, { 2, 2 }, { 3, 3 }};
        
        // test case2, output: 3
//        int[][] points = {{ 0, 0 }, { 2, 2 }, { -1, -1 }};
        
        // test case3, output: 3
        int[][] points = {{ 2, 3 }, { 3, 3 }, { -5, 3 }};
        
//        _149Solution1 solution = new _149Solution1();

        _149Solution2 solution = new _149Solution2();
        
        
        System.out.println(solution.maxPoints(points));
    }
}

/**
 * 解法一：使用直线的一般式
 */
class _149Solution1 {
    
    // 求得 a、b、c 的最大公因数
    public int gcd1(int a, int b, int c) {
        a = Math.abs(a);
        b = Math.abs(b);
        c = Math.abs(c);
        int min = Math.min(Math.min(a, b), c);
        int res = 1;
        
        for (int i = 2; i <= min; ++i) {
            if (a % i == 0 && b % i == 0 && c % i == 0) {
                res = i;
            }
        }
        
        return res;
    }
    
    // 求得 a、b、c 的最大公因数
    public int gcd(int a, int b, int c) {
        a = Math.abs(a);
        b = Math.abs(b);
        c = Math.abs(c);
        
        if (a == 1 || b == 1 || c == 1) {
            return 1;
        }
        
        int min = Math.min(Math.min(a, b), c);
        int res = 1;
        
        for (int i = 2; i <= min; ++i) {
            if (a % i == 0 && b % i == 0 && c % i == 0) {
                return i * gcd(a /i, b / i, c / i);
            }
        }
        
        return res;
    }
    
    public int maxPoints(int[][] points) {
        if (null == points || points.length == 0) {
            return 0;
        }
        if (points.length == 1) { // 当 nums 中只有一个顶点时，则最多也只可能有一个顶点在一条直线上
            return 1;
        }
        
        Map<String, Set<int[]>> map = new HashMap<>();
        for (int i = 0; i < points.length - 1; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                // 通过两个点确定一条直线（直线的一般式， a * x + b * y + c = 0）
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int a = y1 - y2;
                int b = x2 - x1;
                int c = x1 * y2 - x2 * y1;
                
                // 消除 a、b、c 的最大公因数，从而得到化简后的结果
                int div = 1;
                if (a != 0 && b != 0 && c != 0) {
                    div = gcd(a, b, c);
                } else if (a == 0) {
                    // 将可能为 0 的数设置成为非零数，从而可以正常调用含有三个参数的 gcd 
                    div = gcd((b == 0 ? c : b), (b == 0 ? c : b), (c == 0 ? b : c));
                } else if (b == 0) {
                    div = gcd((a == 0 ? c : a), (a == 0 ? c : a), (c == 0 ? a : c));
                } else if (c == 0) {
                    div = gcd((a == 0 ? b : a), (b == 0 ? a : b), (a == 0 ? b : a));
                }
                a /= div;
                b /= div;
                c /= div;
                
                
                // 直线一般式中的 a、b、c 三个参数可以唯一确定该直线，所以可以将其作为 key
                String key = a + "-" + b + "-" + c;
                Set<int[]> set = map.getOrDefault(key, new HashSet<>());
                set.add(points[i]);
                set.add(points[j]);
                map.put(key, set);
            }
        }
        
        // 计算出一条直线上最多的顶点数
        int max = 0;
        Set<String> keys = map.keySet();
        for (String k : keys) {
            max = Math.max(max, map.get(k).size());
        }
        
        return max;
    }
}

/**
 * 解法二：使用直线的点斜式
 */
class _149Solution2 {
    
    public int gcd(int a, int b) {
        return (b == 0 ? a : gcd(b, a % b));
    }
    
    public int maxPoints(int[][] points) {
        if (null == points || 0 == points.length) {
            return 0;
        }
        
        int res = 1; // 默认是 1，因为至少有一个点
        for (int i = 0; i < points.length - 1; ++i) {
            // 统计过点 points[i] 的直线上含有的点数量
            int max = 0; // 直线上含有的最多的顶点数量
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; ++j) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                
                int deltaX = x1 - x2;
                int deltaY = y1 - y2;
                
                // 获取 deltaX 和 deltaY 的最大公因数，然后进行化简
                int div = gcd(deltaX, deltaY);
                deltaX /= div;
                deltaY /= div;
                
                // 求得“斜率”，并将其作为 key。但是需要注意，不能直接求解斜率，原因：（1）斜率可能不存在 （2）除法得到的浮点数无法准确存储
                // 因此，此处用字符串表示 key
                String key = deltaY + "/" + deltaX;
                Integer count = map.getOrDefault(key, 0);
                map.put(key, count + 1);
                
                max = Math.max(max, count + 1);
            }
            
            res = Math.max(res, max + 1); // max + 1 是因为还要包含 points[i] 这一个点
        }
        
        return res; 
    }
}