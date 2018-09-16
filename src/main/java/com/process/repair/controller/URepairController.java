package com.process.repair.controller;
import com.emk.util.ParameterUtil;
import com.emk.util.WebFileUtils;
import com.process.repair.entity.URepairEntity;
import com.process.repair.entity.URepairEntityA;
import com.process.repair.service.URepairServiceI;

import java.io.InputStream;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.process.repairattch.entity.URepairAttchEntity;
import com.service.custom.entity.YmkCustomEntity;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.*;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.util.*;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.CategoryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
 * @Description: 报修单表
 * @author onlineGenerator
 * @date 2018-03-22 21:28:21
 * @version V1.0
 *
 */
@Api(value="URepair",description="报修单表",tags="uRepairController")
@Controller
@RequestMapping("/uRepairController")
public class URepairController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(URepairController.class);
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	RepositoryService repositoryService = processEngine.getRepositoryService();
	ManagementService managementService = processEngine.getManagementService();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
	HistoryService historyService = processEngine.getHistoryService();
	TaskService taskService = processEngine.getTaskService();
	@Autowired
	private URepairServiceI uRepairService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CategoryServiceI categoryService;


	/**
	 * 报修单表列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList");
	}

	/**
	 * 手机端报修单表列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "mReapirList")
	public ModelAndView mReapirList(HttpServletRequest request) {
		TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		List<URepairEntity> uRepairEntityList = systemService.findHql("from URepairEntity where userId=? order by applyDate desc",user.getUserName());
		request.setAttribute("uRepairEntityList",uRepairEntityList);
		return new ModelAndView("com/process/repair/mRepairList");
	}

	/**
	 * 报修单表处理页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "gomReapirInfo")
	public ModelAndView gomReapirInfo(URepairEntity uRepair, HttpServletRequest req) {
		uRepair = uRepairService.getEntity(URepairEntity.class, uRepair.getId());
		req.setAttribute("uRepairPage", uRepair);
		List<URepairAttchEntity> attchEntities = systemService.findHql("from URepairAttchEntity where repairId=?",uRepair.getId());
		req.setAttribute("attchEntities",attchEntities);
		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee(uRepair.getId()).list();
		req.setAttribute("tasks",tasks);
		return new ModelAndView("com/process/repair/mRepair-mobile");
	}


	/**
	 * 手机报修单 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "mRepair")
	public ModelAndView mRepair(URepairEntity uRepair, HttpServletRequest req) {
		TSUser user = (TSUser) req.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		if(req.getAttribute("message") == null){
			req.setAttribute("message","-1");
		}else{
			req.setAttribute("message",req.getAttribute("message"));
		}

		Map orderNum = systemService.findOneForJdbc("select count(0)+1 repairNum from u_repair where sys_org_code=?",user.getCurrentDepart().getOrgCode());
		req.setAttribute("repairNum", "D"+ org.apache.tools.ant.util.DateUtils.format(new Date(),"yyyyMMdd")+String.format("%06d", Integer.parseInt(orderNum.get("repairNum").toString())));
		req.setAttribute("applyDate",DateUtils.date2Str(new Date(),DateUtils.datetimeFormat));
		return new ModelAndView("com/process/repair/mRepair-add");
	}
	/**
	 * 手机报修单 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "mRepairHis")
	public ModelAndView mRepairHis(URepairEntity uRepair, HttpServletRequest req) {
		/*TSUser user = (TSUser) req.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		Map orderNum = systemService.findOneForJdbc("select count(0)+1 repairNum from u_repair where sys_org_code=?",user.getCurrentDepart().getOrgCode());
		req.setAttribute("repairNum", "D"+ org.apache.tools.ant.util.DateUtils.format(new Date(),"yyyyMMdd")+String.format("%06d", Integer.parseInt(orderNum.get("repairNum").toString())));
		req.setAttribute("applyDate",DateUtils.date2Str(new Date(),DateUtils.datetimeFormat));*/
		return new ModelAndView("com/process/repair/mRepair-his");
	}

	/**
	 * 今日新建工单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "todayList")
	public ModelAndView todayList(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList-today");
	}



	/**
	 * 今日关闭工单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "todayCloseList")
	public ModelAndView todayCloseList(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList-todayClose");
	}

	/**
	 * 待受理工单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "waitList")
	public ModelAndView waitList(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList-wait");
	}

	/**
	 * 待分配工单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "dfpList")
	public ModelAndView fenpeiList(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList-dpf");
	}

	/**
	 * 待派工单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "dpList")
	public ModelAndView dpList(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList-dp");
	}

	/**
	 * 待派工单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "workList")
	public ModelAndView workList(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList-work");
	}

	/**
	 * 已完成工单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "finishList")
	public ModelAndView finishList(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList-finish");
	}

	/**
	 * 已关闭工单列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "closeList")
	public ModelAndView closeList(HttpServletRequest request) {
		return new ModelAndView("com/process/repair/uRepairList-close");
	}

	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "todaydatagrid")
	public void todaydatagrid(URepairEntity uRepair,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(URepairEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, uRepair, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			String startDate = DateUtils.date2Str(new Date(),DateUtils.date_sdf)+" 00:00:00";
			String endDate = DateUtils.date2Str(new Date(),DateUtils.date_sdf)+" 23:59:59";
			cq.add(Expression.ge("createDate",DateUtils.str2Date(startDate,DateUtils.datetimeFormat)));
			cq.add(Expression.le("createDate", DateUtils.str2Date(endDate,DateUtils.datetimeFormat)));
			if(!user.getUserName().equals("admin")){
				cq.eq("userId",user.getUserName());
			}

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.uRepairService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "waitdatagrid")
	public void waitdatagrid(URepairEntity uRepair,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(URepairEntity.class, dataGrid);
		//查询条件组装器
		try{
			//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			if(!user.getUserName().equals("admin")){
				if(user.getUserKey().equals("业主管理员")){
					uRepair.setStatus("1");
					cq.eq("checkUserId",user.getUserName());
				}else if(user.getUserKey().equals("服务台管理员")){
					uRepair.setStatus("6");
					cq.eq("serviceUserId",user.getUserName());
				}
			}

			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, uRepair, request.getParameterMap());

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.uRepairService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "dpdatagrid")
	public void dpdatagrid(URepairEntity uRepair,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(URepairEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, uRepair, request.getParameterMap());
		try{
			//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			if(!user.getUserName().equals("admin")){
				if(user.getUserKey().equals("设备中标商")){
					cq.eq("cusId",user.getUserName());
				}else if(user.getUserKey().equals("本地服务商")){
					cq.add(Restrictions.or(
							Restrictions.eq("recevieUserId",user.getUserName()),
							Restrictions.eq("cusId", user.getUserName())
					));
				}
			}

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.uRepairService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * easyui AJAX请求数据
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "workdatagrid")
	public void workdatagrid(URepairEntity uRepair,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(URepairEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, uRepair, request.getParameterMap());
		try{
			//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			if(!user.getUserName().equals("admin")){
				if(user.getUserKey().equals("设备中标商员工")||user.getUserKey().equals("本地服务商员工")){
					cq.add(Restrictions.or(
							Restrictions.eq("recevieUserId",user.getUserName()),
							Restrictions.eq("repairUserId", user.getUserName())
					));
				}
			}

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.uRepairService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(URepairEntity uRepair,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(URepairEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, uRepair, request.getParameterMap());
		try{
			//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			if(!user.getUserName().equals("admin")){
				if(user.getUserKey().equals("业主管理员") || user.getUserKey().equals("业主普通用户")){
					cq.add(Restrictions.or(
							Restrictions.eq("userId",user.getUserName()),
							Restrictions.eq("checkUserId", user.getUserName())
					));
				}else if(user.getUserKey().equals("本地服务商") || user.getUserKey().equals("本地服务商员工")){
					cq.add(Restrictions.or(
							Restrictions.eq("recevieUserId",user.getUserName()),
							Restrictions.eq("kaoheUserId", user.getUserName())
					));;
				}

			}

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.uRepairService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除报修单表
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(URepairEntity uRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		uRepair = systemService.getEntity(URepairEntity.class, uRepair.getId());
		message = "报修单表删除成功";
		try{
			uRepairService.delete(uRepair);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "报修单表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除报修单表
	 *
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "报修单表删除成功";
		try{
			for(String id:ids.split(",")){
				URepairEntity uRepair = systemService.getEntity(URepairEntity.class,
				id
				);
				uRepairService.delete(uRepair);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "报修单表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加报修单表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(URepairEntityA uRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "报修单表添加成功";
		try{
			/*Map code = systemService.findOneForJdbc("SELECT NAME FROM t_s_category WHERE CODE=?",uRepair.getSblx());
			req.setAttribute("sblx",code.get("NAME"));*/
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
//			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//			List<MultipartFile> fileList = multipartRequest.getFiles("file");

			if(map.get("sblxVal").toString().isEmpty() || map.get("sblxVal") == null){
				j.setMsg("设备类型不能为空");
				j.setSuccess(false);
				return j;
			}
			/*if(map.get("ppVal").toString().isEmpty() || map.get("ppVal") == null){
				j.setMsg("品牌不能为空");
				j.setSuccess(false);
				return j;
			}*/
			/*if(map.get("xhVal").toString().isEmpty() || map.get("xhVal") == null){
				j.setMsg("型号不能为空");
				j.setSuccess(false);
				return j;
			}*/
			if(map.get("sblx").toString().isEmpty() || map.get("sblx") == null){
				TSCategoryEntity categoryEntity = new TSCategoryEntity();
				TSCategoryEntity parent = new TSCategoryEntity();
				parent.setCode("A02");
				categoryEntity.setName(map.get("sblxVal").toString());
				categoryEntity.setParent(parent);
				categoryService.saveCategory(categoryEntity);
				uRepair.setSblx(categoryEntity.getCode());
				if(map.get("pp").toString().isEmpty() || map.get("pp") == null){
					TSCategoryEntity ppcategoryEntity = new TSCategoryEntity();
					TSCategoryEntity ppparent = new TSCategoryEntity();
					ppparent.setCode(categoryEntity.getCode());
					ppcategoryEntity.setName(map.get("ppVal").toString());
					ppcategoryEntity.setParent(ppparent);
					categoryService.saveCategory(ppcategoryEntity);
					uRepair.setPp(ppcategoryEntity.getCode());

					if(map.get("xh").toString().isEmpty() || map.get("xh") == null){
						TSCategoryEntity xhcategoryEntity = new TSCategoryEntity();
						TSCategoryEntity xhparent = new TSCategoryEntity();
						xhparent.setCode(ppcategoryEntity.getCode());
						xhcategoryEntity.setName(map.get("xhVal").toString());
						xhcategoryEntity.setParent(xhparent);
						categoryService.saveCategory(xhcategoryEntity);
						uRepair.setXh(xhcategoryEntity.getCode());
					}
				}

			}

			uRepair.setStatus("0");
			uRepair.setProcessValue("10");
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			uRepair.setUserId(user.getUserName());
			uRepair.setUserName(user.getRealName());
			uRepairService.save(uRepair);
			Map<String, Object> variables = new HashMap<String,Object>();
			URepairEntityA t = uRepairService.get(URepairEntityA.class, uRepair.getId());
			t.setStatus("1");
			variables.put("inputUser", t.getId());//表示惟一用户
			t.setCheckUserId(user.getCreateBy());
			t.setCheckUserName(user.getCreateName());

			List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
			ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("repair", "uRepair",variables);
			task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
			Task task1 = task.get(task.size()-1);
			taskService.complete(task1.getId(),variables);

			systemService.saveOrUpdate(t);


			if(map.get("imageFileName1") != null && !map.get("imageFileName1").toString().isEmpty()){
				URepairAttchEntity attchEntity = new URepairAttchEntity();
				attchEntity.setRepairId(t.getId());
				attchEntity.setFilePath(map.get("imageFileName1Path").toString());
				attchEntity.setOldFileName(map.get("imageFileName1").toString());
				systemService.saveOrUpdate(attchEntity);
			}
			if(map.get("audioPath") != null && !map.get("audioPath").toString().isEmpty()){
				URepairAttchEntity attchEntity = new URepairAttchEntity();
				attchEntity.setRepairId(t.getId());
				attchEntity.setFilePath(map.get("audioPath").toString());
				attchEntity.setOldFileName(map.get("audioName").toString());
				systemService.saveOrUpdate(attchEntity);
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			j.setSuccess(false);
			message = "报修单表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加微信报修单表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doWxAdd")
	public ModelAndView doWxAdd(@RequestParam MultipartFile[] myfiles, URepairEntityA uRepair, HttpServletRequest request) {
		String message = "0";
		try{
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			uRepair.setBxType("weixin");
			uRepair.setStatus("0");
			uRepair.setProcessValue("10");

			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			uRepair.setUserId(user.getUserName());
			uRepair.setUserName(user.getRealName());
			uRepairService.save(uRepair);
			Map<String, Object> variables = new HashMap<String,Object>();
			URepairEntityA t = uRepairService.get(URepairEntityA.class, uRepair.getId());
			t.setStatus("1");
			variables.put("inputUser", t.getId());//表示惟一用户
			t.setCheckUserId(user.getCreateBy());
			t.setCheckUserName(user.getCreateName());

			List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
			ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("repair", "uRepair",variables);
			task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
			Task task1 = task.get(task.size()-1);
			taskService.complete(task1.getId(),variables);

			systemService.saveOrUpdate(t);

			String savepath=request.getRealPath("/")+"upload/repair/";
			URepairAttchEntity repairAttchEntity = null;
			for(MultipartFile mf : myfiles) {
				if(!mf.isEmpty()){
					String savename = WebFileUtils.saveFile(mf, savepath);
					repairAttchEntity = new URepairAttchEntity();
					repairAttchEntity.setFileName(savename);
					repairAttchEntity.setOldFileName(mf.getOriginalFilename());
					repairAttchEntity.setFilePath("upload/repair/"+savename);
					repairAttchEntity.setRepairId(t.getId());
					repairAttchEntity.setType("0");
					systemService.saveOrUpdate(repairAttchEntity);
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "1";
		}
		request.setAttribute("message",message);
		return mRepair(null,request);
	}

	/**
	 * 更新报修单表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(URepairEntity uRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "报修单表更新成功";
		URepairEntity t = uRepairService.get(URepairEntity.class, uRepair.getId());
		try {
			Map<String, Object> variables = new HashMap<String,Object>();
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			/*if(map.get("workUserName") != null && !map.get("workUserName").toString().isEmpty()){
				uRepair.setRepairUserName(map.get("workUserName").toString());
				uRepair.setRepairUserId(map.get("workUserId").toString());
				uRepair.setRepairTime(map.get("workTime").toString());
				uRepair.setRepairContent(map.get("workContent").toString());
			}
			if(map.get("recevieUserId2") != null && !map.get("recevieUserId2").toString().isEmpty()){
				uRepair.setRepairUserName(map.get("recevieUserName2").toString());
				uRepair.setRepairUserId(map.get("recevieUserId2").toString());
				uRepair.setRepairTime(map.get("recevieDate2").toString());
				uRepair.setRepairContent(map.get("recevieRemark2").toString());
			}
			if(uRepair.getCusId() != null && !uRepair.getCusId().isEmpty()){
				YmkCustomEntity customEntity = systemService.get(YmkCustomEntity.class,uRepair.getCusId());
				uRepair.setCusName(customEntity.getCusName());
			}*/
			t.setStatus("1");
			variables.put("inputUser", t.getId());//表示惟一用户

			List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
			if(task.size()>0){
				Task task1 = task.get(task.size()-1);
				if(task1.getTaskDefinitionKey().equals("checkTask")){
					variables.put("isFinish", uRepair.getIsFinish());
					if(uRepair.getIsFinish().equals("0")){
						/*t.setRepairUserId(map.get("userName").toString());
						t.setRepairUserName(map.get("realName").toString());*/
						t.setRecevieUserId(map.get("userName").toString());
						t.setRecevieUserName(map.get("realName").toString());
						t.setStatus("2");
					}else if(uRepair.getIsFinish().equals("1")){
						t.setServiceUserId(map.get("userName").toString());
						t.setServiceUserName(map.get("realName").toString());
						t.setStatus("6");
					}
					t.setIsFinish(uRepair.getIsFinish());
					t.setCheckContent(map.get("adviceContent").toString());

				}else if(task1.getTaskDefinitionKey().equals("zxRepair")){
					variables.put("isFinish", uRepair.getIsFinish());
					t.setRepairUserId(map.get("userName").toString());
					t.setRepairUserName(map.get("realName").toString());
					/*t.setKaoheUserId(map.get("userName").toString());
					t.setKaoheUserName(map.get("realName").toString());*/
					t.setRecevieRemark(map.get("adviceContent").toString());
					t.setStatus("3");
				}else if(task1.getTaskDefinitionKey().equals("saveRecord")||task1.getTaskDefinitionKey().equals("normalRepairTask")){
					t.setStatus("4");
					t.setKaoheRemark(map.get("adviceContent").toString());
				}else if(task1.getTaskDefinitionKey().equals("serviceTask")){
					variables.put("repairType", uRepair.getRepairType());
					t.setCusId(map.get("userName").toString());
					t.setCusName(map.get("realName").toString());
					t.setServiceContent(map.get("adviceContent").toString());
					t.setStatus("2");

				}else if(task1.getTaskDefinitionKey().equals("systemToService")){
					variables.put("cstype", uRepair.getCsType());
					if(uRepair.getCsType().equals("1")){
						t.setStatus("3");
					}else{
						t.setStatus("2");
					}
					t.setRecevieUserId(map.get("userName").toString());
					t.setRecevieUserName(map.get("realName").toString());
					t.setCusContent(map.get("adviceContent").toString());
				}else if(task1.getTaskDefinitionKey().equals("servicePd")){
					t.setRecevieUserId(map.get("userName").toString());
					t.setRecevieUserName(map.get("realName").toString());
					t.setCusContent(map.get("adviceContent").toString());
				}else if(task1.getTaskDefinitionKey().equals("belongService")){
					TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
					t.setRepairUserId(user.getUserName());
					t.setRepairUserName(user.getRealName());
					t.setRecevieRemark(map.get("adviceContent").toString());
					t.setStatus("3");
				}else if(task1.getTaskDefinitionKey().equals("localService") || task1.getTaskDefinitionKey().equals("tjService")){
					t.setRepairUserId(map.get("userName").toString());
					t.setRepairUserName(map.get("realName").toString());
					t.setRecevieRemark(map.get("adviceContent").toString());
					t.setStatus("3");
				}else if(task1.getTaskDefinitionKey().equals("homeService")){
					TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
					t.setRepairUserId(user.getUserName());
					t.setRepairUserName(user.getRealName());
					t.setLocalContent(map.get("adviceContent").toString());
					t.setStatus("3");
				}else if(task1.getTaskDefinitionKey().equals("normalRepairTask")){
					t.setRepairContent(map.get("adviceContent").toString());
					t.setStatus("4");

				}

				taskService.complete(task1.getId(),variables);
			}else{
				ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("repair", "uRepair",variables);
				task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
				Task task1 = task.get(task.size()-1);
				t.setCheckUserId(map.get("userName").toString());
				t.setCheckUserName(map.get("realName").toString());
				taskService.complete(task1.getId(),variables);
			}
			//MyBeanUtils.copyBeanNotNull2Bean(uRepair, t);
			uRepairService.saveOrUpdate(t);

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "报修单表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 关闭流程
	 *
	 * @return
	 */
	@RequestMapping(params = "endProcess")
	@ResponseBody
	public AjaxJson endProcess(URepairEntity uRepair, HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "关闭流程成功";
		try{
			URepairEntity t = uRepairService.get(URepairEntity.class, uRepair.getId());
			t.setStatus("5");
			MyBeanUtils.copyBeanNotNull2Bean(uRepair, t);
			uRepairService.saveOrUpdate(t);
		}catch(Exception e){
			e.printStackTrace();
			message = "关闭流程失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "doshowFlag")
	@ResponseBody
	public Object doshowFlag(URepairEntity uRepair, HttpServletRequest request) {
		int showFlag = Integer.parseInt(request.getSession().getAttribute("showMsg").toString());
		return showFlag;
	}

	/**
	 * 流程转向操作
	 *
	 * @param taskId
	 *            当前任务ID
	 * @param activityId
	 *            目标节点任务ID
	 * @param variables
	 *            流程变量
	 * @throws Exception
	 */
	private void turnTransition(String taskId, String activityId,
								Map<String, Object> variables) throws Exception {
		// 当前节点
		ActivityImpl currActivity = findActivitiImpl(taskId, null);
		// 清空当前流向
		List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);
		// 创建新流向
		TransitionImpl newTransition = currActivity.createOutgoingTransition();
		// 目标节点
		ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
		// 设置新流向的目标节点
		newTransition.setDestination(pointActivity);
		// 执行转向任务
		taskService.complete(taskId, variables);
		// 删除目标节点新流入
		pointActivity.getIncomingTransitions().remove(newTransition);
		// 还原以前流向
		restoreTransition(currActivity, oriPvmTransitionList);
	}

	/**
	 * 清空指定活动节点流向
	 *
	 * @param activityImpl
	 *            活动节点
	 * @return 节点流向集合
	 */
	private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
		// 存储当前节点所有流向临时变量
		List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
		// 获取当前节点所有流向，存储到临时变量，然后清空
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		for (PvmTransition pvmTransition : pvmTransitionList) {
			oriPvmTransitionList.add(pvmTransition);
		}
		pvmTransitionList.clear();

		return oriPvmTransitionList;
	}

	/**
	 * 还原指定活动节点流向
	 *
	 * @param activityImpl
	 *            活动节点
	 * @param oriPvmTransitionList
	 *            原有节点流向集合
	 */
	private void restoreTransition(ActivityImpl activityImpl,
								   List<PvmTransition> oriPvmTransitionList) {
		// 清空现有流向
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		pvmTransitionList.clear();
		// 还原以前流向
		for (PvmTransition pvmTransition : oriPvmTransitionList) {
			pvmTransitionList.add(pvmTransition);
		}
	}

	/**
	 * 根据任务ID获取流程定义
	 *
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
			String taskId) throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(findTaskById(taskId)
						.getProcessDefinitionId());

		if (processDefinition == null) {
			throw new Exception("流程定义未找到!");
		}

		return processDefinition;
	}



	/**
	 * 根据任务ID和节点ID获取活动节点 <br>
	 *
	 * @param taskId
	 *            任务ID
	 * @param activityId
	 *            活动节点ID <br>
	 *            如果为null或""，则默认查询当前活动节点 <br>
	 *            如果为"end"，则查询结束节点 <br>
	 *
	 * @return
	 * @throws Exception
	 */
	private ActivityImpl findActivitiImpl(String taskId, String activityId)
			throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

		// 获取当前活动节点ID
		if (activityId == null || activityId.isEmpty()) {
			activityId = findTaskById(taskId).getTaskDefinitionKey();
		}

		// 根据流程定义，获取该流程实例的结束节点
		if (activityId.toUpperCase().equals("END")) {
			for (ActivityImpl activityImpl : processDefinition.getActivities()) {
				List<PvmTransition> pvmTransitionList = activityImpl
						.getOutgoingTransitions();
				if (pvmTransitionList.isEmpty()) {
					return activityImpl;
				}
			}
		}

		// 根据节点ID，获取对应的活动节点
		ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
				.findActivity(activityId);

		return activityImpl;
	}

	/**
	 * 根据任务ID获得任务实例
	 *
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private TaskEntity findTaskById(String taskId) throws Exception {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(
				taskId).singleResult();
		if (task == null) {
			throw new Exception("任务实例未找到!");
		}
		return task;
	}

	/**
	 * 追回
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doRecover")
	@ResponseBody
	public AjaxJson doRecover(URepairEntity uRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "报修单追回成功";
		try {
			int flag = 0;
			Map<String, Object> variables = new HashMap<String,Object>();
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			URepairEntity t = uRepairService.get(URepairEntity.class, uRepair.getId());
			t.setStatus("1");
			variables.put("inputUser", t.getId());//表示惟一用户
			variables.put("isFinish", t.getIsFinish());
			List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(uRepair.getId()).list();
			//List<Task> task = taskService.createTaskQuery().taskAssignee(uRepair.getId()).list();
			List<Task> taskList = taskService.createTaskQuery().taskAssignee(uRepair.getId()).list();
			if(taskList.size()>0){
				Task task1 = taskList.get(taskList.size()-1);
				HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size()-2);
				turnTransition(task1.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
				Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC",t.getId(),historicTaskInstance.getTaskDefinitionKey());
				String[] activitIdArr = activityMap.get("ids").toString().split(",");
				String[] taskIdArr = activityMap.get("taskids").toString().split(",");
				systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【追回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(),taskIdArr[1]);
				systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?",activitIdArr[0],activitIdArr[1]);

				/*HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size()-2);
				TaskEntity taskEntity = new TaskEntity();
				MyBeanUtils.copyBeanNotNull2Bean(historicTaskInstance, taskEntity);
				systemService.executeSql("delete from act_ru_task where ASSIGNEE_=?",t.getId());
				systemService.executeSql("delete from act_hi_taskinst where ASSIGNEE_=? and NAME_=?",t.getId(),map.get("processName"));
				taskEntity.setId(null);
				taskService.saveTask(taskEntity);*/

				/*try {
					// 取得当前任务
					HistoricTaskInstance currTask = historyService
							.createHistoricTaskInstanceQuery().taskId(task1.getId())
							.singleResult();
					// 取得流程实例
					ProcessInstance instance = runtimeService
							.createProcessInstanceQuery()
							.processInstanceId(currTask.getProcessInstanceId())
							.singleResult();
					if (instance == null) {
						j.setMsg("流程已经结束");
						j.setSuccess(false);
						return j;
					}
					variables=instance.getProcessVariables();
					// 取得流程定义
					ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
							.getDeployedProcessDefinition(currTask
									.getProcessDefinitionId());
					if (definition == null) {
						j.setMsg("流程定义未找到");
						j.setSuccess(false);
						return j;
					}
					// 取得下一步活动
					ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
							.findActivity(currTask.getTaskDefinitionKey());
					List<PvmTransition> nextTransitionList = currActivity
							.getOutgoingTransitions();
					for (PvmTransition nextTransition : nextTransitionList) {
						PvmActivity nextActivity = nextTransition.getDestination();
						*//*List<HistoricTaskInstance> completeTasks = historyService
								.createHistoricTaskInstanceQuery()
								.processInstanceId(instance.getId())
								.taskDefinitionKey(nextActivity.getId()).finished()
								.list();
						int finished = completeTasks.size();
						if (finished > 0) {
							j.setMsg("存在已经完成的下一步，流程不能取回");
							j.setSuccess(false);
							return j;
						}*//*
						List<Task> nextTasks = taskService.createTaskQuery().processInstanceId(instance.getId())
								.taskDefinitionKey(nextActivity.getId()).list();
						for (Task nextTask : nextTasks) {
							//取活动，清除活动方向
							List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
							List<PvmTransition> pvmTransitionList = nextActivity
									.getOutgoingTransitions();
							for (PvmTransition pvmTransition : pvmTransitionList) {
								oriPvmTransitionList.add(pvmTransition);
							}
							pvmTransitionList.clear();
							//建立新方向
							ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
									.findActivity(nextTask.getTaskDefinitionKey());
							TransitionImpl newTransition = nextActivityImpl
									.createOutgoingTransition();
							newTransition.setDestination(currActivity);
							//完成任务
							taskService.complete(nextTask.getId(), variables);
							historyService.deleteHistoricTaskInstance(nextTask.getId());
							//恢复方向
							currActivity.getIncomingTransitions().remove(newTransition);
							List<PvmTransition> pvmTList = nextActivity
									.getOutgoingTransitions();
							pvmTList.clear();
							for (PvmTransition pvmTransition : oriPvmTransitionList) {
								pvmTransitionList.add(pvmTransition);
							}
						}
					}
					historyService.deleteHistoricTaskInstance(currTask.getId());

				} catch (Exception e) {
					e.printStackTrace();
					j.setMsg("流程取回失败，未知错误.");
					j.setSuccess(false);
					return j;
				}*/
			}



			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "报修单追回失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;

	}

	/**
	 * 提交
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(URepairEntity uRepair, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "报修单提交成功";
		try {
			int flag = 0;

			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			for(String id:map.get("ids").toString().split(",")){
				URepairEntity repairEntity = systemService.getEntity(URepairEntity.class, id);
				if(repairEntity.getStatus().equals("2")){
					message = "存在已完成的报修单，请重新选择在提交报修单！";
					j.setSuccess(false);
					flag = 1;
					break;
				}
			}
			Map<String, Object> variables = new HashMap<String,Object>();
			if(flag == 0){
				for(String id:map.get("ids").toString().split(",")) {
					URepairEntity t = uRepairService.get(URepairEntity.class, id);
					t.setStatus("1");
					variables.put("inputUser", t.getId());//表示惟一用户

					List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
					if(task.size()>0){
						Task task1 = task.get(task.size()-1);
						if(task1.getTaskDefinitionKey().equals("checkTask")){
							variables.put("isFinish", t.getIsFinish());
						}else if(task1.getTaskDefinitionKey().equals("saveRecord")||task1.getTaskDefinitionKey().equals("normalRepairTask")){
							t.setStatus("2");
						}else if(task1.getTaskDefinitionKey().equals("serviceTask")){
							variables.put("repairType", t.getRepairType());
						}else if(task1.getTaskDefinitionKey().equals("systemToService")){
							variables.put("cstype", t.getCsType());
						}
						taskService.complete(task1.getId(),variables);

						if(task1.getTaskDefinitionKey().equals("localService") || task1.getTaskDefinitionKey().equals("belongService") || task1.getTaskDefinitionKey().equals("tjService")){
							task = taskService.createTaskQuery().taskAssignee(id).list();
							task1 = task.get(task.size()-1);
							taskService.complete(task1.getId(),variables);
						}
					}else{
						ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("repair", "uRepair",variables);
						task = taskService.createTaskQuery().taskAssignee(id).list();
						Task task1 = task.get(task.size()-1);
						taskService.complete(task1.getId(),variables);
					}
					systemService.saveOrUpdate(t);
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "报修单提交失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 报修单表新增页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(URepairEntity uRepair, HttpServletRequest req) {
		TSUser user = (TSUser) req.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		Map orderNum = systemService.findOneForJdbc("select count(0)+1 repairNum from u_repair where sys_org_code=?",user.getCurrentDepart().getOrgCode());
		req.setAttribute("repairNum", "D"+ org.apache.tools.ant.util.DateUtils.format(new Date(),"yyyyMMdd")+String.format("%06d", Integer.parseInt(orderNum.get("repairNum").toString())));
		req.setAttribute("applyDate",DateUtils.date2Str(new Date(),DateUtils.datetimeFormat));
		if (StringUtil.isNotEmpty(uRepair.getId())) {
			uRepair = uRepairService.getEntity(URepairEntity.class, uRepair.getId());
			req.setAttribute("uRepairPage", uRepair);
		}
		return new ModelAndView("com/process/repair/uRepair-add");
	}
	/**
	 * 报修单表编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(URepairEntity uRepair, HttpServletRequest req) {
		req.getSession().setAttribute("showMsg","0");
		if (StringUtil.isNotEmpty(uRepair.getId())) {
			Map processTaskNames = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t1.`TASK_DEF_KEY_`) processTaskNames FROM act_hi_taskinst t1 where ASSIGNEE_=?",uRepair.getId());
			req.setAttribute("applyDate",DateUtils.date2Str(new Date(),DateUtils.datetimeFormat));
			uRepair = uRepairService.getEntity(URepairEntity.class, uRepair.getId());
			req.setAttribute("uRepairPage", uRepair);
			req.setAttribute("processTaskNames", processTaskNames.get("processTaskNames"));

			List<URepairAttchEntity> attchEntities = systemService.findHql("from URepairAttchEntity where repairId=?",uRepair.getId());
			req.setAttribute("attchEntities",attchEntities);
		/*	Map code = systemService.findOneForJdbc("SELECT NAME FROM t_s_category WHERE CODE=?",uRepair.getSblx());
			if(code != null){
				req.setAttribute("sblx",code.get("NAME"));
			}
			code = systemService.findOneForJdbc("SELECT NAME FROM t_s_category WHERE CODE=?",uRepair.getPp());
			if(code != null){
				req.setAttribute("pp",code.get("NAME"));
			}
			code = systemService.findOneForJdbc("SELECT NAME FROM t_s_category WHERE CODE=?",uRepair.getXh());
			if(code != null){
				req.setAttribute("xh",code.get("NAME"));
			}*/
		}
		return new ModelAndView("com/process/repair/uRepair-update");
	}

	/**
	 * 报修单表处理页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goWork")
	public ModelAndView goWork(URepairEntity uRepair, HttpServletRequest req) {
		uRepair = uRepairService.getEntity(URepairEntity.class, uRepair.getId());
		req.setAttribute("uRepairPage", uRepair);
		return new ModelAndView("com/process/repair/uRepair-work");
	}
	@RequestMapping(params = "goTime")
	public ModelAndView goTime(URepairEntity uRepair, HttpServletRequest req,DataGrid dataGrid) {
		String sql = "",countsql = "";
		Map map = ParameterUtil.getParamMaps(req.getParameterMap());

		sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE\n" +
				"WHEN t1.TASK_DEF_KEY_='applyTask' THEN t2.user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.check_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='serviceTask' THEN t2.service_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='zxRepair' THEN t2.repair_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='saveRecord' THEN t2.kaohe_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='serviceTask' THEN t2.service_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='servicePd' THEN t2.cus_name\n" +
				"WHEN t1.TASK_DEF_KEY_='qdService' THEN t2.recevie_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='systemToService' THEN t2.cus_name\n" +
				"WHEN t1.TASK_DEF_KEY_='localService' THEN t2.recevie_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='belongService' THEN t2.recevie_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='tjService' THEN t2.recevie_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='homeService' THEN t2.recevie_user_name\n" +
				"WHEN t1.TASK_DEF_KEY_='normalRepairTask' THEN t2.repair_user_name\n" +
				"ELSE ''\n" +
				"END workname FROM act_hi_taskinst t1 \n" +
				"LEFT JOIN u_repair t2 ON t1.`ASSIGNEE_` = t2.`id` where ASSIGNEE_='"+map.get("id")+"' ";

		countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='"+map.get("id")+"' ";

		if(dataGrid.getPage()==1){
			sql += " limit 0, "+dataGrid.getRows();
		}else{
			sql += "limit "+(dataGrid.getPage()-1)*dataGrid.getRows()+","+dataGrid.getRows();
		}

		this.systemService.listAllByJdbc(dataGrid, sql, countsql);
		req.setAttribute("taskList",dataGrid.getResults());
		if(dataGrid.getResults().size()>0){
			req.setAttribute("stepProcess",dataGrid.getResults().size()-1);
		}else{
			req.setAttribute("stepProcess",0);
		}
		uRepair = uRepairService.getEntity(URepairEntity.class, uRepair.getId());
		req.setAttribute("uRepairPage", uRepair);
		return new ModelAndView("com/process/repair/time");
	}
	/**
	 * 报修单和流程图页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goProcess")
	public ModelAndView goProcess(URepairEntity uRepair, HttpServletRequest req) {
		/*if (StringUtil.isNotEmpty(uRepair.getId())) {
			uRepair = uRepairService.getEntity(URepairEntity.class, uRepair.getId());
			req.setAttribute("uRepairPage", uRepair);
		}*/
		URepairEntity t = systemService.get(URepairEntity.class, uRepair.getId());
		List<Task> task = taskService.createTaskQuery()
				.taskAssignee(t.getId())
				.list();
		if(task.size()>0){
			Task task1 = task.get(task.size()-1);
			req.getSession().setAttribute("repairPorcess",task1);
			req.getSession().setAttribute("processFinish","0");
		}else{
			if(t.getStatus().equals("4")){
				req.getSession().setAttribute("processFinish","1");
			}else {
				req.getSession().setAttribute("processFinish","0");
				req.getSession().setAttribute("repairPorcess",null);
			}
		}
		return new ModelAndView("com/process/repair/uRepair-process");
	}

	@RequestMapping(params = "process")
	public ModelAndView process(URepairEntity uRepair, HttpServletRequest req) {
		/*if (StringUtil.isNotEmpty(uRepair.getId())) {
			uRepair = uRepairService.getEntity(URepairEntity.class, uRepair.getId());
			req.setAttribute("uRepairPage", uRepair);
		}*/
		return new ModelAndView("com/process/repair/process");
	}

	/**
	 * 读取带跟踪的图片
	 */
	@RequestMapping(params = "showProcess")
	public void showProcess(HttpServletRequest req,HttpServletResponse response) throws Exception {
		// 部署流程，只要是符合BPMN2规范的XML文件，理论上都可以被ACTIVITI部署
		Map map = ParameterUtil.getParamMaps(req.getParameterMap());
		// 查询历史表中的Task
		List<Task> task = taskService.createTaskQuery().taskAssignee(map.get("id").toString()).list();
		String processInstanceId = "";
		URepairEntity t = uRepairService.get(URepairEntity.class, map.get("id").toString());
		if(task.size()>0){
			Task task1 = task.get(task.size()-1);
			processInstanceId = task1.getProcessInstanceId();

		}else{
			if(t.getStatus().equals("4")){
				Map hisPorcess = systemService.findOneForJdbc("SELECT PROC_INST_ID_ processid FROM act_hi_taskinst WHERE ASSIGNEE_=? LIMIT 0,1 ",map.get("id").toString());
				processInstanceId = String.valueOf(hisPorcess.get("processid"));
			}
		}
		if(processInstanceId != null && !processInstanceId.isEmpty()){
			//获取历史流程实例
			HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			//获取流程图
			BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
			processEngineConfiguration = processEngine.getProcessEngineConfiguration();
			Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

			ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
			ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

			List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
			//高亮环节id集合
			List<String> highLightedActivitis = new ArrayList<String>();
			//高亮线路id集合
			List<String> highLightedFlows = ParameterUtil.getHighLightedFlows(definitionEntity,highLightedActivitList);

			for(HistoricActivityInstance tempActivity : highLightedActivitList){
				String activityId = tempActivity.getActivityId();
				highLightedActivitis.add(activityId);
			}

			//中文显示的是口口口，设置字体就好了
			InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "JPEG", highLightedActivitis,highLightedFlows,"宋体","宋体",null,1.0);
			//单独返回流程图，不高亮显示
//        InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);
			// 输出资源内容到相应对象
			byte[] b = new byte[1024];
			int len;
			while ((len = imageStream.read(b, 0, 1024)) != -1) {
				response.getOutputStream().write(b, 0, len);
			}
		}

	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","uRepairController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(URepairEntity uRepair,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(URepairEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, uRepair, request.getParameterMap());
		List<URepairEntity> uRepairs = this.uRepairService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"报修单表");
		modelMap.put(NormalExcelConstants.CLASS,URepairEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("报修单表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,uRepairs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(URepairEntity uRepair,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"报修单表");
    	modelMap.put(NormalExcelConstants.CLASS,URepairEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("报修单表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<URepairEntity> listURepairEntitys = ExcelImportUtil.importExcel(file.getInputStream(),URepairEntity.class,params);
				for (URepairEntity uRepair : listURepairEntitys) {
					uRepairService.save(uRepair);
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
	@ApiOperation(value="报修单表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<URepairEntity>> list() {
		List<URepairEntity> listURepairs=uRepairService.getList(URepairEntity.class);
		return Result.success(listURepairs);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取报修单表信息",notes="根据ID获取报修单表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		URepairEntity task = uRepairService.get(URepairEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取报修单表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建报修单表")
	public ResponseMessage<?> create(@ApiParam(name="报修单表对象")@RequestBody URepairEntity uRepair, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<URepairEntity>> failures = validator.validate(uRepair);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			uRepairService.save(uRepair);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("报修单表信息保存失败");
		}
		return Result.success(uRepair);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新报修单表",notes="更新报修单表")
	public ResponseMessage<?> update(@ApiParam(name="报修单表对象")@RequestBody URepairEntity uRepair) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<URepairEntity>> failures = validator.validate(uRepair);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			uRepairService.saveOrUpdate(uRepair);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新报修单表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新报修单表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除报修单表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			uRepairService.deleteEntityById(URepairEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("报修单表删除失败");
		}

		return Result.success();
	}
}
