class Solution {
    public int maxArea(int[] height) {
        int maxWat=Integer.MIN_VALUE;
        int i=0,j=height.length-1;
        while(i<j){
            int water=0;
            if(height[i]<height[j]){
                water=Math.min(height[i],height[j])*(j-i);
                i++;
            }
            else{
                water=Math.min(height[i],height[j])*(j-i);
                j--;
            }
            maxWat=Math.max(maxWat,water);

        }
        return maxWat;
    }
}