```
class Solution {
    public int numWays(int n) {
        if(n==0){
            return 1;
        }
        int a=0,b=0,c=1;
        for(int i=1;i<=n;i++){
            a=b;
            b=c;
            c=(a+b)%1000000007;
        }
        return c;
    }
}
```

同斐波那契数列