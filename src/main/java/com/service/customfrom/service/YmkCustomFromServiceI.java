package com.service.customfrom.service;

import com.service.customfrom.entity.YmkCustomFromEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface YmkCustomFromServiceI
        extends CommonService {
    public abstract void delete(YmkCustomFromEntity paramYmkCustomFromEntity)
            throws Exception;

    public abstract Serializable save(YmkCustomFromEntity paramYmkCustomFromEntity)
            throws Exception;

    public abstract void saveOrUpdate(YmkCustomFromEntity paramYmkCustomFromEntity)
            throws Exception;
}
