1.

private static Single instance;

看这一步：

instance=new Single();

17: new #3 // class com/xxm/singleton/Singleton

创建一个Single实例，然后赋默认值，比如boolean是false，int是0，引用类型是null

20: dup

21: invokespecial #4 // Method ““:()V

调用init()方法，初始化instance

24: putstatic #2 // Field instance:Lcom/xxm/singleton/Singleton;

把对象赋值给instance

如果指令重排21和24可能倒过来，调用instance就会得到一个没有初始化的对象

