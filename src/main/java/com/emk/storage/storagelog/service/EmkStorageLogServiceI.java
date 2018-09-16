package com.emk.storage.storagelog.service;

import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkStorageLogServiceI
  extends CommonService
{
  public abstract void delete(EmkStorageLogEntity paramEmkStorageLogEntity)
    throws Exception;
  
  public abstract Serializable save(EmkStorageLogEntity paramEmkStorageLogEntity)
    throws Exception;
  
  public abstract void saveOrUpdate(EmkStorageLogEntity paramEmkStorageLogEntity)
    throws Exception;
}
