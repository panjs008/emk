package com.emk.bill.offerpricedetail.service;

import com.emk.bill.offerpricedetail.entity.EmkOfferPriceDetailEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkOfferPriceDetailServiceI
        extends CommonService {
    public abstract void delete(EmkOfferPriceDetailEntity paramEmkOfferPriceDetailEntity)
            throws Exception;

    public abstract Serializable save(EmkOfferPriceDetailEntity paramEmkOfferPriceDetailEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkOfferPriceDetailEntity paramEmkOfferPriceDetailEntity)
            throws Exception;
}
