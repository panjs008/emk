package com.emk.caiwu.yspay.controller;
import com.emk.caiwu.dypay.entity.EmkDyPayDetailEntity;
import com.emk.caiwu.yspay.entity.EmkYsPayDetailEntity;
import com.emk.caiwu.yspay.entity.EmkYsPayEntity;
import com.emk.caiwu.yspay.service.EmkYsPayServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.outforum.ship.entity.EmkShipDetailEntity;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
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
 * @Description: 运输费用申请单
 * @author onlineGenerator
 * @date 2019-03-17 21:32:04
 * @version V1.0   
 *
 */
@Api(value="EmkYsPay",description="运输费用申请单",tags="emkYsPayController")
@Controller
@RequestMapping("/emkYsPayController")
public class EmkYsPayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkYsPayController.class);

	@Autowired
	private EmkYsPayServiceI emkYsPayService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 运输费用申请单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/caiwu/yspay/emkYsPayList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if (Utils.notEmpty(map.get("ysPayId"))) {
			List<EmkYsPayDetailEntity> emkYsPayDetailEntities = systemService.findHql("from EmkYsPayDetailEntity where ysPayId=?", map.get("ysPayId"));
			request.setAttribute("emkYsPayDetailEntities", emkYsPayDetailEntities);
		}
		return new ModelAndView("com/emk/caiwu/yspay/detailMxList");
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
	public void datagrid(EmkYsPayEntity emkYsPay,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkYsPayEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkYsPay, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkYsPayService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除运输费用申请单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkYsPayEntity emkYsPay, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkYsPay = systemService.getEntity(EmkYsPayEntity.class, emkYsPay.getId());
		message = "运输费用申请单删除成功";
		try{
			emkYsPayService.delete(emkYsPay);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "运输费用申请单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除运输费用申请单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "运输费用申请单删除成功";
		try{
			for(String id:ids.split(",")){
				EmkYsPayEntity emkYsPay = systemService.getEntity(EmkYsPayEntity.class, 
				id
				);
				emkYsPayService.delete(emkYsPay);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "运输费用申请单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加运输费用申请单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkYsPayEntity emkYsPay, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "运输费用申请单添加成功";
		try{
			emkYsPayService.save(emkYsPay);
			Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkYsPayDetailEntity emkYsPayDetailEntity = new EmkYsPayDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].outDate00"))) {
						emkYsPayDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate00"));
						emkYsPayDetailEntity.setInDate(map.get("orderMxList["+i+"].inDate00"));
						emkYsPayDetailEntity.setCusNum(map.get("orderMxList["+i+"].cusNum00"));
						emkYsPayDetailEntity.setTdNum(map.get("orderMxList["+i+"].tdNum00"));

						emkYsPayDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkYsPayDetailEntity.setOutForumNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkYsPayDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));

						emkYsPayDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkYsPayDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkYsPayDetailEntity.setShipDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal00"))){
							emkYsPayDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
							emkYsPayDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
							emkYsPayDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
						}
						emkYsPayDetailEntity.setStartPlace(map.get("orderMxList["+i+"].startPlace00"));
						emkYsPayDetailEntity.setEndPlace(map.get("orderMxList["+i+"].endPlace00"));
						emkYsPayDetailEntity.setCost(map.get("orderMxList["+i+"].cost00"));
						emkYsPayDetailEntity.setYsPayId(emkYsPay.getId());
						systemService.save(emkYsPayDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "运输费用申请单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新运输费用申请单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkYsPayEntity emkYsPay, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "运输费用申请单更新成功";
		Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkYsPayEntity t = emkYsPayService.get(EmkYsPayEntity.class, map.get("ysPayId"));
		try {
			MyBeanUtils.copyBeanNotNull2Bean(emkYsPay, t);
			emkYsPayService.saveOrUpdate(t);
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_ys_pay_detail where ys_pay_id = ?",t.getId());
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkYsPayDetailEntity emkYsPayDetailEntity = new EmkYsPayDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].outDate00"))) {
						emkYsPayDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate00"));
						emkYsPayDetailEntity.setInDate(map.get("orderMxList["+i+"].inDate00"));
						emkYsPayDetailEntity.setCusNum(map.get("orderMxList["+i+"].cusNum00"));
						emkYsPayDetailEntity.setTdNum(map.get("orderMxList["+i+"].tdNum00"));

						emkYsPayDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkYsPayDetailEntity.setOutForumNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkYsPayDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));

						emkYsPayDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkYsPayDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkYsPayDetailEntity.setShipDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal00"))){
							emkYsPayDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
							emkYsPayDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
							emkYsPayDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
						}
						emkYsPayDetailEntity.setStartPlace(map.get("orderMxList["+i+"].startPlace00"));
						emkYsPayDetailEntity.setEndPlace(map.get("orderMxList["+i+"].endPlace00"));
						emkYsPayDetailEntity.setCost(map.get("orderMxList["+i+"].cost00"));
						emkYsPayDetailEntity.setYsPayId(t.getId());
						systemService.save(emkYsPayDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "运输费用申请单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 运输费用申请单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkYsPayEntity emkYsPay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkYsPay.getId())) {
			emkYsPay = emkYsPayService.getEntity(EmkYsPayEntity.class, emkYsPay.getId());
			req.setAttribute("emkYsPayPage", emkYsPay);
		}
		return new ModelAndView("com/emk/caiwu/yspay/emkYsPay-add");
	}
	/**
	 * 运输费用申请单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkYsPayEntity emkYsPay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkYsPay.getId())) {
			emkYsPay = emkYsPayService.getEntity(EmkYsPayEntity.class, emkYsPay.getId());
			req.setAttribute("emkYsPayPage", emkYsPay);
		}
		return new ModelAndView("com/emk/caiwu/yspay/emkYsPay-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkYsPayController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkYsPayEntity emkYsPay,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkYsPayEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkYsPay, request.getParameterMap());
		List<EmkYsPayEntity> emkYsPays = this.emkYsPayService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"运输费用申请单");
		modelMap.put(NormalExcelConstants.CLASS,EmkYsPayEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("运输费用申请单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkYsPays);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkYsPayEntity emkYsPay,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"运输费用申请单");
    	modelMap.put(NormalExcelConstants.CLASS,EmkYsPayEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("运输费用申请单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkYsPayEntity> listEmkYsPayEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkYsPayEntity.class,params);
				for (EmkYsPayEntity emkYsPay : listEmkYsPayEntitys) {
					emkYsPayService.save(emkYsPay);
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
	@ApiOperation(value="运输费用申请单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkYsPayEntity>> list() {
		List<EmkYsPayEntity> listEmkYsPays=emkYsPayService.getList(EmkYsPayEntity.class);
		return Result.success(listEmkYsPays);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取运输费用申请单信息",notes="根据ID获取运输费用申请单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkYsPayEntity task = emkYsPayService.get(EmkYsPayEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取运输费用申请单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建运输费用申请单")
	public ResponseMessage<?> create(@ApiParam(name="运输费用申请单对象")@RequestBody EmkYsPayEntity emkYsPay, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkYsPayEntity>> failures = validator.validate(emkYsPay);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkYsPayService.save(emkYsPay);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("运输费用申请单信息保存失败");
		}
		return Result.success(emkYsPay);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新运输费用申请单",notes="更新运输费用申请单")
	public ResponseMessage<?> update(@ApiParam(name="运输费用申请单对象")@RequestBody EmkYsPayEntity emkYsPay) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkYsPayEntity>> failures = validator.validate(emkYsPay);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkYsPayService.saveOrUpdate(emkYsPay);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新运输费用申请单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新运输费用申请单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除运输费用申请单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkYsPayService.deleteEntityById(EmkYsPayEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("运输费用申请单删除失败");
		}

		return Result.success();
	}
}
