package dynamicprogramming;

/**
 * https://leetcode.com/problems/maximal-square/
 * 
 * 题目描述：在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 
 * 限制条件：
 *  （1）m == matrix.length
 *  （2）n == matrix[i].length
 *  （3）1 <= m, n <= 300
 *  （4）matrix[i][j] 为 '0' 或 '1'
 *  
 * 示例：
 *  示例 1
 *      输入：matrix = [["1","0","1","0","0"],
 *                     ["1","0","1","1","1"],
 *                     ["1","1","1","1","1"],
 *                     ["1","0","0","1","0"]]
 *      输出：4
 *      解释：从第 2 行第 3 列的 “1” 处开始，可以形成一个 2*2 的正方形
 *  
 *  示例 2
 *      输入：matrix = [["0","1"],
 *                     ["1","0"]]
 *      输出：1
 *      
 */
public class _221_MaximalSquare {

    public static void main(String[] args) {
        // test case1, output: 4
        char[][] matrix = { { '1', '0', '1', '0', '0' }, 
                            { '1', '0', '1', '1', '1' }, 
                            { '1', '1', '1', '1', '1' },
                            { '1', '0', '0', '1', '0' } };
        
        // test case2, output: 1
//        char[][] matrix = { { '0', '1' },
//                            { '1', '0' } };
        
       _221Solution1 solution = new _221Solution1();
       
       
       System.out.println(solution.maximalSquare(matrix));
    }
}

/**
 * 解法一：暴力法，逐个尝试
 */
class _221Solution1 {
    
    private char[][] matrix = null;
    
    private boolean isValid(int x1, int x2, int y1, int y2) {
        for (int x = x1; x <= x2; ++x) {
            for (int y = y1; y <= y2; ++y) {
                if (matrix[x][y] == '0') {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public int maximalSquare(char[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return 0;
        }
        
        int maxLen = 0; // 存储最大的正方形的边长
        this.matrix = matrix;
        int row = matrix.length;
        int col = matrix[0].length;
        
        for (int x = 0; x < row; ++x) {
            for (int y = 0; y < col; ++y) {
                if (matrix[x][y] == '0') {
                    continue;
                }
                int len = Math.min(row - x, col - y); // 以 (x,y) 为起点，能够形成的最大的正方形的边长
                for (int i = len; i > maxLen; --i) {
                    if (isValid(x, x + i - 1, y, y + i - 1)) {
                        maxLen = i;
                    }
                }
            }
        }
        
        return maxLen * maxLen;
    }
}

/**
 * 解法二：动态规划。
 *      用一个二维数组 memo 记录以 (x,y) 为右下角，且只包含 1 的正方形的边长最大值。
 *      以 (x,y) 为右下角的正方形，可以从左边、上边、右上角三个方向向 (x,y) 扩展得到。
 *      但是为了保证左边、上边、右上角三个方向同时满足条件，需要从这三个方向中取最小的边，然后加 1（即加上 (x,y)）。
 *      因此，状态转移为：memo[x][y] = min(min(memo[x][y - 1], memo[x - 1][y]), memo[x - 1][y - 1]) + 1。
 *      特殊处理的地方是，当 x = 0 或 y = 0 时，无法从左边、上边、右上角三个方向向 (x,y) 扩展，因此此时 memo[x][y] = 1。
 *      但是，需要注意的是，上面这些状态转移发生的前提是 matrix[x][y] = '1'。如果 matrix[x][y] = '0'，则不需要进行处理。
 *      
 *      综上所述，状态转移的过程是：
 *      if (matrix[x][y] == '1') {
 *          if (x == 0 || y == 0) {
 *              memo[x][y] = 1;
 *          } else {
 *              memo[x][y] = min(min(memo[x][y - 1], memo[x - 1][y]), memo[x - 1][y - 1]) + 1;
 *          }
 *      } else {
 *          memo[x][y] = 0; // 当然，此处也可省略
 *      }
 *
 */
class _221Solution2 {
    
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int maxLen = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] memo = new int[row][col]; // 记录以 (x,y) 为右下角，且只包含 1 的正方形的边长最大值
        
        for (int x = 0; x < row; ++x) {
            for (int y = 0; y < col; ++y) {
                if (matrix[x][y] == '1') {
                    if (x == 0 || y == 0) {
                        memo[x][y] = 1; // 无法扩展，则只能选择 matrix[x][y]，所以边长是 1
                    } else { // 从左边、上边、右上角三个方向向 (x,y) 扩展得到 
                        memo[x][y] = Math.min(Math.min(memo[x - 1][y], memo[x][y - 1]),
                                              memo[x - 1][y - 1]) + 1;
                    }
                    
                    maxLen = Math.max(maxLen, memo[x][y]);
                }
            }
        }
        
        return maxLen * maxLen;
    }
    
}

