package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/diagonal-traverse/
 * 
 * 题目描述：给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * 
 * 限制条件：
 *  （1）m == mat.length
 *  （2）n == mat[i].length
 *  （3）1 <= m, n <= 10^4
 *  （4）1 <= m * n <= 10^4
 *  （5）-10^5 <= mat[i][j] <= 10^5
 *  
 * 示例：
 *          1   2   3
 *          4   5   6
 *          7   8   9
 *  输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 *  输出：[1,2,4,7,5,3,6,8,9]
 *
 */
public class _498_DiagonalTraverse {

    public static void main(String[] args) {
        int[][] mat = { { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 } };
        
        
        _498Solution1 solution = new _498Solution1();
        int[] res = solution.findDiagonalOrder(mat);
        
        System.out.println(Arrays.toString(res));
    }
}

/**
 * 解法一：利用对角线之和，确定每条对角线，然后求得对角线上的各个数字。
 */
class _498Solution1 {
    
    private int m = 0, n = 0;
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
    
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat[0].length == 0) {
            return new int[] {};
        }
        
        this.m = mat.length;
        this.n = mat[0].length;
        int[] res = new int[m * n];
        int idx = 0;
        int count = m + n - 2; // 对角线之和的最大值
        
        for (int i = 0; i <= count; ++i) {
            if (i % 2 == 0) { // 斜向上方向行走
                for (int y = 0; y < n; ++y) {
                    if (inArea(i - y, y)) {
                        res[idx++] = mat[i - y][y];
                    }
                }
            } else { // 斜向下方向行走
                for (int x = 0; x < m; ++x) {
                    if (inArea(x, i - x)) {
                        res[idx++] = mat[x][i - x];
                    }
                }
            }
        }
        
        return res;
    }
    
}
