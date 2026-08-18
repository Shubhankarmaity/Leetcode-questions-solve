import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();

        // Count each number once per subarray
        for (int i = 0; i <= nums.length - k; i++) {
            Set<Integer> seen = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                seen.add(nums[j]);
            }

            for (int num : seen) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
        }

        int ans = -1;

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                ans = Math.max(ans, entry.getKey());
            }
        }

        return ans;
    }
}