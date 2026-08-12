class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int i = 0;
        int maxSize = 0;

        for (int j = 0; j < nums.length; j++) {

            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            while (map.get(nums[j]) > k) {
                map.put(nums[i], map.get(nums[i]) - 1);
                i++;
            }

            maxSize = Math.max(maxSize, j - i + 1);
        }

        return maxSize;
    }
}