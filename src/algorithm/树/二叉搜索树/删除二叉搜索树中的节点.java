package algorithm.树.二叉搜索树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.Arrays;

public class 删除二叉搜索树中的节点 {
    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
     * 返回二叉搜索树（有可能被更新）的根节点的引用。
     * <p>
     * 一般来说，删除节点可分为两个步骤：
     * <p>
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     */
    public static void main(String[] args) {
        //        5
        //    3       6
        //  2   4       7
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(5, 3, 6, 2, 4, null, 7));
        int key = 3;
        // 删除3之后，2或者4移动到3节点
        //        5
        //    4       6
        //  2           7
        TreeNode res = deleteNode(root, key);
        TreeUtil.preorder(res);
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // 如果当前节点 > key，说明要删除的在left中，那么删除left中的节点并替换当前节点的left
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        // 如果当前节点 < key，说明要删除的在right中，那么删除right中的节点并替换当前节点的right
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        // 找到了要删除的节点
        if (root.val == key) {
            // 如果当前节点是叶子节点，直接删除
            if (root.left == null && root.right == null) {
                return null;
            }
            // 如果当前节点只有右子树，那么返回右子树
            if (root.left == null) {
                return root.right;
            }
            // 如果当前节点只有左子树，那么返回左子树
            if (root.right == null) {
                return root.left;
            }

            // 此时当前节点有左右子树，那么需要找到当前节点右子树的最小值，替换当前节点就行
            // 因为当前节点右子树的最小值，也是大于 root.left 的，然后因为是最小值，所以也小于剩余的 root.right
            // cur就是右子树的最小值
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }

            // 先删除这个节点，防止递归的时候又找到了这个节点，从而死循环
            root.right = deleteNode(root.right, cur.val);
            // 将当前节点替换root，并作为返回值返回，这样才能替换root，因为返回之后，父节点就会用cur
            cur.left = root.left;
            cur.right = root.right;
            return cur;
        }

        // 不会走到这里的，前面3个if语句包含了所有情况，随便返回一个null就行
        return null;
    }
}
