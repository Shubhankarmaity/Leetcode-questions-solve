/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode[]} lists
 * @return {ListNode}
 */
var mergeKLists = function(lists) {
    if (lists.length === 0) {
        return null;
    }
    let ans=lists[0];
    for(let i=1;i<lists.length;i++){
        ans=merge(ans,lists[i]);
    }
    return ans;
};
/**
 * @param {ListNode[]} list1
 * @param {ListNode[]} list2
 * @return {ListNode}
 */
var merge=function(list1,list2){
    let l1=list1;
    let l2=list2;
    let dummy = new ListNode(-1);
    let ans = dummy;
    while(l1!=null && l2!=null){
        if(l1.val<=l2.val){
            ans.next=l1;
            l1=l1.next;
        }
        else{
            ans.next=l2;
            l2=l2.next;
        }
        ans=ans.next;
    }
    while(l1!=null){
        ans.next=l1;
        ans=ans.next;
        l1=l1.next;
    }
    while(l2!=null){
        ans.next=l2;
        ans=ans.next;
        l2=l2.next;
    }
    return dummy.next;
}