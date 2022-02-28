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
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
       return depth(root)!=-1;
    }

    public int depth(TreeNode root){
        if(root==null)return 0;
        int i=depth(root.left);
        if(i==-1){
            return -1;
        }
        int j=depth(root.right);
        if(j==-1){
            return -1;
        }
        if(Math.abs(i-j)>1)return -1;
        return Math.max(i,j)+1;
    }
}
```

如果左右相差深度大于1就返回-1

或者左右子树返回-1，说明子树不是二叉平衡树