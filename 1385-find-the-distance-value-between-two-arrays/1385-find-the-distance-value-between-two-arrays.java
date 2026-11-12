class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count=0;
        int n=arr1.length;
        int m=arr2.length;
        int i=0;
        while(i<n){
            int j=0;
            boolean flag=true;
            while(j<m){
                int val=Math.abs(arr1[i]-arr2[j]);
                if(val<=d){
                    flag=false;
                    break;
                }
                j++;
            }
            if(flag){
                count++;
            }
            i++;
        }
        return count;
    }
}