class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        int mult=0;
        int i=1;
        for(int val:set){
            mult=k*i;
            i++;
            if(!set.contains(mult)){
                return mult;
            }
        }
        return k*i;
    }
}