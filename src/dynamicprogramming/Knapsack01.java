package dynamicprogramming;

import java.util.Arrays;

/**
 * 0-1 背包问题
 * 
 * 给定 n 个物品，每种物品都有自己的重量 wi 和价值 vi。在限定的总重量（或总容量） C 内，从 n 个物品中选择若干个物品使得物品总价值最高。 
 * 注意：对于某个物品，要么选择要么不选择，即选择 0 个或 1 个，也就是 0-1 背包问题名字的来由。
 */
public class Knapsack01 {

    public static void main(String[] args) {
        // test case 1, output: 4
        //        int V = 10;
        //        int n = 2;
        //        int[][] vw = { { 1, 3 }, { 10, 4 } };

        // test case 1, output: 1963
        int V = 200;
        int n = 200;
        int[][] vw = { { 183, 153 }, { 28, 164 }, { 163, 145 }, { 53, 155 }, { 18, 37 }, { 56, 14 }, { 159, 126 },
                { 123, 154 }, { 116, 189 }, { 139, 174 }, { 171, 97 }, { 83, 119 }, { 111, 188 }, { 195, 89 },
                { 10, 135 }, { 173, 7 }, { 64, 15 }, { 108, 100 }, { 36, 192 }, { 134, 179 }, { 137, 105 }, { 84, 52 },
                { 91, 96 }, { 127, 49 }, { 79, 128 }, { 157, 24 }, { 57, 104 }, { 140, 17 }, { 117, 6 }, { 174, 122 },
                { 104, 20 }, { 94, 73 }, { 103, 17 }, { 19, 170 }, { 71, 107 }, { 114, 122 }, { 32, 99 }, { 56, 99 },
                { 20, 42 }, { 56, 88 }, { 76, 59 }, { 114, 28 }, { 93, 72 }, { 101, 86 }, { 198, 50 }, { 94, 40 },
                { 30, 99 }, { 9, 24 }, { 148, 182 }, { 136, 158 }, { 22, 130 }, { 178, 199 }, { 190, 67 }, { 117, 114 },
                { 82, 81 }, { 79, 89 }, { 163, 101 }, { 121, 178 }, { 129, 129 }, { 110, 78 }, { 4, 111 }, { 154, 129 },
                { 5, 165 }, { 30, 100 }, { 63, 167 }, { 171, 200 }, { 32, 5 }, { 61, 28 }, { 149, 79 }, { 123, 40 },
                { 45, 143 }, { 51, 42 }, { 76, 174 }, { 195, 121 }, { 43, 9 }, { 70, 9 }, { 126, 77 }, { 163, 95 },
                { 150, 153 }, { 60, 173 }, { 24, 51 }, { 118, 87 }, { 182, 29 }, { 196, 95 }, { 164, 73 }, { 65, 78 },
                { 109, 3 }, { 190, 172 }, { 135, 158 }, { 96, 91 }, { 149, 162 }, { 37, 103 }, { 44, 133 }, { 169, 96 },
                { 176, 143 }, { 60, 186 }, { 159, 114 }, { 166, 28 }, { 14, 105 }, { 102, 57 }, { 35, 144 },
                { 48, 180 }, { 138, 149 }, { 165, 143 }, { 76, 94 }, { 55, 6 }, { 189, 84 }, { 29, 151 }, { 150, 86 },
                { 59, 44 }, { 34, 96 }, { 13, 189 }, { 12, 92 }, { 190, 87 }, { 41, 82 }, { 92, 42 }, { 114, 117 },
                { 79, 18 }, { 165, 78 }, { 42, 83 }, { 115, 117 }, { 80, 139 }, { 141, 109 }, { 51, 114 }, { 19, 144 },
                { 129, 173 }, { 38, 146 }, { 96, 196 }, { 7, 154 }, { 164, 80 }, { 39, 55 }, { 166, 177 }, { 32, 111 },
                { 143, 151 }, { 52, 133 }, { 173, 64 }, { 21, 92 }, { 85, 17 }, { 148, 23 }, { 170, 192 }, { 78, 171 },
                { 84, 66 }, { 67, 112 }, { 173, 128 }, { 153, 59 }, { 72, 7 }, { 17, 128 }, { 51, 200 }, { 176, 142 },
                { 127, 157 }, { 128, 67 }, { 37, 21 }, { 40, 177 }, { 123, 186 }, { 50, 153 }, { 104, 185 },
                { 164, 200 }, { 100, 194 }, { 33, 151 }, { 35, 41 }, { 72, 32 }, { 75, 59 }, { 13, 85 }, { 164, 109 },
                { 39, 50 }, { 64, 34 }, { 154, 14 }, { 35, 131 }, { 69, 127 }, { 125, 76 }, { 87, 172 }, { 197, 133 },
                { 102, 150 }, { 96, 150 }, { 80, 169 }, { 26, 126 }, { 101, 55 }, { 37, 46 }, { 36, 55 }, { 176, 113 },
                { 70, 140 }, { 193, 199 }, { 192, 184 }, { 158, 170 }, { 125, 155 }, { 25, 9 }, { 99, 31 },
                { 122, 139 }, { 28, 174 }, { 129, 78 }, { 16, 181 }, { 188, 49 }, { 65, 42 }, { 197, 94 }, { 191, 45 },
                { 88, 188 }, { 165, 11 }, { 91, 124 }, { 100, 65 }, { 191, 18 } };

        //        KnapsackSolution1 solution = new KnapsackSolution1();

        //        KnapsackSolution2 solution = new KnapsackSolution2();

        //        KnapsackSolution3 solution = new KnapsackSolution3();

        //        KnapsackSolution4 solution = new KnapsackSolution4();

        KnapsackSolution5 solution = new KnapsackSolution5();

        System.out.println(solution.knapsack(V, n, vw));
    }
}

