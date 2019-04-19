package com.emk.produce.testprogress.controller;
import com.emk.produce.testcost.entity.EmkTestCostDetailEntity;
import com.emk.produce.testprogress.entity.EmkTestProgressEntity;
import com.emk.produce.testprogress.service.EmkTestProgressServiceI;

import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
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
 * @Description: 测试进度更新表
 * @author onlineGenerator
 * @date 2018-10-27 10:49:10
 * @version V1.0   
 *
 */
@Api(value="EmkTestProgress",description="测试进度更新表",tags="emkTestProgressController")
@Controller
@RequestMapping("/emkTestProgressController")
public class EmkTestProgressController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkTestProgressController.class);

	@Autowired
	private EmkTestProgressServiceI emkTestProgressService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 测试进度更新表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/produce/testprogress/emkTestProgressList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='testtype'");
		request.setAttribute("testTypeList", list);
		if (Utils.notEmpty(map.get("costId"))) {
			List<EmkTestCostDetailEntity> emkTestCostDetailEntities = systemService.findHql("from EmkTestCostDetailEntity where testCostId=?", map.get("costId"));
			request.setAttribute("emkTestCostDetailEntities", emkTestCostDetailEntities);
		}
		return new ModelAndView("com/emk/produce/testprogress/detailMxList");
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
	public void datagrid(EmkTestProgressEntity emkTestProgress,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkTestProgressEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkTestProgress, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			Map roleMap = (Map) request.getSession().getAttribute("ROLE");
			if(roleMap != null){
				if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")|| roleMap.get("rolecode").toString().contains("scgdy")){
					cq.eq("createBy",user.getUserName());
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkTestProgressService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除测试进度更新表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkTestProgressEntity emkTestProgress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkTestProgress = systemService.getEntity(EmkTestProgressEntity.class, emkTestProgress.getId());
		message = "测试进度更新表删除成功";
		try{
			emkTestProgressService.delete(emkTestProgress);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试进度更新表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除测试进度更新表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "测试进度更新表删除成功";
		try{
			for(String id:ids.split(",")){
				EmkTestProgressEntity emkTestProgress = systemService.getEntity(EmkTestProgressEntity.class, 
				id
				);
				emkTestProgressService.delete(emkTestProgress);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "测试进度更新表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加测试进度更新表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkTestProgressEntity emkTestProgress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "测试进度更新表添加成功";
		try{
			emkTestProgressService.save(emkTestProgress);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试进度更新表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新测试进度更新表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkTestProgressEntity emkTestProgress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "测试进度更新表更新成功";
		Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkTestProgressEntity t = emkTestProgressService.get(EmkTestProgressEntity.class, map.get("testCostId"));
		try {
			emkTestProgress.setId(null);
			MyBeanUtils.copyBeanNotNull2Bean(emkTestProgress, t);
			emkTestProgressService.saveOrUpdate(t);
			//保存明细数据
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_test_cost_detail where test_cost_id = ? ",t.getId());

				int rows = Integer.parseInt(dataRows);
				EmkTestCostDetailEntity emkTestCostDetailEntity = null;
				for (int i = 0; i < rows; i++) {
					if (Utils.notEmpty(map.get("orderMxList["+i+"].testNo00"))){
						emkTestCostDetailEntity = new EmkTestCostDetailEntity();
						emkTestCostDetailEntity.setTestCostId(t.getId());
						emkTestCostDetailEntity.setTestNo(map.get("orderMxList["+i+"].testNo00"));
						emkTestCostDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkTestCostDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkTestCostDetailEntity.setProduceNum(map.get("orderMxList["+i+"].produceNum00"));
						emkTestCostDetailEntity.setTestType(map.get("orderMxList["+i+"].testType00"));
						emkTestCostDetailEntity.setTestContent(map.get("orderMxList["+i+"].testContent00"));
						emkTestCostDetailEntity.setTestResult(map.get("orderMxList["+i+"].testResult00"));
						emkTestCostDetailEntity.setTestMoney(map.get("orderMxList["+i+"].testMoney00"));
						systemService.save(emkTestCostDetailEntity);
					}
				}
			}

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "测试进度更新表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 测试进度更新表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkTestProgressEntity emkTestProgress, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));

		if (StringUtil.isNotEmpty(emkTestProgress.getId())) {
			emkTestProgress = emkTestProgressService.getEntity(EmkTestProgressEntity.class, emkTestProgress.getId());
			req.setAttribute("emkTestProgressPage", emkTestProgress);
		}
		return new ModelAndView("com/emk/produce/testprogress/emkTestProgress-add");
	}
	/**
	 * 测试进度更新表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkTestProgressEntity emkTestProgress, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkTestProgress.getId())) {
			emkTestProgress = emkTestProgressService.getEntity(EmkTestProgressEntity.class, emkTestProgress.getId());
			req.setAttribute("emkTestProgressPage", emkTestProgress);
		}
		return new ModelAndView("com/emk/produce/testprogress/emkTestProgress-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkTestProgressController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkTestProgressEntity emkTestProgress,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkTestProgressEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkTestProgress, request.getParameterMap());
		List<EmkTestProgressEntity> emkTestProgresss = this.emkTestProgressService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"测试进度更新表");
		modelMap.put(NormalExcelConstants.CLASS,EmkTestProgressEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("测试进度更新表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkTestProgresss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkTestProgressEntity emkTestProgress,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"测试进度更新表");
    	modelMap.put(NormalExcelConstants.CLASS,EmkTestProgressEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("测试进度更新表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkTestProgressEntity> listEmkTestProgressEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkTestProgressEntity.class,params);
				for (EmkTestProgressEntity emkTestProgress : listEmkTestProgressEntitys) {
					emkTestProgressService.save(emkTestProgress);
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
	@ApiOperation(value="测试进度更新表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkTestProgressEntity>> list() {
		List<EmkTestProgressEntity> listEmkTestProgresss=emkTestProgressService.getList(EmkTestProgressEntity.class);
		return Result.success(listEmkTestProgresss);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取测试进度更新表信息",notes="根据ID获取测试进度更新表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkTestProgressEntity task = emkTestProgressService.get(EmkTestProgressEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取测试进度更新表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建测试进度更新表")
	public ResponseMessage<?> create(@ApiParam(name="测试进度更新表对象")@RequestBody EmkTestProgressEntity emkTestProgress, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkTestProgressEntity>> failures = validator.validate(emkTestProgress);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkTestProgressService.save(emkTestProgress);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("测试进度更新表信息保存失败");
		}
		return Result.success(emkTestProgress);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新测试进度更新表",notes="更新测试进度更新表")
	public ResponseMessage<?> update(@ApiParam(name="测试进度更新表对象")@RequestBody EmkTestProgressEntity emkTestProgress) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkTestProgressEntity>> failures = validator.validate(emkTestProgress);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkTestProgressService.saveOrUpdate(emkTestProgress);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新测试进度更新表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新测试进度更新表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除测试进度更新表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkTestProgressService.deleteEntityById(EmkTestProgressEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("测试进度更新表删除失败");
		}

		return Result.success();
	}
}
