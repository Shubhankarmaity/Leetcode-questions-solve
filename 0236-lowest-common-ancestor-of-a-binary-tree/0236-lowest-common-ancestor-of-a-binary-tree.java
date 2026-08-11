/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> path1=new ArrayList<>();
        ArrayList<TreeNode> path2=new ArrayList<>();
        getPath(root,path1,p);
        getPath(root,path2,q);

        int i=0;
        for(;i<path1.size() && i<path2.size();i++){
            if(path1.get(i)!=path2.get(i)){
                break;
            }
        }
        TreeNode lca=path1.get(i-1);
        return lca;
    }
    private static boolean getPath(TreeNode root,List<TreeNode> path,TreeNode target){
        if(root==null){
            return false;
        }
        path.add(root);
        if(root==target){
            return true;
        }
        boolean foundLeft=getPath(root.left,path,target);
        boolean foundRight=getPath(root.right,path,target);

        if(foundLeft || foundRight){
            return true;
        }
        path.remove(path.size()-1);
        return false;
    }
}