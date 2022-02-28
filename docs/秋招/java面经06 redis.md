redis数据类型：

1、string

常用命令：set，get，strlen，exists，decr，incr，setex

应用场景：

计数器

一般常用在需要需要计数的场景，比如用户的访问次数，热点文章的点赞转发数量

set key value

get key

exists key

strlen key

del key

get key

mset key1 value1 key2 value2

mget key1 key2





计数器

set number 1

incr number

get number

decr number

get number



expire key 60

1

setex key 60 value

ttl key

2、list



双向链表，但是更多的内存花销



常用的命令：

rpush，lpop，lpush，rpop，lrange，llen

rpush mylist value1 

rpush mylist value2 value3

lpop mylist

lrange mylist 0 1

lrange mylist 0 -1





rpush myList value1 value2 value3

rpop myList

rpush mylist value1 value2 value3

lrange mylist 0 1

lrange list 0 -1

llen mylist

应用场景：队列



3.hash

常用命令：hset、hmset、hexists、hget、hgetall、hkeys、hvals

应用场景：系统存储对象

hmset userInfoKey name “ltm” description “dev” age “24”

hexists userInfoKey name

hget userInfoKey age

hgetall userInfokey

hkeys userInfoKey

hvals userInfoKey

hset userinfoKey name "kkk"

hget userInfoKey name





4.set

常用命令：sadd，spop，smembers，sismember，scard

sinterstore，sunion



应用场景：去重，还有数据源需要求交集和并集等

sadd myset value1 value2

sadd myset value1

smembers mySet

scard set

sismember myset value1

sadd myset2 value2 value3

sinterstore myset3 myset myset2



smember myset3



5.sorted set

常用命令：zadd，zcard，zscore，zrange，zrevrange，zrem

跳表

应用场景：需要根据某个权重进行排序，比如礼物榜单、排行榜

zadd mySet 3.0 value1

zrevrange myZset 0 1



特殊数据类型：

6.geospatial地理位置

7.hyperloglog基数统计

8.bitmap

getbit key offset

bitcount key

应用场景：

统计用户活跃数，登录未登录，可以当成两种状态的bitmap





redis为什么不使用多线程：

1.单线程编程容易更容易维护

2.redis的性能瓶颈不在于cpu,主要是内存和网络

3.多线程就会存在死锁、线程上下文切换问题





过期的数据的删除：

惰性删除：只在取key的时候检查是否删除，但是有可能导致过多的key没有删除

定期删除：过一段时间抽取一批key删除

redis 采用定期删除+惰性删除

redis 内存淘汰机制

lru 最近最久未使用

ttl 从设置过期时间的数据集中挑选即将过期的删除

random 从设置过期时间的数据集中随机删除



allkeys-lru 当内存不足以容纳新写入的数据，在键空间中移除最近最少使用的数据

redis 持久化控制

snapshot快照持久化

append only file aof持久化





redis 事务 可以让一批次命令不被中断执行（多端连接命令可能会穿插，所以要事务），但是命令引起的异常不会回滚，

原子性

隔离性

持久性

一致性

redis 不支持roll back回滚，所以不支持原子性



1.缓存穿透：

就是大量key不存在redis中（甚至这个key也不存在数据库中，黑客的攻击手段之一），导致大量的请求落到数据库上，数据库性能受到影响，甚至崩溃

1）解决方法，做好一些基本的数据校验

2）缓存无效key，减少落在数据库上的请求

3）使用布隆过滤器，筛选不合法key

2.缓存雪崩

就是缓存在同一时间大量key失效，导致一瞬间大量请求落到数据库

解决方法：

1.采用redis集群，避免redis出现问题直接导致整个缓存服务不可用

2.限流，削峰填谷，避免短时间大量请求

针对热点缓存的话可以不设置过期时间

或者是设置不同的过期时间，避免同一时间大量热点数据过期



保证缓存和数据库数据的一致性，

cache aside pattern 旁路缓存模式

