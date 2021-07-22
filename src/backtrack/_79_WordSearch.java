package backtrack;

/**
 * https://leetcode.com/problems/word-search/
 *
 * 题目描述：给定一个 m*n 的字符矩阵 board 和一个字符串 word，如果矩阵中包含 word 对应，则返回 true。
 * 
 * 限制条件：
 *  （1）m 是字符矩阵的行数
 *  （2）n 是字符矩阵的列数
 *  （3）1 <= m,n <= 6
 *  （4）1 <= word.length <= 15
 *  （5）字符矩阵 board 和字符串 word 只包含大小写字母
 * 
 * 示例：
 *  字符矩阵：
 *      A B C E
 *      S F C S
 *      A D E E
 *  字符串： ABCCED
 *  
 *  在字符矩阵中，存在一条路径使得路径上的字符形成字符串 ABCCED，因此返回 true。
 *  注意：不能重复使用同一个字符，即形成的路径不能往来回重复走
 * 
 */
public class _79_WordSearch {

    public static void main(String[] args) {
        // test case 1, true
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
//        String word = "ABCCED";
                
        // test case 2, true 
//        String word = "SEE";
        
        // test case 3, false
        String word = "ABCB";
        
        _79Solution solution = new _79Solution();
        System.out.println(solution.exist(board, word));
    }
}

class _79Solution {

    // 上、下、左、右四个方向，moves[i][0] 表示 x 方向移动的距离，moves[i][1] 表示 y 方向移动的距离
    private int[][] moves = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    // 标记字符 board 中的字符是否访问过
    private boolean[][] visited;
    // board 的长、宽
    private int m, n;

    // 判断 (x,y) 的点是否在 board 中
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }

    // 在 board 的 (startX, startY) 中出发，寻找 word 中 index 处的字符
    private boolean find(char[][] board, int startX, int startY, String word, int index) {
        if (index == word.length() - 1) {
            // 匹配 word 中最后一个字符
            return board[startX][startY] == word.charAt(index);
        }

        // 判断 board[startX][startY] 是否能够匹配 word 中 index 处的字符
        if (board[startX][startY] != word.charAt(index)) {
            return false;
        }

        // 如果能匹配，则从 board[startX][startY] 处向四个方向寻找 word 中 (index + 1) 处的字符 
        for (int i = 0; i < moves.length; ++i) {
            int newStartX = startX + moves[i][0];
            int newStartY = startY + moves[i][1];

            // 判断新位置 (newStartX, newStartY) 是否在 board 中、是否被访问过
            if (inArea(newStartX, newStartY) && !visited[newStartX][newStartY]) {
                // 新位置合法，则继续寻找下一个字符
                visited[newStartX][newStartY] = true; // 占据 board 中 (newStartX, newStartY) 处的位置
                if (find(board, newStartX, newStartY, word, index + 1)) {
                    return true;
                }
                visited[newStartX][newStartY] = false; // 释放 board 中 (newStartX, newStartY) 处的位置
            }
        }

        return false;
    }

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];

        // board 中每个字符都可以作为起点，因此遍历 board 中的每个字符
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                visited[i][j] = true;
                if (find(board, i, j, word, 0)) { // 从 board[i][j] 处出发开始寻找
                    return true;
                }
                visited[i][j] = false;
            }
        }

        return false;
    }
}
