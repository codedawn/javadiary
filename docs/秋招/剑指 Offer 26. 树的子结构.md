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
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        
        return (A!=null&&B!=null)&&(check(A,B)||isSubStructure(A.left,B)||isSubStructure(A.right,B));
    }
    public boolean check(TreeNode A,TreeNode B){
        if(B==null)return true;
        if(A==null)return false;
        if(A.val==B.val) {
            return check(A.left,B.left)&&check(A.right,B.right);
        }else{
            return false;
        }
    
    }
}
```

A的每一个节点都作为B的头节点进行递归比较一次

