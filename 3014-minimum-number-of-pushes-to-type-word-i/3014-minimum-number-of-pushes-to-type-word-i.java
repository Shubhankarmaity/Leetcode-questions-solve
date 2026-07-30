class Solution {
    public int minimumPushes(String word) {
        int n=word.length();
        int count=1;
        int push=0;

        for(int i=1;i<n+1;i++){
            if(i%8==0){
                push+=count;
                count+=1;
                continue;
            }
            push+=count;
        }
        return push;
    }
}