package cloud.spring.my.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum CommandType {

    /**
     * 连接
     */
    CONNECTION(1001),

    /**
     * 聊天
     */
    CHAT(1002),

    /**
     * 加入群组
     */
    JOIN_GROUP(1003),

    ;


    private final Integer code;

    public static CommandType matchByCode(Integer code) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.getCode().equals(code)) {
                return commandType;
            }
        }
        return null;
    }

}
