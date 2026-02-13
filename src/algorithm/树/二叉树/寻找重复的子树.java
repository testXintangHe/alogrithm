package algorithm.树.二叉树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.*;

public class 寻找重复的子树 {
    /**
     * 给你一棵二叉树的根节点 root ，返回所有 重复的子树 。
     * <p>
     * 对于同一类的重复子树，你只需要返回其中任意 一棵 的根结点即可。
     * <p>
     * 如果两棵树具有 相同的结构 和 相同的结点值 ，则认为二者是 重复 的。
     */
    public static void main(String[] args) {
        TreeNode root = TreeUtil.genByLevel(Arrays.asList(1, 2, 3, 4, null, 2, 4, null, null, 4));
        List<TreeNode> res = findDuplicateSubtrees(root);
        System.out.println(res);
    }

    public static Map<String, TreeNode> seen = new HashMap<>();
    public static Set<TreeNode> repeat = new HashSet<>();

    // 重复的子树，所以对每个子树都进行序列化，看有没有重复的数据
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);

        return new ArrayList<>(repeat);
    }

    // 返回当前节点的序列化结果
    public static String dfs(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("(").append(root.val).append(")(");
        sb.append(dfs(root.left));
        sb.append(")(");
        sb.append(dfs(root.right));
        sb.append(")");
        String result = sb.toString();

        if (seen.containsKey(result)) {
            repeat.add(seen.get(result));
        } else {
            seen.put(result, root);
        }

        return sb.toString();
    }
}
