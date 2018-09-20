package com.emk.bill.proorderpackage.service;

import com.emk.bill.proorderpackage.entity.EmkProOrderPackageEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProOrderPackageServiceI
        extends CommonService {
    public abstract void delete(EmkProOrderPackageEntity paramEmkProOrderPackageEntity)
            throws Exception;

    public abstract Serializable save(EmkProOrderPackageEntity paramEmkProOrderPackageEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProOrderPackageEntity paramEmkProOrderPackageEntity)
            throws Exception;
}
