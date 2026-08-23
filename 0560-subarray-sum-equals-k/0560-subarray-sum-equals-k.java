// class Solution {
//     public int subarraySum(int[] nums, int k) {
//         int i=0;
//         int sum=0;
//         int count=0;
//         while(i<nums.length){
//             sum=0;
//             for(int j=i;j<nums.length;j++){
//                 sum+=nums[j];
//                 if(sum==k){
//                     count++;
//                 }
//             }
//             i++;
//         }
//         return count;
//     }
// }

import java.util.*;

class Solution {
    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(0, 1);

        int sum = 0;
        int count = 0;

        for (int num : nums) {
            sum += num;

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}