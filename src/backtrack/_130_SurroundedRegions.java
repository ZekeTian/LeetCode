package backtrack;

/**
 * https://leetcode.com/problems/surrounded-regions/
 * 
 * 题目描述：给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 
 * 限制条件：
 *  （1）m == board.length
 *  （2）n == board[i].length
 *  （3）1 <= m, n <= 200
 *  （4）board[i][j] 为 'X' 或 'O'
 * 
 * 示例：
 *  示例 1
 *          ["X","X","X","X"]       ["X","X","X","X"]
 *          ["X","O","O","X"]       ["X","X","X","X"]
 *          ["X","X","O","X"]  -->  ["X","X","X","X"]
 *          ["X","O","X","X"]       ["X","O","X","X"]
 *      输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 *      输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *      解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 *          如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 
 *  示例 2
 *      输入：board = [["X"]]
 *      输出：[["X"]]
 *      
 */
public class _130_SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        
        _130Solution solution = new _130Solution();
        
        solution.solve(board);
        
        // 输出结果
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.println();
        }
    }
}


class _130Solution {
    
    private char[][] board = null;
    private int m = 0, n = 0;
    private boolean[][] visited = null;
    private int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
    
    // 从 (x,y) 出发，将 O 区域标记成 +，从而最终可以保留
    private void mark(int x, int y) {
        if ('X' == board[x][y]) {
            return;
        }
        // board[x][y] = 'O'，则将其标记成 +（保留），然后向四周扩展标记
        board[x][y] = '+'; // 标记成保留
        visited[x][y] = true;
        
        // 向四周扩散
        for (int i = 0; i < moves.length; ++i) {
            int newX = x + moves[i][0];
            int newY = y + moves[i][1];
            
            if (inArea(newX, newY) && !visited[newX][newY]) {
                mark(newX, newY);
            }
        }
    }
    
    public void solve(char[][] board) {
        this.board = board;
        this.m = board.length;
        this.n = board[0].length;
        this.visited = new boolean[m][n];
        
        
        // 从边界的 O 处出发，将连通的 O 标记成 + （即标记成保留的 O）。因为这些 O 不是被 X 全部包围，所以可以保留
        for (int i = 0; i < m; ++i) {
            if ('O' == board[i][0] && !visited[i][0]) {
                mark(i, 0);
            }
            if ('O' == board[i][n - 1] && !visited[i][n - 1]) {
                mark(i, n - 1);
            }
        }
        for (int i = 0; i < n; ++i) {
            if ('O' == board[0][i] && !visited[0][i]) {
                mark(0, i);
            }
            if ('O' == board[m - 1][i] && !visited[m - 1][i]) {
                mark(m - 1, i);
            }
        }
        
        // 遍历所有，将未标记的 O 标记成 X，而将标记的区域恢复成 O
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if ('+' == board[i][j]) {
                    board[i][j] = 'O';
                } else if ('O' == board[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}