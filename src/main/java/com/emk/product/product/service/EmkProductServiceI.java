package com.emk.product.product.service;

import com.emk.product.product.entity.EmkProductEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProductServiceI
        extends CommonService {
    public abstract void delete(EmkProductEntity paramEmkProductEntity)
            throws Exception;

    public abstract Serializable save(EmkProductEntity paramEmkProductEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProductEntity paramEmkProductEntity)
            throws Exception;
}
