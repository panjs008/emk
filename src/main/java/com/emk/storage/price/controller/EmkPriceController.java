package com.emk.storage.price.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.price.entity.EmkPriceEntity2;
import com.emk.storage.price.service.EmkPriceServiceI;
import com.emk.util.FlowUtil;
import com.emk.util.ParameterUtil;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
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

@Api(value = "EmkPrice", description = "报价单", tags = "emkPriceController")
@Controller
@RequestMapping("/emkPriceController")
public class EmkPriceController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkPriceController.class);
    @Autowired
    private EmkPriceServiceI emkPriceService;
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
        return new ModelAndView("com/emk/storage/price/emkPriceList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkPriceEntity emkPrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkPriceEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkPrice, request.getParameterMap());


        cq.add();
        this.emkPriceService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkPriceEntity emkPrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkPrice = (EmkPriceEntity) this.systemService.getEntity(EmkPriceEntity.class, emkPrice.getId());
        message = "报价单删除成功";
        try {
            this.emkPriceService.delete(emkPrice);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单删除失败";
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
        message = "报价单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkPriceEntity emkPrice = (EmkPriceEntity) this.systemService.getEntity(EmkPriceEntity.class, id);


                this.emkPriceService.delete(emkPrice);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkPriceEntity2 emkPrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价单添加成功";
        try {
            EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"askNo",emkPrice.getXpNo());
            if(workOrderEntity == null){
                j.setSuccess(false);
                j.setMsg("您输入的询盘单号有误，请核准后在提交");
                return j;
            }
            emkPrice.setState("0");
            emkPrice.setPirceNo(emkPrice.getSampleNo() + DateUtils.format(new Date(), "yyMMdd"));
            this.emkPriceService.save(emkPrice);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkPriceEntity2 emkPrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价单更新成功";
        EmkPriceEntity t = (EmkPriceEntity) this.emkPriceService.get(EmkPriceEntity.class, emkPrice.getId());
        try {
            if (!t.getState().equals("0")) {
                message = "存在已提交的报价单，请重新选择在提交！";
                j.setMsg(message);
                j.setSuccess(false);
                return  j;
            }
            MyBeanUtils.copyBeanNotNull2Bean(emkPrice, t);
            this.emkPriceService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkPriceEntity emkPrice, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = (EmkPriceEntity) this.emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkPriceEntity emkPrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = (EmkPriceEntity) this.emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);

            Map sumYlCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","0",emkPrice.getId());
            Map sumFzCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","1",emkPrice.getId());
            Map sumBzCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","2",emkPrice.getId());
            Map sumRgCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_gx where sample_id=?",emkPrice.getId());
            Map sumRanCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_ran where sample_id=?",emkPrice.getId());
            Map sumYinCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_yin where sample_id=?",emkPrice.getId());

            emkPrice.setSumYl(Double.parseDouble(sumYlCb.get("sumCb").toString()));
            emkPrice.setSumFeng(Double.parseDouble(sumFzCb.get("sumCb").toString()));
            emkPrice.setSumBao(Double.parseDouble(sumBzCb.get("sumCb").toString()));
            emkPrice.setSumRg(Double.parseDouble(sumRgCb.get("sumCb").toString()));
            emkPrice.setSumRan(Double.parseDouble(sumRanCb.get("sumCb").toString()));
            emkPrice.setSumYin(Double.parseDouble(sumYinCb.get("sumCb").toString()));

            double tax = Double.parseDouble(sumYlCb.get("sumCb").toString())+Double.parseDouble(sumFzCb.get("sumCb").toString())+Double.parseDouble(sumBzCb.get("sumCb").toString())+Double.parseDouble(sumRgCb.get("sumCb").toString())+Double.parseDouble(sumRanCb.get("sumCb").toString())+Double.parseDouble(sumYinCb.get("sumCb").toString());
            if(emkPrice.getTestMoney() != null){
                tax += emkPrice.getTestMoney();
            }
            if(emkPrice.getGlMoney() != null){
                tax += emkPrice.getGlMoney();
            }
            if(emkPrice.getUnableMoney() != null){
                tax += emkPrice.getUnableMoney();
            }
            BigDecimal b = new BigDecimal(tax);
            emkPrice.setSumMoney(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            tax = tax * 0.17;
            b = new BigDecimal(tax);
            double dTax = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            emkPrice.setTax(dTax);

            b = new BigDecimal(emkPrice.getSumMoney()-emkPrice.getTax());
            emkPrice.setProfit(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(emkPrice.getHuilv() != null){
                b = new BigDecimal(emkPrice.getSumMoney()/emkPrice.getHuilv());
                emkPrice.setSumWb(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkPriceEntity emkPrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = (EmkPriceEntity) this.emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);

            Map sumYlCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","0",emkPrice.getId());
            Map sumFzCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","1",emkPrice.getId());
            Map sumBzCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","2",emkPrice.getId());
            Map sumRgCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_gx where sample_id=?",emkPrice.getId());
            Map sumRanCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_ran where sample_id=?",emkPrice.getId());
            Map sumYinCb = this.systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_yin where sample_id=?",emkPrice.getId());

            emkPrice.setSumYl(Double.parseDouble(sumYlCb.get("sumCb").toString()));
            emkPrice.setSumFeng(Double.parseDouble(sumFzCb.get("sumCb").toString()));
            emkPrice.setSumBao(Double.parseDouble(sumBzCb.get("sumCb").toString()));
            emkPrice.setSumRg(Double.parseDouble(sumRgCb.get("sumCb").toString()));
            emkPrice.setSumRan(Double.parseDouble(sumRanCb.get("sumCb").toString()));
            emkPrice.setSumYin(Double.parseDouble(sumYinCb.get("sumCb").toString()));

            double tax = Double.parseDouble(sumYlCb.get("sumCb").toString())+Double.parseDouble(sumFzCb.get("sumCb").toString())+Double.parseDouble(sumBzCb.get("sumCb").toString())+Double.parseDouble(sumRgCb.get("sumCb").toString())+Double.parseDouble(sumRanCb.get("sumCb").toString())+Double.parseDouble(sumYinCb.get("sumCb").toString());
            if(emkPrice.getTestMoney() != null){
                tax += emkPrice.getTestMoney();
            }
            if(emkPrice.getGlMoney() != null){
                tax += emkPrice.getGlMoney();
            }
            if(emkPrice.getUnableMoney() != null){
                tax += emkPrice.getUnableMoney();
            }
            BigDecimal b = new BigDecimal(tax);
            emkPrice.setSumMoney(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            tax = tax * 0.17;
            b = new BigDecimal(tax);
            double dTax = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            emkPrice.setTax(dTax);

            b = new BigDecimal(emkPrice.getSumMoney()-emkPrice.getTax());
            emkPrice.setProfit(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(emkPrice.getHuilv() != null){
                b = new BigDecimal(emkPrice.getSumMoney()/emkPrice.getHuilv());
                emkPrice.setSumWb(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkPriceController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkPriceEntity emkPrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkPriceEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkPrice, request.getParameterMap());
        List<EmkPriceEntity> emkPrices = this.emkPriceService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "报价单");
        modelMap.put("entity", EmkPriceEntity.class);
        modelMap.put("params", new ExportParams("报价单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkPrices);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkPriceEntity emkPrice, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "报价单");
        modelMap.put("entity", EmkPriceEntity.class);
        modelMap.put("params", new ExportParams("报价单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "报价单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkPriceEntity>> list() {
        List<EmkPriceEntity> listEmkPrices = this.emkPriceService.getList(EmkPriceEntity.class);
        return Result.success(listEmkPrices);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取报价单信息", notes = "根据ID获取报价单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkPriceEntity task = (EmkPriceEntity) this.emkPriceService.get(EmkPriceEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取报价单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建报价单")
    public ResponseMessage<?> create(@ApiParam(name = "报价单对象") @RequestBody EmkPriceEntity emkPrice, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkPriceEntity>> failures = this.validator.validate(emkPrice, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkPriceService.save(emkPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("报价单信息保存失败");
        }
        return Result.success(emkPrice);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新报价单", notes = "更新报价单")
    public ResponseMessage<?> update(@ApiParam(name = "报价单对象") @RequestBody EmkPriceEntity emkPrice) {
        Set<ConstraintViolation<EmkPriceEntity>> failures = this.validator.validate(emkPrice, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkPriceService.saveOrUpdate(emkPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新报价单信息失败");
        }
        return Result.success("更新报价单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除报价单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkPriceService.deleteEntityById(EmkPriceEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("报价单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkPriceEntity emkPrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkPrice.getId() == null) || (emkPrice.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkPriceEntity priceEntity = systemService.getEntity(EmkPriceEntity.class, id);
                    if (!priceEntity.getState().equals("0")) {
                        message = "存在已提交的报价单，请重新选择在提交报价单！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkPrice.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkPriceEntity t = emkPriceService.get(EmkPriceEntity.class, id);
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
                            t.setLeadAdvice(emkPrice.getLeadAdvice());

                            t.setJser(map.get("realName").toString());
                            t.setJsUserId(map.get("userName").toString());
                            if (emkPrice.getIsPass().equals("0")) {
                                variables.put("isPass", emkPrice.getIsPass());
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
                        if (task1.getTaskDefinitionKey().equals("jsTask")) {
                            t.setJsAdvice(emkPrice.getLeadAdvice());

                            t.setCger(map.get("realName").toString());
                            t.setCgUserId(map.get("userName").toString());
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("cgTask")) {
                            t.setCgAdvice(emkPrice.getLeadAdvice());

                            t.setCwer(map.get("realName").toString());
                            t.setCwUserId(map.get("userName").toString());
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("cwTask")) {
                            t.setCwAdvice(emkPrice.getLeadAdvice());

                            t.setJger(t.getCreateName());
                            t.setJgUserId(t.getCreateBy());
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("usertask1")) {
                            t.setJgAdvice(emkPrice.getLeadAdvice());
                            t.setState("2");
                            taskService.complete(task1.getId(), variables);
                        }
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("price", "emkPriceEntity", variables);
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
            message = "报价单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkPriceEntity emkPrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPrice", emkPrice);
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkPriceEntity emkPrice, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='instorageTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_price t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

        countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='" + map.get("id") + "' ";
        sql += " order by t1.START_TIME_ desc";

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
        emkPrice = emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
        req.setAttribute("emkPrice", emkPrice);
        return new ModelAndView("com/emk/storage/price/time");
    }
}
