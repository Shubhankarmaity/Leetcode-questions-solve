class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int oddCount = 0;
        int answer = 0;

        for (int num : nums) {

            if (num % 2 != 0) {
                oddCount++;
            }

            if (map.containsKey(oddCount - k)) {
                answer += map.get(oddCount - k);
            }

            map.put(oddCount, map.getOrDefault(oddCount, 0) + 1);
        }

        return answer;
    }
}