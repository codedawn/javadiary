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
public class Codec {

     

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        if(root==null)return "[]";
        q.offer(root);
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        while(!q.isEmpty()){
            TreeNode n=q.poll();
            if(n==null) {
                sb.append("null"+",");
                continue;
            }
            q.offer(n.left);
            q.offer(n.right);
            sb.append(n.val+",");
        }
        sb.replace(sb.length()-1,sb.length(),"");
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<TreeNode> q=new LinkedList<>();
        String[] splits=data.substring(1,data.length()-1).split(",");
       // System.out.println(Arrays.toString(splits));
        if(splits[0].equals(""))return null;
        TreeNode head=new TreeNode(Integer.valueOf(splits[0]));
        q.offer(head);
        for(int i=1;!q.isEmpty();){
            TreeNode n=q.poll();
            if(n!=null){
                if(i<splits.length){
                    // System.out.println(splits[i]);
                    if(splits[i].equals("null")){
                        q.offer(null);
                    }else{
                     n.left=new TreeNode(Integer.valueOf(splits[i]));
                     q.offer(n.left);
                    }
                    i++;
                }
                if(i<splits.length){
                   // System.out.println(splits[i]);
                     if(splits[i].equals("null")){
                        q.offer(null);
                    }else{
                      n.right=new TreeNode(Integer.valueOf(splits[i]));
                      q.offer(n.right);
                    }
                    i++;
                }
            }
        }
        return head;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

queue是可以放null的，

所以层序遍历树

然后反序列化时，加入队列保证left先于right