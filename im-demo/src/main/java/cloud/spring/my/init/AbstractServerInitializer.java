package cloud.spring.my.init;

import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;

public abstract class AbstractServerInitializer {

    protected EventLoopGroup bossGroup;
    protected EventLoopGroup workerGroup;

    protected ChannelFuture channelFuture;

    public abstract void init(int nettyPort) throws InterruptedException;

    public void close() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channelFuture.channel().close();
    }

}
