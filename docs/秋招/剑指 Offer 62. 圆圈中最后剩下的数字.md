```java
class Solution {
    public int lastRemaining(int n, int m) {
        int pos=0;
        for(int i=2;i<=n;i++){
            pos=(pos+m)%i;
        }
        return pos;
    }
}
```

[换个角度举例解决约瑟夫环 - 圆圈中最后剩下的数字 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/huan-ge-jiao-du-ju-li-jie-jue-yue-se-fu-huan-by-as/)

求出递推公式

f(n,m)=(f(n-1,m)+m)%n

