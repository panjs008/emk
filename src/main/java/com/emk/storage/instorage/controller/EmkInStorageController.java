package com.emk.storage.instorage.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.instorage.entity.EmkInStorageEntity;
import com.emk.storage.instorage.service.EmkInStorageServiceI;
import com.emk.storage.instoragedetail.entity.EmkInStorageDetailEntity;
import com.emk.util.ParameterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
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

@Api(value = "EmkInStorage", description = "采购订单", tags = {"emkInStorageController"})
@Controller
@RequestMapping({"/emkInStorageController"})
public class EmkInStorageController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkInStorageController.class);
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
    @Autowired
    private EmkInStorageServiceI emkInStorageService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/instorage/emkInStorageList");
    }

    @RequestMapping(params = {"emkInStorageDetailList"})
    public ModelAndView rkglMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("inStorageId") != null) && (!map.get("inStorageId").equals(""))) {
            List<EmkInStorageDetailEntity> rkglMxEntities = this.systemService.findHql("from EmkInStorageDetailEntity where inStorageId=?", new Object[]{map.get("inStorageId")});
            request.setAttribute("rkglMxList", rkglMxEntities);
        }
        return new ModelAndView("com/emk/storage/instorage/emkInStorageDetailList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkInStorageEntity emkInStorage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkInStorageEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkInStorage, request.getParameterMap());


        cq.add();
        this.emkInStorageService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkInStorageEntity emkInStorage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkInStorage = (EmkInStorageEntity) this.systemService.getEntity(EmkInStorageEntity.class, emkInStorage.getId());
        message = "采购订单删除成功";
        try {
            if (!emkInStorage.getState().equals("0")) {
                message = "订单已经提交处理，无法删除";
                j.setMsg(message);
                j.setSuccess(false);
                return j;
            }
            this.emkInStorageService.delete(emkInStorage);
            this.systemService.executeSql("delete from emk_in_storage_detail where in_storage_id=?", new Object[]{emkInStorage.getId()});
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购订单删除失败";
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
        message = "采购订单删除成功";
        try {
            int flag = 0;
            for (String id : ids.split(",")) {
                EmkInStorageEntity inStorageEntity = (EmkInStorageEntity) this.systemService.getEntity(EmkInStorageEntity.class, id);
                if (!inStorageEntity.getState().equals("0")) {
                    message = "存在已提交的订单，请重新选择在提交订单！";
                    j.setSuccess(false);
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                for (String id : ids.split(",")) {
                    EmkInStorageEntity emkInStorage = (EmkInStorageEntity) this.systemService.getEntity(EmkInStorageEntity.class, id);


                    this.systemService.executeSql("delete from emk_in_storage_detail where in_storage_id=?", new Object[]{emkInStorage.getId()});
                    this.emkInStorageService.delete(emkInStorage);
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购订单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkInStorageEntity emkInStorage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购订单添加成功";
        try {
            emkInStorage.setState("0");
            this.emkInStorageService.save(emkInStorage);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkInStorageDetailEntity rkglMxEntity = new EmkInStorageDetailEntity();
                    if ((map.get("rkglMxList[" + i + "].proName") != null) && (!((String) map.get("rkglMxList[" + i + "].proName")).equals(""))) {
                        rkglMxEntity.setInStorageId(emkInStorage.getId());
                        rkglMxEntity.setSampleName((String) map.get("rkglMxList[" + i + "].sampleName"));
                        rkglMxEntity.setSampleNo((String) map.get("rkglMxList[" + i + "].sampleNo"));
                        rkglMxEntity.setProName((String) map.get("rkglMxList[" + i + "].proName"));
                        rkglMxEntity.setProId((String) map.get("rkglMxList[" + i + "].proId"));
                        rkglMxEntity.setProNum((String) map.get("rkglMxList[" + i + "].proNum"));
                        rkglMxEntity.setBrand((String) map.get("rkglMxList[" + i + "].brand"));
                        rkglMxEntity.setSignTotal((String) map.get("rkglMxList[" + i + "].signTotal"));
                        rkglMxEntity.setSignUnit((String) map.get("rkglMxList[" + i + "].signUnit"));
                        rkglMxEntity.setSignPrice((String) map.get("rkglMxList[" + i + "].signPrice"));
                        rkglMxEntity.setRemark((String) map.get("rkglMxList[" + i + "].remark"));

                        this.systemService.save(rkglMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
            j.setMsg("采购订单添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购订单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkInStorageEntity emkInStorage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购订单更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        emkInStorage.setId((String) map.get("inStorageId"));
        EmkInStorageEntity t = (EmkInStorageEntity) this.emkInStorageService.get(EmkInStorageEntity.class, emkInStorage.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkInStorage, t);
            this.emkInStorageService.saveOrUpdate(t);
            this.systemService.executeSql("delete from emk_in_storage_detail where in_storage_id=?", new Object[]{t.getId()});
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkInStorageDetailEntity rkglMxEntity = new EmkInStorageDetailEntity();
                    if ((map.get("rkglMxList[" + i + "].proName") != null) && (!((String) map.get("rkglMxList[" + i + "].proName")).equals(""))) {
                        rkglMxEntity.setInStorageId(emkInStorage.getId());
                        rkglMxEntity.setSampleName((String) map.get("rkglMxList[" + i + "].sampleName"));
                        rkglMxEntity.setSampleNo((String) map.get("rkglMxList[" + i + "].sampleNo"));
                        rkglMxEntity.setProName((String) map.get("rkglMxList[" + i + "].proName"));
                        rkglMxEntity.setProId((String) map.get("rkglMxList[" + i + "].proId"));
                        rkglMxEntity.setProNum((String) map.get("rkglMxList[" + i + "].proNum"));
                        rkglMxEntity.setBrand((String) map.get("rkglMxList[" + i + "].brand"));
                        rkglMxEntity.setSignTotal((String) map.get("rkglMxList[" + i + "].signTotal"));
                        rkglMxEntity.setSignUnit((String) map.get("rkglMxList[" + i + "].signUnit"));
                        rkglMxEntity.setSignPrice((String) map.get("rkglMxList[" + i + "].signPrice"));
                        rkglMxEntity.setRemark((String) map.get("rkglMxList[" + i + "].remark"));

                        this.systemService.save(rkglMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购订单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doSubmit"})
    @ResponseBody
    public AjaxJson doSubmit(EmkInStorageEntity emkInStorage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购订单提交成功";
        try {
            int flag = 0;

            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkInStorage.getId() == null) || (emkInStorage.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkInStorageEntity inStorageEntity = (EmkInStorageEntity) this.systemService.getEntity(EmkInStorageEntity.class, id);
                    if (!inStorageEntity.getState().equals("0")) {
                        message = "存在已提交的订单，请重新选择在提交订单！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            } else {
                map.put("ids", emkInStorage.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkInStorageEntity t = (EmkInStorageEntity) this.emkInStorageService.get(EmkInStorageEntity.class, id);
                    t.setState("1");
                    variables.put("inputUser", t.getId());

                    List<Task> task = ((TaskQuery) this.taskService.createTaskQuery().taskAssignee(id)).list();
                    if (task.size() > 0) {
                        Task task1 = (Task) task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("orderTask")) {
                            this.taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkInStorage.getLeadAdvice());
                            if (emkInStorage.getIsPass().equals("0")) {
                                if ((map.get("realName") == null) || (map.get("realName").toString().equals(""))) {
                                    j.setSuccess(false);
                                    j.setMsg("请选择下一处理人");
                                    return j;
                                }
                                t.setFinancer(map.get("realName").toString());
                                t.setFinanceUserId(map.get("userName").toString());
                                variables.put("isPass", emkInStorage.getIsPass());
                                this.taskService.complete(task1.getId(), variables);
                            } else {
                                List<HistoricTaskInstance> hisTasks = ((HistoricTaskInstanceQuery) this.historyService.createHistoricTaskInstanceQuery().taskAssignee(emkInStorage.getId())).list();

                                List<Task> taskList = ((TaskQuery) this.taskService.createTaskQuery().taskAssignee(emkInStorage.getId())).list();
                                if (taskList.size() > 0) {
                                    Task taskH = (Task) taskList.get(taskList.size() - 1);
                                    HistoricTaskInstance historicTaskInstance = (HistoricTaskInstance) hisTasks.get(hisTasks.size() - 2);
                                    turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
                                    Map activityMap = this.systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC", new Object[]{t.getId(), historicTaskInstance.getTaskDefinitionKey()});
                                    String[] activitIdArr = activityMap.get("ids").toString().split(",");
                                    String[] taskIdArr = activityMap.get("taskids").toString().split(",");
                                    this.systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?", new Object[]{t.getId(), taskIdArr[1]});
                                    this.systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", new Object[]{activitIdArr[0], activitIdArr[1]});
                                }
                                t.setState("0");
                            }
                        } else if (task1.getTaskDefinitionKey().equals("cwTask")) {
                            t.setState("2");
                            t.setFinanceAdvice(emkInStorage.getFinanceAdvice());
                            this.taskService.complete(task1.getId(), variables);
                        }
                    } else {
                        ProcessInstance pi = this.processEngine.getRuntimeService().startProcessInstanceByKey("order", "emkOrder", variables);

                        task = ((TaskQuery) this.taskService.createTaskQuery().taskAssignee(id)).list();
                        Task task1 = (Task) task.get(task.size() - 1);
                        this.taskService.complete(task1.getId(), variables);
                    }
                    this.systemService.saveOrUpdate(t);
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购订单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    private void turnTransition(String taskId, String activityId, Map<String, Object> variables)
            throws Exception {
        ActivityImpl currActivity = findActivitiImpl(taskId, null);

        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        TransitionImpl newTransition = currActivity.createOutgoingTransition();

        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);

        newTransition.setDestination(pointActivity);

        this.taskService.complete(taskId, variables);

        pointActivity.getIncomingTransitions().remove(newTransition);

        restoreTransition(currActivity, oriPvmTransitionList);
    }

    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        List<PvmTransition> oriPvmTransitionList = new ArrayList();

        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    private void restoreTransition(ActivityImpl activityImpl, List<PvmTransition> oriPvmTransitionList) {
        List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();

        pvmTransitionList.clear();
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }

    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(String taskId)
            throws Exception {
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) this.repositoryService).getDeployedProcessDefinition(findTaskById(taskId).getProcessDefinitionId());
        if (processDefinition == null) {
            throw new Exception("流程定义未找到!");
        }
        return processDefinition;
    }

    private ActivityImpl findActivitiImpl(String taskId, String activityId)
            throws Exception {
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);
        if ((activityId == null) || (activityId.isEmpty())) {
            activityId = findTaskById(taskId).getTaskDefinitionKey();
        }
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl.getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }
        ActivityImpl activityImpl = processDefinition.findActivity(activityId);


        return activityImpl;
    }

    private TaskEntity findTaskById(String taskId)
            throws Exception {
        TaskEntity task = (TaskEntity) ((TaskQuery) this.taskService.createTaskQuery().taskId(taskId)).singleResult();
        if (task == null) {
            throw new Exception("任务实例未找到!");
        }
        return task;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkInStorageEntity emkInStorage, HttpServletRequest req) {
        TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");
        Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_in_storage where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
        req.setAttribute("roNo", "CG" + DateUtils.format(new Date(), "yyyy") + String.format("%06d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))}));
        req.setAttribute("createDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkInStorage.getId())) {
            emkInStorage = (EmkInStorageEntity) this.emkInStorageService.getEntity(EmkInStorageEntity.class, emkInStorage.getId());
            req.setAttribute("emkInStoragePage", emkInStorage);
        }
        req.getSession().setAttribute("orderFinish", "");
        return new ModelAndView("com/emk/storage/instorage/emkInStorage-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkInStorageEntity emkInStorage, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkInStorage.getId())) {
            emkInStorage = (EmkInStorageEntity) this.emkInStorageService.getEntity(EmkInStorageEntity.class, emkInStorage.getId());
            req.setAttribute("emkInStoragePage", emkInStorage);
        }
        req.getSession().setAttribute("orderFinish", "");

        return new ModelAndView("com/emk/storage/instorage/emkInStorage-update");
    }

    @RequestMapping(params = {"goUpdate2"})
    public ModelAndView goUpdate2(EmkInStorageEntity emkInStorage, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkInStorage.getId())) {
            emkInStorage = (EmkInStorageEntity) this.emkInStorageService.getEntity(EmkInStorageEntity.class, emkInStorage.getId());
            req.setAttribute("emkInStoragePage", emkInStorage);
        }
        return new ModelAndView("com/emk/storage/instorage/emkInStorage-update2");
    }

    @RequestMapping(params = {"goWork"})
    public ModelAndView goWork(EmkInStorageEntity emkInStorage, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkInStorage.getId())) {
            emkInStorage = (EmkInStorageEntity) this.emkInStorageService.getEntity(EmkInStorageEntity.class, emkInStorage.getId());
            req.setAttribute("emkInStoragePage", emkInStorage);
        }
        return new ModelAndView("com/emk/storage/instorage/emkInStorage-work");
    }

    @RequestMapping(params = {"goTime"})
    public ModelAndView goTime(EmkInStorageEntity emkInStorage, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";
        String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE\nWHEN t1.TASK_DEF_KEY_='orderTask' THEN t2.create_name\nWHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader\nWHEN t1.TASK_DEF_KEY_='cwTask' THEN t2.financer\nELSE ''\nEND workname FROM act_hi_taskinst t1 \nLEFT JOIN emk_in_storage t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";


        countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='" + map.get("id") + "' ";
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
        emkInStorage = (EmkInStorageEntity) this.emkInStorageService.getEntity(EmkInStorageEntity.class, emkInStorage.getId());
        req.setAttribute("emkInStorage", emkInStorage);
        return new ModelAndView("com/emk/storage/instorage/time");
    }

    @RequestMapping(params = {"goProcess"})
    public ModelAndView goProcess(EmkInStorageEntity emkInStorage, HttpServletRequest req) {
        EmkInStorageEntity t = (EmkInStorageEntity) this.systemService.get(EmkInStorageEntity.class, emkInStorage.getId());
        List<Task> task = ((TaskQuery) this.taskService.createTaskQuery().taskAssignee(t.getId())).list();
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
        return new ModelAndView("com/emk/storage/instorage/emkInStorage-process");
    }

    @RequestMapping(params = {"process"})
    public ModelAndView process(EmkInStorageEntity emkInStorage, HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/instorage/process");
    }

    @RequestMapping(params = {"getCurrentProcess"})
    @ResponseBody
    public AjaxJson getCurrentProcess(EmkInStorageEntity emkInStorage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        EmkInStorageEntity t = (EmkInStorageEntity) this.systemService.get(EmkInStorageEntity.class, emkInStorage.getId());
        List<Task> task = ((TaskQuery) this.taskService.createTaskQuery().taskAssignee(t.getId())).list();
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

    @RequestMapping(params = {"showProcess"})
    public void showProcess(HttpServletRequest req, HttpServletResponse response)
            throws Exception {
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        List<Task> task = ((TaskQuery) this.taskService.createTaskQuery().taskAssignee(map.get("id").toString())).list();
        String processInstanceId = "";
        EmkInStorageEntity t = (EmkInStorageEntity) this.emkInStorageService.get(EmkInStorageEntity.class, map.get("id").toString());
        if (task.size() > 0) {
            Task task1 = (Task) task.get(task.size() - 1);
            processInstanceId = task1.getProcessInstanceId();
        } else if (t.getState().equals("2")) {
            Map hisPorcess = this.systemService.findOneForJdbc("SELECT PROC_INST_ID_ processid FROM act_hi_taskinst WHERE ASSIGNEE_=? LIMIT 0,1 ", new Object[]{map.get("id").toString()});
            processInstanceId = String.valueOf(hisPorcess.get("processid"));
        }
        if ((processInstanceId != null) && (!processInstanceId.isEmpty())) {
            HistoricProcessInstance processInstance = (HistoricProcessInstance) this.historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

            BpmnModel bpmnModel = this.repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            this.processEngineConfiguration = this.processEngine.getProcessEngineConfiguration();
            Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) this.processEngineConfiguration);

            ProcessDiagramGenerator diagramGenerator = this.processEngineConfiguration.getProcessDiagramGenerator();
            ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) this.repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

            List<HistoricActivityInstance> highLightedActivitList = this.historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

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

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkInStorageController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkInStorageEntity emkInStorage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkInStorageEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkInStorage, request.getParameterMap());
        List<EmkInStorageEntity> emkInStorages = this.emkInStorageService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "采购订单");
        modelMap.put("entity", EmkInStorageEntity.class);
        modelMap.put("params", new ExportParams("采购订单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkInStorages);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkInStorageEntity emkInStorage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "采购订单");
        modelMap.put("entity", EmkInStorageEntity.class);
        modelMap.put("params", new ExportParams("采购订单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "采购订单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkInStorageEntity>> list() {
        List<EmkInStorageEntity> listEmkInStorages = this.emkInStorageService.getList(EmkInStorageEntity.class);
        return Result.success(listEmkInStorages);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取采购订单信息", notes = "根据ID获取采购订单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkInStorageEntity task = (EmkInStorageEntity) this.emkInStorageService.get(EmkInStorageEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取采购订单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建采购订单")
    public ResponseMessage<?> create(@ApiParam(name = "采购订单对象") @RequestBody EmkInStorageEntity emkInStorage, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkInStorageEntity>> failures = this.validator.validate(emkInStorage, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkInStorageService.save(emkInStorage);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("采购订单信息保存失败");
        }
        return Result.success(emkInStorage);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新采购订单", notes = "更新采购订单")
    public ResponseMessage<?> update(@ApiParam(name = "采购订单对象") @RequestBody EmkInStorageEntity emkInStorage) {
        Set<ConstraintViolation<EmkInStorageEntity>> failures = this.validator.validate(emkInStorage, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkInStorageService.saveOrUpdate(emkInStorage);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新采购订单信息失败");
        }
        return Result.success("更新采购订单信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除采购订单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkInStorageService.deleteEntityById(EmkInStorageEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("采购订单删除失败");
        }
        return Result.success();
    }
}
