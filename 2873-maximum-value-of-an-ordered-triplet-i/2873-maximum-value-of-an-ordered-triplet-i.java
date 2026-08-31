class Solution {
    public long maximumTripletValue(int[] nums) {
        long max = 0;
        long maxNum = nums[0];
        long maxDiff = Long.MIN_VALUE;

        for (int j = 1; j < nums.length; j++) {
            maxDiff = Math.max(maxDiff, maxNum - nums[j]);

            if (j + 1 < nums.length) {
                max = Math.max(max, maxDiff * nums[j + 1]);
            }

            maxNum = Math.max(maxNum, nums[j]);
        }

        return max;
    }
}