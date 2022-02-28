```
class Solution {
    public int strToInt(String str) {
        int length=str.length();
        if(length==0)return 0;
        int i=0;
        int sign=1;
        int re=0;
        int m=Integer.MAX_VALUE/10;
        while(str.charAt(i)==' '){
            if(++i==length)return 0;
        }
        if(str.charAt(i)=='-'){
            sign=-1;
        }
        if(str.charAt(i)=='-'||str.charAt(i)=='+'){
            i++;
        }
        for(;i<length;i++){
            if(str.charAt(i)<'0'||str.charAt(i)>'9')break;
            if(re>m||re==m&&str.charAt(i)>'7'){
                return sign==-1?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }else{
                re=re*10+(str.charAt(i)-'0');
            }
        }
        return re*sign;

    }
}
```

注意判断最大最小值