package com.emk.product.hs.service;

import com.emk.product.hs.entity.EmkProductHsEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProductHsServiceI
        extends CommonService {
    public abstract void delete(EmkProductHsEntity paramEmkProductHsEntity)
            throws Exception;

    public abstract Serializable save(EmkProductHsEntity paramEmkProductHsEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProductHsEntity paramEmkProductHsEntity)
            throws Exception;
}
