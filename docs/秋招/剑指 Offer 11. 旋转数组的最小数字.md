```
class Solution {
    public int minArray(int[] numbers) {
        int low=0;
        int high=numbers.length-1;
        int mid=low+(high-low)/2;
        while(low<high){
            mid=low+(high-low)/2;
            if(numbers[mid]>numbers[high]){
                low=mid+1;
            }else if(numbers[mid]<numbers[high]){
                high=mid;
            }else{
                high--;
            }
        }
        return numbers[low];
    }
}
```

如果number[mid] 大于number[high]什么说明不是递增

所以最小数在这个区间

如果number[mid] 小于number[high]说明是递增，

最小数不在这个区间



如果相等不知道在那边，所以只能去掉high

