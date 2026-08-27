// class Solution {
//     public String lexGreaterPermutation(String s, String target) {
//         List<String> ans = new ArrayList<>();
//         findPermutation(s,"",ans);
//         Collections.sort(ans);

//         for(String st: ans){
//             if(st.compareTo(target)>0){
//                 return st;
//             }
//         }
//         return "";
//     }
//     private static void findPermutation(String s, String temp,List<String> ans){
//         if(s.length()==0){
//             ans.add(temp);
//             return;
//         }
//         for(int i=0;i<s.length();i++){
//             char curr=s.charAt(i);
//             String NewStr = s.substring(0, i) + s.substring(i + 1);
//             findPermutation(NewStr, temp + curr,ans);
//         }
//     }
// }

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {
            int curr = target.charAt(i) - 'a';

            if (freq[curr] > 0) {
                freq[curr]--;
                ans.append(target.charAt(i));
            } else {
                
                for (int c = curr + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        ans.append((char) ('a' + c));
                        freq[c]--;

                        appendRemaining(ans, freq);
                        return ans.toString();
                    }
                }

                break;
            }
        }

        
        for (int i = ans.length() - 1; i >= 0; i--) {
            int curr = ans.charAt(i) - 'a';

            freq[curr]++;

            for (int c = curr + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    ans.setLength(i);
                    ans.append((char) ('a' + c));
                    freq[c]--;

                    appendRemaining(ans, freq);
                    return ans.toString();
                }
            }
        }

        return "";
    }

    private void appendRemaining(StringBuilder ans, int[] freq) {
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                ans.append((char) ('a' + c));
                freq[c]--;
            }
        }
    }
}