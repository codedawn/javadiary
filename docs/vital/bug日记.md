INFO  com.codedawn.vital.server.command.ServerDefaultCommandHandler - 接收到相同的seq：1-0



同一个账号注销之后，马上登录，由于使用idseqstrategy，会产生相同的seq，服务器会当做同一个消息，不做处理

由此也发现其他问题，如果用户在一个设备登录就注销后，马上在其他设备登录，也会出现相同seq的情况