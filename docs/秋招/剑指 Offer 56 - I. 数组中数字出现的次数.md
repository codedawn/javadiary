```
class Solution {
    public int[] singleNumbers(int[] nums) {
        int x=0;
        for(int i=0;i<nums.length;i++){
            x^=nums[i];
        }
        int right=x &(~x+1);
        int a=0;

        for(int i=0;i<nums.length;i++){
            if((nums[i]&right)==0){
                a^=nums[i];
            }
        }
        int b=x^a;
        return new int[]{a,b};
    }
}
```

异或 a^a=0  a^0=a 无进位相加，所以全部异或一遍数，出现偶数次的就抵消了

结果x=a^b

然后a和b不相等的地方异或之后是1，int right=x&(~x+1) 可以拿到最低位的1

然后通过right分组，nums[i]&right==0的分为一组，>0的分为另一组

然后就可以得到a

进而得到b