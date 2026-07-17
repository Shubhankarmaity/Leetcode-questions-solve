import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        // freq[x] = frequency of x
        int[] freq = new int[max + 1];
        for (int x : nums) {
            freq[x]++;
        }

        // cnt[g] = how many numbers are divisible by g
        long[] cnt = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            for (int multiple = g; multiple <= max; multiple += g) {
                cnt[g] += freq[multiple];
            }
        }

        // exact[g] = number of pairs whose gcd is exactly g
        long[] exact = new long[max + 1];

        for (int g = max; g >= 1; g--) {
            long c = cnt[g];
            exact[g] = c * (c - 1) / 2;

            for (int multiple = g * 2; multiple <= max; multiple += g) {
                exact[g] -= exact[multiple];
            }
        }

        // prefix[g] = number of pairs with gcd <= g
        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + exact[g];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i] + 1; // queries are 0-indexed

            int lo = 1;
            int hi = max;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (prefix[mid] >= q) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            ans[i] = lo;
        }

        return ans;
    }
}