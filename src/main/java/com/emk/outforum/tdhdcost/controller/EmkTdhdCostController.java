package com.emk.outforum.tdhdcost.controller;
import com.emk.outforum.ship.entity.EmkShipDetailEntity;
import com.emk.outforum.tdhdcost.entity.EmkTdhdCostDetailEntity;
import com.emk.outforum.tdhdcost.entity.EmkTdhdCostEntity;
import com.emk.outforum.tdhdcost.service.EmkTdhdCostServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @Description: 提单货代费用
 * @author onlineGenerator
 * @date 2018-09-23 18:17:42
 * @version V1.0   
 *
 */
@Api(value="EmkTdhdCost",description="提单货代费用",tags="emkTdhdCostController")
@Controller
@RequestMapping("/emkTdhdCostController")
public class EmkTdhdCostController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkTdhdCostController.class);

	@Autowired
	private EmkTdhdCostServiceI emkTdhdCostService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 提单货代费用列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/outforum/tdhdcost/emkTdhdCostList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if (Utils.notEmpty(map.get("tdhdCostId"))) {
			List<EmkTdhdCostDetailEntity> emkTdhdCostDetailEntities = systemService.findHql("from EmkTdhdCostDetailEntity where tdhdCostId=?", map.get("tdhdCostId"));
			request.setAttribute("emkTdhdCostDetailEntities", emkTdhdCostDetailEntities);
		}
		return new ModelAndView("com/emk/outforum/tdhdcost/detailMxList");
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
	public void datagrid(EmkTdhdCostEntity emkTdhdCost,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkTdhdCostEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkTdhdCost, request.getParameterMap());
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
		this.emkTdhdCostService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除提单货代费用
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkTdhdCostEntity emkTdhdCost, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkTdhdCost = systemService.getEntity(EmkTdhdCostEntity.class, emkTdhdCost.getId());
		message = "提单货代费用删除成功";
		try{
			emkTdhdCostService.delete(emkTdhdCost);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "提单货代费用删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除提单货代费用
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "提单货代费用删除成功";
		try{
			for(String id:ids.split(",")){
				EmkTdhdCostEntity emkTdhdCost = systemService.getEntity(EmkTdhdCostEntity.class, 
				id
				);
				emkTdhdCostService.delete(emkTdhdCost);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "提单货代费用删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加提单货代费用
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkTdhdCostEntity emkTdhdCost, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "提单货代费用添加成功";
		try{
			emkTdhdCostService.save(emkTdhdCost);
			Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkTdhdCostDetailEntity emkTdhdCostDetailEntity = new EmkTdhdCostDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].tdNum00"))) {
						emkTdhdCostDetailEntity.setTdNum(map.get("orderMxList["+i+"].tdNum00"));
						emkTdhdCostDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkTdhdCostDetailEntity.setOutForumNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkTdhdCostDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));
						emkTdhdCostDetailEntity.setLevealDate(map.get("orderMxList["+i+"].levealDate00"));
						emkTdhdCostDetailEntity.setHtNum(map.get("orderMxList["+i+"].produceHtNum00"));
						emkTdhdCostDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));

						if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
							emkTdhdCostDetailEntity.setSumBoxTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
							emkTdhdCostDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
							emkTdhdCostDetailEntity.setSumBoxMao(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightJz00"))){
							emkTdhdCostDetailEntity.setSumBoxJz(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightJz00")));
						}
						emkTdhdCostDetailEntity.setCost(map.get("orderMxList["+i+"].cost00"));
						emkTdhdCostDetailEntity.setTdhdCostId(emkTdhdCost.getId());
						systemService.save(emkTdhdCostDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "提单货代费用添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新提单货代费用
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkTdhdCostEntity emkTdhdCost, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "提单货代费用更新成功";
		Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkTdhdCostEntity t = emkTdhdCostService.get(EmkTdhdCostEntity.class, map.get("tdhdCostId"));
		try {
			emkTdhdCost.setId(null);
			MyBeanUtils.copyBeanNotNull2Bean(emkTdhdCost, t);
			emkTdhdCostService.saveOrUpdate(t);
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_tdhd_cost_detail where tdhd_cost_id = ?",t.getId());
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkTdhdCostDetailEntity emkTdhdCostDetailEntity = new EmkTdhdCostDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].tdNum00"))) {
						emkTdhdCostDetailEntity.setTdNum(map.get("orderMxList["+i+"].tdNum00"));
						emkTdhdCostDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkTdhdCostDetailEntity.setOutForumNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkTdhdCostDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));
						emkTdhdCostDetailEntity.setLevealDate(map.get("orderMxList["+i+"].levealDate00"));
						emkTdhdCostDetailEntity.setHtNum(map.get("orderMxList["+i+"].produceHtNum00"));
						emkTdhdCostDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));

						if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
							emkTdhdCostDetailEntity.setSumBoxTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
							emkTdhdCostDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
							emkTdhdCostDetailEntity.setSumBoxMao(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightJz00"))){
							emkTdhdCostDetailEntity.setSumBoxJz(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightJz00")));
						}
						emkTdhdCostDetailEntity.setCost(map.get("orderMxList["+i+"].cost00"));
						emkTdhdCostDetailEntity.setTdhdCostId(t.getId());
						systemService.save(emkTdhdCostDetailEntity);
					}
				}
			}

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "提单货代费用更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 提单货代费用新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkTdhdCostEntity emkTdhdCost, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkTdhdCost.getId())) {
			emkTdhdCost = emkTdhdCostService.getEntity(EmkTdhdCostEntity.class, emkTdhdCost.getId());
			req.setAttribute("emkTdhdCostPage", emkTdhdCost);
		}
		return new ModelAndView("com/emk/outforum/tdhdcost/emkTdhdCost-add");
	}
	/**
	 * 提单货代费用编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkTdhdCostEntity emkTdhdCost, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkTdhdCost.getId())) {
			emkTdhdCost = emkTdhdCostService.getEntity(EmkTdhdCostEntity.class, emkTdhdCost.getId());
			req.setAttribute("emkTdhdCostPage", emkTdhdCost);
		}
		return new ModelAndView("com/emk/outforum/tdhdcost/emkTdhdCost-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkTdhdCostController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkTdhdCostEntity emkTdhdCost,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkTdhdCostEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkTdhdCost, request.getParameterMap());
		List<EmkTdhdCostEntity> emkTdhdCosts = this.emkTdhdCostService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"提单货代费用");
		modelMap.put(NormalExcelConstants.CLASS,EmkTdhdCostEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("提单货代费用列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkTdhdCosts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkTdhdCostEntity emkTdhdCost,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"提单货代费用");
    	modelMap.put(NormalExcelConstants.CLASS,EmkTdhdCostEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("提单货代费用列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkTdhdCostEntity> listEmkTdhdCostEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkTdhdCostEntity.class,params);
				for (EmkTdhdCostEntity emkTdhdCost : listEmkTdhdCostEntitys) {
					emkTdhdCostService.save(emkTdhdCost);
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
	@ApiOperation(value="提单货代费用列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkTdhdCostEntity>> list() {
		List<EmkTdhdCostEntity> listEmkTdhdCosts=emkTdhdCostService.getList(EmkTdhdCostEntity.class);
		return Result.success(listEmkTdhdCosts);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取提单货代费用信息",notes="根据ID获取提单货代费用信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkTdhdCostEntity task = emkTdhdCostService.get(EmkTdhdCostEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取提单货代费用信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建提单货代费用")
	public ResponseMessage<?> create(@ApiParam(name="提单货代费用对象")@RequestBody EmkTdhdCostEntity emkTdhdCost, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkTdhdCostEntity>> failures = validator.validate(emkTdhdCost);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkTdhdCostService.save(emkTdhdCost);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("提单货代费用信息保存失败");
		}
		return Result.success(emkTdhdCost);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新提单货代费用",notes="更新提单货代费用")
	public ResponseMessage<?> update(@ApiParam(name="提单货代费用对象")@RequestBody EmkTdhdCostEntity emkTdhdCost) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkTdhdCostEntity>> failures = validator.validate(emkTdhdCost);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkTdhdCostService.saveOrUpdate(emkTdhdCost);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新提单货代费用信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新提单货代费用信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除提单货代费用")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkTdhdCostService.deleteEntityById(EmkTdhdCostEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("提单货代费用删除失败");
		}

		return Result.success();
	}
}
