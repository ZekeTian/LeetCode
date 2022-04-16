package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/course-schedule/
 * 
 * 题目描述：你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 *         在选修某些课程之前需要一些先修课程。先修课程按数组 prerequisites 给出，
 *         其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 *         例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 *         请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 
 * 限制条件：
 *  （1）1 <= numCourses <= 10^5
 *  （2）0 <= prerequisites.length <= 5000
 *  （3）prerequisites[i].length == 2
 *  （4）0 <= ai, bi < numCourses
 *  （5）prerequisites[i] 中的所有课程对 互不相同
 *  
 * 示例：
 *  示例 1
 *      输入：numCourses = 2, prerequisites = [[1,0]]
 *      输出：true
 *      解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *  
 *  示例 2
 *      输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 *      输出：false
 *      解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *      
 */
public class _207_CourseSchedule {

    public static void main(String[] args) {
        // test case1, output: true
        int numCourses = 2;
        int[][] prerequisites = { { 1, 0 } };
        
        // test case2, output: false
//        int numCourses = 2;
//        int[][] prerequisites = { { 1, 0 }, { 0, 1 } };
        
        
        _207Solution solution = new _207Solution();
        
        System.out.println(solution.canFinish(numCourses, prerequisites));
    }
}

/**
 * 解法一；使用深度优先搜索实现
 *  在遍历时，每个顶点的访问状态是不相同的，总共有如下三种状态：
 *      （1）未访问，即还未遍历到该顶点
 *      （2）访问中，遍历到该顶点，但是该顶点的邻居点未遍历完，现在正在遍历它的邻居点
 *      （3）已访问，遍历到该顶点，而且该顶点的邻居点已遍历完
 *  如果有向图中存在环，则从环中的一个顶点出发，之后还能够回到该顶点。
 *  在遍历前，所有顶点的状态都初始化为 “未访问” 。
 *  在进行深度遍历时，当遍历到顶点 v 时，先将顶点 v 标记成 “访问中” 状态，然后再对 v 的邻居点继续进行深度遍历，不断递归下去。
 *  如果存在环，则最终会沿着这个环重新回到 v 处。而此时，v 还是 “访问中” 状态。因此，判断是否有环，则只需要判断是否会遍历到
 *  状态为 “访问中” 的顶点。如果遍历到，则说明存在环；否则，不存在环。
 *  当遍历完 v 的所有邻居顶点，并且还没有遇到环时，则将顶点 v 的状态更改为 “已访问”（该状态也可以表明 v 不在环中）。
 *  
 *            v1 ← v2 ← v5
 *                 ↓    ↑
 *                 v3 → v4
 *  对于上面的示例图：
 *      从顶点 v1 出发进行遍历，因为 v1 的正向邻接表为空（即无法从 v1 向外遍历），因此可以将 v1 标记成 “已访问”；
 *      v1 访问完后，继续遍历，则会遍历到顶点 v2，将 v2 标记成 “访问中”，之后遍历 v2 的正向邻居点；
 *        因为 v1 的状态是 “已访问”，则说明该顶点已经遍历过，此次遍历可以跳过该顶点；
 *        因为 v3 的状态是 “未访问”，所以会遍历到 v3，并将 v3 标记成 “访问中”，之后遍历 v3 的正向邻居点；
 *          因为 v4 的状态是 “未访问”，所以会遍历到 v4，并将 v4 标记成 “访问中”，之后遍历 v4 的正向邻居点；
 *            因为 v5 的状态是 “未访问”，所以会遍历到 v5，并将 v5 标记成 “访问中”，之后遍历 v5 的正向邻居点；
 *              在遍历 v5 的正向邻居点时，会回到顶点 v2，而此时 v2 的状态是 “访问中”，即再次遍历到状态为 “访问中” 的顶点，
 *              所以说明图中存在环。
 */
class _207Solution {
    
    // 正向邻接表（ u -> v），u 的邻接表
    private List<List<Integer>> adjList = null;
    // 标记每个顶点的访问状态，顶点的状态总共有如下三种：
    //   （1）未访问，即还未遍历到该顶点，状态值为 0；
    //   （2）访问中，遍历到该顶点，但是该顶点的邻居点未遍历完，现在正在遍历它的邻居点，状态值为 1
    //   （3）已访问，遍历到该顶点，而且该顶点的邻居点已遍历完，状态值为 -1
    private int[] visited = null;
    
    // 从图中的顶点 v 出发，进行深度遍历。如果遍历过程，遇到环，则返回 true；否则，返回 false
    private boolean dfs(int v) {
        visited[v] = 1; // 正在访问顶点 v，所以将访问状态标记成 1
        
        // 访问 v 的邻居点，继续深度遍历 
        List<Integer> adjs = adjList.get(v);
        for (int adjV : adjs) {
            // 判断是否回到环的起点处，即判断邻居点的状态是否为 1
            if (visited[adjV] == 1) {
                return true; // adjV 已是 “访问中” 的状态，则说明存在环，并且回到了 adjV 这个起点处
            }
            
            // 判断邻居点是否访问过，如果未访问过，则继续遍历该顶点
            if (visited[adjV] == 0 && dfs(adjV)) {
                return true; // adjV 未访问过，访问该顶点后，发现存在环，则返回 true
            }
        }
        
        // 已经遍历完顶点 v 和它的邻居点，所以可以将 v 的访问状态标记成 -1
        visited[v] = -1;
        
        return false; // 不存在环
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        // 创建邻接表
        adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            adjList.add(new ArrayList<>());
        }
        for (int[] e : prerequisites) {
            adjList.get(e[1]).add(e[0]);
        }
        
        // 对图进行深度遍历 
        for (int i = 0; i < numCourses; ++i) {
            if (visited[i] == 0 && dfs(i)) { // 顶点 i 没有遍历过，则从该点出发进行深度遍历
                return false; // 如果深度遍历的结果返回 true，则说明存在环，无法完成所有课程的学习，直接返回 false
            }
        }

        return true;
    }
}



