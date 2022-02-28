```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null&&k>0){
            fast=fast.next;
            k--;
        }
        while(fast!=null){
            fast=fast.next;
            slow=slow.next;
        }
        return slow;
    }
   
}
```

使用双指针，一前一后，保持k的距离，也就是slow在i，fast在i+k。fast走到尾部，那slow就是在倒数第k个节点