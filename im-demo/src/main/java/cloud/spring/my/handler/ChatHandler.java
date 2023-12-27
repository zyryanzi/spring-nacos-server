package cloud.spring.my.handler;

import cloud.spring.my.entity.ChatMessage;
import cloud.spring.my.entity.vo.Result;
import cloud.spring.my.enums.MessageType;
import cloud.spring.my.server.IMServer;
import com.alibaba.fastjson2.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ChatHandler {

    public static void execute(ChannelHandlerContext ctx, TextWebSocketFrame frame) {
        try {
            ChatMessage message = JSON.parseObject(frame.text(), ChatMessage.class);
            switch (Objects.requireNonNull(MessageType.matchByType(message.getTypee()))) {
                case PRIVATEE -> handlePrivateMessage(ctx, message);
                case GROUP -> handleGroupMessage(ctx, message);
                default -> ctx.channel().writeAndFlush(Result.error("消息类型错误！"));
            }
        } catch (Exception e) {
            ctx.channel().writeAndFlush(Result.error("消息格式错误！"));
        }
    }

    /**
     * 处理群聊消息
     *
     * @param ctx
     * @param message
     */
    private static void handleGroupMessage(ChannelHandlerContext ctx, ChatMessage message) {
        IMServer.GROUP.writeAndFlush(Result.ok("群消息：[" + message.getNick() + "]" + message.getContent()));
    }

    /**
     * 处理私聊消息
     *
     * @param ctx
     * @param message
     */
    private static void handlePrivateMessage(ChannelHandlerContext ctx, ChatMessage message) {
        if (StringUtils.isBlank(message.getTarget())) {
            ctx.channel().writeAndFlush(Result.error("消息目标不能为空！"));
            return;
        }
        if (StringUtils.isEmpty(message.getContent())) {
            ctx.channel().writeAndFlush(Result.error("消息内容不能为空！"));
            return;
        }
        Channel channel = ctx.channel();
        if (ObjectUtils.isEmpty(channel) || !channel.isActive()) {
            ctx.channel().writeAndFlush(Result.error("消息发送失败！"));
            return;
        }
        ctx.channel().writeAndFlush(Result.ok("私聊消息：[" + message.getNick() + ":" + message.getContent()) + "]");
    }

}
