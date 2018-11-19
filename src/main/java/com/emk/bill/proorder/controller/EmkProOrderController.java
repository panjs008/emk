package com.emk.bill.proorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.bzgyd.entity.EmkBzgydEntity;
import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.bill.proorder.service.EmkProOrderServiceI;
import com.emk.bill.proorderbarcode.entity.EmkProOrderBarcodeEntity;
import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;
import com.emk.bill.proorderdetail.entity.EmkProOrderDetailEntity;
import com.emk.bill.proorderpackage.entity.EmkProOrderPackageEntity;
import com.emk.bill.yjyhtime.entity.EmkYjyhTimeEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.instorage.entity.EmkInStorageEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.storage.entity.EmkStorageEntity;
import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
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
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
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

@Api(value = "EmkProOrder", description = "生产订单", tags = "emkProOrderController")
@Controller
@RequestMapping("/emkProOrderController")
public class EmkProOrderController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProOrderController.class);
    @Autowired
    private EmkProOrderServiceI emkProOrderService;
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
        return new ModelAndView("com/emk/bill/proorder/emkProOrderList");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkProOrderDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkProOrderDetailEntity where proOrderId=?", new Object[]{map.get("proOrderId")});
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/bill/proorder/orderMxList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkProOrder, request.getParameterMap());


        cq.add();
        this.emkProOrderService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkProOrderEntity emkProOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProOrder = (EmkProOrderEntity) this.systemService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());
        message = "生产订单删除成功";
        try {
            if (!emkProOrder.getState().equals("0")) {
                message = "订单已经提交处理，无法删除";
                j.setMsg(message);
                j.setSuccess(false);
                return j;
            }
            this.systemService.executeSql("delete from emk_pro_order_detail where PRO_ORDER_ID=?", new Object[]{emkProOrder.getId()});
            this.emkProOrderService.delete(emkProOrder);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单删除失败";
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
        message = "生产订单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProOrderEntity emkProOrder = (EmkProOrderEntity) this.systemService.getEntity(EmkProOrderEntity.class, id);
                if (!emkProOrder.getState().equals("0")) {
                    message = "存在已提交的订单，请重新选择在提交订单！";
                    j.setSuccess(false);
                    j.setMsg(message);
                    return  j;
                }
                this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", emkProOrder.getId());
                this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", emkProOrder.getId());
                this.systemService.executeSql("delete from emk_pro_order_barcode where order_id=?", emkProOrder.getId());
                this.systemService.executeSql("delete from emk_pro_order_box where order_id=?", emkProOrder.getId());
                this.systemService.executeSql("delete from emk_pro_order_package where order_id=?", emkProOrder.getId());


                this.emkProOrderService.delete(emkProOrder);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkProOrderEntity emkProOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单添加成功";
        try {
            Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(ORDER_NO, 6)),0)+1 AS signed) orderNum from emk_pro_order");
