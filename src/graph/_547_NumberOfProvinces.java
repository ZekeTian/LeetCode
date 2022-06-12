package graph;

/**
 * https://leetcode.com/problems/number-of-provinces/
 * 
 * 题目描述：有 n 个城市，其中一些彼此相连，另一些没有相连。
 *         如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *         省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *         给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 *         而 isConnected[i][j] = 0 表示二者不直接相连。
 *         返回矩阵中 省份 的数量。
 *         
 * 限制条件：
 *  （1）1 <= n <= 200
 *  （2）n == isConnected.length
 *  （3）n == isConnected[i].length
 *  （4）isConnected[i][j] 为 1 或 0
 *  （5）isConnected[i][i] == 1
 *  （6）isConnected[i][j] == isConnected[j][i]
 *  
 * 示例：
 *  示例 1
 *      输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 *      输出：2
 *      
 *  示例 2
 *      输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 *      输出：3
 *
 */
public class _547_NumberOfProvinces {

    public static void main(String[] args) {
        // test case1, output: 2
//        int[][] isConnected = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
        
        // test case2, output: 3
        int[][] isConnected = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
        
        
        _547Solution solution = new _547Solution();
        
        
        System.out.println(solution.findCircleNum(isConnected));
    }
    
}

/**
 * 思路：类似于深度优先遍历
 *      思想和第 200 号题 “NumberOfIslands” 类似，都是使用 flood fill 算法的思想。
 */
class _547Solution {
    
    private int[][] isConnected = null;
    private boolean[] visited = null;
   
    // 从城市 c 向四周出发
    private void find(int c) {
        visited[c] = true; // 将城市 c 标记成已访问
        
        // 从城市 c 出发，向周围与其相邻的城市前进
        int[] arr = isConnected[c];
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 1 /* 城市 i 与城市 c 相邻  */
                    && !visited[i] /* 城市 i 没有被访问过 */ ) {
                find(i); // 向城市 i 前进
            }
        }
    }
    
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        this.isConnected = isConnected;
        this.visited = new boolean[n];
        
        int count = 0;
        // 逐个城市出发遍历
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) { // 城市 i 没有访问过，则从城市 i 出发寻找
                find(i);
                ++count;
            }
        }
        
        return count;
    }
    
}

