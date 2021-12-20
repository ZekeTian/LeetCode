package backtrack;

/**
 * https://leetcode.com/problems/n-queens-ii/
 * 
 * 题目描述：n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *        给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *        
 * 限制条件： 1 <= n <= 9
 * 
 * 
 * 示例：
 *  示例 1
 *          .Q..       ..Q. 
 *          ...Q       Q... 
 *          Q...       ...Q 
 *          ..Q.       .Q.. 
 *      
 *      输入：4
 *      输出：2
 *      解释：如上图所示，4 皇后问题存在两个不同的解法。
 *      
 *  示例 2
 *      输入：1
 *      输出：1
 * 
 */
public class _52_NQueensII {

    public static void main(String[] args) {
        // test case1, output: 2
//        int n = 4;
        
        // test case2, output: 1
        int n = 1;
        
        _52Solution solution = new _52Solution();
        
        
        System.out.println(solution.totalNQueens(n));
    }
}

/**
 * 回溯法解决
 */
class _52Solution {
    
    private int totalNum = 0;
    private int n = 0;
    private boolean[] colFlags = null; // 列标记位
    private boolean[] topRight = null; // \ 对角线标记位
    private boolean[] topLeft = null; // / 对角线标记位
    
    // 放置第 row 行
    private void put(int row) {
        if (row >= n) {
            ++totalNum; // 所有行都已经放置完，则得到一种解
            return;
        }
        
        // 在在放置第 row 行时，每一列都需要尝试
        for (int i = 0; i < n; ++i) {
            if (!colFlags[i] && !topRight[row - i + n] && !topLeft[row + i]) {
                colFlags[i] = true;
                topRight[row - i + n] = true;
                topLeft[row + i] = true;
                
                put(row + 1);
                
                colFlags[i] = false;
                topRight[row - i + n] = false;
                topLeft[row + i] = false;
            }
        }
    }
    
    public int totalNQueens(int n) {
        this.n = n;
        this.colFlags = new boolean[n];
        this.topRight = new boolean[2 * n];
        this.topLeft = new boolean[2 * n];
        
        put(0); // 从第 0 行开始放
        
        return totalNum;
    }
}
