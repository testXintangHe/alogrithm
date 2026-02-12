package algorithm.回溯;

import java.util.ArrayList;
import java.util.List;

public class 括号生成 {
    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     */
    public static void main(String[] args) {
        int n = 3;
        // ["((()))","(()())","(())()","()(())","()()()"]
        List<String> res = generateParenthesis(n);
        System.out.println(res);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(new StringBuilder(), result, n, 0, 0);
        return result;
    }

    // 回溯的时候，如果 (<n，说明还可以增加 (
    // 如果 )的数量小于 (的数量，说明可以增加 )
    public static void backtrack(StringBuilder sb, List<String> result, int n, int openCount, int closeCount) {
        if (sb.length() == n * 2) {
            result.add(sb.toString());
            return;
        }

        if (openCount < n) {
            sb.append("(");
            backtrack(sb, result, n, openCount + 1, closeCount);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (closeCount < openCount) {
            sb.append(")");
            backtrack(sb, result, n, openCount, closeCount + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
