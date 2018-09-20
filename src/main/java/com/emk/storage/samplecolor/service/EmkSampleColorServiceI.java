package com.emk.storage.samplecolor.service;

import com.emk.storage.samplecolor.entity.EmkSampleColorEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSampleColorServiceI
        extends CommonService {
    public abstract void delete(EmkSampleColorEntity paramEmkSampleColorEntity)
            throws Exception;

    public abstract Serializable save(EmkSampleColorEntity paramEmkSampleColorEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSampleColorEntity paramEmkSampleColorEntity)
            throws Exception;
}
