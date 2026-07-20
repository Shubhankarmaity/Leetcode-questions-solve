import java.util.*;

class Solution {
    public int halveArray(int[] nums) {
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        double total = 0;
        for (int num : nums) {
            total += num;
            maxHeap.offer((double) num);
        }

        double target = total / 2.0;
        double reduced = 0;
        int operations = 0;

        while (reduced < target) {
            double largest = maxHeap.poll();
            double half = largest / 2.0;

            reduced += half;
            maxHeap.offer(half);
            operations++;
        }

        return operations;
    }
}