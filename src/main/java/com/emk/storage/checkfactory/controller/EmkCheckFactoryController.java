package com.emk.storage.checkfactory.controller;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.storage.checkfactory.entity.EmkCheckFactoryEntity;
import com.emk.storage.checkfactory.entity.EmkCheckFactoryEntity2;
import com.emk.storage.checkfactory.service.EmkCheckFactoryServiceI;

import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.storage.customuse.entity.EmkCustomUseEntity;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntity;
import com.emk.util.ApprovalUtil;
import com.emk.util.ParameterUtil;

import com.emk.util.Utils;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
 * @Description: 验厂申请表
 * @author onlineGenerator
 * @date 2018-10-26 14:58:55
 * @version V1.0   
 *
 */
@Api(value="EmkCheckFactory",description="验厂申请表",tags="emkCheckFactoryController")
@Controller
@RequestMapping("/emkCheckFactoryController")
public class EmkCheckFactoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkCheckFactoryController.class);

	@Autowired
	private EmkCheckFactoryServiceI emkCheckFactoryService;

	@Autowired
	private Validator validator;



	/**
	 * 验厂申请表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/storage/checkfactory/emkCheckFactoryList");
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
	public void datagrid(EmkCheckFactoryEntity emkCheckFactory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkCheckFactoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkCheckFactory, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkCheckFactoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除验厂申请表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkCheckFactoryEntity emkCheckFactory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkCheckFactory = systemService.getEntity(EmkCheckFactoryEntity.class, emkCheckFactory.getId());
		message = "验厂申请表删除成功";
		try{
			emkCheckFactoryService.delete(emkCheckFactory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "验厂申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除验厂申请表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "验厂申请表删除成功";
		try{
			for(String id:ids.split(",")){
				EmkCheckFactoryEntity emkCheckFactory = systemService.getEntity(EmkCheckFactoryEntity.class, 
				id
				);
				emkCheckFactoryService.delete(emkCheckFactory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "验厂申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加验厂申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkCheckFactoryEntity2 emkCheckFactory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "验厂申请表添加成功";
		try{
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			emkCheckFactory.setState("0");
			emkCheckFactory.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
			Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(ycsqbh, 2)),0)+1 AS signed) orderNum from emk_check_factory");
			emkCheckFactory.setYcsqbh("YCSQ" + DateUtils.format(new Date(), "yyMMdd") + emkCheckFactory.getCusNum() + String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));
			emkCheckFactory.setYcstate("0");
			emkCheckFactoryService.save(emkCheckFactory);

			//type 工单类型，workNum 单号，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
			EmkApprovalEntity approvalEntity = new EmkApprovalEntity();
			EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
			ApprovalUtil.saveApproval(approvalEntity,1,emkCheckFactory.getYcsqbh(),emkCheckFactory.getId(),user);
			systemService.save(approvalEntity);
			ApprovalUtil.saveApprovalDetail(approvalDetailEntity,approvalEntity.getId(),"验厂申请单","checkfactoryTask","提交",user);
			systemService.save(approvalDetailEntity);

			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "验厂申请表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新验厂申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkCheckFactoryEntity2 emkCheckFactory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "验厂申请表更新成功";
		EmkCheckFactoryEntity t = emkCheckFactoryService.get(EmkCheckFactoryEntity.class, emkCheckFactory.getId());
		try {
			if(t.getState().equals("1")){
				j.setMsg("验厂申请已提交，无法修改");
				j.setSuccess(false);
				return j;
			}
			if(t.getState().equals("2")){
				j.setMsg("验厂申请完成，无法修改");
				j.setSuccess(false);
				return j;
			}

			MyBeanUtils.copyBeanNotNull2Bean(emkCheckFactory, t);
			emkCheckFactoryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "验厂申请表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 验厂申请表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkCheckFactoryEntity emkCheckFactory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkCheckFactory.getId())) {
			emkCheckFactory = emkCheckFactoryService.getEntity(EmkCheckFactoryEntity.class, emkCheckFactory.getId());
			req.setAttribute("emkCheckFactoryPage", emkCheckFactory);
		}
		return new ModelAndView("com/emk/storage/checkfactory/emkCheckFactory-add");
	}
	/**
	 * 验厂申请表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkCheckFactoryEntity emkCheckFactory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkCheckFactory.getId())) {
			emkCheckFactory = emkCheckFactoryService.getEntity(EmkCheckFactoryEntity.class, emkCheckFactory.getId());
			req.setAttribute("emkCheckFactoryPage", emkCheckFactory);

			EmkCustomUseEntity customUseEntity = systemService.findUniqueByProperty(EmkCustomUseEntity.class,"cusNum",emkCheckFactory.getCusNum());
			req.setAttribute("customUseEntity",customUseEntity);

			try {
				Map countMap = MyBeanUtils.culBeanCounts(emkCheckFactory);
				req.setAttribute("countMap", countMap);
				double a=0,b=0;
				a = Double.parseDouble(countMap.get("finishColums").toString())-7;
				b = Double.parseDouble(countMap.get("Colums").toString())-9;
				DecimalFormat df = new DecimalFormat("#.00");
				req.setAttribute("recent", df.format(a*100/b));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("com/emk/storage/checkfactory/emkCheckFactory-update");
	}
	@RequestMapping(params = "goUpdate2")
	public ModelAndView goUpdate2(EmkCheckFactoryEntity emkCheckFactory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkCheckFactory.getId())) {
			EmkFactoryArchivesEntity emkFactoryArchivesEntity = systemService.get(EmkFactoryArchivesEntity.class,emkCheckFactory.getId());
			List<EmkCheckFactoryEntity> emkCheckFactoryEntityList = systemService.findHql("from EmkCheckFactoryEntity where (state=1 or state=2) and gysCode=?",emkFactoryArchivesEntity.getCompanyCode());
			if(Utils.notEmpty(emkCheckFactoryEntityList)){
				emkCheckFactory = emkCheckFactoryEntityList.get(0);
				req.setAttribute("emkCheckFactoryPage", emkCheckFactory);
				EmkCustomUseEntity customUseEntity = systemService.findUniqueByProperty(EmkCustomUseEntity.class,"cusNum",emkCheckFactory.getCusNum());
				req.setAttribute("customUseEntity",customUseEntity);
			}
		}
		return new ModelAndView("com/emk/storage/checkfactory/emkCheckFactory-update2");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkCheckFactoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkCheckFactoryEntity emkCheckFactory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkCheckFactoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkCheckFactory, request.getParameterMap());
		List<EmkCheckFactoryEntity> emkCheckFactorys = this.emkCheckFactoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"验厂申请表");
		modelMap.put(NormalExcelConstants.CLASS,EmkCheckFactoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("验厂申请表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkCheckFactorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkCheckFactoryEntity emkCheckFactory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"验厂申请表");
    	modelMap.put(NormalExcelConstants.CLASS,EmkCheckFactoryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("验厂申请表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkCheckFactoryEntity> listEmkCheckFactoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkCheckFactoryEntity.class,params);
				for (EmkCheckFactoryEntity emkCheckFactory : listEmkCheckFactoryEntitys) {
					emkCheckFactoryService.save(emkCheckFactory);
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
	@ApiOperation(value="验厂申请表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkCheckFactoryEntity>> list() {
		List<EmkCheckFactoryEntity> listEmkCheckFactorys=emkCheckFactoryService.getList(EmkCheckFactoryEntity.class);
		return Result.success(listEmkCheckFactorys);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取验厂申请表信息",notes="根据ID获取验厂申请表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkCheckFactoryEntity task = emkCheckFactoryService.get(EmkCheckFactoryEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取验厂申请表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建验厂申请表")
	public ResponseMessage<?> create(@ApiParam(name="验厂申请表对象")@RequestBody EmkCheckFactoryEntity emkCheckFactory, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkCheckFactoryEntity>> failures = validator.validate(emkCheckFactory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkCheckFactoryService.save(emkCheckFactory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("验厂申请表信息保存失败");
		}
		return Result.success(emkCheckFactory);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新验厂申请表",notes="更新验厂申请表")
	public ResponseMessage<?> update(@ApiParam(name="验厂申请表对象")@RequestBody EmkCheckFactoryEntity emkCheckFactory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkCheckFactoryEntity>> failures = validator.validate(emkCheckFactory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkCheckFactoryService.saveOrUpdate(emkCheckFactory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新验厂申请表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新验厂申请表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除验厂申请表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkCheckFactoryService.deleteEntityById(EmkCheckFactoryEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("验厂申请表删除失败");
		}

		return Result.success();
	}

	@RequestMapping(params="doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(EmkCheckFactoryEntity2 emkCheckFactoryEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "验厂申请表提交成功";
		try {
			int flag = 0;
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

			if ((emkCheckFactoryEntity.getId() == null) || (emkCheckFactoryEntity.getId().isEmpty())) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkCheckFactoryEntity checkFactoryEntity = systemService.getEntity(EmkCheckFactoryEntity.class, id);
					if (!checkFactoryEntity.getState().equals("0")) {
						message = "存在已提交的验厂申请表，请重新选择在提交！";
						j.setSuccess(false);
						flag = 1;
						break;
					}

					List<EmkCheckFactoryEntity> emkCheckFactoryEntityList = systemService.findHql("from EmkCheckFactoryEntity where state=1 and gysCode=?",checkFactoryEntity.getGysCode());
					if (Utils.notEmpty(emkCheckFactoryEntityList) && emkCheckFactoryEntityList.size()>0) {
						message = "已存在该供应商的验厂申请单，请重新选择在提交！";
						j.setSuccess(false);
						flag = 1;
						break;
					}
				}
			}else{
				map.put("ids", emkCheckFactoryEntity.getId());
			}
			Map<String, Object> variables = new HashMap();
			if (flag == 0) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkCheckFactoryEntity t = emkCheckFactoryService.get(EmkCheckFactoryEntity.class, id);
					t.setState("1");
					EmkFactoryArchivesEntity a = systemService.findUniqueByProperty(EmkFactoryArchivesEntity.class,"companyCode",t.getGysCode());
					EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",a.getId());

					a.setState("50");
					b.setStatus(50);
					systemService.saveOrUpdate(t);
					List<Task> task = taskService.createTaskQuery().taskAssignee(a.getId()).list();
					if(Utils.notEmpty(task)){
						Task task1 = task.get(task.size() - 1);
						if(task1.getTaskDefinitionKey().equals("ycsqbTask")) {
							variables.put("optUser",a.getId());
							taskService.complete(task1.getId(), variables);
							TSUser bpmUser = systemService.get(TSUser.class,b.getCommitId());
							saveSmsAndEmailForMany("业务经理","【业务员】验厂申请表","您有【"+b.getCreateName()+"】提交的验厂申请表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
						}
					}
					systemService.saveOrUpdate(a);
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
	public ModelAndView goWork(EmkCheckFactoryEntity emkCheckFactoryEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkCheckFactoryEntity.getId())) {
			emkCheckFactoryEntity = emkCheckFactoryService.getEntity(EmkCheckFactoryEntity.class, emkCheckFactoryEntity.getId());
			req.setAttribute("state", emkCheckFactoryEntity.getState());
		}
		return new ModelAndView("com/emk/storage/checkfactory/emkCheckFactory-work");

	}

	@RequestMapping(params="goTime")
	public ModelAndView goTime(EmkCheckFactoryEntity emkCheckFactoryEntity, HttpServletRequest req, DataGrid dataGrid) {
		EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkCheckFactoryEntity.getId());
		List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=?",approvalEntity.getId());
		req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
		req.setAttribute("approvalEntity", approvalEntity);
		req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
		return new ModelAndView("com/emk/storage/checkfactory/time");
	}
}
