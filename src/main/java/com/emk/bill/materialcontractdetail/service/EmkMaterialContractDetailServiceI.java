package com.emk.bill.materialcontractdetail.service;

import com.emk.bill.materialcontractdetail.entity.EmkMaterialContractDetailEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkMaterialContractDetailServiceI
        extends CommonService {
    public abstract void delete(EmkMaterialContractDetailEntity paramEmkMaterialContractDetailEntity)
            throws Exception;

    public abstract Serializable save(EmkMaterialContractDetailEntity paramEmkMaterialContractDetailEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkMaterialContractDetailEntity paramEmkMaterialContractDetailEntity)
            throws Exception;
}
