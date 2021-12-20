package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/sudoku-solver/
 * 
 * 题目描述：编写一个程序，通过填充空格来解决数独问题。
 *        数独的解法需 遵循如下规则：
 *        （1）数字 1-9 在每一行只能出现一次。
 *        （2）数字 1-9 在每一列只能出现一次。
 *        （3）数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 *        （4）数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 限制条件：
 *  （1）board.length == 9
 *  （2）board[i].length == 9
 *  （3）board[i][j] 是一位数字或者 '.'
 *  （4）题目数据 保证 输入数独仅有一个解
 * 
 * 示例：
 *  输入：board = [
 *               ["5","3",".",".","7",".",".",".","."],
 *               ["6",".",".","1","9","5",".",".","."],
 *               [".","9","8",".",".",".",".","6","."],
 *               ["8",".",".",".","6",".",".",".","3"],
 *               ["4",".",".","8",".","3",".",".","1"],
 *               ["7",".",".",".","2",".",".",".","6"],
 *               [".","6",".",".",".",".","2","8","."],
 *               [".",".",".","4","1","9",".",".","5"],
 *               [".",".",".",".","8",".",".","7","9"]]
 *              
 *  输出：        [
 *               ["5","3","4","6","7","8","9","1","2"],
 *               ["6","7","2","1","9","5","3","4","8"],
 *               ["1","9","8","3","4","2","5","6","7"],
 *               ["8","5","9","7","6","1","4","2","3"],
 *               ["4","2","6","8","5","3","7","9","1"],
 *               ["7","1","3","9","2","4","8","5","6"],
 *               ["9","6","1","5","3","7","2","8","4"],
 *               ["2","8","7","4","1","9","6","3","5"],
 *               ["3","4","5","2","8","6","1","7","9"]]
 *               
 */
public class _37_SudokuSolver {

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        
        _37Solution solution = new _37Solution();
        
        System.out.println("初始数独");
        printBoard(board);

        solution.solveSudoku(board);

        System.out.println();
        System.out.println("数独的解");
        printBoard(board);

    }
    
    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

/**
 * 回溯法。
 *  解题思想与 N 皇后类似，但是递归的形式稍有不同。
 *  在 N 皇后中，每行只能放一个，所以一行一行去递归即可。而在本题中，每一行都得填满，也就是所有空白的位置（'.' 的位置）都需要去填。
 *  因此，本题的递归形式是：一个一个去填充空白位置。 
 */
class _37Solution {

    private char[][] board = null;
    private boolean[][] rowFlags = new boolean[9][10]; //  rowFlags[r][i] 标记第 r 行中是否已经存在数字 i
    private boolean[][] colFlags = new boolean[9][10]; //  colFlags[c][i] 标记第 c 行中是否已经存在数字 i
    private boolean[][][] subBoxFlags = new boolean[3][3][10]; // subBoxFlags[i][j][n] 标记大小 3*3 的子盒 [i][j] 是否存在数字 n
    private List<int[]> blankList = new ArrayList<>(); // 记录空白位置的下标，以便填充

    // 放置第 index 个空白位置，如果放置成功则返回 true；否则，返回 false。
    private boolean put(int index) {
        if (index == blankList.size()) {
            return true;
        }
        
        // 获取当前空白位置的下标
        int row = blankList.get(index)[0];
        int col = blankList.get(index)[1];
        
        // 当前空白位置把 1~9 中所有的数字都尝试一遍
        for (int i = 1; i <= 9; ++i) {
            if (!rowFlags[row][i] /* 第 row 行未放置数字 i */
                && !colFlags[col][i] /* 第 col 列未放置数字 i */
                && !subBoxFlags[row / 3][col / 3][i] /* 所处的子盒未放置数字 i */) {
                rowFlags[row][i] = true;
                colFlags[col][i] = true;
                subBoxFlags[row / 3][col / 3][i] = true;
                board[row][col] = (char)('0' + i);
                
                if (put(index + 1)) {
                    return true; // 当前位置放置数字 i 成功，则保留当前方案，无需恢复原始状态，直接结束
                }
                
                // 当前位置放置数字 i 失败，则恢复原始状态，然后继续循环放置下一个数字
                rowFlags[row][i] = false;
                colFlags[col][i] = false;
                subBoxFlags[row / 3][col / 3][i] = false;
                board[row][col] = '.';
            }
        }
        
        
        return false; // 没有从循环中结束返回，则说明当前空白位置的放置方案无效，需要返回到上一层递归，尝试新的放置方案
    }
    
    
    public void solveSudoku(char[][] board) {
        this.board = board;
        
        // 记录空白位置和初始化标记数组
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if ('.' == board[i][j]) {
                    // 记录空白位置
                    blankList.add(new int[] {i, j});
                } else {
                    // 初始化标记位置
                    int num = board[i][j] - '0';
                    rowFlags[i][num] = true;
                    colFlags[j][num] = true;
                    subBoxFlags[i / 3][j / 3][num] = true;
                }
            }
        }
        
        // 从第 0 个空白位置开始放置
        put(0);
    }
}