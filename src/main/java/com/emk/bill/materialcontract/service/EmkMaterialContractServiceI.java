package com.emk.bill.materialcontract.service;

import com.emk.bill.materialcontract.entity.EmkMaterialContractEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkMaterialContractServiceI
        extends CommonService {
    public abstract void delete(EmkMaterialContractEntity paramEmkMaterialContractEntity)
            throws Exception;

    public abstract Serializable save(EmkMaterialContractEntity paramEmkMaterialContractEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkMaterialContractEntity paramEmkMaterialContractEntity)
            throws Exception;
}
