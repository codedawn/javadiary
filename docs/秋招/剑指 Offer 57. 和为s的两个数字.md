```
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i=0,j=nums.length-1;
        while(i<j){
            int s=nums[i]+nums[j];
            if(s>target){
                j--;
            }else if(s<target){
                i++;
            }else{
                return new int[]{nums[i],nums[j]};
            }
        }
        return null;
    }
}
```

使用双指针，一个在头，一个在为，因为数组是递增的，所以s小于target就头指针往前，

s大于target就尾指针往前以