class Solution {
    public int missingInteger(int[] nums) {
        List<Integer> arr=new ArrayList<>();
        int sum=nums[0];
        arr.add(nums[0]);

        for(int i=1;i<nums.length;i++){
            arr.add(nums[i]);
        }
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1]+1){
                sum+=nums[i];
            }
            else{
                break;
            }
        }
        while(arr.contains(sum)){
            sum++;
        }
        return sum;
    }
}