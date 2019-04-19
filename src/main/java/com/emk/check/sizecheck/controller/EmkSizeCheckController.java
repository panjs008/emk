package com.emk.check.sizecheck.controller;
import com.emk.check.qualitycheck.entity.EmkQualityCheckEntity;
import com.emk.check.sizecheck.entity.EmkSizeCheckDetailEntity;
import com.emk.check.sizecheck.entity.EmkSizeCheckEntity;
import com.emk.check.sizecheck.entity.EmkSizeEntity;
import com.emk.check.sizecheck.entity.EmkSizeTotalEntity;
import com.emk.check.sizecheck.service.EmkSizeCheckServiceI;

import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.produce.invoices.entity.EmkInvoicesDetailEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
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
 * @Description: 尺寸检查表
 * @author onlineGenerator
 * @date 2018-09-24 17:43:58
 * @version V1.0   
 *
 */
@Api(value="EmkSizeCheck",description="尺寸检查表",tags="emkSizeCheckController")
@Controller
@RequestMapping("/emkSizeCheckController")
public class EmkSizeCheckController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkSizeCheckController.class);

	@Autowired
	private EmkSizeCheckServiceI emkSizeCheckService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	@Autowired
	ProcessEngine processEngine;
	@Autowired
	TaskService taskService;
	@Autowired
	HistoryService historyService;

	/**
	 * 尺寸检查表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/check/sizecheck/emkSizeCheckList");
	}

	@RequestMapping(params = "orderMxList")
	public ModelAndView orderMxList(HttpServletRequest request) {
		List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
		request.setAttribute("categoryEntityList", codeList);
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
			List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", map.get("proOrderId"));
			request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
		}
		return new ModelAndView("com/emk/check/sizecheck/orderMxList");
	}

	@RequestMapping(params = "detailMxList")
	public ModelAndView detailMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
		request.setAttribute("colorList", list);
		list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='size'");
		request.setAttribute("sizeList", list);
		if (Utils.notEmpty(map.get("sizeCheckId"))) {
			List<EmkSizeCheckDetailEntity> emkSizeCheckDetailEntities = systemService.findHql("from EmkSizeCheckDetailEntity where sizeCheckId=?", map.get("sizeCheckId"));
			request.setAttribute("emkSizeCheckDetailEntities", emkSizeCheckDetailEntities);

			EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",map.get("sizeCheckId"));
			request.setAttribute("emkSizePage", emkSizeEntity);
		}
		return new ModelAndView("com/emk/check/sizecheck/detailMxList");
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
	public void datagrid(EmkSizeCheckEntity emkSizeCheck,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkSizeCheckEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkSizeCheck, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			Map roleMap = (Map) request.getSession().getAttribute("ROLE");
			if(roleMap != null){
				if(roleMap.get("rolecode").toString().contains("zjy")){
					cq.eq("createBy",user.getUserName());
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkSizeCheckService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除尺寸检查表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkSizeCheckEntity emkSizeCheck, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkSizeCheck = systemService.getEntity(EmkSizeCheckEntity.class, emkSizeCheck.getId());
		message = "尺寸检查表删除成功";
		try{
			emkSizeCheckService.delete(emkSizeCheck);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "尺寸检查表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除尺寸检查表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "尺寸检查表删除成功";
		try{
			for(String id:ids.split(",")){
				EmkSizeCheckEntity emkSizeCheck = systemService.getEntity(EmkSizeCheckEntity.class, id);
				this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?",id);
				emkSizeCheckService.delete(emkSizeCheck);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "尺寸检查表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加尺寸检查表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkSizeCheckEntity emkSizeCheck,EmkSizeEntity emkSize,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "尺寸检查表添加成功";
		try{
			emkSizeCheckService.save(emkSizeCheck);
			emkSize.setFormId(emkSizeCheck.getId());
			systemService.save(emkSize);
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				EmkSizeCheckDetailEntity emkSizeCheckDetailEntity = null;
				EmkSizeTotalEntity emkSizeTotalEntity = null;
				for (int i = 0; i < rows; i++) {
					emkSizeCheckDetailEntity = new EmkSizeCheckDetailEntity();
					emkSizeTotalEntity = new EmkSizeTotalEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].buWei00"))) {
						emkSizeCheckDetailEntity.setBuWei(map.get("orderMxList["+i+"].buWei00"));
						emkSizeCheckDetailEntity.setSeqNum(String.valueOf((char)(i % 26 + (int) 'A')));
						emkSizeCheckDetailEntity.setBtotalA(map.get("orderMxList["+i+"].totalA2"));
						emkSizeCheckDetailEntity.setBtotalB(map.get("orderMxList["+i+"].totalB2"));
						emkSizeCheckDetailEntity.setBtotalC(map.get("orderMxList["+i+"].totalC2"));
						emkSizeCheckDetailEntity.setBtotalD(map.get("orderMxList["+i+"].totalD2"));
						emkSizeCheckDetailEntity.setBtotalE(map.get("orderMxList["+i+"].totalE2"));
						emkSizeCheckDetailEntity.setBtotalF(map.get("orderMxList["+i+"].totalF2"));
						emkSizeCheckDetailEntity.setBtotalG(map.get("orderMxList["+i+"].totalG2"));
						emkSizeCheckDetailEntity.setBtotalH(map.get("orderMxList["+i+"].totalH2"));
						emkSizeCheckDetailEntity.setBtotalI(map.get("orderMxList["+i+"].totalI2"));
						emkSizeCheckDetailEntity.setBtotalJ(map.get("orderMxList["+i+"].totalJ2"));
						emkSizeCheckDetailEntity.setBtotalK(map.get("orderMxList["+i+"].totalK2"));

						emkSizeCheckDetailEntity.setCtotalA(map.get("orderMxList["+i+"].totalA3"));
						emkSizeCheckDetailEntity.setCtotalB(map.get("orderMxList["+i+"].totalB3"));
						emkSizeCheckDetailEntity.setCtotalC(map.get("orderMxList["+i+"].totalC3"));
						emkSizeCheckDetailEntity.setCtotalD(map.get("orderMxList["+i+"].totalD3"));
						emkSizeCheckDetailEntity.setCtotalE(map.get("orderMxList["+i+"].totalE3"));
						emkSizeCheckDetailEntity.setCtotalF(map.get("orderMxList["+i+"].totalF3"));
						emkSizeCheckDetailEntity.setCtotalG(map.get("orderMxList["+i+"].totalG3"));
						emkSizeCheckDetailEntity.setCtotalH(map.get("orderMxList["+i+"].totalH3"));
						emkSizeCheckDetailEntity.setCtotalI(map.get("orderMxList["+i+"].totalI3"));
						emkSizeCheckDetailEntity.setCtotalJ(map.get("orderMxList["+i+"].totalJ3"));
						emkSizeCheckDetailEntity.setCtotalK(map.get("orderMxList["+i+"].totalK3"));

						emkSizeCheckDetailEntity.setDtotalA(map.get("orderMxList["+i+"].totalA4"));
						emkSizeCheckDetailEntity.setDtotalB(map.get("orderMxList["+i+"].totalB4"));
						emkSizeCheckDetailEntity.setDtotalC(map.get("orderMxList["+i+"].totalC4"));
						emkSizeCheckDetailEntity.setDtotalD(map.get("orderMxList["+i+"].totalD4"));
						emkSizeCheckDetailEntity.setDtotalE(map.get("orderMxList["+i+"].totalE4"));
						emkSizeCheckDetailEntity.setDtotalF(map.get("orderMxList["+i+"].totalF4"));
						emkSizeCheckDetailEntity.setDtotalG(map.get("orderMxList["+i+"].totalG4"));
						emkSizeCheckDetailEntity.setDtotalH(map.get("orderMxList["+i+"].totalH4"));
						emkSizeCheckDetailEntity.setDtotalI(map.get("orderMxList["+i+"].totalI4"));
						emkSizeCheckDetailEntity.setDtotalJ(map.get("orderMxList["+i+"].totalJ4"));
						emkSizeCheckDetailEntity.setDtotalK(map.get("orderMxList["+i+"].totalK4"));

						emkSizeCheckDetailEntity.setSizeCheckId(emkSizeCheck.getId());
						systemService.save(emkSizeCheckDetailEntity);


						emkSizeTotalEntity.setTotalA(map.get("orderMxList["+i+"].totalA"));
						emkSizeTotalEntity.setTotalB(map.get("orderMxList["+i+"].totalB"));
						emkSizeTotalEntity.setTotalC(map.get("orderMxList["+i+"].totalC"));
						emkSizeTotalEntity.setTotalD(map.get("orderMxList["+i+"].totalD"));
						emkSizeTotalEntity.setTotalE(map.get("orderMxList["+i+"].totalE"));
						emkSizeTotalEntity.setTotalF(map.get("orderMxList["+i+"].totalF"));
						emkSizeTotalEntity.setTotalG(map.get("orderMxList["+i+"].totalG"));
						emkSizeTotalEntity.setTotalH(map.get("orderMxList["+i+"].totalH"));
						emkSizeTotalEntity.setTotalI(map.get("orderMxList["+i+"].totalI"));
						emkSizeTotalEntity.setTotalJ(map.get("orderMxList["+i+"].totalJ"));
						emkSizeTotalEntity.setTotalK(map.get("orderMxList["+i+"].totalK"));
						emkSizeTotalEntity.setpId(emkSizeCheckDetailEntity.getId());
						systemService.save(emkSizeTotalEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "尺寸检查表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新尺寸检查表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkSizeCheckEntity emkSizeCheck,EmkSizeEntity emkSize, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "尺寸检查表更新成功";
		if(Utils.notEmpty(emkSizeCheck.getId())){
			EmkSizeCheckEntity t = emkSizeCheckService.get(EmkSizeCheckEntity.class, emkSizeCheck.getId());
			EmkSizeEntity t2 = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",emkSizeCheck.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(emkSizeCheck, t);
				emkSizeCheckService.saveOrUpdate(t);

				emkSize.setId(null);
				if(Utils.notEmpty(t2)){
					MyBeanUtils.copyBeanNotNull2Bean(emkSize, t2);
					systemService.saveOrUpdate(t2);
				}else{
					t2 = new EmkSizeEntity();
					MyBeanUtils.copyBeanNotNull2Bean(emkSize, t2);
					t2.setFormId(t.getId());
					systemService.save(t2);
				}

				Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
				String dataRows = (String) map.get("orderMxListIDSR");
				if (Utils.notEmpty(dataRows)) {
					int rows = Integer.parseInt(dataRows);
					this.systemService.executeSql("delete from emk_size_total where FIND_IN_SET(p_id,(SELECT GROUP_CONCAT(id) FROM emk_size_detail where size_check_id=?))", t.getId());
					this.systemService.executeSql("delete from emk_size_detail where size_check_id=?", t.getId());

					EmkSizeCheckDetailEntity emkSizeCheckDetailEntity = null;
					EmkSizeTotalEntity emkSizeTotalEntity = null;
					for (int i = 0; i < rows; i++) {
						emkSizeCheckDetailEntity = new EmkSizeCheckDetailEntity();
						emkSizeTotalEntity = new EmkSizeTotalEntity();
						if (Utils.notEmpty(map.get("orderMxList["+i+"].buWei00"))) {
							emkSizeCheckDetailEntity.setBuWei(map.get("orderMxList["+i+"].buWei00"));
							emkSizeCheckDetailEntity.setSeqNum(String.valueOf((char)(i % 26 + (int) 'A')));
							emkSizeCheckDetailEntity.setBtotalA(map.get("orderMxList["+i+"].totalA2"));
							emkSizeCheckDetailEntity.setBtotalB(map.get("orderMxList["+i+"].totalB2"));
							emkSizeCheckDetailEntity.setBtotalC(map.get("orderMxList["+i+"].totalC2"));
							emkSizeCheckDetailEntity.setBtotalD(map.get("orderMxList["+i+"].totalD2"));
							emkSizeCheckDetailEntity.setBtotalE(map.get("orderMxList["+i+"].totalE2"));
							emkSizeCheckDetailEntity.setBtotalF(map.get("orderMxList["+i+"].totalF2"));
							emkSizeCheckDetailEntity.setBtotalG(map.get("orderMxList["+i+"].totalG2"));
							emkSizeCheckDetailEntity.setBtotalH(map.get("orderMxList["+i+"].totalH2"));
							emkSizeCheckDetailEntity.setBtotalI(map.get("orderMxList["+i+"].totalI2"));
							emkSizeCheckDetailEntity.setBtotalJ(map.get("orderMxList["+i+"].totalJ2"));
							emkSizeCheckDetailEntity.setBtotalK(map.get("orderMxList["+i+"].totalK2"));

							emkSizeCheckDetailEntity.setCtotalA(map.get("orderMxList["+i+"].totalA3"));
							emkSizeCheckDetailEntity.setCtotalB(map.get("orderMxList["+i+"].totalB3"));
							emkSizeCheckDetailEntity.setCtotalC(map.get("orderMxList["+i+"].totalC3"));
							emkSizeCheckDetailEntity.setCtotalD(map.get("orderMxList["+i+"].totalD3"));
							emkSizeCheckDetailEntity.setCtotalE(map.get("orderMxList["+i+"].totalE3"));
							emkSizeCheckDetailEntity.setCtotalF(map.get("orderMxList["+i+"].totalF3"));
							emkSizeCheckDetailEntity.setCtotalG(map.get("orderMxList["+i+"].totalG3"));
							emkSizeCheckDetailEntity.setCtotalH(map.get("orderMxList["+i+"].totalH3"));
							emkSizeCheckDetailEntity.setCtotalI(map.get("orderMxList["+i+"].totalI3"));
							emkSizeCheckDetailEntity.setCtotalJ(map.get("orderMxList["+i+"].totalJ3"));
							emkSizeCheckDetailEntity.setCtotalK(map.get("orderMxList["+i+"].totalK3"));

							emkSizeCheckDetailEntity.setDtotalA(map.get("orderMxList["+i+"].totalA4"));
							emkSizeCheckDetailEntity.setDtotalB(map.get("orderMxList["+i+"].totalB4"));
							emkSizeCheckDetailEntity.setDtotalC(map.get("orderMxList["+i+"].totalC4"));
							emkSizeCheckDetailEntity.setDtotalD(map.get("orderMxList["+i+"].totalD4"));
							emkSizeCheckDetailEntity.setDtotalE(map.get("orderMxList["+i+"].totalE4"));
							emkSizeCheckDetailEntity.setDtotalF(map.get("orderMxList["+i+"].totalF4"));
							emkSizeCheckDetailEntity.setDtotalG(map.get("orderMxList["+i+"].totalG4"));
							emkSizeCheckDetailEntity.setDtotalH(map.get("orderMxList["+i+"].totalH4"));
							emkSizeCheckDetailEntity.setDtotalI(map.get("orderMxList["+i+"].totalI4"));
							emkSizeCheckDetailEntity.setDtotalJ(map.get("orderMxList["+i+"].totalJ4"));
							emkSizeCheckDetailEntity.setDtotalK(map.get("orderMxList["+i+"].totalK4"));

							emkSizeCheckDetailEntity.setSizeCheckId(emkSizeCheck.getId());
							systemService.save(emkSizeCheckDetailEntity);

							emkSizeTotalEntity.setTotalA(map.get("orderMxList["+i+"].totalA"));
							emkSizeTotalEntity.setTotalB(map.get("orderMxList["+i+"].totalB"));
							emkSizeTotalEntity.setTotalC(map.get("orderMxList["+i+"].totalC"));
							emkSizeTotalEntity.setTotalD(map.get("orderMxList["+i+"].totalD"));
							emkSizeTotalEntity.setTotalE(map.get("orderMxList["+i+"].totalE"));
							emkSizeTotalEntity.setTotalF(map.get("orderMxList["+i+"].totalF"));
							emkSizeTotalEntity.setTotalG(map.get("orderMxList["+i+"].totalG"));
							emkSizeTotalEntity.setTotalH(map.get("orderMxList["+i+"].totalH"));
							emkSizeTotalEntity.setTotalI(map.get("orderMxList["+i+"].totalI"));
							emkSizeTotalEntity.setTotalJ(map.get("orderMxList["+i+"].totalJ"));
							emkSizeTotalEntity.setTotalK(map.get("orderMxList["+i+"].totalK"));
							emkSizeTotalEntity.setpId(emkSizeCheckDetailEntity.getId());
							systemService.save(emkSizeTotalEntity);
						}
					}
				}
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "尺寸检查表更新失败";
				throw new BusinessException(e.getMessage());
			}
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 尺寸检查表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkSizeCheckEntity emkSizeCheck, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));

		TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");
		Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_size_check where sys_org_code=?", user.getCurrentDepart().getOrgCode());
		req.setAttribute("sizeCheckNum","CCJC" + DateUtils.format(new Date(), "yyMMdd") + String.format("%04d", Integer.parseInt(orderNum.get("orderNum").toString())));
		if (StringUtil.isNotEmpty(emkSizeCheck.getId())) {
			emkSizeCheck = systemService.findUniqueByProperty(EmkSizeCheckEntity.class,"qualityCheckId",emkSizeCheck.getId());
			req.setAttribute("emkSizeCheckPage", emkSizeCheck);
		}
		return new ModelAndView("com/emk/check/sizecheck/emkSizeCheck-add");
	}
	/**
	 * 尺寸检查表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkSizeCheckEntity emkSizeCheck, HttpServletRequest req) {
		List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
		req.setAttribute("categoryEntityList", codeList);
		if (StringUtil.isNotEmpty(emkSizeCheck.getId())) {
			emkSizeCheck = emkSizeCheckService.getEntity(EmkSizeCheckEntity.class, emkSizeCheck.getId());
			req.setAttribute("emkSizeCheckPage", emkSizeCheck);
		}
		return new ModelAndView("com/emk/check/sizecheck/emkSizeCheck-update");
	}

	@RequestMapping(params = "goUpdate2")
	public ModelAndView goUpdate2(EmkSizeCheckEntity emkSizeCheck, HttpServletRequest req) {
		List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
		req.setAttribute("categoryEntityList", codeList);
		if (StringUtil.isNotEmpty(emkSizeCheck.getId())) {
			emkSizeCheck = emkSizeCheckService.getEntity(EmkSizeCheckEntity.class, emkSizeCheck.getId());
			req.setAttribute("emkSizeCheckPage", emkSizeCheck);
		}
		return new ModelAndView("com/emk/check/sizecheck/emkSizeCheck-update2");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkSizeCheckController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkSizeCheckEntity emkSizeCheck,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkSizeCheckEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkSizeCheck, request.getParameterMap());
		List<EmkSizeCheckEntity> emkSizeChecks = this.emkSizeCheckService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"尺寸检查表");
		modelMap.put(NormalExcelConstants.CLASS,EmkSizeCheckEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("尺寸检查表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkSizeChecks);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkSizeCheckEntity emkSizeCheck,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"尺寸检查表");
    	modelMap.put(NormalExcelConstants.CLASS,EmkSizeCheckEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("尺寸检查表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkSizeCheckEntity> listEmkSizeCheckEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkSizeCheckEntity.class,params);
				for (EmkSizeCheckEntity emkSizeCheck : listEmkSizeCheckEntitys) {
					emkSizeCheckService.save(emkSizeCheck);
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
	@ApiOperation(value="尺寸检查表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkSizeCheckEntity>> list() {
		List<EmkSizeCheckEntity> listEmkSizeChecks=emkSizeCheckService.getList(EmkSizeCheckEntity.class);
		return Result.success(listEmkSizeChecks);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取尺寸检查表信息",notes="根据ID获取尺寸检查表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkSizeCheckEntity task = emkSizeCheckService.get(EmkSizeCheckEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取尺寸检查表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建尺寸检查表")
	public ResponseMessage<?> create(@ApiParam(name="尺寸检查表对象")@RequestBody EmkSizeCheckEntity emkSizeCheck, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkSizeCheckEntity>> failures = validator.validate(emkSizeCheck);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkSizeCheckService.save(emkSizeCheck);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("尺寸检查表信息保存失败");
		}
		return Result.success(emkSizeCheck);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新尺寸检查表",notes="更新尺寸检查表")
	public ResponseMessage<?> update(@ApiParam(name="尺寸检查表对象")@RequestBody EmkSizeCheckEntity emkSizeCheck) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkSizeCheckEntity>> failures = validator.validate(emkSizeCheck);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkSizeCheckService.saveOrUpdate(emkSizeCheck);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新尺寸检查表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新尺寸检查表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除尺寸检查表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkSizeCheckService.deleteEntityById(EmkSizeCheckEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("尺寸检查表删除失败");
		}

		return Result.success();
	}

	@RequestMapping(params="doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(EmkSizeCheckEntity emkSizeCheckEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "尺寸检查单提交成功";
		try {
			int flag = 0;
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			if (emkSizeCheckEntity.getId() == null || emkSizeCheckEntity.getId().isEmpty()) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkSizeCheckEntity sizeCheckEntity = systemService.getEntity(EmkSizeCheckEntity.class, id);
					if (!sizeCheckEntity.getState().equals("0")) {
						message = "存在已提交的尺寸检查单，请重新选择在提交尺寸检查单！";
						j.setSuccess(false);
						flag = 1;
						break;
					}
				}
			}else{
				map.put("ids", emkSizeCheckEntity.getId());
			}
			Map<String, Object> variables = new HashMap();
			if (flag == 0) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkSizeCheckEntity t = emkSizeCheckService.get(EmkSizeCheckEntity.class, id);
					t.setState("1");
					variables.put("optUser", t.getId());

					List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
					if (task.size() > 0) {
						Task task1 = (Task)task.get(task.size() - 1);
						if (task1.getTaskDefinitionKey().equals("sizecheckTask")) {
							taskService.complete(task1.getId(), variables);
						}
						if (task1.getTaskDefinitionKey().equals("checkTask")) {
							t.setLeader(user.getRealName());
							t.setLeadUserId(user.getId());
							t.setLeadAdvice(emkSizeCheckEntity.getLeadAdvice());
							if (emkSizeCheckEntity.getIsPass().equals("0")) {
								variables.put("isPass", emkSizeCheckEntity.getIsPass());
								taskService.complete(task1.getId(), variables);
							} else {
								List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(t.getId()).list();

								List<Task> taskList = taskService.createTaskQuery().taskAssignee(t.getId()).list();
								if (taskList.size() > 0) {
									Task taskH = (Task)taskList.get(taskList.size() - 1);
									HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
									FlowUtil.turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
									Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC",  t.getId(), historicTaskInstance.getTaskDefinitionKey());
									String[] activitIdArr = activityMap.get("ids").toString().split(",");
									String[] taskIdArr = activityMap.get("taskids").toString().split(",");
									systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(), taskIdArr[1]);
									systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", activitIdArr[0], activitIdArr[1] );
								}
								t.setState("0");
							}
						}
					}else {
						ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("sizecheck", "emkSizeCheckEntity", variables);
						task = taskService.createTaskQuery().taskAssignee(id).list();
						Task task1 = task.get(task.size() - 1);
						taskService.complete(task1.getId(), variables);
					}
					systemService.saveOrUpdate(t);
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		catch (Exception e) {
			e.printStackTrace();
			message = "尺寸检查单提交失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	@RequestMapping(params="goWork")
	public ModelAndView goWork(EmkSizeCheckEntity emkSizeCheckEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkSizeCheckEntity.getId())) {
			emkSizeCheckEntity = emkSizeCheckService.getEntity(EmkSizeCheckEntity.class, emkSizeCheckEntity.getId());
			req.setAttribute("emkSizeCheck", emkSizeCheckEntity);
		}
		return new ModelAndView("com/emk/check/sizecheck/emkSizeCheck-work");


	}

	@RequestMapping(params="goTime")
	public ModelAndView goTime(EmkSizeCheckEntity emkSizeCheckEntity, HttpServletRequest req, DataGrid dataGrid) {
		String sql = "";String countsql = "";
		Map map = ParameterUtil.getParamMaps(req.getParameterMap());

		sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
				" WHEN t1.TASK_DEF_KEY_='sizecheckTask' THEN t2.create_name \n" +
				" WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
				" END workname FROM act_hi_taskinst t1 \n" +
				" LEFT JOIN emk_size_check t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

		countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='" + map.get("id") + "' ";
		if (dataGrid.getPage() == 1) {
			sql = sql + " limit 0, " + dataGrid.getRows();
		} else {
			sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
		}
		systemService.listAllByJdbc(dataGrid, sql, countsql);
		req.setAttribute("taskList", dataGrid.getResults());
		if (dataGrid.getResults().size() > 0) {
			req.setAttribute("stepProcess", Integer.valueOf(dataGrid.getResults().size() - 1));
		} else {
			req.setAttribute("stepProcess", Integer.valueOf(0));
		}
		emkSizeCheckEntity = emkSizeCheckService.getEntity(EmkSizeCheckEntity.class, emkSizeCheckEntity.getId());
		req.setAttribute("emkSizeCheck", emkSizeCheckEntity);
		return new ModelAndView("com/emk/check/sizecheck/time");
	}
}
