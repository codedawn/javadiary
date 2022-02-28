1.synchronized是不是可重入的？是不是公平锁？如何实现公平锁？

公平锁：线程要获取锁时会加入队列排队，队列头的线程会得到锁

缺点：效率低，因为使用线程都得排到第一个线程才能有锁，第一个线程外其他全部会阻塞

![线程状态图](https://segmentfault.com/img/remote/1460000023194699)

初始化，就绪，运行，等待，超时等待，阻塞



非公平锁：线程申请锁时会先去尝试获取锁，如果不成功才加入等待队列

缺点：有可能出现线程饥饿，一直获取不到锁

synchronized和reentrantlock（默认是非公平锁）都是非公平锁

可重入锁：一个线程进入了一个需要锁的方法，然后这个方法又调用了另一个方法，这另一个方法需要同一把锁，则这个线程可以直接进入，不需要重新获取锁

手写自旋锁：

```java
package com.codedawn;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void getLock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)) {
//            System.out.println(thread.getName()+"trying to get lock");
        }
        System.out.println(thread.getName()+"got the lock");

    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLock.getLock();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLock.getLock();
            }
        }).start();
    }
}
```