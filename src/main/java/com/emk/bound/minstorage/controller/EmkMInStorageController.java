package com.emk.bound.minstorage.controller;
import com.emk.bill.materialcontract.entity.EmkMaterialContractEntity;
import com.emk.bill.materialcontractdetail.entity.EmkMaterialContractDetailEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.bound.minstorage.service.EmkMInStorageServiceI;

import java.io.InputStream;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import com.emk.product.product.entity.EmkProductEntity;
import com.emk.storage.instorage.entity.EmkInStorageEntity;
import com.emk.storage.instoragedetail.entity.EmkInStorageDetailEntity;
import com.emk.storage.storage.entity.EmkStorageEntity;
import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import com.emk.util.ParameterUtil;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
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
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
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
 * @Description: 入库申请表
 * @author onlineGenerator
 * @date 2018-09-15 11:33:47
 * @version V1.0   
 *
 */
@Api(value="EmkMInStorage",description="入库申请表",tags="emkMInStorageController")
@Controller
@RequestMapping("/emkMInStorageController")
public class EmkMInStorageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkMInStorageController.class);

	@Autowired
	private EmkMInStorageServiceI emkMInStorageService;
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
	 * 入库申请表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorageList");
	}

	@RequestMapping(params={"emkMInStorageDetailList"})
	public ModelAndView rkglMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if ((map.get("inStorageId") != null) && (!map.get("inStorageId").equals(""))){
			List<EmkMInStorageDetailEntity> rkglMxEntities = systemService.findHql("from EmkMInStorageDetailEntity where inStorageId=?", new Object[] { map.get("inStorageId") });
			request.setAttribute("rkglMxList", rkglMxEntities);
		}
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorageDetailList");
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
	public void datagrid(EmkMInStorageEntity emkMInStorage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkMInStorageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkMInStorage, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		emkMInStorageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除入库申请表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkMInStorageEntity emkMInStorage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkMInStorage = systemService.getEntity(EmkMInStorageEntity.class, emkMInStorage.getId());
		message = "入库申请表删除成功";
		try{
			emkMInStorageService.delete(emkMInStorage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "入库申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除入库申请表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库申请表删除成功";
		try{
			for(String id:ids.split(",")){
				EmkMInStorageEntity emkMInStorage = systemService.getEntity(EmkMInStorageEntity.class, id);
				systemService.executeSql("delete from emk_m_in_storage_detail where inStorageId=?", id);
				emkMInStorageService.delete(emkMInStorage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "入库申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加入库申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkMInStorageEntity emkMInStorage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库申请表添加成功";
		try{
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			emkMInStorage.setState("0");
			emkMInStorage.setAppler(map.get("realName"));
			emkMInStorage.setApplerId(map.get("userName"));
			emkMInStorage.setRker(map.get("sender"));
			emkMInStorage.setRkerId(map.get("senderUserNames"));

			emkMInStorageService.save(emkMInStorage);
			String dataRows = (String)map.get("dataRowsVal");
			if ((dataRows != null) && (!dataRows.isEmpty())) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkMInStorageDetailEntity rkglMxEntity = new EmkMInStorageDetailEntity();
					if (map.get("rkglMxList["+i+"].proName") != null && !map.get("rkglMxList["+i+"].proName").isEmpty()) {
						rkglMxEntity.setInStorageId(emkMInStorage.getId());
						rkglMxEntity.setProZnName((String)map.get("rkglMxList[" + i + "].proName"));
						rkglMxEntity.setProNum((String)map.get("rkglMxList[" + i + "].proNum"));
						rkglMxEntity.setBrand((String)map.get("rkglMxList[" + i + "].brand"));
						rkglMxEntity.setTotal(map.get("rkglMxList[" + i + "].total"));
						rkglMxEntity.setHtTotal(map.get("rkglMxList[" + i + "].htTotal"));
						rkglMxEntity.setInTotal(map.get("rkglMxList[" + i + "].inTotal"));
						rkglMxEntity.setActualTotal(map.get("rkglMxList[" + i + "].actualTotal"));
						systemService.save(rkglMxEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "入库申请表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新入库申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkMInStorageEntity emkMInStorage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库申请表更新成功";
		EmkMInStorageEntity t = emkMInStorageService.get(EmkMInStorageEntity.class, emkMInStorage.getId());
		try {
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

			emkMInStorage.setState("0");
			emkMInStorage.setAppler(map.get("realName"));
			emkMInStorage.setApplerId(map.get("userName"));
			emkMInStorage.setRker(map.get("sender"));
			emkMInStorage.setRkerId(map.get("senderUserNames"));
			MyBeanUtils.copyBeanNotNull2Bean(emkMInStorage, t);
			emkMInStorageService.saveOrUpdate(t);
			systemService.executeSql("delete from emk_m_in_storage_detail where inStorageId=?", t.getId());

			String dataRows = (String)map.get("dataRowsVal");
			if ((dataRows != null) && (!dataRows.isEmpty())) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkMInStorageDetailEntity rkglMxEntity = new EmkMInStorageDetailEntity();
					if (map.get("rkglMxList["+i+"].proName") != null && !map.get("rkglMxList["+i+"].proName").isEmpty()) {
						rkglMxEntity.setInStorageId(emkMInStorage.getId());
						rkglMxEntity.setProZnName((String)map.get("rkglMxList[" + i + "].proName"));
						rkglMxEntity.setProNum((String)map.get("rkglMxList[" + i + "].proNum"));
						rkglMxEntity.setBrand((String)map.get("rkglMxList[" + i + "].brand"));
						rkglMxEntity.setTotal(map.get("rkglMxList[" + i + "].total"));
						rkglMxEntity.setHtTotal(map.get("rkglMxList[" + i + "].htTotal"));
						rkglMxEntity.setInTotal(map.get("rkglMxList[" + i + "].inTotal"));
						rkglMxEntity.setActualTotal(map.get("rkglMxList[" + i + "].actualTotal"));
						systemService.save(rkglMxEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库申请表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 入库申请表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkMInStorageEntity emkMInStorage, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
		if (StringUtil.isNotEmpty(emkMInStorage.getId())) {
			emkMInStorage = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorage.getId());
			req.setAttribute("emkMInStoragePage", emkMInStorage);
		}
		req.getSession().setAttribute("orderFinish", "");

		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-add");
	}
	/**
	 * 入库申请表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkMInStorageEntity emkMInStorage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkMInStorage.getId())) {
			emkMInStorage = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorage.getId());
			req.setAttribute("emkMInStoragePage", emkMInStorage);
		}
		req.getSession().setAttribute("orderFinish", "");

		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-update");
	}


	@RequestMapping(params="goUpdate2")
	public ModelAndView goUpdate2(EmkMInStorageEntity emkMInStorage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkMInStorage.getId())) {
			emkMInStorage = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorage.getId());
			req.setAttribute("emkMInStoragePage", emkMInStorage);
		}
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-update2");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkMInStorageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkMInStorageEntity emkMInStorage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkMInStorageEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkMInStorage, request.getParameterMap());
		List<EmkMInStorageEntity> emkMInStorages = emkMInStorageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"入库申请表");
		modelMap.put(NormalExcelConstants.CLASS,EmkMInStorageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("入库申请表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkMInStorages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkMInStorageEntity emkMInStorage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"入库申请表");
    	modelMap.put(NormalExcelConstants.CLASS,EmkMInStorageEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("入库申请表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkMInStorageEntity> listEmkMInStorageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkMInStorageEntity.class,params);
				for (EmkMInStorageEntity emkMInStorage : listEmkMInStorageEntitys) {
					emkMInStorageService.save(emkMInStorage);
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
	@ApiOperation(value="入库申请表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkMInStorageEntity>> list() {
		List<EmkMInStorageEntity> listEmkMInStorages=emkMInStorageService.getList(EmkMInStorageEntity.class);
		return Result.success(listEmkMInStorages);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取入库申请表信息",notes="根据ID获取入库申请表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkMInStorageEntity task = emkMInStorageService.get(EmkMInStorageEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取入库申请表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建入库申请表")
	public ResponseMessage<?> create(@ApiParam(name="入库申请表对象")@RequestBody EmkMInStorageEntity emkMInStorage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkMInStorageEntity>> failures = validator.validate(emkMInStorage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkMInStorageService.save(emkMInStorage);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("入库申请表信息保存失败");
		}
		return Result.success(emkMInStorage);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新入库申请表",notes="更新入库申请表")
	public ResponseMessage<?> update(@ApiParam(name="入库申请表对象")@RequestBody EmkMInStorageEntity emkMInStorage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkMInStorageEntity>> failures = validator.validate(emkMInStorage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkMInStorageService.saveOrUpdate(emkMInStorage);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新入库申请表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新入库申请表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除入库申请表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkMInStorageService.deleteEntityById(EmkMInStorageEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("入库申请表删除失败");
		}

		return Result.success();
	}

	@RequestMapping(params="doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(EmkMInStorageEntity emkMInStorageEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库申请单提交成功";
		try {
			int flag = 0;
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			if ((emkMInStorageEntity.getId() == null) || (emkMInStorageEntity.getId().isEmpty())) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkMInStorageEntity inStorageEntity = systemService.getEntity(EmkMInStorageEntity.class, id);
					if (!inStorageEntity.getState().equals("0")) {
						message = "存在已提交的入库单，请重新选择在提交入库单！";
						j.setSuccess(false);
						flag = 1;
						break;
					}
				}
			}else{
				map.put("ids", emkMInStorageEntity.getId());
			}
			Map<String, Object> variables = new HashMap();
			if (flag == 0) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkMInStorageEntity t = emkMInStorageService.get(EmkMInStorageEntity.class, id);
					t.setState("1");
					variables.put("optUser", t.getId());

					List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
					if (task.size() > 0) {
						Task task1 = (Task)task.get(task.size() - 1);
						if (task1.getTaskDefinitionKey().equals("instorageTask")) {
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("checkTask")) {
							t.setLeader(user.getRealName());
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
							}
						}else if (task1.getTaskDefinitionKey().equals("cwTask")) {
							EmkStorageLogEntity storageLogEntity = new EmkStorageLogEntity();
							List<EmkMInStorageDetailEntity> inStorageDetailEntityList = systemService.findHql("from EmkMInStorageDetailEntity where inStorageId=?",t.getId());
							for(EmkMInStorageDetailEntity inStorageDetailEntity : inStorageDetailEntityList){
								//Map stroageMap = systemService.findOneForJdbc("select id,IFNULL(total,0) total from emk_storage where pro_id=?",user.getCurrentDepart().getOrgCode(),inStorageDetailEntity.getProId(),inStorageDetailEntity.getStorageSetId(),inStorageDetailEntity.getPositionId());
								EmkStorageEntity storageEntity = systemService.findUniqueByProperty(EmkStorageEntity.class,"proNum",inStorageDetailEntity.getProNum());
								storageLogEntity = new EmkStorageLogEntity();
								storageLogEntity.setTotal(inStorageDetailEntity.getActualTotal());
								storageLogEntity.setProZnName(inStorageDetailEntity.getProZnName());
								storageLogEntity.setRkNo("0");
								if(storageEntity == null){
									storageEntity = new EmkStorageEntity();
									storageEntity.setProZnName(inStorageDetailEntity.getProZnName());
									storageEntity.setProNum(inStorageDetailEntity.getProNum());
									storageEntity.setTotal(inStorageDetailEntity.getActualTotal());
									storageEntity.setUnit(inStorageDetailEntity.getUnit());
									storageEntity.setBrand(inStorageDetailEntity.getBrand());
									systemService.save(storageEntity);
									storageLogEntity.setPreTotal("0");
									storageLogEntity.setNextTotal(inStorageDetailEntity.getActualTotal().toString());
								}else{
									storageLogEntity.setPreTotal(storageEntity.getTotal());
									double total = Double.parseDouble(storageEntity.getTotal());
									total = total + Double.parseDouble(inStorageDetailEntity.getActualTotal().toString());
									storageLogEntity.setNextTotal(String.valueOf(total));
									systemService.executeSql("update emk_storage set total = (total+?) where pro_num=?",inStorageDetailEntity.getActualTotal(),inStorageDetailEntity.getProNum());
								}
								systemService.save(storageLogEntity);
							}

							t.setState("2");
							taskService.complete(task1.getId(), variables);
						}
					}else {
						ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("instorage", "emkMInStorage", variables);
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
	public ModelAndView goWork(EmkMInStorageEntity emkMInStorageEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkMInStorageEntity.getId())) {
			emkMInStorageEntity = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorageEntity.getId());
			req.setAttribute("emkInStoragePage", emkMInStorageEntity);
		}
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-work");
	}

	@RequestMapping(params="goTime")
	public ModelAndView goTime(EmkMInStorageEntity emkMInStorageEntity, HttpServletRequest req, DataGrid dataGrid) {
		String sql = "";String countsql = "";
		Map map = ParameterUtil.getParamMaps(req.getParameterMap());

		sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
				" WHEN t1.TASK_DEF_KEY_='instorageTask' THEN t2.create_name \n" +
				" WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
				" WHEN t1.TASK_DEF_KEY_='cwTask' THEN t2.financer\nELSE ''\n" +
				" END workname FROM act_hi_taskinst t1 \n" +
				" LEFT JOIN emk_m_in_storage t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
		emkMInStorageEntity = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorageEntity.getId());
		req.setAttribute("emkInStorage", emkMInStorageEntity);
		return new ModelAndView("com/emk/bound/minstorage/time");

	}

	@RequestMapping(params="goProcess")
	public ModelAndView goProcess(EmkMInStorageEntity emkMInStorageEntity, HttpServletRequest req) {
		EmkMInStorageEntity t = systemService.get(EmkMInStorageEntity.class, emkMInStorageEntity.getId());
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
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-process");

	}

	@RequestMapping(params="process")
	public ModelAndView process(EmkInStorageEntity emkInStorage, HttpServletRequest req) {
		return new ModelAndView("com/emk/bound/minstorage/process");
	}

	@RequestMapping(params="getCurrentProcess")
	@ResponseBody
	public AjaxJson getCurrentProcess(EmkMInStorageEntity emkMInStorageEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		EmkMInStorageEntity t = systemService.get(EmkMInStorageEntity.class, emkMInStorageEntity.getId());
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
			j.setMsg("入库申请单");
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
		EmkMInStorageEntity t = emkMInStorageService.get(EmkMInStorageEntity.class, map.get("id").toString());
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
