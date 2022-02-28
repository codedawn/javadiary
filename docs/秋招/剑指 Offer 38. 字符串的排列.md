```
class Solution {
    List<String> re=new LinkedList<>();

    char[] c;
    public String[] permutation(String s) {
        c=s.toCharArray();
        dfs(0);
        return re.toArray(new String[re.size()]);
    }

    public void dfs(int x){
        if(x==c.length-1){
            re.add(String.valueOf(c));
            return ;
        }
        Set<Character> s=new HashSet<>();
        for(int i=x;i<c.length;i++){
            if(s.contains(c[i])){
                continue;
            }
            s.add(c[i]);
            swap(i,x);
            dfs(x+1);
            swap(i,x);
        }
    }

    public void swap(int i,int j){
        char tmp=c[i];
        c[i]=c[j];
        c[j]=tmp;
    }
}
```

递归，然后递归了for循环递归当前位置所以选择

比如选择需要固定第一个位置

第一个位置其实可以是所有元素中的之一

第二个位置其实可以是所有元素中的之一

。。。

然后需要剪枝

比如

aab

两个a相同，所以a开头只需要考虑第一个a，第二个a需要剪枝

