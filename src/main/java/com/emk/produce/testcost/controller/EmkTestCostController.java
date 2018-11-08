package com.emk.produce.testcost.controller;
import com.emk.produce.test.entity.EmkTestEntity;
import com.emk.produce.testcost.entity.EmkTestCostEntity;
import com.emk.produce.testcost.service.EmkTestCostServiceI;

import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
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
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.jeecgframework.core.util.ExceptionUtil;

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
 * @Description: 测试费用付款申请
 * @author onlineGenerator
 * @date 2018-10-27 10:49:20
 * @version V1.0   
 *
 */
@Api(value="EmkTestCost",description="测试费用付款申请",tags="emkTestCostController")
@Controller
@RequestMapping("/emkTestCostController")
public class EmkTestCostController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkTestCostController.class);

	@Autowired
	private EmkTestCostServiceI emkTestCostService;
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
	 * 测试费用付款申请列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/produce/testcost/emkTestCostList");
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
	public void datagrid(EmkTestCostEntity emkTestCost,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkTestCostEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkTestCost, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkTestCostService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除测试费用付款申请
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkTestCostEntity emkTestCost, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkTestCost = systemService.getEntity(EmkTestCostEntity.class, emkTestCost.getId());
		message = "测试费用付款申请删除成功";
		try{
			emkTestCostService.delete(emkTestCost);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试费用付款申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除测试费用付款申请
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "测试费用付款申请删除成功";
		try{
			for(String id:ids.split(",")){
				EmkTestCostEntity emkTestCost = systemService.getEntity(EmkTestCostEntity.class, 
				id
				);
				emkTestCostService.delete(emkTestCost);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "测试费用付款申请删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加测试费用付款申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkTestCostEntity emkTestCost, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "测试费用付款申请添加成功";
		try{
			emkTestCost.setState("0");
			Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(COST_NO, 3)),0)+1 AS signed) orderNum from emk_test_cost");
			emkTestCost.setCostNo("CSFY" + emkTestCost.getCusNum() + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", Integer.parseInt(orderNum.get("orderNum").toString())));

			emkTestCostService.save(emkTestCost);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试费用付款申请添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新测试费用付款申请
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkTestCostEntity emkTestCost, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "测试费用付款申请更新成功";
		EmkTestCostEntity t = emkTestCostService.get(EmkTestCostEntity.class, emkTestCost.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(emkTestCost, t);
			emkTestCostService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "测试费用付款申请更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 测试费用付款申请新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkTestCostEntity emkTestCost, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));

		if (StringUtil.isNotEmpty(emkTestCost.getId())) {
			emkTestCost = emkTestCostService.getEntity(EmkTestCostEntity.class, emkTestCost.getId());
			req.setAttribute("emkTestCostPage", emkTestCost);
		}
		return new ModelAndView("com/emk/produce/testcost/emkTestCost-add");
	}
	/**
	 * 测试费用付款申请编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkTestCostEntity emkTestCost, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkTestCost.getId())) {
			emkTestCost = emkTestCostService.getEntity(EmkTestCostEntity.class, emkTestCost.getId());
			req.setAttribute("emkTestCostPage", emkTestCost);
		}
		return new ModelAndView("com/emk/produce/testcost/emkTestCost-update");
	}

	@RequestMapping(params = "goUpdate2")
	public ModelAndView goUpdate2(EmkTestCostEntity emkTestCost, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkTestCost.getId())) {
			emkTestCost = emkTestCostService.getEntity(EmkTestCostEntity.class, emkTestCost.getId());
			req.setAttribute("emkTestCostPage", emkTestCost);
		}
		return new ModelAndView("com/emk/produce/testcost/emkTestCost-update2");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkTestCostController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkTestCostEntity emkTestCost,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkTestCostEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkTestCost, request.getParameterMap());
		List<EmkTestCostEntity> emkTestCosts = this.emkTestCostService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"测试费用付款申请");
		modelMap.put(NormalExcelConstants.CLASS,EmkTestCostEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("测试费用付款申请列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkTestCosts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkTestCostEntity emkTestCost,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"测试费用付款申请");
    	modelMap.put(NormalExcelConstants.CLASS,EmkTestCostEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("测试费用付款申请列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkTestCostEntity> listEmkTestCostEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkTestCostEntity.class,params);
				for (EmkTestCostEntity emkTestCost : listEmkTestCostEntitys) {
					emkTestCostService.save(emkTestCost);
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
	@ApiOperation(value="测试费用付款申请列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkTestCostEntity>> list() {
		List<EmkTestCostEntity> listEmkTestCosts=emkTestCostService.getList(EmkTestCostEntity.class);
		return Result.success(listEmkTestCosts);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取测试费用付款申请信息",notes="根据ID获取测试费用付款申请信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkTestCostEntity task = emkTestCostService.get(EmkTestCostEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取测试费用付款申请信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建测试费用付款申请")
	public ResponseMessage<?> create(@ApiParam(name="测试费用付款申请对象")@RequestBody EmkTestCostEntity emkTestCost, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkTestCostEntity>> failures = validator.validate(emkTestCost);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkTestCostService.save(emkTestCost);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("测试费用付款申请信息保存失败");
		}
		return Result.success(emkTestCost);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新测试费用付款申请",notes="更新测试费用付款申请")
	public ResponseMessage<?> update(@ApiParam(name="测试费用付款申请对象")@RequestBody EmkTestCostEntity emkTestCost) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkTestCostEntity>> failures = validator.validate(emkTestCost);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkTestCostService.saveOrUpdate(emkTestCost);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新测试费用付款申请信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新测试费用付款申请信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除测试费用付款申请")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkTestCostService.deleteEntityById(EmkTestCostEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("测试费用付款申请删除失败");
		}

		return Result.success();
	}

	@RequestMapping(params="doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(EmkTestCostEntity emkTestCostEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "测试费用付款申请表提交成功";
		try {
			int flag = 0;
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			if ((emkTestCostEntity.getId() == null) || (emkTestCostEntity.getId().isEmpty())) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkTestCostEntity testCostEntity = systemService.getEntity(EmkTestCostEntity.class, id);
					if (!testCostEntity.getState().equals("0")) {
						message = "存在已提交的测试费用付款申请表，请重新选择在提交！";
						j.setSuccess(false);
						flag = 1;
						break;
					}
				}
			}else{
				map.put("ids", emkTestCostEntity.getId());
			}
			Map<String, Object> variables = new HashMap();
			if (flag == 0) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkTestCostEntity t = emkTestCostService.get(EmkTestCostEntity.class, id);
					t.setState("1");
					variables.put("optUser", t.getId());

					List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
					if (task.size() > 0) {
						Task task1 = (Task)task.get(task.size() - 1);
						if (task1.getTaskDefinitionKey().equals("testcostTask")) {
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("checkTask")) {
							t.setLeader(user.getRealName());
							t.setLeadUserId(user.getId());
							t.setLeadAdvice(emkTestCostEntity.getLeadAdvice());
							if (t.getIsPass().equals("0")) {
								variables.put("isPass", emkTestCostEntity.getIsPass());
								taskService.complete(task1.getId(), variables);
							} else {
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

						}
						if (task1.getTaskDefinitionKey().equals("cwTask")) {
							t.setState("2");
							this.taskService.complete(task1.getId(), variables);

						}
					}else {
						ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("testcost", "emkTestCostEntity", variables);
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
			message = "测试费用付款申请表提交失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params="goWork")
	public ModelAndView goWork(EmkTestCostEntity emkTestCostEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkTestCostEntity.getId())) {
			emkTestCostEntity = emkTestCostService.getEntity(EmkTestCostEntity.class, emkTestCostEntity.getId());
			req.setAttribute("emkTestEntity", emkTestCostEntity);
		}
		return new ModelAndView("com/emk/produce/testcost/emkTestCost-work");

	}

	@RequestMapping(params="goTime")
	public ModelAndView goTime(EmkTestCostEntity emkTestCostEntity, HttpServletRequest req, DataGrid dataGrid) {
		String sql = "";String countsql = "";
		Map map = ParameterUtil.getParamMaps(req.getParameterMap());

		sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
				" WHEN t1.TASK_DEF_KEY_='testcostTask' THEN t2.create_name \n" +
				" WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
				" END workname FROM act_hi_taskinst t1 \n" +
				" LEFT JOIN emk_test_cost t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
		emkTestCostEntity = emkTestCostService.getEntity(EmkTestCostEntity.class, emkTestCostEntity.getId());
		req.setAttribute("emkTestCost", emkTestCostEntity);
		return new ModelAndView("com/emk/produce/testcost/time");

	}
}
