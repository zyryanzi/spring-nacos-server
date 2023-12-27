package cloud.spring.my.redis.service.impl;

import cloud.spring.my.redis.service.IShortDomainService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ShortDomainServiceImpl implements IShortDomainService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void save() {
        Object forObject = restTemplate.getForObject("http://localhost:58080//short-domain/domain/get", JSONObject.class);
        System.out.println("============" + forObject);
    }

    @Override
    public RecordId sendMessage(Message message) {
        if (ObjectUtils.isEmpty(message)) {
            log.info("--->> 消息内容为空");
        }
        ObjectRecord<String, Object> objRecord = ObjectRecord.create("streamKey", JSON.toJSONString(message));
        return redisTemplate.opsForStream().add(objRecord);
    }

}
