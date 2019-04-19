package com.emk.outforum.fobbusiness.controller;
import com.emk.outforum.fobbusiness.entity.EmkFobBusinessDetailEntity;
import com.emk.outforum.fobbusiness.entity.EmkFobBusinessEntity;
import com.emk.outforum.fobbusiness.service.EmkFobBusinessServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.outforum.tdhdcost.entity.EmkTdhdCostDetailEntity;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
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
 * @Description: 订舱通知单号
 * @author onlineGenerator
 * @date 2018-09-20 22:29:01
 * @version V1.0   
 *
 */
@Api(value="EmkFobBusiness",description="订舱通知单号",tags="emkFobBusinessController")
@Controller
@RequestMapping("/emkFobBusinessController")
public class EmkFobBusinessController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkFobBusinessController.class);

	@Autowired
	private EmkFobBusinessServiceI emkFobBusinessService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 订舱通知单号列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/outforum/fobbusiness/emkFobBusinessList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if (Utils.notEmpty(map.get("fobId"))) {
			List<EmkFobBusinessDetailEntity> emkFobBusinessDetailEntities = systemService.findHql("from EmkFobBusinessDetailEntity where fobId=?", map.get("fobId"));
			request.setAttribute("emkFobBusinessDetailEntities", emkFobBusinessDetailEntities);
		}
		return new ModelAndView("com/emk/outforum/fobbusiness/detailMxList");
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
	public void datagrid(EmkFobBusinessEntity emkFobBusiness,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkFobBusinessEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkFobBusiness, request.getParameterMap());
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
		this.emkFobBusinessService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除订舱通知单号
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkFobBusinessEntity emkFobBusiness, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkFobBusiness = systemService.getEntity(EmkFobBusinessEntity.class, emkFobBusiness.getId());
		message = "订舱通知单号删除成功";
		try{
			emkFobBusinessService.delete(emkFobBusiness);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订舱通知单号删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除订舱通知单号
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订舱通知单号删除成功";
		try{
			for(String id:ids.split(",")){
				EmkFobBusinessEntity emkFobBusiness = systemService.getEntity(EmkFobBusinessEntity.class, 
				id
				);
				emkFobBusinessService.delete(emkFobBusiness);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "订舱通知单号删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加订舱通知单号
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkFobBusinessEntity emkFobBusiness, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订舱通知单号添加成功";
		try{
			emkFobBusinessService.save(emkFobBusiness);
			Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkFobBusinessDetailEntity emkFobBusinessDetailEntity = new EmkFobBusinessDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].cusNum00"))) {
						emkFobBusinessDetailEntity.setCusNum(map.get("orderMxList["+i+"].cusNum00"));
						emkFobBusinessDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkFobBusinessDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkFobBusinessDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal00"))){
							emkFobBusinessDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal00")));
						}
						emkFobBusinessDetailEntity.setPrice(map.get("orderMxList["+i+"].price00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].sumMoney00"))){
							emkFobBusinessDetailEntity.setSumMoney(Double.parseDouble(map.get("orderMxList["+i+"].sumMoney00")));

						}
						emkFobBusinessDetailEntity.setFactoryCode(map.get("orderMxList["+i+"].factoryCode00"));
						emkFobBusinessDetailEntity.setJqDate(map.get("orderMxList["+i+"].jqDate00"));
						emkFobBusinessDetailEntity.setCargoState(map.get("orderMxList["+i+"].cargoState00"));
						emkFobBusinessDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate00"));


						emkFobBusinessDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkFobBusinessDetailEntity.setOutFourmNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkFobBusinessDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));
						emkFobBusinessDetailEntity.setLevealDate(map.get("orderMxList["+i+"].levealDate00"));

						emkFobBusinessDetailEntity.setTdNum(map.get("orderMxList["+i+"].tdNum00"));
						emkFobBusinessDetailEntity.setTdState(map.get("orderMxList["+i+"].tdState00"));
						emkFobBusinessDetailEntity.setStartPlace(map.get("orderMxList["+i+"].startPlace00"));
						emkFobBusinessDetailEntity.setEndPlace(map.get("orderMxList["+i+"].endPlace00"));
						emkFobBusinessDetailEntity.setHdCode(map.get("orderMxList["+i+"].hdCode00"));
						emkFobBusinessDetailEntity.setChCost(map.get("orderMxList["+i+"].chCost00"));
						emkFobBusinessDetailEntity.setChState(map.get("orderMxList["+i+"].chState00"));
						emkFobBusinessDetailEntity.setYsEntCode(map.get("orderMxList["+i+"].ysEntCode00"));
						emkFobBusinessDetailEntity.setYsEntCost(map.get("orderMxList["+i+"].ysEntCost00"));
						emkFobBusinessDetailEntity.setYsState(map.get("orderMxList["+i+"].ysState00"));
						emkFobBusinessDetailEntity.setBgd(map.get("orderMxList["+i+"].bgd00"));
						emkFobBusinessDetailEntity.setFxt(map.get("orderMxList["+i+"].fxt00"));
						emkFobBusinessDetailEntity.setFpNum(map.get("orderMxList["+i+"].fpNum00"));
						emkFobBusinessDetailEntity.setZxd(map.get("orderMxList["+i+"].boxNum00"));

						emkFobBusinessDetailEntity.setFobId(emkFobBusiness.getId());
						systemService.save(emkFobBusinessDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订舱通知单号添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新订舱通知单号
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkFobBusinessEntity emkFobBusiness, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "订舱通知单号更新成功";
		Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkFobBusinessEntity t = emkFobBusinessService.get(EmkFobBusinessEntity.class, map.get("fobId"));
		try {
			emkFobBusiness.setId(null);
			MyBeanUtils.copyBeanNotNull2Bean(emkFobBusiness, t);
			emkFobBusinessService.saveOrUpdate(t);
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_fob_business_detail where fob_id = ?",t.getId());
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkFobBusinessDetailEntity emkFobBusinessDetailEntity = new EmkFobBusinessDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].cusNum00"))) {
						emkFobBusinessDetailEntity.setCusNum(map.get("orderMxList["+i+"].cusNum00"));
						emkFobBusinessDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkFobBusinessDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkFobBusinessDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal00"))){
							emkFobBusinessDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal00")));
						}
						emkFobBusinessDetailEntity.setPrice(map.get("orderMxList["+i+"].price00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].sumMoney00"))){
							emkFobBusinessDetailEntity.setSumMoney(Double.parseDouble(map.get("orderMxList["+i+"].sumMoney00")));

						}
						emkFobBusinessDetailEntity.setFactoryCode(map.get("orderMxList["+i+"].factoryCode00"));
						emkFobBusinessDetailEntity.setJqDate(map.get("orderMxList["+i+"].jqDate00"));
						emkFobBusinessDetailEntity.setCargoState(map.get("orderMxList["+i+"].cargoState00"));
						emkFobBusinessDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate00"));


						emkFobBusinessDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkFobBusinessDetailEntity.setOutFourmNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkFobBusinessDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));
						emkFobBusinessDetailEntity.setLevealDate(map.get("orderMxList["+i+"].levealDate00"));

						emkFobBusinessDetailEntity.setTdNum(map.get("orderMxList["+i+"].tdNum00"));
						emkFobBusinessDetailEntity.setTdState(map.get("orderMxList["+i+"].tdState00"));
						emkFobBusinessDetailEntity.setStartPlace(map.get("orderMxList["+i+"].startPlace00"));
						emkFobBusinessDetailEntity.setEndPlace(map.get("orderMxList["+i+"].endPlace00"));
						emkFobBusinessDetailEntity.setHdCode(map.get("orderMxList["+i+"].hdCode00"));
						emkFobBusinessDetailEntity.setChCost(map.get("orderMxList["+i+"].chCost00"));
						emkFobBusinessDetailEntity.setChState(map.get("orderMxList["+i+"].chState00"));
						emkFobBusinessDetailEntity.setYsEntCode(map.get("orderMxList["+i+"].ysEntCode00"));
						emkFobBusinessDetailEntity.setYsEntCost(map.get("orderMxList["+i+"].ysEntCost00"));
						emkFobBusinessDetailEntity.setYsState(map.get("orderMxList["+i+"].ysState00"));
						emkFobBusinessDetailEntity.setBgd(map.get("orderMxList["+i+"].bgd00"));
						emkFobBusinessDetailEntity.setFxt(map.get("orderMxList["+i+"].fxt00"));
						emkFobBusinessDetailEntity.setFpNum(map.get("orderMxList["+i+"].fpNum00"));
						emkFobBusinessDetailEntity.setZxd(map.get("orderMxList["+i+"].boxNum00"));

						emkFobBusinessDetailEntity.setFobId(t.getId());
						systemService.save(emkFobBusinessDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "订舱通知单号更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 订舱通知单号新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkFobBusinessEntity emkFobBusiness, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkFobBusiness.getId())) {
			emkFobBusiness = emkFobBusinessService.getEntity(EmkFobBusinessEntity.class, emkFobBusiness.getId());
			req.setAttribute("emkFobBusinessPage", emkFobBusiness);
		}
		return new ModelAndView("com/emk/outforum/fobbusiness/emkFobBusiness-add");
	}
	/**
	 * 订舱通知单号编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkFobBusinessEntity emkFobBusiness, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkFobBusiness.getId())) {
			emkFobBusiness = emkFobBusinessService.getEntity(EmkFobBusinessEntity.class, emkFobBusiness.getId());
			req.setAttribute("emkFobBusinessPage", emkFobBusiness);
		}
		return new ModelAndView("com/emk/outforum/fobbusiness/emkFobBusiness-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkFobBusinessController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkFobBusinessEntity emkFobBusiness,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkFobBusinessEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkFobBusiness, request.getParameterMap());
		List<EmkFobBusinessEntity> emkFobBusinesss = this.emkFobBusinessService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"订舱通知单号");
		modelMap.put(NormalExcelConstants.CLASS,EmkFobBusinessEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订舱通知单号列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkFobBusinesss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkFobBusinessEntity emkFobBusiness,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"订舱通知单号");
    	modelMap.put(NormalExcelConstants.CLASS,EmkFobBusinessEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("订舱通知单号列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkFobBusinessEntity> listEmkFobBusinessEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkFobBusinessEntity.class,params);
				for (EmkFobBusinessEntity emkFobBusiness : listEmkFobBusinessEntitys) {
					emkFobBusinessService.save(emkFobBusiness);
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
	@ApiOperation(value="订舱通知单号列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkFobBusinessEntity>> list() {
		List<EmkFobBusinessEntity> listEmkFobBusinesss=emkFobBusinessService.getList(EmkFobBusinessEntity.class);
		return Result.success(listEmkFobBusinesss);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取订舱通知单号信息",notes="根据ID获取订舱通知单号信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkFobBusinessEntity task = emkFobBusinessService.get(EmkFobBusinessEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取订舱通知单号信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建订舱通知单号")
	public ResponseMessage<?> create(@ApiParam(name="订舱通知单号对象")@RequestBody EmkFobBusinessEntity emkFobBusiness, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkFobBusinessEntity>> failures = validator.validate(emkFobBusiness);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkFobBusinessService.save(emkFobBusiness);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("订舱通知单号信息保存失败");
		}
		return Result.success(emkFobBusiness);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新订舱通知单号",notes="更新订舱通知单号")
	public ResponseMessage<?> update(@ApiParam(name="订舱通知单号对象")@RequestBody EmkFobBusinessEntity emkFobBusiness) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkFobBusinessEntity>> failures = validator.validate(emkFobBusiness);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkFobBusinessService.saveOrUpdate(emkFobBusiness);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新订舱通知单号信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新订舱通知单号信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除订舱通知单号")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkFobBusinessService.deleteEntityById(EmkFobBusinessEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("订舱通知单号删除失败");
		}

		return Result.success();
	}
}
