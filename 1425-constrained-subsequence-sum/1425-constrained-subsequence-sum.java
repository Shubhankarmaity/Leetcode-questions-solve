import java.util.*;

class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        int ans = nums[0];

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            dp[i] = nums[i];

            if (!deque.isEmpty()) {
                dp[i] = Math.max(dp[i], nums[i] + dp[deque.peekFirst()]);
            }

            ans = Math.max(ans, dp[i]);

            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return ans;
    }
}