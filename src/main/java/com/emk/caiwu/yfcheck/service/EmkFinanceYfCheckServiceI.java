package com.emk.caiwu.yfcheck.service;
import com.emk.caiwu.yfcheck.entity.EmkFinanceYfCheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkFinanceYfCheckServiceI extends CommonService{
	
 	public void delete(EmkFinanceYfCheckEntity entity) throws Exception;
 	
 	public Serializable save(EmkFinanceYfCheckEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkFinanceYfCheckEntity entity) throws Exception;
 	
}
