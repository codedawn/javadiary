## [缓存池](https://duhouan.github.io/Java/#/JavaBasics/1_数据类型?id=缓存池)

Java 基本类型的包装类的大部分都实现了缓存池技术，对应的缓冲池如下：

- Boolean 直接返回 true / false
- Byte/ Short / Integer / Long 创建了数值范围在 [-128，127] 的相应类型的缓存数据
- Character 创建了数值在 [0,127] 的缓存数据

如果超出对应范围仍然会去创建新的对象。



new Integer(123) 与 Integer.valueOf(123) 的区别在于：

- new Integer(123) 每次都会新建一个对象；
- Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。

![image-20210924203855917](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20210924203855917.png)

缓存池是一个integer数组

![image-20210924203933977](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20210924203933977.png)

![image-20210924204131706](C:\Users\11096\AppData\Roaming\Typora\typora-user-images\image-20210924204131706.png)

是一个私有内部