package com.emk.storage.material.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity2;
import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import com.emk.storage.instorage.entity.EmkInStorageEntity;
import com.emk.storage.material.entity.EmkMaterialEntity;
import com.emk.storage.material.service.EmkMaterialServiceI;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.util.*;
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

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 order by company_code asc ");
        request.setAttribute("gysList", list);
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=0", map.get("sampleId"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/storage/material/orderMxList");
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
        emkMaterialService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkMaterialEntity emkMaterial, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMaterial =  systemService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
        message = "原料面料需求开发单删除成功";
        try {
            emkMaterialService.delete(emkMaterial);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
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
                EmkMaterialEntity emkMaterial =  systemService.getEntity(EmkMaterialEntity.class, id);
                WebFileUtils.delete( request.getRealPath("/")+emkMaterial.getCustomSampleUrl());
                systemService.executeSql("delete from emk_sample_detail where sample_id = ?",id);

                EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",id);
                systemService.executeSql("delete from emk_approval where form_id=?",id);
                if(Utils.notEmpty(approvalEntity)){
                    systemService.executeSql("delete from emk_approval_detail where approval_id=?",approvalEntity.getId());
                }
                emkMaterialService.delete(emkMaterial);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
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
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            emkMaterial.setState("0");
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(MATERIAL_NO, 2)),0)+1 AS signed) orderNum from emk_material");
            emkMaterial.setMaterialNo("SY" + DateUtils.format(new Date(), "yyMMdd") + "B" + String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkMaterialService.save(emkMaterial);

            String dataRows = map.get("orderMxListID");
            //保存原料面料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        emkSampleDetailEntity.setSignTotal(map.get("orderMxList["+i+"].signTotal"));
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setSampleId(emkMaterial.getId());
                        emkSampleDetailEntity.setType("0");

                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
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
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        emkMaterial.setProTypeName(emkMaterial.getProTypeName().replaceAll(",",""));
        emkMaterial.setProType(emkMaterial.getProType().replaceAll(",",""));
        EmkMaterialEntity t =  emkMaterialService.get(EmkMaterialEntity.class, map.get("materailId").toString());
        if(Utils.notEmpty(t.getState())){
            if(!t.getState().equals("0")){
                j.setMsg("原料面料需求开发单已提交，无法进行修改");
                j.setSuccess(false);
                return j;
            }
        }
        try {
            emkMaterial.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterial, t);
            emkMaterialService.saveOrUpdate(t);
            String dataRows = map.get("orderMxListID");
            //保存原料面料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=0",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        emkSampleDetailEntity.setSignTotal(map.get("orderMxList["+i+"].signTotal"));
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("0");

                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }

            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
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
            emkMaterial =  emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
            req.setAttribute("emkMaterialPage", emkMaterial);
        }
        return new ModelAndView("com/emk/storage/material/emkMaterial-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkMaterialEntity emkMaterial, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterial.getId())) {
            emkMaterial =  emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
            req.setAttribute("emkMaterialPage", emkMaterial);
        }
        return new ModelAndView("com/emk/storage/material/emkMaterial-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkMaterialEntity emkMaterial, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterial.getId())) {
            emkMaterial =  emkMaterialService.getEntity(EmkMaterialEntity.class, emkMaterial.getId());
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
        List<EmkMaterialEntity> emkMaterials = emkMaterialService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
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
        List<EmkMaterialEntity> listEmkMaterials = emkMaterialService.getList(EmkMaterialEntity.class);
        return Result.success(listEmkMaterials);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取原料面料需求开发单信息", notes = "根据ID获取原料面料需求开发单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMaterialEntity task =  emkMaterialService.get(EmkMaterialEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取原料面料需求开发单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建原料面料需求开发单")
    public ResponseMessage<?> create(@ApiParam(name = "原料面料需求开发单对象") @RequestBody EmkMaterialEntity emkMaterial, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMaterialEntity>> failures = validator.validate(emkMaterial, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkMaterialService.save(emkMaterial);
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
        Set<ConstraintViolation<EmkMaterialEntity>> failures = validator.validate(emkMaterial, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkMaterialService.saveOrUpdate(emkMaterial);
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
            emkMaterialService.deleteEntityById(EmkMaterialEntity.class, id);
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
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

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
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
                    if(Utils.isEmpty(b)){
                        b = new EmkApprovalEntity();
                        EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
                        ApprovalUtil.saveApproval(b,4,t.getMaterialNo(),t.getId(),user);
                        systemService.save(b);
                        ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"原料面料需求开发申请单","materialTask","提交",user);
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
                        if (task1.getTaskDefinitionKey().equals("materialTask")) {
                            taskService.complete(task1.getId(), variables);
                            t.setState("1");
                            b.setStatus(1);
                            saveApprvoalDetail(approvalDetail,"重新提交的原料面料需求开发申请单","materialTask",0,"重新提交原料面料需求开发申请单");
                            saveSmsAndEmailForMany("业务员","业务员审核","您有【"+b.getCreateName()+"】重新提交的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }
                        if (task1.getTaskDefinitionKey().equals("ywyTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(35);
                                approvalDetail.setBpmName("业务员审核");
                                t.setState("35");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("业务经理","业务员审核","您有【"+user.getRealName()+"】审核过的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务员审核","ywyTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"ywyTask","materialTask","原料面料需求开发申请单");
                                t.setState("0");
                                b.setStatus(0);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(3);
                                approvalDetail.setBpmName("业务经理审核");
                                t.setState("3");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("采购员","业务经理审核","您有【"+user.getRealName()+"】审核过的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务经理审核","checkTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"checkTask","materialTask","原料面料需求开发申请单");
                                systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and act_id_=? ",task1.getProcessInstanceId(),"ywyTask");
                                systemService.executeSql("delete from act_hi_taskinst where proc_inst_id_=? and task_def_key_=? ",task1.getProcessInstanceId(),"ywyTask");
                                t.setState("0");
                                b.setStatus(0);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgyTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(24);
                                approvalDetail.setBpmName("采购员审核");
                                t.setState("24");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("采购经理","采购员审核","您有【"+user.getRealName()+"】审核过的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购员审核","cgyTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cgyTask","checkTask","业务经理审核");
                                systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and act_id_=? ",task1.getProcessInstanceId(),"isPass");

                                t.setState("4");
                                b.setStatus(4);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购员审核","您有【"+user.getRealName()+"】回退的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgjlTask")) {
                            b.setNextBpmSherId(bpmUser.getId());
                            b.setNextBpmSher(bpmUser.getRealName());

                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(15);
                                approvalDetail.setBpmName("采购部经理复核");
                                t.setState("15");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("业务经理","采购部经理复核","您有【"+user.getRealName()+"】审核过的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购部经理复核","cgjlTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cgjlTask","cgyTask","采购员审核");
                                t.setState("36");
                                b.setStatus(36);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购部经理复核","您有【"+user.getRealName()+"】回退的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgyzxTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(37);
                                approvalDetail.setBpmName("采购员执行");
                                t.setState("37");
                                approvalDetail.setApproveStatus(0);
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgyjdTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(38);
                                approvalDetail.setBpmName("采购员进度");
                                t.setState("38");
                                approvalDetail.setApproveStatus(0);
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("rksqTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(39);
                                approvalDetail.setBpmName("入库申请单【采购员】");
                                t.setState("39");

                                EmkMInStorageEntity emkMInStorageEntity = new EmkMInStorageEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t,emkMInStorageEntity);
                                emkMInStorageEntity.setId(null);
                                emkMInStorageEntity.setRkNo("RK"+DateUtil.format(new Date(),"yyyyMMddHHmmss"));
                                emkMInStorageEntity.setType("0");
                                emkMInStorageEntity.setCreateBy(user.getUserName());
                                emkMInStorageEntity.setCreateName(user.getRealName());
                                emkMInStorageEntity.setState("0");
                                emkMInStorageEntity.setFormType("1");
                                emkMInStorageEntity.setAppler(user.getRealName());
                                emkMInStorageEntity.setApplerId(user.getId());
                                emkMInStorageEntity.setRker(map.get("realName"));
                                emkMInStorageEntity.setRkerId(map.get("userName"));
                                emkMInStorageEntity.setCaigouer(user.getRealName());
                                emkMInStorageEntity.setCaigouerName(user.getUserName());
                                emkMInStorageEntity.setMaterialNo(t.getMaterialNo());
                                systemService.save(emkMInStorageEntity);

                                List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=0", t.getId());
                                EmkMInStorageDetailEntity emkMInStorageDetailEntity = null;
                                for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntities){
                                    emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkMInStorageDetailEntity);
                                    emkMInStorageDetailEntity.setId(null);
                                    emkMInStorageDetailEntity.setTotal(sampleDetailEntity.getSumTotal());
                                    emkMInStorageDetailEntity.setInStorageId(emkMInStorageEntity.getId());
                                    systemService.save(emkMInStorageDetailEntity);
                                }

                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgjlshTask")) {
                            if(map.get("isPass").equals("0")) {
                                if(b.getStatus().equals(39)){
                                    variables.put("isType","0");
                                }
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(41);
                                approvalDetail.setBpmName("采购部经理审核");
                                t.setState("41");
                                approvalDetail.setApproveStatus(0);

                                EmkMInStorageEntity emkMInStorageEntity = systemService.findUniqueByProperty(EmkMInStorageEntity.class,"materialNo",t.getMaterialNo());
                                saveSmsAndEmailForOne("采购部经理审核","您有【"+user.getRealName()+"】提交的入库申请单，单号："+emkMInStorageEntity.getRkNo()+"，请及时处理。",bpmUser,user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购部经理审核","rksqTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cgjlshTask","cgjlshTask","采购部经理审核");
                                t.setState("36");
                                b.setStatus(36);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购部经理审核","您有【"+user.getRealName()+"】回退的入库申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        systemService.save(approvalDetail);
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("material", "emkMaterialEntity", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);

                        t.setState("1");
                        b.setStatus(1);
                        b.setBpmStatus("0");
                        b.setProcessName("业务员审核");

                        saveApprvoalDetail(approvalDetail,"提交的原料面料需求开发申请单","materialTask",0,"提交原料面料需求开发申请单");
                        saveSmsAndEmailForMany("业务员","业务员审核","您有【"+b.getCreateName()+"】提交的原料面料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                    }

                    systemService.saveOrUpdate(t);
                    systemService.saveOrUpdate(b);

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
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkMaterialEntity.getId());
        List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
        req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
        req.setAttribute("approvalEntity", approvalEntity);
        req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
        return new ModelAndView("com/emk/storage/material/time");
    }
}
