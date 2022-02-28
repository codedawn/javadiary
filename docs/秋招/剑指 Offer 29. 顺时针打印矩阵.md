```
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length==0) return new int[];
        int[] re=new int[matrix.length*matrix[0].length];
        int sum=matrix.length*matrix[0].length;
        int t=0;
        int l=0;
        int r=matrix[0].length-1;
        int b=matrix.length-1;
        int count=0;
        
        while(count<sum){
           // System.out.println(re[count]);
            for(int i=l;i<=r;i++){
                re[count++]=matrix[t][i];
            }
            if(count>=sum)break;
            t++;
            for(int i=t;i<=b;i++){
                re[count++]=matrix[i][r];
            }
             if(count>=sum)break;
             r--;
            for(int i=r;i>=l;i--){
                re[count++]=matrix[b][i];
            }
             if(count>=sum)break;
            b--;
             for(int i=b;i>=t;i--){
                re[count++]=matrix[i][l];
                
            }
             if(count>=sum)break;
            l++;
        }
        return re;
    }
}
```

设定边界，如果四面打印一面收缩一次