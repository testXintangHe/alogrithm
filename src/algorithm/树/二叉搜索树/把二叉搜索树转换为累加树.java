package algorithm.树.二叉搜索树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.Arrays;

public class 把二叉搜索树转换为累加树 {
    /**
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree）
     * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     */
    public static void main(String[] args) {
        //            4
        //      1          6
        //    0   2      5   7
        //         3          8
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8));
        // 累加之后变成：
        //            30
        //      36          21
        //    36  35      26   15
        //         33            8
        TreeNode res = convertBST(root);
        TreeUtil.preorder(res);
    }

    public static int greaterSum;

    // 中序二叉搜索树的话，得到的是一个正序的数组，要想每个元素是更大值的和，那么就倒序遍历，然后每个元素就是累加就行
    // 倒序遍历不是后序遍历，倒序遍历是反中序遍历 (因为后序是先left，再right，再root) (反中序是先right，再root，再left)
    public static TreeNode convertBST(TreeNode root) {
        antiInOrder(root);
        return root;
    }

    public static void antiInOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        antiInOrder(root.right);
        root.val = root.val + greaterSum;
        greaterSum = root.val;
        antiInOrder(root.left);
    }
}
