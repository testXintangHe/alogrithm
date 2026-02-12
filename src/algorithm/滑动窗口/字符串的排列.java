package algorithm.滑动窗口;

import java.util.Arrays;

public class 字符串的排列 {
    /**
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的 排列。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     */
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        // true -- s2 包含 s1 的排列之一 ("ba").
        boolean res = checkInclusion(s1, s2);
        System.out.println(res);
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1Cnt = new int[26];
        int[] s2Cnt = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1Cnt[s1.charAt(i) - 'a']++;
            s2Cnt[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(s1Cnt, s2Cnt)) {
            return true;
        }

        int leftIndex = 0;
        int rightIndex = leftIndex + s1.length();
        while (rightIndex < s2.length()) {
            s2Cnt[s2.charAt(rightIndex) - 'a']++;
            s2Cnt[s2.charAt(leftIndex) - 'a']--;

            if (Arrays.equals(s1Cnt, s2Cnt)) {
                return true;
            }
            rightIndex++;
            leftIndex++;
        }

        return false;
    }
}
