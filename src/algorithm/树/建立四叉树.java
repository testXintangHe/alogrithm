package algorithm.树;

public class 建立四叉树 {
    /**
     * 给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
     * <p>
     * 你需要返回能表示矩阵 grid 的 四叉树 的根结点。
     * <p>
     * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
     * <p>
     * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False。当非叶子节点的时候，true 和 false 都可以
     * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
     * <p>
     * topLeft      topRight
     * bottomLeft   bottomRight
     */
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0}};
        Node res = construct(grid);
        System.out.println(res);
    }

    public static Node construct(int[][] grid) {
        return dfs(grid, 0, grid.length, 0, grid.length);
    }

    // 递归得到 row1,col1 到 row2,col2 组成的矩阵，所得到的四叉树
    public static Node dfs(int[][] grid, int row1, int row2, int col1, int col2) {
        // 如果全是一样的值，那么直接返回node就行
        if (isSame(grid, row1, row2, col1, col2)) {
            return new Node(grid[row1][col1] == 1, true);
        }

        // 按照行的一半和列的一半，划分grid，可以得到四个子节点，递归可以得到四个子节点的node值
        int rowHalf = (row1 + row2) / 2;
        int colHalf = (col1 + col2) / 2;

        return new Node(true, false,
                dfs(grid, row1, rowHalf, col1, colHalf),
                dfs(grid, row1, rowHalf, colHalf, col2),
                dfs(grid, rowHalf, row2, col1, colHalf),
                dfs(grid, rowHalf, row2, colHalf, col2)
        );
    }

    public static boolean isSame(int[][] grid, int row1, int row2, int col1, int col2) {
        int first = grid[row1][col1];
        for (int i = row1; i < row2; i++) {
            for (int j = col1; j < col2; j++) {
                if (grid[i][j] != first) {
                    return false;
                }
            }
        }
        return true;
    }


    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