//            Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_pro_order where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
            emkProOrder.setOrderNo("D" + DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkProOrder.setState("0");
            this.emkProOrderService.save(emkProOrder);
            EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",emkProOrder.getWorkNo());
            if(workOrderEntity != null){
                EmkPriceEntity priceEntity = systemService.findUniqueByProperty(EmkPriceEntity.class,"xpNo",workOrderEntity.getAskNo());
                EmkSampleDetailEntity emkSampleDetailEntity = null;

                List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=?", priceEntity.getId());
                for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                    emkSampleDetailEntity = new EmkSampleDetailEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                    emkSampleDetailEntity.setId(null);
                    emkSampleDetailEntity.setSampleId(emkProOrder.getId());
                    this.systemService.save(emkSampleDetailEntity);
                }
            }

            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkProOrderEntity emkProOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单更新成功";


        EmkProOrderEntity t = (EmkProOrderEntity) this.emkProOrderService.get(EmkProOrderEntity.class, emkProOrder.getId());
        try {
            if(!t.getState().equals("0")){
                j.setMsg("订单已提交，请重新选择");
                j.setSuccess(false);
                return j;
            }
            MyBeanUtils.copyBeanNotNull2Bean(emkProOrder, t);
            this.emkProOrderService.saveOrUpdate(t);
            EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",t.getWorkNo());
            if(workOrderEntity != null){
                EmkPriceEntity priceEntity = systemService.findUniqueByProperty(EmkPriceEntity.class,"xpNo",workOrderEntity.getAskNo());
                EmkSampleDetailEntity emkSampleDetailEntity = null;
                systemService.executeSql("delete from emk_sample_detail where SAMPLE_ID=?",t.getId());
                List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=?", t.getId());
                for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                    emkSampleDetailEntity = new EmkSampleDetailEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                    emkSampleDetailEntity.setId(null);
                    emkSampleDetailEntity.setSampleId(t.getId());
                    this.systemService.save(emkSampleDetailEntity);
                }
            }

            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = (EmkProOrderEntity) this.emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());
            req.setAttribute("emkProOrderPage", emkProOrder);
        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = (EmkProOrderEntity) this.emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(org.jeecgframework.core.util.DateUtils.str2Date(emkProOrder.getRecevieDate(), org.jeecgframework.core.util.DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = org.jeecgframework.core.util.DateUtils.dateDiff('d',cal1,cal2);
            if(day<0){
                day = 0;
            }
            req.setAttribute("levelDays",day);

            Map infoMap = this.systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total,ifnull(ROUND(SUM(t.total*t.price),2),0) sumprice FROM emk_enquiry_detail t WHERE t.enquiry_id=?",emkProOrder.getId());

            emkProOrder.setSumTotal(Integer.parseInt(infoMap.get("total").toString()));
            emkProOrder.setSumMoney(Double.parseDouble(infoMap.get("sumprice").toString()));

            req.setAttribute("emkProOrderPage", emkProOrder);

        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = (EmkProOrderEntity) this.emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(org.jeecgframework.core.util.DateUtils.str2Date(emkProOrder.getRecevieDate(), org.jeecgframework.core.util.DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = org.jeecgframework.core.util.DateUtils.dateDiff('d',cal1,cal2);
            if(day<0){
                day = 0;
            }
            req.setAttribute("levelDays",day);

            Map infoMap = this.systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total,ifnull(ROUND(SUM(t.total*t.price),2),0) sumprice FROM emk_enquiry_detail t WHERE t.enquiry_id=?",emkProOrder.getId());
            emkProOrder.setSumTotal(Integer.parseInt(infoMap.get("total").toString()));
            emkProOrder.setSumMoney(Double.parseDouble(infoMap.get("sumprice").toString()));
            req.setAttribute("emkProOrderPage", emkProOrder);

        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProOrderController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProOrder, request.getParameterMap());
        List<EmkProOrderEntity> emkProOrders = this.emkProOrderService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "生产订单");
        modelMap.put("entity", EmkProOrderEntity.class);
        modelMap.put("params", new ExportParams("生产订单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProOrders);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "生产订单");
        modelMap.put("entity", EmkProOrderEntity.class);
        modelMap.put("params", new ExportParams("生产订单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "生产订单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkProOrderEntity>> list() {
        List<EmkProOrderEntity> listEmkProOrders = this.emkProOrderService.getList(EmkProOrderEntity.class);
        return Result.success(listEmkProOrders);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取生产订单信息", notes = "根据ID获取生产订单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProOrderEntity task = (EmkProOrderEntity) this.emkProOrderService.get(EmkProOrderEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取生产订单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建生产订单")
    public ResponseMessage<?> create(@ApiParam(name = "生产订单对象") @RequestBody EmkProOrderEntity emkProOrder, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProOrderEntity>> failures = this.validator.validate(emkProOrder, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderService.save(emkProOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("生产订单信息保存失败");
        }
        return Result.success(emkProOrder);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新生产订单", notes = "更新生产订单")
    public ResponseMessage<?> update(@ApiParam(name = "生产订单对象") @RequestBody EmkProOrderEntity emkProOrder) {
        Set<ConstraintViolation<EmkProOrderEntity>> failures = this.validator.validate(emkProOrder, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderService.saveOrUpdate(emkProOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新生产订单信息失败");
        }
        return Result.success("更新生产订单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除生产订单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkProOrderService.deleteEntityById(EmkProOrderEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("生产订单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkProOrderEntity emkProOrderEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订单录入申请提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkProOrderEntity.getId() == null) || (emkProOrderEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkProOrderEntity proOrderEntity = systemService.getEntity(EmkProOrderEntity.class, id);
                    if (!proOrderEntity.getState().equals("0")) {
                        message = "存在已提交的订单录入申请，请重新选择在提交订单录入申请！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkProOrderEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkProOrderEntity t = emkProOrderService.get(EmkProOrderEntity.class, id);
                    t.setState("1");
                    variables.put("optUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("instorageTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setState("2");
                            t.setLeadAdvice(map.get("leadAdvice").toString());
                            variables.put("isPass", emkProOrderEntity.getIsPass());

                            if (emkProOrderEntity.getIsPass().equals("0")) {
                                /*if ((map.get("realName") == null) || (map.get("realName").toString().equals(""))) {
                                    j.setSuccess(false);
                                    j.setMsg("请选择下一处理人");
                                    return j;
                                }*/
                                EmkMaterialRequiredEntity materialRequiredEntity = null;
                                EmkMaterialPactEntity materialPactEntity = null;
                                EmkMaterialPactEntity pactEntity = null;

                                //生成合同表
                                EmkContractEntity contractEntity = new EmkContractEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t, contractEntity);

                                contractEntity.setId(null);
                                contractEntity.setState("0");
                                contractEntity.setPartyA(user.getCurrentDepart().getDepartname());
                                contractEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
                                contractEntity.setPartyB(t.getGys());
                                contractEntity.setPartyBId(t.getGysCode());
                                contractEntity.setHtNum(t.getOrderNo().replace("D", "HT"));

                                /*EmkContractEntity emkContractEntity = (EmkContractEntity) this.systemService.findUniqueByProperty(EmkContractEntity.class, "htNum", contractEntity.getHtNum());
                                String contractId = "";
                                if (emkContractEntity != null) {
                                    MyBeanUtils.copyBeanNotNull2Bean(contractEntity, emkContractEntity);
                                    this.systemService.saveOrUpdate(emkContractEntity);
                                    this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{emkContractEntity.getId()});
                                    contractId = emkContractEntity.getId();
                                } else {
                                    this.systemService.save(contractEntity);
                                    contractId = contractEntity.getId();
                                }*/
                                this.systemService.save(contractEntity);

                                EmkEnquiryDetailEntity emkEnquiryDetailEntity = null;
                                List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?",t.getId());
                                for (EmkEnquiryDetailEntity enquiryDetailEntity : emkEnquiryDetailEntityList) {
                                    emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(enquiryDetailEntity, emkEnquiryDetailEntity);
                                    emkEnquiryDetailEntity.setId(null);
                                    //emkEnquiryDetailEntity.setEnquiryId(contractId);
                                    emkEnquiryDetailEntity.setEnquiryId(contractEntity.getId());
                                    this.systemService.save(emkEnquiryDetailEntity);
                                }

                                //生成包装工艺表
                                EmkBzgydEntity bzgydEntity = new EmkBzgydEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t, bzgydEntity);
                                this.systemService.save(bzgydEntity);
                                EmkProOrderBarcodeEntity emkProOrderBarcodeEntity = null;
                                EmkProOrderBoxEntity emkProOrderBoxEntity = null;
                                EmkProOrderPackageEntity emkProOrderPackageEntity = null;
                                EmkSampleDetailEntity emkSampleDetailEntity = null;

                                List<EmkProOrderBarcodeEntity> proOrderBarcodeEntityList = systemService.findHql("from EmkProOrderBarcodeEntity where orderId=?",t.getId());
                                for(EmkProOrderBarcodeEntity proOrderBarcodeEntity : proOrderBarcodeEntityList){
                                    emkProOrderBarcodeEntity = new EmkProOrderBarcodeEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(proOrderBarcodeEntity, emkProOrderBarcodeEntity);
                                    emkProOrderBarcodeEntity.setId(null);
                                    emkProOrderBarcodeEntity.setOrderId(bzgydEntity.getId());
                                    systemService.save(emkProOrderBarcodeEntity);
                                }
                                List<EmkProOrderBoxEntity> proOrderBoxEntityList = systemService.findHql("from EmkProOrderBoxEntity where orderId=?",t.getId());
                                for(EmkProOrderBoxEntity proOrderBoxEntity : proOrderBoxEntityList){
                                    emkProOrderBoxEntity = new EmkProOrderBoxEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(proOrderBoxEntity, emkProOrderBoxEntity);
                                    emkProOrderBoxEntity.setId(null);
                                    emkProOrderBoxEntity.setOrderId(bzgydEntity.getId());
                                    systemService.save(emkProOrderBoxEntity);

                                }
                                List<EmkProOrderPackageEntity> proOrderPackageEntityList = systemService.findHql("from EmkProOrderPackageEntity where orderId=?",t.getId());
                                for(EmkProOrderPackageEntity proOrderPackageEntity : proOrderPackageEntityList){
                                    emkProOrderPackageEntity = new EmkProOrderPackageEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(proOrderPackageEntity, emkProOrderPackageEntity);
                                    emkProOrderPackageEntity.setId(null);
                                    emkProOrderPackageEntity.setOrderId(bzgydEntity.getId());
                                    systemService.save(emkProOrderPackageEntity);
                                }
                                List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=2", t.getId());
                                for(EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList){
                                    emkSampleDetailEntity = new EmkSampleDetailEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                    emkSampleDetailEntity.setId(null);
                                    emkSampleDetailEntity.setSampleId(bzgydEntity.getId());
                                    this.systemService.save(emkSampleDetailEntity);
                                }


                                //预计验货时间表
                                EmkYjyhTimeEntity yjyhTimeEntity = new EmkYjyhTimeEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t, yjyhTimeEntity);
                                yjyhTimeEntity.setYjzqyhDate(t.getZqyhDate());
                                yjyhTimeEntity.setYjwqyhDate(t.getWqyhDate());
                                yjyhTimeEntity.setKdTime(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                this.systemService.save(yjyhTimeEntity);


                                EmkSampleDetailEntity emkSampleDetailPactEntity;

                                //生成采购需求单、预采购合同、采购合同
                                String materialRequiredId;
                                for (int i = 0; i < 3; i++) {
                                    sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", new Object[]{t.getId(), String.valueOf(i)});
                                    if ((sampleDetailEntityList != null) && (sampleDetailEntityList.size() > 0)) {
                                        materialRequiredEntity = new EmkMaterialRequiredEntity();
                                        materialPactEntity = new EmkMaterialPactEntity();
                                        pactEntity = new EmkMaterialPactEntity();

                                        try {
                                            MyBeanUtils.copyBeanNotNull2Bean(t, materialRequiredEntity);
                                            MyBeanUtils.copyBeanNotNull2Bean(t, materialPactEntity);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        //预采购合同
                                        materialPactEntity.setId(null);
                                        materialPactEntity.setHtNum(contractEntity.getHtNum());
                                        materialPactEntity.setOrderNum(t.getOrderNo());
                                        materialPactEntity.setPartyA(user.getCurrentDepart().getDepartname());
                                        materialPactEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
                                        materialPactEntity.setPartyB(t.getGys());
                                        materialPactEntity.setPartyBId(t.getGysCode());
                                        materialPactEntity.setMaterialNo(t.getOrderNo().replace("D", "CGHT"));
                                        materialPactEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                        materialPactEntity.setDhjqDate(t.getRecevieDate());
                                        materialPactEntity.setLeaveDhjqDays(t.getLevelDays());
                                        materialPactEntity.setType(String.valueOf(i));
                                        materialPactEntity.setFlag("0");

                                        //采购合同
                                        MyBeanUtils.copyBeanNotNull2Bean(materialPactEntity, pactEntity);
                                        pactEntity.setCghtbh(materialPactEntity.getMaterialNo());
                                        pactEntity.setFlag("1");
                                        pactEntity.setZscghtbh(materialPactEntity.getMaterialNo().replace("CGHT","ZSCGHT"));
                                        pactEntity.setCgxqdh(materialRequiredEntity.getMaterialNo());

                                        /*List<EmkMaterialPactEntity> emkMaterialPactEntityList = this.systemService.findHql("from EmkMaterialPactEntity where materialNo=? and type=?", new Object[]{materialPactEntity.getMaterialNo(), String.valueOf(i)});
                                        String materialPactId = "";
                                        if ((emkMaterialPactEntityList != null) && (emkMaterialPactEntityList.size() > 0)) {
                                            EmkMaterialPactEntity t2 = (EmkMaterialPactEntity) emkMaterialPactEntityList.get(0);
                                            MyBeanUtils.copyBeanNotNull2Bean(materialPactEntity, t2);
                                            this.systemService.saveOrUpdate(t2);
                                            this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", t2.getId());
                                            materialPactId = t2.getId();
                                        } else {
                                            this.systemService.save(materialPactEntity);
                                            materialPactId = materialPactEntity.getId();
                                        }*/
                                        //生成预采购合同单
                                        this.systemService.save(materialPactEntity);

                                        //生成采购合同单
                                        this.systemService.save(pactEntity);

                                        emkSampleDetailEntity = null;
                                        for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                                            //预采购合同明细
                                            emkSampleDetailEntity = new EmkSampleDetailEntity();
                                            MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                            emkSampleDetailEntity.setId(null);
                                            emkSampleDetailEntity.setSampleId(materialPactEntity.getId());
                                            this.systemService.save(emkSampleDetailEntity);

                                            //采购合同明细
                                            emkSampleDetailPactEntity = new EmkSampleDetailEntity();
                                            MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailPactEntity);
                                            emkSampleDetailPactEntity.setId(null);
                                            emkSampleDetailPactEntity.setSampleId(pactEntity.getId());
                                            this.systemService.save(emkSampleDetailPactEntity);
                                        }
                                        materialRequiredEntity.setId(null);
                                        materialRequiredEntity.setMaterialNo(t.getOrderNo().replace("D", "CGXQ"));
                                        materialRequiredEntity.setOrderNum(t.getOrderNo());
                                        materialRequiredEntity.setHtNum(contractEntity.getHtNum());
                                        materialRequiredEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                        materialRequiredEntity.setDhjqDate(t.getRecevieDate());
                                        materialRequiredEntity.setLeaveDhjqDays(t.getLevelDays());
                                        materialRequiredEntity.setType(String.valueOf(i));

                                       /* List<EmkMaterialRequiredEntity> emkMaterialRequiredEntityList = this.systemService.findHql("from EmkMaterialRequiredEntity where materialNo=? and type=?", new Object[]{materialRequiredEntity.getMaterialNo(), String.valueOf(i)});
                                        materialRequiredId = "";
                                        if ((emkMaterialRequiredEntityList != null) && (emkMaterialRequiredEntityList.size() > 0)) {
                                            EmkMaterialRequiredEntity t2 = (EmkMaterialRequiredEntity) emkMaterialRequiredEntityList.get(0);
                                            MyBeanUtils.copyBeanNotNull2Bean(materialRequiredEntity, t2);
                                            this.systemService.saveOrUpdate(t2);
                                            this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[]{t2.getId()});
                                            materialRequiredId = t2.getId();
                                        } else {
                                            this.systemService.save(materialRequiredEntity);
                                            materialRequiredId = materialRequiredEntity.getId();
                                        }*/

                                        //生成预采购需求单
                                        this.systemService.save(materialRequiredEntity);

                                        for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                                            emkSampleDetailEntity = new EmkSampleDetailEntity();
                                            MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                            emkSampleDetailEntity.setId(null);
                                            emkSampleDetailEntity.setSampleId(materialRequiredEntity.getId());
                                            this.systemService.save(emkSampleDetailEntity);
                                        }
                                    }
                                }
                                EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",t.getWorkNo());
                                workOrderEntity.setOrderNo(t.getOrderNo());
                                workOrderEntity.setOrderType("0");
                                systemService.saveOrUpdate(workOrderEntity);
                                taskService.complete(task1.getId(), variables);
                            } else {
                                List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(t.getId()).list();

                                List<Task> taskList = taskService.createTaskQuery().taskAssignee(t.getId()).list();
                                if (taskList.size() > 0) {
                                    Task taskH = (Task)taskList.get(taskList.size() - 1);
                                    HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
                                    FlowUtil.turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
                                    Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC", new Object[] { t.getId(), historicTaskInstance.getTaskDefinitionKey() });
                                    String[] activitIdArr = activityMap.get("ids").toString().split(",");
                                    String[] taskIdArr = activityMap.get("taskids").toString().split(",");
                                    systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(), taskIdArr[1]);
                                    systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", activitIdArr[0], activitIdArr[1] );
                                }
                                t.setState("0");
                            }
                        }
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("bill", "emkProOrderPage", variables);
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
            message = "订单录入申请提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkProOrderEntity emkProOrderEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrderEntity.getId())) {
            emkProOrderEntity = emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrderEntity.getId());
            req.setAttribute("emkProOrderPage", emkProOrderEntity);
        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-work");
    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkProOrderEntity emkProOrderEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='instorageTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_pro_order t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
        emkProOrderEntity = emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrderEntity.getId());
        req.setAttribute("emkProOrderPage", emkProOrderEntity);
        return new ModelAndView("com/emk/bill/proorder/time");
    }

    @RequestMapping(params="goProcess")
    public ModelAndView goProcess(EmkProOrderEntity emkProOrderEntity, HttpServletRequest req) {
        EmkProOrderEntity t = systemService.get(EmkProOrderEntity.class, emkProOrderEntity.getId());
        List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
        if (task.size() > 0) {
            Task task1 = (Task)task.get(task.size() - 1);
            req.getSession().setAttribute("orderPorcess", task1);
            req.getSession().setAttribute("orderFinish", "0");
        }else if (t.getState().equals("4")) {
            req.getSession().setAttribute("orderFinish", "1");
        }else {
            req.getSession().setAttribute("orderFinish", "0");
            req.getSession().setAttribute("orderPorcess", null);
        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-process");
    }

    @RequestMapping(params="process")
    public ModelAndView process(EmkProOrderEntity emkProOrderEntity, HttpServletRequest req) {
        return new ModelAndView("com/emk/bill/proorder/process");
    }

    @RequestMapping(params="getCurrentProcess")
    @ResponseBody
    public AjaxJson getCurrentProcess(EmkProOrderEntity emkProOrderEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        EmkProOrderEntity t = systemService.get(EmkProOrderEntity.class, emkProOrderEntity.getId());
        List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
        if (task.size() > 0) {
            Task task1 = (Task)task.get(task.size() - 1);
            j.setMsg(task1.getName());
            request.getSession().setAttribute("orderPorcess", task1);
            request.getSession().setAttribute("orderFinish", "0");
        }else if (t.getState().equals("2")) {
            j.setMsg("完成");
            request.getSession().setAttribute("orderFinish", "1");
        }else {
            j.setMsg("订单录入申请");
            request.getSession().setAttribute("orderFinish", "0");
            request.getSession().setAttribute("orderPorcess", null);
        }
        return j;
    }

}
