package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/partition-list/
 * 
 * 题目描述：给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *        你应当 保留 两个分区中每个节点的初始相对位置。
 * 
 * 限制条件：
 *  （1）链表中节点的数目在范围 [0, 200] 内
 *  （2）-100 <= Node.val <= 100
 *  （3）-200 <= x <= 200
 * 
 * 示例：
 *  输入：head = [1,4,3,2,5,2], x = 3
 *  输出：[1,2,2,4,3,5]
 *
 */
public class _86_PartitionList {

    public static void main(String[] args) {
        int[] nums = { 1, 4, 3, 2, 5, 2};
        int x = 3;
        
        ListNode head = ListUtil.createList(nums);
        
        _86Solution solution = new _86Solution();
        
        head = solution.partition(head, x);
        
        ListUtil.print(head);
    }
}

/**
 * 将原来的链表拆分成两个链表，一个链表存储小于指定值的节点，另一个链表存储大于等于指定值的节点，然后合并两个链表即可。
 */
class _86Solution {
    
    public ListNode partition(ListNode head, int x) {
        ListNode dummyHeadSmall = new ListNode(); // 存储小于 x 的节点
        ListNode dummyHeadLarge = new ListNode(); // 存储大于等于 x 的节点
        ListNode curSmall = dummyHeadSmall; // 小于 x 的链表的尾节点
        ListNode curLarge = dummyHeadLarge; // 大于等于 x 的链表的尾节点
        ListNode cur = head;
        
        while (null != cur) {
            if (cur.val < x) {
                curSmall.next = cur;
                curSmall = curSmall.next;
            } else {
                curLarge.next = cur;
                curLarge = curLarge.next;
            }
            
            cur = cur.next;
        }
        
        curSmall.next = dummyHeadLarge.next;
        curLarge.next = null;
        
        return dummyHeadSmall.next;
    }
}