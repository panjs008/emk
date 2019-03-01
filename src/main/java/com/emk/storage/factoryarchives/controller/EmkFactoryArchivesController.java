package com.emk.storage.factoryarchives.controller;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntity;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntityA;
import com.emk.storage.factoryarchives.service.EmkFactoryArchivesServiceI;

import java.io.File;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.util.*;
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
 * @Description: 工厂档案
 * @author onlineGenerator
 * @date 2019-02-22 09:26:23
 * @version V1.0   
 *
 */
@Api(value="EmkFactoryArchives",description="工厂档案",tags="emkFactoryArchivesController")
@Controller
@RequestMapping("/emkFactoryArchivesController")
public class EmkFactoryArchivesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkFactoryArchivesController.class);

	@Autowired
	private EmkFactoryArchivesServiceI emkFactoryArchivesService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 工厂档案列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/storage/factoryarchives/emkFactoryArchivesList");
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
	public void datagrid(EmkFactoryArchivesEntity emkFactoryArchives,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkFactoryArchivesEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkFactoryArchives, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkFactoryArchivesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除工厂档案
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkFactoryArchives = systemService.getEntity(EmkFactoryArchivesEntity.class, emkFactoryArchives.getId());
		message = "工厂档案删除成功";
		try{
			emkFactoryArchivesService.delete(emkFactoryArchives);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工厂档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工厂档案
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工厂档案删除成功";
		try{
			for(String id:ids.split(",")){
				EmkFactoryArchivesEntity emkFactoryArchives = systemService.getEntity(EmkFactoryArchivesEntity.class, 
				id
				);
				emkFactoryArchivesService.delete(emkFactoryArchives);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工厂档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工厂档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工厂档案添加成功";
		try{
			Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(archives_no, 3)),0)+1 AS signed) orderNum from emk_factory_archives ");
			emkFactoryArchives.setArchivesNo("GYSDA" + String.format("%04d", Integer.parseInt(orderNum.get("orderNum").toString())));
			emkFactoryArchivesService.save(emkFactoryArchives);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工厂档案添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新工厂档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工厂档案更新成功";
		EmkFactoryArchivesEntity t = emkFactoryArchivesService.get(EmkFactoryArchivesEntity.class, emkFactoryArchives.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(emkFactoryArchives, t);
			emkFactoryArchivesService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工厂档案更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "doPrintPDF")
	public String doPrintPDF(String ids, EmkFactoryArchivesEntity emkFactoryArchivesEntity, HttpServletRequest request, HttpServletResponse response) {
		String message = null;

		try {
			for (String id : ids.split(",")) {
				String fileName = "c:\\PDF\\"+ DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
				File file = new File(fileName);
				File dir = file.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				emkFactoryArchivesEntity = systemService.getEntity(EmkFactoryArchivesEntity.class, id);
				EmkFactoryArchivesEntityA emkFactoryArchivesEntityA = new EmkFactoryArchivesEntityA();
				MyBeanUtils.copyBeanNotNull2Bean(emkFactoryArchivesEntity,emkFactoryArchivesEntityA);


				Map type = systemService.findOneForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='trade' and typecode=?",emkFactoryArchivesEntityA.getGuoJia());
				if(Utils.notEmpty(type)){
					emkFactoryArchivesEntityA.setGuoJia(type.get("typename").toString());
				}
				type = systemService.findOneForJdbc("select code,name from t_s_category where code=?",emkFactoryArchivesEntityA.getShengFen());
				if(Utils.notEmpty(type)) {
					emkFactoryArchivesEntityA.setShengFen(type.get("name").toString());
				}
				type = systemService.findOneForJdbc("select code,name from t_s_category where code=?",emkFactoryArchivesEntityA.getChengShi());
				if(Utils.notEmpty(type)) {
					emkFactoryArchivesEntityA.setChengShi(type.get("name").toString());
				}
				new createPdf(file).generateFactoryArchivesPDF(emkFactoryArchivesEntityA);
				String fFileName = "c:\\PDF\\G"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
				WaterMark.waterMark(fileName,fFileName, "供应商档案表");
				file.delete();
				WebFileUtils.downLoad(fFileName,response,false);
				file = new File(fFileName);
				file.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "询盘单导出PDF失败";
			throw new BusinessException(e.getMessage());
		}
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	

	/**
	 * 工厂档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtil.format(new Date(), "yyyy-MM-dd"));

		if (StringUtil.isNotEmpty(emkFactoryArchives.getId())) {
			emkFactoryArchives = emkFactoryArchivesService.getEntity(EmkFactoryArchivesEntity.class, emkFactoryArchives.getId());
			req.setAttribute("emkFactoryArchivesPage", emkFactoryArchives);
		}
		return new ModelAndView("com/emk/storage/factoryarchives/emkFactoryArchives-add");
	}
	/**
	 * 工厂档案编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkFactoryArchives.getId())) {
			emkFactoryArchives = emkFactoryArchivesService.getEntity(EmkFactoryArchivesEntity.class, emkFactoryArchives.getId());
			req.setAttribute("emkFactoryArchivesPage", emkFactoryArchives);
		}
		return new ModelAndView("com/emk/storage/factoryarchives/emkFactoryArchives-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkFactoryArchivesController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkFactoryArchivesEntity emkFactoryArchives,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkFactoryArchivesEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkFactoryArchives, request.getParameterMap());
		List<EmkFactoryArchivesEntity> emkFactoryArchivess = this.emkFactoryArchivesService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"工厂档案");
		modelMap.put(NormalExcelConstants.CLASS,EmkFactoryArchivesEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工厂档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkFactoryArchivess);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkFactoryArchivesEntity emkFactoryArchives,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"工厂档案");
    	modelMap.put(NormalExcelConstants.CLASS,EmkFactoryArchivesEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工厂档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkFactoryArchivesEntity> listEmkFactoryArchivesEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkFactoryArchivesEntity.class,params);
				for (EmkFactoryArchivesEntity emkFactoryArchives : listEmkFactoryArchivesEntitys) {
					emkFactoryArchivesService.save(emkFactoryArchives);
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
	@ApiOperation(value="工厂档案列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkFactoryArchivesEntity>> list() {
		List<EmkFactoryArchivesEntity> listEmkFactoryArchivess=emkFactoryArchivesService.getList(EmkFactoryArchivesEntity.class);
		return Result.success(listEmkFactoryArchivess);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取工厂档案信息",notes="根据ID获取工厂档案信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkFactoryArchivesEntity task = emkFactoryArchivesService.get(EmkFactoryArchivesEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取工厂档案信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建工厂档案")
	public ResponseMessage<?> create(@ApiParam(name="工厂档案对象")@RequestBody EmkFactoryArchivesEntity emkFactoryArchives, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkFactoryArchivesEntity>> failures = validator.validate(emkFactoryArchives);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkFactoryArchivesService.save(emkFactoryArchives);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工厂档案信息保存失败");
		}
		return Result.success(emkFactoryArchives);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新工厂档案",notes="更新工厂档案")
	public ResponseMessage<?> update(@ApiParam(name="工厂档案对象")@RequestBody EmkFactoryArchivesEntity emkFactoryArchives) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkFactoryArchivesEntity>> failures = validator.validate(emkFactoryArchives);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkFactoryArchivesService.saveOrUpdate(emkFactoryArchives);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新工厂档案信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新工厂档案信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除工厂档案")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkFactoryArchivesService.deleteEntityById(EmkFactoryArchivesEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工厂档案删除失败");
		}

		return Result.success();
	}
}
