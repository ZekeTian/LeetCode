package backtrack;

/**
 * https://leetcode.com/problems/max-area-of-island/
 * 
 * 题目描述：给你一个大小为 m x n 的二进制矩阵 grid 。
 *         岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
 *         你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *         岛屿的面积是岛上值为 1 的单元格的数目。
 *         计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *         
 * 限制条件：
 *  （1）m == grid.length
 *  （2）n == grid[i].length
 *  （3）1 <= m, n <= 50
 *  （4）grid[i][j] 为 0 或 1
 *  
 * 
 * 示例：
 *  示例 1
 *      输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *                   [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *                   [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *                   [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *                   [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *                   [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *                   [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *                   [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *      输出：6
 *      解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 *  
 *  示例 2
 *      输入：grid = [[0,0,0,0,0,0,0,0]]
 *      输出：0
 *      
 */

public class _695_MaxAreaOfIsland{
    
    public static void main(String[] args) {
        // test case1, output: 6
//        int[][] grid = { {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
//                         {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, 
//                         {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, 
//                         {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, 
//                         {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, 
//                         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, 
//                         {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, 
//                         {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        
        // test case2, output: 0
        int[][] grid = { { 0, 0, 0, 0, 0, 0, 0, 0 } };
        
        _695Solution solution = new _695Solution();
        
        
        System.out.println(solution.maxAreaOfIsland(grid));
    }
}


class _695Solution {
    
    private int[][] grid = null;
    private boolean[][] visited = null;
    private int m = 0, n = 0;
    private int[][] moves = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
    
    private int countArea(int x, int y, int count) {
        visited[x][y] = true;
        ++count;
        
        for (int i = 0; i < moves.length; ++i) {
            int newX = x + moves[i][0];
            int newY = y + moves[i][1];
            
            if (inArea(newX, newY) && grid[newX][newY] == 1 && !visited[newX][newY]) {
                count = countArea(newX, newY, count);
            }
        }
        
        return count;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (null == grid || grid.length == 0) {
            return 0;
        }
        
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.visited = new boolean[m][n];
        
        int max = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(max, countArea(i, j, 0));
                }
            }
        }
        
        return max;
    }
    
}

