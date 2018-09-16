package com.emk.storage.storage.service;

import com.emk.storage.storage.entity.EmkStorageEntity;
import java.io.Serializable;
import org.jeecgframework.core.common.service.CommonService;

public abstract interface EmkStorageServiceI
  extends CommonService
{
  public abstract void delete(EmkStorageEntity paramEmkStorageEntity)
    throws Exception;
  
  public abstract Serializable save(EmkStorageEntity paramEmkStorageEntity)
    throws Exception;
  
  public abstract void saveOrUpdate(EmkStorageEntity paramEmkStorageEntity)
    throws Exception;
}
