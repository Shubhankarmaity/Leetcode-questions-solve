class Solution {
    public int binaryGap(int n) {
        String binary = Integer.toBinaryString(n);
        int m=binary.length();
        int i=0,j=0;
        int dist=0;
        while(i<=j && j<m){
            if(binary.charAt(i)=='1' && binary.charAt(j)=='1' && i!=j){
                dist=Math.max(dist,j-i);
                i=j;
                j=i+1;
            }
            else{
                j++;
            }
        }
        return dist;
    }
}