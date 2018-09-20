package com.emk.storage.sampletotal.service;

import com.emk.storage.sampletotal.entity.EmkSampleTotalEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSampleTotalServiceI
        extends CommonService {
    public abstract void delete(EmkSampleTotalEntity paramEmkSampleTotalEntity)
            throws Exception;

    public abstract Serializable save(EmkSampleTotalEntity paramEmkSampleTotalEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSampleTotalEntity paramEmkSampleTotalEntity)
            throws Exception;
}
