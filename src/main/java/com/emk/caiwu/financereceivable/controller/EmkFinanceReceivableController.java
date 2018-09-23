package com.emk.caiwu.financereceivable.controller;
import com.emk.caiwu.financereceivable.entity.EmkFinanceReceivableEntity;
import com.emk.caiwu.financereceivable.service.EmkFinanceReceivableServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.util.ParameterUtil;
import org.apache.log4j.Logger;
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
import java.util.Map;
import java.util.HashMap;
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
 * @Description: 应收通知单
 * @author onlineGenerator
 * @date 2018-09-20 22:26:46
 * @version V1.0   
 *
 */
@Api(value="EmkFinanceReceivable",description="应收通知单",tags="emkFinanceReceivableController")
@Controller
@RequestMapping("/emkFinanceReceivableController")
public class EmkFinanceReceivableController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkFinanceReceivableController.class);

	@Autowired
	private EmkFinanceReceivableServiceI emkFinanceReceivableService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 应收通知单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/caiwu/financereceivable/emkFinanceReceivableList");
	}
	/**
	 * 应付通知单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list1")
	public ModelAndView list1(HttpServletRequest request) {
		return new ModelAndView("com/emk/caiwu/financereceivable/emkFinanceReceivableList1");
	}

	@RequestMapping(params = "orderMxList")
	public ModelAndView orderMxList(HttpServletRequest request) {
		List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
		request.setAttribute("categoryEntityList", codeList);
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
			List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{map.get("proOrderId")});
			request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
		}
		return new ModelAndView("com/emk/caiwu/financereceivable/orderMxList");
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
	public void datagrid(EmkFinanceReceivableEntity emkFinanceReceivable,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkFinanceReceivableEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkFinanceReceivable, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkFinanceReceivableService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除应收通知单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkFinanceReceivableEntity emkFinanceReceivable, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkFinanceReceivable = systemService.getEntity(EmkFinanceReceivableEntity.class, emkFinanceReceivable.getId());
		message = "应收通知单删除成功";
		try{
			emkFinanceReceivableService.delete(emkFinanceReceivable);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "应收通知单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除应收通知单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "应收通知单删除成功";
		try{
			for(String id:ids.split(",")){
				EmkFinanceReceivableEntity emkFinanceReceivable = systemService.getEntity(EmkFinanceReceivableEntity.class, 
				id
				);
				emkFinanceReceivableService.delete(emkFinanceReceivable);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "应收通知单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加应收通知单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkFinanceReceivableEntity emkFinanceReceivable, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "应收通知单添加成功";
		try{
			emkFinanceReceivableService.save(emkFinanceReceivable);
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("dataRowsVal");
			if ((dataRows != null) && (!dataRows.isEmpty())) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
					if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
						orderMxEntity.setEnquiryId(emkFinanceReceivable.getId());
						orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
						orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
						orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
						orderMxEntity.setPrice(Double.valueOf(Double.parseDouble((String) map.get("orderMxList[" + i + "].signPrice"))));
						this.systemService.save(orderMxEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "应收通知单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新应收通知单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkFinanceReceivableEntity emkFinanceReceivable, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "应收通知单更新成功";
		EmkFinanceReceivableEntity t = emkFinanceReceivableService.get(EmkFinanceReceivableEntity.class, emkFinanceReceivable.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(emkFinanceReceivable, t);
			emkFinanceReceivableService.saveOrUpdate(t);
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{t.getId()});
			String dataRows = (String) map.get("dataRowsVal");
			if ((dataRows != null) && (!dataRows.isEmpty())) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
					if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
						orderMxEntity.setEnquiryId(t.getId());
						orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
						orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
						orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
						orderMxEntity.setPrice(Double.valueOf(Double.parseDouble((String) map.get("orderMxList[" + i + "].signPrice"))));
						this.systemService.save(orderMxEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "应收通知单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 应收通知单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkFinanceReceivableEntity emkFinanceReceivable, HttpServletRequest req) {
		List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
		req.setAttribute("categoryEntityList", codeList);

		if (StringUtil.isNotEmpty(emkFinanceReceivable.getId())) {
			emkFinanceReceivable = emkFinanceReceivableService.getEntity(EmkFinanceReceivableEntity.class, emkFinanceReceivable.getId());
			req.setAttribute("emkFinanceReceivablePage", emkFinanceReceivable);
		}
		return new ModelAndView("com/emk/caiwu/financereceivable/emkFinanceReceivable-add");
	}
	/**
	 * 应付通知单新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd1")
	public ModelAndView goAdd1(EmkFinanceReceivableEntity emkFinanceReceivable, HttpServletRequest req) {
		List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
		req.setAttribute("categoryEntityList", codeList);

		if (StringUtil.isNotEmpty(emkFinanceReceivable.getId())) {
			emkFinanceReceivable = emkFinanceReceivableService.getEntity(EmkFinanceReceivableEntity.class, emkFinanceReceivable.getId());
			req.setAttribute("emkFinanceReceivablePage", emkFinanceReceivable);
		}
		return new ModelAndView("com/emk/caiwu/financereceivable/emkFinanceReceivable-add1");
	}
	/**
	 * 应收通知单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkFinanceReceivableEntity emkFinanceReceivable, HttpServletRequest req) {
		List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
		req.setAttribute("categoryEntityList", codeList);

		if (StringUtil.isNotEmpty(emkFinanceReceivable.getId())) {
			emkFinanceReceivable = emkFinanceReceivableService.getEntity(EmkFinanceReceivableEntity.class, emkFinanceReceivable.getId());
			req.setAttribute("emkFinanceReceivablePage", emkFinanceReceivable);
		}
		return new ModelAndView("com/emk/caiwu/financereceivable/emkFinanceReceivable-update");
	}
	/**
	 * 应付通知单编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate1")
	public ModelAndView goUpdate1(EmkFinanceReceivableEntity emkFinanceReceivable, HttpServletRequest req) {
		List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
		req.setAttribute("categoryEntityList", codeList);

		if (StringUtil.isNotEmpty(emkFinanceReceivable.getId())) {
			emkFinanceReceivable = emkFinanceReceivableService.getEntity(EmkFinanceReceivableEntity.class, emkFinanceReceivable.getId());
			req.setAttribute("emkFinanceReceivablePage", emkFinanceReceivable);
		}
		return new ModelAndView("com/emk/caiwu/financereceivable/emkFinanceReceivable-update1");
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkFinanceReceivableController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkFinanceReceivableEntity emkFinanceReceivable,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkFinanceReceivableEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkFinanceReceivable, request.getParameterMap());
		List<EmkFinanceReceivableEntity> emkFinanceReceivables = this.emkFinanceReceivableService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"应收通知单");
		modelMap.put(NormalExcelConstants.CLASS,EmkFinanceReceivableEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("应收通知单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkFinanceReceivables);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkFinanceReceivableEntity emkFinanceReceivable,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"应收通知单");
    	modelMap.put(NormalExcelConstants.CLASS,EmkFinanceReceivableEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("应收通知单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkFinanceReceivableEntity> listEmkFinanceReceivableEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkFinanceReceivableEntity.class,params);
				for (EmkFinanceReceivableEntity emkFinanceReceivable : listEmkFinanceReceivableEntitys) {
					emkFinanceReceivableService.save(emkFinanceReceivable);
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
	@ApiOperation(value="应收通知单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkFinanceReceivableEntity>> list() {
		List<EmkFinanceReceivableEntity> listEmkFinanceReceivables=emkFinanceReceivableService.getList(EmkFinanceReceivableEntity.class);
		return Result.success(listEmkFinanceReceivables);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取应收通知单信息",notes="根据ID获取应收通知单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkFinanceReceivableEntity task = emkFinanceReceivableService.get(EmkFinanceReceivableEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取应收通知单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建应收通知单")
	public ResponseMessage<?> create(@ApiParam(name="应收通知单对象")@RequestBody EmkFinanceReceivableEntity emkFinanceReceivable, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkFinanceReceivableEntity>> failures = validator.validate(emkFinanceReceivable);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkFinanceReceivableService.save(emkFinanceReceivable);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("应收通知单信息保存失败");
		}
		return Result.success(emkFinanceReceivable);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新应收通知单",notes="更新应收通知单")
	public ResponseMessage<?> update(@ApiParam(name="应收通知单对象")@RequestBody EmkFinanceReceivableEntity emkFinanceReceivable) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkFinanceReceivableEntity>> failures = validator.validate(emkFinanceReceivable);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkFinanceReceivableService.saveOrUpdate(emkFinanceReceivable);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新应收通知单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新应收通知单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除应收通知单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkFinanceReceivableService.deleteEntityById(EmkFinanceReceivableEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("应收通知单删除失败");
		}

		return Result.success();
	}
}
