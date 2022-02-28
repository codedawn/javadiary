类加载子系统，加载class文件

类加载过程：加载-》验证-》准备-》解析-》初始化

clinit，是javac编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来

类加载器：

bootstrap class loader

extension class loader

application class loader



用户类默认使用application class loader





bootstrap classloader 用来加载java的核心库

出于安全考虑，只加载java、javax、sun路径下的类

extension class loader

加载特定目录下的类

application class loader



双亲委派机制

如果一个类加载器收到了类加载请求，它并不会直接去加载，

而是先把这个请求委派给上一级的加载器

依次递推，直到最顶层的bootstrap class loader

如果上一级可以完成加载，就成功返回，如果上一层无法加载，

子加载器才尝试去加载



沙箱安全机制

例子：String 类

自己定义 java.lang.String类，但是还是会加载jdk自带的类

例子：可以反向委派，比如需要用到一些spi接口，

比如spi实现类jdbc

引导类会委派application class loader加载





双亲委派机制优势：

1.避免类的重复加载

2.保护程序安全，防止核心api被随意篡改





如何判断两个class相同：

1.完全限定类名必须相同

2.加载这个类的classLoader必须相同





运行时数据区与内存

native method stack 本地方法栈

program counter register 程序计数器

jvm stacks（里面一个个栈，栈里面一个个栈帧）



heap 堆

新生代：eden survivor0 survivor1

老年代 ：old老年代

metaspace 元空间

metaspace 元数据区

常量池 方法元信息  class类元信息

code cache





线程的内存空间

线程独有：独立包括程序计数器、栈、本地方法栈

线程间共享：堆、堆外内存



pc register pc寄存器

寄存器存储指令相关的现场信息



每个线程都有自己的程序计数器，是线程私有的，生命周期当然和线程保持一致

每个线程任何时间都只有一个线程在执行，也就是当前方法，pc寄存器会存储当前方法执行

的jvm指令地址，如果native方法，就是undefined



通过pc寄存器可以完成分支、循环、跳转、异常处理、线程恢复

字节码解释器工作时，通过改变这个计数器的值来选取下一跳需要执行的指令

不会oom



cpu需要不断的切换线程，切换回来需要知道从哪里继续执行

jvm的字节码解释器需要通过pc寄存器明确下一条执行的字节码指令是什么



虚拟机栈

一个线程对应虚拟机栈，生命周期和线程一致

保存方法的局部变量、部分结果，并参与方法的调用和返回

操作：入栈和出栈

栈不需要gc，可能stackoverflow和outofmemoryexception



每个栈帧存着：

局部变量表

操作数栈

动态链接

方法返回地址

一些附加信息

局部变量和类变量初始化不同的是，局部变量表不存在系统初始化的过程，所以必须人为初始化

，否则无法使用

数组实现的操作数栈



栈顶缓存技术

将栈顶元素全部缓存在物理cpu的寄存器中，以此降低对内存的读、写次数，提高执行次数



动态语言和静态语言

区别在于对类型的检查是在编译器还是运行期

在编译期就是静态语言

在运行期就是动态语言



一个进程只有一个jvm实例，一个进程可以有n个线程，所以它们是共享同一堆空间的

为逃逸的对象可以分配内存在栈中





单个线程时，不断递归（或者其他申请栈内存的操作），就会stackoverflow

不断地创建线程，stack内存不足，就会oom

不断的new对象，堆内存用完，就会oom

vital测试时，短时间消息太多，就会oom



方法区-》元空间

堆

程序计数器

java栈

本地方法栈



没有使用引用计数，引用计数不能

解决循环依赖问题

使用可达性分析算法

gc roots

一般是方法中的参数和局部变量

类的引用类型静态变量

常量池中的引用

![image-20211026105013270](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211026105013270.png)

虚方法表是方法真正的入口

当前没有就找父类，如果是接口的方法，没有重写就调用，abstractmethoderor

eden survivor0 survivor1 新生代

old gen 老年代



eden区满触发ygc/minorgc 

s0,s1满不会触发，会直接晋升old gen



old gen满就触发full gc

![image-20211026124444736](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211026124444736.png)

cms gc 使用标记-清除

标记gcroots关联的对象

初始标记（stw）-》并发标记-》重新标记（stw）-》并发清除

初始标记（stw）-》并发标记-》重新标记（stw）-》并发清理-》重置线程

缺点：内存碎片，占用线程，cpu资源，无法处理浮动垃圾

空闲链表



因为是并发执行，不能整理内存





g1 优先回收价值比较大的

g1大内存，多处理器

低gc延迟，有大堆内存



jvm虚拟机栈，本地方法栈，元空间，堆，pc寄存器



栈帧：局部变量表，操作数栈，方法返回值，动态链接



归并和基数和改良的快排和冒泡是稳定的排序

缓存一致性，先更新数据库，后删除缓存







栈帧：

操作数栈

局部变量表

动态链接

方法返回值
