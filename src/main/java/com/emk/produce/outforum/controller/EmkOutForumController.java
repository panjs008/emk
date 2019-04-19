package com.emk.produce.outforum.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.bill.proorderdetail.entity.EmkProOrderDetailEntity;
import com.emk.produce.outforum.entity.EmkOutForumEntity;
import com.emk.produce.outforum.service.EmkOutForumServiceI;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
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

@Api(value = "EmkOutForum", description = "出货通知单", tags = "emkOutForumController")
@Controller
@RequestMapping("/emkOutForumController")
public class EmkOutForumController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkOutForumController.class);
    @Autowired
    private EmkOutForumServiceI emkOutForumService;
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
        return new ModelAndView("com/emk/produce/outforum/emkOutForumList");
    }

    @RequestMapping(params = "detailMxList")
    public ModelAndView detailMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        request.setAttribute("colorList", list);
        list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='size'");
        request.setAttribute("sizeList", list);
        if (Utils.notEmpty(map.get("outForumId"))) {
            List<EmkProOrderDetailEntity> emkProOrderDetailEntities = systemService.findHql("from EmkProOrderDetailEntity where proOrderId=?", map.get("outForumId"));
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/produce/outforum/detailMxList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkOutForumEntity emkOutForum, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkOutForumEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")|| roleMap.get("rolecode").toString().contains("scgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkOutForum, request.getParameterMap());


        cq.add();
        emkOutForumService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkOutForumEntity emkOutForum, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkOutForum = systemService.getEntity(EmkOutForumEntity.class, emkOutForum.getId());
        message = "出货通知单删除成功";
        try {
            emkOutForumService.delete(emkOutForum);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "出货通知单删除失败";
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
        message = "出货通知单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkOutForumEntity emkOutForum = systemService.getEntity(EmkOutForumEntity.class, id);
                emkOutForumService.delete(emkOutForum);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "出货通知单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkOutForumEntity emkOutForum, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "出货通知单添加成功";
        try {
            emkOutForum.setState("0");
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(OUT_FORUM_NO, 3)),0)+1 AS signed) orderNum from emk_out_forum");
            emkOutForum.setOutForumNo("CH" + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkOutForumService.save(emkOutForum);
            //保存明细数据
            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                EmkProOrderDetailEntity proOrderDetailEntity = null;
                for (int i = 0; i < rows; i++) {
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))){
                        proOrderDetailEntity = new EmkProOrderDetailEntity();
                        proOrderDetailEntity.setProOrderId(emkOutForum.getId());
                        proOrderDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00").toString());
                        proOrderDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00").toString());
                        proOrderDetailEntity.setSampleDesc(map.get("orderMxList["+i+"].sampleDesc00").toString());
                        proOrderDetailEntity.setColor(map.get("orderMxList["+i+"].color").toString());
                        proOrderDetailEntity.setSortDesc(String.valueOf(i+1));
                        proOrderDetailEntity.setSize(map.get("orderMxList["+i+"].size00").toString());
                        proOrderDetailEntity.setPrice(map.get("orderMxList["+i+"].price00").toString());
                        proOrderDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumPrice00").toString());
                        proOrderDetailEntity.setSumMoney(map.get("orderMxList["+i+"].sumMoney00").toString());
                        proOrderDetailEntity.setSumTotal(map.get("orderMxList["+i+"].sumTotal00").toString());
                        proOrderDetailEntity.setTotal(map.get("orderMxList["+i+"].signTotal00").toString());
                        systemService.save(proOrderDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "出货通知单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkOutForumEntity emkOutForum, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "出货通知单更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

        EmkOutForumEntity t = emkOutForumService.get(EmkOutForumEntity.class, map.get("outForumId"));
        try {
            emkOutForum.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkOutForum, t);
            emkOutForumService.saveOrUpdate(t);

            //保存明细数据
            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_pro_order_detail where pro_order_id = ? ",t.getId());

                int rows = Integer.parseInt(dataRows);
                EmkProOrderDetailEntity proOrderDetailEntity = null;
                for (int i = 0; i < rows; i++) {
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))){
                        proOrderDetailEntity = new EmkProOrderDetailEntity();
                        proOrderDetailEntity.setProOrderId(t.getId());
                        proOrderDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00").toString());
                        proOrderDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00").toString());
                        proOrderDetailEntity.setSampleDesc(map.get("orderMxList["+i+"].sampleDesc00").toString());
                        proOrderDetailEntity.setColor(map.get("orderMxList["+i+"].color").toString());
                        proOrderDetailEntity.setSortDesc(String.valueOf(i+1));
                        proOrderDetailEntity.setSize(map.get("orderMxList["+i+"].size00").toString());
                        proOrderDetailEntity.setPrice(map.get("orderMxList["+i+"].price00").toString());
                        proOrderDetailEntity.setSumPrice(map.get("orderMxList["+i+"].sumPrice00").toString());
                        proOrderDetailEntity.setSumMoney(map.get("orderMxList["+i+"].sumMoney00").toString());
                        proOrderDetailEntity.setSumTotal(map.get("orderMxList["+i+"].sumTotal00").toString());
                        proOrderDetailEntity.setTotal(map.get("orderMxList["+i+"].signTotal00").toString());
                        systemService.save(proOrderDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "出货通知单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkOutForumEntity emkOutForum, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkOutForum.getId())) {
            emkOutForum = emkOutForumService.getEntity(EmkOutForumEntity.class, emkOutForum.getId());
            req.setAttribute("emkOutForumPage", emkOutForum);
        }
        return new ModelAndView("com/emk/produce/outforum/emkOutForum-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkOutForumEntity emkOutForum, HttpServletRequest req) {
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkOutForum.getId())) {
            emkOutForum = emkOutForumService.getEntity(EmkOutForumEntity.class, emkOutForum.getId());
            req.setAttribute("emkOutForumPage", emkOutForum);
        }
        return new ModelAndView("com/emk/produce/outforum/emkOutForum-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkOutForumController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkOutForumEntity emkOutForum, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkOutForumEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkOutForum, request.getParameterMap());
        List<EmkOutForumEntity> emkOutForums = emkOutForumService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "出货通知单");
        modelMap.put("entity", EmkOutForumEntity.class);
        modelMap.put("params", new ExportParams("出货通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkOutForums);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkOutForumEntity emkOutForum, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "出货通知单");
        modelMap.put("entity", EmkOutForumEntity.class);
        modelMap.put("params", new ExportParams("出货通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "出货通知单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkOutForumEntity>> list() {
        List<EmkOutForumEntity> listEmkOutForums = emkOutForumService.getList(EmkOutForumEntity.class);
        return Result.success(listEmkOutForums);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取出货通知单信息", notes = "根据ID获取出货通知单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkOutForumEntity task = emkOutForumService.get(EmkOutForumEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取出货通知单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建出货通知单")
    public ResponseMessage<?> create(@ApiParam(name = "出货通知单对象") @RequestBody EmkOutForumEntity emkOutForum, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkOutForumEntity>> failures = validator.validate(emkOutForum, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkOutForumService.save(emkOutForum);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("出货通知单信息保存失败");
        }
        return Result.success(emkOutForum);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新出货通知单", notes = "更新出货通知单")
    public ResponseMessage<?> update(@ApiParam(name = "出货通知单对象") @RequestBody EmkOutForumEntity emkOutForum) {
        Set<ConstraintViolation<EmkOutForumEntity>> failures = validator.validate(emkOutForum, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkOutForumService.saveOrUpdate(emkOutForum);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新出货通知单信息失败");
        }
        return Result.success("更新出货通知单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除出货通知单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkOutForumService.deleteEntityById(EmkOutForumEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("出货通知单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkOutForumEntity emkOutForum, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "出货通知单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkOutForum.getId() == null) || (emkOutForum.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkOutForumEntity outForumEntity = systemService.getEntity(EmkOutForumEntity.class, id);
                    if (!outForumEntity.getState().equals("0")) {
                        message = "存在已提交的出货通知单，请重新选择在提交出货通知单！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkOutForum.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkOutForumEntity t = emkOutForumService.get(EmkOutForumEntity.class, id);
                    t.setState("1");
                    variables.put("optUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("htTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkOutForum.getLeadAdvice());
                            if (emkOutForum.getIsPass().equals("0")) {
                                variables.put("isPass", emkOutForum.getIsPass());
                                taskService.complete(task1.getId(), variables);
                            } else {
                                List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(t.getId()).list();

                                List<Task> taskList = taskService.createTaskQuery().taskAssignee(t.getId()).list();
                                if (taskList.size() > 0) {
                                    Task taskH = (Task)taskList.get(taskList.size() - 1);
                                    HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
                                    FlowUtil.turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
                                    Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC",  t.getId(), historicTaskInstance.getTaskDefinitionKey());
                                    String[] activitIdArr = activityMap.get("ids").toString().split(",");
                                    String[] taskIdArr = activityMap.get("taskids").toString().split(",");
                                    systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(), taskIdArr[1]);
                                    systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", activitIdArr[0], activitIdArr[1] );
                                }
                                t.setState("0");
                            }
                        }
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("ht", "emkContractEntity", variables);
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
            message = "出货通知单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkOutForumEntity emkOutForum, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkOutForum.getId())) {
            emkOutForum = emkOutForumService.getEntity(EmkPriceEntity.class, emkOutForum.getId());
            req.setAttribute("emkOutForum", emkOutForum);
        }
        return new ModelAndView("com/emk/produce/outforum/emkOutForum-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkOutForumEntity emkOutForum, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='htTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_contract t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

        countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='" + map.get("id") + "' ";
        if (dataGrid.getPage() == 1) {
            sql = sql + " limit 0, " + dataGrid.getRows();
        } else {
            sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
        }
        systemService.listAllByJdbc(dataGrid, sql, countsql);
        req.setAttribute("taskList", dataGrid.getResults());
        if (dataGrid.getResults().size() > 0) {
            req.setAttribute("stepProcess", dataGrid.getResults().size() - 1);
        } else {
            req.setAttribute("stepProcess", 0);
        }
        emkOutForum = emkOutForumService.getEntity(EmkOutForumEntity.class, emkOutForum.getId());
        req.setAttribute("emkOutForum", emkOutForum);
        return new ModelAndView("com/emk/produce/outforum/time");

    }
}
