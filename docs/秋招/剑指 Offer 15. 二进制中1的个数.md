```
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int re=0;
        while(n!=0){
           n&=n-1;
           re++;
        }
        return re;
    }
}
```

n-1是把最后一个1转化为0

n&=n-1每次消除一个0

直到n==0