```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null){
            return null;
        }
        ListNode n1=headA,n2=headB;
        while(n1!=n2){
            n1=n1==null ? headB:n1.next;
            n2=n2==null ? headA:n2.next;
        }
        return n1;
    }
}
```

先遍历一遍1链表然后再遍历2链表，把节点步调变成一致的，就是m+n