package com.emk.produce.invoices.service;

import com.emk.produce.invoices.entity.EmkInvoicesEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkInvoicesServiceI
        extends CommonService {
    public abstract void delete(EmkInvoicesEntity paramEmkInvoicesEntity)
            throws Exception;

    public abstract Serializable save(EmkInvoicesEntity paramEmkInvoicesEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkInvoicesEntity paramEmkInvoicesEntity)
            throws Exception;
}
