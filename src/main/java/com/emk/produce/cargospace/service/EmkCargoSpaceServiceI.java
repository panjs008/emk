package com.emk.produce.cargospace.service;

import com.emk.produce.cargospace.entity.EmkCargoSpaceEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkCargoSpaceServiceI
        extends CommonService {
    public abstract void delete(EmkCargoSpaceEntity paramEmkCargoSpaceEntity)
            throws Exception;

    public abstract Serializable save(EmkCargoSpaceEntity paramEmkCargoSpaceEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkCargoSpaceEntity paramEmkCargoSpaceEntity)
            throws Exception;
}
