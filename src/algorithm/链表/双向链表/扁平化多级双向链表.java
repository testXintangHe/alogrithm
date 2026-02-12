package algorithm.链表.双向链表;

import bean.Node;

public class 扁平化多级双向链表 {
    /**
     * 你会得到一个双链表，其中包含的节点有一个下一个指针、一个前一个指针和一个额外的 子指针 。
     * 这个子指针可能指向一个单独的双向链表，也包含这些特殊的节点。这些子列表可以有一个或多个自己的子列表，以此类推，以生成如下面的示例所示的 多层数据结构 。
     * <p>
     * 给定链表的头节点 head ，将链表 扁平化 ，以便所有节点都出现在单层双链表中。
     * 让 curr 是一个带有子列表的节点。子列表中的节点应该出现在扁平化列表中的 curr 之后 和 curr.next 之前 。
     * <p>
     * 返回 扁平列表的 head 。列表中的节点必须将其 所有 子指针设置为 null 。
     */
    public static void main(String[] args) {
        // 1 - 2 - 3 - 4 - 5 - 6
        //         7 - 8 - 9 - 10
        //             11 - 12
        // 拼起来的结果应该是 1-2-3-7-8-11-12-9-10-4-5-6
        Node head = new Node(1);
        genAll(new int[]{1, 2, 3, 4, 5, 6}, head);

        Node head7 = new Node(7);
        genAll(new int[]{7, 8, 9, 10}, head7);

        Node head11 = new Node(11);
        genAll(new int[]{11, 12}, head11);

        head.next.next.child = head7;
        head7.next.child = head11;

        Node res = flatten(head);
        System.out.println(res);
    }

    // genAll 和 gen方法只是用于快速创建main里面的条件
    public static void genAll(int[] vals, Node head) {
        Node cur = head;
        for (int i = 1; i < vals.length; i++) {
            Node now = gen(vals[i], cur);
            cur = now;
        }
    }

    public static Node gen(int val, Node pre) {
        Node now = new Node(val);
        pre.next = now;
        now.prev = pre;
        return now;
    }

    public static Node flatten(Node head) {
        dfs(head);
        return head;
    }

    // dfs检索当前链表，一旦发现有节点有child，就先挖child
    // 返回当前链表的最后一个节点
    public static Node dfs(Node head) {
        Node cur = head;
        Node last = null;

        while (cur != null) {
            // 如果有子节点，就先挖子节点，得到子节点的最后一个节点 childLast
            // 当前节点的 next 就是子节点的开头
            // 当前节点的下一个节点的 pre 就是 子节点的最后一个节点
            // last 记录当前节点平化之后的最后一个节点，这个最后一个节点既可能是 子节点的最后一个，也可能是当前节点的最后一个
            if (cur.child != null) {
                Node childLast = dfs(cur.child);

                Node originNext = cur.next;
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;

                if (originNext != null) {
                    originNext.prev = childLast;
                    childLast.next = originNext;
                }

                last = childLast;
            } else {
                last = cur;
            }
            cur = cur.next;
        }

        return last;
    }
}
