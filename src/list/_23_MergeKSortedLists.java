package list;

import java.util.PriorityQueue;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * 
 * 题目描述：给你一个链表数组，每个链表都已经按升序排列。
 *        请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 
 * 限制条件：
 *  （1）k == lists.length
 *  （2）0 <= k <= 10^4
 *  （3）0 <= lists[i].length <= 500
 *  （4）-10^4 <= lists[i][j] <= 10^4
 *  （5）lists[i] 按 升序 排列
 *  （6）lists[i].length 的总和不超过 10^4
 * 
 * 示例：
 *  输入：lists = [[1,4,5],[1,3,4],[2,6]]
 *  输出：[1,1,2,3,4,4,5,6]
 *  解释：链表数组如下：
 *      [
 *        1->4->5,
 *        1->3->4,
 *        2->6
 *      ]
 *      将它们合并到一个有序链表中得到。
 *      1->1->2->3->4->4->5->6
 */
public class _23_MergeKSortedLists {

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        lists[0] = ListUtil.createList(new int[] {1, 4, 5});
        lists[1] = ListUtil.createList(new int[] {1, 3, 4});
        lists[2] = ListUtil.createList(new int[] {2, 6});
        
        _23Solution2 solution = new _23Solution2();
        
        ListNode head = solution.mergeKLists(lists);
        
        ListUtil.print(head);
    }
}

/**
 * 解法一：循环多次，每次合并两个链表
 */
class _23Solution1 {
    
    // 按照升序合并 head1、head2 两个链表
    private ListNode mergeList(ListNode head1, ListNode head2) {
        if (null == head1) {
            return head2;
        }
        if (null == head2) {
            return head1;
        }
        
        if (head1.val < head2.val) {
            head1.next = mergeList(head1.next, head2);
            return head1;
        }
            
        head2.next = mergeList(head1, head2.next);
        return head2;
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        
        // 循环多次，每次合并两个链表，然后将合并的新链表与下一个链表继续合并
        for (ListNode list : lists) {
            head = mergeList(head, list);
        }
        
        return head;
    }
}

/**
 * 解法二：使用最小堆。利用最小堆取 k 个链表当前头节点中的最小节点。
 */
class _23Solution2 {
    
    public ListNode mergeKLists(ListNode[] lists) {
        // 最小堆，用于存储每个链表中当前的头节点
        PriorityQueue<ListNode> heap = new PriorityQueue<>((node1, node2) -> (node1.val - node2.val));

        for (ListNode head : lists) {
            if (null != head) {
                heap.add(head);
            }
        }
        
        ListNode dummyHead = new ListNode(); // 合并后链表的虚拟头节点
        ListNode tail = dummyHead; // 合并后链表的尾节点
        while (!heap.isEmpty()) {
            // 取出堆中最小的元素（即当前各个链表的头节点中最小的节点），然后再加入到合并的链表中
            ListNode min = heap.poll();
            tail.next = min; // 加入到合并的新链表中
            
            if (null != min.next) {
                heap.add(min.next); // 因为 min 对应的节点已经加入到链表中，则 min 所在链表的头节点应当是 min.next，也需要将 min.next 添加到最小堆中
            }
            
            min.next = null; // 将 min 节点与原来所在的链表分离开
            
            // tail 指针后移
            tail = min;
        }
        
        return dummyHead.next;
    }
}