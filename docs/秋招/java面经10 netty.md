1.netty是什么？

netty是基于nio的一个网络通讯框架,支持多种协议,ftp,http,websocket

简单来说,使用netty可以快速开发高性能的服务器,

ftp服务器,http服务器,rpc框架,用作游戏服务器通讯



用到netty的开源项目,dubbo grpc,vertx



2.为什么要用netty

netty有统一的api,支持多种传输协议,支持阻塞和非阻塞

使用高性能的多线程reactor模型

自带很多组件,比如自带解决tcp粘包/拆包问题的编解码器

做了很多优化,经过大型项目验证,性能有保证,有很多开源项目使用

安全性好,支持ssl/tls

3.netty应用场景

1.做rpc

2.或者是http服务器,

3.即时通讯

4.游戏服务器

4.netty核心组件有哪些?有什么用

 1.channel是对网络操作的程序,常用类nioserversocketchannel,niosocketchannel

2.eventloop处理连接方式的事件，write，read等（io操作）

channel注册到eventloop上

3.channelfuture

4.channelhandler和channelpipeline

channelhandler是消息的具体处理器。他负责处理读写操作、客户端连接

channelpipeline是一个channelhandler，负责把channelhandler组织起来，责任链模式

5.eventloopgroup

eventloopgroup是一个线程池，包含1-n个eventloop，eventloop和线程是一对一关系，

boss eventloopgroup负责接收连接，然后连接会绑定到workeventloopgroup中的某一个eventloop，实现线程安全



6.bootstrap和serverbootstrap

bootstrap是客户端的启动类

serverbootstrap是服务端的启动类

7.nioeventloopgroup默认的构造函数会起 可用核心数*2个线程

8.reactor模型

reactor模型基于事件驱动，采用多路复用将事件分发给相应的handler处理，非常适合处理海量io的场景



netty的是多线程的reactor模型

netty的线程池可以重用，可以启动多个serverbootstrap，重用group

[websocket - Netty 4.0 multi port with difference protocol each port - Stack Overflow](https://stackoverflow.com/questions/24571339/netty-4-0-multi-port-with-difference-protocol-each-port)

```
EventLoopGroup bossGroup = new NioEventLoopGroup(numBossThreads);
EventLoopGroup workerGroup = new NioEventLoopGroup(numWorkerThreads);

ServerBootstrap sb1 = new ServerBootstrap();
sb1.group(bossGroup, workerGroup);
...
sb1.bind();

ServerBootstrap sb2 = new ServerBootstrap();
sb2.group(bossGroup, workerGroup);
...
sb2.bind();

ServerBootstrap sb3 = new ServerBootstrap();
sb3.group(bossGroup, workerGroup);
...
sb3.bind();
```



两个线程池：

bossgroup：接收连接

workgroup：负责具体的处理，交由对应的handler处理

[面试官：Netty的线程模型可不只是主从多Reactor这么简单 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/345001344)

![image-20211011202651797](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211011202651797.png)

**二进制变量的声明以0b为前缀；**
**八进制变量的声明以0为前缀；**
**十六进制变量的声明以0x为前缀。**

9.什么是tcp粘包和拆包

就是你发送连续多个字符串时，由于tcp是流式的，可能出现字符串接收时多个字符串拼在一起，或者一个字符串被拆开

**LengthFieldBasedFrameDecoder** 协议头添加消息长度 

LengthFieldPrepender编码

protobuf的实现

```
ProtobufVarint32LengthFieldPrepender varint32编码长度值，然后放在消息头
ProtobufVarint32FrameDecoder
```

10.netty长连接、心跳机制

短连接就是建立连接之后，读完消息就断开，下一次发消息重新连接，建立一次连接非常耗费资源，不推荐

长连接就是建立连接之后，维护着连接状态，完成一次读写之后不会断开，不需要频繁建立连接，省资源。

11.为什么需要心跳

当客户端和服务器建立长连接之后，如果出现网络异常，客户端和服务端又没有交互，另一端可能没有感知连接已经断了，所以定期发生心跳包，作为一种交互，如果服务器一定时间内没有收到客户端的心跳，就可以判断连接已经断开。

虽然tcp的长连接也有心跳，但是不能自定义配置，不灵活，默认是两小时

netty提供了idlestatehandler，ReadTimeoutHandler

IdleStateHandler源码分析
IdleStateHandler构造器

readerIdleTime读空闲超时时间设定，如果channelRead()方法超过readerIdleTime时间未被调用则会触发超时事件调用userEventTrigger()方法；

writerIdleTime写空闲超时时间设定，如果write()方法超过writerIdleTime时间未被调用则会触发超时事件调用userEventTrigger()方法；

allIdleTime所有类型的空闲超时时间设定，包括读空闲和写空闲；

unit时间单位，包括时分秒等；

12.netty零拷贝

netty 的bytebuf使用堆外内存（direct memory）

如果使用堆内存，需要与堆外内存进行一次拷贝





bio 发起io请求会阻塞

nio 同步非阻塞，多路复用会轮询这些缓冲区，

