package com.emk.caiwu.dypay.controller;
import com.emk.caiwu.dypay.entity.EmkDyPayDetailEntity;
import com.emk.caiwu.dypay.entity.EmkDyPayEntity;
import com.emk.caiwu.dypay.service.EmkDyPayServiceI;
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
 * @Description: 原料布料打样付款申请单
 * @author onlineGenerator
 * @date 2019-03-17 21:32:12
 * @version V1.0   
 *
 */
@Api(value="EmkDyPay",description="原料布料打样付款申请单",tags="emkDyPayController")
@Controller
@RequestMapping("/emkDyPayController")
public class EmkDyPayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkDyPayController.class);

	@Autowired
	private EmkDyPayServiceI emkDyPayService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 原料布料打样付款申请单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/caiwu/dypay/emkDyPayList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if (Utils.notEmpty(map.get("dyPayId"))) {
			List<EmkDyPayDetailEntity> emkDyPayDetailEntities = systemService.findHql("from EmkDyPayDetailEntity where dyPayId=?", map.get("dyPayId"));
			request.setAttribute("emkDyPayDetailEntities", emkDyPayDetailEntities);
		}
		return new ModelAndView("com/emk/caiwu/dypay/detailMxList");
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
	public void datagrid(EmkDyPayEntity emkDyPay,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkDyPayEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkDyPay, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkDyPayService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除原料布料打样付款申请单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkDyPayEntity emkDyPay, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkDyPay = systemService.getEntity(EmkDyPayEntity.class, emkDyPay.getId());
		message = "原料布料打样付款申请单删除成功";
		try{
			emkDyPayService.delete(emkDyPay);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "原料布料打样付款申请单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除原料布料打样付款申请单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "原料布料打样付款申请单删除成功";
		try{
			for(String id:ids.split(",")){
				EmkDyPayEntity emkDyPay = systemService.getEntity(EmkDyPayEntity.class, 
				id
				);
				emkDyPayService.delete(emkDyPay);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "原料布料打样付款申请单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加原料布料打样付款申请单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkDyPayEntity emkDyPay, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "原料布料打样付款申请单添加成功";
		try{
			emkDyPayService.save(emkDyPay);
			Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkDyPayDetailEntity emkDyPayDetailEntity = new EmkDyPayDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].xqdNo00"))) {
						emkDyPayDetailEntity.setCgxqdh(map.get("orderMxList["+i+"].xqdNo00"));
						emkDyPayDetailEntity.setCgdh(map.get("orderMxList["+i+"].cgdh00"));
						emkDyPayDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkDyPayDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName00"));
						emkDyPayDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkDyPayDetailEntity.setBrand(map.get("orderMxList["+i+"].brand00"));
						emkDyPayDetailEntity.setSignTotal(map.get("orderMxList["+i+"].signTotal00"));
						emkDyPayDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
						emkDyPayDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumMoney"));
						emkDyPayDetailEntity.setDyPayId(emkDyPay.getId());
						systemService.save(emkDyPayDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "原料布料打样付款申请单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新原料布料打样付款申请单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkDyPayEntity emkDyPay, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "原料布料打样付款申请单更新成功";
		Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkDyPayEntity t = emkDyPayService.get(EmkDyPayEntity.class, emkDyPay.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(emkDyPay, t);
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_dy_pay_detail where dy_pay_id = ?",t.getId());
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkDyPayDetailEntity emkDyPayDetailEntity = new EmkDyPayDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].xqdNo00"))) {
						emkDyPayDetailEntity.setCgxqdh(map.get("orderMxList["+i+"].xqdNo00"));
						emkDyPayDetailEntity.setCgdh(map.get("orderMxList["+i+"].cgdh00"));
						emkDyPayDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkDyPayDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName00"));
						emkDyPayDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkDyPayDetailEntity.setBrand(map.get("orderMxList["+i+"].brand00"));
						emkDyPayDetailEntity.setSignTotal(map.get("orderMxList["+i+"].signTotal00"));
						emkDyPayDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
						emkDyPayDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumMoney"));
						emkDyPayDetailEntity.setDyPayId(t.getId());
						systemService.save(emkDyPayDetailEntity);
					}
				}
			}
			emkDyPayService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "原料布料打样付款申请单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 原料布料打样付款申请单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkDyPayEntity emkDyPay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkDyPay.getId())) {
			emkDyPay = emkDyPayService.getEntity(EmkDyPayEntity.class, emkDyPay.getId());
			req.setAttribute("emkDyPayPage", emkDyPay);
		}
		return new ModelAndView("com/emk/caiwu/dypay/emkDyPay-add");
	}
	/**
	 * 原料布料打样付款申请单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkDyPayEntity emkDyPay, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkDyPay.getId())) {
			emkDyPay = emkDyPayService.getEntity(EmkDyPayEntity.class, emkDyPay.getId());
			req.setAttribute("emkDyPayPage", emkDyPay);
		}
		return new ModelAndView("com/emk/caiwu/dypay/emkDyPay-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkDyPayController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkDyPayEntity emkDyPay,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkDyPayEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkDyPay, request.getParameterMap());
		List<EmkDyPayEntity> emkDyPays = this.emkDyPayService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"原料布料打样付款申请单");
		modelMap.put(NormalExcelConstants.CLASS,EmkDyPayEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("原料布料打样付款申请单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkDyPays);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkDyPayEntity emkDyPay,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"原料布料打样付款申请单");
    	modelMap.put(NormalExcelConstants.CLASS,EmkDyPayEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("原料布料打样付款申请单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkDyPayEntity> listEmkDyPayEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkDyPayEntity.class,params);
				for (EmkDyPayEntity emkDyPay : listEmkDyPayEntitys) {
					emkDyPayService.save(emkDyPay);
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
	@ApiOperation(value="原料布料打样付款申请单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkDyPayEntity>> list() {
		List<EmkDyPayEntity> listEmkDyPays=emkDyPayService.getList(EmkDyPayEntity.class);
		return Result.success(listEmkDyPays);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取原料布料打样付款申请单信息",notes="根据ID获取原料布料打样付款申请单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkDyPayEntity task = emkDyPayService.get(EmkDyPayEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取原料布料打样付款申请单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建原料布料打样付款申请单")
	public ResponseMessage<?> create(@ApiParam(name="原料布料打样付款申请单对象")@RequestBody EmkDyPayEntity emkDyPay, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkDyPayEntity>> failures = validator.validate(emkDyPay);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkDyPayService.save(emkDyPay);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("原料布料打样付款申请单信息保存失败");
		}
		return Result.success(emkDyPay);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新原料布料打样付款申请单",notes="更新原料布料打样付款申请单")
	public ResponseMessage<?> update(@ApiParam(name="原料布料打样付款申请单对象")@RequestBody EmkDyPayEntity emkDyPay) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkDyPayEntity>> failures = validator.validate(emkDyPay);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkDyPayService.saveOrUpdate(emkDyPay);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新原料布料打样付款申请单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新原料布料打样付款申请单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除原料布料打样付款申请单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkDyPayService.deleteEntityById(EmkDyPayEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("原料布料打样付款申请单删除失败");
		}

		return Result.success();
	}
}
