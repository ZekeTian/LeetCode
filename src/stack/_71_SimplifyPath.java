package stack;

import java.util.Stack;

/**
 * 
 * 题目描述：给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 *        在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。
 *        任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 *        
 *        请注意，返回的 规范路径 必须遵循下述格式：
 *          （1）始终以斜杠 '/' 开头。
 *          （2）两个目录名之间必须只有一个斜杠 '/' 。
 *          （3）最后一个目录名（如果存在）不能 以 '/' 结尾。
 *          （4）此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 *        返回简化后得到的 规范路径 。
 * 
 * 限制条件：
 *  （1）1 <= path.length <= 3000
 *  （2）path 由英文字母，数字，'.'，'/' 或 '_' 组成。
 *  （3）path 是一个有效的 Unix 风格绝对路径。
 *  
 * 示例：
 *   示例 1
 *      输入：path = "/home/"
 *      输出："/home"
 *      解释：注意，最后一个目录名后面没有斜杠。 
 *
 *  示例 2
 *      输入：path = "/home//foo/"
 *      输出："/home/foo"
 *      解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 *
 *   示例 3
 *      输入：path = "/a/./b/../../c/"
 *      输出："/c"
 */
public class _71_SimplifyPath {

    public static void main(String[] args) {
        // test case1, output: /home
//        String path = "/home/";
        
        // test case2, output: /home/foo
//        String path = "/home//foo/";

        // test case3, output: /usr
//        String path = "/usr/local/..";
        
        // test case4, output: /c
        String path = "/a/./b/../../c/";
        
        _71Solution solution = new _71Solution();
        
        
        System.out.println(solution.simplifyPath(path));
    }
}

/**
 * 提取出路径中各级目录或文件名（即两个 "/" 之间的字符）。如果目录或文件名不是 ".."，则将当前目录或文件名压入栈中；否则，弹出栈顶元素。
 */
class _71Solution {
    
    public String simplifyPath(String path) {
        int left = 0; // 左边 "/" 的下标
        Stack<String> stack = new Stack<>();
        
        for (int i = 1 /* 因为第一个是 "/"，所以 i 从 1 开始 */; i <= path.length(); ++i) {
            String str = null;
            if (i == path.length() /* 已经遍历完 path，则需要提取最后一级的目录或文件名 */ 
                    || '/' == path.charAt(i) /* 遇到 "/"，则当前级的目录或文件名已经遍历完毕 */) {
                str = path.substring(left + 1, i);
                left = i;
            }
            
            if (null == str || "".equals(str) /* 未成功提取出目录或文件名，则继续遍历  */ 
                    || ".".equals(str) /* 提取的是当前目录，则无需处理，继续下一次遍历 */) { // 未成功提取出，则继续遍历 
                continue;
            }
            
            if (!"..".equals(str)) {
                stack.push(str); // 提取出目录或文件名有效，则压入栈中
            } else if (!stack.isEmpty()) { // str 是 ".."，并且栈不为空（即可以返回上一级），则弹出栈顶元素，返回到上一级
                stack.pop();
            }
        }
        
        // 弹出栈中最终剩余的所有元素，拼接成简化后的路径
        String res = "";
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        
        return (res.length() == 0 ? "/" : res);
    }
}
