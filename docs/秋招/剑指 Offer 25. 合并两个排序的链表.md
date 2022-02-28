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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead=new ListNode(0);
        ListNode tmp=newHead;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                tmp.next=l1;
                tmp=l1;
                l1=l1.next;
            }else{
                tmp.next=l2;
                tmp=l2;
                l2=l2.next;
            }
        }
        tmp.next=(l1==null?l2:l1);
        return newHead.next;

    }
}
```

引入一个伪头节点，后面的就是归并链表就可以了