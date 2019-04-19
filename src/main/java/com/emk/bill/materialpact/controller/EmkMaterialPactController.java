package com.emk.bill.materialpact.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity2;
import com.emk.bill.materialpact.service.EmkMaterialPactServiceI;
import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.storage.pack.entity.EmkPackEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Api(value = "EmkMaterialPact", description = "面料预采购合同", tags = "emkMaterialPactController")
@Controller
@RequestMapping("/emkMaterialPactController")
public class EmkMaterialPactController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMaterialPactController.class);
    @Autowired
    private EmkMaterialPactServiceI emkMaterialPactService;
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


    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPactList");
    }

    @RequestMapping(params = "list2")
    public ModelAndView list2(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPactList2");
    }

    @RequestMapping(params = "list3")
    public ModelAndView list3(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPactList3");
    }

    @RequestMapping(params = "emkPactList")
    public ModelAndView emkPactList(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkPactList");
    }

    @RequestMapping(params = "emkPactList2")
    public ModelAndView emkPactList2(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkPactList2");
    }

    @RequestMapping(params = "emkPactList3")
    public ModelAndView emkPactList3(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialpact/emkPactList3");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", map.get("proOrderId"),map.get("type"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        if(map.get("type").equals("0")){
            return new ModelAndView("com/emk/bill/materialpact/orderMxList");
        }else if(map.get("type").equals("1")){
            return new ModelAndView("com/emk/bill/materialpact/orderMxList2");
        }else if(map.get("type").equals("2")){
            return new ModelAndView("com/emk/bill/materialpact/orderMxList3");
        }
        return new ModelAndView("com/emk/bill/materialpact/orderMxList");
    }


    @RequestMapping(params = "datagrid")
    public void datagrid(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialPactEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkMaterialPact, request.getParameterMap());


        cq.add();
        emkMaterialPactService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMaterialPact = systemService.getEntity(EmkMaterialPactEntity.class, emkMaterialPact.getId());
        message = "面料预采购合同删除成功";
        try {
            emkMaterialPactService.delete(emkMaterialPact);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料预采购合同删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料预采购合同删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkMaterialPactEntity emkMaterialPact = systemService.getEntity(EmkMaterialPactEntity.class, id);
                systemService.executeSql("delete from emk_sample_detail where SAMPLE_ID=?", id);
                emkMaterialPactService.delete(emkMaterialPact);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料预采购合同删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkMaterialPactEntity2 emkMaterialPact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料预采购合同添加成功";
        try {
            Map<String,String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            emkMaterialPactService.save(emkMaterialPact);
            String dataRows = map.get("orderMxListID");
            dataRows = map.get("orderMxListID");
            //保存原料面料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setCgxqdh(map.get("orderMxList["+i+"].cgxqdh"));
                        if(emkMaterialPact.getFlag().equals("1")){
                            emkSampleDetailEntity.setCghtbh(map.get("orderMxList["+i+"].cghtbh"));
                        }
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumPrice").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(emkMaterialPact.getId());
                        emkSampleDetailEntity.setType("0");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            dataRows = map.get("orderMxListID2");
            //保存缝制辅料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setCgxqdh(map.get("orderMxList["+i+"].cgxqdh"));
                        if(emkMaterialPact.getFlag().equals("1")){
                            emkSampleDetailEntity.setCghtbh(map.get("orderMxList["+i+"].cghtbh"));
                        }
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumPrice").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(emkMaterialPact.getId());
                        emkSampleDetailEntity.setType("1");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            dataRows = map.get("orderMxListID3");
            //保存包装辅料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setCgxqdh(map.get("orderMxList["+i+"].cgxqdh"));
                        if(emkMaterialPact.getFlag().equals("1")){
                            emkSampleDetailEntity.setCghtbh(map.get("orderMxList["+i+"].cghtbh"));
                        }
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumPrice").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(emkMaterialPact.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料预采购合同添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkMaterialPactEntity2 emkMaterialPact, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料预采购合同更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        EmkMaterialPactEntity t = emkMaterialPactService.get(EmkMaterialPactEntity.class, map.get("pactId").toString());
        try {
            emkMaterialPact.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterialPact, t);
            emkMaterialPactService.saveOrUpdate(t);

            String dataRows = map.get("orderMxListID");
            dataRows = map.get("orderMxListID");
            //保存原料面料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=0",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setCgxqdh(map.get("orderMxList["+i+"].cgxqdh"));
                        if(t.getFlag().equals("1")){
                            emkSampleDetailEntity.setCghtbh(map.get("orderMxList["+i+"].cghtbh"));
                        }
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumPrice").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("0");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            dataRows = map.get("orderMxListID2");
            //保存缝制辅料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=1",t.getId());

                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setCgxqdh(map.get("orderMxList["+i+"].cgxqdh"));
                        if(t.getFlag().equals("1")){
                            emkSampleDetailEntity.setCghtbh(map.get("orderMxList["+i+"].cghtbh"));
                        }
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumPrice").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("1");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            dataRows = map.get("orderMxListID3");
            //保存包装辅料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=2",t.getId());

                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setCgxqdh(map.get("orderMxList["+i+"].cgxqdh"));
                        if(t.getFlag().equals("1")){
                            emkSampleDetailEntity.setCghtbh(map.get("orderMxList["+i+"].cghtbh"));
                        }
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumPrice").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料预采购合同更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialPact.getId())) {
            emkMaterialPact = emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, emkMaterialPact.getId());
            req.setAttribute("emkMaterialPactPage", emkMaterialPact);
        }
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPact-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialPact.getId())) {
            emkMaterialPact = emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, emkMaterialPact.getId());
            req.setAttribute("emkMaterialPactPage", emkMaterialPact);
            if(emkMaterialPact.getType().equals("0")){
                req.setAttribute("pactTypeName", "原料面料");
            }else if(emkMaterialPact.getType().equals("1")){
                req.setAttribute("pactTypeName", "缝制辅料");
            }else if(emkMaterialPact.getType().equals("2")){
                req.setAttribute("pactTypeName", "包装辅料");
            }
        }
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPact-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialPact.getId())) {
            emkMaterialPact = emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, emkMaterialPact.getId());
            req.setAttribute("emkMaterialPactPage", emkMaterialPact);
            if(emkMaterialPact.getType().equals("0")){
                req.setAttribute("pactTypeName", "原料面料");
            }else if(emkMaterialPact.getType().equals("1")){
                req.setAttribute("pactTypeName", "缝制辅料");
            }else if(emkMaterialPact.getType().equals("2")){
                req.setAttribute("pactTypeName", "包装辅料");
            }
        }
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPact-update2");
    }


    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkMaterialPactController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialPactEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkMaterialPact, request.getParameterMap());
        List<EmkMaterialPactEntity> emkMaterialPacts = emkMaterialPactService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "面料预采购合同");
        modelMap.put("entity", EmkMaterialPactEntity.class);
        modelMap.put("params", new ExportParams("面料预采购合同列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkMaterialPacts);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "面料预采购合同");
        modelMap.put("entity", EmkMaterialPactEntity.class);
        modelMap.put("params", new ExportParams("面料预采购合同列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "面料预采购合同列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkMaterialPactEntity>> list() {
        List<EmkMaterialPactEntity> listEmkMaterialPacts = emkMaterialPactService.getList(EmkMaterialPactEntity.class);
        return Result.success(listEmkMaterialPacts);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取面料预采购合同信息", notes = "根据ID获取面料预采购合同信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMaterialPactEntity task = emkMaterialPactService.get(EmkMaterialPactEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取面料预采购合同信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建面料预采购合同")
    public ResponseMessage<?> create(@ApiParam(name = "面料预采购合同对象") @RequestBody EmkMaterialPactEntity emkMaterialPact, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMaterialPactEntity>> failures = validator.validate(emkMaterialPact, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkMaterialPactService.save(emkMaterialPact);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("面料预采购合同信息保存失败");
        }
        return Result.success(emkMaterialPact);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新面料预采购合同", notes = "更新面料预采购合同")
    public ResponseMessage<?> update(@ApiParam(name = "面料预采购合同对象") @RequestBody EmkMaterialPactEntity emkMaterialPact) {
        Set<ConstraintViolation<EmkMaterialPactEntity>> failures = validator.validate(emkMaterialPact, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkMaterialPactService.saveOrUpdate(emkMaterialPact);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新面料预采购合同信息失败");
        }
        return Result.success("更新面料预采购合同信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除面料预采购合同")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkMaterialPactService.deleteEntityById(EmkMaterialPactEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("面料预采购合同删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params = "doGenerate")
    @ResponseBody
    public AjaxJson doGenerate(EmkMaterialPactEntity emkMaterialPact,String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        //emkMaterialPact = systemService.getEntity(EmkMaterialPactEntity.class, emkMaterialPact.getId());
        message = "生成面料正式采购合同成功";
        try {
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(zscghtbh, 6)),0)+1 AS signed) orderNum from emk_material_pact where flag=1 and type=?",emkMaterialPact.getType());
            EmkMaterialPactEntity pactEntity = new EmkMaterialPactEntity();
            TSDepart depart = systemService.findUniqueByProperty(TSDepart.class,"orgCode","A01");
            pactEntity.setZscghtbh("HT" + DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", Integer.parseInt(orderNum.get("orderNum").toString())));
            pactEntity.setType(emkMaterialPact.getType());
            pactEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
            pactEntity.setFlag("1");
            pactEntity.setPartyA(depart.getDepartname());
            pactEntity.setPartyAId(depart.getOrgCode());

            systemService.save(pactEntity);
            for(String id : ids.split(",")){
                List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", id,emkMaterialPact.getType());
                EmkSampleDetailEntity t = null;
                EmkMaterialPactEntity emkMaterialPactEntity = systemService.get(EmkMaterialPactEntity.class,id);
                for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntities){
                    t = new EmkSampleDetailEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,t);
                    t.setId(null);
                    t.setSampleId(pactEntity.getId());
                    t.setOrderNum(emkMaterialPactEntity.getOrderNum());
                    t.setSampleNo(emkMaterialPactEntity.getSampleNo());
                    t.setDhjqDate(emkMaterialPactEntity.getDhjqDate());
                    systemService.save(t);
                }
            }
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生成面料正式采购合同失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkMaterialPactEntity2 emkMaterialPactEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "操作成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkMaterialPactEntity.getId() == null) || (emkMaterialPactEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialPactEntity materialPactEntity = systemService.getEntity(EmkMaterialPactEntity.class, id);
                    if (!materialPactEntity.getState().equals("0")) {
                        message = "存在已提交的预购销合同单，请重新选择在提交预购销合同单！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkMaterialPactEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialPactEntity t = emkMaterialPactService.get(EmkMaterialPactEntity.class, id);
                    t.setState("1");
                    variables.put("optUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("htTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkMaterialPactEntity.getLeadAdvice());
                            if (emkMaterialPactEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkMaterialPactEntity.getIsPass());
                                taskService.complete(task1.getId(), variables);

                                Map yht = systemService.findOneForJdbc("SELECT id FROM emk_material_pact WHERE  flag=1 and TYPE=? and order_num=?",t.getType(),t.getOrderNum());
                                EmkMaterialPactEntity materialPactEntityHt = systemService.get(EmkMaterialPactEntity.class,yht.get("id").toString());
                                materialPactEntityHt.setYhtUserId(user.getUserName());
                                materialPactEntityHt.setYhter(user.getRealName());
                                materialPactEntityHt.setYhtAdvice(t.getLeadAdvice());

                                systemService.saveOrUpdate(materialPactEntityHt);
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
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("ht", "materialPactEntityYht", variables);
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
            message = "购销合同申请单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }
    @RequestMapping(params="doSubmit2")
    @ResponseBody
    public AjaxJson doSubmit2(EmkMaterialPactEntity2 emkMaterialPactEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "操作成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkMaterialPactEntity.getId() == null) || (emkMaterialPactEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialPactEntity materialPactEntity = systemService.getEntity(EmkMaterialPactEntity.class, id);
                    if (!materialPactEntity.getState().equals("0")) {
                        message = "存在已提交的购销合同单，请重新选择在提交购销合同单！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkMaterialPactEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialPactEntity t = emkMaterialPactService.get(EmkMaterialPactEntity.class, id);
                    t.setState("1");
                    variables.put("optUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("htTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkMaterialPactEntity.getLeadAdvice());
                            if (emkMaterialPactEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkMaterialPactEntity.getIsPass());
                                taskService.complete(task1.getId(), variables);

                                Map yht = systemService.findOneForJdbc("SELECT id FROM emk_material_pact WHERE flag=0 and TYPE=? and order_num=?",t.getType(),t.getOrderNum());
                                EmkMaterialPactEntity materialPactEntityYht = systemService.get(EmkMaterialPactEntity.class,yht.get("id").toString());

                                task = taskService.createTaskQuery().taskAssignee(materialPactEntityYht.getId()).list();
                                task1 = (Task)task.get(task.size() - 1);
                                taskService.complete(task1.getId(), variables);

                                materialPactEntityYht.setYhtUserId(user.getUserName());
                                materialPactEntityYht.setYhter(user.getRealName());
                                materialPactEntityYht.setYhtAdvice(t.getLeadAdvice());
                                materialPactEntityYht.setState("2");
                                systemService.saveOrUpdate(materialPactEntityYht);

                                t.setState("2");
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
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("zsht", "materialPactEntityZsht", variables);
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
            message = "购销合同申请单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkMaterialPactEntity emkMaterialPactEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialPactEntity.getId())) {
            emkMaterialPactEntity = emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, emkMaterialPactEntity.getId());
            req.setAttribute("emkMaterialPact", emkMaterialPactEntity);
        }
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPact-work");

    }
    @RequestMapping(params="goWork2")
    public ModelAndView goWork2(EmkMaterialPactEntity emkMaterialPactEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialPactEntity.getId())) {
            emkMaterialPactEntity = emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, emkMaterialPactEntity.getId());
            req.setAttribute("emkMaterialPact", emkMaterialPactEntity);
        }
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPact-work2");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkMaterialPactEntity emkMaterialPactEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='htTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_material_pact t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
        emkMaterialPactEntity = emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, emkMaterialPactEntity.getId());
        req.setAttribute("emkMaterialPact", emkMaterialPactEntity);
        return new ModelAndView("com/emk/bill/materialpact/time");

    }
}
