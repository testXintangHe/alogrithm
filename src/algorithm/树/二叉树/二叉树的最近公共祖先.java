package algorithm.树.二叉树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.Arrays;

public class 二叉树的最近公共祖先 {
    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     */
    public static void main(String[] args) {
        //          3
        //    5           1
        // 6     2     0     8
        //     7   4
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4));
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(4);
        // 5
        TreeNode res = lowestCommonAncestor(root, p, q);
        System.out.println(res);
    }

    public static TreeNode res;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }

    // 返回当前root和子树中，是否包含 p 或者 q 节点
    public static boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        // 左右子树是否包含p或者q节点
        boolean leftFlag = dfs(root.left, p, q);
        boolean rightFlag = dfs(root.right, p, q);

        // 如果左右子树包含p和q节点，说明当前节点就是最近的祖先
        if (leftFlag && rightFlag) {
            res = root;
        }
        // 如果只有 左 / 右子树 包含 p / q节点，同时当前节点就是p / q 节点，那么当前节点也是最近的祖先
        if ((leftFlag || rightFlag) && (root.val == q.val || root.val == p.val)) {
            res = root;
        }

        // 返回是否包含两个节点之一 (子树包含或者当前节点是，都可以)
        return leftFlag || rightFlag || root.val == q.val || root.val == p.val;
    }
}
