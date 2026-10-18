class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
       if(head==null || left==right){
        return head;
       }
       ListNode before = null;
       ListNode last=head;
       for(int i=1;i<left;i++){
        before=last;
        last=last.next;
       }
       ListNode cur=last;
       ListNode prev = null;

       for(int i=0;i<right -left+1;i++){
        ListNode next=cur.next;
        cur.next=prev;
        prev=cur;
        cur=next;
       }
       if(before!=null){
        before.next=prev;
       }
       else{
        head=prev;
       }
       last.next=cur;

      return head;
    }
}