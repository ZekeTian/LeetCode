package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * https://leetcode.com/problems/number-of-boomerangs/
 * 题目描述：给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi]。
 *        回旋镖是由点 (i, j, k) 表示的元组，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 *        返回平面上所有回旋镖的数量。
 * 
 * 条件限制：
 * （1）n == points.length
 * （2）1 <= n <= 500
 * （3）points[i].length == 2
 * （4）-10^4 <= xi, yi <= 10^4
 * 
 * 示例：
 *  Input: points = [[0,0],[1,0],[2,0]]
 *  Output: 2
 *  解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 
 */
public class _447_NumberOfBoomerangs {
    public static void main(String[] args) {
        _447Solution solution = new _447Solution();

        //        int[][] points = { { 0, 0 }, { 1, 0 }, { 2, 0 } };
//        int[][] points = { { 1, 1 }, { 2, 2 }, { 3, 3 } };
        int[][] points = { { 1, 1 }};
        
        int result = solution.numberOfBoomerangs(points);
        System.out.println(result);
    }
}

class _447Solution {
    public int numberOfBoomerangs(int[][] points) {
        int result = 0;

        // 计算每对两点之间的距离，并统计各个距离边的数量
        for (int i = 0; i < points.length; ++i) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            Map<Long, Integer> countMap = new HashMap<>(); // key：两点间距离，value：距离为 key 的边的数量

            for (int j = 0; j < points.length; ++j) {
                int x2 = points[j][0];
                int y2 = points[j][1];

                long distance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                Integer count = countMap.getOrDefault(distance, 0);
                countMap.put(distance, count + 1);
            }

            Set<Entry<Long, Integer>> entrySet = countMap.entrySet();
            for (Entry<Long, Integer> entry : entrySet) {
                result = result + entry.getValue() * (entry.getValue() - 1);
            }
        }

        return result;
    }
}