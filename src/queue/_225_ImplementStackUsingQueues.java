package queue;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/
 * 
 * 题目描述：请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *         实现 MyStack 类：
 *         （1）void push(int x) 将元素 x 压入栈顶。
 *         （2）int pop() 移除并返回栈顶元素。
 *         （3）int top() 返回栈顶元素。
 *         （4）boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *         
 *         注意：
 *          （1）你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 *          （2）你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可
 *         
 * 限制条件：
 *  （1） 1 <= x <= 9
 *  （2）最多调用100 次 push、pop、top 和 empty
 *  （3）每次调用 pop 和 top 都保证栈不为空
 *  
 *  
 * 示例：
 *  输入：
 *      ["MyStack", "push", "push", "top", "pop", "empty"]
 *      [[], [1], [2], [], [], []]
 *  输出：
 *      [null, null, null, 2, 2, false]
 *  解释：
 *      MyStack myStack = new MyStack();
 *      myStack.push(1);
 *      myStack.push(2);
 *      myStack.top(); // 返回 2
 *      myStack.pop(); // 返回 2
 *      myStack.empty(); // 返回 False
 *
 */
public class _225_ImplementStackUsingQueues {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // 返回 2
        System.out.println(myStack.pop()); // 返回 2
        System.out.println(myStack.empty()); // 返回 False
    }
}

/**
 * 使用两个队列，一个队列（topQueue）只存储栈顶元素，方便取栈顶元素；另一个队列（dataQueue）存储剩余的元素。
 * topQueue 最多只存储一个元素，即栈顶元素。
 * push 操作：
 *  （1）如果 topQueue 中存在元素，则将 topQueue 中的元素移动到 dataQueue 中
 *  （2）将新元素加入到 topQueue 中
 * 
 * pop 操作：
 *  （1）从 topQueue 中取出元素
 *  （2）再将 dataQueue 中的元素全部移动到 topQueue
 *  （3）将 topQueue 中的元素移动到 dataQueue 中，只保留队尾的一个元素（即栈顶元素）
 *  示例：
 *      input: 1, 2, 3, 4, 5
 *      stack bottom: 1, 2, 3, 4, 5 top
 *      
 *      topQueue: 5
 *      dataQueue: 1, 2, 3, 4
 *      ↓ 从 topQueue 中取出元素，获得栈顶元素
 *      topQueue: 
 *      dataQueue: 1, 2, 3, 4
 *      ↓ 将 dataQueue 中的元素全部移动到 topQueue
 *      topQueue: 1, 2, 3, 4
 *      dataQueue: 
 *      ↓ 将 topQueue 中的元素移动到 dataQueue 中，只保留队尾的一个元素
 *      topQueue: 4
 *      dataQueue: 1, 2, 3
 */
class MyStack {
    private LinkedList<Integer> topQueue = null; // 存储栈顶元素，最多只存储一个元素
    private LinkedList<Integer> dataQueue = null; // 存储栈中除栈顶之外的元素
    
    public MyStack() {
        this.topQueue = new LinkedList<>();
        this.dataQueue = new LinkedList<>();
    }
    
    public void push(int x) {
        if (!topQueue.isEmpty()) {
            dataQueue.addLast(topQueue.poll()); // topQueue 中有元素，则将其移动到 dataQueue 中，从而清空 topQueue，便于存储新的栈顶元素
        }
        topQueue.add(x);
    }
    
    public int pop() {
        // 取出栈顶元素
        int ret = topQueue.poll();
        
        // 将 dataQueue 中的元素全部移动到 topQueue 中
        while (!dataQueue.isEmpty()) {
            topQueue.addLast(dataQueue.poll());
        }
        
        // 将 topQueue 中的元素移动到 dataQueue 中，只保留队尾的一个元素（即栈顶元素）
        while (topQueue.size() > 1) {
            dataQueue.addLast(topQueue.poll());
        }
        
        return ret;
    }
    
    public int top() {
        return topQueue.peekFirst();
    }
    
    public boolean empty() {
        return topQueue.isEmpty() && dataQueue.isEmpty();
    }
}


