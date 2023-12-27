package cloud.spring.my.init;

import cloud.spring.my.handler.EpollServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;

public class EpollServerInitializer extends AbstractServerInitializer {

    public EpollServerInitializer(EpollEventLoopGroup bossGroup, EpollEventLoopGroup workerGroup) {
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    @Override
    public void init(int nettyPort) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(EpollServerSocketChannel.class)
                .childHandler(new EpollServerHandler());

        this.channelFuture = serverBootstrap.bind(nettyPort).sync();
        channelFuture.channel().closeFuture().sync();
    }

}
