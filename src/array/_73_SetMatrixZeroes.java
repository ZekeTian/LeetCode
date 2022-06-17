package array;

/**
 * https://leetcode.com/problems/set-matrix-zeroes/
 * 
 * 题目描述：给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * 
 * 进阶：
 *  （1）一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 *  （2）一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 *  （3）你能想出一个仅使用常量空间的解决方案吗？
 * 
 * 限制条件：
 *  （1）m == matrix.length
 *  （2）n == matrix[0].length
 *  （3）1 <= m, n <= 200
 *  （4）-2^31 <= matrix[i][j] <= 2^31 - 1
 * 
 * 示例：
 *  示例 1
 *      输入：matrix = [[1,1,1],
 *                     [1,0,1],
 *                     [1,1,1]]
 *      输出：[[1,0,1],
 *            [0,0,0],
 *            [1,0,1]]
 *
 *  示例 2
 *      输入：matrix = [[0,1,2,0],
 *                     [3,4,5,2],
 *                     [1,3,1,5]]
 *      输出：[[0,0,0,0],
 *            [0,4,5,0],
 *            [0,3,1,0]]
 *            
 */
public class _73_SetMatrixZeroes {

    public static void main(String[] args) {
        // test case1
        int[][] matrix = { { 1, 1, 1 }, 
                           { 1, 0, 1 }, 
                           { 1, 1, 1 } };
        
        // test case2
//        int[][] matrix = { { 0, 1, 2, 0 }, 
//                           { 3, 4, 5, 2 }, 
//                           { 1, 3, 1, 5 } };
        
//        _73Solution1 solution = new _73Solution1();
        
        _73Solution2 solution = new _73Solution2();
        
        solution.setZeroes(matrix);
        
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
    }
    
}


/**
 * 解法一：空间复杂度 O(mn)
 */
class _73Solution1 {
    
    // 将 matrix 中的第 row 行、第 col 列全部置为 0
    private void setZeroes(int[][] matrix, int row, int col) {
        // 将第 row 行全部置为 0
        for (int i = 0; i < matrix[row].length; ++i) {
            matrix[row][i] = 0;
        }
        
        // 将第 col 列全部置为 0 
        for (int i = 0; i < matrix.length; ++i) {
            matrix[i][col] = 0;
        }
    }
    
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] flags = new boolean[m][n]; // 如果 flags[i][j] 为 true，则说明 matrix[i][j] = 0
        
        // 利用 flags 记录 matrix 中元素为 0 的位置
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    flags[i][j] = true;
                }
            }
        }
        
        // 根据 flags 将 matrix 中的元素设置为 0
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (flags[i][j]) {
                    setZeroes(matrix, i, j);
                }
            }
        }
        
    }
    
}

/**
 * 解法二：空间复杂度 O(m + n)
 */
class _73Solution2 {
    
    // 将 matrix 中第 row 行全部设置为 0
    private void setRows(int[][] matrix, int row) {
        for (int i = 0; i < matrix[row].length; ++i) {
            matrix[row][i] = 0;
        }
    }
    
    // 将 matrix 中第 col 列全部设置为 0
    private void setCols(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; ++i) {
            matrix[i][col] = 0;
        }
    }
    
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] rows = new boolean[m]; // 记录 matrix 中含有 0 的行
        boolean[] cols = new boolean[n]; // 记录 matrix 中含有 0 的列
        
        // 根据 matrix 设置 rows、cols
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        
        // 根据 rows、cols，将 matrix 中对应的行、列设置成 0
        for (int i = 0; i < rows.length; ++i) {
            if (rows[i]) {
                setRows(matrix, i);
            }
        }
        for (int i = 0; i < cols.length; ++i) {
            if (cols[i]) {
                setCols(matrix, i);
            }
        }
        
    }
}

