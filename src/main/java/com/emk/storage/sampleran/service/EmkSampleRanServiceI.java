package com.emk.storage.sampleran.service;

import com.emk.storage.sampleran.entity.EmkSampleRanEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSampleRanServiceI
        extends CommonService {
    public abstract void delete(EmkSampleRanEntity paramEmkSampleRanEntity)
            throws Exception;

    public abstract Serializable save(EmkSampleRanEntity paramEmkSampleRanEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSampleRanEntity paramEmkSampleRanEntity)
            throws Exception;
}
