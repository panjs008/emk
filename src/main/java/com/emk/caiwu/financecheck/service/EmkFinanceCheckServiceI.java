package com.emk.caiwu.financecheck.service;
import com.emk.caiwu.financecheck.entity.EmkFinanceCheckEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkFinanceCheckServiceI extends CommonService{
	
 	public void delete(EmkFinanceCheckEntity entity) throws Exception;
 	
 	public Serializable save(EmkFinanceCheckEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkFinanceCheckEntity entity) throws Exception;
 	
}
