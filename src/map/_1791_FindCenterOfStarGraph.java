package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-center-of-star-graph/
 * 
 * 题目描述：有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。
 *        给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间存在一条边。请你找出并返回 edges 所表示星型图的中心节点。
 *        
 * 限制条件：
 *  （1）3 <= n <= 10^5
 *  （2）edges.length == n - 1
 *  （3）edges[i].length == 2
 *  （4）1 <= ui, vi <= n
 *  （5）ui != vi
 *  （6）题目数据给出的 edges 表示一个有效的星型图
 *  
 * 示例：
 *          4
 *          |
 *          2
 *         / \
 *        1   3
 *  输入：edges = [[1,2],[2,3],[4,2]]
 *  输出：2
 *  解释：如上图所示，节点 2 与其他每个节点都相连，所以节点 2 是中心节点。
 *
 */
public class _1791_FindCenterOfStarGraph {

    public static void main(String[] args) {
        // test case1, output: 2
        int[][] edges = { {1, 2}, {2, 3}, {4, 2} };
        
        // test case2, output: 3
//        int[][] edges = { {1, 3}, {2, 3} };
        
        _1791Solution solution = new _1791Solution();
        
        System.out.println(solution.findCenter(edges));
    }
}

/**
 * 使用 Map 记录各个顶点在边中出现的次数。因为中心节点是出现次数最多的顶点，所以根据这一特点，可以找到中心节点
 */
class _1791Solution {
    
    public int findCenter(int[][] edges) {
        // 如果边的数量少于 2 条，则不存在中心顶点
        if (edges.length < 2) {
            return -1;
        }
        
        // 统计各个顶点出现的次数
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int[] e : edges) {
            countMap.put(e[0], countMap.getOrDefault(e[0], 0) + 1);
            countMap.put(e[1], countMap.getOrDefault(e[1], 0) + 1);
        }
        
        // 找到出现次数最多的顶点，该顶点即为中心节点
        Set<Integer> keys = countMap.keySet();
        int maxCount = 0, center = -1;
        for (Integer k : keys) {
            if (countMap.get(k) > maxCount) {
                maxCount = countMap.get(k);
                center = k;
            }
        }
        
        return center;
    }
}
