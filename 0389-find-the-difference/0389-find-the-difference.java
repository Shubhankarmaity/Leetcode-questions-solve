class Solution {
    public char findTheDifference(String s, String t) {

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);

            if (map.containsKey(c)) {
                int count = map.get(c) - 1;

                if (count == 0) {
                    map.remove(c);
                } else {
                    map.put(c, count);
                }
            } else {
                return c;
            }
        }

        return ' ';
    }
}