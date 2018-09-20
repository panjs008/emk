package com.emk.storage.sampledetail.service;

import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSampleDetailServiceI
        extends CommonService {
    public abstract void delete(EmkSampleDetailEntity paramEmkSampleDetailEntity)
            throws Exception;

    public abstract Serializable save(EmkSampleDetailEntity paramEmkSampleDetailEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSampleDetailEntity paramEmkSampleDetailEntity)
            throws Exception;
}
