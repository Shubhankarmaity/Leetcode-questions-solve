class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        Set<Integer> arr=new HashSet<>();
        for(int i=0;i<nums2.length;i++){
            arr.add(nums2[i]);
        }
        for(int i=0;i<nums1.length;i++){
            if(arr.contains(nums1[i])){
                return nums1[i];
            }
        }
        return -1;
    }
}