package com.emk.bill.proorderdetail.service;

import com.emk.bill.proorderdetail.entity.EmkProOrderDetailEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProOrderDetailServiceI
        extends CommonService {
    public abstract void delete(EmkProOrderDetailEntity paramEmkProOrderDetailEntity)
            throws Exception;

    public abstract Serializable save(EmkProOrderDetailEntity paramEmkProOrderDetailEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProOrderDetailEntity paramEmkProOrderDetailEntity)
            throws Exception;
}
