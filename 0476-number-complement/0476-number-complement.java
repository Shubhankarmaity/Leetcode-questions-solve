class Solution {
    public int findComplement(int num) {
        String s=Integer.toBinaryString(num);
        StringBuilder str=new StringBuilder("");
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1'){
                str.append('0');
            }
            else{
                str.append('1');
            }
        }
        s=str.toString();
        return Integer.parseInt(s,2);
    }
}