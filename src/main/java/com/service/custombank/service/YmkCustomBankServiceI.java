package com.service.custombank.service;

import com.service.custombank.entity.YmkCustomBankEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface YmkCustomBankServiceI
        extends CommonService {
    public abstract void delete(YmkCustomBankEntity paramYmkCustomBankEntity)
            throws Exception;

    public abstract Serializable save(YmkCustomBankEntity paramYmkCustomBankEntity)
            throws Exception;

    public abstract void saveOrUpdate(YmkCustomBankEntity paramYmkCustomBankEntity)
            throws Exception;
}
