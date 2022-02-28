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
    private Map<Integer,Integer> m=new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0;i<inorder.length;i++){
            m.put(inorder[i],i);
        }
        return help(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
    }
    public TreeNode help(int[] preorder,int[] inorder,int preLeft,int preRight,int inLeft,int inRight){
        if(preLeft>preRight)
            return null;
        int n=m.get(preorder[preLeft]);
        TreeNode root=new TreeNode(preorder[preLeft]);
        int size=n-inLeft;
        root.left=help(preorder,inorder,preLeft+1,preLeft+size,inLeft,n-1);
        root.right=help(preorder,inorder,preLeft+size+1,preRight,n+1,inRight);
        return root;
    }
}
```

前序的第一个是当前节点，中序中找到这个节点的位置，左边是左子树，右边是右子树，递归求解