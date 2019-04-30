package com.emk.bill.materialpact.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity2;
import com.emk.bill.materialpact.service.EmkMaterialPactServiceI;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;


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
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    private Validator validator;


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
        emkMaterialPact.setProTypeName(emkMaterialPact.getProTypeName().replaceAll(",",""));
        EmkMaterialPactEntity t = emkMaterialPactService.get(EmkMaterialPactEntity.class, map.get("pactId").toString());
        try {
            emkMaterialPact.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterialPact, t);
            emkMaterialPactService.saveOrUpdate(t);

            String dataRows = map.get("orderMxListID");
            if(t.getType().equals("0")){
                dataRows = map.get("orderMxListID");
            }else if(t.getType().equals("1")){
                dataRows = map.get("orderMxListID2");
            }else if(t.getType().equals("2")){
                dataRows = map.get("orderMxListID3");
            }
            //保存原料面料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=?",t.getId(),t.getType());
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
                        emkSampleDetailEntity.setType(t.getType());
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }

            if("1".equals(map.get("isPass"))){
                EmkMaterialRequiredEntity a = systemService.get(EmkMaterialRequiredEntity.class, map.get("materialRequiredId"));
                EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",a.getId());

                a.setState("46");
                b.setStatus(46);

                systemService.saveOrUpdate(a);
                systemService.saveOrUpdate(b);
                Map<String, Object> variables = new HashMap();
                List<Task> task = taskService.createTaskQuery().taskAssignee(a.getId()).list();
                if(Utils.notEmpty(task)){
                    Task task1 = task.get(task.size() - 1);
                    if(task1.getTaskDefinitionKey().equals("htTask")) {
                        variables.put("inputUser",a.getId());
                        taskService.complete(task1.getId(), variables);
                        TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
                        saveSmsAndEmailForMany("采购经理","【采购员】正式购销合同","您有【"+b.getCreateName()+"】审核过的采购需求单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
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

    @RequestMapping(params = "goUpdate3")
    public ModelAndView goUpdate3(EmkMaterialPactEntity emkMaterialPact, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialPact.getId())) {
            EmkMaterialRequiredEntity materialRequiredEntity = systemService.get(EmkMaterialRequiredEntity.class,emkMaterialPact.getId());
            Map m = systemService.findOneForJdbc("SELECT DISTINCT sample_id  FROM emk_sample_detail t WHERE t.cgxqdh=?" ,materialRequiredEntity.getMaterialNo());
            if(Utils.notEmpty(m)){
                emkMaterialPact = emkMaterialPactService.getEntity(EmkMaterialPactEntity.class, m.get("sample_id").toString());
            }

            List<TSUser> userList = systemService.findHql("from TSUser where userKey='业务员'");
            req.setAttribute("ywyList", userList);
            userList = systemService.findHql("from TSUser where userKey='业务跟单员'");
            req.setAttribute("ywgdyList", userList);
            userList = systemService.findHql("from TSUser where userKey='生产跟单员'");
            req.setAttribute("scgdyList", userList);

            req.setAttribute("emkMaterialPactPage", emkMaterialPact);
            if(emkMaterialPact.getType().equals("0")){
                req.setAttribute("pactTypeName", "原料面料");
            }else if(emkMaterialPact.getType().equals("1")){
                req.setAttribute("pactTypeName", "缝制辅料");
            }else if(emkMaterialPact.getType().equals("2")){
                req.setAttribute("pactTypeName", "包装辅料");
            }
        }
        return new ModelAndView("com/emk/bill/materialpact/emkMaterialPact-update3");
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
            for (String id : ids.split(",")) {
                EmkMaterialPactEntity materialPactEntity = systemService.getEntity(EmkMaterialPactEntity.class, id);
                if (!materialPactEntity.getState().equals("0")) {
                    message = "存在已生成的预购销合同单，请重新选择预购销合同单！";
                    j.setSuccess(false);
                    j.setMsg(message);
                    return j;
                }
            }
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(zscghtbh, 6)),0)+1 AS signed) orderNum from emk_material_pact where flag=1 and type=?",emkMaterialPact.getType());
            EmkMaterialPactEntity pactEntity = new EmkMaterialPactEntity();
            TSDepart depart = systemService.findUniqueByProperty(TSDepart.class,"orgCode","A01");
            pactEntity.setZscghtbh("HT" + DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", Integer.parseInt(orderNum.get("orderNum").toString())));
            pactEntity.setType(emkMaterialPact.getType());
            pactEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
            pactEntity.setFlag("1");
            //pactEntity.setPartyA(depart.getDepartname());
            pactEntity.setPartyAId(depart.getOrgCode());

            systemService.save(pactEntity);
            for(String id : ids.split(",")){
                EmkMaterialPactEntity materialPactEntity = systemService.get(EmkMaterialPactEntity.class,id);
                materialPactEntity.setState("1");
                systemService.saveOrUpdate(materialPactEntity);

                List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", id,emkMaterialPact.getType());
                EmkSampleDetailEntity t = null;
                EmkMaterialPactEntity emkMaterialPactEntity = systemService.get(EmkMaterialPactEntity.class,id);
                for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntities){
                    t = new EmkSampleDetailEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,t);
                    t.setId(null);
                    t.setOrderNum(emkMaterialPactEntity.getOrderNum());
                    t.setSampleNo(emkMaterialPactEntity.getSampleNo());
                    t.setDhjqDate(emkMaterialPactEntity.getDhjqDate());
                    t.setSampleId(pactEntity.getId());
                    systemService.save(t);
                }

                Map<String, Object> variables = new HashMap();
                //订单流程流转到购销合同环节
                EmkProOrderEntity proOrderEntity = systemService.findUniqueByProperty(EmkProOrderEntity.class,"orderNo",materialPactEntity.getOrderNum());
                if(Utils.notEmpty(proOrderEntity)){
                    List<Task> task = taskService.createTaskQuery().taskAssignee(proOrderEntity.getId()).list();
                    if(Utils.notEmpty(task)){
                        Task task1 = task.get(task.size() - 1);
                        if(task1.getTaskDefinitionKey().equals("ygxhtTask")) {
                            variables.put("optUser", proOrderEntity.getId());
                            taskService.complete(task1.getId(), variables);

                            EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",proOrderEntity.getId());
                            b.setStatus(45);
                            proOrderEntity.setState("45");
                            systemService.saveOrUpdate(b);
                            systemService.saveOrUpdate(proOrderEntity);
                        }
                    }

                }


                //采购需求单流程流转到采购员执行环节
                Map m = systemService.findOneForJdbc("select * from emk_material_required where material_no=? and type=?",materialPactEntity.getCgxqdh(),materialPactEntity.getType());
                //EmkMaterialRequiredEntity materialRequiredEntity = systemService.findUniqueByProperty(EmkMaterialRequiredEntity.class,"materialNo",materialPactEntity.getCgxqdh());
                if(Utils.notEmpty(m)){
                    List<Task> task = taskService.createTaskQuery().taskAssignee(m.get("id").toString()).list();
                    if(Utils.notEmpty(task)){
                        Task task1 = task.get(task.size() - 1);
                        if(task1.getTaskDefinitionKey().equals("ycghtTask")) {
                            variables.put("inputUser", m.get("id").toString());
                            taskService.complete(task1.getId(), variables);
                        }
                    }

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
            if (Utils.notEmpty(emkMaterialPactEntity.getId())) {
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
