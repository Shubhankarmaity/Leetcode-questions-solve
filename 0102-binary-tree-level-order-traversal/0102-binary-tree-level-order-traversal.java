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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> inner=new ArrayList<>();
        levelOrder(root,ans,inner);

        return ans;
    }

    private static void levelOrder(TreeNode root,List<List<Integer>> ans,List<Integer> inner){
        Queue<TreeNode> q=new LinkedList<>();
        if(root==null){
            return;
        }
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            TreeNode currNode=q.remove();
            if(currNode==null){
                ans.add(inner);
                inner=new ArrayList<>();
                if(q.isEmpty()){
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else{
                inner.add(currNode.val);
                if(currNode.left!=null){
                    q.add(currNode.left);
                }
                if(currNode.right!=null){
                    q.add(currNode.right);
                }
            }
        }
    }
}