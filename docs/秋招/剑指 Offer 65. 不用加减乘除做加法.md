```
class Solution {
    public int add(int a, int b) {
        if(b==0)return a;
        while(b!=0){
            int c=(a&b)<<1;
            a^=b;
            b=c;
        }
        return a;
    }
}
```

异或是无进位相加，所以当a^b是如果不需要进位，那a^b的结果就是a+b

c=(a&b)<<1;就是a+b产生的进位，因为只有a和b同一位都是1时，就需要进位，&结果中某位为1就说明这位需要进位，<<1就是需要加上的数，所以b可以看成需要加上的进位结果，b==0，说明不需要进位，结果就等于a^b



![image-20211023132821260](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211023132821260.png)

![image-20211023132836209](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211023132836209.png)