package com.emk.bill.proorder.service;

import com.emk.bill.proorder.entity.EmkProOrderEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProOrderServiceI
        extends CommonService {
    public abstract void delete(EmkProOrderEntity paramEmkProOrderEntity)
            throws Exception;

    public abstract Serializable save(EmkProOrderEntity paramEmkProOrderEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProOrderEntity paramEmkProOrderEntity)
            throws Exception;
}
