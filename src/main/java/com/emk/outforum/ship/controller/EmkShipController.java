package com.emk.outforum.ship.controller;
import com.emk.outforum.ship.entity.EmkShipDetailEntity;
import com.emk.outforum.ship.entity.EmkShipEntity;
import com.emk.outforum.ship.service.EmkShipServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.outforum.shipexpenses.entity.EmkShipExpensesDetailEntity;
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
 * @Description: 客用船务文件
 * @author onlineGenerator
 * @date 2018-09-23 16:41:16
 * @version V1.0   
 *
 */
@Api(value="EmkShip",description="客用船务文件",tags="emkShipController")
@Controller
@RequestMapping("/emkShipController")
public class EmkShipController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkShipController.class);

	@Autowired
	private EmkShipServiceI emkShipService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 客用船务文件列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/outforum/ship/emkShipList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if (Utils.notEmpty(map.get("shipId"))) {
			List<EmkShipDetailEntity> emkShipDetailEntities = systemService.findHql("from EmkShipDetailEntity where shipId=?", map.get("shipId"));
			request.setAttribute("emkShipDetailEntities", emkShipDetailEntities);
		}
		return new ModelAndView("com/emk/outforum/ship/detailMxList");
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
	public void datagrid(EmkShipEntity emkShip,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkShipEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkShip, request.getParameterMap());
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
		this.emkShipService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除客用船务文件
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkShipEntity emkShip, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkShip = systemService.getEntity(EmkShipEntity.class, emkShip.getId());
		message = "客用船务文件删除成功";
		try{
			emkShipService.delete(emkShip);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客用船务文件删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除客用船务文件
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客用船务文件删除成功";
		try{
			for(String id:ids.split(",")){
				EmkShipEntity emkShip = systemService.getEntity(EmkShipEntity.class, 
				id
				);
				emkShipService.delete(emkShip);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "客用船务文件删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加客用船务文件
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkShipEntity emkShip, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客用船务文件添加成功";
		try{
			emkShipService.save(emkShip);
			Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkShipDetailEntity emkShipDetailEntity = new EmkShipDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))) {
						emkShipDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkShipDetailEntity.setOutForumNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkShipDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));
						emkShipDetailEntity.setLevealDate(map.get("orderMxList["+i+"].levealDate00"));

						emkShipDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode00"));
						emkShipDetailEntity.setHtNum(map.get("orderMxList["+i+"].produceHtNum00"));
						emkShipDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkShipDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkShipDetailEntity.setShipDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal00"))){
							emkShipDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal00")));
						}
						emkShipDetailEntity.setSumBoxTotal(map.get("orderMxList["+i+"].atotalBox00"));
						emkShipDetailEntity.setSumBoxVolume(map.get("orderMxList["+i+"].asumVolume00"));
						emkShipDetailEntity.setSumBoxMao(map.get("orderMxList["+i+"].asumWeightMao00"));
						emkShipDetailEntity.setSumBoxJz(map.get("orderMxList["+i+"].asumWeightJz00"));
						emkShipDetailEntity.setTdNum(map.get("orderMxList["+i+"].tdNum00"));
						emkShipDetailEntity.setTdState(map.get("orderMxList["+i+"].tdState00"));
						emkShipDetailEntity.setFpNum(map.get("orderMxList["+i+"].fpNum00"));
						emkShipDetailEntity.setBoxNum(map.get("orderMxList["+i+"].boxNum00"));
						emkShipDetailEntity.setShipId(emkShip.getId());
						systemService.save(emkShipDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客用船务文件添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新客用船务文件
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkShipEntity emkShip, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客用船务文件更新成功";
		Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkShipEntity t = emkShipService.get(EmkShipEntity.class, map.get("shipId"));
		try {
			emkShip.setId(null);
			MyBeanUtils.copyBeanNotNull2Bean(emkShip, t);
			emkShipService.saveOrUpdate(t);
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_ship_detail where ship_id = ?",t.getId());
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkShipDetailEntity emkShipDetailEntity = new EmkShipDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].cargoNo00"))) {
						emkShipDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkShipDetailEntity.setOutForumNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkShipDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));
						emkShipDetailEntity.setLevealDate(map.get("orderMxList["+i+"].levealDate00"));

						emkShipDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode00"));
						emkShipDetailEntity.setHtNum(map.get("orderMxList["+i+"].produceHtNum00"));
						emkShipDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkShipDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkShipDetailEntity.setShipDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal00"))){
							emkShipDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal00")));
						}
						emkShipDetailEntity.setSumBoxTotal(map.get("orderMxList["+i+"].atotalBox00"));
						emkShipDetailEntity.setSumBoxVolume(map.get("orderMxList["+i+"].asumVolume00"));
						emkShipDetailEntity.setSumBoxMao(map.get("orderMxList["+i+"].asumWeightMao00"));
						emkShipDetailEntity.setSumBoxJz(map.get("orderMxList["+i+"].asumWeightJz00"));
						emkShipDetailEntity.setTdNum(map.get("orderMxList["+i+"].tdNum00"));
						emkShipDetailEntity.setTdState(map.get("orderMxList["+i+"].tdState00"));
						emkShipDetailEntity.setFpNum(map.get("orderMxList["+i+"].fpNum00"));
						emkShipDetailEntity.setBoxNum(map.get("orderMxList["+i+"].boxNum00"));
						emkShipDetailEntity.setShipId(t.getId());
						systemService.save(emkShipDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客用船务文件更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 客用船务文件新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkShipEntity emkShip, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkShip.getId())) {
			emkShip = emkShipService.getEntity(EmkShipEntity.class, emkShip.getId());
			req.setAttribute("emkShipPage", emkShip);
		}
		return new ModelAndView("com/emk/outforum/ship/emkShip-add");
	}
	/**
	 * 客用船务文件编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkShipEntity emkShip, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkShip.getId())) {
			emkShip = emkShipService.getEntity(EmkShipEntity.class, emkShip.getId());
			req.setAttribute("emkShipPage", emkShip);
		}
		return new ModelAndView("com/emk/outforum/ship/emkShip-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkShipController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkShipEntity emkShip,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkShipEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkShip, request.getParameterMap());
		List<EmkShipEntity> emkShips = this.emkShipService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"客用船务文件");
		modelMap.put(NormalExcelConstants.CLASS,EmkShipEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客用船务文件列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkShips);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkShipEntity emkShip,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"客用船务文件");
    	modelMap.put(NormalExcelConstants.CLASS,EmkShipEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客用船务文件列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkShipEntity> listEmkShipEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkShipEntity.class,params);
				for (EmkShipEntity emkShip : listEmkShipEntitys) {
					emkShipService.save(emkShip);
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
	@ApiOperation(value="客用船务文件列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkShipEntity>> list() {
		List<EmkShipEntity> listEmkShips=emkShipService.getList(EmkShipEntity.class);
		return Result.success(listEmkShips);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取客用船务文件信息",notes="根据ID获取客用船务文件信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkShipEntity task = emkShipService.get(EmkShipEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取客用船务文件信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建客用船务文件")
	public ResponseMessage<?> create(@ApiParam(name="客用船务文件对象")@RequestBody EmkShipEntity emkShip, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkShipEntity>> failures = validator.validate(emkShip);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkShipService.save(emkShip);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("客用船务文件信息保存失败");
		}
		return Result.success(emkShip);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新客用船务文件",notes="更新客用船务文件")
	public ResponseMessage<?> update(@ApiParam(name="客用船务文件对象")@RequestBody EmkShipEntity emkShip) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkShipEntity>> failures = validator.validate(emkShip);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkShipService.saveOrUpdate(emkShip);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新客用船务文件信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新客用船务文件信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除客用船务文件")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkShipService.deleteEntityById(EmkShipEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("客用船务文件删除失败");
		}

		return Result.success();
	}
}
