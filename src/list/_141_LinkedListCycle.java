package list;

import java.util.HashSet;
import java.util.Set;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/linked-list-cycle/submissions/
 *
 * 题目描述：给定一个链表，判断链表中是否有环。如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
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
//                int[] nums = { 1 };
//                ListNode head = ListUtil.createList(nums);

        //        _141Solution1 solution = new _141Solution1();
        //        _141Solution2 solution = new _141Solution2();
        _141Solution3 solution = new _141Solution3();

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

/**
 * 解法三：使用计数器计数循环次数
 */
class _141Solution3 {
    private static final int MAX_NUM = 10000;

    public boolean hasCycle(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = dummyHead;
        int count = 0;

        while (null != cur.next) {
            cur = cur.next;
            ++count;

            if (count > MAX_NUM) { // 正常情况下，循环次数不会大于最大值；只有当存在环时，会重复遍历元素，从而使得循环次数大于最大值
                return true;
            }
        }

        return false;
    }
}
