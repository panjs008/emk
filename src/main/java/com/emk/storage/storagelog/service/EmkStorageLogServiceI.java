package com.emk.storage.storagelog.service;

import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkStorageLogServiceI extends CommonService {

    public void delete(EmkStorageLogEntity entity) throws Exception;

    public Serializable save(EmkStorageLogEntity entity) throws Exception;

    public void saveOrUpdate(EmkStorageLogEntity entity) throws Exception;

}
