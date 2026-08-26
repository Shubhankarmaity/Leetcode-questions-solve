class Solution {

    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();
        int count = 0;
        String max = "";
        int i = 0;

        for (int j = 0; j < n; j++) {

            if (s.charAt(j) == '1') {
                count++;
            }

            while (count > k) {
                if (s.charAt(i) == '1') {
                    count--;
                }
                i++;
            }

            if (count == k) {

                while (s.charAt(i) == '0') {
                    i++;
                }

                String curr = s.substring(i, j + 1);

                if (max.equals("")
                        || curr.length() < max.length()
                        || (curr.length() == max.length()
                            && curr.compareTo(max) < 0)) {
                    max = curr;
                }
            }
        }

        return max;
    }
}