class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n=grid.length*grid[0].length;
        int temp[]=new int[n];
        int s=0;
        int count=0;
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> inner=new ArrayList<>();

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                temp[s++]=grid[i][j];
            }
        }
        while(count<k){
            int lastVal=temp[n-1];
            for(int i=n-1;i>0;i--){
                temp[i]=temp[i-1];
            }
            temp[0]=lastVal;
            count++;
        }
        for (int i = 0; i < n; i++) {
            inner.add(temp[i]);

            if ((i + 1) % grid[0].length == 0) {
                ans.add(inner);
                inner = new ArrayList<>();
            }
        }
        return ans;
    }
}