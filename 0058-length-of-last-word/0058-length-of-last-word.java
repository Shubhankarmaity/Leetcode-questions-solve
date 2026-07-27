class Solution {
    public int lengthOfLastWord(String s) {
        Stack<Character> st=new Stack<>();
        StringBuilder temp=new StringBuilder("");
        for(int i=0;i<s.length();i++){
            
            if(s.charAt(i)==' ' && !st.isEmpty()){
                temp=new StringBuilder("");
                while(!st.isEmpty()){
                    temp.append(st.pop());
                }
            }
            else if(s.charAt(i)==' ' && st.isEmpty()){
                continue;
            }
            else{
                st.push(s.charAt(i));
            }
        }
        if(!st.isEmpty()){
            return st.size();
        }
        return temp.length();
    }
}