package recursion;

/**
 * https://leetcode.com/problems/spiral-matrix-ii/
 * 
 * 题目描述：给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 
 * 限制条件：1 <= n <= 20
 *  
 * 示例：
 *  示例 1
 *          1 2 3
 *          8 9 4
 *          7 6 5
 *      输入：n = 3
 *      输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2
 *          1
 *      输入：n = 1
 *      输出：[[1]]
 *      
 */
public class _59_SpiralMatrixII {

    public static void main(String[] args) {
        int n = 3;
        
        _59Solution solution = new _59Solution();
        
        int[][] matrix = solution.generateMatrix(n);
        
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                System.out.print(matrix[i][j] + " ");
                
            }
            
            System.out.println();
        }
        
    }
}

class _59Solution {
    
    // 存储顺时针的移动方向，即：右、下、左、上
    private int[][] moves = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    // 当前移动的方向（即在 moves 中的下标，是循环变化）
    private int moveDirection = 0;
    // 最终的矩阵
    private int[][] matrix = null;
    // 矩阵的长度
    private int len = 0;
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < len) && (y >= 0 && y < len);
    }
    
    // 在 matrix[x][y] 处生成数字 n
    private void generateMatrix(int x, int y, int n) {
        matrix[x][y] = n;
        
        // 沿原来的方向继续移动
        int newX = x + moves[moveDirection][0];
        int newY = y + moves[moveDirection][1];
        
        if (inArea(newX, newY) && matrix[newX][newY] == 0) {
            // 新位置合法并且未生成数字，则向新位置移动
            generateMatrix(newX, newY, n + 1);
            return;
        }
        
        // 沿原来方向移动的新位置不合法或已经生成数字，则需要切换方向
        for (int i = 1; i < moves.length; ++i) {
            // 切换到新方向，使用 % 保证是循环切换方向
            moveDirection = (moveDirection + i) % moves.length;
            // 沿新方向移动 
            newX = x + moves[moveDirection][0];
            newY = y + moves[moveDirection][1];
            
            // 判断新位置是否合法并且未生成数字
            if (inArea(newX, newY) && matrix[newX][newY] == 0) {
                // 新位置合法并且未生成数字，则向新位置移动
                generateMatrix(newX, newY, n + 1);
                return;
            }
        }
    }
    
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[][] {};
        }
        
        this.matrix = new int[n][n];
        this.len = n;
        
        generateMatrix(0, 0, 1);
        
        return matrix;
    }
}

