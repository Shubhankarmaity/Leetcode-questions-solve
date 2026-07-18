class Solution {
    public int findGCD(int[] nums) {
        Arrays.sort(nums);
        return findGCD(nums[0],nums[nums.length-1]);
    }
    private int findGCD(int a,int b){
        if(b==0){
            return a; 
        }
        return findGCD(b,a%b);
    }
}