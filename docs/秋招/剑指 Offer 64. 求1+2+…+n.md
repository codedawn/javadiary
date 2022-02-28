```
class Solution {
   
    public int sumNums(int n) {
            boolean flag=n>0&&((n+=sumNums(n-1))>0);
            return n;
        
    }
}

```

不能使用循环，可以用递归代替，不可以使用if可以用&&  || 等逻辑运算代替

