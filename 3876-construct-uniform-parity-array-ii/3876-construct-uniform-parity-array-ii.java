class Solution {
    public boolean uniformArray(int[] nums) {
        int minOdd = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            }
        }

        // If there is no odd number, array is already uniform
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Every even number must be >= minOdd
        for (int num : nums) {
            if (num % 2 == 0 && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}