import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int maxFactor = n;
        int minPrice = Integer.MAX_VALUE;

        int[] freq = new int[maxFactor + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            freq[factor]++;
            minPrice = Math.min(minPrice, price);
        }

        // Count how many item types have factors
        // divisible by each factor.
        int[] divisibleCount = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            for (int multiple = f;
                 multiple <= maxFactor;
                 multiple += f) {

                divisibleCount[f] += freq[multiple];
            }
        }

        // Sort items by price.
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remainingBudget = budget;
        long answer = 0;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // Double reward is not better than
            // buying the cheapest item twice.
            if (price > 2L * minPrice) {
                break;
            }

            int freeCount = divisibleCount[factor] - 1;

            if (freeCount <= 0) {
                continue;
            }

            // Maximum double-reward purchases.
            long buyCount = Math.min(
                freeCount,
                remainingBudget / price
            );

            remainingBudget -= buyCount * price;

            // Each purchase gives 1 purchased + 1 free item.
            answer += buyCount * 2;
        }

        // Spend the remaining budget on the cheapest item.
        answer += remainingBudget / minPrice;

        return (int) answer;
    }
}