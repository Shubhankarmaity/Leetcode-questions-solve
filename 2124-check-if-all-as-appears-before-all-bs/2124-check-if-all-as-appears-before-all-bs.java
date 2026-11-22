class Solution {
    public boolean checkString(String s) {
        int n=s.length();

        for(int i=1;i<n;i++){
            if(s.charAt(i-1)>s.charAt(i)){
                return false;
            }
        }
        return true;
    }
}