package com.emk.bill.proorderbarcode.service;

import com.emk.bill.proorderbarcode.entity.EmkProOrderBarcodeEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProOrderBarcodeServiceI
        extends CommonService {
    public abstract void delete(EmkProOrderBarcodeEntity paramEmkProOrderBarcodeEntity)
            throws Exception;

    public abstract Serializable save(EmkProOrderBarcodeEntity paramEmkProOrderBarcodeEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProOrderBarcodeEntity paramEmkProOrderBarcodeEntity)
            throws Exception;
}
