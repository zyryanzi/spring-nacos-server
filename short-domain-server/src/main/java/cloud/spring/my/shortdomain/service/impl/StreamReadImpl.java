package cloud.spring.my.shortdomain.service.impl;

import cloud.spring.my.shortdomain.mapper.StreamReadMapper;
import cloud.spring.my.shortdomain.service.IStreamRead;
import jakarta.annotation.Resource;

import java.util.List;

public class StreamReadImpl implements IStreamRead {

    @Resource
    private StreamReadMapper streamReadMapper;

    @Override
    public List<Object> streamRead() {
        streamReadMapper.getAll(resultContext -> {
            Object resultObject = resultContext.getResultObject();

        });
        return null;
    }
}
