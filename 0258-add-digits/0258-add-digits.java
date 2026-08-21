class Solution {
    public int addDigits(int num) {
        String str=Integer.toString(num);
        
        while(str.length()>1){
            int sum=0;
            for(int i=0;i<str.length();i++){
                sum+=str.charAt(i)-'0';
            }
            str=Integer.toString(sum);
        }
        return Integer.parseInt(str);
    }
}