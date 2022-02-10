package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 * 
 * 题目描述：给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 *        第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 *        请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 *        你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
 * 
 * 限制条件：
 *  （1）n 表示链表中的节点数
 *  （2）0 <= n <= 10^4
 *  （3）-10^6 <= Node.val <= 10^6
 *
 * 示例：
 *  输入: head = [1,2,3,4,5]
 *  输出: [1,3,5,2,4]
 *  
 */
public class _328_OddEvenLinkedList {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };

        ListNode head = ListUtil.createList(nums);
        
        _328Solution solution = new _328Solution();
        
        head = solution.oddEvenList(head);
        
        ListUtil.print(head);
    }
}


class _328Solution {
    
    public ListNode oddEvenList(ListNode head) {
        ListNode dummyHeadOdd = new ListNode();
        ListNode dummyHeadEven = new ListNode();
        ListNode curOdd = dummyHeadOdd;
        ListNode curEven = dummyHeadEven;
        ListNode cur = head;
        int count = 0;
        
        while (null != cur) {
            ++count;
            if (0 == count % 2) {
                curEven.next = cur;
                curEven = curEven.next;
            } else {
                curOdd.next = cur;
                curOdd = curOdd.next;
            }
            cur = cur.next;
        }

        curOdd.next = dummyHeadEven.next; 
        curEven.next = null;
        
        return dummyHeadOdd.next;
    }
}

