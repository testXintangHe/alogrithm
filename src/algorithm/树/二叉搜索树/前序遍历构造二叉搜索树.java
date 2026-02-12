package algorithm.树.二叉搜索树;

import bean.TreeNode;
import util.TreeUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 知道任意二叉树的前序 / 中序 / 后序遍历中的两个，就可以构造出对应的二叉树
public class 前序遍历构造二叉搜索树 {
    /**
     * 给定一个整数数组，它表示BST(即 二叉搜索树 )的 先序遍历 ，构造树并返回其根。
     */
    public static void main(String[] args) {
        //         8
        //    5        10
        //  1   7        12
        int[] preorder = new int[]{8, 5, 1, 7, 10, 12};
        TreeNode res = bstFromPreorder(preorder);
        TreeUtil.preorder(res);
    }

    // 存储正序的值和对应的下标
    public static Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    public static TreeNode bstFromPreorder(int[] preorder) {
        // 本题只给了前序的结果，但是因为是二叉搜索树，所以中序遍历的结果就是前序结果的正序
        int[] realPreorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(preorder);
        int[] realInorder = Arrays.copyOf(preorder, preorder.length);

        // 存储正序的值和对应的下标
        for (int i = 0; i < realInorder.length; i++) {
            inorderIndexMap.put(realInorder[i], i);
        }

        return build(realPreorder, 0, preorder.length - 1, realInorder, 0, preorder.length - 1);
    }

    // 前序：[root, left的前序, right的前序]
    // 中序：[left的中序, root, right的中序]
    // 前序的第一个元素就是root，根据root在中序的位置，可以得到left的长度
    // 递归构建left和right
    // preStart 和 preEnd 记录当前节点的前序是在 preorder 的哪个部分，inStart 和 inEnd 记录当前节点的中序是在 inorder 的哪个部分
    public static TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        if (preStart == preEnd) {
            return root;
        }
        int rootIndexInInorder = inorderIndexMap.get(preorder[preStart]);
        int leftSize = rootIndexInInorder - inStart;

        // left的前序是：preStart+1, preStart+leftSize
        // left的中序是：inStart, rootIndexInInorder - 1
        // right的前序是：preStart+leftSize +1, preEnd
        // right的中序是：rootIndexInInorder+1, inEnd
        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndexInInorder - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndexInInorder + 1, inEnd);
        return root;
    }
}
