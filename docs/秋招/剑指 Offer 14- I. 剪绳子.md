```
class Solution {
    public int cuttingRope(int n) {
        int[] dp=new int[n+1];
        dp[2]=1;
        for(int i=3;i<=n;i++){
            for(int j=1;j<i;j++){
                dp[i]=Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));
            }
        }
        return dp[n];
    }
}
```

动态规划

可以剪

 j*（i-j） 表示剩下的不减

j*dp[i-j]s