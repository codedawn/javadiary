```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Integer re=0;
    private Integer flag=0;
    private Integer count=0;
    public int kthLargest(TreeNode root, int k) {
       flag=k;
       order(root);
        return re;
    }

    public void order(TreeNode n){
        if(n==null){
            return ;
        }
        if(count>flag)return;
        order(n.right);
        count++;
       // System.out.println(n.val+"   count="+count);
        if(count==flag){
            re=n.val;
            return;
        }
        order(n.left);
    }
}
```

首先二叉搜索树中序遍历是有序的，而且是递增的，那么中序遍历的倒序是递减的，也就是右根左

，就求第k个数就ok了