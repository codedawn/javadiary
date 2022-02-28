```
class Solution {
    public int[] exchange(int[] nums) {
        if(nums.length<1)return nums;
        int i=0,j=nums.length-1;
        while(i<j){
            while(i<j&&nums[j]%2==0)j--;
            while(i<j&&nums[i]%2==1)i++;
            swap(nums,i,j);
        }
        return nums;
    }
    public static void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
```

双指针，一个在左边找偶数，一个在右边找奇数，然后交换

指针需要前后数据的一般考虑双指针，加map，map可以记录上一个节点

分层