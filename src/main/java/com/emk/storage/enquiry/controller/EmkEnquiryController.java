package com.emk.storage.enquiry.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquiry.service.EmkEnquiryServiceI;
import com.emk.storage.sampleprice.entity.EmkSamplePriceEntity;
import com.emk.util.DateUtil;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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

@Api(value = "EmkEnquiry", description = "询盘单", tags = "emkEnquiryController")
@Controller
@RequestMapping("/emkEnquiryController")
public class EmkEnquiryController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkEnquiryController.class);
    @Autowired
    private EmkEnquiryServiceI emkEnquiryService;
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
        return new ModelAndView("com/emk/storage/enquiry/emkEnquiryList");
    }

    @RequestMapping(params = "photo")
    public ModelAndView photo(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/enquiry/photo");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkEnquiryEntity emkEnquiry, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkEnquiryEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkEnquiry, request.getParameterMap());


        cq.add();
        this.emkEnquiryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkEnquiryEntity emkEnquiry, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkEnquiry = (EmkEnquiryEntity) this.systemService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
        message = "询盘单删除成功";
        try {
            this.emkEnquiryService.delete(emkEnquiry);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单删除失败";
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
        message = "询盘单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkEnquiryEntity emkEnquiry = (EmkEnquiryEntity) this.systemService.getEntity(EmkEnquiryEntity.class, id);


                this.emkEnquiryService.delete(emkEnquiry);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkEnquiryEntity emkEnquiry, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "询盘单添加成功";
        try {
            emkEnquiry.setState("0");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(enquiry_no, 3)),0)+1 AS signed) orderNum from emk_enquiry");

            emkEnquiry.setEnquiryNo("YXDD" + emkEnquiry.getCusNum() + DateUtil.format(new Date(), "yyMMdd") + String.format("%03d", Integer.parseInt(orderNum.get("orderNum").toString())));
            this.emkEnquiryService.save(emkEnquiry);

            EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
                samplePriceEntity.setBz(map.get("pbz").toString());
                samplePriceEntity.setEnquiryId(emkEnquiry.getId());
                samplePriceEntity.setState(map.get("pstate").toString());
                this.systemService.save(samplePriceEntity);
            }


            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkEnquiryEntity emkEnquiry, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "询盘单更新成功";
        EmkEnquiryEntity t = (EmkEnquiryEntity) this.emkEnquiryService.get(EmkEnquiryEntity.class, emkEnquiry.getId());
        try {
            if(!t.getState().equals("0")){
                j.setMsg("询盘单已提交，不能进行编辑");
                j.setSuccess(false);
                return j;
            }
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            emkEnquiry.setState("0");
            MyBeanUtils.copyBeanNotNull2Bean(emkEnquiry, t);
            this.emkEnquiryService.saveOrUpdate(t);

            this.systemService.executeSql("delete from emk_sample_price where ENQUIRY_ID=?", emkEnquiry.getId());
            EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
                samplePriceEntity.setBz(map.get("pbz").toString());
                samplePriceEntity.setEnquiryId(emkEnquiry.getId());
                samplePriceEntity.setState(map.get("pstate").toString());
                this.systemService.save(samplePriceEntity);
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkEnquiryEntity emkEnquiry, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkEnquiry.getId())) {
            emkEnquiry = (EmkEnquiryEntity) this.emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
            req.setAttribute("emkEnquiryPage", emkEnquiry);
        }
        return new ModelAndView("com/emk/storage/enquiry/emkEnquiry-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkEnquiryEntity emkEnquiry, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkEnquiry.getId())) {
            emkEnquiry = (EmkEnquiryEntity) this.emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
            req.setAttribute("emkEnquiryPage", emkEnquiry);

            EmkSamplePriceEntity samplePriceEntity = (EmkSamplePriceEntity) this.systemService.findUniqueByProperty(EmkSamplePriceEntity.class, "enquiryId", emkEnquiry.getId());
            req.setAttribute("samplePriceEntity", samplePriceEntity);
        }
        return new ModelAndView("com/emk/storage/enquiry/emkEnquiry-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkEnquiryEntity emkEnquiry, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkEnquiry.getId())) {
            emkEnquiry = (EmkEnquiryEntity) this.emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
            req.setAttribute("emkEnquiryPage", emkEnquiry);

           /* Calendar cal1 = Calendar.getInstance();
            cal1.setTime(DateUtils.str2Date(emkEnquiry.getYsDate(),DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = DateUtils.dateDiff('d',cal1,cal2);
            if(day<0){
                day = 0;
            }
            req.setAttribute("levelDays",day);*/
            EmkSamplePriceEntity samplePriceEntity = (EmkSamplePriceEntity) this.systemService.findUniqueByProperty(EmkSamplePriceEntity.class, "enquiryId", emkEnquiry.getId());
            req.setAttribute("samplePriceEntity", samplePriceEntity);
        }
        return new ModelAndView("com/emk/storage/enquiry/emkEnquiry-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkEnquiryController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkEnquiryEntity emkEnquiry, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkEnquiryEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkEnquiry, request.getParameterMap());
        List<EmkEnquiryEntity> emkEnquirys = this.emkEnquiryService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "询盘单");
        modelMap.put("entity", EmkEnquiryEntity.class);
        modelMap.put("params", new ExportParams("询盘单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkEnquirys);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkEnquiryEntity emkEnquiry, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "询盘单");
        modelMap.put("entity", EmkEnquiryEntity.class);
        modelMap.put("params", new ExportParams("询盘单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "询盘单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkEnquiryEntity>> list() {
        List<EmkEnquiryEntity> listEmkEnquirys = this.emkEnquiryService.getList(EmkEnquiryEntity.class);
        return Result.success(listEmkEnquirys);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取询盘单信息", notes = "根据ID获取询盘单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkEnquiryEntity task = (EmkEnquiryEntity) this.emkEnquiryService.get(EmkEnquiryEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取询盘单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建询盘单")
    public ResponseMessage<?> create(@ApiParam(name = "询盘单对象") @RequestBody EmkEnquiryEntity emkEnquiry, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkEnquiryEntity>> failures = this.validator.validate(emkEnquiry, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkEnquiryService.save(emkEnquiry);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("询盘单信息保存失败");
        }
        return Result.success(emkEnquiry);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新询盘单", notes = "更新询盘单")
    public ResponseMessage<?> update(@ApiParam(name = "询盘单对象") @RequestBody EmkEnquiryEntity emkEnquiry) {
        Set<ConstraintViolation<EmkEnquiryEntity>> failures = this.validator.validate(emkEnquiry, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkEnquiryService.saveOrUpdate(emkEnquiry);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新询盘单信息失败");
        }
        return Result.success("更新询盘单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除询盘单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkEnquiryService.deleteEntityById(EmkEnquiryEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("询盘单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkEnquiryEntity emkEnquiryEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "询盘单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkEnquiryEntity.getId() == null) || (emkEnquiryEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkEnquiryEntity enquiryEntity = systemService.getEntity(EmkEnquiryEntity.class, id);
                    if (!enquiryEntity.getState().equals("0")) {
                        message = "存在已提交的询盘单，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkEnquiryEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkEnquiryEntity t = emkEnquiryService.get(EmkEnquiryEntity.class, id);
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
                            t.setLeadAdvice(emkEnquiryEntity.getLeadAdvice());
                            if (emkEnquiryEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkEnquiryEntity.getIsPass());
                                taskService.complete(task1.getId(), variables);

                                t.setState("2");

                                //发起工单流程
                                EmkWorkOrderEntity emkWorkOrderEntity = new EmkWorkOrderEntity();
                                emkWorkOrderEntity.setIsPrint(t.getIsPrint());
                                emkWorkOrderEntity.setAskNo(t.getEnquiryNo());
                                emkWorkOrderEntity.setState("1");
                                emkWorkOrderEntity.setAskWorkAdvice(t.getLeadAdvice());
                                emkWorkOrderEntity.setAskWorkUserId(user.getUserName());
                                emkWorkOrderEntity.setAskWorkUser(user.getRealName());
                                emkWorkOrderEntity.setAskWorkDate(org.apache.tools.ant.util.DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                                emkWorkOrderEntity.setCreateName(t.getCreateName());
                                emkWorkOrderEntity.setKdDate(t.getKdDate());
                                Map orderNum = this.systemService.findOneForJdbc("select CAST(ifnull(max(right(workNo, 6)),0)+1 AS signed) orderNum from emk_work_order");
                                emkWorkOrderEntity.setWorkNo("GD" + DateUtil.format(new Date(), "yyMMdd") + String.format("%03d", Integer.parseInt(orderNum.get("orderNum").toString())));
                                systemService.save(emkWorkOrderEntity);
                                variables.put("isPrint", emkWorkOrderEntity.getIsPrint());
                                variables.put("inputUser", emkWorkOrderEntity.getId());

                                task = taskService.createTaskQuery().taskAssignee(emkWorkOrderEntity.getId()).list();
                                if (task.size()== 0 || task == null) {
                                    ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("emk", "emkWorkOrderPage", variables);
                                    task = taskService.createTaskQuery().taskAssignee(emkWorkOrderEntity.getId()).list();
                                }
                                task1 = task.get(task.size() - 1);
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

                        }
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("enquiry", "emkEnquiryEntity", variables);
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
            message = "询盘单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkEnquiryEntity emkEnquiryEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkEnquiryEntity.getId())) {
            emkEnquiryEntity = emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiryEntity.getId());
            req.setAttribute("emkEnquiry", emkEnquiryEntity);
        }
        return new ModelAndView("com/emk/storage/enquiry/emkEnquiry-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkEnquiryEntity emkEnquiryEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='instorageTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_enquiry t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
        emkEnquiryEntity = emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiryEntity.getId());
        req.setAttribute("emkEnquiry", emkEnquiryEntity);
        return new ModelAndView("com/emk/storage/enquiry/time");
    }
}
