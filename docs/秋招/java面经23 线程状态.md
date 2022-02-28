

[(18条消息) Java线程的6种状态及切换(透彻讲解)_潘建南的博客-CSDN博客_线程状态](https://blog.csdn.net/pange1991/article/details/53860651)

![线程状态图](https://img-blog.csdnimg.cn/20181120173640764.jpeg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3BhbmdlMTk5MQ==,size_16,color_FFFFFF,t_70)

ready

running，ready获取时间片

blocked，申请synchronized锁，没拿到

waiting 调用wait

time wait sleep wait设置超时时间

终止状态，run方法执行完，或者是main方法执行完

![image-20211028190846634](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20211028190846634.png)