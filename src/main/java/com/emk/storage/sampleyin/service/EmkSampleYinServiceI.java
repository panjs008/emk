package com.emk.storage.sampleyin.service;

import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSampleYinServiceI
        extends CommonService {
    public abstract void delete(EmkSampleYinEntity paramEmkSampleYinEntity)
            throws Exception;

    public abstract Serializable save(EmkSampleYinEntity paramEmkSampleYinEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSampleYinEntity paramEmkSampleYinEntity)
            throws Exception;
}
