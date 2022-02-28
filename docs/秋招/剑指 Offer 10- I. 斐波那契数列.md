```
class Solution {
    public int fib(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
       int a=0,b=0,c=1;
       for(int i=2;i<=n;i++){
           a=b;
           b=c;
           c=(a+b)%1000000007;
       }
       return c;
    }
}
```

使用dp，利用之前的结果，进行滚动

a=b

b=c

c=a+b