package com.emk.bill.clause.service;

import com.emk.bill.clause.entity.EmkClauseEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkClauseServiceI
        extends CommonService {
    public abstract void delete(EmkClauseEntity paramEmkClauseEntity)
            throws Exception;

    public abstract Serializable save(EmkClauseEntity paramEmkClauseEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkClauseEntity paramEmkClauseEntity)
            throws Exception;
}
