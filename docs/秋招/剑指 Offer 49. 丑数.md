```
class Solution {
    public int nthUglyNumber(int n) {
        int a=0,b=0,c=0;
        int[] dp=new int[n];
        dp[0]=1;
        for(int i=1;i<n;i++){
            int n1=dp[a]*2;
            int n2=dp[b]*3;
            int n3=dp[c]*5;
            int tmp=Math.min(n1,Math.min(n2,n3));
            dp[i]=tmp;
            if(n1==tmp)a++;
            if(n2==tmp)b++;
            if(n3==tmp)c++;
        }
        return dp[n-1];
    }
}
```

[质因子分解 (shuxuele.com)](https://www.shuxuele.com/prime-factorization.html)

![image-20211030152414641](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211030152414641.png)

丑数就是分解到不能分解，质因子都是2，3，5组成



dp[0]=1 

所以从1开始，乘2，乘3，乘5，得到肯定也是丑数，

dp[1]=2

第二个也可以，乘2，乘3，乘5，得到肯定也是丑数，

但是必须要最小的丑数在前面，也就是要递增，

```
			int n1=dp[a]*2;
            int n2=dp[b]*3;
            int n3=dp[c]*5;
            int tmp=Math.min(n1,Math.min(n2,n3));
```

所以要取最小值

