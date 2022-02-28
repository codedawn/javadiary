```
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
       int maxR=matrix.length;
       int row=0;
       if(maxR<1)return false;
       int column=matrix[0].length-1;
       boolean re=false;
        while(row<maxR&&column>=0){
            if(matrix[row][column]>target){
                column--;
            }else if(matrix[row][column]<target){
                row++;
            }else{
                re=true;
                break;
            }
        }
        return re;
    }
}
```

从右上角开始，row递增，column递减。matrix [row] [column]大于target，说明需要往左找，matrix [row] [column]小于target，说明需要往下找