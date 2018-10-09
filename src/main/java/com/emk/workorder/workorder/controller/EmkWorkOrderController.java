package com.emk.workorder.workorder.controller;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import com.emk.storage.instorage.entity.EmkInStorageEntity;
import com.emk.storage.storage.entity.EmkStorageEntity;
import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import com.emk.util.ParameterUtil;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
import com.emk.workorder.workorder.service.EmkWorkOrderServiceI;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.*;
import org.jeecgframework.p3.core.common.utils.DateUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 工单管理
 * @author onlineGenerator
 * @date 2018-09-29 21:28:13
 * @version V1.0   
 *
 */
@Api(value="EmkWorkOrder",description="工单管理",tags="emkWorkOrderController")
@Controller
@RequestMapping("/emkWorkOrderController")
public class EmkWorkOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkWorkOrderController.class);

	@Autowired
	private EmkWorkOrderServiceI emkWorkOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	@Autowired
	ProcessEngine processEngine;
	@Autowired
	ManagementService managementService;
	@Autowired
	ProcessEngineConfiguration processEngineConfiguration;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService historyService;


	/**
	 * 工单管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/workorder/workorder/emkWorkOrderList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(EmkWorkOrderEntity emkWorkOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkWorkOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkWorkOrder, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkWorkOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除工单管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkWorkOrderEntity emkWorkOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkWorkOrder = systemService.getEntity(EmkWorkOrderEntity.class, emkWorkOrder.getId());
		message = "工单管理删除成功";
		try{
			emkWorkOrderService.delete(emkWorkOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工单管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工单管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工单管理删除成功";
		try{
			for(String id:ids.split(",")){
				EmkWorkOrderEntity emkWorkOrder = systemService.getEntity(EmkWorkOrderEntity.class, id);
				emkWorkOrderService.delete(emkWorkOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工单管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工单管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkWorkOrderEntity emkWorkOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工单管理添加成功";
		try{
			emkWorkOrderService.save(emkWorkOrder);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工单管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新工单管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkWorkOrderEntity emkWorkOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工单管理更新成功";
		EmkWorkOrderEntity t = emkWorkOrderService.get(EmkWorkOrderEntity.class, emkWorkOrder.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(emkWorkOrder, t);
			emkWorkOrderService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工单管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 工单管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkWorkOrderEntity emkWorkOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkWorkOrder.getId())) {
			emkWorkOrder = emkWorkOrderService.getEntity(EmkWorkOrderEntity.class, emkWorkOrder.getId());
			req.setAttribute("emkWorkOrderPage", emkWorkOrder);
		}
		return new ModelAndView("com/emk/workorder/workorder/emkWorkOrder-add");
	}
	/**
	 * 工单管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkWorkOrderEntity emkWorkOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkWorkOrder.getId())) {
			emkWorkOrder = emkWorkOrderService.getEntity(EmkWorkOrderEntity.class, emkWorkOrder.getId());
			req.setAttribute("emkWorkOrderPage", emkWorkOrder);
		}
		return new ModelAndView("com/emk/workorder/workorder/emkWorkOrder-update");
	}
	@RequestMapping(params = "goUpdate2")
	public ModelAndView goUpdate2(EmkWorkOrderEntity emkWorkOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkWorkOrder.getId())) {
			emkWorkOrder = emkWorkOrderService.getEntity(EmkWorkOrderEntity.class, emkWorkOrder.getId());
			req.setAttribute("emkWorkOrderPage", emkWorkOrder);
		}
		return new ModelAndView("com/emk/workorder/workorder/emkWorkOrder-update2");
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkWorkOrderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkWorkOrderEntity emkWorkOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkWorkOrderEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkWorkOrder, request.getParameterMap());
		List<EmkWorkOrderEntity> emkWorkOrders = this.emkWorkOrderService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"工单管理");
		modelMap.put(NormalExcelConstants.CLASS,EmkWorkOrderEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工单管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkWorkOrders);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkWorkOrderEntity emkWorkOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"工单管理");
    	modelMap.put(NormalExcelConstants.CLASS,EmkWorkOrderEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工单管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<EmkWorkOrderEntity> listEmkWorkOrderEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkWorkOrderEntity.class,params);
				for (EmkWorkOrderEntity emkWorkOrder : listEmkWorkOrderEntitys) {
					emkWorkOrderService.save(emkWorkOrder);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="工单管理列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkWorkOrderEntity>> list() {
		List<EmkWorkOrderEntity> listEmkWorkOrders=emkWorkOrderService.getList(EmkWorkOrderEntity.class);
		return Result.success(listEmkWorkOrders);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取工单管理信息",notes="根据ID获取工单管理信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkWorkOrderEntity task = emkWorkOrderService.get(EmkWorkOrderEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取工单管理信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建工单管理")
	public ResponseMessage<?> create(@ApiParam(name="工单管理对象")@RequestBody EmkWorkOrderEntity emkWorkOrder, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkWorkOrderEntity>> failures = validator.validate(emkWorkOrder);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkWorkOrderService.save(emkWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工单管理信息保存失败");
		}
		return Result.success(emkWorkOrder);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新工单管理",notes="更新工单管理")
	public ResponseMessage<?> update(@ApiParam(name="工单管理对象")@RequestBody EmkWorkOrderEntity emkWorkOrder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkWorkOrderEntity>> failures = validator.validate(emkWorkOrder);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkWorkOrderService.saveOrUpdate(emkWorkOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新工单管理信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新工单管理信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除工单管理")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkWorkOrderService.deleteEntityById(EmkWorkOrderEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工单管理删除失败");
		}

		return Result.success();
	}

	@RequestMapping(params="doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(EmkWorkOrderEntity emkWorkOrderEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工单提交成功";
		try {
			int flag = 0;
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			if ((emkWorkOrderEntity.getId() == null) || (emkWorkOrderEntity.getId().isEmpty())) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkWorkOrderEntity workOrderEntity = systemService.getEntity(EmkWorkOrderEntity.class, id);
					if (!workOrderEntity.getState().equals("0")) {
						message = "存在已提交的工单，请重新选择在提交工单！";
						j.setSuccess(false);
						flag = 1;
						break;
					}
				}
			}else{
				map.put("ids", emkWorkOrderEntity.getId());
			}
			Map<String, Object> variables = new HashMap();
			if (flag == 0) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkWorkOrderEntity t = emkWorkOrderService.get(EmkWorkOrderEntity.class, id);
					t.setState("1");
					//variables.put("isPrint", t.getIsPrint());
					variables.put("inputUser", t.getId());
					MyBeanUtils.copyBeanNotNull2Bean(emkWorkOrderEntity, t);

					List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
					if (task.size() > 0) {
						Task task1 = (Task)task.get(task.size() - 1);
						if (task1.getTaskDefinitionKey().equals("khxpTask")) {
							t.setAskWorkUser(user.getRealName());
							t.setAskWorkUserId(user.getUserName());
							t.setAskWorkDate(DateUtil.getCurrentDate());
							t.setSampleNum(map.get("leadAdvice").toString());

							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("sampleTask")) {
							if(t.getSampleNum() == null || t.getSampleNum().equals("")){
								j.setMsg("打印单号还未生成，请确认");
								j.setSuccess(false);
								return j;
							}
							t.setSampleAdvice(map.get("leadAdvice").toString());
							t.setSampleCheckUser(user.getRealName());
							t.setSampleCheckUserId(user.getUserName());
							t.setSampleDate(DateUtil.getCurrentDate());
							variables.put("isPass", t.getIsPass());
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("sampleCheckTask")) {
							if(t.getSampleCheckNo() == null || t.getSampleCheckNo().equals("")){
								j.setMsg("样品质检单号还未生成，请确认");
								j.setSuccess(false);
								return j;
							}
							if(emkWorkOrderEntity.getIsPass().equals("0")){
								variables.put("isPass", t.getIsPrint());
							}
							t.setAskWorkUser(user.getRealName());
							t.setAskWorkUserId(user.getUserName());
							t.setSampleCheckAdvice(map.get("leadAdvice").toString());
							t.setAskWorkDate(DateUtil.getCurrentDate());
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("checkTask")) {
							/*t.setLeader(user.getRealName());
							t.setLeadUserId(user.getId());
							t.setLeadAdvice(emkMInStorageEntity.getLeadAdvice());
							if (emkMInStorageEntity.getIsPass().equals("0")) {
								if ((map.get("realName") == null) || (map.get("realName").toString().equals(""))) {
									j.setSuccess(false);
									j.setMsg("请选择下一处理人");
									return j;
								}
								t.setFinancer(map.get("realName").toString());
								t.setFinanceUserId(map.get("userName").toString());
								variables.put("isPass", emkMInStorageEntity.getIsPass());
								taskService.complete(task1.getId(), variables);
							} else {
								List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(emkMInStorageEntity.getId()).list();

								List<Task> taskList = taskService.createTaskQuery().taskAssignee(emkMInStorageEntity.getId()).list();
								if (taskList.size() > 0) {
									Task taskH = (Task)taskList.get(taskList.size() - 1);
									HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
									turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
									Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC", new Object[] { t.getId(), historicTaskInstance.getTaskDefinitionKey() });
									String[] activitIdArr = activityMap.get("ids").toString().split(",");
									String[] taskIdArr = activityMap.get("taskids").toString().split(",");
									systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(), taskIdArr[1]);
									systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", activitIdArr[0], activitIdArr[1] );
								}
								t.setState("0");
							}*/
						}else if (task1.getTaskDefinitionKey().equals("cwTask")) {

							t.setState("2");
							taskService.complete(task1.getId(), variables);
						}
					}else {
						ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("emk", "emkWorkOrderPage", variables);
						task = taskService.createTaskQuery().taskAssignee(id).list();
						Task task1 = task.get(task.size() - 1);
						taskService.complete(task1.getId(), variables);
					}
					systemService.saveOrUpdate(t);
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		catch (Exception e) {
			e.printStackTrace();
			message = "入库申请单提交失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 流程转向操作
	 *
	 * @param taskId
	 *            当前任务ID
	 * @param activityId
	 *            目标节点任务ID
	 * @param variables
	 *            流程变量
	 * @throws Exception
	 */
	private void turnTransition(String taskId, String activityId,
								Map<String, Object> variables) throws Exception {
		// 当前节点
		ActivityImpl currActivity = findActivitiImpl(taskId, null);
		// 清空当前流向
		List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);
		// 创建新流向
		TransitionImpl newTransition = currActivity.createOutgoingTransition();
		// 目标节点
		ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
		// 设置新流向的目标节点
		newTransition.setDestination(pointActivity);
		// 执行转向任务
		taskService.complete(taskId, variables);
		// 删除目标节点新流入
		pointActivity.getIncomingTransitions().remove(newTransition);
		// 还原以前流向
		restoreTransition(currActivity, oriPvmTransitionList);
	}

	/**
	 * 清空指定活动节点流向
	 *
	 * @param activityImpl
	 *            活动节点
	 * @return 节点流向集合
	 */
	private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
		// 存储当前节点所有流向临时变量
		List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
		// 获取当前节点所有流向，存储到临时变量，然后清空
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		for (PvmTransition pvmTransition : pvmTransitionList) {
			oriPvmTransitionList.add(pvmTransition);
		}
		pvmTransitionList.clear();

		return oriPvmTransitionList;
	}

	/**
	 * 还原指定活动节点流向
	 *
	 * @param activityImpl
	 *            活动节点
	 * @param oriPvmTransitionList
	 *            原有节点流向集合
	 */
	private void restoreTransition(ActivityImpl activityImpl,
								   List<PvmTransition> oriPvmTransitionList) {
		// 清空现有流向
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		pvmTransitionList.clear();
		// 还原以前流向
		for (PvmTransition pvmTransition : oriPvmTransitionList) {
			pvmTransitionList.add(pvmTransition);
		}
	}

	/**
	 * 根据任务ID获取流程定义
	 *
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
			String taskId) throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(findTaskById(taskId)
						.getProcessDefinitionId());

		if (processDefinition == null) {
			throw new Exception("流程定义未找到!");
		}

		return processDefinition;
	}



	/**
	 * 根据任务ID和节点ID获取活动节点 <br>
	 *
	 * @param taskId
	 *            任务ID
	 * @param activityId
	 *            活动节点ID <br>
	 *            如果为null或""，则默认查询当前活动节点 <br>
	 *            如果为"end"，则查询结束节点 <br>
	 *
	 * @return
	 * @throws Exception
	 */
	private ActivityImpl findActivitiImpl(String taskId, String activityId)
			throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

		// 获取当前活动节点ID
		if (activityId == null || activityId.isEmpty()) {
			activityId = findTaskById(taskId).getTaskDefinitionKey();
		}

		// 根据流程定义，获取该流程实例的结束节点
		if (activityId.toUpperCase().equals("END")) {
			for (ActivityImpl activityImpl : processDefinition.getActivities()) {
				List<PvmTransition> pvmTransitionList = activityImpl
						.getOutgoingTransitions();
				if (pvmTransitionList.isEmpty()) {
					return activityImpl;
				}
			}
		}

		// 根据节点ID，获取对应的活动节点
		ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
				.findActivity(activityId);

		return activityImpl;
	}

	/**
	 * 根据任务ID获得任务实例
	 *
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private TaskEntity findTaskById(String taskId) throws Exception {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(
				taskId).singleResult();
		if (task == null) {
			throw new Exception("任务实例未找到!");
		}
		return task;
	}

	@RequestMapping(params="goWork")
	public ModelAndView goWork(EmkWorkOrderEntity emkWorkOrderEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkWorkOrderEntity.getId())) {
			emkWorkOrderEntity = emkWorkOrderService.getEntity(EmkMInStorageEntity.class, emkWorkOrderEntity.getId());
			req.setAttribute("emkWorkOrderPage", emkWorkOrderEntity);
		}
		return new ModelAndView("com/emk/workorder/workorder/emkWorkOrder-work");
	}

	@RequestMapping(params="goTime")
	public ModelAndView goTime(EmkWorkOrderEntity emkWorkOrderEntity, HttpServletRequest req, DataGrid dataGrid) {
		String sql = "";String countsql = "";
		Map map = ParameterUtil.getParamMaps(req.getParameterMap());

		sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
				" WHEN t1.TASK_DEF_KEY_='khxpTask' THEN t2.create_name \n" +
				" WHEN t1.TASK_DEF_KEY_='sampleTask' THEN t2.ask_work_user \n" +
				" END workname FROM act_hi_taskinst t1 \n" +
				" LEFT JOIN emk_work_order t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

		countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='" + map.get("id") + "' ";
		if (dataGrid.getPage() == 1) {
			sql = sql + " limit 0, " + dataGrid.getRows();
		} else {
			sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
		}
		systemService.listAllByJdbc(dataGrid, sql, countsql);
		req.setAttribute("taskList", dataGrid.getResults());
		if (dataGrid.getResults().size() > 0) {
			req.setAttribute("stepProcess", Integer.valueOf(dataGrid.getResults().size() - 1));
		} else {
			req.setAttribute("stepProcess", Integer.valueOf(0));
		}
		emkWorkOrderEntity = emkWorkOrderService.getEntity(EmkWorkOrderEntity.class, emkWorkOrderEntity.getId());
		req.setAttribute("emkWorkOrderPage", emkWorkOrderEntity);
		return new ModelAndView("com/emk/workorder/workorder/time");


	}

	@RequestMapping(params="goProcess")
	public ModelAndView goProcess(EmkWorkOrderEntity emkWorkOrderEntity, HttpServletRequest req) {
		EmkWorkOrderEntity t = systemService.get(EmkWorkOrderEntity.class, emkWorkOrderEntity.getId());
		List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
		if (task.size() > 0) {
			Task task1 = (Task)task.get(task.size() - 1);
			req.getSession().setAttribute("orderPorcess", task1);
			req.getSession().setAttribute("orderFinish", "0");
		}else if (t.getState().equals("4")) {
			req.getSession().setAttribute("orderFinish", "1");
		}else {
			req.getSession().setAttribute("orderFinish", "0");
			req.getSession().setAttribute("orderPorcess", null);
		}
		return new ModelAndView("com/emk/workorder/workorder/emkWorkOrder-process");

	}

	@RequestMapping(params="process")
	public ModelAndView process(EmkWorkOrderEntity emkWorkOrderEntity, HttpServletRequest req) {
		return new ModelAndView("com/emk/workorder/workorder/process");
	}

	@RequestMapping(params="getCurrentProcess")
	@ResponseBody
	public AjaxJson getCurrentProcess(EmkWorkOrderEntity emkWorkOrderEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		EmkWorkOrderEntity t = systemService.get(EmkWorkOrderEntity.class, emkWorkOrderEntity.getId());
		List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
		if (task.size() > 0) {
			Task task1 = (Task)task.get(task.size() - 1);
			j.setMsg(task1.getName());
			request.getSession().setAttribute("orderPorcess", task1);
			request.getSession().setAttribute("orderFinish", "0");
		}else if (t.getState().equals("2")) {
			j.setMsg("完成");
			request.getSession().setAttribute("orderFinish", "1");
		}else {
			j.setMsg("工单");
			request.getSession().setAttribute("orderFinish", "0");
			request.getSession().setAttribute("orderPorcess", null);
		}
		return j;
	}

	@RequestMapping(params="showProcess")
	public void showProcess(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Map map = ParameterUtil.getParamMaps(req.getParameterMap());

		List<Task> task = taskService.createTaskQuery().taskAssignee(map.get("id").toString()).list();
		String processInstanceId = "";
		EmkWorkOrderEntity t = emkWorkOrderService.get(EmkWorkOrderEntity.class, map.get("id").toString());
		if (task.size() > 0) {
			Task task1 = (Task)task.get(task.size() - 1);
			processInstanceId = task1.getProcessInstanceId();
		}else if (t.getState().equals("2")) {
			Map hisPorcess = systemService.findOneForJdbc("SELECT PROC_INST_ID_ processid FROM act_hi_taskinst WHERE ASSIGNEE_=? LIMIT 0,1 ", new Object[] { map.get("id").toString() });
			processInstanceId = String.valueOf(hisPorcess.get("processid"));
		}
		if (processInstanceId != null && !processInstanceId.isEmpty()) {
			HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

			BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
			processEngineConfiguration = processEngine.getProcessEngineConfiguration();
			Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl)processEngineConfiguration);

			ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
			ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

			List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

			List<String> highLightedActivitis = new ArrayList();

			List<String> highLightedFlows = ParameterUtil.getHighLightedFlows(definitionEntity, highLightedActivitList);
			for (HistoricActivityInstance tempActivity : highLightedActivitList) {
				String activityId = tempActivity.getActivityId();
				highLightedActivitis.add(activityId);
			}
			InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, "宋体", "宋体", null, 1.0D);

			byte[] b = new byte[1024];
			int len;
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		}
	}
}
