package com.emk.produce.produceschedule.service;

import com.emk.produce.produceschedule.entity.EmkProduceScheduleEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkProduceScheduleServiceI
        extends CommonService {
    public abstract void delete(EmkProduceScheduleEntity paramEmkProduceScheduleEntity)
            throws Exception;

    public abstract Serializable save(EmkProduceScheduleEntity paramEmkProduceScheduleEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkProduceScheduleEntity paramEmkProduceScheduleEntity)
            throws Exception;
}
