package algorithm.链表.单向链表;

import bean.ListNode;

public class 反转链表 {
    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // [5,4,3,2,1]
        ListNode res = reverseList(head);
        System.out.println(res);
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 链表题目中，dummy这个初始节点一定要指向head
        ListNode dummy = new ListNode();
        dummy.next = head;

        // 初始的时候：0->1->2->3->4->5，pre是0，cur是1，next是2
        // 第一轮的时候：0->2->1->3->4->5，pre是0，cur还是1，next是3
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur.next != null) {
            // 当前节点的下一个是next，也就是需要反转的节点
            ListNode next = cur.next;
            cur.next = next.next;
            // 注意这里只能用pre.next，不能用cur，因为pre.next指向第一个节点，cur永远指向1，也就是需要反转的前一个节点
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }
}
