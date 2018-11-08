package com.emk.produce.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.produce.test.entity.EmkTestEntity;
import com.emk.produce.test.service.EmkTestServiceI;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.util.DateUtil;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.WebFileUtils;
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

@Api(value = "EmkTest", description = "测试申请表", tags = "emkTestController")
@Controller
@RequestMapping("/emkTestController")
public class EmkTestController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkTestController.class);
    @Autowired
    private EmkTestServiceI emkTestService;
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
        return new ModelAndView("com/emk/produce/test/emkTestList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkTestEntity emkTest, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkTestEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkTest, request.getParameterMap());


        cq.add();
        this.emkTestService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkTestEntity emkTest, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkTest = (EmkTestEntity) this.systemService.getEntity(EmkTestEntity.class, emkTest.getId());
        message = "测试申请表删除成功";
        try {
            this.emkTestService.delete(emkTest);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "测试申请表删除失败";
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
        message = "测试申请表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkTestEntity emkTest = (EmkTestEntity) this.systemService.getEntity(EmkTestEntity.class, id);


                this.emkTestService.delete(emkTest);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "测试申请表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkTestEntity emkTest, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "测试申请表添加成功";
        try {
            emkTest.setState("0");
            Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(cssqdh, 3)),0)+1 AS signed) orderNum from emk_test");
            emkTest.setCssqdh("CS" + emkTest.getCusNum() + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", Integer.parseInt(orderNum.get("orderNum").toString())));

            this.emkTestService.save(emkTest);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "测试申请表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkTestEntity emkTest, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "测试申请表更新成功";
        EmkTestEntity t = (EmkTestEntity) this.emkTestService.get(EmkTestEntity.class, emkTest.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkTest, t);
            this.emkTestService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "测试申请表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "dowaLoadFile")
    public ModelAndView dowaLoadFile(EmkTestEntity emkTest, HttpServletRequest request, HttpServletResponse response) {
        String message = null;
        message = "文件下载成功";
        try {
            String filePath = "";
            String file = "";
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            file = emkTest.getTestContentUrl();
            if ((file != null) && (!file.isEmpty())) {
                filePath = request.getRealPath("/") + file;
                WebFileUtils.downLoad(filePath, response, false);
            } else {
                emkTest = (EmkTestEntity) this.emkTestService.getEntity(EmkTestEntity.class, emkTest.getId());
                request.setAttribute("emkTestPage", emkTest);
                return new ModelAndView("com/emk/produce/test/emkTest-update");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "文件下载失败";
            throw new BusinessException(e.getMessage());
        }
        return null;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkTestEntity emkTest, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkTest.getId())) {
            emkTest = (EmkTestEntity) this.emkTestService.getEntity(EmkTestEntity.class, emkTest.getId());
            req.setAttribute("emkTestPage", emkTest);
        }
        return new ModelAndView("com/emk/produce/test/emkTest-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkTestEntity emkTest, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkTest.getId())) {
            emkTest = (EmkTestEntity) this.emkTestService.getEntity(EmkTestEntity.class, emkTest.getId());
            req.setAttribute("emkTestPage", emkTest);
        }
        return new ModelAndView("com/emk/produce/test/emkTest-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkTestEntity emkTest, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkTest.getId())) {
            emkTest = (EmkTestEntity) this.emkTestService.getEntity(EmkTestEntity.class, emkTest.getId());
            req.setAttribute("emkTestPage", emkTest);
        }
        return new ModelAndView("com/emk/produce/test/emkTest-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkTestController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkTestEntity emkTest, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkTestEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkTest, request.getParameterMap());
        List<EmkTestEntity> emkTests = this.emkTestService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "测试申请表");
        modelMap.put("entity", EmkTestEntity.class);
        modelMap.put("params", new ExportParams("测试申请表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkTests);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkTestEntity emkTest, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "测试申请表");
        modelMap.put("entity", EmkTestEntity.class);
        modelMap.put("params", new ExportParams("测试申请表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "测试申请表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkTestEntity>> list() {
        List<EmkTestEntity> listEmkTests = this.emkTestService.getList(EmkTestEntity.class);
        return Result.success(listEmkTests);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取测试申请表信息", notes = "根据ID获取测试申请表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkTestEntity task = (EmkTestEntity) this.emkTestService.get(EmkTestEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取测试申请表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建测试申请表")
    public ResponseMessage<?> create(@ApiParam(name = "测试申请表对象") @RequestBody EmkTestEntity emkTest, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkTestEntity>> failures = this.validator.validate(emkTest, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkTestService.save(emkTest);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("测试申请表信息保存失败");
        }
        return Result.success(emkTest);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新测试申请表", notes = "更新测试申请表")
    public ResponseMessage<?> update(@ApiParam(name = "测试申请表对象") @RequestBody EmkTestEntity emkTest) {
        Set<ConstraintViolation<EmkTestEntity>> failures = this.validator.validate(emkTest, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkTestService.saveOrUpdate(emkTest);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新测试申请表信息失败");
        }
        return Result.success("更新测试申请表信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除测试申请表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkTestService.deleteEntityById(EmkTestEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("测试申请表删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkTestEntity emkTestEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "测试申请表提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkTestEntity.getId() == null) || (emkTestEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkTestEntity testEntity = systemService.getEntity(EmkTestEntity.class, id);
                    if (!testEntity.getState().equals("0")) {
                        message = "存在已提交的测试申请表，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkTestEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkTestEntity t = emkTestService.get(EmkTestEntity.class, id);
                    t.setState("1");
                    variables.put("optUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("testTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkTestEntity.getLeadAdvice());
                            if (t.getIsPass().equals("0")) {
                                variables.put("isPass", emkTestEntity.getIsPass());
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
                        if (task1.getTaskDefinitionKey().equals("cwTask")) {
                            t.setState("2");
                            this.taskService.complete(task1.getId(), variables);

                        }
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("test", "emkTestEntity", variables);
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
            message = "测试申请表提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkTestEntity emkTestEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkTestEntity.getId())) {
            emkTestEntity = emkTestService.getEntity(EmkTestEntity.class, emkTestEntity.getId());
            req.setAttribute("emkTestEntity", emkTestEntity);
        }
        return new ModelAndView("com/emk/produce/test/emkTest-work");
    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkTestEntity emkTestEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='testTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_test t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
        emkTestEntity = emkTestService.getEntity(EmkTestEntity.class, emkTestEntity.getId());
        req.setAttribute("emkTest", emkTestEntity);
        return new ModelAndView("com/emk/produce/test/time");

    }
}
