package com.emk.storage.materialdetail.service;

import com.emk.storage.materialdetail.entity.EmkMaterialDetailEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkMaterialDetailServiceI
        extends CommonService {
    public abstract void delete(EmkMaterialDetailEntity paramEmkMaterialDetailEntity)
            throws Exception;

    public abstract Serializable save(EmkMaterialDetailEntity paramEmkMaterialDetailEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkMaterialDetailEntity paramEmkMaterialDetailEntity)
            throws Exception;
}
