```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> q=new LinkedList<Integer>();
        int l=0;
        for(int i=0;i<pushed.length;i++){
            q.push(pushed[i]);
            while((!q.isEmpty())&&popped[l]==q.peek()){
                l++;
                q.pop();
            }
        }
        return q.isEmpty();
    }
}
```

栈是先进后出，所以出栈队列前面的肯定是比后面的迟入栈，先出栈

然后数字是唯一的

所以可以通过pushed数组模拟入栈

遇到和poped相等的直接出栈

如果队列是空就是true