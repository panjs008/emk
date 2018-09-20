package com.emk.bill.materialrequired.service;

import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkMaterialRequiredServiceI
        extends CommonService {
    public abstract void delete(EmkMaterialRequiredEntity paramEmkMaterialRequiredEntity)
            throws Exception;

    public abstract Serializable save(EmkMaterialRequiredEntity paramEmkMaterialRequiredEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkMaterialRequiredEntity paramEmkMaterialRequiredEntity)
            throws Exception;
}
