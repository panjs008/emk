package com.service.customalert.service;

import com.service.customalert.entity.YmkCustomAlertEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface YmkCustomAlertServiceI
        extends CommonService {
    public abstract void delete(YmkCustomAlertEntity paramYmkCustomAlertEntity)
            throws Exception;

    public abstract Serializable save(YmkCustomAlertEntity paramYmkCustomAlertEntity)
            throws Exception;

    public abstract void saveOrUpdate(YmkCustomAlertEntity paramYmkCustomAlertEntity)
            throws Exception;
}
