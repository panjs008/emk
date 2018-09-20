package com.emk.storage.sample.service;

import com.emk.storage.sample.entity.EmkSampleEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSampleServiceI
        extends CommonService {
    public abstract void delete(EmkSampleEntity paramEmkSampleEntity)
            throws Exception;

    public abstract Serializable save(EmkSampleEntity paramEmkSampleEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSampleEntity paramEmkSampleEntity)
            throws Exception;
}
