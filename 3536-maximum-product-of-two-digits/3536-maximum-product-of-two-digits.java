class Solution {
    public int maxProduct(int n) {
        String str=Integer.toString(n);
        int arr[]=new int[str.length()];

        for(int i=0;i<str.length();i++){
            arr[i]=str.charAt(i)-'0';
        }
        Arrays.sort(arr);

        return arr[arr.length-1] * arr[arr.length-2];
    }
}