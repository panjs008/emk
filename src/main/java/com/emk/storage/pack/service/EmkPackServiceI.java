package com.emk.storage.pack.service;

import com.emk.storage.pack.entity.EmkPackEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkPackServiceI
        extends CommonService {
    public abstract void delete(EmkPackEntity paramEmkPackEntity)
            throws Exception;

    public abstract Serializable save(EmkPackEntity paramEmkPackEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkPackEntity paramEmkPackEntity)
            throws Exception;
}
