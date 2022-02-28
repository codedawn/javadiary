```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private Deque q=new LinkedList<Integer>();
    private List<List<Integer>> list= new ArrayList<List<Integer>>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if(root==null){
            return new ArrayList<List<Integer>>();
        }
        recur(root,0,target);
        return list;
    }

    public void recur(TreeNode root,int sum,int target){
        if(root==null) return;
        sum+=root.val;
        q.offerLast(root.val);
      //  System.out.println(q);
        if(sum==target&&root.left==null&&root.right==null){
            list.add(new LinkedList<Integer>(q));
        }
        recur(root.left,sum,target);
        recur(root.right,sum,target);
        q.pollLast();
    }
}
```

使用递归，把路径保存在一个队列中，如果队列的和等于target并且到达叶子就说明找到一条路径