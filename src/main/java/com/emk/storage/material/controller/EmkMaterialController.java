package com.emk.storage.material.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.material.entity.EmkMaterialEntity;
import com.emk.storage.material.service.EmkMaterialServiceI;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.*;
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
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.entity.ExportParams;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Api(value = "EmkMaterial", description = "原料面料需求开发单", tags = "emkMaterialController")
@Controller
@RequestMapping("/emkMaterialController")
public class EmkMaterialController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMaterialController.class);
    @Autowired
    private EmkMaterialServiceI emkMaterialService;
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
        return new ModelAndView("com/emk/storage/material/emkMaterialList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkMaterialEntity emkMaterial, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("jsy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkMaterial, request.getParameterMap());


        cq.add();
        this.emkMaterialService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkMaterialEntity emkMaterial, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMaterial = (EmkMaterialEntity) this.systemService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
        message = "原料面料需求开发单删除成功";
        try {
            this.emkMaterialService.delete(emkMaterial);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料面料需求开发单删除失败";
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
        message = "原料面料需求开发单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkMaterialEntity emkMaterial = (EmkMaterialEntity) this.systemService.getEntity(EmkMaterialEntity.class, id);


                this.emkMaterialService.delete(emkMaterial);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料面料需求开发单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkMaterialEntity emkMaterial, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料面料需求开发单添加成功";
        try {
            emkMaterial.setState("0");
            Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(MATERIAL_NO, 2)),0)+1 AS signed) orderNum from emk_material");
            emkMaterial.setMaterialNo("SY" + DateUtils.format(new Date(), "yyMMdd") + "B" + String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));
            this.emkMaterialService.save(emkMaterial);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料面料需求开发单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkMaterialEntity emkMaterial, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料面料需求开发单更新成功";
        EmkMaterialEntity t = (EmkMaterialEntity) this.emkMaterialService.get(EmkMaterialEntity.class, emkMaterial.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterial, t);
            this.emkMaterialService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "原料面料需求开发单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkMaterialEntity emkMaterial, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkMaterial.getId())) {
            emkMaterial = (EmkMaterialEntity) this.emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
            req.setAttribute("emkMaterialPage", emkMaterial);
        }
        return new ModelAndView("com/emk/storage/material/emkMaterial-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkMaterialEntity emkMaterial, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterial.getId())) {
            emkMaterial = (EmkMaterialEntity) this.emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
            req.setAttribute("emkMaterialPage", emkMaterial);
        }
        return new ModelAndView("com/emk/storage/material/emkMaterial-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkMaterialEntity emkMaterial, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterial.getId())) {
            emkMaterial = (EmkMaterialEntity) this.emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
            req.setAttribute("emkMaterialPage", emkMaterial);
        }
        return new ModelAndView("com/emk/storage/material/emkMaterial-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkMaterialController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkMaterialEntity emkMaterial, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkMaterial, request.getParameterMap());
        List<EmkMaterialEntity> emkMaterials = this.emkMaterialService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "原料面料需求开发单");
        modelMap.put("entity", EmkMaterialEntity.class);
        modelMap.put("params", new ExportParams("原料面料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkMaterials);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkMaterialEntity emkMaterial, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "原料面料需求开发单");
        modelMap.put("entity", EmkMaterialEntity.class);
        modelMap.put("params", new ExportParams("原料面料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "原料面料需求开发单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkMaterialEntity>> list() {
        List<EmkMaterialEntity> listEmkMaterials = this.emkMaterialService.getList(EmkMaterialEntity.class);
        return Result.success(listEmkMaterials);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取原料面料需求开发单信息", notes = "根据ID获取原料面料需求开发单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMaterialEntity task = (EmkMaterialEntity) this.emkMaterialService.get(EmkMaterialEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取原料面料需求开发单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建原料面料需求开发单")
    public ResponseMessage<?> create(@ApiParam(name = "原料面料需求开发单对象") @RequestBody EmkMaterialEntity emkMaterial, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMaterialEntity>> failures = this.validator.validate(emkMaterial, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialService.save(emkMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("原料面料需求开发单信息保存失败");
        }
        return Result.success(emkMaterial);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新原料面料需求开发单", notes = "更新原料面料需求开发单")
    public ResponseMessage<?> update(@ApiParam(name = "原料面料需求开发单对象") @RequestBody EmkMaterialEntity emkMaterial) {
        Set<ConstraintViolation<EmkMaterialEntity>> failures = this.validator.validate(emkMaterial, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkMaterialService.saveOrUpdate(emkMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新原料面料需求开发单信息失败");
        }
        return Result.success("更新原料面料需求开发单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除原料面料需求开发单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkMaterialService.deleteEntityById(EmkMaterialEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("原料面料需求开发单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkMaterialEntity emkMaterialEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "原料面料需求开发单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkMaterialEntity.getId() == null) || (emkMaterialEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialEntity materialEntity = systemService.getEntity(EmkMaterialEntity.class, id);
                    if (!materialEntity.getState().equals("0")) {
                        message = "存在已提交的原料面料需求开发单，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkMaterialEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialEntity t = emkMaterialService.get(EmkMaterialEntity.class, id);
                    t.setState("1");
                    variables.put("optUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("checkfactoryTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkMaterialEntity.getLeadAdvice());
                            if (emkMaterialEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkMaterialEntity.getIsPass());
                                taskService.complete(task1.getId(), variables);
                                t.setState("2");
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

                            }
                        }

                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("material", "emkMaterialEntity", variables);
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
            message = "原料面料需求开发单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkMaterialEntity emkMaterialEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialEntity.getId())) {
            emkMaterialEntity = emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterialEntity.getId());
            req.setAttribute("emkMaterial", emkMaterialEntity);
        }
        return new ModelAndView("com/emk/storage/material/emkMaterial-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkMaterialEntity emkMaterialEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='materialTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_material t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
        emkMaterialEntity = emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterialEntity.getId());
        req.setAttribute("emkMaterial", emkMaterialEntity);
        return new ModelAndView("com/emk/storage/material/time");

    }
}
