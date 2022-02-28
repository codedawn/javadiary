```
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length<2)return 0;
        int[] dp=new int[prices.length];
        int cost=prices[0];

        for(int i=1;i<prices.length;i++){
            dp[i]=Math.max(dp[i-1],prices[i]-cost);
            cost=Math.min(cost,prices[i]);
        }
        return dp[prices.length-1];
    }
}
```

可以选择入手或者是卖出