/**
 * 解法一：仅使用 递归 的方式解决
 */
class KnapsackSolution1 {

    private int n = 0; // 物品个数
    private int[][] vw = null; // 物品的体积和重量，vw[i][0]，vw[i][1] 分别描述 i 号物品的 vi，wi

    // 放置 start 号物品，此时背包体积为 V
    private int put(int V, int start) {
        if (start == n || V <= 0) {
            return 0;
        }

        int res = -1;
        // 判断当前体积能否放下 start 号物品
        if (V >= vw[start][0]) {
            res = vw[start][1] + put(V - vw[start][0], start + 1); // 将 start 号物品放进背包
        }

        res = Math.max(res, put(V, start + 1)); // 不放 start 号物品，取放 start 号物品和不放 start 号物品两者间的较大值

        return res;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算01背包问题的结果
     * @param V int整型 背包的体积
     * @param n int整型 物品的个数
     * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
     * @return int整型
     */
    public int knapsack(int V, int n, int[][] vw) {
        this.n = n;
        this.vw = vw;

        return put(V, 0);
    }

}

/**
 * 解法二：使用 递归 + 记忆化搜索 的方式解决
 */
class KnapsackSolution2 {

    private int n = 0; // 物品个数
    private int[][] vw = null; // 物品的体积和重量，vw[i][0]，vw[i][1] 分别描述 i 号物品的 vi，wi
    private int[][] memo = null; // memo[i][j] 表示在放置前 i+1 件物品，并且背包体积为 j 的限制条件下时，背包能够装的最大的重量

    private int put(int V, int start) {
        if (start == n || V <= 0) {
            return 0;
        }

        // 判断是否已经计算过，如果已经计算过，则直接返回
        if (memo[start][V] != -1) {
            return memo[start][V];
        }

        // 未计算，则计算，然后保存
        int res = -1;
        if (V >= vw[start][0]) {
            res = vw[start][1] + put(V - vw[start][0], start + 1);
        }

        res = Math.max(res, put(V, start + 1));

        memo[start][V] = res;

        return res;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算01背包问题的结果
     * @param V int整型 背包的体积
     * @param n int整型 物品的个数
     * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
     * @return int整型
     */
    public int knapsack(int V, int n, int[][] vw) {
        this.n = n;
        this.vw = vw;
        this.memo = new int[n][V + 1];

        for (int i = 0; i < n; ++i) {
            Arrays.fill(memo[i], -1);
        }

        return put(V, 0);
    }
}

/**
 * 解法三：使用 动态规划 的方式解决
 */
class KnapsackSolution3 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算01背包问题的结果
     * @param V int整型 背包的体积
     * @param n int整型 物品的个数
     * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
     * @return int整型
     */
    public int knapsack(int V, int n, int[][] vw) {
        int[][] memo = new int[n][V + 1]; // memo[i][j] 表示在放置前 i+1 件物品，并且背包体积为 j 的限制条件下时，背包能够装的最大的重量（此时，已经放置了 i + 1 个物品）
        for (int i = 0; i < n; ++i) {
            Arrays.fill(memo[i], 0);
        }
        // 初始化第 0 行
        for (int i = 0; i <= V; ++i) {
            if (i >= vw[0][0]) {
                memo[0][i] = vw[0][0];
            }
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= V; ++j) {
                if (j >= vw[i][0]) {
                    int res1 = vw[i][1] + memo[i - 1][j - vw[i][0]]; // 放 i 号物品
                    int res2 = memo[i - 1][j]; // 不放 i 号物品
                    memo[i][j] = Math.max(res1, res2); // 取放 i 号物品和不放 i 号物品的较大值
                } else {
                    memo[i][j] = memo[i - 1][j];
                }
            }
        }

