class Solution {
    public List<Integer> getRow(int rowIndex) {
        int arr[][]=new int[rowIndex+1][rowIndex+1];
        for(int i=0;i<=rowIndex;i++){
            for(int j=0;j<=i;j++){
                if(j==0 || i==j){
                    arr[i][j]=1;
                }
                else{
                    arr[i][j]=arr[i-1][j-1]+arr[i-1][j];
                }
            }
        }
        List<Integer>ans=new ArrayList<>();
        for(int i=0;i<arr[rowIndex].length;i++){
            ans.add(arr[rowIndex][i]);
        }
        return ans;
    }
}