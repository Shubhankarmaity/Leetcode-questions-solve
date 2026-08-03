class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        // dp[i] = maximum score difference (Alice - Bob)
        // starting from index i.
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int take = 0;
            dp[i] = Integer.MIN_VALUE;

            // Take 1, 2, or 3 stones
            for (int j = i; j < Math.min(i + 3, n); j++) {
                take += stoneValue[j];

                // After taking stones, opponent gets the turn.
                // dp[j + 1] is opponent's advantage, so subtract it.
                dp[i] = Math.max(dp[i], take - dp[j + 1]);
            }
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}