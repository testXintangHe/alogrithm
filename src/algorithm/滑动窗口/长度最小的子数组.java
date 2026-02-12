package algorithm.滑动窗口;


public class 长度最小的子数组 {
    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * <p>
     * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */
    public static void main(String[] args) {
        int target = 7;
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        // 2 -- 子数组 [4,3] 是该条件下的长度最小的子数组。
        int res = minSubArrayLen(target, nums);
        System.out.println(res);
    }

    public static int minSubArrayLen(int target, int[] nums) {
        // 窗口左边和右边两个指针
        int minLength = Integer.MAX_VALUE;
        int sumNow = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        //当右边指针没有到底，就可以一直走，并计算当前的总和是否大于目标，大于了，说明左边窗口可以往前走
        while (rightIndex < nums.length) {
            sumNow += nums[rightIndex];
            while (sumNow >= target) {
                minLength = Math.min(minLength, rightIndex - leftIndex + 1);
                sumNow -= nums[leftIndex];
                leftIndex++;
            }
            rightIndex++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
