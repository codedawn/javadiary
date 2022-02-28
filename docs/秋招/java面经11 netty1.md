selector java nio能够非阻塞进行io操作的关键

通过selector，一个线程可以监听多个 channel的io事件，当我们像selector中注册了channel后

selector内部的可以为我们不断地查询（select）这些注册的channel是否有已就绪的事件，

例如可读，可写，网络连接事件，通过这种机制，我们就可以实现io多路复用



channel类型：

filechannel

datagramchannel

socketchannel

serversocketchannel





在netty中，channel是socket的抽象，提供了io操作和连接的状态

netty添加handler，是在channle initializer中的chanelregister中调用了initchannel

我们会重写initChannel，





bootstrap会调用connect，然后connect0中实例化niosocketchannel

serverbootstrap会调用bind，然后bind0会实例化nioserverchannel

nioserversocketchannel会和bossgroup中的一个eventloop关联起来



```
@Override
    void init(Channel channel) {
        setChannelOptions(channel, newOptionsArray(), logger);
        setAttributes(channel, attrs0().entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY));

        ChannelPipeline p = channel.pipeline();

        final EventLoopGroup currentChildGroup = childGroup;
        final ChannelHandler currentChildHandler = childHandler;
        final Entry<ChannelOption<?>, Object>[] currentChildOptions;
        synchronized (childOptions) {
            currentChildOptions = childOptions.entrySet().toArray(EMPTY_OPTION_ARRAY);
        }
        final Entry<AttributeKey<?>, Object>[] currentChildAttrs = childAttrs.entrySet().toArray(EMPTY_ATTRIBUTE_ARRAY);

        p.addLast(new ChannelInitializer<Channel>() {
            @Override
            public void initChannel(final Channel ch) {
                final ChannelPipeline pipeline = ch.pipeline();
                ChannelHandler handler = config.handler();
                if (handler != null) {
                    pipeline.addLast(handler);
                }

                ch.eventLoop().execute(new Runnable() {
                    @Override
                    public void run() {
                        pipeline.addLast(new ServerBootstrapAcceptor(
                                ch, currentChildGroup, currentChildHandler, currentChildOptions, currentChildAttrs));
                    }
                });
            }
        });
    }
```

nioserversocket 重写了init方法，添加了ServerBootstrapAcceptor，里面的channelread方法实现对新连接进来的channel进行注册

```
 @Override
        @SuppressWarnings("unchecked")
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            final Channel child = (Channel) msg;

            child.pipeline().addLast(childHandler);

            setChannelOptions(child, childOptions, logger);
            setAttributes(child, childAttrs);

            try {
                childGroup.register(child).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (!future.isSuccess()) {
                            forceClose(child, future.cause());
                        }
                    }
                });
            } catch (Throwable t) {
                forceClose(child, t);
            }
        }
```

