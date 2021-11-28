package dynamicprogramming;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * 
 * 题目描述：一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *        现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *        网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 
 * 限制条件：
 *  （1）m == obstacleGrid.length
 *  （2）n == obstacleGrid[i].length
 *  （3）1 <= m, n <= 100
 *  （4）obstacleGrid[i][j] 为 0 或 1
 * 
 * 示例：
 *  0 0 0 
 *  0 1 0
 *  0 0 0
 * 
 *  输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 *  输出：2
 *  解释：
 *      3x3 网格的正中间有一个障碍物。
 *      从左上角到右下角一共有 2 条不同的路径：
 *      1. 向右 -> 向右 -> 向下 -> 向下
 *      2. 向下 -> 向下 -> 向右 -> 向右
 * 
 */
public class _63_UniquePathsII {

    public static void main(String[] args) {
        // test case 1, output: 2
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        
        // test case 2, output: 1
//        int[][] obstacleGrid = {{0,1},{0,0}};
        
        _63Solution1 solution = new _63Solution1();
        
        System.out.println(solution.uniquePathsWithObstacles(obstacleGrid));
    }
}

/**
 * 解法一：递归实现
 */
class _63Solution1 {
    
    private int[][] obstacleGrid = null;
    private int m = 0;
    private int n = 0;
    private int num = 0;
    
    private void getUniquePathsNum(int x, int y) {
        if (x == m - 1 && y == n - 1) {
            ++num;
            return;
        }
        
        if (1 == obstacleGrid[x][y]) {
            return; // 障碍物，无法继续走
        }
        
        if (x < m - 1) {
            getUniquePathsNum(x + 1, y);
        }
        if (y < n - 1) {
            getUniquePathsNum(x, y + 1);
        }
    }
    
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.obstacleGrid = obstacleGrid;
        this.m = obstacleGrid.length;
        this.n = obstacleGrid[0].length;
        
        if (1 == obstacleGrid[m - 1][n - 1]) {
            return 0;
        }
        
        getUniquePathsNum(0, 0);
        
        return num;
    }
}

/**
 * 解法二：递归 + 记忆化
 */
class _63Solution2 {
    
    private int m = 0;
    private int n = 0;
    private int[][] memo = null;
    private int[][] obstacleGrid = null;
    
    private int getUniquePathsNum(int x, int y) {
        if (x == m - 1 && y == n - 1) {
            memo[x][y] = 1;
            return 1;
        }
        
        if (1 == obstacleGrid[x][y]) {
            memo[x][y] = 0;
            return 0;
        }
        
        if (0 != memo[x][y]) {
            return memo[x][y];
        }
        
        int res = 0;
        if (x < m - 1) {
            res += getUniquePathsNum(x + 1, y);
        }
        if (y < n - 1) {
            res += getUniquePathsNum(x, y + 1);
        }

        memo[x][y] = res;
        return res;
    }
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.m = obstacleGrid.length;
        this.n = obstacleGrid[0].length;
        this.memo = new int[m][n];
        this.obstacleGrid = obstacleGrid;

        if (1 == obstacleGrid[m - 1][n - 1]) {
            return 0;
        }
        
        getUniquePathsNum(0, 0);
        
        return memo[0][0];
    }
    
}

