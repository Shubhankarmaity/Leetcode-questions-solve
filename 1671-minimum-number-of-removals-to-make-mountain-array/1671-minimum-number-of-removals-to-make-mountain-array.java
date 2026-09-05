class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        int[] left = new int[n];
        int[] right = new int[n];

        // LIS ending at each index
        for (int i = 0; i < n; i++) {
            left[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    left[i] = Math.max(left[i], left[j] + 1);
                }
            }
        }

        // Longest decreasing subsequence starting at each index
        for (int i = n - 1; i >= 0; i--) {
            right[i] = 1;

            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    right[i] = Math.max(right[i], right[j] + 1);
                }
            }
        }

        int longestMountain = 0;

        // Find the best peak
        for (int i = 0; i < n; i++) {
            if (left[i] > 1 && right[i] > 1) {
                int mountainLength = left[i] + right[i] - 1;
                longestMountain = Math.max(longestMountain, mountainLength);
            }
        }

        return n - longestMountain;
    }
}