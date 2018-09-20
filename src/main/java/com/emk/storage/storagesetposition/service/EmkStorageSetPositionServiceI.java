package com.emk.storage.storagesetposition.service;

import com.emk.storage.storagesetposition.entity.EmkStorageSetPositionEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkStorageSetPositionServiceI
        extends CommonService {
    public abstract void delete(EmkStorageSetPositionEntity paramEmkStorageSetPositionEntity)
            throws Exception;

    public abstract Serializable save(EmkStorageSetPositionEntity paramEmkStorageSetPositionEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkStorageSetPositionEntity paramEmkStorageSetPositionEntity)
            throws Exception;
}
