package cloud.spring.my.handler;

import cloud.spring.my.entity.Command;
import cloud.spring.my.entity.vo.Result;
import cloud.spring.my.server.IMServer;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;

public class ConnectionHandler {

    public static void execute(ChannelHandlerContext ctx, Command command) {

        if (IMServer.USERS.containsKey(command.getNick())) {
            ctx.channel().writeAndFlush(Result.error("昵称已存在，请更换登录"));
            ctx.channel().disconnect();
            return;
        }

        IMServer.USERS.put(command.getNick(), ctx.channel());

        ctx.channel().writeAndFlush(Result.ok("与服务端建立连接成功"));
        ctx.channel().writeAndFlush(Result.ok(JSON.toJSONString(IMServer.USERS.keySet())));
    }

}
