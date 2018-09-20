package com.service.customtrace.service;

import com.service.customtrace.entity.YmkCustomTraceEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface YmkCustomTraceServiceI
        extends CommonService {
    public abstract void delete(YmkCustomTraceEntity paramYmkCustomTraceEntity)
            throws Exception;

    public abstract Serializable save(YmkCustomTraceEntity paramYmkCustomTraceEntity)
            throws Exception;

    public abstract void saveOrUpdate(YmkCustomTraceEntity paramYmkCustomTraceEntity)
            throws Exception;
}
