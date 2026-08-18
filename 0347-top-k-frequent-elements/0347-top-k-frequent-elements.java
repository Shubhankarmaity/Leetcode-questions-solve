class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> tm = new TreeMap<>();

        // Count frequency
        for (int i = 0; i < nums.length; i++) {
            tm.put(nums[i], tm.getOrDefault(nums[i], 0) + 1);
        }

        // Max heap: stores [number, frequency]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[1] - a[1]
        );

        // Add all elements to heap
        for (int key : tm.keySet()) {
            pq.offer(new int[]{key, tm.get(key)});
        }

        // Get top k frequent elements
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            ans[i] = pq.poll()[0];
        }

        return ans;
    }
}