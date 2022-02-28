```java
class Solution {
    public int maxValue(int[][] grid) {
       for(int i=1;i<grid[0].length;i++){
           grid[0][i]+=grid[0][i-1];
       }
        for(int i=1;i<grid.length;i++){
           grid[i][0]+=grid[i-1][0];
       }
       for(int i=1;i<grid.length;i++){
           for(int j=1;j<grid[0].length;j++){
               grid[i][j]=Math.max(grid[i-1][j],grid[i][j-1])+grid[i][j];
           }
       }
       return grid[grid.length-1][grid[0].length-1];
    }
}
```

走到一个点，只能从左边或者上边；

然后原数组作为dp数组

grid [i] [j] 表示到达这个点获得的最大价值