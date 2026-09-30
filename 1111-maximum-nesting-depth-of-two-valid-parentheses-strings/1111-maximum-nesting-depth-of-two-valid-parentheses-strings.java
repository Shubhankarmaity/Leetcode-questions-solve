class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int d=0;
        int []result=new int[seq.length()];

        for(int i=0;i<seq.length();i++){
            if(seq.charAt(i)=='('){
                d++;
                result[i]=(d%2 == 0)?0:1;
            }
            else{
                result[i]=(d%2==0)?0:1;
                d--;
            }
        }
        return result;
    }
}