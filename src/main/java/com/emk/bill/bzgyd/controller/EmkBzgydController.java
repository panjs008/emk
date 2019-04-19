package com.emk.bill.bzgyd.controller;
import com.emk.bill.bzgyd.entity.EmkBzgydEntity;
import com.emk.bill.bzgyd.service.EmkBzgydServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.bill.proorderbarcode.entity.EmkProOrderBarcodeEntity;
import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
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
 * @Description: 包装工艺单
 * @author onlineGenerator
 * @date 2018-10-24 21:38:26
 * @version V1.0   
 *
 */
@Api(value="EmkBzgyd",description="包装工艺单",tags="emkBzgydController")
@Controller
@RequestMapping("/emkBzgydController")
public class EmkBzgydController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkBzgydController.class);

	@Autowired
	private EmkBzgydServiceI emkBzgydService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 包装工艺单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/bill/bzgyd/emkBzgydList");
	}

	@RequestMapping(params = "boxMxList")
	public ModelAndView boxMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='size'");
		request.setAttribute("sizeList", list);
		if(Utils.notEmpty(map.get("proOrderId"))) {
			List<EmkProOrderBoxEntity> emkProOrderBoxEntities = systemService.findHql("from EmkProOrderBoxEntity where orderId=? and boxType=?", map.get("proOrderId"),map.get("type"));
			request.setAttribute("emkProOrderBoxEntities", emkProOrderBoxEntities);
		}
		if(map.get("type").equals("0")){
			return new ModelAndView("com/emk/bill/bzgyd/boxMxList");
		}else if(map.get("type").equals("1")){
			return new ModelAndView("com/emk/bill/bzgyd/boxMxList1");
		}else if(map.get("type").equals("2")){
			return new ModelAndView("com/emk/bill/bzgyd/boxMxList2");
		}else if(map.get("type").equals("3")){
			return new ModelAndView("com/emk/bill/bzgyd/boxMxList3");
		}
		return new ModelAndView("com/emk/bill/bzgyd/boxMxList");
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
	public void datagrid(EmkBzgydEntity emkBzgyd,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkBzgydEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkBzgyd, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.emkBzgydService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除包装工艺单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkBzgydEntity emkBzgyd, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkBzgyd = systemService.getEntity(EmkBzgydEntity.class, emkBzgyd.getId());
		message = "包装工艺单删除成功";
		try{
			emkBzgydService.delete(emkBzgyd);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "包装工艺单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除包装工艺单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "包装工艺单删除成功";
		try{
			for(String id:ids.split(",")){
				EmkBzgydEntity emkBzgyd = systemService.getEntity(EmkBzgydEntity.class, 
				id
				);
				emkBzgydService.delete(emkBzgyd);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "包装工艺单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加包装工艺单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkBzgydEntity emkBzgyd, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "包装工艺单添加成功";
		try{
			emkBzgydService.save(emkBzgyd);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "包装工艺单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新包装工艺单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkBzgydEntity emkBzgyd, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "包装工艺单更新成功";
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());

		EmkBzgydEntity t = emkBzgydService.get(EmkBzgydEntity.class, map.get("bzgydId").toString());
		try {
			emkBzgyd.setId(null);
			MyBeanUtils.copyBeanNotNull2Bean(emkBzgyd, t);
			emkBzgydService.saveOrUpdate(t);

			String dataRows = (String)map.get("orderMxListID3");
			//保存包装辅料数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=2",t.getId());
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].cproZnName"))) {
						emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].cproZnName"));
						emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].cproNum"));
						emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].cgysCode"));
						emkSampleDetailEntity.setBrand((String)map.get("orderMxList["+i+"].cbrand"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].cyongliang"))){
							emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].cyongliang").toString()));
						}
						emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].csignPrice"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].csunhaoPrecent"))){
							emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].csunhaoPrecent").toString()));
						}
						emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].csumYongliang").toString());
						if(Utils.notEmpty(map.get("orderMxList["+i+"].cchengben"))){
							emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].cchengben").toString()));
						}
						emkSampleDetailEntity.setPosition(map.get("orderMxList["+i+"].cposition").toString());
						emkSampleDetailEntity.setRkState(map.get("orderMxList["+i+"].crkState").toString());
						emkSampleDetailEntity.setSampleId(t.getId());
						emkSampleDetailEntity.setType("2");
						systemService.save(emkSampleDetailEntity);
					}
				}
			}

			dataRows = (String)map.get("orderMxListIDBarCode");
			//保存胶袋贴条码数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_pro_order_barcode where order_id = ? and type=1",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkProOrderBarcodeEntity emkProOrderBarcodeEntity = new EmkProOrderBarcodeEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].bcolor00"))) {
						emkProOrderBarcodeEntity.setColor((String)map.get("orderMxList["+i+"].bcolor00"));
						emkProOrderBarcodeEntity.setSize((String)map.get("orderMxList["+i+"].bsize00"));
						emkProOrderBarcodeEntity.setCode((String)map.get("orderMxList["+i+"].bcode00"));
						emkProOrderBarcodeEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].bsignTotal00").toString()));
						emkProOrderBarcodeEntity.setOrderId(t.getId());
						emkProOrderBarcodeEntity.setType("1");
						systemService.save(emkProOrderBarcodeEntity);
					}
				}
			}
			dataRows = (String)map.get("orderMxListIDBarCode");
			//保存箱贴条码数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_pro_order_barcode where order_id = ? and type=2",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkProOrderBarcodeEntity emkProOrderBarcodeEntity = new EmkProOrderBarcodeEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].ccolor00"))) {
						emkProOrderBarcodeEntity.setColor((String)map.get("orderMxList["+i+"].ccolor00"));
						emkProOrderBarcodeEntity.setSize((String)map.get("orderMxList["+i+"].csize00"));
						emkProOrderBarcodeEntity.setCode((String)map.get("orderMxList["+i+"].ccode00"));
						emkProOrderBarcodeEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].csignTotal00").toString()));
						emkProOrderBarcodeEntity.setOrderId(t.getId());
						emkProOrderBarcodeEntity.setType("2");
						systemService.save(emkProOrderBarcodeEntity);
					}
				}
			}

			dataRows = (String)map.get("orderMxListIDBox");
			//保存单色单码装数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_pro_order_box where order_id = ? and box_type=0",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkProOrderBoxEntity emkProOrderBoxEntity = new EmkProOrderBoxEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].acolorName00"))) {
						emkProOrderBoxEntity.setColorName((String)map.get("orderMxList["+i+"].acolorName00"));
						emkProOrderBoxEntity.setSize((String)map.get("orderMxList["+i+"].asizeBox00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].ainboxTotal00"))){
							emkProOrderBoxEntity.setInboxTotal(Integer.parseInt(map.get("orderMxList["+i+"].ainboxTotal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].atotalBox00"))){
							emkProOrderBoxEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].atotalBox00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumZsl00"))){
							emkProOrderBoxEntity.setSumZsl(Integer.parseInt(map.get("orderMxList["+i+"].asumZsl00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].asumTotal00"))){
							emkProOrderBoxEntity.setSumTotal(Integer.parseInt(map.get("orderMxList["+i+"].asumTotal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].alongVal00"))){
							emkProOrderBoxEntity.setLongVal(Double.parseDouble(map.get("orderMxList["+i+"].alongVal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].awidthVal00"))){
							emkProOrderBoxEntity.setWidthVal(Double.parseDouble(map.get("orderMxList["+i+"].awidthVal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].aheightVal00"))){
							emkProOrderBoxEntity.setHeightVal(Double.parseDouble(map.get("orderMxList["+i+"].aheightVal00").toString()));
						}

						emkProOrderBoxEntity.setBoxType("0");
						emkProOrderBoxEntity.setOrderId(t.getId());

						systemService.save(emkProOrderBoxEntity);
					}
				}
			}

			dataRows = (String)map.get("orderMxListIDBox1");
			//保存单色混码装数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_pro_order_box where order_id = ? and box_type=1",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkProOrderBoxEntity emkProOrderBoxEntity = new EmkProOrderBoxEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].bcolorName00"))) {
						emkProOrderBoxEntity.setColorName((String)map.get("orderMxList["+i+"].bcolorName00"));
						emkProOrderBoxEntity.setSize((String)map.get("orderMxList["+i+"].bsizeBox00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].binboxTotal00"))){
							emkProOrderBoxEntity.setInboxTotal(Integer.parseInt(map.get("orderMxList["+i+"].binboxTotal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].btotalBox00"))){
							emkProOrderBoxEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].btotalBox00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].bsumZsl00"))){
							emkProOrderBoxEntity.setSumZsl(Integer.parseInt(map.get("orderMxList["+i+"].bsumZsl00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].bsumTotal00"))){
							emkProOrderBoxEntity.setSumTotal(Integer.parseInt(map.get("orderMxList["+i+"].bsumTotal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].blongVal00"))){
							emkProOrderBoxEntity.setLongVal(Double.parseDouble(map.get("orderMxList["+i+"].blongVal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].bwidthVal00"))){
							emkProOrderBoxEntity.setWidthVal(Double.parseDouble(map.get("orderMxList["+i+"].bwidthVal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].bheightVal00"))){
							emkProOrderBoxEntity.setHeightVal(Double.parseDouble(map.get("orderMxList["+i+"].bheightVal00").toString()));
						}

						emkProOrderBoxEntity.setBoxType("1");
						emkProOrderBoxEntity.setOrderId(t.getId());

						systemService.save(emkProOrderBoxEntity);
					}
				}
			}

			dataRows = (String)map.get("orderMxListIDBox2");
			//保存混色单码装数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_pro_order_box where order_id = ? and box_type=2",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkProOrderBoxEntity emkProOrderBoxEntity = new EmkProOrderBoxEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].ccolorName00"))) {
						emkProOrderBoxEntity.setColorName((String)map.get("orderMxList["+i+"].ccolorName00"));
						emkProOrderBoxEntity.setSize((String)map.get("orderMxList["+i+"].csizeBox00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].cinboxTotal00"))){
							emkProOrderBoxEntity.setInboxTotal(Integer.parseInt(map.get("orderMxList["+i+"].cinboxTotal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].ctotalBox00"))){
							emkProOrderBoxEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].ctotalBox00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].csumZsl00"))){
							emkProOrderBoxEntity.setSumZsl(Integer.parseInt(map.get("orderMxList["+i+"].csumZsl00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].csumTotal00"))){
							emkProOrderBoxEntity.setSumTotal(Integer.parseInt(map.get("orderMxList["+i+"].csumTotal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].clongVal00"))){
							emkProOrderBoxEntity.setLongVal(Double.parseDouble(map.get("orderMxList["+i+"].clongVal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].cwidthVal00"))){
							emkProOrderBoxEntity.setWidthVal(Double.parseDouble(map.get("orderMxList["+i+"].cwidthVal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].cheightVal00"))){
							emkProOrderBoxEntity.setHeightVal(Double.parseDouble(map.get("orderMxList["+i+"].cheightVal00").toString()));
						}

						emkProOrderBoxEntity.setBoxType("2");
						emkProOrderBoxEntity.setOrderId(t.getId());

						systemService.save(emkProOrderBoxEntity);
					}
				}
			}

			dataRows = (String)map.get("orderMxListIDBox4");
			//保存混色混码装数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_pro_order_box where order_id = ? and box_type=3",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkProOrderBoxEntity emkProOrderBoxEntity = new EmkProOrderBoxEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].ecolorName00"))) {
						emkProOrderBoxEntity.setColorName((String)map.get("orderMxList["+i+"].ecolorName00"));
						emkProOrderBoxEntity.setSize((String)map.get("orderMxList["+i+"].esizeBox00"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].einboxTotal00"))){
							emkProOrderBoxEntity.setInboxTotal(Integer.parseInt(map.get("orderMxList["+i+"].einboxTotal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].etotalBox00"))){
							emkProOrderBoxEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].etotalBox00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].esumZsl00"))){
							emkProOrderBoxEntity.setSumZsl(Integer.parseInt(map.get("orderMxList["+i+"].esumZsl00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].esumTotal00"))){
							emkProOrderBoxEntity.setSumTotal(Integer.parseInt(map.get("orderMxList["+i+"].esumTotal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].elongVal00"))){
							emkProOrderBoxEntity.setLongVal(Double.parseDouble(map.get("orderMxList["+i+"].elongVal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].ewidthVal00"))){
							emkProOrderBoxEntity.setWidthVal(Double.parseDouble(map.get("orderMxList["+i+"].ewidthVal00").toString()));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].eheightVal00"))){
							emkProOrderBoxEntity.setHeightVal(Double.parseDouble(map.get("orderMxList["+i+"].eheightVal00").toString()));
						}

						emkProOrderBoxEntity.setBoxType("3");
						emkProOrderBoxEntity.setOrderId(t.getId());

						systemService.save(emkProOrderBoxEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "包装工艺单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 包装工艺单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkBzgydEntity emkBzgyd, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkBzgyd.getId())) {
			emkBzgyd = emkBzgydService.getEntity(EmkBzgydEntity.class, emkBzgyd.getId());
			req.setAttribute("emkBzgydPage", emkBzgyd);
		}
		return new ModelAndView("com/emk/bill/bzgyd/emkBzgyd-add");
	}
	/**
	 * 包装工艺单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkBzgydEntity emkBzgyd, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkBzgyd.getId())) {
			emkBzgyd = emkBzgydService.getEntity(EmkBzgydEntity.class, emkBzgyd.getId());
			req.setAttribute("emkBzgydPage", emkBzgyd);
		}
		return new ModelAndView("com/emk/bill/bzgyd/emkBzgyd-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkBzgydController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkBzgydEntity emkBzgyd,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkBzgydEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkBzgyd, request.getParameterMap());
		List<EmkBzgydEntity> emkBzgyds = this.emkBzgydService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"包装工艺单");
		modelMap.put(NormalExcelConstants.CLASS,EmkBzgydEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("包装工艺单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkBzgyds);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkBzgydEntity emkBzgyd,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"包装工艺单");
    	modelMap.put(NormalExcelConstants.CLASS,EmkBzgydEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("包装工艺单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkBzgydEntity> listEmkBzgydEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkBzgydEntity.class,params);
				for (EmkBzgydEntity emkBzgyd : listEmkBzgydEntitys) {
					emkBzgydService.save(emkBzgyd);
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
	@ApiOperation(value="包装工艺单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkBzgydEntity>> list() {
		List<EmkBzgydEntity> listEmkBzgyds=emkBzgydService.getList(EmkBzgydEntity.class);
		return Result.success(listEmkBzgyds);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取包装工艺单信息",notes="根据ID获取包装工艺单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkBzgydEntity task = emkBzgydService.get(EmkBzgydEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取包装工艺单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建包装工艺单")
	public ResponseMessage<?> create(@ApiParam(name="包装工艺单对象")@RequestBody EmkBzgydEntity emkBzgyd, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkBzgydEntity>> failures = validator.validate(emkBzgyd);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkBzgydService.save(emkBzgyd);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("包装工艺单信息保存失败");
		}
		return Result.success(emkBzgyd);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新包装工艺单",notes="更新包装工艺单")
	public ResponseMessage<?> update(@ApiParam(name="包装工艺单对象")@RequestBody EmkBzgydEntity emkBzgyd) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkBzgydEntity>> failures = validator.validate(emkBzgyd);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkBzgydService.saveOrUpdate(emkBzgyd);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新包装工艺单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新包装工艺单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除包装工艺单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkBzgydService.deleteEntityById(EmkBzgydEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("包装工艺单删除失败");
		}

		return Result.success();
	}
}
