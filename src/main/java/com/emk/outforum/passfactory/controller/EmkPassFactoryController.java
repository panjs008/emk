package com.emk.outforum.passfactory.controller;
import com.emk.outforum.passfactory.entity.EmkPassFactoryDetailEntity;
import com.emk.outforum.passfactory.entity.EmkPassFactoryEntity;
import com.emk.outforum.passfactory.service.EmkPassFactoryServiceI;
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
 * @Description: 离厂放行单
 * @author onlineGenerator
 * @date 2018-09-20 22:29:12
 * @version V1.0   
 *
 */
@Api(value="EmkPassFactory",description="离厂放行单",tags="emkPassFactoryController")
@Controller
@RequestMapping("/emkPassFactoryController")
public class EmkPassFactoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkPassFactoryController.class);

	@Autowired
	private EmkPassFactoryServiceI emkPassFactoryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 离厂放行单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/outforum/passfactory/emkPassFactoryList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if (Utils.notEmpty(map.get("passId"))) {
			List<EmkPassFactoryDetailEntity> emkPassFactoryDetailEntities = systemService.findHql("from EmkPassFactoryDetailEntity where passId=?", map.get("passId"));
			request.setAttribute("emkPassFactoryDetailEntities", emkPassFactoryDetailEntities);
		}
		return new ModelAndView("com/emk/outforum/passfactory/detailMxList");
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
	public void datagrid(EmkPassFactoryEntity emkPassFactory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkPassFactoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkPassFactory, request.getParameterMap());
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
		this.emkPassFactoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除离厂放行单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkPassFactoryEntity emkPassFactory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkPassFactory = systemService.getEntity(EmkPassFactoryEntity.class, emkPassFactory.getId());
		message = "离厂放行单删除成功";
		try{
			emkPassFactoryService.delete(emkPassFactory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "离厂放行单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除离厂放行单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "离厂放行单删除成功";
		try{
			for(String id:ids.split(",")){
				EmkPassFactoryEntity emkPassFactory = systemService.getEntity(EmkPassFactoryEntity.class, 
				id
				);
				emkPassFactoryService.delete(emkPassFactory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "离厂放行单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加离厂放行单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkPassFactoryEntity emkPassFactory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "离厂放行单添加成功";
		try{
			emkPassFactoryService.save(emkPassFactory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "离厂放行单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新离厂放行单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkPassFactoryEntity emkPassFactory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "离厂放行单更新成功";
		Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkPassFactoryEntity t = emkPassFactoryService.get(EmkPassFactoryEntity.class, map.get("passId"));
		try {
			emkPassFactory.setId(null);
			MyBeanUtils.copyBeanNotNull2Bean(emkPassFactory, t);
			emkPassFactoryService.saveOrUpdate(t);

			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_pass_factory_detail where pass_id = ?",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkPassFactoryDetailEntity emkPassFactoryDetailEntity = new EmkPassFactoryDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].cargoNo00"))) {
						emkPassFactoryDetailEntity.setCargoNo(map.get("orderMxList["+i+"].cargoNo00"));
						emkPassFactoryDetailEntity.setOutFourmNo(map.get("orderMxList["+i+"].outForumNo00"));
						emkPassFactoryDetailEntity.setLevealFactoryNo(map.get("orderMxList["+i+"].levealFactoryNo00"));
						emkPassFactoryDetailEntity.setLevealDate(map.get("orderMxList["+i+"].levealDate00"));

						emkPassFactoryDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode00"));
						emkPassFactoryDetailEntity.setHtNum(map.get("orderMxList["+i+"].produceHtNum00"));
						emkPassFactoryDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkPassFactoryDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkPassFactoryDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						emkPassFactoryDetailEntity.setTotal(map.get("orderMxList["+i+"].signTotal00"));

						emkPassFactoryDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
						emkPassFactoryDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
						emkPassFactoryDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));
						emkPassFactoryDetailEntity.setTotal(map.get("orderMxList["+i+"].signTotal00"));

						if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
							emkPassFactoryDetailEntity.setSumBoxTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumVolume00"))){
							emkPassFactoryDetailEntity.setSumBoxVolume(Double.parseDouble(map.get("orderMxList["+i+"].asumVolume00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightMao00"))){
							emkPassFactoryDetailEntity.setSumBoxMao(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightMao00")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumWeightJz00"))){
							emkPassFactoryDetailEntity.setSumBoxJz(Double.parseDouble(map.get("orderMxList["+i+"].asumWeightJz00")));
						}
						emkPassFactoryDetailEntity.setPassId(t.getId());
						systemService.save(emkPassFactoryDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "离厂放行单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 离厂放行单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkPassFactoryEntity emkPassFactory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkPassFactory.getId())) {
			emkPassFactory = emkPassFactoryService.getEntity(EmkPassFactoryEntity.class, emkPassFactory.getId());
			req.setAttribute("emkPassFactoryPage", emkPassFactory);
		}
		return new ModelAndView("com/emk/outforum/passfactory/emkPassFactory-add");
	}
	/**
	 * 离厂放行单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkPassFactoryEntity emkPassFactory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkPassFactory.getId())) {
			emkPassFactory = emkPassFactoryService.getEntity(EmkPassFactoryEntity.class, emkPassFactory.getId());
			req.setAttribute("emkPassFactoryPage", emkPassFactory);
		}
		return new ModelAndView("com/emk/outforum/passfactory/emkPassFactory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkPassFactoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkPassFactoryEntity emkPassFactory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkPassFactoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkPassFactory, request.getParameterMap());
		List<EmkPassFactoryEntity> emkPassFactorys = this.emkPassFactoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"离厂放行单");
		modelMap.put(NormalExcelConstants.CLASS,EmkPassFactoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("离厂放行单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkPassFactorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkPassFactoryEntity emkPassFactory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"离厂放行单");
    	modelMap.put(NormalExcelConstants.CLASS,EmkPassFactoryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("离厂放行单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkPassFactoryEntity> listEmkPassFactoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkPassFactoryEntity.class,params);
				for (EmkPassFactoryEntity emkPassFactory : listEmkPassFactoryEntitys) {
					emkPassFactoryService.save(emkPassFactory);
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
	@ApiOperation(value="离厂放行单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkPassFactoryEntity>> list() {
		List<EmkPassFactoryEntity> listEmkPassFactorys=emkPassFactoryService.getList(EmkPassFactoryEntity.class);
		return Result.success(listEmkPassFactorys);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取离厂放行单信息",notes="根据ID获取离厂放行单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkPassFactoryEntity task = emkPassFactoryService.get(EmkPassFactoryEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取离厂放行单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建离厂放行单")
	public ResponseMessage<?> create(@ApiParam(name="离厂放行单对象")@RequestBody EmkPassFactoryEntity emkPassFactory, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkPassFactoryEntity>> failures = validator.validate(emkPassFactory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkPassFactoryService.save(emkPassFactory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("离厂放行单信息保存失败");
		}
		return Result.success(emkPassFactory);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新离厂放行单",notes="更新离厂放行单")
	public ResponseMessage<?> update(@ApiParam(name="离厂放行单对象")@RequestBody EmkPassFactoryEntity emkPassFactory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkPassFactoryEntity>> failures = validator.validate(emkPassFactory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkPassFactoryService.saveOrUpdate(emkPassFactory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新离厂放行单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新离厂放行单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除离厂放行单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkPassFactoryService.deleteEntityById(EmkPassFactoryEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("离厂放行单删除失败");
		}

		return Result.success();
	}
}
