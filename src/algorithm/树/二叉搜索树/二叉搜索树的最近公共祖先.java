package algorithm.树.二叉搜索树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.Arrays;

public class 二叉搜索树的最近公共祖先 {
    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */
    public static void main(String[] args) {
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5));
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        TreeNode res = lowestCommonAncestor(root, p, q);
        System.out.println(res);
    }

    public static TreeNode result;
    public static boolean first = true;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        result = root;
        dfs(root, p.val, q.val);
        return result;
    }

    // 两个节点的最近的公共祖先，那么值就是在两个节点之间
    // 当p/q < root的时候，就往左走
    // 当p/q > root的时候，就往右走
    public static void dfs(TreeNode root, int p, int q) {
        if (root == null) {
            return;
        }
        if (q < root.val && p < root.val) {
            dfs(root.left, p, q);
        } else if (q > root.val && p > root.val) {
            dfs(root.right, p, q);
        }
        // 第一次遇到两者之间的值，就是公共祖先，不加这判断，后面每次回溯都会把当前root赋值给result
        if (first) {
            result = root;
            first = !first;
        }
    }
}
