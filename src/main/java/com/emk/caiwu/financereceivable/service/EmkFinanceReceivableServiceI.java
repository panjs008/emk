package com.emk.caiwu.financereceivable.service;
import com.emk.caiwu.financereceivable.entity.EmkFinanceReceivableEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkFinanceReceivableServiceI extends CommonService{
	
 	public void delete(EmkFinanceReceivableEntity entity) throws Exception;
 	
 	public Serializable save(EmkFinanceReceivableEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkFinanceReceivableEntity entity) throws Exception;
 	
}
