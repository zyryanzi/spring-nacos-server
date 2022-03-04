package cloud.spring.my.common.service.impl;

import cloud.spring.my.common.entity.SnowFlake;
import cloud.spring.my.common.service.IGenIdService;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IdServiceImpl
 * @Author: uray
 * @Date: 2022-02-28 21:19
 **/
@Service("GenIdService")
public class GenIdServiceImpl implements IGenIdService   {


    @Override
    public Long genSFId() {
        return new SnowFlake().nextId();
    }

}
