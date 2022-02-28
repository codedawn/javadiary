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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if(root==null||root==p||root==q){
          return root;
      }
      TreeNode l=lowestCommonAncestor(root.left,p,q);
      TreeNode r=lowestCommonAncestor(root.right,p,q);
      if(l!=null&&r!=null){
          return root;
      }
      if(l!=null){
          return l;
      }
      return r;
    }
}
```

思路：从根节点开始遍历，如果p和q中的任一个和root匹配，那么root就是最低公共祖先。 如果都不匹配，则分别递归左、右子树，如果有一个 节点出现在左子树，并且另一个节点出现在右子树，则root就是最低公共祖先.  如果两个节点都出现在左子树，则说明最低公共祖先在左子树中，否则在右子树


返回null说明节点不在这边