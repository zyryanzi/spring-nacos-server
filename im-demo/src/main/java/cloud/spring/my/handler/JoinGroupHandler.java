package cloud.spring.my.handler;

import cloud.spring.my.entity.vo.Result;
import cloud.spring.my.server.IMServer;
import io.netty.channel.ChannelHandlerContext;

public class JoinGroupHandler {

    public static void execute(ChannelHandlerContext ctx) {

        IMServer.GROUP.add(ctx.channel());

        ctx.channel().writeAndFlush(Result.ok("加入群聊成功！"));

    }

}
