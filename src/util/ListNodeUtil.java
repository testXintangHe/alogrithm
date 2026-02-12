package util;

import bean.ListNode;

import java.util.List;

public class ListNodeUtil {
    public static ListNode genListNode(List<Integer> list) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        for (int i = 0; i < list.size(); i++) {
            ListNode node = new ListNode(list.get(i));
            cur.next = node;
            cur = cur.next;
        }
        return dummy.next;
    }
}
