```
class Solution {
    public boolean isStraight(int[] nums) {
        Set<Integer> s=new HashSet<Integer>();
        int min=14;int max=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0) continue;
            
            if(s.contains(nums[i])){
                return false;
            }else{
                s.add(nums[i]);
            }
            min=Math.min(min,nums[i]);
            max=Math.max(max,nums[i]);
        }
        return max-min<5;
    }
}
```

5个数想组成顺子，最小和最大差值必须小于5，也不能重复，所以可以使用set去重，然后计算差值