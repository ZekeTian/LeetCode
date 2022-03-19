package list;

import list._138Solution.Node;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * 
 * 题目描述：给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *         构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 *         新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 *         复制链表中的指针都不应指向原链表中的节点 。
 *         例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。
 *         那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 *         返回复制链表的头节点。
 *         用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *          （1）val：一个表示 Node.val 的整数。
 *          （2）random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *         你的代码只接受原链表的头节点 head 作为传入参数。
 *         
 * 限制条件：
 *  （1）0 <= n <= 1000
 *  （2）-10^4 <= Node.val <= 10^4
 *  （3）Node.random 为 null 或指向链表中的节点。
 *
 * 示例：
 *  示例 1
 *      输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *      输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *      
 *  示例 2
 *      输入：head = [[1,1],[2,1]]
 *      输出：[[1,1],[2,1]]
 *      
 *  示例 3
 *      输入：head = [[1,null],[3,0],[3,null]]
 *      输出：[[1,null],[3,0],[3,null]]
 *
 */
public class _138_CopyListWithRandomPointer {

    public static void main(String[] args) {
        // head = [[1,null],[3,0],[3,null]]
        Node head = new Node(1);
        Node node1 = new Node(3);
        head.next = node1;
        
        Node node2 = new Node(3);
        node1.next = node2;
        node1.random = head;
        
        _138Solution solution = new _138Solution();
        
        Node newHead = solution.copyRandomList(head);
        
        Node cur = newHead; 
        while (cur != null) {
            String random = (cur.random == null) ? "null" : cur.random.val + "";
            System.out.println(cur.val + ", " + random);
            cur = cur.next;
        }
    }
}

/**
 * 本题和“剑指 Offer” 中的第 35 题一样，有两种解法。
 * 第一种，使用 Map 记录 “旧节点 -> 新节点” 的映射关系，然后利用该 Map 复制 Random 指针。 
 * 第二种，不使用 Map，将复制的新节点存储在原来节点的后面，然后三次循环。
 * 在这里，将使用第二种解法。
 */
class _138Solution {
    
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    
    public Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
        
        // 第一次循环，复制节点，并将复制的新节点保存在原来节点的后面
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        
        // 第二次循环，复制 random 节点的指向关系
        cur = head;
        while (cur != null) {
            Node newCur = cur.next; // cur 对应的新节点
            if (cur.random != null) {
                newCur.random = cur.random.next; // newCur 对应 random 节点是 cur 的 random 节点的下一个
            }
            cur = newCur.next;
        }
        
        // 第三次循环，分离新旧链表节点
        Node newHead = head.next;
        cur = head;
        while (cur != null) {
            Node newCur = cur.next;
            cur.next = newCur.next;
            if (cur.next != null) {
                newCur.next = cur.next.next;
            } else {
                newCur.next = null;
            }
            cur = cur.next;
        }
        
        return newHead;
    }
}
