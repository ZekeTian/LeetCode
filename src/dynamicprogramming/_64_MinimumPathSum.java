package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * 
 * 题目描述：给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *        说明：每次只能向下或者向右移动一步。
 * 
 * 
 * 限制条件：
 *  （1）m == grid.length 
 *  （2）n == grid[i].length
 *  （3）1 <= m, n <= 200
 *  （4）0 <= grid[i][j] <= 100
 * 
 * 示例：
 *      1 3 1
 *      1 5 1
 *      4 2 1
 *  输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 *  输出：7
 *  解释：因为路径 1→3→1→1→1 的总和最小。
 */
public class _64_MinimumPathSum {

    public static void main(String[] args) {
        // test case, output: 7
        int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        
//        _64Solution1 solution = new _64Solution1();
        _64Solution2 solution = new _64Solution2();
        
        System.out.println(solution.minPathSum(grid));
    }
}

/**
 * 解法一：使用递归
 */
class _64Solution1 {
    
    private int[][] grid = null; 
    private int row = 0;
    private int col = 0;
    
    private int getMinPathSum(int r, int c) {
        if (r == row - 1 && c == col - 1) {
            return grid[r][c];
        }
        
        if (c == col - 1) {
            return grid[r][c] + getMinPathSum(r + 1, c); // 最后一列，只能向下走
        }
        
        if (r == row - 1) {
            return grid[r][c] + getMinPathSum(r, c + 1); // 最后一行，只能向右走
        }
        
        return grid[r][c] + Math.min(getMinPathSum(r + 1, c), getMinPathSum(r, c + 1));
    }
    
    public int minPathSum(int[][] grid) {
        this.grid = grid;
        row = grid.length;
        col = grid[0].length;
        
        return getMinPathSum(0, 0);
    }
}


/**
 * 解法二：递归 + 记忆化
 */
class _64Solution2 {
    private int[][] grid = null;
    private int[][] memo = null;
    private int row = 0;
    private int col = 0;
    
    private int getMinPathSum(int r, int c) {
        if (r == row - 1 && c == col - 1) {
            memo[r][c] = grid[r][c];
            return grid[r][c];
        }
        
        if (-1 != memo[r][c]) {
            return memo[r][c]; // 已经计算，则直接返回
        }
        
        if (c == col - 1) {
            memo[r][c] = grid[r][c] + getMinPathSum(r + 1, c); // 最后一列，向下走
            return memo[r][c];
        }
        
        if (r == row - 1) {
            memo[r][c] = grid[r][c] + getMinPathSum(r, c + 1); // 最后一行，向右走
            return memo[r][c];
        }
        
        memo[r][c] = grid[r][c] + Math.min(getMinPathSum(r + 1, c), getMinPathSum(r, c + 1));
        return memo[r][c];
    }
    
    public int minPathSum(int[][] grid) {
        this.grid = grid;
        this.row = grid.length;
        this.col = grid[0].length;
        this.memo = new int[row][col];
        
        for (int i = 0; i < row; ++i) {
            Arrays.fill(memo[i], -1);
        }
        
        getMinPathSum(0, 0);
        
        return memo[0][0];
    }
}
