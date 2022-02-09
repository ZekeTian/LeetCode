package list;

import datastructure.ListNode;
import datastructure.ListUtil;


/**
 * https://leetcode.com/problems/insertion-sort-list/
 * 
 * 题目描述：给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 *        插入排序 算法的步骤:
 *          （1）插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 *          （2）每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 *          （3）重复直到所有输入数据插入完为止。
 * 
 * 限制条件：
 *  （1）列表中的节点数在 [1, 5000]范围内
 *  （2）-5000 <= Node.val <= 5000
 *  
 * 示例：
 *  输入: head = [4,2,1,3]
 *  输出: [1,2,3,4]
 *
 */
public class _147_InsertionSortList {

    public static void main(String[] args) {
        int[] nums = { 4, 2, 1, 3 };
        ListNode head = ListUtil.createList(nums);
        
        _147Solution solution = new _147Solution();
        
        head = solution.insertionSortList(head);

        ListUtil.print(head);
    }
}


class _147Solution {
    
    // 将 node 节点插入到 head 链表中合适的位置
    private void insert(ListNode head, ListNode node) {
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (node.val < cur.val) {
                break; // node 节点寻找到合适的位置，则终止寻找
            }
            pre = cur;
            cur = cur.next;
        }
        
        // 将 node 节点插入到链表中
        node.next = cur;
        pre.next = node;
    }
    
    public ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE); // 有序链表的头节点
        ListNode cur = head; // 无序链表中当前待插入的节点

        // 逐个将链表中的节点插入到有序链表中
        while (cur != null) {
            ListNode next = cur.next; // 因为在将 cur 节点插入到有序链表时，cur 会与下一个节点断开，因此用 next 记录下一个节点
            insert(dummyHead, cur);
            cur = next; // 到无序链表中的下一个节点
        }
        
        return dummyHead.next;
    }
}