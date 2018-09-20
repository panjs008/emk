package com.emk.storage.instorage.service;

import com.emk.storage.instorage.entity.EmkInStorageEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkInStorageServiceI
        extends CommonService {
    public abstract void delete(EmkInStorageEntity paramEmkInStorageEntity)
            throws Exception;

    public abstract Serializable save(EmkInStorageEntity paramEmkInStorageEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkInStorageEntity paramEmkInStorageEntity)
            throws Exception;
}
