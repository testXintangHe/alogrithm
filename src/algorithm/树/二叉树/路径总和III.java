package algorithm.树.二叉树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 是前缀和 + 回溯的算法
public class 路径总和III {
    /**
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * <p>
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     */
    public static void main(String[] args) {
        //          10
        //     5          -3
        //  3      2           11
        //3  -2      1
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1));
        int targetSum = 8;
        // 3条，5->3 和 5->2->1 和 -3->11
        int res = pathSum(root, targetSum);
        System.out.println(res);
    }

    public static int pathSum(TreeNode root, int targetSum) {
        // 存储 前缀和 以及 对应的路径数量，也就是根到当前节点的路径数量以及根到当前节点的路径和
        Map<Long, Integer> prefixSum = new HashMap<>();
        // 初始没有前缀和的时候，就有1条路径
        prefixSum.put(0L, 1);
        return dfs(root, targetSum, 0, prefixSum);
    }

    // prefixSum存储能抵达当前root的所有路径的前缀和 以及 对应的路径数量
    // sum存储当前路径下，抵达root的总和
    public static int dfs(TreeNode root, int targetSum, long sum, Map<Long, Integer> prefixSum) {
        if (root == null) {
            return 0;
        }

        int result;

        // 当前节点到根的和是 sum，需要实现的目标是 targetSum，也就是起点到当前节点的差值就是 targetSum
        // 那么起点的前缀和就是 sum - targetSum
        sum = sum + root.val;
        long needSum = sum - targetSum;
        // 当前节点能实现 targetSum 的路径数量就是 prefixSum 里面 needSum 的数量
        // 因为 prefixSum 存储的是回溯的前缀和，所以可以保证里面存储的前缀和都是抵达当前 root 的前缀和
        result = prefixSum.getOrDefault(needSum, 0);

        // 更新前缀和
        int sumCount = prefixSum.getOrDefault(sum, 0);
        prefixSum.put(sum, sumCount + 1);

        // 当前的总和还需要加上递归左右子树之后的结果
        result += dfs(root.left, targetSum, sum, prefixSum);
        result += dfs(root.right, targetSum, sum, prefixSum);

        // 回溯前缀和
        prefixSum.put(sum, sumCount - 1);

        return result;
    }
}
