// class Solution {
//     public int[][] rotateGrid(int[][] grid, int k) {
//         int i=1;
//         // int row=0,col=0;
//         int m=grid.length;
//         int n=grid[0].length;
//         while(i<=k){
//             int temp1=grid[i-1][i-1];
//             int temp2=grid[i-1][n-i];
//             int temp3=grid[m-1][i-1];
//             int temp4=grid[m-i][n-i];

//             //upper right to left
//             for(int j=i-1;j<n-i;j++){
//                 grid[i-1][j]=grid[i-1][j+1];
//             }
//             //left top to button
//             for(int j=m-i;j>=i;j--){
//                 grid[j][i-1]=grid[j-1][i-1];
//             }
//             //buttom left to right
//             for(int j=n-i;j>=i;j--){
//                 grid[m-i][j]=grid[m-i][j-1];
//             }
//             //right buttom to up
//             for(int j=i-1;j<m-i;j++){
//                 grid[j][n-i]=grid[j+1][n-i];
//             }
//             grid[i][i-1]=temp1;
//             grid[i-1][n-i-1]=temp2;
//             grid[m-i][i]=temp3;
//             grid[m-i-1][n-i]=temp4;

//             i+=1;
//         }
//         return grid;
//     }
// }

class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {
            int top = layer;
            int bottom = m - 1 - layer;
            int left = layer;
            int right = n - 1 - layer;

            int perimeter = 2 * (bottom - top + right - left);

            int rotations = k % perimeter;

            while (rotations-- > 0) {
                int temp = grid[top][left];

                // top row: left -> right
                for (int j = left; j < right; j++) {
                    grid[top][j] = grid[top][j + 1];
                }

                // right column: top -> bottom
                for (int i = top; i < bottom; i++) {
                    grid[i][right] = grid[i + 1][right];
                }

                // bottom row: right -> left
                for (int j = right; j > left; j--) {
                    grid[bottom][j] = grid[bottom][j - 1];
                }

                // left column: bottom -> top
                for (int i = bottom; i > top + 1; i--) {
                    grid[i][left] = grid[i - 1][left];
                }

                grid[top + 1][left] = temp;
            }
        }

        return grid;
    }
}