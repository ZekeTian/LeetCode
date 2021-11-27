package dynamicprogramming;

/**
 * https://leetcode.com/problems/unique-paths/
 * 
 * 题目描述：一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 问总共有多少条不同的路径？
 * 
 * 限制条件：
 *  （1）1 <= m, n <= 100
 *  （2）答案小于等于 2 * 10^9
 * 
 * 示例：
 *  输入：m = 3, n = 2
 *  输出：3
 *  解释：
 *  从左上角开始，总共有 3 条路径可以到达右下角。
 *  1. 向右 -> 向下 -> 向下
 *  2. 向下 -> 向下 -> 向右
 *  3. 向下 -> 向右 -> 向下
 * 
 */
public class _62_UniquePaths {
    
    public static void main(String[] args) {
        // test case 1, output: 28
        int m = 3, n = 7;
        
        // test case 2, output: 3
//        int m = 3, n = 2;
        
        // test case 3, output: 28
//        int m = 7, n = 3;
        
        // test case 4, output: 6
//        int m = 3, n = 3;
        
//        _62Solution1 solution = new _62Solution1();

        _62Solution2 solution = new _62Solution2();
        
        System.out.println(solution.uniquePaths(m, n));
        
    }
}

/**
 * 解法一：使用递归
 */
class _62Solution1 {
   
    private int m = 0;
    private int n = 0;
    private int num = 0;
    
    
    private void getUniquePathsNum(int x, int y) {
        if (x == m - 1 && y == n - 1) {
            ++num;
            return;
        }
        
        if (x < m) { // 向下
            getUniquePathsNum(x + 1, y);
        }
        if (y < n) { // 向右
            getUniquePathsNum(x, y + 1);
        }
    }
    
    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;
    
        getUniquePathsNum(0, 0);
        
        return num;
    }
}

/**
 * 解法二：使用递归 + 记忆化
 */
class _62Solution2 {
    
    private int m = 0;
    private int n = 0;
    private int[][] memo = null; // memo[x][y] 表示从 (x,y) 到达右下角的路径数量
    
    
    private int getUniquePaths(int x, int y) {
        if (x == m - 1 && y == n - 1) {
            memo[x][y] = 1;
            return 1;
        }
        
        if (0 != memo[x][y]) {
            return memo[x][y];
        }
        
        int res = 0;
        if (x < m - 1) {
            res += getUniquePaths(x + 1, y); // 继续向下走
        }
        if (y < n - 1) {
            res += getUniquePaths(x, y + 1); // 继续向右走
        }
        
        memo[x][y] = res;
        return res;
    }
    
    public int uniquePaths(int m, int n) {
        this.m = m;
        this.n = n;
        this.memo = new int[m][n];
        
        getUniquePaths(0, 0);
        
        return memo[0][0];
    }
}

