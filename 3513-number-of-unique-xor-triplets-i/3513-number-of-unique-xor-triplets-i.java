class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n=nums.length;
        if(n<3){
            return n;
        }
        for(int i=0;i<n;i++){
            double ans=Math.pow(2,i);
            if(ans >n){
                return (int) ans;
            }
        }
        return 0;
    }
}