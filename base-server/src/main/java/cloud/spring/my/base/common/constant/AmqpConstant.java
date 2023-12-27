package cloud.spring.my.base.common.constant;

/**
 * amqp常量
 */
public class AmqpConstant {

    public static final String EXCHANGE_NAME = "beyond-exchange";
    public static final String CUSTOM_EXCHANGE_NAME = "custom-exchange";
    public static final String DEAD_EXCHANGE_NAME = "beyond-dead-exchange";
    public static final String CONFIRM_EXCHANGE_NAME = "beyond-confirm-exchange";
    public static final String BACKUP_EXCHANGE_NAME = "beyond-backup-exchange";
    public static final String FED_EXCHANGE = "fed-exchange";
    public static final String QUEUE_NAME = "beyond-queue";
    public static final String DEAD_QUEUE_NAME = "beyond-dead-queue";
    public static final String CONFIRM_QUEUE_NAME = "beyond-confirm-queue";
    public static final String BACKUP_QUEUE_NAME = "beyond-backup-queue";
    public static final String WARNING_QUEUE_NAME = "beyond-warning-queue";

    public static final String ROUTING_KEY = "beyond-routing-key";
    public static final String DEAD_ROUTING_KEY = "beyond-dead-routing-key";
    public static final String CONFIRM_ROUTING_KEY = "beyond-confirm-routing-key";
    public static final String BACKUP_ROUTING_KEY = "beyond-backup-routing-key";

    /**
     * 死信交换机
     */
    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    /**
     * 存活时间 time to live
     */
    public static final String X_MESSAGE_TTL = "x-message-ttl";
    /**
     * 最大长度
     */
    public static final String X_MAX_LENGTH = "x-max-length";
    /**
     * 变更到备份交换机
     */
    public static final String ALTERNATIVE_EXCHANGE = "alternative-exchange";
    /**
     * 优先级，建议不太大，浪费内存和cpu资源，可以设为 10
     */
    public static final String X_MAX_PRIORITY = "x-max-priority";

}
