1.**请教可以使用MQ消息队列中间件做即时通讯系统吗？** [请教可以使用MQ消息队列中间件做即时通讯系统吗？-IM开发/专项技术区 - 即时通讯开发者社区! (52im.net)](http://www.52im.net/thread-699-1-1.html) 

阅读（55292） | 评论（23）[收藏3](http://www.52im.net/home.php?mod=spacecp&ac=favorite&type=thread&id=699&formhash=0d8b9a77) [淘帖1](http://www.52im.net/forum.php?mod=collection&action=edit&op=addthread&tid=699) [赞](http://www.52im.net/forum.php?mod=misc&action=recommend&do=add&tid=699&hash=0d8b9a77)

[denghui1010](http://www.52im.net/space-uid-1150.html) [Lv.1](http://www.52im.net/home.php?mod=spacecp&ac=usergroup&gid=10) *4 年前* | |[只看大图](http://www.52im.net/forum.php?mod=viewthread&tid=699&from=album)

ActiveMQ类似的消息队列 可以使用发布-订阅或P2P模式, 可以用于开发即时通讯系统吗, 感觉好像没什么应用, 会有什么缺陷吗?

 **单纯从技术上看，IM系统的所有数据路径无非就是3种情况：** 1）消息从A客户端 -> 经由服务器->再转发给B客户端：即C to S to C； 2）消息从A客户端直发服务端：即C to S； 2）消息从服务器直发A客户端：即S to C。

**而消息中间件里的订阅模式：** 即生产者推送消息到MQ、再由消费者从MQ读取，理论上是完全可以实现上面所说的3种消息传递路径的，如果要实现IM消息的生产和消费，基本上就是一个用户对应一个队列，而大量用户存在的情况下就是大量的队列产生，而每个队列的消息流转其实很少，试想一个人聊天时能发出多少消息？。

**但存在一个问题的：** 本身MQ被设计来的目的是处理大量的消息的，也即是通常的应用场景下，不会有多少队列存在，但每个队列每秒都要满负荷处理大量的消息，而假设你来开发MQ中间的话，你也能想到：你最大的优化目标是将每个队列的消息处理吞吐做到最大化，而不应该把处理大量的队列连接、断开、重连这些事情做为MQ存在的意义。

**综上：** 我的个人意见是，没有行不行，而是合不合适，所以就看你怎么选择了。有的人因为IM系统很小，为了省事或偷懒，也真就是用MQ来实现的，但除非你老板懂技术，不然谁管你。 


 2.**[已回复] MobileIMSDK在集群下怎么实现收发消息，首选MQ还是RPC？** [[已回复\] MobileIMSDK在集群下怎么实现收发消息，首选MQ还是RPC？-MobileIMSDK/解决方案区 - 即时通讯开发者社区! (52im.net)](http://www.52im.net/thread-3517-1-1.html) 

阅读（1706） | 评论（5）[收藏](http://www.52im.net/home.php?mod=spacecp&ac=favorite&type=thread&id=3517&formhash=0d8b9a77) [淘帖1](http://www.52im.net/forum.php?mod=collection&action=edit&op=addthread&tid=3517) [赞](http://www.52im.net/forum.php?mod=misc&action=recommend&do=add&tid=3517&hash=0d8b9a77)

[461611894](http://www.52im.net/space-uid-22271.html) [Lv.2](http://www.52im.net/home.php?mod=spacecp&ac=usergroup&gid=11) |



 对于IM这种对实时性、时序性等等要求很高的场景，首选你写的第2个方案 