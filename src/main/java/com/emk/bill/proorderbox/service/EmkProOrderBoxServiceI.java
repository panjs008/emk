package com.emk.bill.proorderbox.service;

import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProOrderBoxServiceI
        extends CommonService {
    public abstract void delete(EmkProOrderBoxEntity paramEmkProOrderBoxEntity)
            throws Exception;

    public abstract Serializable save(EmkProOrderBoxEntity paramEmkProOrderBoxEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProOrderBoxEntity paramEmkProOrderBoxEntity)
            throws Exception;
}
