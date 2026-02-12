package algorithm.树.二叉树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.Arrays;

public class 最长同值路径 {
    /**
     * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
     * <p>
     * 两个节点之间的路径长度 由它们之间的边数表示。
     */
    public static void main(String[] args) {
        //         1
        //     4       5
        //   4   4       5
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(1, 4, 5, 4, 4, null, 5));
        // 2 -- 是三个4的路径
        int res = longestUnivaluePath(root);
        System.out.println(res);
    }


    public static int result = 0;

    public static int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return result;
    }

    // 返回以当前 root节点 为根节点出发的 最长'/'型 或 '\' 同值长度
    public static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        // 包含当前root的时候，leftNow就不是left了，而是需要进行判断
        int leftNow = 0;
        int rightNow = 0;
        if (root.left != null && root.val == root.left.val) {
            leftNow = left + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            rightNow = right + 1;
        }
        // 结果是经过当前 root 的 /\ 最大值
        result = Math.max(result, leftNow + rightNow);
        // 因为是单边的最大同值长度，所以只需要返回两者的最大值就行
        return Math.max(leftNow, rightNow);
    }
}
