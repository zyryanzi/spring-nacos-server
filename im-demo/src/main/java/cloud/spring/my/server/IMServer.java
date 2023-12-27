package cloud.spring.my.server;

import cloud.spring.my.init.AbstractServerInitializer;
import cloud.spring.my.init.EpollServerInitializer;
import cloud.spring.my.init.NioServerInitializer;
import io.netty.channel.Channel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IMServer {

    @Value("${server.netty.port}")
    private Integer nettyPort;

    /**
     * 用户列表
     */
    public static final Map<String, Channel> USERS = new ConcurrentHashMap<>(1024);

    /**
     * 群组列表
     */
    public static final ChannelGroup GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @PostConstruct
    public void start() {
        AbstractServerInitializer nettyServerInit;
        if (Epoll.isAvailable()) {
            nettyServerInit = new EpollServerInitializer(new EpollEventLoopGroup(1), new EpollEventLoopGroup());
        } else {
            nettyServerInit = new NioServerInitializer(new NioEventLoopGroup(), new NioEventLoopGroup());
        }

        try {
            nettyServerInit.init(nettyPort);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 资源优雅释放
            nettyServerInit.close();
        }

    }


}
