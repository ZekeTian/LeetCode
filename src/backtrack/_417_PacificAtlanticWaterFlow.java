package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * 
 * 题目描述：给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *        规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *        请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。输出坐标的顺序不重要。
 * 
 * 限制条件：m 和 n 都小于150
 * 
 * 示例：
       太平洋 ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * 大西洋
 * 返回:
 *  [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 *
 */
public class _417_PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        int[][] heights = {
                {1, 2, 2, 3, 5}, 
                {3, 2, 3, 4, 4}, 
                {2, 4, 5, 3, 1}, 
                {6, 7, 1, 4, 5}, 
                {5, 1, 1, 2, 4}};
        
        _417Solution solution = new _417Solution();
        
        System.out.println(solution.pacificAtlantic(heights));
    }
}

/**
 * 逆向思维。从太平洋、大西洋开始向里面流，标记出太平洋和大西洋可以流动的区域，然后求出这两个区域的交集即可得到最终的结果。
 * 在程序中，即为：从四周的边界出发向里面流，在流动的过程中要保证是向较大的方向流（因为是反着流，所以与题目中的要求相反）。
 */
class _417Solution {
    
    private int[][] heights = null;
    private int m = 0, n = 0;
    private int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
    
    private void flow(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        
        for (int i = 0; i < moves.length; ++i) {
            int newX = x + moves[i][0];
            int newY = y + moves[i][1];
            
            if (inArea(newX, newY) && heights[x][y] <= heights[newX][newY] /* 反方向，故向高处流 */
                    && !visited[newX][newY]) {
                flow(newX, newY, visited);
            }
                
        }
    }
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacific = new boolean[m][n]; // 用于标记太平洋区域
        boolean[][] atlantic = new boolean[m][n]; // 用于标记大西洋区域
        
        // 从边界出发，向高处流，分别标记太平洋、大西洋区域
        for (int i = 0; i < m; ++i) {
            if (!pacific[i][0]) {
                flow(i, 0, pacific);
            }
            if (!atlantic[i][n - 1]) {
                flow(i, n - 1, atlantic);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (!pacific[0][i]) {
                flow(0, i, pacific);
            }
            if (!atlantic[m - 1][i]) {
                flow(m - 1, i, atlantic);
            }
        }
        
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pacific[i][j] && atlantic[i][j]) { // 太平洋和大西洋都有的区域，即两者的公共区域
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    resultList.add(list);
                }
            }
        }
        
        return resultList;
    }
}

