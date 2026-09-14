class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {

            // Compare current with next element
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }

            // More than one drop means invalid
            if (count > 1) {
                return false;
            }
        }

        return true;
    }
}