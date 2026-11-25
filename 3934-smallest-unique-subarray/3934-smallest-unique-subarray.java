import java.util.*;

class Solution {

    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE = 233L;

    private long[] prefix1;
    private long[] prefix2;
    private long[] power1;
    private long[] power2;

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        prefix1 = new long[n + 1];
        prefix2 = new long[n + 1];

        power1 = new long[n + 1];
        power2 = new long[n + 1];

        power1[0] = 1;
        power2[0] = 1;

        // Precompute prefix hashes and powers
        for (int i = 0; i < n; i++) {

            prefix1[i + 1] =
                (prefix1[i] * BASE + nums[i]) % MOD1;

            prefix2[i + 1] =
                (prefix2[i] * BASE + nums[i]) % MOD2;

            power1[i + 1] =
                (power1[i] * BASE) % MOD1;

            power2[i + 1] =
                (power2[i] * BASE) % MOD2;
        }

        int left = 1;
        int right = n;
        int ans = n;

        // Binary search
        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (hasUniqueSubarray(nums, mid)) {

                ans = mid;
                right = mid - 1;

            } else {

                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {

        int n = nums.length;

        HashMap<Long, Integer> map = new HashMap<>();

        // Check every subarray of length len
        for (int i = 0; i + len <= n; i++) {

            int left = i;
            int right = i + len;

            long hash1 =
                (prefix1[right]
                - prefix1[left] * power1[len] % MOD1
                + MOD1) % MOD1;

            long hash2 =
                (prefix2[right]
                - prefix2[left] * power2[len] % MOD2
                + MOD2) % MOD2;

            // Combine both hashes into one long
            long hash = hash1 * MOD2 + hash2;

            map.put(hash, map.getOrDefault(hash, 0) + 1);
        }

        // Check if any subarray occurs exactly once
        for (int frequency : map.values()) {

            if (frequency == 1) {
                return true;
            }
        }

        return false;
    }
}