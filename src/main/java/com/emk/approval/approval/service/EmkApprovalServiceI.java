package com.emk.approval.approval.service;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkApprovalServiceI extends CommonService{
	
 	public void delete(EmkApprovalEntity entity) throws Exception;
 	
 	public Serializable save(EmkApprovalEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkApprovalEntity entity) throws Exception;
 	
}
