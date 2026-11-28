class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] dp = new int[budget + 1];

        int NEG = -1_000_000;
        java.util.Arrays.fill(dp, NEG);

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            int freeItems = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factor == 0) {
                    freeItems++;
                }
            }

            int reward = 1 + freeItems;

            for (int cost = budget; cost >= price; cost--) {
                if (dp[cost - price] != NEG) {
                    dp[cost] = Math.max(
                        dp[cost],
                        dp[cost - price] + reward
                    );
                }
            }
        }

        int answer = 0;

        for (int cost = 0; cost <= budget; cost++) {
            if (dp[cost] != NEG) {
                int extraItems = (budget - cost) / minPrice;

                answer = Math.max(
                    answer,
                    dp[cost] + extraItems
                );
            }
        }

        return answer;
    }
}