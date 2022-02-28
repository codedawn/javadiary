[Java默认提供的线程池 - robin·张 - 博客园 (cnblogs.com)](https://www.cnblogs.com/amunote/p/10322294.html)

默认线程池

fixedThreadpool

固定线程大小，核心线程和最大线程一样，使用linkedblockingqueue，默认Integer.max_value，无限提交任务可能oom

cacheThreadPool

线程最小为0，但是最大integer.max_value

无限创建线程可能00m

singlethreadpool

只有一个线程，使用linkedblockingqueue，可能oom

schedulethreadpool，然后线程可以无限创建，oom





threadlocalmap key是threadlocal，key是integer等，value是要线程安全的引用，

key是弱引用，gc时没有强引用就回收，但是value还没有回收，所以最好手动remove

每一个线程都有自己的treadlocalmap

每个线程set，get都是对自己的map操作，但是value如果是一个引用，还是有线程安全问题





用于参数传递，因为线程自带threadlocal



可以解决线程安全问题，每个线程保存自己的副本，比如threadlocal get为null，就获取一份



性能，线程管理，可以统计空闲时间、完成的任务数量



## 线程池

1.为什么使用线程池

* 降低创建和销毁的开销

* 提高响应速度，核心线程是一直存活的，来任务就可以马上执行

* 提高线程的可管理性。统一管理线程，可以方便打印日志，可以监控队列

  

2.线程池执行原理

提交任务，如果核心线程数还没有满，就创建核心线程，

否则，如果等待队列还没有满，放入等待队列

否则，线程数是否达到最大，否就创建线程

否则，就是触发拒接策略



corePoolsize:核心线程数，不主动设置一般不会过期

maximumPoolSize：最大线程数

blockingQueue：阻塞队列，take，put阻塞

KeepAliveTime：非核心线程空闲存活时间

timeunit：时间单位

TheadFactory：每次创建线程时，通过调用TheadFactory完成

handler：拒绝策略

abort policy：默认的策略，直接抛出异常rejectexecutionexception

discardpolicy：不处理，直接丢弃

discardoldestpolicy：将等待队列队首的任务丢弃，并执行当前任务

callerrunpolicy：有调用线程处理该任务

3.线程池大小怎么设置？

n是核心数

cpu密集型任务：n+1，尽量减少线程切换

io密集型任务：2*n，频繁阻塞的线程，所以多创建一点

线程，充分利用cpu



4.线程池类型有哪些？适用场景

fixedThreadPool：固定线程数的线程池,但是使用了LinkedBlockingQueue，然后默认容量integer.max_value,21亿左右，有可能oom

SingleThreadExecutor：只有一个线程的线程池，也使用了默认容量的linkedblockingqueue，可能oom

cachedThreadPool：核心线程是0，然后最大线程是integer.max_value,可能会大量创建线程，从而oom

scheduleThreadPool：延迟执行任务的线程池，最大线程数integer.max_value，linkedblockingqueue,默认容量



## 进程和线程

进程都有自己的独立的一块内存空间，一个进程可以有1到n个线程

同一个进程下的线程可以共享进程的内存

#### 线程的生命周期

初始（new）：线程被构建，还没有调用start（）

运行（runnable）：包括操作系统的就绪和运行两种状态

阻塞（blocked）：进入获取锁队列，就是获取锁被挂起，释放cpu

等待（waiting）：进入该状态的线程需要等待其他线程做出一些特定动作，一般是wait（）

超时等待（time_waiting）:sleep() wait(long)等，可以在指定时间后自行返回

终止（terminated）：表示该线程已经执行完毕

#### 创建线程的方式

* 通过继承Thread类创建线程

* 通过实现runnable接口的来创建多线程

  可以避免单继承的限制，然后可以多个runnable实例

* 实现callable接口，可以有返回值，异常

* 使用executor创建线程池

#### 线程死锁

```
class deadLock{
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o1){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2){
                        System.out.println("thread1");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o2){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1){
                        System.out.println("thread2");
                    }
                }
            }
        }).start();
    }
}
```

解决方法：

一次性申请资源，要么全部拿到，要么一点资源不占有

超时申请，然后超过一定时间申请不到，把占有的资源释放

有序申请资源，申请资源是根据资源的编号去申请，没有获得1，就不能申请2



aqs原理

使用一个volatile的int类型的成员变量state来	表示同步状态，然后使用cas修改同步状态的值。state=0说明，没有线程占用，线程可以占用。然后state加1，state不为0说明有线程占用，就加入等待队列，一个fifo双向队列，双向链表



#### Synchronized

原子性，可见性



乐观锁