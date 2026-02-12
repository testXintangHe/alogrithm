package algorithm.链表.单向链表;

import bean.ListNode;

public class 环路检测 {
    /**
     * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回 null。
     * <p>
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;
        // 链表中有一个环，其尾部连接到第二个节点。
        // 所以返回的节点是2
        ListNode res = detectCycle(head);
        System.out.println(res);
    }

    // 快慢指针判断环路
    // 慢指针一次走一步，快指针一次走两步，如果有环，一定在环上相遇
    // 当相遇的时候，此时再来一个指针保持慢指针速度从头走，当两者相遇的时候，就是起始节点
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
}
