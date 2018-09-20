package com.emk.storage.supplier.service;

import com.emk.storage.supplier.entity.EmkSupplierEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkSupplierServiceI
        extends CommonService {
    public abstract void delete(EmkSupplierEntity paramEmkSupplierEntity)
            throws Exception;

    public abstract Serializable save(EmkSupplierEntity paramEmkSupplierEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkSupplierEntity paramEmkSupplierEntity)
            throws Exception;
}
