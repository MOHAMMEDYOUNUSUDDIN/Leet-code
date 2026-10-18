class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode last = head;
        int n = 1;

        while(last.next!=null){
            last=last.next;
            n++;
        }

        k=k%n;

        if(k==0){
            return head;
        }

        last.next=head;

        int steps = n - k - 1;
        ListNode newLast = head;

        for(int i=0;i<steps;i++){
            newLast=newLast.next;
        }

      

        ListNode newHead=newLast.next;
        newLast.next=null;

        return newHead;
    }
}