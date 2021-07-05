package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 * 
 * 题目描述：给定单向链表中的一个节点，在链表中删除该节点。你不能访问链表的头节点，但是可以直接访问待删除的节点。同时，确保待删除的链表节点不是是尾节点。
 * 
 * 条件限制：
 * （1）给定链表的长度的范围：[2, 1000]
 * （2）-1000 <= Node.val <= 1000
 * （3）链表中每个节点的值都是不同的
 * （4）待删除的节点不是链表的尾节点
 * 
 * 示例：
 *  Input: head = [4,5,1,9], node = 5
 *  Output: [4,1,9]
 *  给定链表中第二个节点，删除该节点后，链表应该为：4->1->9->null
 *  
 */
public class _237_DeleteNodeInLinkedList {
    
    public static void main(String[] args) {
        int[] nums = {4, 5, 1, 9};
        
        ListNode head = ListUtil.createList(nums);
        ListUtil.print(head);

        new _237Solution().deleteNode(ListUtil.first(head, 4));
        
        ListUtil.print(head);
    }
}

/**
 * 因为无法访问链表的头节点，只能直接访问待删除的节点，所以无法获取待删除节点的前一个节点，因此常规的链表节点删除方法无效。
 * 在此题目中，可以通过改变节点的值，然后使用常规的链表节点删除方法解决。
 *
 */
class _237Solution {
    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val; // 将 next 的值赋值给 node，从而将待删除的节点变成 next
        
        node.next = next.next;
        next.next = null;
    }
}
