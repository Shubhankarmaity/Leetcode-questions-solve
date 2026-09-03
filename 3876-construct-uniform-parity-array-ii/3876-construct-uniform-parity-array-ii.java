class Solution {
    public boolean uniformArray(int[] nums) {
        int minOdd = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num % 2 != 0) {
                minOdd = Math.min(minOdd, num);
            }
        }

        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        for (int num : nums) {
            if (num % 2 == 0 && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}