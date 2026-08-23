class Solution {
    public long maximumSubarraySum(int[] nums, int k) {

        Set<Integer> set = new HashSet<>();

        long sum = 0;
        long maxSum = 0;

        int left = 0;

        for (int right = 0; right < nums.length; right++) {

            // Remove duplicates
            while (set.contains(nums[right])) {
                set.remove(nums[left]);
                sum -= nums[left];
                left++;
            }

            // Add current element
            set.add(nums[right]);
            sum += nums[right];

            // Window size > k
            if (right - left + 1 > k) {
                set.remove(nums[left]);
                sum -= nums[left];
                left++;
            }

            // Window size == k
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}