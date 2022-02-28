1.线程池参数，线程池处理流程

[Java线程池必备知识点：工作流程、常见参数、调优、监控-技术圈 (proginn.com)](https://jishuin.proginn.com/p/763bfbd55f8f)

[你知道线程池的 创建方式、7大参数、处理流程 和 最大线程数量该如何配置吗 - 掘金 (juejin.cn)](https://juejin.cn/post/6988062267640119332)

使用线程池的好处：

降低资源消耗：

重复利用线程，减少了创建和销毁线程 的花销

提高响应速度：

有一些线程是一直存活的，任务一来可以马上工作（第一次是需要创建的）



提供线程 的可管理性

可以对线程进行调优和监控，统一管理



线程池的工作流程，

假如一个新的任务到来：

1.如果运行 的线程数小于corePoolSize，就创建一个新的线程来执行任务，如果corePoolSize已经最大，走下一流程

2.如果任务队列还没有满，那就把新任务放入任务队列

3.如果任务队列已经满了，则会判断线程是否小于maximumPoolSize，小于就创建新线程，执行任务

4.如果队列满了，线程也大于或等于maximumPoolSize，就触发拒绝策略

 七个参数：

corePoolSIze

maximumPoolSize

workQueue

keepAliveTime

unit

handler

threadFactory



饱和策略：

abortPolicy：直接抛异常

callerrunpolicy：让调用者执行

discardoldestPolicy抛弃等待最久的任务，然后提交当前任务

discardpolicy直接丢弃任务，不做处理



自定义线程：

```
ExecutorService executor = new ThreadPoolExecutor(5,10,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1000),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
```



cpu密集型

需要大量运算，阻塞比较少

cpu核数+1

io密集型 

io操作比较多，阻塞比较多

cpu核数*2



阻塞队列take会阻塞
