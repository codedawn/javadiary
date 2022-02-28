强引用：对应gcroot的对象

Object o=new Object();

o=null;



软引用：内存不足时，软引用会被回收，如果回收了软引用还是内存不足，就是oom

可以用于缓存，图片缓存，网页缓存

SoftReference<Integer> s=new SoftReference<>(new Integer(1));



弱引用：只要gc就回收

WeakReference<Integer> w=new WeakReference<>(new Integer(1));



虚引用：虚引用phantomReference，get返回null，获取不了和ReferenceQueue结合使用，

如果要被回收了，就会加入ReferenceQueue，通过监听referencequeue判断对象是否会回收

