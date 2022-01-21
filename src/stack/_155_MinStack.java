package stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/min-stack/
 * 
 * 题目描述：设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *        push(x) —— 将元素 x 推入栈中。
 *        pop() —— 删除栈顶的元素。
 *        top() —— 获取栈顶元素。
 *        getMin() —— 检索栈中的最小元素。
 *  
 * 限制条件：pop、top 和 getMin 操作总是在 非空栈 上调用。
 *        
 * 示例：
 *   输入：
 *      ["MinStack","push","push","push","getMin","pop","top","getMin"]
 *      [[],[-2],[0],[-3],[],[],[],[]]
 *   输出：
 *      [null,null,null,null,-3,null,0,-2]
 *   解释：
 *      MinStack minStack = new MinStack();
 *      minStack.push(-2);
 *      minStack.push(0);
 *      minStack.push(-3);
 *      minStack.getMin();   --> 返回 -3.
 *      minStack.pop();
 *      minStack.top();      --> 返回 0.
 *      minStack.getMin();   --> 返回 -2.
 *      
 */
public class _155_MinStack {

    public static void main(String[] args) {
//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin());   // 返回 -3.
//        minStack.pop();
//        System.out.println(minStack.top());     // 返回 0.
//        System.out.println(minStack.getMin());  // 返回 -2.
        
        MinStack minStack = new MinStack();
        
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        
        minStack.pop();
        System.out.println(minStack.getMin()); // -1024
        minStack.pop();
        System.out.println(minStack.getMin()); // -1024
        minStack.pop();
        System.out.println(minStack.getMin()); // 512
    }
}


class MinStack {
    
    private Stack<Integer> dataStack = null;
    private Stack<Integer> minStack = null;

    public MinStack() {
        this.dataStack = new Stack<Integer>();
        this.minStack = new Stack<Integer>();
    }
    
    public void push(int val) {
        if (minStack.isEmpty()) {
            dataStack.push(val);
            minStack.push(val);
            return;
        }
        
        dataStack.push(val);
        Integer min = minStack.peek();
        if (val <= min) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        if (dataStack.isEmpty()) {
            return;
        }
        
        int peek = dataStack.peek();
        int min = minStack.peek();
        
        dataStack.pop();
        if (peek == min) {
            minStack.pop();
        }
    }
    
    public int top() {
        if (dataStack.isEmpty()) {
            return -1;
        }
        
        return dataStack.peek();
    }
    
    public int getMin() {
        if (minStack.isEmpty()) {
            return -1;
        }
        
        return minStack.peek();
    }
}
