package queue;

/**
 * https://leetcode.com/problems/design-circular-queue/
 * 
 * 题目描述：设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。
 *         它也被称为“环形缓冲器”。
 *         循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在
 *         队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *         你的实现应该支持如下操作：
 *          MyCircularQueue(k): 构造器，设置队列长度为 k 。
 *          Front: 从队首获取元素。如果队列为空，返回 -1 。
 *          Rear: 获取队尾元素。如果队列为空，返回 -1 。
 *          enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 *          deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 *          isEmpty(): 检查循环队列是否为空。
 *          isFull(): 检查循环队列是否已满。
 *          
 * 限制条件：
 *  （1）所有的值都在 0 至 1000 的范围内；
 *  （2）操作数将在 1 至 1000 的范围内；
 *  （3）请不要使用内置的队列库。
 *  
 * 示例：
 *  MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 *  circularQueue.enQueue(1);  // 返回 true
 *  circularQueue.enQueue(2);  // 返回 true
 *  circularQueue.enQueue(3);  // 返回 true
 *  circularQueue.enQueue(4);  // 返回 false，队列已满
 *  circularQueue.Rear();  // 返回 3
 *  circularQueue.isFull();  // 返回 true
 *  circularQueue.deQueue();  // 返回 true
 *  circularQueue.enQueue(4);  // 返回 true
 *  circularQueue.Rear();  // 返回 4
 *
 *
 */
public class _622_DesignCircularQueue {

    public static void main(String[] args) {
//        MyCircularQueue1 circularQueue = new MyCircularQueue1(3); // 设置长度为 3
        
        MyCircularQueue2 circularQueue = new MyCircularQueue2(3); // 设置长度为 3
        
        System.out.println(circularQueue.enQueue(1)); // true
        System.out.println(circularQueue.enQueue(2)); // true
        System.out.println(circularQueue.enQueue(3)); // true
        System.out.println(circularQueue.enQueue(4)); // false
        System.out.println(circularQueue.Rear()); // 3
        System.out.println(circularQueue.isFull()); // true
        System.out.println(circularQueue.deQueue()); // true
        System.out.println(circularQueue.enQueue(4)); // true
        System.out.println(circularQueue.Rear()); // 4
        
    }
    
}

/**
 * 实现方式一：利用数组实现
 */
class MyCircularQueue1 {
    
    private int[] data = null; // 真正存储元素的数组
    private int capacity = 0; // 队列的容量
    private int size = 0; // 队列中元素个数
    private int front = 0; // 队头下标
    private int rear = -1; // 队尾下标
    
    
    public MyCircularQueue1(int k) {
        this.data = new int[k];
        this.capacity = k;
    }
    
    // 从队尾处向队列中添加元素，如果成功插入则返回真。
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        
        // 队尾指针向后移动，得到一个新的空位置，然后添加元素
        ++size;
        rear = (rear + 1) % data.length;
        data[rear] = value;
        
        return true;
    }
    
    // 从队头处删除一个元素，如果成功删除则返回真。
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        
        // 删除队头元素，然后队头指针向后移动
        --size;
        front = (front + 1) % data.length;
        
        return true;
    }
    
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        
        return data[front];
    }
    
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        
        return data[rear];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return capacity == size;
    }
    
}

/**
 * 实现方式二：利用循环链表实现
 */
class MyCircularQueue2 {

    private class ListNode {
        ListNode next;
        int val;
        
        ListNode(ListNode next, int val) {
            this.next = next;
            this.val = val;
        }
        
        ListNode(int val) {
            this(null, val);
        }
    }
    
    private ListNode dummyHead = null; // 虚拟头节点
    private int capacity = 0; // 队列的容量
    private int size = 0; // 队列中实际的元素个数
    private ListNode tail = null; // 队尾节点
    
    public MyCircularQueue2(int k) {
        this.capacity = k;
        this.dummyHead = new ListNode(0);
        this.tail = dummyHead;
    }
    
    // 队尾处添加元素
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        
        ++size;
        tail.next = new ListNode(value); // 将新元素添加到队尾后面
        tail = tail.next; // 队尾指针向后移动
        tail.next = dummyHead.next; // 队尾指向队头
        
        return true;
    }
    
    // 队头处移除元素
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        
        --size;
        if (isEmpty()) { // 移除队头元素后，队列为空
            dummyHead.next = null;
            tail = dummyHead;
        } else { // 移除队头元素后，队列不为空
            dummyHead.next = dummyHead.next.next; // 删除队头
            tail.next = dummyHead.next; // 队尾指向新的队头
        }
        
        return true;
    }
    
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        
        return dummyHead.next.val;
    }
    
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        
        return tail.val;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    
}

