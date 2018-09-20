package com.emk.storage.accessories.service;

import com.emk.storage.accessories.entity.EmkAccessoriesEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkAccessoriesServiceI
        extends CommonService {
    public abstract void delete(EmkAccessoriesEntity paramEmkAccessoriesEntity)
            throws Exception;

    public abstract Serializable save(EmkAccessoriesEntity paramEmkAccessoriesEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkAccessoriesEntity paramEmkAccessoriesEntity)
            throws Exception;
}
