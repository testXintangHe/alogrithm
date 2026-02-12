package algorithm.最小生成树;

import java.util.*;

// prim算法 或者 Kruskal算法
// 性能方面prim是优于Kruskal的
public class 连接所有点的最小费用 {
    /**
     * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
     * <p>
     * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
     * <p>
     * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
     */
    public static void main(String[] args) {
        int[][] points = new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        // 20
        int res = minCostConnectPointsKruskal(points);
        System.out.println(res);
    }

    // prim算法是从点到面
    // 先计算路径图
    // 然后先加入一个点，组成图，然后用两个数组记录，一个记录其他没加入的点到图的最短距离，一个记录有哪些点已经加入了点
    // 每次找到最近的点加入图，并刷新其他点到图的最短距离
    // 只需要n-1次加入点，就可以将所有点加入了
    public static int minCostConnectPointsPrim(int[][] points) {
        int result = 0;

        // 先计算每个点到其他点的路径
        int n = points.length;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            int xi = points[i][0];
            int yi = points[i][1];
            for (int j = 0; j < n; j++) {
                int xj = points[j][0];
                int yj = points[j][1];
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Math.abs(xi - xj) + Math.abs(yi - yj);
                }
            }
        }

        // length记录当前情况下，每个节点想要加入图的最短距离
        int[] length = new int[n];
        // visited记录当前情况下，有哪些节点已经加入了图，加入了的变成true
        boolean[] visited = new boolean[n];
        // 认为从0节点开始，那么其他节点想要加入图的距离就是matrix[0][i]
        for (int i = 0; i < n; i++) {
            length[i] = matrix[0][i];
        }
        visited[0] = true;

        // 因为一开始认为0节点加入了，所以只需要加入剩余的n-1个节点，就循环n-1次，每次加入一个点
        for (int i = 0; i < n - 1; i++) {
            int minLength = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && length[j] < minLength) {
                    minLength = length[j];
                    minIndex = j;
                }
            }

            // 把最近的节点加入
            visited[minIndex] = true;
            result += length[minIndex];

            // 通过最近的节点，刷新 length，因为当前的length是在当前节点组成的图的情况下，其他点到图的最近距离
            // 此时新增了当前节点，那么最近距离就是 min(当前距离, 当前节点到其他点的距离)
            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    length[j] = Math.min(length[j], matrix[minIndex][j]);
                }
            }
        }

        return result;
    }

    // Kruskal算法是从线段到面
    // 先计算路径图，并组成边的集合，再把边从小到大排序
    // 每次取其中最小的边，如果图里面没有这个边的某个顶点，那么就加入图
    public static int minCostConnectPointsKruskal(int[][] points) {
        int result = 0;

        // 先计算每个点到其他点的路径，放入边中，起点和终点不重要，因为在集合里面是无向的，所以只需要一个即可
        List<Edge> edges = new ArrayList<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            int xi = points[i][0];
            int yi = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int xj = points[j][0];
                int yj = points[j][1];
                int distance = Math.abs(xi - xj) + Math.abs(yi - yj);
                edges.add(new Edge(i, j, distance));
            }
        }

        // 排序所有的边，从小到大，这样就可以从最短的边遍历
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.distance - o2.distance;
            }
        });

        List<Edge> resultEdges = new ArrayList<>();
        UnionFindSet unionFindSet = new UnionFindSet(n);
        // 遍历边，如果边的两个顶点不在一个集合，那么就合并，并加入图
        for (Edge edge : edges) {
            if (unionFindSet.union(edge.start, edge.end)) {
                resultEdges.add(edge);
                result += edge.distance;
                // 如果已经有n-1个边了，说明所有点都加入了图，可以退出循环
                if (resultEdges.size() == n - 1) {
                    break;
                }
            }
        }

        return result;
    }

    static class Edge {
        int start;
        int end;
        int distance;

        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    // 并查集 -- 快速判断两个节点是否在一个集合中，并且快速合并两个集合
    static class UnionFindSet {
        // parent数组中，每个index表示一个元素，parent[index]表示它父亲是哪个，一开始都指向自己，表示自己就是这个集合的根
        int[] parent;

        public UnionFindSet(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // 查找当前节点的根 (也就是属于哪个根的集合)
        // 也就是一直要找到parent[val] == val的时候
        // 同时，在查找的时候，可以将找到的结果赋值给查找路径上的所有节点，这样可以让下次查找的时候更快
        // 比如一开始的关系是：0 -> 1, 1 -> 2, 2 -> 3, 4 -> 3
        // 此时查找0的根，在递归之后会变成：0 -> 3, 1 -> 3, 2 -> 3, 4 -> 3
        public int find(int val) {
            if (parent[val] != val) {
                parent[val] = find(parent[val]);
            }
            return parent[val];
        }

        // 合并两个节点所在的集合，先找到两者的根，然后判断是否已经在一个集合了，不在的时候合并
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootY == rootX) {
                return false;
            }

            parent[rootY] = rootX;
            return true;
        }
    }
}
