package list;

import java.util.HashSet;
import java.util.Set;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/linked-list-cycle/submissions/
 *
 * 题目描述：给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *        为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
 *        注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况（实际上 pos 值仅只用于创建带环的链表，不会作为 hasCycle 方法的参数）。
 * 
 * 条件限制：
 *  （1）链表中节点的数目范围是 [0, 10^4]
 *  （2）-10^5 <= Node.val <= 10^5
 *  （3）pos 为 -1 或者链表中的一个 有效索引 。
 * 
 * 示例：
 *  Input: head = [3,2,0,-4], pos = 1
 *  Output: true
 *  解释：链表中有一个环，其尾部连接到第二个节点。
 */
public class _142_LinkedListCycleII {

    public static void main(String[] args) {
        // test case1, output: tail connects to node index 1 
        //        int[] nums = { 3, 2, 0, -4 };
        //        ListNode head = ListUtil.createList(nums);
        //        ListNode tail = ListUtil.lastElement(head);
        //        ListNode node = ListUtil.get(head, 1);
        //        tail.next = node;

        // test case2, output: tail connects to node index 0
        //        int[] nums = { 1, 2 };
        //        ListNode head = ListUtil.createList(nums);
        //        ListNode tail = ListUtil.lastElement(head);
        //        ListNode node = ListUtil.get(head, 0);
        //        tail.next = node;

        // test case3, output: no cycle
        int[] nums = { 1 };
        ListNode head = ListUtil.createList(nums);

        _142Solution1 solution = new _142Solution1();

        ListNode entrance = solution.detectCycle(head);
        if (null == entrance) {
            System.out.println("no cycle");
        } else {
            int index = ListUtil.index(head, entrance);
            System.out.println("tail connects to node index " + index);
        }
    }
}

/**
 * 解法一：使用 Set 
 */
class _142Solution1 {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = head;

        while (null != cur) {
            if (set.contains(cur)) {
                return cur;
            }

            set.add(cur);
            cur = cur.next;
        }

        return null;
    }
}
