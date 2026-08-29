// class Solution {
//     public int[] nextGreaterElement(int[] nums1, int[] nums2) {
//         int n=nums1.length;
//         int m=nums2.length;
//         int []ans=new int[n];

//         for(int i=0;i<n;i++){
//             for(int j=0;j<m;j++){
//                 if(nums1[i]==nums2[j]){
//                     for(int k=j;k<m;k++){
//                         if(nums2[k]>nums2[j]){
//                             ans[i]=nums2[k];
//                             break;
//                         }
//                         ans[i]=-1;
//                     }
//                 }
//             }
//         }
//         return ans;
//     }
// }

import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] temp = monoStack(nums2);

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], temp[i]);
        }

        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }

    private static int[] monoStack(int[] nums2) {

        int[] temp = new int[nums2.length];

        Stack<Integer> st = new Stack<>();

        for (int i = nums2.length - 1; i >= 0; i--) {

            while (!st.isEmpty() && st.peek() <= nums2[i]) {
                st.pop();
            }

            if (st.isEmpty()) {
                temp[i] = -1;
            } else {
                temp[i] = st.peek();
            }

            st.push(nums2[i]);
        }

        return temp;
    }
}