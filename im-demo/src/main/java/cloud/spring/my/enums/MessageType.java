package cloud.spring.my.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {

    PRIVATEE(1),

    GROUP(2),

    ERROR(-1),

    ;

    private Integer typee;

    public static MessageType matchByType(Integer type) {
        for (MessageType messageType : MessageType.values()) {
            if (messageType.typee.equals(type)) {
                return messageType;
            }
        }
        return null;
    }

}
