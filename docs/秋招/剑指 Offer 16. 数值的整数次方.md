```
class Solution {
    public double myPow(double x, int n) {
        if(x==0)return 0;
        double re=1.0;
        long b=n;
        if(n<0){
            x=1/x;
            b=-b;
        }
        while(b>0){
            if((b&1)==1){
                re=re*x;
            }
            x*=x;
            b=b>>1;
        }
        return re;
    }

}
```

快速幂问题

8^5=8*(64)^2

就是当次方为偶数时，可以把2提取出来

如果是奇数可以先乘一个x



![image-20211023001357358](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211023001357358.png)