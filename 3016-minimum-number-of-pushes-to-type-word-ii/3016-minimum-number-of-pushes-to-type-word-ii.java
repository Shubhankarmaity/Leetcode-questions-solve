import java.util.*;

class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        Map<Character, Integer> hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            hm.put(ch, hm.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> sorted = hm.entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        int count = 1;
        int ans = 0;
        int i = 0;

        for (int freq : sorted.values()) {
            ans += freq * count;

            if (i % 8 == 7) {
                count++;
            }

            i++;
        }

        return ans;
    }
}