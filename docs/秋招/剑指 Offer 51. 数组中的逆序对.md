```
class Solution {
    int re=0;
    public int reversePairs(int[] nums) {
            mergeSort(nums,0,nums.length-1);
            return re;
    }
    public void mergeSort(int[] nums,int left,int right){
        if(left>=right){
            return;
        }
        int mid=left+(right-left)/2;
        mergeSort(nums,left,mid);
        mergeSort(nums,mid+1,right);
        merge(nums,left,mid,right);
    }

    public void merge(int[] nums,int left,int mid,int right){
        int i=left;
        int j=mid+1;
        int[] tmp=new int[right-left+1];
        int l=0;
        
        while(i<=mid&&j<=right){
           
            if(nums[i]>nums[j]){
                tmp[l++]=nums[j++];
                re+=mid-i+1;
            }else{
                  tmp[l++]=nums[i++];
            }
        }
          
        while(i<=mid){
             tmp[l++]=nums[i++];
        }
        while(j<=right){
            tmp[l++]=nums[j++];
        }
      
        for(int k=0;k<l;k++){
            nums[left+k]=tmp[k];
        }
    }
}
```

归并排序

![image-20211030181911891](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211030181911891.png)

合并过程中，左边比右边大说明当前左边的数往后都可以和右边当前的数组成逆序对

re+=mid-i+1；

![image-20211030182137894](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211030182137894.png)