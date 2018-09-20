package com.emk.storage.storage.service;

import com.emk.storage.storage.entity.EmkStorageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkStorageServiceI extends CommonService {

    public void delete(EmkStorageEntity entity) throws Exception;

    public Serializable save(EmkStorageEntity entity) throws Exception;

    public void saveOrUpdate(EmkStorageEntity entity) throws Exception;

}
