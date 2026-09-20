class Solution {
    public int reverseDegree(String s) {
        int ans = 0;

        for (int i = 1; i <= s.length(); i++) {
            int value = 'z' - s.charAt(i - 1) + 1;
            ans += value * i;
        }

        return ans;
    }
}