        return memo[n - 1][V];
    }
}

/**
 * 解法四：动态规划的优化版本 1，只保存两行中间结果
 */
class KnapsackSolution4 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算01背包问题的结果
     * @param V int整型 背包的体积
     * @param n int整型 物品的个数
     * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
     * @return int整型
     */
    public int knapsack(int V, int n, int[][] vw) {
        int[][] memo = new int[2][V + 1]; // memo[i][j] 表示在放置前 i+1 件物品，并且背包体积为 j 的限制条件下时，背包能够装的最大的重量（此时，已经放置了 i + 1 个物品）
        for (int i = 0; i < 2; ++i) {
            Arrays.fill(memo[i], 0);
        }
        // 初始化第 0 行
        for (int i = 0; i <= V; ++i) {
            if (i >= vw[0][0]) {
                memo[0][i] = vw[0][0];
            }
        }

        // memo 只有两行，一行存储当前次的结果，一行存储上一次的结果。如果这一次，第 0 行存储上一次结果，第 1 行存储当前次结果；则下一次，第 0 行存储当前次结果，第 1 行存储上一次结果。
        // 即第 0 行和第 1 行交替存储上一次结果和当前次结果。对于第 i 个物品而言，(i + 1) % 2 行是上一次的结果，(i % 2) 行是当前次所在的行
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= V; ++j) {
                if (j >= vw[i][0]) {
                    int res1 = vw[i][1] + memo[(i + 1) % 2][j - vw[i][0]]; // 放 i 号物品
                    int res2 = memo[(i + 1) % 2][j]; // 不放 i 号物品
                    memo[i % 2][j] = Math.max(res1, res2); // 取放 i 号物品和不放 i 号物品的较大值
                } else {
                    memo[i % 2][j] = memo[(i + 1) % 2][j];
                }
            }
        }

        return memo[(n - 1) % 2][V];
    }
}

/**
 * 解法四：动态规划的优化版本 1，只保存一行中间结果
 */
class KnapsackSolution5 {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算01背包问题的结果
     * @param V int整型 背包的体积
     * @param n int整型 物品的个数
     * @param vw int整型二维数组 第一维度为n,第二维度为2的二维数组,vw[i][0],vw[i][1]分别描述i+1个物品的vi,wi
     * @return int整型
     */
    public int knapsack(int V, int n, int[][] vw) {
        int[] memo = new int[V + 1]; // memo[j] 表示在放置前 i 件物品、背包体积为 j 时，背包能够装的最大的重量（此时，已经放置了 i + 1 个物品）
        // 初始化第 0 行
        for (int i = 0; i <= V; ++i) {
            memo[i] = (i >= vw[0][0] ? vw[0][0] : 0);
        }

        // memo 只存储一行中间结果，为了避免将上一次的结果覆盖掉，所以 j 从 V 开始自减，即反向倒着向前遍历
        for (int i = 1; i < n; ++i) {
            for (int j = V; j >= vw[i][0]; --j) {
                memo[j] = Math.max(memo[j], memo[j - vw[i][0]] + vw[i][1]); // 在放 i 号物品和不放 i 号物品之间取较大值，作为放置第 i 号物品、背包体积为 j 时背包能够装的最大的重量
            }
        }

        return memo[V];
    }
}
