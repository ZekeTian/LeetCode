package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/triangle/
 * 
 * 题目描述：给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *        每一步只能移动到下一行中相邻的结点上。相邻的结点在这里指的是下标与上一层结点下标 相同或者等于上一层结点下标 +1 的两个结点。
 *        也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *        
 * 限制条件：
 *  （1）1 <= triangle.length <= 200
 *  （2）triangle[0].length == 1
 *  （3）triangle[i].length == triangle[i - 1].length + 1
 *  （4）-104 <= triangle[i][j] <= 104
 * 
 * 示例：
 *      2
 *     3 4
 *    6 5 7
 *   4 1 8 3
 *  输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 *  输出：11
 *  解释：自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 */
public class _120_Triangle {

    public static void main(String[] args) {
        // test case, [[2],[3,4],[6,5,7],[4,1,8,3]], output: 11
        List<List<Integer>> triangle = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            triangle.add(new ArrayList<>());
        }
        triangle.get(0).add(2);
        triangle.get(1).add(3);
        triangle.get(1).add(4);
        triangle.get(2).add(6);
        triangle.get(2).add(5);
        triangle.get(2).add(7);
        triangle.get(3).add(4);
        triangle.get(3).add(1);
        triangle.get(3).add(8);
        triangle.get(3).add(3);
        
//        _120Solution1 solution = new _120Solution1();
//        _120Solution2 solution = new _120Solution2();
        _120Solution3 solution = new _120Solution3();
        
        System.out.println(solution.minimumTotal(triangle));
    }
}

/**
 * 解法一：递归
 */
class _120Solution1 {
    
    private List<List<Integer>> triangel = null;
    
    private int minimum(int row, int col) {
        if (row == triangel.size() - 1) {
            return triangel.get(row).get(col);
        }
        
        return triangel.get(row).get(col) + Math.min(minimum(row + 1, col), minimum(row + 1, col + 1));
    }
    
    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangel = triangle;
        return minimum(0, 0);
    }
}

/**
 * 解法二：递归 + 记忆化
 */
class _120Solution2 {
    
    private List<List<Integer>> triangle = null;
    private int[][] memo = null;
    
    private int minimum(int row, int col) {
        if (row == triangle.size() - 1) {
            memo[row][col] = triangle.get(row).get(col);
            return memo[row][col];
        }
        
        if (Integer.MIN_VALUE != memo[row][col]) {
            return memo[row][col];
        }
        
        memo[row][col] = triangle.get(row).get(col) + Math.min(minimum(row + 1, col), minimum(row + 1, col + 1));
        
        return memo[row][col];
    }
    
    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        this.memo = new int[triangle.size()][triangle.size()];
        
        for (int i = 0; i < memo.length; ++i) {
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        }
        
        
        minimum(0, 0);
        return memo[0][0];
    }
}

/**
 * 解法三：自底向上动态规划
 */
class _120Solution3 {
    
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[][] memo = new int[row][row];
        
        for (int i = 0; i < row; ++i) {
            memo[row - 1][i] = triangle.get(row - 1).get(i); // 初始化最后一行的结果
        }
        
        for (int i = row - 2; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                memo[i][j] = triangle.get(i).get(j) + Math.min(memo[i + 1][j], memo[i + 1][j + 1]);
            }
        }
        
        return memo[0][0];
    }
}

