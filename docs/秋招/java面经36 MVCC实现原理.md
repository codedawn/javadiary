[(18条消息) MySQL中MVCC的正确打开方式（源码佐证）_Waves___的博客-CSDN博客](https://blog.csdn.net/Waves___/article/details/105295060#1.2、Read View 结构)



mvcc 的实现原理主要依赖记录中的三个隐藏字段，undolog readview

隐藏字段

1.DB_TRX_ID

6字节，最近修改事务id，记录创建这条数据或者最后一次修改这条数据的事务id

DB_ROLL_PTR

7字节，回滚指针，指向上一版本，用于配合undolog，也可能为null，mysql delete是逻辑删除

记录头有delete_mask,

[(18条消息) MySQL—记录头信息的秘密_多一天，多一点-CSDN博客](https://blog.csdn.net/xioayu96/article/details/107857452)

6字节，隐藏的主键，如果没有显式设主键，innodb会自动生成



undolog

undolog 回滚日志，旧版本的数据就在undolog中，insert操作就保存一个delete操作

read view

生成一个当前事务的快照数据

trx_ids:记录正活跃的事务列表

low limit id，下一个事务id

up limit id 活跃列表中最小事务的id

因为trx_ids是逆序的，所以最后一个最小

![image-20211031215600350](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211031215600350.png)