package com.emk.bill.materialpact.service;

import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkMaterialPactServiceI
        extends CommonService {
    public abstract void delete(EmkMaterialPactEntity paramEmkMaterialPactEntity)
            throws Exception;

    public abstract Serializable save(EmkMaterialPactEntity paramEmkMaterialPactEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkMaterialPactEntity paramEmkMaterialPactEntity)
            throws Exception;
}
