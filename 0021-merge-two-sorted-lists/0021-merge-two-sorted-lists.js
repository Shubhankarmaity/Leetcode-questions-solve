/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} list1
 * @param {ListNode} list2
 * @return {ListNode}
 */
var mergeTwoLists = function(list1, list2) {
    let head1=list1;
    let head2=list2;
    let ans=new ListNode(-1);
    let head=ans;

    while(head1!=null && head2!=null){
        if(head1.val<=head2.val){
            ans.next=head1;
            head1=head1.next;
            ans=ans.next;
        }
        else{
            ans.next=head2;
            head2=head2.next;
            ans=ans.next;
        }
    }
    while(head1!=null){
        ans.next=head1;
        head1=head1.next;
        ans=ans.next;
    }
    while(head2!=null){
        ans.next=head2;
        head2=head2.next;
        ans=ans.next;
    }
    return head.next;
};