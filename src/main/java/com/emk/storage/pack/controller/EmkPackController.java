package com.emk.storage.pack.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.storage.material.entity.EmkMaterialEntity;
import com.emk.storage.pack.entity.EmkPackEntity;
import com.emk.storage.pack.service.EmkPackServiceI;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.util.ApprovalUtil;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
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

@Api(value = "EmkPack", description = "包装辅料需求开发单", tags = "emkPackController")
@Controller
@RequestMapping("/emkPackController")
public class EmkPackController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkPackController.class);
    @Autowired
    private EmkPackServiceI emkPackService;
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
        return new ModelAndView("com/emk/storage/pack/emkPackList");
    }

    @RequestMapping(params = "orderMxList3")
    public ModelAndView orderMxList3(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=2", map.get("sampleId"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/storage/pack/orderMxList3");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkPackEntity emkPack, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkPackEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkPack, request.getParameterMap());


        cq.add();
        emkPackService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkPackEntity emkPack, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkPack = systemService.getEntity(EmkPackEntity.class, emkPack.getId());
        message = "包装辅料需求开发单删除成功";
        try {
            emkPackService.delete(emkPack);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "包装辅料需求开发单删除失败";
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
        message = "包装辅料需求开发单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkPackEntity emkPack = systemService.getEntity(EmkPackEntity.class, id);


                emkPackService.delete(emkPack);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "包装辅料需求开发单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkPackEntity emkPack, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "包装辅料需求开发单添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            emkPack.setState("0");
            emkPack.setParkNo(emkPack.getSampleNo() + DateUtils.format(new Date(), "yyMMdd"));
            emkPackService.save(emkPack);

            String dataRows = (String)map.get("orderMxListID3");
            //保存包装辅料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].cproZnName"))) {
                        emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].cproZnName"));
                        emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].cproNum"));
                        emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].cgysCode"));
                        emkSampleDetailEntity.setBrand((String)map.get("orderMxList["+i+"].cbrand"));
                        emkSampleDetailEntity.setChengf((String)map.get("orderMxList["+i+"].cchengf"));
                        emkSampleDetailEntity.setWeight((String)map.get("orderMxList["+i+"].cweight"));
                        emkSampleDetailEntity.setSignTotal((String)map.get("orderMxList["+i+"].csignTotal"));
                        emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].csignPrice"));

                        emkSampleDetailEntity.setSampleId(emkPack.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "包装辅料需求开发单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkPackEntity emkPack, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "包装辅料需求开发单更新成功";
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        EmkPackEntity t = emkPackService.get(EmkPackEntity.class,  map.get("materailId").toString());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkPack, t);
            emkPackService.saveOrUpdate(t);

            String dataRows = (String)map.get("orderMxListID3");
            //保存包装辅料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=2",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].cproZnName"))) {
                        emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].cproZnName"));
                        emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].cproNum"));
                        emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].cgysCode"));
                        emkSampleDetailEntity.setBrand((String)map.get("orderMxList["+i+"].cbrand"));
                        emkSampleDetailEntity.setChengf((String)map.get("orderMxList["+i+"].cchengf"));
                        emkSampleDetailEntity.setWeight((String)map.get("orderMxList["+i+"].cweight"));
                        emkSampleDetailEntity.setSignTotal((String)map.get("orderMxList["+i+"].csignTotal"));
                        emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].csignPrice"));
                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "包装辅料需求开发单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkPackEntity emkPack, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkPack.getId())) {
            emkPack = emkPackService.getEntity(EmkPackEntity.class, emkPack.getId());
            req.setAttribute("emkPackPage", emkPack);
        }
        return new ModelAndView("com/emk/storage/pack/emkPack-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkPackEntity emkPack, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPack.getId())) {
            emkPack = emkPackService.getEntity(EmkPackEntity.class, emkPack.getId());
            req.setAttribute("emkPackPage", emkPack);
        }
        return new ModelAndView("com/emk/storage/pack/emkPack-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkPackEntity emkPack, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPack.getId())) {
            emkPack = emkPackService.getEntity(EmkPackEntity.class, emkPack.getId());
            req.setAttribute("emkPackPage", emkPack);
        }
        return new ModelAndView("com/emk/storage/pack/emkPack-update2");
    }


    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkPackController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkPackEntity emkPack, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkPackEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkPack, request.getParameterMap());
        List<EmkPackEntity> emkPacks = emkPackService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "包装辅料需求开发单");
        modelMap.put("entity", EmkPackEntity.class);
        modelMap.put("params", new ExportParams("包装辅料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkPacks);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkPackEntity emkPack, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "包装辅料需求开发单");
        modelMap.put("entity", EmkPackEntity.class);
        modelMap.put("params", new ExportParams("包装辅料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "包装辅料需求开发单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkPackEntity>> list() {
        List<EmkPackEntity> listEmkPacks = emkPackService.getList(EmkPackEntity.class);
        return Result.success(listEmkPacks);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取包装辅料需求开发单信息", notes = "根据ID获取包装辅料需求开发单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkPackEntity task = emkPackService.get(EmkPackEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取包装辅料需求开发单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建包装辅料需求开发单")
    public ResponseMessage<?> create(@ApiParam(name = "包装辅料需求开发单对象") @RequestBody EmkPackEntity emkPack, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkPackEntity>> failures = validator.validate(emkPack, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkPackService.save(emkPack);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("包装辅料需求开发单信息保存失败");
        }
        return Result.success(emkPack);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新包装辅料需求开发单", notes = "更新包装辅料需求开发单")
    public ResponseMessage<?> update(@ApiParam(name = "包装辅料需求开发单对象") @RequestBody EmkPackEntity emkPack) {
        Set<ConstraintViolation<EmkPackEntity>> failures = validator.validate(emkPack, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkPackService.saveOrUpdate(emkPack);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新包装辅料需求开发单信息失败");
        }
        return Result.success("更新包装辅料需求开发单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除包装辅料需求开发单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkPackService.deleteEntityById(EmkPackEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("包装辅料需求开发单删除失败");
        }
        return Result.success();
    }


    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkPackEntity emkPackEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "包装辅料需求开发单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

            if(emkPackEntity.getId() == null || emkPackEntity.getId().isEmpty()) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkPackEntity packEntity = systemService.getEntity(EmkPackEntity.class, id);
                    if (!packEntity.getState().equals("0")) {
                        message = "存在已提交的包装辅料需求开发单，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkPackEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkPackEntity t = emkPackService.get(EmkPackEntity.class, id);
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
                    if(Utils.isEmpty(b)){
                        b = new EmkApprovalEntity();
                        EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
                        ApprovalUtil.saveApproval(b,4,t.getParkNo(),t.getId(),user);
                        systemService.save(b);
                        ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"包装辅料需求开发申请单","packTask","提交",user);
                        systemService.save(approvalDetailEntity);
                    }

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    TSUser makerUser = systemService.findUniqueByProperty(TSUser.class,"userName",t.getCreateBy());
                    TSUser bpmUser = null;
                    if (task.size() > 0) {
                        bpmUser = systemService.get(TSUser.class,b.getBpmSherId());
                    }else{
                        bpmUser = systemService.get(TSUser.class,b.getCommitId());
                    }
                    //保存审批意见
                    EmkApprovalDetailEntity approvalDetail = ApprovalUtil.saveApprovalDetail(b.getId(),user,b,map.get("advice"));

                    t.setState("1");
                    variables.put("optUser", t.getId());
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("checkfactoryTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                           /* t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkPackEntity.getLeadAdvice());
                            if (emkPackEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkPackEntity.getIsPass());
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
                            }*/

                        }

                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("pack", "emkPackEntity", variables);
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
            message = "包装辅料需求开发单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkPackEntity emkPackEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPackEntity.getId())) {
            emkPackEntity = emkPackService.getEntity(EmkPackEntity.class, emkPackEntity.getId());
            req.setAttribute("emkPack", emkPackEntity);
        }
        return new ModelAndView("com/emk/storage/pack/emkPack-work");
    }
    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkPackEntity emkPackEntity, HttpServletRequest req, DataGrid dataGrid) {
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkPackEntity.getId());
        List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
        req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
        req.setAttribute("approvalEntity", approvalEntity);
        req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
        return new ModelAndView("com/emk/storage/pack/time");
    }
}
