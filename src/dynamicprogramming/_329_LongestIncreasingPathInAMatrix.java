package dynamicprogramming;


/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * 
 * 题目描述：给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 *         对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）
 *         
 * 限制条件：
 *  （1）m == matrix.length
 *  （2）n == matrix[i].length
 *  （3）1 <= m, n <= 200
 *  （4）0 <= matrix[i][j] <= 2^31 - 1
 *  
 * 示例：
 *  示例 1
 *          9   9   4
 *          6   6   8
 *          2   1   1
 *      输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 *      输出：4 
 *      解释：最长递增路径为 [1, 2, 6, 9]。
 *  
 *  示例 2
 *          3   4   5
 *          3   2   6
 *          2   2   1
 *      输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 *      输出：4 
 *      解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 
 *  示例 3
 *      输入：matrix = [[1]]
 *      输出：1
 *      
 */
public class _329_LongestIncreasingPathInAMatrix {

    public static void main(String[] args) {
        // test case1, output: 4
//        int[][] matrix = { { 3, 4, 5 },
//                           { 3, 2, 6 },
//                           { 2, 2, 1 }};
        
        // test case1, output: 1
        int[][] matrix = { { 1 } };
        
        _329Solution1 solution = new _329Solution1();
        
        System.out.println(solution.longestIncreasingPath(matrix));
    }
}

/**
 * 解法一：记忆化搜索
 */
class _329Solution1 {
    
    private int m = 0, n = 0;
    private int[][] memo = null;
    private boolean[][] visited = null; // visited 保证不会重复访问，但是针对此题，实际上可以去掉，因为要求是递增，所以不可能掉头，即不存在重复访问的情况
    private int[][] moves = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private int[][] matrix = null;
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
    
    // 从 matrix[x][y] 处出发，能够形成的 “最长递增路径” 的长度
    private int getLongestIncreasingPath(int x, int y) {
        if (memo[x][y] != 0) {
            return memo[x][y]; // 从 matrix[x][y] 出发，能够形成的 “最长递增路径” 的长度已经被计算出来，则直接返回即可
        }
        
        int longest = 1;
        
        // 尝试从四个方向出发
        for (int i = 0; i < moves.length; ++i) {
            int newX = x + moves[i][0];
            int newY = y + moves[i][1];
            
            if (inArea(newX, newY) && !visited[newX][newY] /* 针对此题，visited 可以省略，因为题目要求路径是递增的 */
                && matrix[newX][newY] > matrix[x][y] /* 保证路径是递增 */ ) {
                visited[newX][newY] = true;
                longest = Math.max(longest, getLongestIncreasingPath(newX, newY) + 1);
                visited[newX][newY] = false;
            }
        }
        
        memo[x][y] = longest;
        
        return longest;
    }
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.memo = new int[m][n];
        this.visited = new boolean[m][n];
        this.matrix = matrix;
        
        // 从 matrix 中不同的位置出发，计算出能够形成的 “最长递增路径” 的长度，然后从这些长度中取最大值
        int longest = 0; // 最终最长的路径长度
        for (int x = 0; x < m; ++x) {
            for (int y = 0; y < n; ++y) {
                visited[x][y] = true;
                longest = Math.max(longest, getLongestIncreasingPath(x, y));
                visited[x][y] = false;
            }
        }
        
        return longest;
    }
}
