class Solution {
    public int[] resultArray(int[] nums) {
        int n=nums.length;
        List<Integer> arr1=new ArrayList<>();
        List<Integer> arr2=new ArrayList<>();
        int a1=0,a2=0;

        arr1.add(nums[0]);
        arr2.add(nums[1]);

        for(int i=2;i<n;i++){
            if(arr1.get(a1)>arr2.get(a2)){
                arr1.add(nums[i]);
                a1++;
            }
            else{
                arr2.add(nums[i]);
                a2++;
            }
        }
        int i=0;
        int j=0;
        while(j<arr1.size()){
            nums[i++]=arr1.get(j);
            j++;
        }
        int k=0;
        while(k<arr2.size()){
            nums[i++]=arr2.get(k);
            k++;
        }
        return nums;
    }
}