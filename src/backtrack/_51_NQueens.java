package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/n-queens/
 *
 * 题目描述：将 n 个皇后放在一个 n × n的棋盘中，使得皇后之间不会发生攻击。为了达到此目的，任意两个皇后都不能处于同一条横行、纵行或斜线上
 *        给定一个整数 n，返回 N 皇后问题所有不同的解，返回的解的顺序可以是任意的。每个解包含一种不同的 n 皇后放置策略，其中 'Q' 表示皇后，'.' 表示空。
 * 
 * 条件限制：1 <= n <= 9
 * 
 * 示例：
 * 输入： n = 4
 * 输出：
 *  第一种解：
 *      .Q..
 *      ...Q
 *      Q...
 *      ..Q.
 *  
 *  第二种解：
 *      ..Q.
 *      Q...
 *      ...Q
 *      .Q..
 */
public class _51_NQueens {

    public static void main(String[] args) {
        int n = 4;
        
        _51Solution solution = new _51Solution();
        
        System.out.println(solution.solveNQueens(n));
    }
}

class _51Solution {

    private List<List<String>> resultList = new ArrayList<List<String>>();
    // 标记每列是否存放皇后
    private boolean[] colFlag;
    // 存储每行皇后存放的位置（每行皇后所在的列）
    private int[] rowQueuePos;
    // 标记从左上角到右下角（\）的对角线是否存在皇后，对角线下标：(x - y) + n - 1 
    private boolean[] topLeft;
    // 标记左下角到右上角（/）的对角线是否存在皇后，对角线下标：x + y
    private boolean[] topRight;
    private int queueNum;

    // 在棋盘的第 row 行放置皇后
    private void putQueue(int row) {
        if (row == queueNum) {
            // 放满棋盘，得到一个解
            ArrayList<String> result = new ArrayList<String>();
            // 逐行生成棋盘
            for (int i = 0; i < queueNum; ++i) {
                char[] charArr = new char[queueNum];
                Arrays.fill(charArr, '.');
                charArr[rowQueuePos[i]] = 'Q';
                result.add(new String(charArr));
            }
            resultList.add(result);
        }

        for (int col = 0; col < queueNum; ++col) {
            // 判断 (row, col) 位置放皇后，是否合法
            if (!colFlag[col] /* 列合法 */
                    && !topLeft[(row - col) + queueNum - 1] /* topLeft 对角线合法 */
                    && !topRight[row + col] /* topRight 对角线合法 */) {
                // 如果 (row, col) 位置放皇后合法，在该位置放置皇后
                rowQueuePos[row] = col;
                colFlag[col] = true;
                topLeft[(row - col) + queueNum - 1] = true;
                topRight[row + col] = true;
                
                // 继续放下一行
                putQueue(row + 1);
                
                // 恢复状态
                rowQueuePos[row] = -1;
                colFlag[col] = false;
                topLeft[(row - col) + queueNum - 1] = false;
                topRight[row + col] = false;
            }
                
        }
    }

    public List<List<String>> solveNQueens(int n) {
        queueNum = n;
        colFlag = new boolean[n];
        rowQueuePos = new int[n];
        topLeft = new boolean[2 * n - 1];
        topRight = new boolean[2 * n - 1];
        Arrays.fill(rowQueuePos, -1);

        // 每行逐列放皇后
        putQueue(0);

        return resultList;
    }
}
