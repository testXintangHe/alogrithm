package algorithm.树.二叉搜索树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.Arrays;

public class 修剪二叉搜索树 {
    /**
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。
     * 通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
     * 修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。
     * 可以证明，存在 唯一的答案 。
     * <p>
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
     */
    public static void main(String[] args) {
        //       3
        //   0      4
        //     2
        //   1
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(3, 0, 4, null, 2, null, null, 1));
        int low = 1, high = 3;
        // 修剪之后变成：
        //       3
        //     2
        //   1
        TreeNode res = trimBST(root, low, high);
        TreeUtil.preorder(res);
    }

    public static TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            // 如果当前节点值 < low，说明当前节点 和 left都不要，直接返回 修剪后的right
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            // 如果当前节点值 > high，说明当前节点 和 right都不要，直接返回 修剪后的left
            return trimBST(root.left, low, high);
        } else {
            // 如果当前节点在low和high之间，那么当前节点不动，其left是修剪后的left，right是修剪后的right
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}
