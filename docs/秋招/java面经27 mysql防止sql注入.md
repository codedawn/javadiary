预编译防止sql注入

jdbc preparedstatement进行预编译



预编译之后，然后装入参数，参数就不会进行

[MySQL命令执行过程和存储引擎概述 - 云+社区 - 腾讯云 (tencent.com)](https://cloud.tencent.com/developer/article/1418795)

![image-20211028224338623](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211028224338623.png)

连接-》查询缓存-》语义解析-》查询优化-》结果返回

如外连接转换为内连接、表达式简化、子查询的转为连接、使用索引吧啦吧啦的一堆东西

[mysql查询语句执行顺序 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/51032131)

![image-20211028224653046](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211028224653046.png)