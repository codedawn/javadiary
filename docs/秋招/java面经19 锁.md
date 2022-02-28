对象头的mark word 8字节

对象头，元指针，实例数据，对齐填充

unsafe可以直接访问内存，底层cas，cpu原生支持，多核会加锁，所以是原子性

[Java并发编程：Synchronized底层优化（偏向锁、轻量级锁） - liuxiaopeng - 博客园 (cnblogs.com)](https://www.cnblogs.com/paddix/p/5405678.html)



对象加final





synchronized和lock的区别

1.lock是一个接口，synchronized是java的关键字，synchronized

是内置的语言实现

2.异常时释放锁

synchronized发生异常会自动释放锁，字节码层面会加上，不会死锁

而lock需要自己操作，所以一定要在finally释放锁unlock，避免死锁

3.是否响应中断

lock可以使用lock interruptibly

4.是否知道获取锁

lock可以通过trylock来知道有没有获取锁

5.lock提供多线程的读效率，readwritelock

6.性能如果竞争激烈lock性能比较好

7.synchronized使用object的wait，notify，lock使用condition await，signal

lock可以实现公平锁

[看完你就明白的锁系列之锁的状态 - 程序员cxuan - 博客园 (cnblogs.com)](https://www.cnblogs.com/cxuanBlog/p/11684390.html)

[深度分析：锁升级过程和锁状态，看完这篇你就懂了！ - SegmentFault 思否](https://segmentfault.com/a/1190000022904663)

![image-20211027094953701](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211027094953701.png)

![image-20211027094122892](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211027094122892.png)

目前锁状态01，无锁，如果访问一个同步代码块，然后就升级成偏向锁

cas申请锁线程id指向当前线程，如果失败，说明有竞争，就升级成轻量级锁

轻量级锁标志位00，如果获取锁，自旋n次，如果还不行，锁升级为重量级锁

锁标志位11

无锁：是判断是否是偏向锁

偏向锁：偏向线程id  不需要额外的消耗，不会主动释放偏向锁，竞争时才会，等到线程安全点恢复到无锁状态或者是轻量级锁

轻量级锁（自旋锁）：栈中锁记录指针 需要消耗cpu自旋，默认10次

重量级锁：指向互斥量的指针 monitor 需要阻塞等待线程



new 初始状态

ready状态 时间片用完，或者是有资格获取时间片，yield

运行中操作running

blocked

就是synchronized 中sleep

waiting

wait ，等待nitify唤醒

timed waiting，超时自动唤醒

terminated终止状态

