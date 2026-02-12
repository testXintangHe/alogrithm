package algorithm.滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class 使库存平衡的最少丢弃次数 {
    /**
     * 给你两个整数 w 和 m，以及一个整数数组 arrivals，其中 arrivals[i] 表示第 i 天到达的物品类型（天数从 1 开始编号）。
     * <p>
     * 物品的管理遵循以下规则：
     * <p>
     * 每个到达的物品可以被 保留 或 丢弃 ，物品只能在到达当天被丢弃。
     * 对于每一天 i，考虑天数范围为 [max(1, i - w + 1), i]（也就是直到第 i 天为止最近的 w 天）：
     * 对于 任何 这样的时间窗口，在被保留的到达物品中，每种类型最多只能出现 m 次。
     * 如果在第 i 天保留该到达物品会导致其类型在该窗口中出现次数 超过 m 次，那么该物品必须被丢弃。
     * 返回为满足每个 w 天的窗口中每种类型最多出现 m 次，最少 需要丢弃的物品数量。
     */
    public static void main(String[] args) {
        int[] arrivals = new int[]{1, 2, 3, 3, 3, 4};
        int w = 3, m = 2;
        // 1
        // 第 1 天，物品 1 到达。我们保留它。
        // 第 2 天，物品 2 到达，窗口 [1, 2] 是可以的。
        // 第 3 天，物品 3 到达，窗口 [1, 2, 3] 中物品 3 出现一次。
        // 第 4 天，物品 3 到达，窗口 [2, 3, 3] 中物品 3 出现两次，允许。
        // 第 5 天，物品 3 到达，窗口 [3, 3, 3] 中物品 3 出现三次，超过限制，因此该物品必须被丢弃。
        // 第 6 天，物品 4 到达，窗口 [3, 4] 是可以的。
        // 第 5 天的物品 3 被丢弃，这是最少必须丢弃的数量，因此返回 1。
        int res = minArrivalsToDiscard(arrivals, w, m);
        System.out.println(res);
    }

    public static int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int result = 0;
        // 窗口左侧是开始节点，右侧是w，这是个固定窗口，长度一直是w
        int left = 0;
        int right = w;
        // cnt计数数字以及对应出现的次数，用于判断是否超过m次
        Map<Integer, Integer> cnt = new HashMap<>();
        // alreadyAdd用于统计当前节点是否加入了，只有加入了的在后面窗口滚动的时候才需要移除，不然会错误统计
        boolean[] alreadyAdd = new boolean[arrivals.length];
        // 统计一开始的窗口，每次加进去当前元素的时候，判断是否超过次数，超过了就移除当前的，因为之前加入的在后面会自动先移除掉，这样丢弃的次数会少
        for (int i = 0; i < w; i++) {
            cnt.put(arrivals[i], cnt.getOrDefault(arrivals[i], 0) + 1);
            if (cnt.get(arrivals[i]) > m) {
                result++;
                cnt.put(arrivals[i], cnt.get(arrivals[i]) - 1);
            } else {
                // 注意：不丢弃说明当前元素需要加入，那么就计入已经加入
                alreadyAdd[i] = true;
            }
        }

        while (right < arrivals.length) {
            // 只有已经加入的，才需要移除，不然会统计错误
            if (alreadyAdd[left]) {
                int remove = arrivals[left];
                cnt.put(remove, cnt.get(remove) - 1);
            }

            int add = arrivals[right];
            cnt.put(add, cnt.getOrDefault(add, 0) + 1);
            if (cnt.get(add) > m) {
                result++;
                cnt.put(add, cnt.get(add) - 1);
            } else {
                alreadyAdd[right] = true;
            }

            left++;
            right++;
        }

        return result;
    }
}
