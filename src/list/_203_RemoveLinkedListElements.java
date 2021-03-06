package list;

/**
 * https://leetcode.com/problems/remove-linked-list-elements/
 * 
 * 题目描述：从一个整数链表中删除所有指定值的元素 示例：
 *  Input: 	1->2->6->3->4->5->6, val = 6 
 *  Output: 1->2->3->4->5
 */
public class _203_RemoveLinkedListElements {
	public static void main(String[] args) {
		ListNode node6 = new ListNode(6, null);
		ListNode node5 = new ListNode(5, node6);
		ListNode node4 = new ListNode(4, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(6, node3);
		ListNode node1 = new ListNode(1, node2);
//		ListNode node1 = new ListNode(1, null);
		ListNode head = new ListNode(1, node1);

		head = new _203Solution2().removeElements(head, 6);

		for (ListNode cur = head; cur != null; cur = cur.next) {
			System.out.println(cur.val);
		}

	}
}

/**
 * 解法一，使用循环遍历的方式删除指定值的元素
 */
class _203Solution1 {

	public ListNode removeElements(ListNode head, int val) {
		ListNode pre;
		ListNode cur;

		cur = head;
		// 删除链表中头部与 val 相等的部分
		while (cur != null && cur.val == val) {
			head = cur.next;
			cur.next = null;
			cur = head;
		}
		pre = head;

		// 删除链表中间及尾部与 val 相等的部分
		while (cur != null) {
			if (cur.val == val) {
				pre.next = cur.next;
				cur.next = null;
				cur = pre.next;
			} else {
				pre = cur;
				cur = cur.next;
			}
		}

		return head;
	}
}

/**
 * 解法二，使用 “递归” 的方式删除指定值的元素
 */
class _203Solution2 {

	public ListNode removeElements(ListNode head, int val) {
		if (null == head) {
			return head;
		}

		// 删除链表中 [head.next, null) 范围内的指定值元素，然后返回删除元素后的链表。
		// 不断缩短链表的长短，直到链表中无元素，即满足递归结束的条件null == head
		ListNode ret = removeElements(head.next, val);

		if (head.val == val) {
			head.next = null;
			return ret; // 当 head 与待删除值相等时，则 head 对应的元素不需要添加到链表中，因此直接返回“删除元素后的链表”（即 ret）即可
		} else {
			head.next = ret; // 当 head 与待删除值不相等时，则 head 需要添加到链表中，因此需要将 head.next 与“删除元素后的链表”（即 ret） 连接起来
			return head;
		}
	}
}

/**
 *	链表节点类型  
 */
class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
