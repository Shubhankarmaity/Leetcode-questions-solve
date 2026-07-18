class Solution {

    private long calculateTotalHours(int[] piles, int speed) {
        long totalH = 0;

        for (int bananas : piles) {
            totalH += (bananas + speed - 1L) / speed;
        }

        return totalH;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;

        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int low = 1, high = maxPile;
        int ans = maxPile;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (calculateTotalHours(piles, mid) <= h) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}