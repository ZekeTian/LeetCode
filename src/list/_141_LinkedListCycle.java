package list;

import java.util.HashSet;
import java.util.Set;

import datastructure.ListNode;
import datastructure.ListUtil;

public class _141_LinkedListCycle {

    public static void main(String[] args) {
        // test case1, output: true
        int[] nums = { 3, 2, 0, -4 };
        ListNode head = ListUtil.createList(nums);
        ListNode tail = ListUtil.lastElement(head);
        ListNode node = ListUtil.get(head, 1);
        tail.next = node;

        // test case2, output: true
        //        int[] nums = { 1, 2 };
        //        ListNode head = ListUtil.createList(nums);
        //        ListNode tail = ListUtil.lastElement(head);
        //        ListNode node = ListUtil.get(head, 0);
        //        tail.next = node;

        // test case3, output: false
        //        int[] nums = { 1 };
        //        ListNode head = ListUtil.createList(nums);

        _141Solution1 solution = new _141Solution1();

        System.out.println(solution.hasCycle(head));

    }
}

/**
 * 解法一：使用 map
 */
class _141Solution1 {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = dummyHead;

        while (null != cur.next) {
            if (set.contains(cur)) {
                // set 中已经存在 cur 节点，说明 cur 节点之前已经遍历过，则此时是因为存在环，从而第二次遍历到该节点
                return true;
            }

            set.add(cur);
            cur = cur.next;
        }

        return false;
    }
}

/**
 * 解法二：使用快慢指针，快指针一次走两步，慢指针一次走一步
 */
class _141Solution2 {
    public boolean hasCycle(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode slow = dummyHead;
        ListNode fast = dummyHead;

        // 慢指针 slow 走一步，快指针 fast 走两步
        while (null != slow.next && null != fast.next && null != fast.next.next) {
            slow = slow.next;
            fast = fast.next.next;

            // 快慢指针相遇，则说明有环
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}