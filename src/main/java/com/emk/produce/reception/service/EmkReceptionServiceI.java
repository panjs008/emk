package com.emk.produce.reception.service;

import com.emk.produce.reception.entity.EmkReceptionEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkReceptionServiceI
        extends CommonService {
    public abstract void delete(EmkReceptionEntity paramEmkReceptionEntity)
            throws Exception;

    public abstract Serializable save(EmkReceptionEntity paramEmkReceptionEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkReceptionEntity paramEmkReceptionEntity)
            throws Exception;
}
