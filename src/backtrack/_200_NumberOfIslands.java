package backtrack;

/**
 * https://leetcode.com/problems/number-of-islands/
 * 
 * 题目描述：给定一个 m*n 的二维网格 grid，grid 中的元素值只有 0、1 两种，0 表示水域， 1 表示陆地，返回 grid 中含有岛的个数。
 *        岛是指被水环绕，通过水平、垂直两个方向的相邻陆地形成的。你可以假设 grid 的四条边都被水环绕。
 *
 * 限制条件：
 *  （1）m == grid.length
 *  （2）n == gird[i].length
 *  （3）1 <= m, n <= 300
 *  （4）grid[i][j] 是 0 或者 1
 *  
 * 示例：
 *  grid1:
 *      ["1","1","1","1","0"],
 *      ["1","1","0","1","0"],
 *      ["1","1","0","0","0"],
 *      ["0","0","0","0","0"]
 *  在 grid1 中，岛的个数是 1。
 *  
 *  grid2:
 *      ["1","1","0","0","0"],
 *      ["1","1","0","0","0"],
 *      ["0","0","1","0","0"],
 *      ["0","0","0","1","1"]
 *  在 grid2 中，岛的个数是 3。
 */
public class _200_NumberOfIslands {

    public static void main(String[] args) {
        // test case 1, 1
        char[][] grid1 = { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' } };

        // test case 2, 3
        char[][] grid2 = { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' } };

        _200Solution solution = new _200Solution();
        System.out.println(solution.numIslands(grid1));

    }
}

/**
 * 本问题与 79 号问题 Word Search稍微有点不同，使用 flood fill 算法解决。79 号问题可以典型的回溯算法框架解决，每次递归前记录状态，递归返回后恢复状态。
 * 而在本问题中，递归返回后不会恢复状态。
 * 
 * flood fill 算法的核心思想就是：在一个区域中提取若干个连通的点与其他相邻区域区分开（或分别染成不同颜色）。因为其思路类似洪水从一个区域扩散到所有能到达的区域而得名。
 * 正是因为 flood fill 算法会将不同区域标记成不同颜色，所以递归返回后不会恢复状态。
 */
class _200Solution {

    // 上、下、左、右四个方向
    private int[][] moves = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    // grid 的行个数、列个数
    private int m, n;

    // 标记 grid 中的元素是否访问过
    private boolean[][] visited;

    private boolean inArea(int startX, int startY) {
        return (startX >= 0 && startX < m) && (startY >= 0 && startY < n);
    }

    // 在 grid[startX][startY] 处，向四个方向行走，寻找连通的陆地，并进行标记（该过程实际上是一个 DFS 过程）。
    public void findLands(char[][] grid, int startX, int startY) {
        visited[startX][startY] = true; // 将当前位置的元素标记成访问过（该位置与上次递归的位置是连通的）

        // 从 grid[startX][startY] 处向四个方向移动，将与 grid[startX][startY] 连通的陆地进行标记
        for (int i = 0; i < moves.length; ++i) {
            int newStartX = startX + moves[i][0];
            int newStartY = startY + moves[i][1];

            // 判断 (newStartX, newStartY) 是否在 grid 中，是否是陆地，是否访问过
            if (inArea(newStartX, newStartY) && grid[newStartX][newStartY] == '1' && !visited[newStartX][newStartY]) {
                findLands(grid, newStartX, newStartY);
            }
        }

        // 递归终止条件隐藏在 for 循环的 if 中，如果 for 循环中的 if 均不成立，则会执行至此，结束递归调用
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        // 从 grid 中每个未访问过的陆地出发，寻找新的岛
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    // 寻找到一个新的岛，从 grid[i][j] 处向四周寻找连通的陆地，从而形成完整的岛
                    ++count;
                    findLands(grid, i, j);
                }
            }
        }

        return count;
    }
}
