```
class MaxQueue {
    private Deque<Integer> q=new LinkedList<>();

    private Queue<Integer> q1=new LinkedList<>(); 
    public MaxQueue() {

    }
    
    public int max_value() {
        if(q1.isEmpty()){
            return -1;
        }
        return q.peek();
    }
    
    public void push_back(int value) {
        q1.offer(value);
        while(!q.isEmpty()&&q.peekLast()<value){
            q.pollLast();
        }
        q.offer(value);
    }
    
    public int pop_front() {
        if(q1.isEmpty()){
            return -1;
        }
        int p=q1.poll();
        if(q.peek()==p)q.poll();
        return p;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
```

使用双端队列，单调减

添加一个元素在队列尾，前面比它小的全部出队，保持单调减，也就是队首的最大的元素，

如果需要删除元素且正好是队首，就可以删除，因为队列是先进先出，所以前面出对的元素肯定是比当前进队元素先删除