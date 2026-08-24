class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int dp = sum;

        int prefix = sum - stones[n - 1];

        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, prefix - dp);
            prefix -= stones[i];
        }

        return dp;
    }
}