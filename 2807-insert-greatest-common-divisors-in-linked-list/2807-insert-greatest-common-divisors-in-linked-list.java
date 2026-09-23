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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode curr=head;
        ListNode prev=null;

        while(curr.next!=null){
            prev=curr;
            curr=curr.next;
            ListNode newNode = new ListNode(findGCD(prev.val,curr.val));
            prev.next=newNode;
            newNode.next=curr;
        }
        return head;
    }
    private static int findGCD(int a,int b){
        if(b==0){
            return a;
        }
        return findGCD(b,a%b);
    }
}