package cloud.spring.my.entity;

import lombok.Data;

/**
 * 消息实体类
 */
@Data
public class ChatMessage extends Command {

    /**
     * 消息类型
     */
    private Integer typee;

    /**
     * 目标用户
     */
    private String target;

    /**
     * 消息内容
     */
    private String content;

}
