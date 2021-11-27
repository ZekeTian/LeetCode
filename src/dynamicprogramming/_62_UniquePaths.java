package dynamicprogramming;

/**
 * https://leetcode.com/problems/unique-paths/
 * 
 * 题目描述：一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *        问总共有多少条不同的路径？
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
//        int m = 3, n = 7;
        
        // test case 2, output: 3
//        int m = 3, n = 2;
        
        // test case 3, output: 28
//        int m = 7, n = 3;
        
        // test case 4, output: 6
        int m = 3, n = 3;
        
        _62Solution1 solution = new _62Solution1();
        
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
        
        if (x < m) {
            getUniquePathsNum(x + 1, y);
        }
        if (y < n) {
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
