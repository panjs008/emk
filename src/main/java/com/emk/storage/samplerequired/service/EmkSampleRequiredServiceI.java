package com.emk.storage.samplerequired.service;

import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSampleRequiredServiceI
        extends CommonService {
    public abstract void delete(EmkSampleRequiredEntity paramEmkSampleRequiredEntity)
            throws Exception;

    public abstract Serializable save(EmkSampleRequiredEntity paramEmkSampleRequiredEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSampleRequiredEntity paramEmkSampleRequiredEntity)
            throws Exception;
}
