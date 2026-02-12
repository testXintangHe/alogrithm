package algorithm.回溯;

import java.util.ArrayList;
import java.util.List;

public class 无重复字符串的排列组合 {
    /**
     * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
     */
    public static void main(String[] args) {
        String s = "qqe";
        // ["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
        String[] res = permutation(s);
        System.out.println(res);
    }

    public static String[] permutation(String s) {
        List<String> result = new ArrayList<>();
        char[] array = s.toCharArray();
        backtrack(array, new StringBuilder(), result, new boolean[s.length()]);

        String[] res = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    // 回溯来解决排列组合的问题
    // 当前长度等于数组长度，说明是一个拼好的结果，这时候判断是否有重复元素加入，没有再加入
    // 然后就是遍历数组，当前元素没用过的时候，放进去，并设置成用过，再进行回溯
    public static void backtrack(char[] array, StringBuilder sb, List<String> result, boolean[] used) {
        if (sb.length() == array.length) {
            if (!result.contains(sb.toString())) {
                result.add(sb.toString());
            }
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (!used[i]) {
                sb.append(array[i]);
                used[i] = true;
                backtrack(array, sb, result, used);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
