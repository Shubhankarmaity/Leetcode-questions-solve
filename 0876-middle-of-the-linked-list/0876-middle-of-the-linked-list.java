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
    public ListNode middleNode(ListNode head) {
        int sz=0;
        ListNode temp=head;
        while(temp!=null){
            temp=temp.next;
            sz++;
        }
        int mid=findMid(sz);

        int i=1;
        ListNode prev=head;
        while(i<=mid){
            prev=prev.next;
            i++;
        }
        head=prev;
        return head;
        
    }
    private static int findMid(int sz){
        int mid;
        if(sz%2==0){
            mid=sz/2 ;
        }
        else{
            mid=sz/2;
        }
        return mid;
    }
}