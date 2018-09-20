package com.emk.storage.material.service;

import com.emk.storage.material.entity.EmkMaterialEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkMaterialServiceI
        extends CommonService {
    public abstract void delete(EmkMaterialEntity paramEmkMaterialEntity)
            throws Exception;

    public abstract Serializable save(EmkMaterialEntity paramEmkMaterialEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkMaterialEntity paramEmkMaterialEntity)
            throws Exception;
}
