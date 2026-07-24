class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;

        boolean[] pair = new boolean[2048];
        boolean[] ans = new boolean[2048];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pair[nums[i] ^ nums[j]] = true;
            }
        }

        for (int x = 0; x < 2048; x++) {
            if (!pair[x]) continue;
            for (int num : nums) {
                ans[x ^ num] = true;
            }
        }

        int res = 0;
        for (boolean b : ans) {
            if (b) res++;
        }
        return res;
    }
}