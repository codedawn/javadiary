```
class Solution {
    public int[][] findContinuousSequence(int target) {
            int i=1,j=2,s=3;
            List<int[]> l=new ArrayList<>();
            while(i<j){
                if(s==target){
                    int[] tmp=new int[j-i+1];
                    for(int k=i;k<=j;k++){
                        tmp[k-i]=k;
                    }
                    l.add(tmp);
                  

                }
                 if(s<target){
                    j++;
                    s+=j;
                }else {
                    s-=i;
                    i++;
                }
            }
            return l.toArray(new int[0][]);
    }
}
```

双指针

s大于等于s，就右移动i，

s小于s，就左移j

![image-20211024235207062](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211024235207062.png)