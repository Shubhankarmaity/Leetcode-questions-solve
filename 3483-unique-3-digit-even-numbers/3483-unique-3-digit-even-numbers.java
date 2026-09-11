class Solution {
    public int totalNumbers(int[] digits) {
        StringBuilder sb =new StringBuilder("");
        int n=digits.length;
        Set<String> set =new HashSet<>();

        for(int i=0;i<n;i++){
            if (digits[i] == 0) continue;
            sb.append(digits[i]);
            for(int j=0;j<n;j++){
                if(i==j){
                    continue;
                }
                sb.append(digits[j]);
                for(int k=0;k<n;k++){
                    if(k==j || k==i){
                        continue;
                    }
                    sb.append(digits[k]);
                    if(digits[k]%2==0){
                        set.add(sb.toString());
                    }
                    sb.delete(sb.length()-1,sb.length());
                }
                sb.delete(sb.length()-1,sb.length());
            }
            sb.delete(sb.length()-1,sb.length());
        }

        return set.size();
    }
}