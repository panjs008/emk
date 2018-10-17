package com.emk.produce.produceschedule.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.produceschedule.entity.EmkProduceScheduleEntity;
import com.emk.produce.produceschedule.service.EmkProduceScheduleServiceI;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.sample.entity.EmkSampleEntity;
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

@Api(value = "EmkProduceSchedule", description = "采购生产表", tags = {"emkProduceScheduleController"})
@Controller
@RequestMapping({"/emkProduceScheduleController"})
public class EmkProduceScheduleController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProduceScheduleController.class);
    @Autowired
    private EmkProduceScheduleServiceI emkProduceScheduleService;
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

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/produceschedule/emkProduceScheduleList");
    }

    @RequestMapping(params = {"orderMxList"})
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        request.setAttribute("categoryEntityList", codeList);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{map.get("proOrderId")});
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/produce/produceschedule/orderMxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProduceScheduleEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProduceSchedule, request.getParameterMap());


        cq.add();
        this.emkProduceScheduleService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProduceSchedule = (EmkProduceScheduleEntity) this.systemService.getEntity(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
        message = "采购生产表删除成功";
        try {
            this.emkProduceScheduleService.delete(emkProduceSchedule);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购生产表删除失败";
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
        message = "采购生产表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProduceScheduleEntity emkProduceSchedule = (EmkProduceScheduleEntity) this.systemService.getEntity(EmkProduceScheduleEntity.class, id);


                this.emkProduceScheduleService.delete(emkProduceSchedule);
                this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{emkProduceSchedule.getId()});
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购生产表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购生产表添加成功";
        try {
            emkProduceSchedule.setState("0");
            this.emkProduceScheduleService.save(emkProduceSchedule);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(emkProduceSchedule.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        this.systemService.save(orderMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购生产表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购生产表更新成功";
        EmkProduceScheduleEntity t = (EmkProduceScheduleEntity) this.emkProduceScheduleService.get(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
        try {
            emkProduceSchedule.setState("0");
            MyBeanUtils.copyBeanNotNull2Bean(emkProduceSchedule, t);
            this.emkProduceScheduleService.saveOrUpdate(t);
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

            this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{t.getId()});
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && (!((String) map.get("orderMxList[" + i + "].color")).equals(""))) {
                        orderMxEntity.setEnquiryId(t.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        this.systemService.save(orderMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "采购生产表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkProduceSchedule.getId())) {
            emkProduceSchedule = (EmkProduceScheduleEntity) this.emkProduceScheduleService.getEntity(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
            req.setAttribute("emkProduceSchedulePage", emkProduceSchedule);
        }
        return new ModelAndView("com/emk/produce/produceschedule/emkProduceSchedule-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest req) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkProduceSchedule.getId())) {
            emkProduceSchedule = (EmkProduceScheduleEntity) this.emkProduceScheduleService.getEntity(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
            req.setAttribute("emkProduceSchedulePage", emkProduceSchedule);
        }
        return new ModelAndView("com/emk/produce/produceschedule/emkProduceSchedule-update");
    }

    @RequestMapping(params = {"goUpdate2"})
    public ModelAndView goUpdate2(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest req) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkProduceSchedule.getId())) {
            emkProduceSchedule = (EmkProduceScheduleEntity) this.emkProduceScheduleService.getEntity(EmkProduceScheduleEntity.class, emkProduceSchedule.getId());
            req.setAttribute("emkProduceSchedulePage", emkProduceSchedule);
        }
        return new ModelAndView("com/emk/produce/produceschedule/emkProduceSchedule-update2");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProduceScheduleController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProduceScheduleEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProduceSchedule, request.getParameterMap());
        List<EmkProduceScheduleEntity> emkProduceSchedules = this.emkProduceScheduleService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "采购生产表");
        modelMap.put("entity", EmkProduceScheduleEntity.class);
        modelMap.put("params", new ExportParams("采购生产表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProduceSchedules);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkProduceScheduleEntity emkProduceSchedule, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "采购生产表");
        modelMap.put("entity", EmkProduceScheduleEntity.class);
        modelMap.put("params", new ExportParams("采购生产表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "采购生产表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkProduceScheduleEntity>> list() {
        List<EmkProduceScheduleEntity> listEmkProduceSchedules = this.emkProduceScheduleService.getList(EmkProduceScheduleEntity.class);
        return Result.success(listEmkProduceSchedules);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取采购生产表信息", notes = "根据ID获取采购生产表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProduceScheduleEntity task = (EmkProduceScheduleEntity) this.emkProduceScheduleService.get(EmkProduceScheduleEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取采购生产表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建采购生产表")
    public ResponseMessage<?> create(@ApiParam(name = "采购生产表对象") @RequestBody EmkProduceScheduleEntity emkProduceSchedule, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProduceScheduleEntity>> failures = this.validator.validate(emkProduceSchedule, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProduceScheduleService.save(emkProduceSchedule);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("采购生产表信息保存失败");
        }
        return Result.success(emkProduceSchedule);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新采购生产表", notes = "更新采购生产表")
    public ResponseMessage<?> update(@ApiParam(name = "采购生产表对象") @RequestBody EmkProduceScheduleEntity emkProduceSchedule) {
        Set<ConstraintViolation<EmkProduceScheduleEntity>> failures = this.validator.validate(emkProduceSchedule, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProduceScheduleService.saveOrUpdate(emkProduceSchedule);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新采购生产表信息失败");
        }
        return Result.success("更新采购生产表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除采购生产表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkProduceScheduleService.deleteEntityById(EmkProduceScheduleEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("采购生产表删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkProduceScheduleEntity emkProduceScheduleEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购生产单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkProduceScheduleEntity.getId() == null) || (emkProduceScheduleEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkProduceScheduleEntity produceScheduleEntity = systemService.getEntity(EmkProduceScheduleEntity.class, id);
                    if (!produceScheduleEntity.getState().equals("0")) {
                        message = "存在已提交的采购生产单，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkProduceScheduleEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkProduceScheduleEntity t = emkProduceScheduleService.get(EmkProduceScheduleEntity.class, id);
                    t.setState("1");
                    variables.put("optUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("produceTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkProduceScheduleEntity.getLeadAdvice());
                            if (emkProduceScheduleEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkProduceScheduleEntity.getIsPass());
                                taskService.complete(task1.getId(), variables);

                                t.setSsSampleUser(t.getCreateName());
                                t.setSsSampleUserId(t.getCreateBy());

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
                        if (task1.getTaskDefinitionKey().equals("syTask")) {
                            t.setTestUser(t.getLeader());
                            t.setTestUserId(t.getLeadUserId());
                            t.setColorAdvice(t.getLeadAdvice());
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("testTask")) {
                            t.setTestUserAdvice(t.getLeadAdvice());
                            taskService.complete(task1.getId(), variables);
                        }
                        systemService.saveOrUpdate(t);

                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("produce", "emkProduceScheduleEntity", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);
                    }

                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "采购生产单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkProduceScheduleEntity emkProduceScheduleEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProduceScheduleEntity.getId())) {
            emkProduceScheduleEntity = emkProduceScheduleService.getEntity(EmkProduceScheduleEntity.class, emkProduceScheduleEntity.getId());
            req.setAttribute("emkProduceSchedule", emkProduceScheduleEntity);
        }
        return new ModelAndView("com/emk/produce/produceschedule/emkProduceSchedule-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkProduceScheduleEntity emkProduceScheduleEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='produceTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " WHEN t1.TASK_DEF_KEY_='ssyTask' THEN t2.SS_SAMPLE_USER \n" +
                " WHEN t1.TASK_DEF_KEY_='cqyTask' THEN t2.CQ_SAMPLE_USER \n" +

                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_produce_schedule t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
        emkProduceScheduleEntity = emkProduceScheduleService.getEntity(EmkProduceScheduleEntity.class, emkProduceScheduleEntity.getId());
        req.setAttribute("emkProduceSchedule", emkProduceScheduleEntity);
        return new ModelAndView("com/emk/produce/produceschedule/time");

    }
}
