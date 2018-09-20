package com.emk.produce.produceqa.service;

import com.emk.produce.produceqa.entity.EmkProduceQaEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProduceQaServiceI
        extends CommonService {
    public abstract void delete(EmkProduceQaEntity paramEmkProduceQaEntity)
            throws Exception;

    public abstract Serializable save(EmkProduceQaEntity paramEmkProduceQaEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProduceQaEntity paramEmkProduceQaEntity)
            throws Exception;
}
