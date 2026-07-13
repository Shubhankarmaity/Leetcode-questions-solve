import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= 9; i++) {
            q.offer(i);
        }

        while (!q.isEmpty()) {
            int val = q.poll();

            if (val >= low && val <= high)
                ans.add(val);

            if (val % 10 != 9) {
                int newVal = val * 10 + (val % 10 + 1);
                if (newVal <= high){
                    q.offer(newVal);
                }
            }
        }

        return ans;
    }
}