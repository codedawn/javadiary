1.redis是基于内存的，操作非常快的相较于磁盘

2.redis是k-v的格式，o（1），mysql b+树是log（n）

3.mysql需要加载到内存，redis本来就在内存

4.redis 单线程，io多路复用，避免线程切换，避免了线程安全问题