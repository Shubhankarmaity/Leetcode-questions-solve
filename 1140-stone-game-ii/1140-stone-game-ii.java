class Solution {
    int t[][][]=new int[2][101][101];
    public int stoneGameII(int[] piles) {
        return solveForAlice(piles,1,0,1);
    }
    private int solveForAlice(int []piles,int p,int idx,int M){
        int n=piles.length;
        
        if(idx>=n){
            return 0;
        }
        
        int stone=0;
        int result=(p==1)?Integer.MIN_VALUE:Integer.MAX_VALUE;
        if(t[p][idx][M]!=0){
            return t[p][idx][M];
        }

        for(int i=1;i<=Math.min(2*M,n-idx);i++){
            stone+=piles[idx+i-1];
            if(p==1){
                result=Math.max(result,stone+solveForAlice(piles,0,idx+i,Math.max(i,M)));
            }
            else{
                result=Math.min(result,solveForAlice(piles,1,idx+i,Math.max(i,M)));
            }
        }
        return t[p][idx][M]=result;
    }
}