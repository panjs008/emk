package com.emk.storage.customuse.controller;
import com.emk.storage.customuse.entity.EmkCustomUseEntity;
import com.emk.storage.customuse.service.EmkCustomUseServiceI;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntityA;
import com.emk.util.*;
import org.apache.log4j.Logger;
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
 * @Description: 客户手册
 * @author onlineGenerator
 * @date 2018-10-26 14:58:45
 * @version V1.0   
 *
 */
@Api(value="EmkCustomUse",description="客户手册",tags="emkCustomUseController")
@Controller
@RequestMapping("/emkCustomUseController")
public class EmkCustomUseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkCustomUseController.class);

	@Autowired
	private EmkCustomUseServiceI emkCustomUseService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 客户手册列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/storage/customuse/emkCustomUseList");
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
	public void datagrid(EmkCustomUseEntity emkCustomUse,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkCustomUseEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkCustomUse, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			Map roleMap = (Map) request.getSession().getAttribute("ROLE");
			if(roleMap != null){
				if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
					cq.eq("createBy",user.getUserName());
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkCustomUseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除客户手册
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkCustomUseEntity emkCustomUse, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkCustomUse = systemService.getEntity(EmkCustomUseEntity.class, emkCustomUse.getId());
		message = "客户手册删除成功";
		try{
			emkCustomUseService.delete(emkCustomUse);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户手册删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除客户手册
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户手册删除成功";
		try{
			for(String id:ids.split(",")){
				EmkCustomUseEntity emkCustomUse = systemService.getEntity(EmkCustomUseEntity.class, 
				id
				);
				emkCustomUseService.delete(emkCustomUse);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "客户手册删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加客户手册
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkCustomUseEntity emkCustomUse, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户手册添加成功";
		try{
			emkCustomUse.setKyscbh("KHSCKHDA"+emkCustomUse.getCusNum());
			emkCustomUseService.save(emkCustomUse);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户手册添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新客户手册
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkCustomUseEntity emkCustomUse, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户手册更新成功";
		EmkCustomUseEntity t = emkCustomUseService.get(EmkCustomUseEntity.class, emkCustomUse.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(emkCustomUse, t);
			emkCustomUseService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客户手册更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 客户手册新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkCustomUseEntity emkCustomUse, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkCustomUse.getId())) {
			emkCustomUse = emkCustomUseService.getEntity(EmkCustomUseEntity.class, emkCustomUse.getId());
			req.setAttribute("emkCustomUsePage", emkCustomUse);
		}
		return new ModelAndView("com/emk/storage/customuse/emkCustomUse-add");
	}
	/**
	 * 客户手册编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkCustomUseEntity emkCustomUse, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkCustomUse.getId())) {
			emkCustomUse = emkCustomUseService.getEntity(EmkCustomUseEntity.class, emkCustomUse.getId());
			req.setAttribute("emkCustomUsePage", emkCustomUse);
			try {
				Map countMap = MyBeanUtils.culBeanCounts(emkCustomUse);
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
		return new ModelAndView("com/emk/storage/customuse/emkCustomUse-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkCustomUseController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkCustomUseEntity emkCustomUse,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkCustomUseEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkCustomUse, request.getParameterMap());
		List<EmkCustomUseEntity> emkCustomUses = this.emkCustomUseService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"客户手册");
		modelMap.put(NormalExcelConstants.CLASS,EmkCustomUseEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户手册列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkCustomUses);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkCustomUseEntity emkCustomUse,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"客户手册");
    	modelMap.put(NormalExcelConstants.CLASS,EmkCustomUseEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户手册列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkCustomUseEntity> listEmkCustomUseEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkCustomUseEntity.class,params);
				for (EmkCustomUseEntity emkCustomUse : listEmkCustomUseEntitys) {
					emkCustomUseService.save(emkCustomUse);
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
	@ApiOperation(value="客户手册列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkCustomUseEntity>> list() {
		List<EmkCustomUseEntity> listEmkCustomUses=emkCustomUseService.getList(EmkCustomUseEntity.class);
		return Result.success(listEmkCustomUses);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取客户手册信息",notes="根据ID获取客户手册信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkCustomUseEntity task = emkCustomUseService.get(EmkCustomUseEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取客户手册信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建客户手册")
	public ResponseMessage<?> create(@ApiParam(name="客户手册对象")@RequestBody EmkCustomUseEntity emkCustomUse, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkCustomUseEntity>> failures = validator.validate(emkCustomUse);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkCustomUseService.save(emkCustomUse);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("客户手册信息保存失败");
		}
		return Result.success(emkCustomUse);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新客户手册",notes="更新客户手册")
	public ResponseMessage<?> update(@ApiParam(name="客户手册对象")@RequestBody EmkCustomUseEntity emkCustomUse) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkCustomUseEntity>> failures = validator.validate(emkCustomUse);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkCustomUseService.saveOrUpdate(emkCustomUse);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新客户手册信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新客户手册信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除客户手册")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkCustomUseService.deleteEntityById(EmkCustomUseEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("客户手册删除失败");
		}

		return Result.success();
	}
}
