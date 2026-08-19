// class Solution {
//     public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
//         int temp[][]=new int[n][10];
//         for(int i=0;i<n;i++){
//             for(int j=0;j<10;j++){
//                 temp[i][j]=0;
//             }
//         }
//         for(int i=0;i<reservedSeats.length;i++){
//             int posRow=reservedSeats[i][0];
//             int posCol=reservedSeats[i][1];

//             temp[posRow-1][posCol-1]=1;
//         }
//         int ans=0;
//         for(int i=0;i<n;i++){
//             int j=1;
//             int count=0;
//             while(j<9){
//                 if(temp[i][j]==0){
//                     count++;
//                     if(count==4){
//                         ans++;
//                         count=0;
//                     }
//                 } 
//                 else{
//                     count=0;
//                 }
//                 j++;
//             }
//         }
//         return ans;
//     }
// }

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.putIfAbsent(row, new HashSet<>());
            map.get(row).add(col);
        }

        // Rows without any reservation can always have 2 families
        int ans = (n - map.size()) * 2;

        // Process only rows having reserved seats
        for (HashSet<Integer> seats : map.values()) {

            boolean left = true;    // 2,3,4,5
            boolean middle = true;  // 4,5,6,7
            boolean right = true;   // 6,7,8,9

            for (int seat : seats) {

                if (seat >= 2 && seat <= 5) {
                    left = false;
                }

                if (seat >= 4 && seat <= 7) {
                    middle = false;
                }

                if (seat >= 6 && seat <= 9) {
                    right = false;
                }
            }

            if (left && right) {
                ans += 2;
            } 
            else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
}