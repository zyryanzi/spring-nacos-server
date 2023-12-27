package cloud.spring.my.shortdomain.mapper;

import org.apache.ibatis.session.ResultHandler;

public interface StreamReadMapper {
    void getAll(ResultHandler<Object> handler);
}
