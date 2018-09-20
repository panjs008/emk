package com.emk.bill.offerprice.service;

import com.emk.bill.offerprice.entity.EmkOfferPriceEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkOfferPriceServiceI
        extends CommonService {
    public abstract void delete(EmkOfferPriceEntity paramEmkOfferPriceEntity)
            throws Exception;

    public abstract Serializable save(EmkOfferPriceEntity paramEmkOfferPriceEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkOfferPriceEntity paramEmkOfferPriceEntity)
            throws Exception;
}
