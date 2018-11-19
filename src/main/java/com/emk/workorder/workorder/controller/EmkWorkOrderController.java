package com.emk.workorder.workorder.controller;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
import com.emk.workorder.workorder.service.EmkWorkOrderServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
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
							t.setSampleUser(user.getRealName());
							t.setSampleDate(DateUtil.getCurrentDate());
							t.setSampleUserId(user.getUserName());
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
							}else {
								List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(t.getId()).list();

								List<Task> taskList = taskService.createTaskQuery().taskAssignee(t.getId()).list();
								if (taskList.size() > 0) {
									Task taskH = (Task)taskList.get(taskList.size() - 1);
									HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
									FlowUtil.turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
									Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC", new Object[] { t.getId(), historicTaskInstance.getTaskDefinitionKey() });
									String[] activitIdArr = activityMap.get("ids").toString().split(",");
									String[] taskIdArr = activityMap.get("taskids").toString().split(",");
									systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(), taskIdArr[1]);
									systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", activitIdArr[0], activitIdArr[1] );
								}
								t.setState("0");
							}
							t.setSampleCheckUser(user.getRealName());
							t.setSampleCheckUserId(user.getUserName());
							t.setSampleCheckAdvice(map.get("leadAdvice").toString());
							t.setSampleCheckDate(DateUtil.getCurrentDate());
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("billTask")) {
							if(t.getOrderNo() == null || t.getOrderNo().equals("")){
								j.setMsg("订单单号还未生成，请确认");
								j.setSuccess(false);
								return j;
							}

							t.setOrderUser(user.getRealName());
							t.setOrderUserId(user.getUserName());
							t.setOrderAdvice(map.get("leadAdvice").toString());
							t.setOrderDate(DateUtil.getCurrentDate());
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("htTask")) {
							if(t.getHtNo() == null || t.getHtNo().equals("")){
								j.setMsg("合同单号还未生成，请确认");
								j.setSuccess(false);
								return j;
							}

							t.setHtUser(user.getRealName());
							t.setHtUserId(user.getUserName());
							t.setHtAdvice(map.get("leadAdvice").toString());
							t.setHtDate(DateUtil.getCurrentDate());
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("produceTask")) {
							if(t.getProduceNo() == null || t.getProduceNo().equals("")){
								j.setMsg("生产单号还未生成，请确认");
								j.setSuccess(false);
								return j;
							}

							t.setHtUser(user.getRealName());
							t.setHtUserId(user.getUserName());
							t.setHtAdvice(map.get("leadAdvice").toString());
							t.setHtDate(DateUtil.getCurrentDate());
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

		sql = " SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
				" WHEN t1.TASK_DEF_KEY_='khxpTask' THEN t2.ask_work_user \n" +
				" WHEN t1.TASK_DEF_KEY_='sampleTask' THEN t2.sample_user \n" +
				" WHEN t1.TASK_DEF_KEY_='sampleCheckTask' THEN t2.sample_check_user \n" +
				" WHEN t1.TASK_DEF_KEY_='billTask' THEN t2.order_user \n" +
				" WHEN t1.TASK_DEF_KEY_='htTask' THEN t2.ht_user \n" +
				" WHEN t1.TASK_DEF_KEY_='produceTask' THEN t2.produce_user \n" +
				" WHEN t1.TASK_DEF_KEY_='outTask' THEN t2.out_user \n" +

				" END workname FROM act_hi_taskinst t1 \n" +
				" LEFT JOIN emk_work_order t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

		sql += " order by t1.START_TIME_ desc";

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

	/*@RequestMapping(params="goProcess")
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
	}*/

}
