package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * 
 * 题目描述：数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 限制条件：1 <= n <= 8
 * 
 * 示例：
 *  示例 1
 *      输入：n = 3
 *      输出：["((()))","(()())","(())()","()(())","()()()"]
 *      
 *  示例 2
 *      输入：n = 1
 *      输出：["()"]
 *
 */
public class _22_GenerateParentheses {

    public static void main(String[] args) {
        // test case1, output: ["()"]
//        int n = 1;
        
        // test case2, output: ["((()))","(()())","(())()","()(())","()()()"]
        int n = 3;
        
        _22Solution solution = new _22Solution();
        
        
        System.out.println(solution.generateParenthesis(n));
    }
}

/**
 * 解法思路：
 * （1）利用回溯写出基础版本（该版本中包含非法括号字符串）
 * （2）在基础版本中，加上过滤条件，将非法的情况剪枝掉
 * 
 * 括号字符串非法示例：)))(((，即右括号在左括号前。因此，要想过滤掉非法情况，需要在放右括号时进行判断。
 * 在放右括号时，要保证前面有多余的左括号，从而可以匹配当前的右括号，如：(()，最前面的一个左括号是多余的，所以当前可以放置右括号。
 */
class _22Solution {
    
    private int n = 0;
    private List<String> list = new ArrayList<>();
    
    // left 表示左括号已经使用的个数，right 表示右括号已经使用的个数，str 表示当前生成的括号字符串
    private void generateParenthesis(int left, int right, String str) {
        if (left == n && right == n) {
            list.add(str);
            return;
        }
        
        // 先放置左括号
        if (left < n) { // 左括号的个数未使用完
            generateParenthesis(left + 1, right, str + "("); 
        }
        
        if (right < n /* 右括号的个数未使用完 */
            && (left > right) /* 前面有多余的左括号，从而保证不会生成非法括号字符串 */) {
            generateParenthesis(left, right + 1, str + ")");
        }
    }
    
    public List<String> generateParenthesis(int n) {
        this.n = n;
        
        generateParenthesis(0, 0, "");
        
        return list;
    }
}

