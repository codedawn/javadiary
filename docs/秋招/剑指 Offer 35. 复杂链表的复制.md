```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node,Node> m=new HashMap<>();
        for(Node n=head;n!=null;){
            m.put(n,new Node(n.val));
            n=n.next;
        }
        Node newHead=m.get(head);
        Node tmp=newHead;
        for(Node n=head;n!=null;){
            tmp.next=n.next==null?null:m.get(n.next);
            tmp.random=n.random==null?null:m.get(n.random);
            n=n.next;
            tmp=tmp.next;
        }
        return newHead;
    }
}
```

先遍历一遍，用map映射原节点和新节点

然后再遍历一次，连接新节点，但是要主要null的情况