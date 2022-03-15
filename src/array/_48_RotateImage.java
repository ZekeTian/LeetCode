package array;

/**
 * https://leetcode.com/problems/rotate-image/
 * 
 * 题目描述：给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *        你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 
 * 限制条件：
 *  （1）n == matrix.length == matrix[i].length
 *  （2）1 <= n <= 20
 *  （3）-1000 <= matrix[i][j] <= 1000
 * 
 * 示例：
 *      1 2 3       7 4 1
 *      4 5 6  =>   8 5 2
 *      7 8 9       9 6 3
 *  
 *  输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *  输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 
 */
public class _48_RotateImage {

    public static void main(String[] args) {
        int[][] matrix = {
                            {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}
                         };
        
        System.out.println("翻转之前：");
        print(matrix);
        
        _48Solution solution = new _48Solution();
        solution.rotate(matrix);
        
        System.out.println("翻转之后：");
        print(matrix);
    }
    
    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/**
 * 解法思路：先沿水平轴翻转，然后沿主对角线翻转。
 * 
 *      1 2 3   水平轴翻转  7 8 9   主对角线翻转   7 4 1
 *      4 5 6    ====>    4 5 6      ====>     8 5 2
 *      7 8 9             1 2 3                9 6 3
 */
class _48Solution {
    
    private void swap(int[][] matrix, int r1, int c1, int r2, int c2) {
        int tmp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = tmp;
    }
    
    public void rotate(int[][] matrix) {
        if (null == matrix || 0 == matrix.length) {
            return;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        // 沿水平轴翻转
        for (int r = 0; r < row / 2; ++r) {
            for (int c = 0; c < col; ++c) {
                swap(matrix, r, c, row - 1 - r, c);
            }
        }
        
        // 沿主对角线翻转
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < r; ++c) {
                swap(matrix, r, c, c, r);
            }
        }
    }
}
