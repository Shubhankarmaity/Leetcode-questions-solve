class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n=matrix.length;
        Queue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                pq.add(matrix[i][j]);
            }
        }
        int i=0;
        while(i<k-1){
            pq.remove();
            i++;
        }
        if(pq.size()!=0){
            return pq.remove();
        }
        return 0;
    }
}