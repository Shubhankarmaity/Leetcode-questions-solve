class Solution {
    public int[] arrayRankTransform(int[] arr) {
        Set<Integer> set = new TreeSet<>();

        for (int x : arr) {
            set.add(x);
        }

        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;

        for (int x : set) {
            map.put(x, rank++);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }

        return arr;
    }
}