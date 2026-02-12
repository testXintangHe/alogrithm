package algorithm.树.二叉搜索树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 恢复二叉搜索树 {
    /**
     * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
     */
    public static void main(String[] args) {
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(3, 1, 4, null, null, 2));
        //     3
        // 1       4
        //      2
        // 3 和 2 的顺序不对，需要换一下
        recoverTree(root);
        TreeUtil.preorder(root);
    }

    public static void recoverTree(TreeNode root) {
        // 二叉搜索树的中序遍历一定是顺序的，有2个节点交换了，说明顺序被打破了，找到这两个节点交换回去就行
        List<Integer> result = new ArrayList<>();
        inorder(root, result);

        // 找到错误的两个节点值
        int[] swapValue = find2SwapValue(result);

        // 将节点中这两个值的数据进行交换
        swapValue(root, swapValue);
    }

    public static void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    public static int[] find2SwapValue(List<Integer> list) {
        Integer[] result = new Integer[]{null, null};
        // 比如原本的顺序是：1,2,3,4,5,6,7
        // 交换 3 和 6
        // 错误交换后的顺序是：1,2,6,4,5,3,7
        // 所以第一个节点是第一个找到的倒序，也就是6,4，其中的6，也就是第一个元素
        // 第二个节点就是第二个找到的倒序，也就是5,3，其中的3，也就是第二个元素
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                // 这个判断是保证 第一个元素 是第一次出现的那个
                if (result[0] == null) {
                    result[0] = list.get(i);
                }
                result[1] = list.get(i + 1);
            }
        }

        int[] res = new int[2];
        res[0] = result[0];
        res[1] = result[1];
        return res;
    }

    public static void swapValue(TreeNode root, int[] swapValue) {
        if (root == null) {
            return;
        }
        if (root.val == swapValue[0]) {
            root.val = swapValue[1];
        } else if (root.val == swapValue[1]) {
            root.val = swapValue[0];
        }
        swapValue(root.left, swapValue);
        swapValue(root.right, swapValue);
    }
}
