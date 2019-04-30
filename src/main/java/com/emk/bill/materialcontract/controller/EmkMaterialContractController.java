package com.emk.bill.materialcontract.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.bill.materialcontract.entity.EmkMaterialContractEntity;
import com.emk.bill.materialcontract.service.EmkMaterialContractServiceI;
import com.emk.bill.materialcontractdetail.entity.EmkMaterialContractDetailEntity;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.caiwu.yfcheck.entity.EmkFinanceYfCheckEntity;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.util.ApprovalUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

@Api(value = "EmkMaterialContract", description = "原料采购合同表", tags = "emkMaterialContractController")
@Controller
@RequestMapping("/emkMaterialContractController")
public class EmkMaterialContractController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMaterialContractController.class);
    @Autowired
    private EmkMaterialContractServiceI emkMaterialContractService;
    @Autowired
    private Validator validator;


    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContractList");
    }
    @RequestMapping(params = "list2")
    public ModelAndView list2(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContractList2");
    }
    @RequestMapping(params = "list3")
    public ModelAndView list3(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContractList3");
    }
    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", map.get("proOrderId"),map.get("type"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        if(map.get("type").equals("0")){
            return new ModelAndView("com/emk/bill/materialcontract/orderMxList");
        }else if(map.get("type").equals("1")){
            return new ModelAndView("com/emk/bill/materialcontract/orderMxList2");
        }else if(map.get("type").equals("2")){
            return new ModelAndView("com/emk/bill/materialcontract/orderMxList3");
        }
        return new ModelAndView("com/emk/bill/materialcontract/orderMxList");
    }



    @RequestMapping(params = "emkMaterialContractDetailList")
    public ModelAndView rkglMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("contractId") != null) && (!map.get("contractId").equals(""))) {
            List<EmkMaterialContractDetailEntity> rkglMxEntities = this.systemService.findHql("from EmkMaterialContractDetailEntity where contractId=?", new Object[]{map.get("contractId")});
            request.setAttribute("rkglMxList", rkglMxEntities);
        }
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContractDetailList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialContractEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkMaterialContract, request.getParameterMap());
        cq.add();
        this.emkMaterialContractService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMaterialContract = systemService.getEntity(EmkMaterialContractEntity.class, emkMaterialContract.getId());
        message = "原料采购合同表删除成功";
        try {
            this.emkMaterialContractService.delete(emkMaterialContract);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料采购合同表删除失败";
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
        message = "原料采购合同表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkMaterialContractEntity emkMaterialContract = systemService.getEntity(EmkMaterialContractEntity.class, id);


                this.emkMaterialContractService.delete(emkMaterialContract);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料采购合同表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料采购合同表添加成功";
        try {
            emkMaterialContract.setState("0");
            this.emkMaterialContractService.save(emkMaterialContract);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            String dataRows = map.get("orderMxListID");
            dataRows = map.get("orderMxListID");
            //保存原料面料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        emkSampleDetailEntity.setSumTotal(map.get("orderMxList["+i+"].sumTotal"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setSampleId(emkMaterialContract.getId());
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
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        emkSampleDetailEntity.setSumTotal(map.get("orderMxList["+i+"].sumTotal"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setSampleId(emkMaterialContract.getId());
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
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        emkSampleDetailEntity.setSumTotal(map.get("orderMxList["+i+"].sumTotal"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setSampleId(emkMaterialContract.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料采购合同表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料采购合同表更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

        EmkMaterialContractEntity t = emkMaterialContractService.get(EmkMaterialContractEntity.class, map.get("emkMaterialContractId"));
        try {
            emkMaterialContract.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterialContract, t);
            this.emkMaterialContractService.saveOrUpdate(t);

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
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        emkSampleDetailEntity.setSumTotal(map.get("orderMxList["+i+"].sumTotal"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType(t.getType());
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }

            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料采购合同表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkMaterialContract.getId())) {
            emkMaterialContract = emkMaterialContractService.getEntity(EmkMaterialContractEntity.class, emkMaterialContract.getId());
            req.setAttribute("emkMaterialContractPage", emkMaterialContract);
        }
        req.getSession().setAttribute("orderFinish", "");
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContract-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialContract.getId())) {
            emkMaterialContract = emkMaterialContractService.getEntity(EmkMaterialContractEntity.class, emkMaterialContract.getId());
            req.setAttribute("emkMaterialContractPage", emkMaterialContract);

            if(emkMaterialContract.getType().equals("0")){
                req.setAttribute("pactTypeName", "原料面料");
            }else if(emkMaterialContract.getType().equals("1")){
                req.setAttribute("pactTypeName", "缝制辅料");
            }else if(emkMaterialContract.getType().equals("2")){
                req.setAttribute("pactTypeName", "包装辅料");
            }
        }
        req.getSession().setAttribute("orderFinish", "");

        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContract-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialContract.getId())) {
            emkMaterialContract = emkMaterialContractService.getEntity(EmkMaterialContractEntity.class, emkMaterialContract.getId());
            req.setAttribute("emkMaterialContractPage", emkMaterialContract);

            if(emkMaterialContract.getType().equals("0")){
                req.setAttribute("pactTypeName", "原料面料");
            }else if(emkMaterialContract.getType().equals("1")){
                req.setAttribute("pactTypeName", "缝制辅料");
            }else if(emkMaterialContract.getType().equals("2")){
                req.setAttribute("pactTypeName", "包装辅料");
            }
        }
        req.getSession().setAttribute("orderFinish", "");

        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContract-update2");
    }

    @RequestMapping(params = "goUpdate3")
    public ModelAndView goUpdate3(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialContract.getId())) {
            EmkMaterialRequiredEntity emkMaterialRequiredEntity = systemService.get(EmkMaterialRequiredEntity.class,emkMaterialContract.getId());
            emkMaterialContract = emkMaterialContractService.findUniqueByProperty(EmkMaterialContractEntity.class, "caigouNo",emkMaterialRequiredEntity.getMaterialNo());
            req.setAttribute("emkMaterialContractPage", emkMaterialContract);

            if(emkMaterialContract.getType().equals("0")){
                req.setAttribute("pactTypeName", "原料面料");
            }else if(emkMaterialContract.getType().equals("1")){
                req.setAttribute("pactTypeName", "缝制辅料");
            }else if(emkMaterialContract.getType().equals("2")){
                req.setAttribute("pactTypeName", "包装辅料");
            }

            List<TSUser> userList = systemService.findHql("from TSUser where userKey='业务员'");
            req.setAttribute("ywyList", userList);
            userList = systemService.findHql("from TSUser where userKey='业务跟单员'");
            req.setAttribute("ywgdyList", userList);
            userList = systemService.findHql("from TSUser where userKey='生产跟单员'");
            req.setAttribute("scgdyList", userList);

            List<EmkFactoryArchivesEntity> supplierEntities = systemService.findHql("from EmkFactoryArchivesEntity", null);
            req.setAttribute("supplierEntities", supplierEntities);


        }
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContract-update3");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkMaterialContractController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialContractEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkMaterialContract, request.getParameterMap());
        List<EmkMaterialContractEntity> emkMaterialContracts = this.emkMaterialContractService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "原料采购合同表");
        modelMap.put("entity", EmkMaterialContractEntity.class);
        modelMap.put("params", new ExportParams("原料采购合同表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkMaterialContracts);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "原料采购合同表");
        modelMap.put("entity", EmkMaterialContractEntity.class);
        modelMap.put("params", new ExportParams("原料采购合同表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "原料采购合同表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkMaterialContractEntity>> list() {
        List<EmkMaterialContractEntity> listEmkMaterialContracts = this.emkMaterialContractService.getList(EmkMaterialContractEntity.class);
        return Result.success(listEmkMaterialContracts);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取原料采购合同表信息", notes = "根据ID获取原料采购合同表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMaterialContractEntity task = emkMaterialContractService.get(EmkMaterialContractEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取原料采购合同表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建原料采购合同表")
    public ResponseMessage<?> create(@ApiParam(name = "原料采购合同表对象") @RequestBody EmkMaterialContractEntity emkMaterialContract, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMaterialContractEntity>> failures = this.validator.validate(emkMaterialContract, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialContractService.save(emkMaterialContract);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("原料采购合同表信息保存失败");
        }
        return Result.success(emkMaterialContract);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新原料采购合同表", notes = "更新原料采购合同表")
    public ResponseMessage<?> update(@ApiParam(name = "原料采购合同表对象") @RequestBody EmkMaterialContractEntity emkMaterialContract) {
        Set<ConstraintViolation<EmkMaterialContractEntity>> failures = this.validator.validate(emkMaterialContract, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialContractService.saveOrUpdate(emkMaterialContract);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新原料采购合同表信息失败");
        }
        return Result.success("更新原料采购合同表信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除原料采购合同表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkMaterialContractService.deleteEntityById(EmkMaterialContractEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("原料采购合同表删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params = "doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkMaterialContractEntity emkMaterialContractEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料布料采购单提交成功";
        try {
            int flag = 0;

            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkMaterialContractEntity.getId() == null) || (emkMaterialContractEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialContractEntity inStorageEntity = systemService.getEntity(EmkMaterialContractEntity.class, id);
                    if (!inStorageEntity.getState().equals("0")) {
                        message = "存在已提交的订单，请重新选择在提交订单！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            } else {
                map.put("ids", emkMaterialContractEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialContractEntity t = emkMaterialContractService.get(EmkMaterialContractEntity.class, id);
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
                    if(Utils.isEmpty(b)){
                        //type 工单类型，workNum 单号，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
                        b = new EmkApprovalEntity();
                        EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
                        if(t.getType().equals("0")){
                            ApprovalUtil.saveApproval(b,12,t.getPayNo(),t.getId(),user);
                        }else if(t.getType().equals("1")){
                            ApprovalUtil.saveApproval(b,13,t.getPayNo(),t.getId(),user);
                        }else if(t.getType().equals("2")){
                            ApprovalUtil.saveApproval(b,14,t.getPayNo(),t.getId(),user);
                        }
                        systemService.save(b);
                        ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"【生产跟单员】费用付款申请单","fyfksqdTask","提交",user);
                        systemService.save(approvalDetailEntity);
                    }
                    t.setState("1");
                    variables.put("optUser", t.getId());

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
                    if (task.size() > 0) {
                        Task task1 = (Task) task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("fyfksqdTask")) {
                            taskService.complete(task1.getId(), variables);
                            t.setState("1");
                            b.setStatus(1);
                            saveApprvoalDetail(approvalDetail,"重新提交的费用付款申请单","orderTask",0,"重新提交费用付款申请单");
                            saveSmsAndEmailForMany("业务员","重新提交的费用付款申请单","您有【"+b.getCreateName()+"】重新提交的费用付款申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }
                        if(task1.getTaskDefinitionKey().equals("ywyTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(35);
                                approvalDetail.setBpmName("业务员复核");
                                t.setState("35");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("业务经理","业务员复核","您有【"+b.getCreateName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务员复核","ywyTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"ywyTask","fyfksqdTask","用付款申请单");
                                t.setState("0");
                                b.setStatus(0);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务员复核","您有【"+user.getRealName()+"】回退的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("ywjlshTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(3);
                                approvalDetail.setBpmName("业务经理审核");
                                t.setState("3");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("技术员","业务经理审核","您有【"+b.getCreateName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务经理审核","ywjlshTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"ywjlshTask","ywyTask","业务员复核");
                                t.setState("21");
                                b.setStatus(21);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("jsyzsTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(22);
                                approvalDetail.setBpmName("技术员再审");
                                t.setState("22");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("采购员","技术员再审","您有【"+b.getCreateName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"技术员再审","jsyzsTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"jsyzsTask","ywjlshTask","业务经理审核");
                                t.setState("4");
                                b.setStatus(4);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("技术员再审","您有【"+user.getRealName()+"】回退的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("cgyzsTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(24);
                                approvalDetail.setBpmName("采购员再审");
                                t.setState("24");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("采购经理","采购员再审","您有【"+b.getCreateName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购员再审","cgyzsTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cgyzsTask","jsyzsTask","技术员再审");
                                t.setState("23");
                                b.setStatus(23);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购员再审","您有【"+user.getRealName()+"】回退的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }

                        if(task1.getTaskDefinitionKey().equals("cgjlzsTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(41);
                                approvalDetail.setBpmName("采购经理再审");
                                t.setState("41");
                                b.setNextBpmSher(map.get("realName"));
                                b.setNextBpmSherId(map.get("userName"));
                                approvalDetail.setApproveStatus(0);

                                bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());
                                saveSmsAndEmailForOne("采购经理再审","您有【"+user.getRealName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());

                                //采购需求单流程流转下一环节
                                saveMaterialRequiredProcess(t,"审核通过的付款申请单",map.get("advice"),user);
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购经理再审","cgjlzsTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cgjlzsTask","cgyzsTask","采购员再审");
                                t.setState("36");
                                b.setStatus(36);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购经理再审","您有【"+user.getRealName()+"】回退的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("cwshTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(25);
                                approvalDetail.setBpmName("财务审核");
                                t.setState("25");
                                b.setNextBpmSher(map.get("realName"));
                                b.setNextBpmSherId(map.get("userName"));
                                approvalDetail.setApproveStatus(0);

                                saveSmsAndEmailForMany("财务经理","财务审核","您有【"+b.getCreateName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"财务审核","cwshTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cwshTask","cgjlzsTask","采购经理再审");
                                t.setState("16");
                                b.setStatus(16);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("财务审核","您有【"+user.getRealName()+"】回退的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("cwjlshTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(26);
                                approvalDetail.setBpmName("财务经理审核");
                                t.setState("26");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForOne("财务经理审核","您有【"+user.getRealName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());

                                //生成应付核准单
                                EmkFinanceYfCheckEntity emkFinanceYfCheckEntity = new EmkFinanceYfCheckEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t,emkFinanceYfCheckEntity);
                                emkFinanceYfCheckEntity.setId(null);
                                emkFinanceYfCheckEntity.setYlmldyfyfkNo(t.getPayNo());
                                Map<String, Object> orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(yhz_check_no, 6)),0)+1 AS signed) orderNum from emk_finance_yf_check");
                                emkFinanceYfCheckEntity.setYfCheckNo("YF" + DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", Integer.parseInt(orderNum.get("orderNum").toString())));
                                systemService.save(emkFinanceYfCheckEntity);

                                //采购需求单流程流转下一环节
                                saveMaterialRequiredProcess(t,"财务审核通过的付款申请单",map.get("advice"),user);
                            }else{
                                saveApprvoalDetail(approvalDetail,"财务经理审核","cwjlshTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cwjlshTask","cwshTask","财务审核");
                                t.setState("27");
                                b.setStatus(27);
                                b.setBpmStatus("1");
                                b.setNextBpmSher(bpmUser.getRealName());
                                b.setNextBpmSherId(bpmUser.getUserName());
                                saveSmsAndEmailForOne("财务经理审核","您有【"+user.getRealName()+"】回退的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("fkhzdTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(47);
                                approvalDetail.setBpmName("【财务员】付款核准单");
                                t.setState("47");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("总经理","【财务员】付款核准单","您有【"+b.getCreateName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());

                                //采购需求单流程流转下一环节
                                saveMaterialRequiredProcess(t,"总经理审核通过的付款申请单",map.get("advice"),user);
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("zjlpfTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(48);
                                approvalDetail.setBpmName("总经理批复");
                                t.setState("48");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForOne("总经理批复","您有【"+user.getRealName()+"】审核通过的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"总经理批复","zjlpfTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"zjlpfTask","fkhzdTask","【财务员】付款核准单");
                                t.setState("27");
                                b.setStatus(27);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("总经理批复","您有【"+user.getRealName()+"】回退的费用付款申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("cwzzTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(2);
                                approvalDetail.setBpmName("财务转账");
                                t.setState("2");
                                approvalDetail.setApproveStatus(0);
                            }
                        }
                        systemService.save(approvalDetail);

                    } else {
                        ProcessInstance pi = this.processEngine.getRuntimeService().startProcessInstanceByKey("yf", "emkMaterialContractEntity", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);

                        saveSmsAndEmailForMany("业务员","【生产跟单员】费用付款申请单","您有【"+b.getCreateName()+"】提交的费用付款申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        t.setState("1");
                        b.setStatus(1);
                        b.setBpmStatus("0");
                        b.setProcessName("【生产跟单员】费用付款申请单");

                        //采购需求单流程流转下一环节
                        saveMaterialRequiredProcess(t,"提交审核的付款申请单",map.get("advice"),user);
                    }
                    systemService.saveOrUpdate(t);
                    systemService.saveOrUpdate(b);
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料布料采购单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goWork")
    public ModelAndView goWork(EmkMaterialContractEntity emkMaterialContractEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialContractEntity.getId())) {
            emkMaterialContractEntity = emkMaterialContractService.getEntity(EmkMaterialContractEntity.class, emkMaterialContractEntity.getId());
            req.setAttribute("emkMaterialContract", emkMaterialContractEntity);
        }
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContract-work");
    }

    @RequestMapping(params = "goTime")
    public ModelAndView goTime(EmkMaterialContractEntity emkMaterialContractEntity, HttpServletRequest req, DataGrid dataGrid) {
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkMaterialContractEntity.getId());
        if(Utils.notEmpty(approvalEntity)){
            List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
            req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
            req.setAttribute("approvalEntity", approvalEntity);
            req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
        }
        return new ModelAndView("com/emk/bill/materialcontract/time");
    }

}
