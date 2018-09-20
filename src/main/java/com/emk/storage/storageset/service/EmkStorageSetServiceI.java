package com.emk.storage.storageset.service;

import com.emk.storage.storageset.entity.EmkStorageSetEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkStorageSetServiceI
        extends CommonService {
    public abstract void delete(EmkStorageSetEntity paramEmkStorageSetEntity)
            throws Exception;

    public abstract Serializable save(EmkStorageSetEntity paramEmkStorageSetEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkStorageSetEntity paramEmkStorageSetEntity)
            throws Exception;
}
