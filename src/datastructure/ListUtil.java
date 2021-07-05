package datastructure;

/**
 * 链表的工具类，用于创建链表、打印链表
 *
 */
public class ListUtil {

    public static ListNode createList(int[] nums) {
        ListNode head = null;
        if (0 == nums.length) {
            return null;
        }

        head = new ListNode(nums[0]);
        ListNode pre = head;
        for (int i = 1; i < nums.length; ++i) {
            pre.next = new ListNode(nums[i]);
            pre = pre.next;
        }

        return head;
    }

    /**
     * 获取链表中第一个值为 val 的节点
     * @param head 链表的头节点
     * @param val 待查找节点的值
     * @return 链表中第一个值为 val 的节点
     */
    public static ListNode first(ListNode head, int val) {
        ListNode cur = head;

        while (null != cur) {
            if (cur.val == val) {
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }

    public static void print(ListNode head) {
        ListNode cur = head;
        System.out.print("head: ");
        while (cur != null) {
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }

        System.out.println("null");
    }
}
