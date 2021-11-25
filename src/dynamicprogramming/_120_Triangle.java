package dynamicprogramming;

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

