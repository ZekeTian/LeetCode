package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * 
 * 题目描述：给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素
 * 
 * 条件限制：
 *  （1）m == matrix.length
 *  （2）n == matrix[i].length
 *  （3）1 <= m, n <= 10
 *  （4）-100 <= matrix[i][j] <= 100
 *
 * 示例：
 *  示例 1：
 *  输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *      1   2   3
 *      4   5   6
 *      7   8   9
 *  输出：1,2,3,6,9,8,7,4,5
 *  
 *  示例 2：
 *  输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *      1   2   3   4
 *      5   6   7   8
 *      9   10  11  12
 *  输出：1,2,3,4,8,12,11,10,9,5,6,7
 */
public class _54_SpiralMatrix {

    public static void main(String[] args) {
        // test case1, output: 1,2,3,6,9,8,7,4,5
//        int[][] martix = {{1,2,3},{4,5,6},{7,8,9}};
        
        // test case2, otuput: 1,2,3,4,8,12,11,10,9,5,6,7
        int[][] martix =  {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        
        
        _54Solution solution = new _54Solution();
        
        System.out.println(solution.spiralOrder(martix));
        
    }
}

/**
 * 使用递归模拟顺时针螺旋遍历矩阵的过程
 *
 */
class _54Solution {
    private int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 顺时针的四个移动方向
    private int moveIdx = 0; // moves 中的下标，用来标记当前移动的方向
    private int m = 0; // 行
    private int n = 0; // 列
    private boolean[][] visited = null; // 标记矩阵中的元素是否访问过
    private int[][] matrix = null;
    private List<Integer> result = null;
    
    private boolean inMatrix(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
    
    // (x, y) 是当前点在矩阵中的位置
    private void spiral(int x, int y) {
        if (result.size() == m * n /* 已经遍历完 */
            || !inMatrix(x, y) /* (x,y) 不在矩阵中 */
            || visited[x][y] /* (x,y) 已经访问过 */) {
            
            return;
        }
        
        result.add(matrix[x][y]);
        visited[x][y] = true;
        int newX = x + moves[moveIdx % 4][0]; // 因为是顺时针移动，即右、下、左、上四个方向循环移动，所以通过 moveIdx 实现循环的方向切换
        int newY = y + moves[moveIdx % 4][1];
        
        if (inMatrix(newX, newY) && !visited[newX][newY]) {
            spiral(newX, newY);
        } else {
            // 按照原来的方向无法继续移动，则换个方向，继续移动一步
            ++moveIdx; // 换个方向
            newX = x + moves[moveIdx % 4][0];
            newY = y + moves[moveIdx % 4][1];
            spiral(newX, newY);
        }
    }
    
    public List<Integer> spiralOrder(int[][] matrix) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.visited = new boolean[m][n];
        this.matrix = matrix;
        this.result = new ArrayList<Integer>();
        
        spiral(0, 0);
        
        return result;
    }
}

