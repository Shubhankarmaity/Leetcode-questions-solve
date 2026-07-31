class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // We have seen 0 odd numbers once before starting
        map.put(0, 1);

        int oddCount = 0;
        int answer = 0;

        for (int num : nums) {

            // Count odd numbers
            if (num % 2 != 0) {
                oddCount++;
            }

            // Need an earlier prefix with (oddCount - k) odds
            if (map.containsKey(oddCount - k)) {
                answer += map.get(oddCount - k);
            }

            // Store current prefix count
            map.put(oddCount, map.getOrDefault(oddCount, 0) + 1);
        }

        return answer;
    }
}