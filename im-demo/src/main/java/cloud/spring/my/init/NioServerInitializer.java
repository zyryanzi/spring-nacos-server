package cloud.spring.my.init;

import cloud.spring.my.handler.NioServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NioServerInitializer extends AbstractServerInitializer {

    public NioServerInitializer(NioEventLoopGroup bossGroup, NioEventLoopGroup workerGroup) {
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    @Override
    public void init(int nettyPort) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NioServerHandler());

        this.channelFuture = serverBootstrap.bind(nettyPort).sync();
        this.channelFuture.channel().closeFuture().sync();
    }

}
