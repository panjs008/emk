package com.emk.check.qualitycheck.controller;
import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.check.qualitycheck.entity.EmkQualityCheckEntity;
import com.emk.check.qualitycheck.entity.EmkQualityImageEntity;
import com.emk.check.qualitycheck.service.EmkQualityCheckServiceI;

import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.check.sizecheck.entity.EmkSizeCheckDetailEntity;
import com.emk.check.sizecheck.entity.EmkSizeCheckEntity;
import com.emk.check.sizecheck.entity.EmkSizeEntity;
import com.emk.produce.invoices.entity.EmkInvoicesDetailEntity;
import com.emk.produce.outforum.entity.EmkOutForumEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.util.*;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
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
 * @Description: 质量检查表
 * @author onlineGenerator
 * @date 2018-09-24 17:44:09
 * @version V1.0   
 *
 */
@Api(value="EmkQualityCheck",description="质量检查表",tags="emkQualityCheckController")
@Controller
@RequestMapping("/emkQualityCheckController")
public class EmkQualityCheckController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkQualityCheckController.class);

	@Autowired
	private EmkQualityCheckServiceI emkQualityCheckService;
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
	 * 质量检查表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/check/qualitycheck/emkQualityCheckList");
	}
	@RequestMapping(params = "orderMxList")
	public ModelAndView orderMxList(HttpServletRequest request) {
		List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
		request.setAttribute("categoryEntityList", codeList);
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
			List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", map.get("proOrderId"));
			request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
		}
		return new ModelAndView("com/emk/check/qualitycheck/orderMxList");
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
	public void datagrid(EmkQualityCheckEntity emkQualityCheck,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkQualityCheckEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkQualityCheck, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			Map roleMap = (Map) request.getSession().getAttribute("ROLE");
			if(roleMap != null){
				if(roleMap.get("rolecode").toString().contains("zjy")){
					cq.eq("createBy",user.getUserName());
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		emkQualityCheckService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除质量检查表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkQualityCheck = systemService.getEntity(EmkQualityCheckEntity.class, emkQualityCheck.getId());
		message = "质量检查表删除成功";
		try{
			emkQualityCheckService.delete(emkQualityCheck);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "质量检查表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除质量检查表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "质量检查表删除成功";
		try{
			for(String id:ids.split(",")){
				EmkQualityCheckEntity emkQualityCheck = systemService.getEntity(EmkQualityCheckEntity.class, id);
				List<EmkQualityImageEntity> emkQualityImageEntities = systemService.findHql("from EmkQualityImageEntity where qualityId=?",id);
				for(EmkQualityImageEntity emkQualityImageEntity : emkQualityImageEntities){
					WebFileUtils.delete( request.getRealPath("/")+emkQualityImageEntity.getImageUrl());
					systemService.delete(emkQualityImageEntity);
				}

				EmkSizeCheckEntity t = systemService.findUniqueByProperty(EmkSizeCheckEntity.class,"qualityCheckId",id);
				if(Utils.notEmpty(t)){
					systemService.executeSql("delete from emk_size_total where FIND_IN_SET(p_id,(SELECT GROUP_CONCAT(id) FROM emk_size_detail where size_check_id=?))", t.getId());
					systemService.executeSql("delete from emk_size_detail where size_check_id=?", t.getId());
					systemService.executeSql("delete from emk_size where form_id=?", t.getId());
					systemService.delete(t);
				}
				emkQualityCheckService.delete(emkQualityCheck);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "质量检查表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加质量检查表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "质量检查表添加成功";
		Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		try{
			/*EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",emkQualityCheck.getWorkNo());
			if(workOrderEntity == null){
				j.setSuccess(false);
				j.setMsg("您输入的工单号有误，请核准后在提交");
				return j;
			}*/
			emkQualityCheck.setState("0");
			emkQualityCheckService.save(emkQualityCheck);
			j.setObj(emkQualityCheck.getId());
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			EmkQualityImageEntity emkQualityImageEntity = null;
			for(int i=1 ; i<=9 ; i++){
				String imageNmae = map.get("imageName"+i);
				String imageUrl = map.get("imageUrl"+i);

				if(Utils.notEmpty(imageUrl)){
					emkQualityImageEntity = new EmkQualityImageEntity();
					emkQualityImageEntity.setUploadId(user.getId());
					emkQualityImageEntity.setUploadTime(DateUtil.getCurrentTimeString(null));
					emkQualityImageEntity.setQualityId(emkQualityCheck.getId());
					emkQualityImageEntity.setSortDesc(String.valueOf(i-1));
					emkQualityImageEntity.setImageName(imageNmae);
					emkQualityImageEntity.setImageUrl(imageUrl);
					systemService.save(emkQualityImageEntity);
				}
			}
			/*if(emkQualityCheck.getCyjg().equals("0")){
				EmkProOrderEntity proOrderEntity = systemService.findUniqueByProperty(EmkProOrderEntity.class,"orderNo",emkQualityCheck.getOrderNo());
				EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",proOrderEntity.getWorkNo());
				workOrderEntity.setSampleCheckNo(emkQualityCheck.getQualityCheckNum());
				systemService.saveOrUpdate(workOrderEntity);
			}*/


			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "质量检查表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新质量检查表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "质量检查表更新成功";
		EmkQualityCheckEntity t = emkQualityCheckService.get(EmkQualityCheckEntity.class, emkQualityCheck.getId());
		try {
			emkQualityCheck.setState("0");
			MyBeanUtils.copyBeanNotNull2Bean(emkQualityCheck, t);
			/*if(emkQualityCheck.getCyjg().equals("0")){
				EmkProOrderEntity proOrderEntity = systemService.findUniqueByProperty(EmkProOrderEntity.class,"orderNo",emkQualityCheck.getOrderNo());
				EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",proOrderEntity.getWorkNo());
				workOrderEntity.setSampleCheckNo(emkQualityCheck.getQualityCheckNum());
				systemService.saveOrUpdate(workOrderEntity);
			}*/
			emkQualityCheckService.saveOrUpdate(t);
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			systemService.executeSql("delete from emk_quality_check_image where quality_id=?", t.getId());
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			EmkQualityImageEntity emkQualityImageEntity = null;
			for(int i=1 ; i<=9 ; i++){
				String imageNmae = map.get("imageName"+i);
				String imageUrl = map.get("imageUrl"+i);

				if(Utils.notEmpty(imageUrl)){
					emkQualityImageEntity = new EmkQualityImageEntity();
					emkQualityImageEntity.setUploadId(user.getId());
					emkQualityImageEntity.setUploadTime(DateUtil.getCurrentTimeString(null));
					emkQualityImageEntity.setQualityId(emkQualityCheck.getId());
					emkQualityImageEntity.setSortDesc(String.valueOf(i-1));
					emkQualityImageEntity.setImageName(imageNmae);
					emkQualityImageEntity.setImageUrl(imageUrl);
					systemService.save(emkQualityImageEntity);
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "质量检查表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "goTab")
	public ModelAndView goBtn(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest req) {
		return new ModelAndView("com/emk/check/qualitycheck/emkQualityCheck-tab");
	}
	@RequestMapping(params = "goBase")
	public ModelAndView goBase(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
		/*List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
		req.setAttribute("categoryEntityList", codeList);*/
		TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");
		Map orderNum = systemService.findOneForJdbc("select count(0)+1 orderNum from emk_quality_check where sys_org_code=?", user.getCurrentDepart().getOrgCode());
		req.setAttribute("qualityCheckNum","ZLJC" + DateUtils.format(new Date(), "yyMMdd") + String.format("%04d", Integer.parseInt(orderNum.get("orderNum").toString())));
		if (StringUtil.isNotEmpty(emkQualityCheck.getId())) {
			emkQualityCheck = emkQualityCheckService.getEntity(EmkQualityCheckEntity.class, emkQualityCheck.getId());
			req.setAttribute("emkQualityCheckPage", emkQualityCheck);
			try {
				Map countMap = MyBeanUtils.culBeanCounts(emkQualityCheck);
				req.setAttribute("countMap", countMap);
				double a=0,b=0;
				a = Double.parseDouble(countMap.get("finishColums").toString());
				b = Double.parseDouble(countMap.get("Colums").toString());
				DecimalFormat df = new DecimalFormat("#.00");
				req.setAttribute("recent", df.format(a*100/b));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("com/emk/check/qualitycheck/emkQualityCheck-base");
	}

	/**
	 * 图片页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goImage")
	public ModelAndView goImage(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkQualityCheck.getId())) {
			List<EmkQualityImageEntity> emkQualityImageEntities = systemService.findHql("from EmkQualityImageEntity where qualityId=?",emkQualityCheck.getId());
			req.setAttribute("emkQualityImageEntities", emkQualityImageEntities);
		}
		return new ModelAndView("com/emk/check/qualitycheck/emkQualityCheck-image");
	}

	/**
	 * 质量检查表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest req) {

		if (StringUtil.isNotEmpty(emkQualityCheck.getId())) {
			emkQualityCheck = emkQualityCheckService.getEntity(EmkQualityCheckEntity.class, emkQualityCheck.getId());
			req.setAttribute("emkQualityCheckPage", emkQualityCheck);
		}
		return new ModelAndView("com/emk/check/qualitycheck/emkQualityCheck-add");
	}
	/**
	 * 质量检查表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest req) {
		/*List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
		req.setAttribute("categoryEntityList", codeList);*/
		if (StringUtil.isNotEmpty(emkQualityCheck.getId())) {
			emkQualityCheck = emkQualityCheckService.getEntity(EmkQualityCheckEntity.class, emkQualityCheck.getId());
			req.setAttribute("emkQualityCheckPage", emkQualityCheck);
		}
		return new ModelAndView("com/emk/check/qualitycheck/emkQualityCheck-update");
	}
	@RequestMapping(params = "goUpdate2")
	public ModelAndView goUpdate2(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest req) {
		List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
		req.setAttribute("categoryEntityList", codeList);
		if (StringUtil.isNotEmpty(emkQualityCheck.getId())) {
			emkQualityCheck = emkQualityCheckService.getEntity(EmkQualityCheckEntity.class, emkQualityCheck.getId());
			req.setAttribute("emkQualityCheckPage", emkQualityCheck);
		}
		return new ModelAndView("com/emk/check/qualitycheck/emkQualityCheck-update2");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkQualityCheckController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkQualityCheckEntity emkQualityCheck,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkQualityCheckEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkQualityCheck, request.getParameterMap());
		List<EmkQualityCheckEntity> emkQualityChecks = emkQualityCheckService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"质量检查表");
		modelMap.put(NormalExcelConstants.CLASS,EmkQualityCheckEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("质量检查表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkQualityChecks);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkQualityCheckEntity emkQualityCheck,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"质量检查表");
    	modelMap.put(NormalExcelConstants.CLASS,EmkQualityCheckEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("质量检查表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkQualityCheckEntity> listEmkQualityCheckEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkQualityCheckEntity.class,params);
				for (EmkQualityCheckEntity emkQualityCheck : listEmkQualityCheckEntitys) {
					emkQualityCheckService.save(emkQualityCheck);
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
	@ApiOperation(value="质量检查表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkQualityCheckEntity>> list() {
		List<EmkQualityCheckEntity> listEmkQualityChecks=emkQualityCheckService.getList(EmkQualityCheckEntity.class);
		return Result.success(listEmkQualityChecks);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取质量检查表信息",notes="根据ID获取质量检查表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkQualityCheckEntity task = emkQualityCheckService.get(EmkQualityCheckEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取质量检查表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建质量检查表")
	public ResponseMessage<?> create(@ApiParam(name="质量检查表对象")@RequestBody EmkQualityCheckEntity emkQualityCheck, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkQualityCheckEntity>> failures = validator.validate(emkQualityCheck);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkQualityCheckService.save(emkQualityCheck);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("质量检查表信息保存失败");
		}
		return Result.success(emkQualityCheck);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新质量检查表",notes="更新质量检查表")
	public ResponseMessage<?> update(@ApiParam(name="质量检查表对象")@RequestBody EmkQualityCheckEntity emkQualityCheck) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkQualityCheckEntity>> failures = validator.validate(emkQualityCheck);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkQualityCheckService.saveOrUpdate(emkQualityCheck);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新质量检查表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新质量检查表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除质量检查表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkQualityCheckService.deleteEntityById(EmkQualityCheckEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("质量检查表删除失败");
		}

		return Result.success();
	}

	@RequestMapping(params="doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "质量检查单提交成功";
		try {
			int flag = 0;
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			if ((emkQualityCheck.getId() == null) || (emkQualityCheck.getId().isEmpty())) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkQualityCheckEntity qualityCheckEntity = systemService.getEntity(EmkQualityCheckEntity.class, id);
					if (!qualityCheckEntity.getState().equals("0")) {
						message = "存在已提交的质量检查单，请重新选择在提交质量检查单！";
						j.setSuccess(false);
						flag = 1;
						break;
					}
				}
			}else{
				map.put("ids", emkQualityCheck.getId());
			}
			Map<String, Object> variables = new HashMap();
			if (flag == 0) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkQualityCheckEntity t = emkQualityCheckService.get(EmkQualityCheckEntity.class, id);
					t.setState("1");
					variables.put("inputUser", t.getId());

					List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
					if (task.size() > 0) {
						Task task1 = (Task)task.get(task.size() - 1);
						if (task1.getTaskDefinitionKey().equals("qualitycheckTask")) {
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("leadTask")) {
							t.setLeader(user.getRealName());
							t.setLeadUserId(user.getId());
							t.setLeadAdvice(emkQualityCheck.getLeadAdvice());
							if (emkQualityCheck.getIsPass().equals("0")) {
								variables.put("isPass", "0");
								taskService.complete(task1.getId(), variables);

								if(t.getCyjg().equals("0")){
									EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",t.getWorkNo());
									if(workOrderEntity.getSampleCheckUserId() == null || workOrderEntity.getSampleCheckUserId().isEmpty()){
										workOrderEntity.setSampleUserId(user.getUserName());
										workOrderEntity.setSampleCheckUser(user.getRealName());
										workOrderEntity.setSampleCheckDate(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
										workOrderEntity.setSampleCheckAdvice(emkQualityCheck.getLeadAdvice());

										systemService.saveOrUpdate(workOrderEntity);

										task = taskService.createTaskQuery().taskAssignee(workOrderEntity.getId()).list();
										task1 = (Task)task.get(task.size() - 1);
										variables.put("inputUser", workOrderEntity.getId());
										taskService.complete(task1.getId(), variables);
									}
									/*if(workOrderEntity.getCheckUser() == null || workOrderEntity.getCheckUser().isEmpty()){
										workOrderEntity.setCheckUserId(user.getUserName());
										workOrderEntity.setCheckUser(user.getRealName());
										workOrderEntity.setCaiwuDate(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
										workOrderEntity.setCheckAdvice(emkQualityCheck.getLeadAdvice());
									}*/

								}
								t.setState("2");

							} else {
								List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(t.getId()).list();

								List<Task> taskList = taskService.createTaskQuery().taskAssignee(t.getId()).list();
								if (taskList.size() > 0) {
									Task taskH = (Task)taskList.get(taskList.size() - 1);
									HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
									FlowUtil.turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
									Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC",  t.getId(), historicTaskInstance.getTaskDefinitionKey());
									String[] activitIdArr = activityMap.get("ids").toString().split(",");
									String[] taskIdArr = activityMap.get("taskids").toString().split(",");
									systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(), taskIdArr[1]);
									systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", activitIdArr[0], activitIdArr[1] );
								}
								t.setState("0");
							}
						}
					}else {
						ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("qualitycheck", "emkQualityCheckEntity", variables);
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
			message = "质量检查单提交失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	@RequestMapping(params="goWork")
	public ModelAndView goWork(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkQualityCheck.getId())) {
			emkQualityCheck = emkQualityCheckService.getEntity(EmkQualityCheckEntity.class, emkQualityCheck.getId());
			req.setAttribute("emkQualityCheck", emkQualityCheck);
		}
		return new ModelAndView("com/emk/check/qualitycheck/emkQualityCheck-work");
	}

	@RequestMapping(params="goTime")
	public ModelAndView goTime(EmkQualityCheckEntity emkQualityCheck, HttpServletRequest req, DataGrid dataGrid) {
		String sql = "";String countsql = "";
		Map map = ParameterUtil.getParamMaps(req.getParameterMap());

		sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
				" WHEN t1.TASK_DEF_KEY_='qualitycheckTask' THEN t2.create_name \n" +
				" WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
				" END workname FROM act_hi_taskinst t1 \n" +
				" LEFT JOIN emk_quality_check t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
		emkQualityCheck = emkQualityCheckService.getEntity(EmkQualityCheckEntity.class, emkQualityCheck.getId());
		req.setAttribute("emkQualityCheck", emkQualityCheck);
		return new ModelAndView("com/emk/check/qualitycheck/time");
	}
}
