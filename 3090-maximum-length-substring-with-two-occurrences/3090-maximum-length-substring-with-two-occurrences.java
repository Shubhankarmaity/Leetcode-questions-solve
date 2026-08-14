class Solution {
    public int maximumLengthSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxSize = 0;
        int i = 0;

        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (map.get(ch) > 2) {
                char left = s.charAt(i);
                map.put(left, map.get(left) - 1);
                i++;
            }

            maxSize = Math.max(maxSize, j - i + 1);
        }

        return maxSize;
    }
}