```
class MedianFinder {
    private PriorityQueue<Integer> minQ=new PriorityQueue<Integer>();
    private PriorityQueue<Integer> maxQ=new PriorityQueue<Integer>((x,y)->y-x);
    /** initialize your data structure here. */
    public MedianFinder() {

    }
    
    public void addNum(int num) {
        if(minQ.size()==maxQ.size()){
            minQ.offer(num);
            maxQ.offer(minQ.poll());
        }else{
            maxQ.offer(num);
            minQ.offer(maxQ.poll());
        }
    }
    
    public double findMedian() {
        if(minQ.size()==maxQ.size()){
            return (minQ.peek()+maxQ.peek()*1.0)/2;
        }else{
            return maxQ.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

有两个堆

一个大顶堆

一个小顶堆

相当于把一个有序数组 [1,2,4,7,10,30] 分成了两部分  [1,2,4]     [10,30]

1.

大顶堆就左边部分，堆顶就是中位数

小顶堆就是右边部分，堆顶也是中位数



插入时，如果插入大顶堆，就要把大顶堆的堆顶移到小顶堆

反之就是把小顶堆的堆顶往大顶堆移动，保证1.部分

轮流插入，保证两个堆大小最多差1

