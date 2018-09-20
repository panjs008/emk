package com.emk.storage.color.service;

import com.emk.storage.color.entity.EmkColorEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkColorServiceI
        extends CommonService {
    public abstract void delete(EmkColorEntity paramEmkColorEntity)
            throws Exception;

    public abstract Serializable save(EmkColorEntity paramEmkColorEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkColorEntity paramEmkColorEntity)
            throws Exception;
}
