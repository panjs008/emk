package com.emk.storage.factoryarchives.controller;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.storage.checkfactory.entity.EmkCheckFactoryEntity;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntity;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntityA;
import com.emk.storage.factoryarchives.service.EmkFactoryArchivesServiceI;

import java.io.File;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.util.*;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.core.util.MyBeanUtils;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
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
			emkFactoryArchives.setState("0");
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
		Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		EmkFactoryArchivesEntity t = emkFactoryArchivesService.get(EmkFactoryArchivesEntity.class, emkFactoryArchives.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(emkFactoryArchives, t);
			emkFactoryArchivesService.saveOrUpdate(t);

			if("1".equals(map.get("isPass"))){
				EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());

				t.setState("1");
				b.setStatus(1);

				systemService.saveOrUpdate(t);
				systemService.saveOrUpdate(b);
				Map<String, Object> variables = new HashMap();
				List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
				if(Utils.notEmpty(task)){
					Task task1 = task.get(task.size() - 1);
					if(task1.getTaskDefinitionKey().equals("checkfactoryTask")) {
						variables.put("optUser",t.getId());
						taskService.complete(task1.getId(), variables);
						TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
						saveSmsAndEmailForMany("业务经理","【业务员】工厂信息表","您有【"+b.getCreateName()+"】重新提交的工厂信息表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
					}
				}
			}
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
	@RequestMapping(params = "goUpdate2")
	public ModelAndView goUpdate2(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkFactoryArchives.getId())) {
			emkFactoryArchives = emkFactoryArchivesService.getEntity(EmkFactoryArchivesEntity.class, emkFactoryArchives.getId());
			req.setAttribute("emkFactoryArchivesPage", emkFactoryArchives);
		}
		return new ModelAndView("com/emk/storage/factoryarchives/emkFactoryArchives-update2");
	}
	@RequestMapping(params = "goUpdate3")
	public ModelAndView goUpdate3(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkFactoryArchives.getId())) {
			emkFactoryArchives = emkFactoryArchivesService.getEntity(EmkFactoryArchivesEntity.class, emkFactoryArchives.getId());
			req.setAttribute("emkFactoryArchivesPage", emkFactoryArchives);

			List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE='A01A05' order by code asc");
			req.setAttribute("codeList", codeList);

		}
		return new ModelAndView("com/emk/storage/factoryarchives/emkFactoryArchives-update3");
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

	@RequestMapping(params="doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(EmkFactoryArchivesEntity emkFactoryArchivesEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "验厂申请表提交成功";
		try {
			int flag = 0;
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

			if ((emkFactoryArchivesEntity.getId() == null) || (emkFactoryArchivesEntity.getId().isEmpty())) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkFactoryArchivesEntity factoryArchivesEntity = systemService.getEntity(EmkFactoryArchivesEntity.class, id);
					if (!factoryArchivesEntity.getState().equals("0")) {
						message = "存在已提交的验厂申请表，请重新选择在提交！";
						j.setSuccess(false);
						flag = 1;
						break;
					}
				}
			}else{
				map.put("ids", emkFactoryArchivesEntity.getId());
			}
			Map<String, Object> variables = new HashMap();
			if (flag == 0) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkFactoryArchivesEntity t = emkFactoryArchivesService.get(EmkFactoryArchivesEntity.class, id);
					variables.put("optUser", t.getId());
					EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
					if(Utils.isEmpty(b)){
						b = new EmkApprovalEntity();
						EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
						ApprovalUtil.saveApproval(b,1,t.getArchivesNo(),t.getId(),user);
						systemService.save(b);
						ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"工厂信息表","checkfactoryTask","提交",user);
						systemService.save(approvalDetailEntity);
					}

					List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
					TSUser makerUser = systemService.findUniqueByProperty(TSUser.class,"userName",t.getCreateBy());
					TSUser bpmUser = null;
					if (task.size() > 0) {
						bpmUser = systemService.get(TSUser.class,b.getBpmSherId());
					}else{
						bpmUser = systemService.get(TSUser.class,b.getCommitId());
					}
					//保存审批意见
					EmkApprovalDetailEntity approvalDetail = ApprovalUtil.saveApprovalDetail(b.getId(),user,b,map.get("advice"));
					t.setState("1");
					variables.put("optUser", t.getId());
					if (task.size() > 0) {
						Task task1 = (Task)task.get(task.size() - 1);
						if (task1.getTaskDefinitionKey().equals("checkfactoryTask")) {
							taskService.complete(task1.getId(), variables);
							t.setState("1");
							b.setStatus(1);
							saveApprvoalDetail(approvalDetail,"重新提交的工厂信息表","checkfactoryTask",0,"重新提交工厂信息表");
							saveSmsAndEmailForMany("业务经理","【业务员】工厂信息表","您有【"+b.getCreateName()+"】重新提交的订单表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
						}
						if(task1.getTaskDefinitionKey().equals("checkTask")) {
							if (map.get("isPass").equals("0")){
								taskService.complete(task1.getId(), variables);
								b.setStatus(3);
								approvalDetail.setBpmName("业务经理审核");
								t.setState("3");
								approvalDetail.setApproveStatus(0);
								saveSmsAndEmailForMany("总经理","业务经理审核","您有【"+b.getCreateName()+"】审核通过的工厂信息表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());

							}else{
								saveApprvoalDetail(approvalDetail,"业务经理审核","checkTask",1,"回退");
								backProcess(task1.getProcessInstanceId(),"checkTask","checkfactoryTask","【业务员】工厂信息表");
								t.setState("0");
								b.setStatus(0);
								b.setBpmStatus("1");
								saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的工厂信息表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}
						}
						if(task1.getTaskDefinitionKey().equals("zjlshTask")) {
							if (map.get("isPass").equals("0")){
								taskService.complete(task1.getId(), variables);
								b.setStatus(48);
								approvalDetail.setBpmName("总经理审批");
								t.setState("48");
								approvalDetail.setApproveStatus(0);

								bpmUser = systemService.get(TSUser.class,b.getCommitId());
								saveSmsAndEmailForOne("总经理审批","您有【"+user.getRealName()+"】审核通过的订单表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}
						}
						if(task1.getTaskDefinitionKey().equals("ywjlshTask")) {
							if (map.get("isPass").equals("0")){
								variables.put("isPass2","0");
								taskService.complete(task1.getId(), variables);
								b.setStatus(3);
								approvalDetail.setBpmName("业务经理审核");
								t.setState("3");
								approvalDetail.setApproveStatus(0);
								saveSmsAndEmailForMany("验厂经理","业务经理审核","您有【"+b.getCreateName()+"】审核通过的验厂申请表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
							}else{
								List<EmkCheckFactoryEntity> emkCheckFactoryEntityList = systemService.findHql("from EmkCheckFactoryEntity where state=1 and gysCode=?",t.getCompanyCode());
								if(Utils.notEmpty(emkCheckFactoryEntityList)){
									EmkCheckFactoryEntity emkCheckFactoryEntity = emkCheckFactoryEntityList.get(0);
									emkCheckFactoryEntity.setState("0");
									systemService.saveOrUpdate(emkCheckFactoryEntity);
								}
								saveApprvoalDetail(approvalDetail,"业务经理审核","ywjlshTask",1,"回退");
								backProcess(task1.getProcessInstanceId(),"ywjlshTask","ycsqbTask","【业务员】验厂申请表");
								t.setState("21");
								b.setStatus(21);
								b.setBpmStatus("1");
								saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的工厂信息表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}
						}
						if(task1.getTaskDefinitionKey().equals("fpycyTask")) {
							if (map.get("isPass").equals("0")){
								taskService.complete(task1.getId(), variables);
								b.setStatus(51);
								b.setNextBpmSher(map.get("realName"));
								b.setNextBpmSherId(map.get("userName"));
								approvalDetail.setBpmName("【验厂部经理】分配验厂员");
								t.setState("51");
								approvalDetail.setApproveStatus(0);

								bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());
								saveSmsAndEmailForOne("【验厂部经理】分配验厂员","您有【"+user.getRealName()+"】审核通过的验厂申请表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}else{
								saveApprvoalDetail(approvalDetail,"【验厂部经理】分配验厂员","fpycyTask",1,"回退");
								backProcess(task1.getProcessInstanceId(),"fpycyTask","ywjlshTask","业务经理审核");
								systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and act_id_=? ",task1.getProcessInstanceId(),"exclusivegateway2");
								t.setState("4");
								b.setStatus(4);
								b.setBpmStatus("1");
								saveSmsAndEmailForOne("【验厂部经理】分配验厂员","您有【"+user.getRealName()+"】回退的验厂申请表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}
						}
						if(task1.getTaskDefinitionKey().equals("cyTask")) {
							if (map.get("isPass").equals("0")){
								taskService.complete(task1.getId(), variables);
								b.setStatus(52);
								approvalDetail.setBpmName("【验厂员】执行验厂");
								t.setState("52");
								approvalDetail.setApproveStatus(0);
							}
						}
						if(task1.getTaskDefinitionKey().equals("bgTask")) {
							if (map.get("isPass").equals("0")){
								List<EmkCheckFactoryEntity> emkCheckFactoryEntityList = systemService.findHql("from EmkCheckFactoryEntity where state=1 and gysCode=?",t.getCompanyCode());
								if(Utils.notEmpty(emkCheckFactoryEntityList)){
									EmkCheckFactoryEntity emkCheckFactoryEntity = emkCheckFactoryEntityList.get(0);
									emkCheckFactoryEntity.setFileName(map.get("fileName"));
									emkCheckFactoryEntity.setFileNameUrl(map.get("fileNameUrl"));
									systemService.saveOrUpdate(emkCheckFactoryEntity);
								}
								taskService.complete(task1.getId(), variables);
								b.setStatus(53);
								approvalDetail.setBpmName("验厂报告");
								t.setState("53");
								approvalDetail.setApproveStatus(0);

								saveSmsAndEmailForMany("验厂经理","验厂报告","您有【"+b.getCreateName()+"】提交的的验厂报告，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
							}
						}
						if(task1.getTaskDefinitionKey().equals("ycbshTask")) {
							if (map.get("isPass").equals("0")){
								List<EmkCheckFactoryEntity> emkCheckFactoryEntityList = systemService.findHql("from EmkCheckFactoryEntity where state=1 and gysCode=?",t.getCompanyCode());
								if(Utils.notEmpty(emkCheckFactoryEntityList)){
									EmkCheckFactoryEntity emkCheckFactoryEntity = emkCheckFactoryEntityList.get(0);
									emkCheckFactoryEntity.setState("2");
									systemService.saveOrUpdate(emkCheckFactoryEntity);
								}
								variables.put("isPass2","0");
								taskService.complete(task1.getId(), variables);
								b.setStatus(2);
								approvalDetail.setBpmName("【验厂部经理】验厂部审核");
								t.setState("2");
								approvalDetail.setApproveStatus(0);

								bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());
								saveSmsAndEmailForOne("【验厂部经理】验厂部审核","您有【"+user.getRealName()+"】审核通过的验厂报告，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}else{
								saveApprvoalDetail(approvalDetail,"【验厂部经理】验厂部审核","ycbshTask",1,"回退");
								backProcess(task1.getProcessInstanceId(),"ycbshTask","bgTask","验厂报告");
								backProcess(task1.getProcessInstanceId(),"bgTask","cyTask","【验厂员】执行验厂");
								t.setState("51");
								b.setStatus(51);
								b.setBpmStatus("1");
								saveSmsAndEmailForOne("【验厂部经理】验厂部审核","您有【"+user.getRealName()+"】回退的验厂报告，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}
						}
						systemService.save(approvalDetail);

					}else {
						ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("checkfactory", "emkFactoryArchivesEntity", variables);
						task = taskService.createTaskQuery().taskAssignee(id).list();
						Task task1 = task.get(task.size() - 1);
						taskService.complete(task1.getId(), variables);

						t.setState("1");
						b.setStatus(1);
						b.setBpmStatus("0");
						b.setProcessName("【业务员】工厂信息表");

						saveApprvoalDetail(approvalDetail,"提交的【业务员】工厂信息表","checkfactoryTask",0,"提交【业务员】工厂信息表");
						saveSmsAndEmailForMany("业务经理","【业务员】工厂信息表","您有【"+b.getCreateName()+"】提交的【业务员】工厂信息表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
					}

					systemService.saveOrUpdate(t);
					systemService.saveOrUpdate(b);
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		catch (Exception e) {
			e.printStackTrace();
			message = "验厂申请表提交失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params="goWork")
	public ModelAndView goWork(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkFactoryArchives.getId())) {
			emkFactoryArchives = emkFactoryArchivesService.getEntity(EmkFactoryArchivesEntity.class, emkFactoryArchives.getId());
			req.setAttribute("emkFactoryArchivesPage", emkFactoryArchives);
		}
		return new ModelAndView("com/emk/storage/factoryarchives/emkFactoryArchives-work");
	}

	@RequestMapping(params="goTime")
	public ModelAndView goTime(EmkFactoryArchivesEntity emkFactoryArchives, HttpServletRequest req, DataGrid dataGrid) {
		EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkFactoryArchives.getId());
		List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=?",approvalEntity.getId());
		req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
		req.setAttribute("approvalEntity", approvalEntity);
		req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
		return new ModelAndView("com/emk/storage/checkfactory/time");
	}
}
