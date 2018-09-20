package com.emk.product.producttype.service;

import com.emk.product.producttype.entity.EmkProductTypeEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProductTypeServiceI
        extends CommonService {
    public abstract void delete(EmkProductTypeEntity paramEmkProductTypeEntity)
            throws Exception;

    public abstract Serializable save(EmkProductTypeEntity paramEmkProductTypeEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProductTypeEntity paramEmkProductTypeEntity)
            throws Exception;
}
