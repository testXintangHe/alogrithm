package algorithm.树.二叉搜索树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.ArrayList;
import java.util.List;

// 二叉搜索树，在二叉树的基础上，满足左节点的值 < 当前节点 < 右节点的值，且左子树的所有节点值也是 < 当前节点 < 右子树的所有节点的值
// 所以二叉搜索树的中序遍历，一定是一个正序的
// 前序遍历 -- 先走root，再left，再right
// 中序遍历 -- 先走left，再root，再right
// 后序遍历 -- 先走left，再right，再root
public class 不同的二叉搜索树II {
    /**
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     */
    public static void main(String[] args) {
        int n = 3;
        // [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
        List<TreeNode> list = generateTrees(n);
        for (TreeNode root : list) {
            TreeUtil.preorder(root);
            System.out.println();
        }
    }

    public static List<TreeNode> generateTrees(int n) {
        return backtrack(1, n);
    }

    public static List<TreeNode> backtrack(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        // 当不存在当前节点的时候，需要也给result加一个null节点，因为后面需要给root的left或者right设置成null
        if (start > end) {
            result.add(null);
            return result;
        }

        // 以当前节点为根节点，递归得到他的左右节点，然后遍历拼接
        for (int i = start; i <= end; i++) {
            // 左节点可能的所有树，左节点的前后数值肯定在 start 和 i-1 之中
            List<TreeNode> lefts = backtrack(start, i - 1);
            // 右节点可能的所有树，右节点的前后数值肯定在 i+1 和 end 之中
            List<TreeNode> rights = backtrack(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }

        return result;
    }
}
