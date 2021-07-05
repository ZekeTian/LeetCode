package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * 
 * 题目描述：给定一个链表，交换相邻的两个节点，并返回交换节点后的链表的头节点。不能修改节点的值
 * 
 * 条件限制：
 * （1）链表中节点个数的范围：[0, 100]
 * （2）0 <= Node.val <= 100
 * 示例：
 *  Input: head = [1,2,3,4]
 *  Output: [2,1,4,3]
 */
public class _24_SwapNodesInPairs {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4};
//        int[] nums = {};
        int[] nums = {1, 2, 3};
        ListNode head = ListUtil.createList(nums);
        
        ListUtil.print(head);
        
        head = new _24Solution().swapPairs(head);
        
        ListUtil.print(head);
        
    }
}

class _24Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode node1 = dummyHead.next;
        ListNode node2 = null;
        
        while (null != node1 /* 链表长度为偶数时，node1 = null */ 
                && null != node1.next /* 链表长度为奇数时， node1.next = null */) {
            node2 = node1.next;
            pre.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            
            // pre、node1 向后移动
            pre = node1;
            node1 = node1.next;
        }
        
        return dummyHead.next;
    }
}
