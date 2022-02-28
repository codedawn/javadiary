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
    public boolean isSymmetric(TreeNode root) {
        return root==null?true:check(root.right,root.left);
    }
    public static boolean check(TreeNode l,TreeNode r){
        if(l==null&&r==null) return true;
        if(l==null||r==null||l.val!=r.val)return false;
        return check(l.left,r.right)&&check(l.right,r.left);
    }
}
```

可以理解为判断两个树是不是对称，

左子树和另一半的右子树比

右子树和另一半的左子树比