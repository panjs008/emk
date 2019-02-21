package com.emk.approval.approvaldetail.service;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EmkApprovalDetailServiceI extends CommonService{
	
 	public void delete(EmkApprovalDetailEntity entity) throws Exception;
 	
 	public Serializable save(EmkApprovalDetailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EmkApprovalDetailEntity entity) throws Exception;
 	
}
