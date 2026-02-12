package util;

import bean.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtil {
    public static TreeNode genByLevel(List<Integer> list) {
        TreeNode root = new TreeNode(list.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (index < list.size() && list.get(index) != null) {
                node.left = new TreeNode(list.get(index));
                queue.offer(node.left);
            }
            index++;

            if (index < list.size() && list.get(index) != null) {
                node.right = new TreeNode(list.get(index));
                queue.offer(node.right);
            }
            index++;
        }

        return root;
    }

    public static void preorder(TreeNode root) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }
}
