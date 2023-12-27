package cloud.spring.my.redis.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.stream.RecordId;

public interface IShortDomainService {

    void save();

    RecordId sendMessage(Message message);

}
