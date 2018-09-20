package com.emk.produce.test.service;

import com.emk.produce.test.entity.EmkTestEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkTestServiceI
        extends CommonService {
    public abstract void delete(EmkTestEntity paramEmkTestEntity)
            throws Exception;

    public abstract Serializable save(EmkTestEntity paramEmkTestEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkTestEntity paramEmkTestEntity)
            throws Exception;
}
