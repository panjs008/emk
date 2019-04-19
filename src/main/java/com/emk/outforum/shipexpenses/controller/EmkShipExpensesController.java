package com.emk.outforum.shipexpenses.controller;
import com.emk.outforum.shipexpenses.entity.EmkShipExpensesDetailEntity;
import com.emk.outforum.shipexpenses.entity.EmkShipExpensesEntity;
import com.emk.outforum.shipexpenses.service.EmkShipExpensesServiceI;

import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.produce.invoices.entity.EmkInvoicesDetailEntity;
import com.emk.produce.packinglist.entity.EmkPackingListDetailEntity;
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
 * @Description: 运费费
 * @author onlineGenerator
 * @date 2018-09-23 16:41:07
 * @version V1.0   
 *
 */
@Api(value="EmkShipExpenses",description="运费费",tags="emkShipExpensesController")
@Controller
@RequestMapping("/emkShipExpensesController")
public class EmkShipExpensesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkShipExpensesController.class);

	@Autowired
	private EmkShipExpensesServiceI emkShipExpensesService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 运费费列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/outforum/shipexpenses/emkShipExpensesList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if (Utils.notEmpty(map.get("shipExpensesId"))) {
			List<EmkShipExpensesDetailEntity> emkShipExpensesDetailEntities = systemService.findHql("from EmkShipExpensesDetailEntity where shipExpensesId=?", map.get("shipExpensesId"));
			request.setAttribute("emkShipExpensesDetailEntities", emkShipExpensesDetailEntities);
		}
		return new ModelAndView("com/emk/outforum/shipexpenses/detailMxList");
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
	public void datagrid(EmkShipExpensesEntity emkShipExpenses,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkShipExpensesEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkShipExpenses, request.getParameterMap());
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
		this.emkShipExpensesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除运费费
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkShipExpensesEntity emkShipExpenses, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkShipExpenses = systemService.getEntity(EmkShipExpensesEntity.class, emkShipExpenses.getId());
		message = "运费费删除成功";
		try{
			emkShipExpensesService.delete(emkShipExpenses);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "运费费删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除运费费
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "运费费删除成功";
		try{
			for(String id:ids.split(",")){
				EmkShipExpensesEntity emkShipExpenses = systemService.getEntity(EmkShipExpensesEntity.class, 
				id
				);
				emkShipExpensesService.delete(emkShipExpenses);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "运费费删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加运费费
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkShipExpensesEntity emkShipExpenses, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "运费费添加成功";
		try{
			Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			emkShipExpensesService.save(emkShipExpenses);
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkShipExpensesDetailEntity emkShipExpensesDetailEntity = new EmkShipExpensesDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))) {
						emkShipExpensesDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkShipExpensesDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkShipExpensesDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						emkShipExpensesDetailEntity.setTotal(map.get("orderMxList["+i+"].signTotal00"));

						if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
							emkShipExpensesDetailEntity.setSumBoxTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
							emkShipExpensesDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
							emkShipExpensesDetailEntity.setSumBoxMao(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightJz00"))){
							emkShipExpensesDetailEntity.setSumBoxJz(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightJz00")));
						}
						emkShipExpensesDetailEntity.setShipExpensesId(emkShipExpenses.getId());
						systemService.save(emkShipExpensesDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "运费费添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新运费费
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkShipExpensesEntity emkShipExpenses, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "运费费更新成功";
		Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkShipExpensesEntity t = emkShipExpensesService.get(EmkShipExpensesEntity.class, map.get("shipId"));
		try {
			emkShipExpenses.setId(null);
			MyBeanUtils.copyBeanNotNull2Bean(emkShipExpenses, t);
			emkShipExpensesService.saveOrUpdate(t);
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_ship_expenses_detail where ship_expenses_id = ?",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkShipExpensesDetailEntity emkShipExpensesDetailEntity = new EmkShipExpensesDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))) {
						emkShipExpensesDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkShipExpensesDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkShipExpensesDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						emkShipExpensesDetailEntity.setTotal(map.get("orderMxList["+i+"].signTotal00"));

						if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
							emkShipExpensesDetailEntity.setSumBoxTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
							emkShipExpensesDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
							emkShipExpensesDetailEntity.setSumBoxMao(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightJz00"))){
							emkShipExpensesDetailEntity.setSumBoxJz(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightJz00")));
						}
						emkShipExpensesDetailEntity.setShipExpensesId(t.getId());
						systemService.save(emkShipExpensesDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "运费费更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 运费费新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkShipExpensesEntity emkShipExpenses, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));

		if (StringUtil.isNotEmpty(emkShipExpenses.getId())) {
			emkShipExpenses = emkShipExpensesService.getEntity(EmkShipExpensesEntity.class, emkShipExpenses.getId());
			req.setAttribute("emkShipExpensesPage", emkShipExpenses);
		}
		return new ModelAndView("com/emk/outforum/shipexpenses/emkShipExpenses-add");
	}
	/**
	 * 运费费编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkShipExpensesEntity emkShipExpenses, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkShipExpenses.getId())) {
			emkShipExpenses = emkShipExpensesService.getEntity(EmkShipExpensesEntity.class, emkShipExpenses.getId());
			req.setAttribute("emkShipExpensesPage", emkShipExpenses);
		}
		return new ModelAndView("com/emk/outforum/shipexpenses/emkShipExpenses-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkShipExpensesController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkShipExpensesEntity emkShipExpenses,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkShipExpensesEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkShipExpenses, request.getParameterMap());
		List<EmkShipExpensesEntity> emkShipExpensess = this.emkShipExpensesService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"运费费");
		modelMap.put(NormalExcelConstants.CLASS,EmkShipExpensesEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("运费费列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkShipExpensess);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkShipExpensesEntity emkShipExpenses,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"运费费");
    	modelMap.put(NormalExcelConstants.CLASS,EmkShipExpensesEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("运费费列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkShipExpensesEntity> listEmkShipExpensesEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkShipExpensesEntity.class,params);
				for (EmkShipExpensesEntity emkShipExpenses : listEmkShipExpensesEntitys) {
					emkShipExpensesService.save(emkShipExpenses);
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
	@ApiOperation(value="运费费列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkShipExpensesEntity>> list() {
		List<EmkShipExpensesEntity> listEmkShipExpensess=emkShipExpensesService.getList(EmkShipExpensesEntity.class);
		return Result.success(listEmkShipExpensess);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取运费费信息",notes="根据ID获取运费费信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkShipExpensesEntity task = emkShipExpensesService.get(EmkShipExpensesEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取运费费信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建运费费")
	public ResponseMessage<?> create(@ApiParam(name="运费费对象")@RequestBody EmkShipExpensesEntity emkShipExpenses, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkShipExpensesEntity>> failures = validator.validate(emkShipExpenses);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkShipExpensesService.save(emkShipExpenses);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("运费费信息保存失败");
		}
		return Result.success(emkShipExpenses);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新运费费",notes="更新运费费")
	public ResponseMessage<?> update(@ApiParam(name="运费费对象")@RequestBody EmkShipExpensesEntity emkShipExpenses) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkShipExpensesEntity>> failures = validator.validate(emkShipExpenses);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkShipExpensesService.saveOrUpdate(emkShipExpenses);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新运费费信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新运费费信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除运费费")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkShipExpensesService.deleteEntityById(EmkShipExpensesEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("运费费删除失败");
		}

		return Result.success();
	}
}
