class Solution {
    static class Point implements Comparable<Point>{
        int x;
        int y;
        long dist;
        int idx;
        public Point(int x,int y,long dist,int idx){
            this.x=x;
            this.y=y;
            this.dist=dist;
            this.idx=idx;
        }
        @Override
        public int compareTo(Point p2){
            return Long.compare(this.dist, p2.dist);//accending order
        }
    }
    public int[][] kClosest(int[][] points, int k) {
        int [][] ans=new int[k][2];
        Queue<Point> pq=new PriorityQueue<>();
        for(int i=0;i<points.length;i++){
            long x = points[i][0];
            long y = points[i][1];
            long dist = x * x + y * y;
            pq.add(new Point(points[i][0],points[i][1],dist,i));
        }
        for(int i=0;i<k;i++){
            int pos=pq.remove().idx;
            ans[i][0]=points[pos][0];
            ans[i][1]=points[pos][1];
        }
        return ans;
    }
}