package com.emk.storage.samplegx.service;

import com.emk.storage.samplegx.entity.EmkSampleGxEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSampleGxServiceI
        extends CommonService {
    public abstract void delete(EmkSampleGxEntity paramEmkSampleGxEntity)
            throws Exception;

    public abstract Serializable save(EmkSampleGxEntity paramEmkSampleGxEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSampleGxEntity paramEmkSampleGxEntity)
            throws Exception;
}
