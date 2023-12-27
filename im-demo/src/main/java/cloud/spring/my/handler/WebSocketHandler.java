package cloud.spring.my.handler;

import cloud.spring.my.entity.Command;
import cloud.spring.my.entity.vo.Result;
import cloud.spring.my.enums.CommandType;
import com.alibaba.fastjson2.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
        if (frame != null) {
            try {
                log.info("<<-- frame.text: {}", frame.text());
                Command command = JSON.parseObject(frame.text(), Command.class);
                switch (Objects.requireNonNull(CommandType.matchByCode(command.getTypee()))) {
                    case CONNECTION -> ConnectionHandler.execute(ctx, command);
                    case CHAT -> ChatHandler.execute(ctx, frame);
                    case JOIN_GROUP -> JoinGroupHandler.execute(ctx);
                    default -> ctx.channel().writeAndFlush(Result.error("操作类型不存在"));
                }
            } catch (Exception e) {
                log.error("websocket处理失败: {}", e.getMessage());
                ctx.channel().writeAndFlush(Result.error(e.getMessage()));
            }

        }
    }


}
