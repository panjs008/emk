package com.emk.storage.accessories.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.accessories.entity.EmkAccessoriesEntity;
import com.emk.storage.accessories.service.EmkAccessoriesServiceI;
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
import org.jeecgframework.core.util.ExceptionUtil;
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

@Api(value = "EmkAccessories", description = "缝制辅料需求开发单", tags = "emkAccessoriesController")
@Controller
@RequestMapping("/emkAccessoriesController")
public class EmkAccessoriesController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkAccessoriesController.class);
    @Autowired
    private EmkAccessoriesServiceI emkAccessoriesService;
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
        return new ModelAndView("com/emk/storage/accessories/emkAccessoriesList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkAccessoriesEntity emkAccessories, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkAccessoriesEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("jsy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkAccessories, request.getParameterMap());


        cq.add();
        this.emkAccessoriesService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkAccessoriesEntity emkAccessories, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkAccessories = (EmkAccessoriesEntity) this.systemService.getEntity(EmkAccessoriesEntity.class, emkAccessories.getId());
        message = "缝制辅料需求开发单删除成功";
        try {
            this.emkAccessoriesService.delete(emkAccessories);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单删除失败";
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
        message = "缝制辅料需求开发单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkAccessoriesEntity emkAccessories = (EmkAccessoriesEntity) this.systemService.getEntity(EmkAccessoriesEntity.class, id);


                this.emkAccessoriesService.delete(emkAccessories);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkAccessoriesEntity emkAccessories, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "缝制辅料需求开发单添加成功";
        try {
            emkAccessories.setState("0");
            Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(MATERIAL_NO, 2)),0)+1 AS signed) orderNum from emk_accessories");
            emkAccessories.setMaterialNo("SY" + DateUtils.format(new Date(), "yyMMdd") + "B" + String.format("%02d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
            this.emkAccessoriesService.save(emkAccessories);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkAccessoriesEntity emkAccessories, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "缝制辅料需求开发单更新成功";
        EmkAccessoriesEntity t = (EmkAccessoriesEntity) this.emkAccessoriesService.get(EmkAccessoriesEntity.class, emkAccessories.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkAccessories, t);
            this.emkAccessoriesService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkAccessoriesEntity emkAccessories, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkAccessories.getId())) {
            emkAccessories = (EmkAccessoriesEntity) this.emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessories.getId());
            req.setAttribute("emkAccessoriesPage", emkAccessories);
        }
        return new ModelAndView("com/emk/storage/accessories/emkAccessories-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkAccessoriesEntity emkAccessories, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkAccessories.getId())) {
            emkAccessories = (EmkAccessoriesEntity) this.emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessories.getId());
            req.setAttribute("emkAccessoriesPage", emkAccessories);
        }
        return new ModelAndView("com/emk/storage/accessories/emkAccessories-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkAccessoriesEntity emkAccessories, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkAccessories.getId())) {
            emkAccessories = (EmkAccessoriesEntity) this.emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessories.getId());
            req.setAttribute("emkAccessoriesPage", emkAccessories);
        }
        return new ModelAndView("com/emk/storage/accessories/emkAccessories-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkAccessoriesController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkAccessoriesEntity emkAccessories, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkAccessoriesEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkAccessories, request.getParameterMap());
        List<EmkAccessoriesEntity> emkAccessoriess = this.emkAccessoriesService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "缝制辅料需求开发单");
        modelMap.put("entity", EmkAccessoriesEntity.class);
        modelMap.put("params", new ExportParams("缝制辅料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkAccessoriess);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkAccessoriesEntity emkAccessories, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "缝制辅料需求开发单");
        modelMap.put("entity", EmkAccessoriesEntity.class);
        modelMap.put("params", new ExportParams("缝制辅料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "缝制辅料需求开发单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkAccessoriesEntity>> list() {
        List<EmkAccessoriesEntity> listEmkAccessoriess = this.emkAccessoriesService.getList(EmkAccessoriesEntity.class);
        return Result.success(listEmkAccessoriess);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取缝制辅料需求开发单信息", notes = "根据ID获取缝制辅料需求开发单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkAccessoriesEntity task = (EmkAccessoriesEntity) this.emkAccessoriesService.get(EmkAccessoriesEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取缝制辅料需求开发单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建缝制辅料需求开发单")
    public ResponseMessage<?> create(@ApiParam(name = "缝制辅料需求开发单对象") @RequestBody EmkAccessoriesEntity emkAccessories, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkAccessoriesEntity>> failures = this.validator.validate(emkAccessories, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkAccessoriesService.save(emkAccessories);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("缝制辅料需求开发单信息保存失败");
        }
        return Result.success(emkAccessories);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新缝制辅料需求开发单", notes = "更新缝制辅料需求开发单")
    public ResponseMessage<?> update(@ApiParam(name = "缝制辅料需求开发单对象") @RequestBody EmkAccessoriesEntity emkAccessories) {
        Set<ConstraintViolation<EmkAccessoriesEntity>> failures = this.validator.validate(emkAccessories, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkAccessoriesService.saveOrUpdate(emkAccessories);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新缝制辅料需求开发单信息失败");
        }
        return Result.success("更新缝制辅料需求开发单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除缝制辅料需求开发单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkAccessoriesService.deleteEntityById(EmkAccessoriesEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("缝制辅料需求开发单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkAccessoriesEntity emkAccessoriesEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "缝制辅料需求开发单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkAccessoriesEntity.getId() == null) || (emkAccessoriesEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkAccessoriesEntity accessoriesEntity = systemService.getEntity(EmkAccessoriesEntity.class, id);
                    if (!accessoriesEntity.getState().equals("0")) {
                        message = "存在已提交的缝制辅料需求开发单，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkAccessoriesEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkAccessoriesEntity t = emkAccessoriesService.get(EmkAccessoriesEntity.class, id);
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
                            t.setLeadAdvice(emkAccessoriesEntity.getLeadAdvice());
                            if (emkAccessoriesEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkAccessoriesEntity.getIsPass());
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
                                t.setState("0");
                            }

                        }

                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("accessories", "emkAccessoriesEntity", variables);
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
            message = "缝制辅料需求开发单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkAccessoriesEntity emkAccessoriesEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkAccessoriesEntity.getId())) {
            emkAccessoriesEntity = emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessoriesEntity.getId());
            req.setAttribute("emkAccessories", emkAccessoriesEntity);
        }
        return new ModelAndView("com/emk/storage/accessories/emkAccessories-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkAccessoriesEntity emkAccessoriesEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='accessoriesTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_accessories t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
        emkAccessoriesEntity = emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessoriesEntity.getId());
        req.setAttribute("emkAccessories", emkAccessoriesEntity);
        return new ModelAndView("com/emk/storage/accessories/time");

    }
}
