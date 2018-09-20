package com.emk.produce.examtime.service;

import com.emk.produce.examtime.entity.EmkExamTimeEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkExamTimeServiceI
        extends CommonService {
    public abstract void delete(EmkExamTimeEntity paramEmkExamTimeEntity)
            throws Exception;

    public abstract Serializable save(EmkExamTimeEntity paramEmkExamTimeEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkExamTimeEntity paramEmkExamTimeEntity)
            throws Exception;
}
