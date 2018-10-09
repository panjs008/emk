package com.emk.bill.proorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.bill.proorder.service.EmkProOrderServiceI;
import com.emk.bill.proorderdetail.entity.EmkProOrderDetailEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.instorage.entity.EmkInStorageEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.storage.entity.EmkStorageEntity;
import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import com.emk.util.ParameterUtil;
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

@Api(value = "EmkProOrder", description = "生产订单", tags = {"emkProOrderController"})
@Controller
@RequestMapping({"/emkProOrderController"})
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
    ManagementService managementService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/proorder/emkProOrderList");
    }

    @RequestMapping(params = {"orderMxList"})
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkProOrderDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkProOrderDetailEntity where proOrderId=?", new Object[]{map.get("proOrderId")});
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/bill/proorder/orderMxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProOrder, request.getParameterMap());


        cq.add();
        this.emkProOrderService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
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

    @RequestMapping(params = {"doBatchDel"})
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
                this.systemService.executeSql("delete from emk_pro_order_detail where pro_order_id=?", emkProOrder.getId());

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

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProOrderEntity emkProOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单添加成功";
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_pro_order where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
            emkProOrder.setOrderNo("D" + DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))}));
            emkProOrder.setState("0");
            this.emkProOrderService.save(emkProOrder);
            Map price = this.systemService.findOneForJdbc("select * from emk_price where cus_num =? and sample_no=? order by kd_date desc limit 0,1", new Object[]{emkProOrder.getCusNum(), emkProOrder.getSampleNo()});
            List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=?", new Object[]{price.get("id")});
            for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                sampleDetailEntity.setSampleId(price.get("id").toString());
                sampleDetailEntity.setId(null);
                this.systemService.save(sampleDetailEntity);
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

   /* @RequestMapping(params = {"doSubmit"})
    @ResponseBody
    public AjaxJson doSubmit(EmkProOrderEntity emkProOrder, HttpServletRequest request, String ids) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单提交成功";
        EmkProOrderEntity t = (EmkProOrderEntity) this.emkProOrderService.get(EmkProOrderEntity.class, emkProOrder.getId());
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");

            MyBeanUtils.copyBeanNotNull2Bean(emkProOrder, t);
            this.emkProOrderService.saveOrUpdate(t);
            EmkMaterialRequiredEntity materialRequiredEntity = null;
            EmkMaterialPactEntity materialPactEntity = null;

            EmkContractEntity contractEntity = new EmkContractEntity();
            MyBeanUtils.copyBeanNotNull2Bean(t, contractEntity);


            contractEntity.setId(null);

            contractEntity.setPartyA(user.getCurrentDepart().getDepartname());
            contractEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
            contractEntity.setPartyB(t.getGys());
            contractEntity.setPartyBId(t.getGysCode());
            contractEntity.setHtNum(t.getOrderNo().replace("D", "HT"));

            EmkContractEntity emkContractEntity = (EmkContractEntity) this.systemService.findUniqueByProperty(EmkContractEntity.class, "htNum", contractEntity.getHtNum());
            String contractId = "";
            if (emkContractEntity != null) {
                MyBeanUtils.copyBeanNotNull2Bean(contractEntity, emkContractEntity);
                this.systemService.saveOrUpdate(emkContractEntity);
                this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{emkContractEntity.getId()});
                contractId = emkContractEntity.getId();
            } else {
                this.systemService.save(contractEntity);
                contractId = contractEntity.getId();
            }
            EmkEnquiryDetailEntity emkEnquiryDetailEntity = null;
            List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{emkProOrder.getId()});
            for (EmkEnquiryDetailEntity enquiryDetailEntity : emkEnquiryDetailEntityList) {
                emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                MyBeanUtils.copyBeanNotNull2Bean(enquiryDetailEntity, emkEnquiryDetailEntity);
                emkEnquiryDetailEntity.setId(null);
                emkEnquiryDetailEntity.setEnquiryId(contractId);
                this.systemService.save(emkEnquiryDetailEntity);
            }
            EmkSampleDetailEntity emkSampleDetailEntity;
            String materialRequiredId;
            for (int i = 0; i < 3; i++) {
                List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", new Object[]{emkProOrder.getId(), String.valueOf(i)});
                if ((sampleDetailEntityList != null) && (sampleDetailEntityList.size() > 0)) {
                    materialRequiredEntity = new EmkMaterialRequiredEntity();
                    materialPactEntity = new EmkMaterialPactEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(t, materialRequiredEntity);
                    MyBeanUtils.copyBeanNotNull2Bean(t, materialPactEntity);


                    materialPactEntity.setId(null);
                    materialPactEntity.setOrderNum(t.getOrderNo());
                    materialPactEntity.setPartyA(user.getCurrentDepart().getDepartname());
                    materialPactEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
                    materialPactEntity.setPartyB(t.getGys());
                    materialPactEntity.setPartyBId(t.getGysCode());
                    materialPactEntity.setMaterialNo(t.getOrderNo().replace("D", "YHT"));
                    materialPactEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                    materialPactEntity.setDhjqDate(t.getRecevieDate());
                    materialPactEntity.setLeaveDhjqDays(t.getLevelDays());
                    materialPactEntity.setType(String.valueOf(i));
                    List<EmkMaterialPactEntity> emkMaterialPactEntityList = this.systemService.findHql("from EmkMaterialPactEntity where materialNo=? and type=?", new Object[]{materialPactEntity.getMaterialNo(), String.valueOf(i)});
                    String materialPactId = "";
                    if ((emkMaterialPactEntityList != null) && (emkMaterialPactEntityList.size() > 0)) {
                        EmkMaterialPactEntity t2 = (EmkMaterialPactEntity) emkMaterialPactEntityList.get(0);
                        MyBeanUtils.copyBeanNotNull2Bean(materialPactEntity, t2);
                        this.systemService.saveOrUpdate(t2);
                        this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[]{t2.getId()});
                        materialPactId = t2.getId();
                    } else {
                        this.systemService.save(materialPactEntity);
                        materialPactId = materialPactEntity.getId();
                    }
                    emkSampleDetailEntity = null;
                    for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                        emkSampleDetailEntity = new EmkSampleDetailEntity();
                        MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                        emkSampleDetailEntity.setId(null);
                        emkSampleDetailEntity.setSampleId(materialPactId);
                        this.systemService.save(emkSampleDetailEntity);
                    }
                    materialRequiredEntity.setId(null);
                    materialRequiredEntity.setMaterialNo(t.getOrderNo().replace("D", "CG"));
                    materialRequiredEntity.setOrderNum(t.getOrderNo());
                    materialRequiredEntity.setHtNum(materialPactEntity.getMaterialNo());
                    materialRequiredEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                    materialRequiredEntity.setDhjqDate(t.getRecevieDate());
                    materialRequiredEntity.setLeaveDhjqDays(t.getLevelDays());
                    materialRequiredEntity.setType(String.valueOf(i));

                    List<EmkMaterialRequiredEntity> emkMaterialRequiredEntityList = this.systemService.findHql("from EmkMaterialRequiredEntity where materialNo=? and type=?", new Object[]{materialRequiredEntity.getMaterialNo(), String.valueOf(i)});
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
                    }
                    for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                        emkSampleDetailEntity = new EmkSampleDetailEntity();
                        MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                        emkSampleDetailEntity.setId(null);
                        emkSampleDetailEntity.setSampleId(materialRequiredId);
                        this.systemService.save(emkSampleDetailEntity);
                    }
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
    }*/

    @RequestMapping(params = {"doUpdate"})
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
            Map price = this.systemService.findOneForJdbc("select id from emk_price where cus_num =? and sample_no=? order by kd_date desc limit 0,1", new Object[]{emkProOrder.getCusNum(), emkProOrder.getSampleNo()});
            EmkSampleDetailEntity emkSampleDetailEntity;
            if (price != null) {
                List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=?", new Object[]{price.get("id")});
                emkSampleDetailEntity = null;
                for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                    emkSampleDetailEntity = new EmkSampleDetailEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                    emkSampleDetailEntity.setId(null);
                    emkSampleDetailEntity.setSampleId(emkProOrder.getId());
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

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = (EmkProOrderEntity) this.emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());
            req.setAttribute("emkProOrderPage", emkProOrder);
        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-add");
    }

    @RequestMapping(params = {"goUpdate"})
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

    @RequestMapping(params = {"goUpdate2"})
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

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProOrderController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
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

    @RequestMapping(params = {"exportXlsByT"})
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

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取生产订单信息", notes = "根据ID获取生产订单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProOrderEntity task = (EmkProOrderEntity) this.emkProOrderService.get(EmkProOrderEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取生产订单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
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

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
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

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
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
                                if ((map.get("realName") == null) || (map.get("realName").toString().equals(""))) {
                                    j.setSuccess(false);
                                    j.setMsg("请选择下一处理人");
                                    return j;
                                }

                                EmkMaterialRequiredEntity materialRequiredEntity = null;
                                EmkMaterialPactEntity materialPactEntity = null;

                                EmkContractEntity contractEntity = new EmkContractEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t, contractEntity);


                                contractEntity.setId(null);

                                contractEntity.setPartyA(user.getCurrentDepart().getDepartname());
                                contractEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
                                contractEntity.setPartyB(t.getGys());
                                contractEntity.setPartyBId(t.getGysCode());
                                contractEntity.setHtNum(t.getOrderNo().replace("D", "HT"));

                                EmkContractEntity emkContractEntity = (EmkContractEntity) this.systemService.findUniqueByProperty(EmkContractEntity.class, "htNum", contractEntity.getHtNum());
                                String contractId = "";
                                if (emkContractEntity != null) {
                                    MyBeanUtils.copyBeanNotNull2Bean(contractEntity, emkContractEntity);
                                    this.systemService.saveOrUpdate(emkContractEntity);
                                    this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{emkContractEntity.getId()});
                                    contractId = emkContractEntity.getId();
                                } else {
                                    this.systemService.save(contractEntity);
                                    contractId = contractEntity.getId();
                                }
                                EmkEnquiryDetailEntity emkEnquiryDetailEntity = null;
                                List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{t.getId()});
                                for (EmkEnquiryDetailEntity enquiryDetailEntity : emkEnquiryDetailEntityList) {
                                    emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(enquiryDetailEntity, emkEnquiryDetailEntity);
                                    emkEnquiryDetailEntity.setId(null);
                                    emkEnquiryDetailEntity.setEnquiryId(contractId);
                                    this.systemService.save(emkEnquiryDetailEntity);
                                }
                                EmkSampleDetailEntity emkSampleDetailEntity;
                                String materialRequiredId;
                                for (int i = 0; i < 3; i++) {
                                    List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", new Object[]{t.getId(), String.valueOf(i)});
                                    if ((sampleDetailEntityList != null) && (sampleDetailEntityList.size() > 0)) {
                                        materialRequiredEntity = new EmkMaterialRequiredEntity();
                                        materialPactEntity = new EmkMaterialPactEntity();
                                        try {
                                            MyBeanUtils.copyBeanNotNull2Bean(t, materialRequiredEntity);
                                            MyBeanUtils.copyBeanNotNull2Bean(t, materialPactEntity);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        materialPactEntity.setId(null);
                                        materialPactEntity.setOrderNum(t.getOrderNo());
                                        materialPactEntity.setPartyA(user.getCurrentDepart().getDepartname());
                                        materialPactEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
                                        materialPactEntity.setPartyB(t.getGys());
                                        materialPactEntity.setPartyBId(t.getGysCode());
                                        materialPactEntity.setMaterialNo(t.getOrderNo().replace("D", "YHT"));
                                        materialPactEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                        materialPactEntity.setDhjqDate(t.getRecevieDate());
                                        materialPactEntity.setLeaveDhjqDays(t.getLevelDays());
                                        materialPactEntity.setType(String.valueOf(i));
                                        List<EmkMaterialPactEntity> emkMaterialPactEntityList = this.systemService.findHql("from EmkMaterialPactEntity where materialNo=? and type=?", new Object[]{materialPactEntity.getMaterialNo(), String.valueOf(i)});
                                        String materialPactId = "";
                                        if ((emkMaterialPactEntityList != null) && (emkMaterialPactEntityList.size() > 0)) {
                                            EmkMaterialPactEntity t2 = (EmkMaterialPactEntity) emkMaterialPactEntityList.get(0);
                                            MyBeanUtils.copyBeanNotNull2Bean(materialPactEntity, t2);
                                            this.systemService.saveOrUpdate(t2);
                                            this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[]{t2.getId()});
                                            materialPactId = t2.getId();
                                        } else {
                                            this.systemService.save(materialPactEntity);
                                            materialPactId = materialPactEntity.getId();
                                        }
                                        emkSampleDetailEntity = null;
                                        for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                                            emkSampleDetailEntity = new EmkSampleDetailEntity();
                                            MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                            emkSampleDetailEntity.setId(null);
                                            emkSampleDetailEntity.setSampleId(materialPactId);
                                            this.systemService.save(emkSampleDetailEntity);
                                        }
                                        materialRequiredEntity.setId(null);
                                        materialRequiredEntity.setMaterialNo(t.getOrderNo().replace("D", "CG"));
                                        materialRequiredEntity.setOrderNum(t.getOrderNo());
                                        materialRequiredEntity.setHtNum(materialPactEntity.getMaterialNo());
                                        materialRequiredEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                        materialRequiredEntity.setDhjqDate(t.getRecevieDate());
                                        materialRequiredEntity.setLeaveDhjqDays(t.getLevelDays());
                                        materialRequiredEntity.setType(String.valueOf(i));

                                        List<EmkMaterialRequiredEntity> emkMaterialRequiredEntityList = this.systemService.findHql("from EmkMaterialRequiredEntity where materialNo=? and type=?", new Object[]{materialRequiredEntity.getMaterialNo(), String.valueOf(i)});
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
                                        }
                                        for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                                            emkSampleDetailEntity = new EmkSampleDetailEntity();
                                            MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                            emkSampleDetailEntity.setId(null);
                                            emkSampleDetailEntity.setSampleId(materialRequiredId);
                                            this.systemService.save(emkSampleDetailEntity);
                                        }
                                    }
                                }
                                taskService.complete(task1.getId(), variables);
                            } else {
                                List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(t.getId()).list();

                                List<Task> taskList = taskService.createTaskQuery().taskAssignee(t.getId()).list();
                                if (taskList.size() > 0) {
                                    Task taskH = (Task)taskList.get(taskList.size() - 1);
                                    HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
                                    turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
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

    @RequestMapping(params="showProcess")
    public void showProcess(HttpServletRequest req, HttpServletResponse response) throws Exception {
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        List<Task> task = taskService.createTaskQuery().taskAssignee(map.get("id").toString()).list();
        String processInstanceId = "";
        EmkProOrderEntity t = emkProOrderService.get(EmkProOrderEntity.class, map.get("id").toString());
        if (task.size() > 0) {
            Task task1 = (Task)task.get(task.size() - 1);
            processInstanceId = task1.getProcessInstanceId();
        }else if (t.getState().equals("2")) {
            Map hisPorcess = systemService.findOneForJdbc("SELECT PROC_INST_ID_ processid FROM act_hi_taskinst WHERE ASSIGNEE_=? LIMIT 0,1 ", map.get("id").toString());
            processInstanceId = String.valueOf(hisPorcess.get("processid"));
        }
        if (processInstanceId != null && !processInstanceId.isEmpty()) {
            HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

            BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            processEngineConfiguration = processEngine.getProcessEngineConfiguration();
            Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl)processEngineConfiguration);

            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
            ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

            List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

            List<String> highLightedActivitis = new ArrayList();

            List<String> highLightedFlows = ParameterUtil.getHighLightedFlows(definitionEntity, highLightedActivitList);
            for (HistoricActivityInstance tempActivity : highLightedActivitList) {
                String activityId = tempActivity.getActivityId();
                highLightedActivitis.add(activityId);
            }
            InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, "宋体", "宋体", null, 1.0D);

            byte[] b = new byte[1024];
            int len;
            while ((len = imageStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        }
    }
}
