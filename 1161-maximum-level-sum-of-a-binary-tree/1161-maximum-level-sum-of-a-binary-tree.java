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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        HashMap<Integer,Integer> hm=new HashMap<>();
        TreeNode currNode=new TreeNode(0);
        int prev=0;
        int level=1;
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){
            prev +=currNode.val;
            currNode=q.remove();

            if(currNode==null){
                if(q.isEmpty()){
                    hm.put(level,prev);
                    break;
                }
                else{
                    q.add(null);
                    hm.put(level++,prev);
                    prev=0;currNode=new TreeNode(0);
                }
            }
            else{
                if(currNode.left!=null){
                    q.add(currNode.left);
                }
                if(currNode.right!=null){
                    q.add(currNode.right);
                }
            }
        }
        int max=Integer.MIN_VALUE;
        int maxLevel=1;
        for(int i=1;i<=level;i++){
            if(hm.get(i)>max){
                max=hm.get(i);
                maxLevel=i;
            }
        }
        return maxLevel;
    }
}