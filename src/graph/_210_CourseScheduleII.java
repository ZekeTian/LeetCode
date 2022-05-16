package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 * 
 * 题目描述：现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
 *         给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 *         例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 *         返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。
 *         如果不可能完成所有课程，返回 一个空数组 。
 * 
 * 限制条件：
 *  （1）1 <= numCourses <= 2000
 *  （2）0 <= prerequisites.length <= numCourses * (numCourses - 1)
 *  （3）prerequisites[i].length == 2
 *  （4）0 <= ai, bi < numCourses
 *  （5）ai != bi
 *  （6）所有[ai, bi] 互不相同
 * 
 * 示例：
 *  示例 1
 *      输入：numCourses = 2, prerequisites = [[1,0]]
 *      输出：[0,1]
 *      解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 *      
 *  示例 2
 *      输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 *      输出：[0,2,1,3]
 *      解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *           因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 *      
 *  示例 3
 *      输入：numCourses = 1, prerequisites = []
 *      输出：[0]      
 *
 */
public class _210_CourseScheduleII {

    
    public static void main(String[] args) {
        // test case1, output: [0, 1]
//        int numCourses = 2;
//        int[][] prerequisites = { { 1, 0 } };
        
        // test case2, output: [0, 2, 1, 3]
//        int numCourses = 4;
//        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
        
        // test case3, output: [0]
      int numCourses = 1;
      int[][] prerequisites = { };
      
        
        _210Solution1 solution = new _210Solution1();
        
        
        System.out.println(Arrays.toString(solution.findOrder(numCourses, prerequisites)));
    }
    
}

/**
 * 解法一：深度优先遍历，思路和第 207 题一样。 
 */
class _210Solution1 {
    
    private int[] order = null;
    private int idx = 0; // order 的下标 
    private List<List<Integer>> list = null;
    private int[] visited = null; // 标记顶点的访问状态，0 表示未访问，1 表示访问中，-1 表示已访问
    
    // 从顶点 v 出发，进行深度遍历，如果找到环，则返回 true
    private boolean dfs(int v) {
        visited[v] = 1; // 将顶点 v 标记成正在访问
        
        // 遍历 v 的邻居点
        List<Integer> adjList = list.get(v);
        for (int adjV : adjList) {
            if (visited[adjV] == 1) {
                return true; // adjV 再次被访问到，则说明存在环
            }
            
            if (visited[adjV] == 0 && dfs(adjV)) {
                return true; // 从 adjV 出发，存在环
            }
        }
        
        // v 已经访问完，则更新 v 的标记状态，并且将 v 放进 order 列表中。
        // 但是，需要注意的是：这种生成顺序的方式，是先将 v 的邻居点放进 order 中，然后再将 v 放进 order 中，和实际的顺序刚好相反，所以最好需要反转 order 
        visited[v] = -1;
        order[idx++] = v;
        
        return false;
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        this.order = new int[numCourses];
        this.visited = new int[numCourses];
        this.list = new ArrayList<>();
        
        // 生成邻接表
        for (int i = 0; i < numCourses; ++i) {
            list.add(new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            list.get(edge[1]).add(edge[0]);
        }
        
        // 深度遍历
        for (int i = 0; i < numCourses; ++i) {
            if (visited[i] == 0 && dfs(i)) {
                return new int[] {};
            }
        }
        
        // 反转 order，得到正确的顺序
        reverse(order);
        
        return order;
    }

    private void reverse(int[] arr) {
        for (int l = 0, r = arr.length - 1; l < r; ++l, --r) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
    
}
