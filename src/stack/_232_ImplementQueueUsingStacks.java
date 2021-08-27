package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/
 * 
 * 题目描述：请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *        实现 MyQueue 类：
 *          void push(int x) 将元素 x 推到队列的末尾
 *          int pop() 从队列的开头移除并返回元素
 *          int peek() 返回队列开头的元素
 *          boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * 限制条件：
 *  （1） 1 <= x <= 9
 *  （2）最多调用 100 次 push、pop、peek 和 empty 操作
 *  （3）所有的 pop、peek 操作保证合法
 */
public class _232_ImplementQueueUsingStacks {
    
    public static void main(String[] args) {
        _232MyQueue myQueue = new _232MyQueue();
        
        myQueue.push(1);
        myQueue.push(2);
        
        System.out.println(myQueue.peek()); // 1
        System.out.println(myQueue.pop()); // 1
        System.out.println(myQueue.empty()); // false
    }

}

class _232MyQueue {
    
    private Stack<Integer> inputStack = null;
    private Stack<Integer> outputStack = null;
    
    /** Initialize your data structure here. */
    public _232MyQueue() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inputStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outputStack.isEmpty()) {
            move();
        }
        
        return outputStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (outputStack.isEmpty()) {
            move();
        }
        
        return outputStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inputStack.isEmpty() && outputStack.isEmpty();
    }
    
    private void move() {
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
    }
}