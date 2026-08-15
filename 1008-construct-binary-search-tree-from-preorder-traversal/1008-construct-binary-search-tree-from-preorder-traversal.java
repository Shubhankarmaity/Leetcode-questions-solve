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
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root=null;
        for(int i=0;i<preorder.length;i++){
            root=helper(root,preorder[i]);
        }
        return root;
    }
    public static TreeNode helper(TreeNode root,int key){
        if(root==null){
            root=new TreeNode(key);
            return root;
        }
        if(root.val>key){
            root.left=helper(root.left,key);
        }
        else{
            root.right=helper(root.right,key);
        }
        return root;
    }
}