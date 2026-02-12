package algorithm.回溯;

import java.util.Arrays;

public class 公平分发饼干 {
    /**
     * 给你一个整数数组 cookies ，其中 cookies[i] 表示在第 i 个零食包中的饼干数量。
     * 另给你一个整数 k 表示等待分发零食包的孩子数量，所有 零食包都需要分发。
     * 在同一个零食包中的所有饼干都必须分发给同一个孩子，不能分开。
     * <p>
     * 分发的 不公平程度 定义为单个孩子在分发过程中能够获得饼干的最大总数。
     * <p>
     * 返回所有分发的最小不公平程度。
     */
    public static void main(String[] args) {
        int[] cookies = new int[]{6, 1, 3, 2, 2, 4, 1, 2};
        int k = 3;
        // 7
        // 一种最优方案是 [6,1]、[3,2,2] 和 [4,1,2] 。
        // 第 1 个孩子分到 [6,1] ，总计 6 + 1 = 7 块饼干。
        // 第 2 个孩子分到 [3,2,2] ，总计 3 + 2 + 2 = 7 块饼干。
        // 第 3 个孩子分到 [4,1,2] ，总计 4 + 1 + 2 = 7 块饼干。
        // 分发的不公平程度为 max(7,7,7) = 7 。
        int res = distributeCookies(cookies, k);
        System.out.println(res);
    }

    public static int result = Integer.MAX_VALUE;

    public static int distributeCookies(int[] cookies, int k) {
        int[] childs = new int[k];
        backtrack(cookies, k, 0, childs);
        return result;
    }

    // 回溯来解决饼干的排列问题
    // index就是当前是哪个饼干，index一直增长，回溯的是当前饼干分给哪个孩子
    // 所以循环的是孩子，而不是cookie，当饼干分完的时候，就可以得到当前分配结果，从而进行比较得到result
    public static void backtrack(int[] cookies, int k, int index, int[] childs) {
        if (index == cookies.length) {
            result = Math.min(result, Arrays.stream(childs).max().getAsInt());
            return;
        }
        for (int i = 0; i < k; i++) {
            // 这个减枝逻辑 -- 如果当前的分配方案已经超过result了，说明当前方案不是最优的，那么可以舍弃
            if (childs[i] + cookies[index] > result) {
                continue;
            }
            childs[i] = childs[i] + cookies[index];
            backtrack(cookies, k, index + 1, childs);
            childs[i] = childs[i] - cookies[index];
        }
    }
}
