package com.emk.product.productinfo.service;

import com.emk.product.productinfo.entity.EmkProductInfoEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProductInfoServiceI
        extends CommonService {
    public abstract void delete(EmkProductInfoEntity paramEmkProductInfoEntity)
            throws Exception;

    public abstract Serializable save(EmkProductInfoEntity paramEmkProductInfoEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProductInfoEntity paramEmkProductInfoEntity)
            throws Exception;
}
