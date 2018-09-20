package com.emk.bill.contract.service;

import com.emk.bill.contract.entity.EmkContractEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkContractServiceI
        extends CommonService {
    public abstract void delete(EmkContractEntity paramEmkContractEntity)
            throws Exception;

    public abstract Serializable save(EmkContractEntity paramEmkContractEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkContractEntity paramEmkContractEntity)
            throws Exception;
}