[58一面：Redis数据更新，是先更新数据库还是先更新缓存？-技术圈 (proginn.com)](https://jishuin.proginn.com/p/763bfbd65e4e)

先更新db，再删除缓存，不能更新缓存，多线程下可能产生脏数据





sorted set 底层

跳表

![image-20211022213336064](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211022213336064.png)

建立多级索引

每一级索引是下一级的1/2

查找是o（logn）

插入是o（logn）

插入时然后随机0或1决定更新索引，比如抛硬币抛出1，就更新上一层，继续抛，直到0

空间复杂度 o（n）



惰性删除：只有当访问一个key时，才会判断该key是否已过期，过期则清除

对cpu友好，对内存不友好

定期删除：每隔一定的时间，会扫描一定数量的数据库的expire字典中一定数量的key

并清除其中已过期的key

redis同时使用了惰性过期和定期过期策略



redis 单线程，io多路复用，同时监听多个socket，根据不同的事件类型，

选择不同的处理器



纯内存操作，基于非阻塞的io多路复用机制

单线程避免多线程频繁的上下文切换带来的性能问题

```
UNLINK 进行大key删除，后台线程删除
```

acid，

原子性

隔离性

一致性

持久性



1.事务开始

multi

2.命令入队

3.事务执行

exec



语法有错回滚，逻辑有错不回滚，为了性能的取舍



redis 集群方案

主从

哨兵模式基于主从

sentinel，哨兵功能：

集群监控：负责监控redis master 和slave进程是否正常工作

消息通知：如果某个redis实例有故障，就会通知管理员

故障转移：如果master挂掉 ，会自动转移到slave node上

配置中心：如果故障转移发生了，通过client客户端新的master地址





集群监控

消息通知

故障转移

配置中心



redis sharing 客户端分片，通过hash，把key哈希到某个节点上

全量复制

主节点通过bgsave命令fork子进程进行rdb持久化，非常耗内存

然后把rdb（redis database）文件发送给从节点，对带宽影响大

从节点清空数据，加载rdb文件

部分复制

有一个缓冲区，fifo队列

offset



redis应用场景

1.缓存

2.排行榜

3.计数器

浏览量、播放量

4.分布式会话

5.分布式锁

1.过期时间需要提前设置，可能业务执行还没有完成就过期了

2.redis服务器时钟漂移

就是时间不一致，然后导致key过期，但是业务还没有完成

3.单点问题，

redlock：

5个节点部署，

获取当前时间戳，设定key过期时间，尝试获取大部分redis实例的锁

然后再次获取时间戳计算两次时间差，没有过期说明获取成功

释放锁：所有节点申请释放一次

容错性，避免死锁，互斥



redis 高性能，因为在内存中，操作快

redis高并发，线程模型好，是单线程模型，io多路复用支持多个客户端连接（socket）



计数器，排行榜、缓存、分布式锁



数据类型

string

list

hash

set

sortedset

bitmap

geo

hyperlog



redis 4.0后引入多线程，但是是在删除key的时候使用

大key删除，因为redis是单线程执行命令，删除key可能阻塞

命令unlink，flushall async



一致性hash

16384 slot

一个key进行hash得到哈希值，然后进行16384取模，得到slot编号



#### Redis优缺点

1.基于内存操作，内存操作读写操作非常快

2.redis单线程，io多路复用，一个线程可以处理多个socket。但是大key删除会另起一个线程

3.支持多种数据类型，string，list，map，set，sorted_set

4.支持持久化，rdb，aof，一个是快照，一个写命令记录，rdb文件小，恢复快，但是会丢失两次快照之间的数据，aof，文件体积大，恢复慢，可读性好，丢失数据少

5.k-v查询是o（1）

#### Redis为什么这么快

基于内存：内存读写速度快

单线程实现：redis使用单线程处理请求，避免了多个线程间的切换和锁资源争用的开销

io多路复用模型：redis采用io多路复用技术





因为redis操作是在内存中，所以处理速度非常快，cpu不会redis的瓶颈

瓶颈是网络延迟，也就是网络io

单线程不需要考虑线程安全问题

单线程使用io多路复用技术也能处理多个socket



Redis 4.0后引入多线程

进行大key删除，unlink等非阻塞操作



Redis 内存淘汰策略

volatile-lru 在设置了过期时间的key中挑选最近最少使用的数据进行淘汰

volatile-ttl 在设置了过期时间的key中挑选即将过期的数据进行淘汰

volatile-random 在设置了过期时间的key中随机挑选数据进行淘汰

allkey-lru 在所有key中挑选最近最少使用的数据淘汰

allkey-random 在所有key中随机挑选数据淘汰





volatile-lfu 在设置了过期时间的key中挑选最少被使用的数据淘汰

allkeys-lfu 在所有的key中挑选挑选最少被使用的数据淘汰



#### 缓存问题

##### 缓存雪崩

同一时间内大量key失效，或者是缓存服务器宕机，导致对应的请求直接到达数据库，然后数据库根本没有办法承受这么多请求

##### 解决方案

* 可以合理设置缓存过期时间，比如随机设置过期时间，
* 热点数据缓存设置永不失效

缓存服务器宕机的情况：

* 可以部署redis集群
* 限流，避免同时处理大量请求





##### 缓存穿透

缓存穿透是指某个不存在的数据，会穿过缓存，请求到数据库，比如请求某个不存在的id



##### 解决方案

* 参数校验

  将一些不合法的参数请求直接抛异常返回给客户端，比如手机号或者邮箱

* 缓存无效键

  如果一个频繁查询的数据不存在，可以设置过期缓存，但是value为空

* 使用布隆过滤器对这类请求进行过滤

   布隆过滤器，可以使用极小的空间开销解决海量数据判重问题。有一定可能误判，通过布隆过滤器并不代表就存在

  原理是bitset，在存储是使用k个哈希函数，得到k个位置，然后把k个位置的对应的为设置为1

  查找的时候，只要查找k个位置上是否都为1



