import java.util.*;
class Solution {
    public boolean stoneGame(int[] piles) {
        int n=piles.length;
        int temp[]=new int[n-2];
        for (int i = 1, j = 0; i < n - 1; i++, j++) {
            temp[j] = piles[i];
        }
        // Arrays.sort(temp,Collections.reverseOrder());
        Arrays.sort(temp);
        int m=temp.length;
        int i=m-1,j=m-2;
        int alice=Math.max(piles[0],piles[n-1]);
        int bob=Math.min(piles[0],piles[n-1]);
        
        while(i>=0 && j>=0){
            alice+=temp[i];
            bob+=temp[j];
            i-=2;
            j-=2;
        }
        if(alice>bob){
            return true;
        }
        return false;
    }
}