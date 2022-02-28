[java -- 迭代器并发修改异常 | RQ BLOG (rqsir.github.io)](https://rqsir.github.io/2019/05/14/java-迭代器并发修改异常/)

iterator遍历集合时，实际上是对集合副本的操作，这期间，如果

iterator发现和原集合数据不一样了，就会报concurrentmodificationexception，判断大小之类的

ConcurrentHashMap允许一边更新、一边遍历，也就是说在Iterator对象遍历的时候，ConcurrentHashMap也可以进行remove,put操作，且遍历的数据会随着remove,put操作产出变化，所以希望遍历到当前全部数据的话，要么以ConcurrentHashMap变量为锁进行同步(synchronized该变量)，要么使用CopiedIterator包装iterator，使其拷贝当前集合的全部数据，但是这样生成的iterator不可以进行remove操作。

 