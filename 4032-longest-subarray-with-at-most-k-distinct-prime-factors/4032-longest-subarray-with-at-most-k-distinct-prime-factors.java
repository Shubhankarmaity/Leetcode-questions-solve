import java.util.*;

class Solution {

    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;

        // nums[i] <= 1e5
        int MAX = 100000;

        // Smallest Prime Factor
        int[] spf = new int[MAX + 1];

        for (int i = 2; i <= MAX; i++) {
            if (spf[i] == 0) {
                spf[i] = i;

                if ((long) i * i <= MAX) {
                    for (int j = i * i; j <= MAX; j += i) {
                        if (spf[j] == 0) {
                            spf[j] = i;
                        }
                    }
                }
            }
        }

        // Prime factors of every number
        int[][] factors = new int[n][];

        for (int i = 0; i < n; i++) {
            factors[i] = getFactors(nums[i], spf);
        }

        // Sliding window
        int[] count = new int[MAX + 1];

        int left = 0;
        int distinct = 0;
        int answer = 0;

        for (int right = 0; right < n; right++) {

            // Add nums[right]
            for (int p : factors[right]) {
                if (count[p] == 0) {
                    distinct++;
                }
                count[p]++;
            }

            // Too many distinct prime factors
            while (distinct > k) {

                // Remove nums[left]
                for (int p : factors[left]) {
                    count[p]--;

                    if (count[p] == 0) {
                        distinct--;
                    }
                }

                left++;
            }

            answer = Math.max(answer, right - left + 1);
        }

        return answer;
    }

    private int[] getFactors(int x, int[] spf) {
        int[] temp = new int[10];
        int size = 0;

        while (x > 1) {
            int p = spf[x];

            temp[size++] = p;

            // Remove all occurrences of this prime.
            while (x % p == 0) {
                x /= p;
            }
        }

        return Arrays.copyOf(temp, size);
    }
}