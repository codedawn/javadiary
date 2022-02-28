```java
class CQueue {

    private Deque<Integer> stack1=new LinkedList<>();

    private Deque<Integer> stack2=new LinkedList<>();
    public CQueue() {

    }
    
    public void appendTail(int value) {
            stack1.push(value);
    }
    
    public int deleteHead() {
           
           if(stack2.size()<=0){
                while(stack1.size()>0){
                    stack2.push(stack1.pop());
                }
           }
           if(stack2.size()>0){
               return stack2.pop();
           }else{
               return -1;
           }
    }
}
```

先判断第二个栈是否有元素，有就返回

没有就把第一个stack的push进来