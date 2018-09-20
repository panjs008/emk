package com.service.customcontact.service;

import com.service.customcontact.entity.YmkCustomContactEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface YmkCustomContactServiceI
        extends CommonService {
    public abstract void delete(YmkCustomContactEntity paramYmkCustomContactEntity)
            throws Exception;

    public abstract Serializable save(YmkCustomContactEntity paramYmkCustomContactEntity)
            throws Exception;

    public abstract void saveOrUpdate(YmkCustomContactEntity paramYmkCustomContactEntity)
            throws Exception;
}
