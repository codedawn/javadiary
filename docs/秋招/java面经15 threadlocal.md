ThreadLocal



```
 /* ThreadLocal values pertaining to this thread. This map is maintained
     * by the ThreadLocal class. */
    ThreadLocal.ThreadLocalMap threadLocals = null;
```

每个线程有自己的ThreadlocalMap  entry继承了弱引用，gc就回收

![image-20211025161305220](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211025161305220.png)

public void set(T value){

​	获取当前线程

  getmap（当前线程）

​	如果不是map不是null就

​	map.set

  否则

  createMap

}

public T get（）{

通过map，getvalue返回

否则setinitialvalue（）；

}

