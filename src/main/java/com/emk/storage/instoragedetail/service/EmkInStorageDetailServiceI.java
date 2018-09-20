package com.emk.storage.instoragedetail.service;

import com.emk.storage.instoragedetail.entity.EmkInStorageDetailEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkInStorageDetailServiceI
        extends CommonService {
    public abstract void delete(EmkInStorageDetailEntity paramEmkInStorageDetailEntity)
            throws Exception;

    public abstract Serializable save(EmkInStorageDetailEntity paramEmkInStorageDetailEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkInStorageDetailEntity paramEmkInStorageDetailEntity)
            throws Exception;
}
