1. Java的ConcurrentHashMap介绍一下

   ​	concurrentHahmap和hashmap底层都是数组加链表（阈值是8，大于等于8转红黑树），大小都是2的多少次方

   ​	原因：这个算法实际就是取模，hash%length，计算机中直接求余效率不如移位运算，源码中做了优化hash&(length-1)，
    hash%length==hash&(length-1)的前提是length是2的n次方；

   如果是2的n次方，减1之后除了最高位全是1，然后和hash值&，就得到取模值

   

   

   hashmap在并发条件下主要有以下几个问题：

   1.死循环，1.7之前链表使用头插法，扩容时需要转移元素，然后链表会翻转，在多线程情况下

   可能发生死循环。1.8使用了尾插法，解决了死循环问题。

   2.数据丢失

   插入数据时没有用同步操作或者cas，插入元素可能会出现覆盖的情况

   ![image-20211009142012390](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211009142012390.png)

   hashtable问题：

   1.所有方法都是加synchronized重锁，效率太低

   乐观锁：先假设每次操作不会出现冲突，如果出现冲突再尝试

   cas（compare and swap）是一种乐观锁的实现

   底层是unsafe.compareAndSwapInt(this,offset,expect,update)

concurrenthashmap已经不用分段锁了，然后是node数组+链表+红黑树的组合

![image-20211009143622527](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211009143622527.png)

一个大循环自旋，如果是第一次插入就直接cas，如果已经有值，就synchronized锁住头节点，然后

找有没有hash值和equals都相等的，有就覆盖value，否则尾插法，最后会判断binCount是否大于等于8

是否需要转红黑树

sizectl：

- **当为负数时：-1代表正在初始化，-N代表有N-1个线程正在进行扩容**
- **当为0时（默认值）：代表table数组还没有被初始化**
- **当为正数时：表示初始化或者下一次进行扩容的大小**



volatile：

保证变量的可见性，线程每次读取这个变量都会到主存中读取，而不是线程自己的工作空间

禁止指令重排

volatile 不能保证变量的原子性

get方法中tabAt使用getReferenceVolatile，使用volatile加载读取元素，因为局部变量没办法使用关键字volatile





不小于一个数的最小2的幂次方

static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}

不是0的情况下

n |= n >>> 1;

00000000 00010000 00000000 00000000

00000000 00001000 00000000 00000000

结果是：

00000000 00011000 00000000 00000000

肯定有两个1所以可以移动2位，就是

​    n |= n >>> 2;

以此类推，到n |= n >>> 16;后

int是32位就全覆盖了

扩容到原来两倍
