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
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode l=mirrorTree(root.left);
        TreeNode r=mirrorTree(root.right);
        root.left=r;
        root.right=l;
        return root;
    }
}
```

可以先递归到叶子节点开始反转，自底向上反转