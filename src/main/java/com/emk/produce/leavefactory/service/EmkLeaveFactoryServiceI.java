package com.emk.produce.leavefactory.service;

import com.emk.produce.leavefactory.entity.EmkLeaveFactoryEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkLeaveFactoryServiceI
        extends CommonService {
    public abstract void delete(EmkLeaveFactoryEntity paramEmkLeaveFactoryEntity)
            throws Exception;

    public abstract Serializable save(EmkLeaveFactoryEntity paramEmkLeaveFactoryEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkLeaveFactoryEntity paramEmkLeaveFactoryEntity)
            throws Exception;
}
