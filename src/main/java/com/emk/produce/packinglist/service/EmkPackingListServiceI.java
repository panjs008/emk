package com.emk.produce.packinglist.service;

import com.emk.produce.packinglist.entity.EmkPackingListEntity;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkPackingListServiceI
        extends CommonService {
    public abstract void delete(EmkPackingListEntity paramEmkPackingListEntity)
            throws Exception;

    public abstract Serializable save(EmkPackingListEntity paramEmkPackingListEntity)
            throws Exception;

    public abstract void saveOrUpdate(EmkPackingListEntity paramEmkPackingListEntity)
            throws Exception;
}
