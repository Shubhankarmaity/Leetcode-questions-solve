import java.util.*;

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> hs = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            hs.put(s.charAt(i), hs.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);

            if (!hs.containsKey(c)) {
                return false;
            }
            hs.put(c, hs.get(c) - 1);
            if (hs.get(c) == 0) {
                hs.remove(c);
            }
        }
        return hs.isEmpty();
    }
}