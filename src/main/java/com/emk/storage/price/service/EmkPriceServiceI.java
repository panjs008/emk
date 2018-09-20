package com.emk.storage.price.service;

import com.emk.storage.price.entity.EmkPriceEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkPriceServiceI
        extends CommonService {
    public abstract void delete(EmkPriceEntity paramEmkPriceEntity)
            throws Exception;

    public abstract Serializable save(EmkPriceEntity paramEmkPriceEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkPriceEntity paramEmkPriceEntity)
            throws Exception;
}
