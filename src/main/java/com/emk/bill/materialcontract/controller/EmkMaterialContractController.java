package com.emk.bill.materialcontract.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.materialcontract.entity.EmkMaterialContractEntity;
import com.emk.bill.materialcontract.entity.EmkMaterialContractEntity2;
import com.emk.bill.materialcontract.service.EmkMaterialContractServiceI;
import com.emk.bill.materialcontractdetail.entity.EmkMaterialContractDetailEntity;
import com.emk.storage.instorage.entity.EmkInStorageEntity;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
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
import org.activiti.engine.task.TaskQuery;
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

@Api(value = "EmkMaterialContract", description = "原料采购合同表", tags = "emkMaterialContractController")
@Controller
@RequestMapping("/emkMaterialContractController")
public class EmkMaterialContractController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMaterialContractController.class);
    @Autowired
    private EmkMaterialContractServiceI emkMaterialContractService;
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
    public AjaxJson doAdd(EmkMaterialContractEntity2 emkMaterialContract, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料采购合同表添加成功";
        try {
            emkMaterialContract.setState("0");
            this.emkMaterialContractService.save(emkMaterialContract);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkMaterialContractDetailEntity rkglMxEntity = new EmkMaterialContractDetailEntity();
                    if ((map.get("rkglMxList[" + i + "].proName") != null) && (!((String) map.get("rkglMxList[" + i + "].proName")).equals(""))) {
                        rkglMxEntity.setContractId(emkMaterialContract.getId());
                        rkglMxEntity.setProZnName((String) map.get("rkglMxList[" + i + "].proName"));
                        rkglMxEntity.setProNum((String) map.get("rkglMxList[" + i + "].proNum"));
                        rkglMxEntity.setBrand((String) map.get("rkglMxList[" + i + "].brand"));
                        rkglMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("rkglMxList[" + i + "].signTotal"))));
                        rkglMxEntity.setSignPrice(Double.valueOf(Double.parseDouble((String) map.get("rkglMxList[" + i + "].signPrice"))));
                        rkglMxEntity.setInTotal(Integer.parseInt((map.get("rkglMxList[" + i + "].inTotal") != null) && (!((String) map.get("rkglMxList[" + i + "].inTotal")).isEmpty()) ? (String) map.get("rkglMxList[" + i + "].inTotal") : "0"));
                        rkglMxEntity.setOutTotal(Integer.parseInt((map.get("rkglMxList[" + i + "].outTotal") != null) && (!((String) map.get("rkglMxList[" + i + "].outTotal")).isEmpty()) ? (String) map.get("rkglMxList[" + i + "].outTotal") : "0"));
                        rkglMxEntity.setLeavelTotal(Integer.parseInt((map.get("rkglMxList[" + i + "].leavelTotal") != null) && (!((String) map.get("rkglMxList[" + i + "].leavelTotal")).isEmpty()) ? (String) map.get("rkglMxList[" + i + "].leavelTotal") : "0"));
                        this.systemService.save(rkglMxEntity);
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
    public AjaxJson doUpdate(EmkMaterialContractEntity2 emkMaterialContract, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料采购合同表更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        EmkMaterialContractEntity t = emkMaterialContractService.get(EmkMaterialContractEntity.class, map.get("emkMaterialContractId"));
        try {
            emkMaterialContract.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterialContract, t);
            this.emkMaterialContractService.saveOrUpdate(t);
            this.systemService.executeSql("delete from emk_material_contract_detail where CONTRACT_ID=?", t.getId());

            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkMaterialContractDetailEntity rkglMxEntity = new EmkMaterialContractDetailEntity();
                    if ((map.get("rkglMxList[" + i + "].proName") != null) && (!((String) map.get("rkglMxList[" + i + "].proName")).equals(""))) {
                        rkglMxEntity.setContractId(t.getId());
                        rkglMxEntity.setProZnName((String) map.get("rkglMxList[" + i + "].proName"));
                        rkglMxEntity.setProNum((String) map.get("rkglMxList[" + i + "].proNum"));
                        rkglMxEntity.setBrand((String) map.get("rkglMxList[" + i + "].brand"));
                        rkglMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("rkglMxList[" + i + "].signTotal"))));
                        rkglMxEntity.setSignPrice(Double.valueOf(Double.parseDouble((String) map.get("rkglMxList[" + i + "].signPrice"))));
                        rkglMxEntity.setInTotal(Integer.valueOf(Integer.parseInt((map.get("rkglMxList[" + i + "].inTotal") != null) && (!((String) map.get("rkglMxList[" + i + "].inTotal")).isEmpty()) ? (String) map.get("rkglMxList[" + i + "].inTotal") : "0")));
                        rkglMxEntity.setOutTotal(Integer.valueOf(Integer.parseInt((map.get("rkglMxList[" + i + "].outTotal") != null) && (!((String) map.get("rkglMxList[" + i + "].outTotal")).isEmpty()) ? (String) map.get("rkglMxList[" + i + "].outTotal") : "0")));
                        rkglMxEntity.setLeavelTotal(Integer.valueOf(Integer.parseInt((map.get("rkglMxList[" + i + "].leavelTotal") != null) && (!((String) map.get("rkglMxList[" + i + "].leavelTotal")).isEmpty()) ? (String) map.get("rkglMxList[" + i + "].leavelTotal") : "0")));
                        this.systemService.save(rkglMxEntity);
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
        }
        req.getSession().setAttribute("orderFinish", "");

        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContract-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkMaterialContractEntity emkMaterialContract, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialContract.getId())) {
            emkMaterialContract = emkMaterialContractService.getEntity(EmkMaterialContractEntity.class, emkMaterialContract.getId());
            req.setAttribute("emkMaterialContractPage", emkMaterialContract);
        }
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContract-update2");
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
    public AjaxJson doSubmit(EmkMaterialContractEntity2 emkMaterialContractEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料布料采购单提交成功";
        try {
            int flag = 0;

            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
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
                    t.setState("1");
                    variables.put("inputUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task) task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("orderTask")) {
                            this.taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkMaterialContractEntity.getLeadAdvice());
                            if (emkMaterialContractEntity.getIsPass().equals("0")) {
                                t.setYhtUserId(map.get("userName").toString());
                                t.setYhtUserName(map.get("realName").toString());

                                variables.put("isPass", emkMaterialContractEntity.getIsPass());
                                this.taskService.complete(task1.getId(), variables);
                            } else {
                                List<HistoricTaskInstance> hisTasks = ((HistoricTaskInstanceQuery) this.historyService.createHistoricTaskInstanceQuery().taskAssignee(emkMaterialContractEntity.getId())).list();

                                List<Task> taskList = taskService.createTaskQuery().taskAssignee(emkMaterialContractEntity.getId()).list();
                                if (taskList.size() > 0) {
                                    Task taskH = (Task) taskList.get(taskList.size() - 1);
                                    HistoricTaskInstance historicTaskInstance = (HistoricTaskInstance) hisTasks.get(hisTasks.size() - 2);
                                    FlowUtil.turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
                                    Map activityMap = this.systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC", new Object[]{t.getId(), historicTaskInstance.getTaskDefinitionKey()});
                                    String[] activitIdArr = activityMap.get("ids").toString().split(",");
                                    String[] taskIdArr = activityMap.get("taskids").toString().split(",");
                                    this.systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?", new Object[]{t.getId(), taskIdArr[1]});
                                    this.systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", new Object[]{activitIdArr[0], activitIdArr[1]});
                                }
                                t.setState("0");
                            }
                        }else if(task1.getTaskDefinitionKey().equals("ycghtTask")) {
                            t.setHtUserId(map.get("userName").toString());
                            t.setHtUserName(map.get("realName").toString());
                            t.setYhtAdvice(t.getLeadAdvice());
                            this.taskService.complete(task1.getId(), variables);
                        }else if(task1.getTaskDefinitionKey().equals("htTask")) {
                            t.setRkUserId(t.getCreateBy());
                            t.setRkUserName(t.getCreateName());
                            t.setHtAdvice(t.getLeadAdvice());
                            this.taskService.complete(task1.getId(), variables);
                        }else if(task1.getTaskDefinitionKey().equals("rkTask")) {
                            t.setCkUserId(t.getCreateBy());
                            t.setCkUserName(t.getCreateName());
                            t.setRkAdvice(t.getLeadAdvice());
                            this.taskService.complete(task1.getId(), variables);
                        }else if(task1.getTaskDefinitionKey().equals("ckTask")) {
                            t.setState("2");
                            this.taskService.complete(task1.getId(), variables);
                        }
                    } else {
                        ProcessInstance pi = this.processEngine.getRuntimeService().startProcessInstanceByKey("order", "emkOrder", variables);

                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = (Task) task.get(task.size() - 1);
                        this.taskService.complete(task1.getId(), variables);
                    }
                    this.systemService.saveOrUpdate(t);
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
        String sql = "";
        String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='orderTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " WHEN t1.TASK_DEF_KEY_='ycghtTask' THEN t2.yht_user_name \n" +
                " WHEN t1.TASK_DEF_KEY_='htTask' THEN t2.ht_user_name \n" +
                " WHEN t1.TASK_DEF_KEY_='rkTask' THEN t2.rk_user_name \n" +
                " WHEN t1.TASK_DEF_KEY_='ckTask' THEN t2.ck_user_name \n" +

                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_material_contract t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

        countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='" + map.get("id") + "' ";

        sql += " order by t1.START_TIME_ desc";
        if (dataGrid.getPage() == 1) {
            sql = sql + " limit 0, " + dataGrid.getRows();
        } else {
            sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
        }
        this.systemService.listAllByJdbc(dataGrid, sql, countsql);
        req.setAttribute("taskList", dataGrid.getResults());
        if (dataGrid.getResults().size() > 0) {
            req.setAttribute("stepProcess", Integer.valueOf(dataGrid.getResults().size() - 1));
        } else {
            req.setAttribute("stepProcess", Integer.valueOf(0));
        }
        emkMaterialContractEntity = emkMaterialContractService.getEntity(EmkMaterialContractEntity.class, emkMaterialContractEntity.getId());
        req.setAttribute("emkMaterialContract", emkMaterialContractEntity);
        return new ModelAndView("com/emk/bill/materialcontract/time");
    }

    @RequestMapping(params = "goProcess")
    public ModelAndView goProcess(EmkMaterialContractEntity emkMaterialContractEntity, HttpServletRequest req) {
        EmkMaterialContractEntity t = systemService.get(EmkMaterialContractEntity.class, emkMaterialContractEntity.getId());
        List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
        if (task.size() > 0) {
            Task task1 = (Task) task.get(task.size() - 1);
            req.getSession().setAttribute("orderPorcess", task1);
            req.getSession().setAttribute("orderFinish", "0");
        } else if (t.getState().equals("4")) {
            req.getSession().setAttribute("orderFinish", "1");
        } else {
            req.getSession().setAttribute("orderFinish", "0");
            req.getSession().setAttribute("orderPorcess", null);
        }
        return new ModelAndView("com/emk/bill/materialcontract/emkMaterialContract-process");
    }

    @RequestMapping(params = "process")
    public ModelAndView process(EmkInStorageEntity emkInStorage, HttpServletRequest req) {
        return new ModelAndView("com/emk/bill/materialcontract/process");
    }

    @RequestMapping(params = "getCurrentProcess")
    @ResponseBody
    public AjaxJson getCurrentProcess(EmkMaterialContractEntity emkMaterialContractEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        EmkMaterialContractEntity t = systemService.get(EmkMaterialContractEntity.class, emkMaterialContractEntity.getId());
        List<Task> task = taskService.createTaskQuery().taskAssignee(t.getId()).list();
        if (task.size() > 0) {
            Task task1 = (Task) task.get(task.size() - 1);
            j.setMsg(task1.getName());
            request.getSession().setAttribute("orderPorcess", task1);
            request.getSession().setAttribute("orderFinish", "0");
        } else if (t.getState().equals("2")) {
            j.setMsg("完成");
            request.getSession().setAttribute("orderFinish", "1");
        } else {
            j.setMsg("采购单申请");
            request.getSession().setAttribute("orderFinish", "0");
            request.getSession().setAttribute("orderPorcess", null);
        }
        return j;
    }


}
