package com.emk.storage.sampleprice.service;

import com.emk.storage.sampleprice.entity.EmkSamplePriceEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSamplePriceServiceI
        extends CommonService {
    public abstract void delete(EmkSamplePriceEntity paramEmkSamplePriceEntity)
            throws Exception;

    public abstract Serializable save(EmkSamplePriceEntity paramEmkSamplePriceEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSamplePriceEntity paramEmkSamplePriceEntity)
            throws Exception;
}
