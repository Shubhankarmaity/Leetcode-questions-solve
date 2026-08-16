/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if(root.left==null && root.right==null){
            return false;
        }
        List<Integer> temp =new ArrayList<>();
        inorder(root,temp);
        int sum=0;
        int i=0,j=temp.size()-1;
        while(i<j){
            sum=temp.get(i)+temp.get(j);
            if(sum==k){
                return true;
            }
            else if(sum>k){
                j--;
            }
            else{
                i++;
            }
        }
        return false;
        
    }
    private static void inorder(TreeNode root,List<Integer> temp){
        if(root==null){
            return;
        }
        inorder(root.left,temp);
        temp.add(root.val);
        inorder(root.right,temp);
    }
}