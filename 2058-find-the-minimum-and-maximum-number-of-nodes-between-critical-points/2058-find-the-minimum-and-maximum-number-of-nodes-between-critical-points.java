/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev=null;
        ListNode curr=head;
        int i=2;
        List<Integer> list=new ArrayList<>();

        while(curr.next.next!=null){
            prev=curr;
            curr=curr.next;
            //maximum
            if(curr.val>prev.val && curr.val>curr.next.val){
                list.add(i);
            }
            if(curr.val<prev.val && curr.val<curr.next.val){
                list.add(i);
            }
            i++;
        }
        int ans[]=new int[2];
        int minDist=-1,maxDist=-1;

        if(!list.isEmpty() && list.size()>1){
            maxDist=list.get(list.size()-1) - list.get(0);
            minDist=list.get(1) - list.get(0);
            for(int j=1;j<list.size();j++){
                if((list.get(j) - list.get(j-1))<minDist){
                    minDist=list.get(j) - list.get(j-1);
                }
            }
        }
        ans[0]=minDist;
        ans[1]=maxDist;
        return ans;
    }
}