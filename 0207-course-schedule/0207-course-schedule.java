class Solution {
    
    public static boolean isCycle(int src, boolean[]vis, boolean[] recPath,int [][] edges){
        vis[src]=true;
        recPath[src]=true;

        for(int i=0;i<edges.length;i++){
            int v=edges[i][0];
            int u=edges[i][1];
            if(u==src){
                if(!vis[v]){
                    if(isCycle(v,vis,recPath,edges)){
                        return true;
                    }
                }
                else if(recPath[v]){
                    return true;
                }
            }
        }
        recPath[src]=false;
        return false;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean []vis=new boolean[numCourses];
        boolean []recPath=new boolean[numCourses];

        for(int i=0;i<numCourses;i++){
            if(!vis[i]){
                if(isCycle(i,vis,recPath,prerequisites)){
                    return false;
                }
            }
        }
        return true;
    }
}