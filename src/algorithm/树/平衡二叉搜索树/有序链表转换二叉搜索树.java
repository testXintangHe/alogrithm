package algorithm.树.平衡二叉搜索树;

import bean.ListNode;
import bean.TreeNode;
import util.ListNodeUtil;
import util.TreeUtil;

import java.util.Arrays;

// 平衡二叉搜索树 -- 在二叉搜索树的基础上，满足 左右子树的层级差 <= 1
public class 有序链表转换二叉搜索树 {
    /**
     * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为 平衡 二叉搜索树。
     */
    public static void main(String[] args) {
        ListNode head = ListNodeUtil.genListNode(Arrays.asList(-10,-3,0,5,9));
        //          0
        //     -3     9
        //  -10      5
        TreeNode res = sortedListToBST(head);
        TreeUtil.preorder(res);
    }

    public static TreeNode sortedListToBST(ListNode head) {
        return transfer(head, null);
    }

    // 每次找到大小在中间的节点，它的值就是当前节点的根，这样可以保证建立的二叉搜索树是平衡的 (左右子树的层级差 <= 1)
    public static TreeNode transfer(ListNode startNode, ListNode endNode) {
        if (startNode == endNode) {
            return null;
        }

        ListNode mid = findMidNode(startNode, endNode);
        TreeNode root = new TreeNode(mid.val);

        root.left = transfer(startNode, mid);
        root.right = transfer(mid.next, endNode);
        return root;
    }

    // 快慢指针遍历当前链表 (从start到end)，最后慢指针就指向中间节点
    public static ListNode findMidNode(ListNode startNode, ListNode endNode) {
        ListNode fast = startNode;
        ListNode slow = startNode;
        while (fast != endNode && fast.next != endNode) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
