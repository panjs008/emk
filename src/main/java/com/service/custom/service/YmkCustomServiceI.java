package com.service.custom.service;

import com.service.custom.entity.YmkCustomEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface YmkCustomServiceI
        extends CommonService {
    public abstract void delete(YmkCustomEntity paramYmkCustomEntity)
            throws Exception;

    public abstract Serializable save(YmkCustomEntity paramYmkCustomEntity)
            throws Exception;

    public abstract void saveOrUpdate(YmkCustomEntity paramYmkCustomEntity)
            throws Exception;
}
