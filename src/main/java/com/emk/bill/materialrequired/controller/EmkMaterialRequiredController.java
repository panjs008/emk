package com.emk.bill.materialrequired.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.bill.materialcontract.entity.EmkMaterialContractEntity;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bill.materialrequired.service.EmkMaterialRequiredServiceI;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.util.ApprovalUtil;
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

@Api(value = "EmkMaterialRequired", description = "面料采购需求单", tags = "emkMaterialRequiredController")
@Controller
@RequestMapping("/emkMaterialRequiredController")
public class EmkMaterialRequiredController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkMaterialRequiredController.class);
    @Autowired
    private EmkMaterialRequiredServiceI emkMaterialRequiredService;

    @Autowired
    private Validator validator;


    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequiredList");
    }

    @RequestMapping(params = "list2")
    public ModelAndView list2(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequiredList2");
    }

    @RequestMapping(params = "list3")
    public ModelAndView list3(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequiredList3");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", map.get("proOrderId"),map.get("type"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        if(map.get("type").equals("0")){
            return new ModelAndView("com/emk/bill/materialrequired/orderMxList");
        }else if(map.get("type").equals("1")){
            return new ModelAndView("com/emk/bill/materialrequired/orderMxList2");
        }else if(map.get("type").equals("2")){
            return new ModelAndView("com/emk/bill/materialrequired/orderMxList3");
        }
        return new ModelAndView("com/emk/bill/materialrequired/orderMxList");
    }

    
    @RequestMapping(params = "datagrid")
    public void datagrid(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialRequiredEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkMaterialRequired, request.getParameterMap());


        cq.add();
        emkMaterialRequiredService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkMaterialRequired = systemService.getEntity(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
        message = "面料采购需求单删除成功";
        try {
            emkMaterialRequiredService.delete(emkMaterialRequired);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料采购需求单删除失败";
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
        message = "面料采购需求单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkMaterialRequiredEntity emkMaterialRequired = systemService.getEntity(EmkMaterialRequiredEntity.class, id);
                systemService.executeSql("delete from emk_sample_detail where SAMPLE_ID=?", id);

                emkMaterialRequiredService.delete(emkMaterialRequired);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料采购需求单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料采购需求单添加成功";
        try {
            emkMaterialRequiredService.save(emkMaterialRequired);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料采购需求单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "面料采购需求单更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        emkMaterialRequired.setProTypeName(emkMaterialRequired.getProTypeName().replaceAll(",",""));
        emkMaterialRequired.setProType(emkMaterialRequired.getProType().replaceAll(",",""));
        EmkMaterialRequiredEntity t = emkMaterialRequiredService.get(EmkMaterialRequiredEntity.class, map.get("materalRequiredId").toString());
        try {
            if(!t.getState().equals("0") && !t.getState().equals("35")){
                j.setMsg("采购需求单已在处理中，无法修改");
                j.setSuccess(false);
                return j;
            }
            emkMaterialRequired.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkMaterialRequired, t);
            emkMaterialRequiredService.saveOrUpdate(t);

            String dataRows = map.get("orderMxListID");
            if(t.getType().equals("0")){
                dataRows = map.get("orderMxListID");
            }else if(t.getType().equals("1")){
                dataRows = map.get("orderMxListID2");
            }else if(t.getType().equals("2")){
                dataRows = map.get("orderMxListID3");
            }
            //保存面料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=?",t.getId(),t.getType());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
                        emkSampleDetailEntity.setRkState(map.get("orderMxList["+i+"].rkState").toString());
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType(t.getType());
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
          /*  dataRows = map.get("orderMxListID2");
            //保存缝制辅料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=1",t.getId());

                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
                        emkSampleDetailEntity.setRkState(map.get("orderMxList["+i+"].rkState").toString());
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("1");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            dataRows = map.get("orderMxListID3");
            //保存包装辅料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=2",t.getId());

                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].direction"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].betchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].width"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].weight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].chengf"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        }
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        }
                        emkSampleDetailEntity.setSumYongliang(map.get("orderMxList["+i+"].sumYongliang").toString());
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
                        emkSampleDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
                        emkSampleDetailEntity.setRkState(map.get("orderMxList["+i+"].rkState").toString());
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].remark"));

                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }*/
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "面料采购需求单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkMaterialRequiredEntity emkMaterialRequiredEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "采购需求单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

            if ((emkMaterialRequiredEntity.getId() == null) || (emkMaterialRequiredEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialRequiredEntity materialRequiredEntity = systemService.getEntity(EmkMaterialRequiredEntity.class, id);
                    if(Utils.notEmpty(materialRequiredEntity.getState())){
                        if (!materialRequiredEntity.getState().equals("0")) {
                            message = "存在已提交的采购需求单，请重新选择在提交采购需求单！";
                            j.setSuccess(false);
                            flag = 1;
                            break;
                        }
                    }

                }
            }else{
                map.put("ids", emkMaterialRequiredEntity.getId());
            }

            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkMaterialRequiredEntity t = emkMaterialRequiredService.get(EmkMaterialRequiredEntity.class, id);
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
                    if(Utils.isEmpty(b)){
                        //type 工单类型，workNum 单号，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
                        b = new EmkApprovalEntity();
                        EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
                        if(t.getType().equals("0")){
                            ApprovalUtil.saveApproval(b,9,t.getMaterialNo(),t.getId(),user);
                        }else if(t.getType().equals("1")){
                            ApprovalUtil.saveApproval(b,10,t.getMaterialNo(),t.getId(),user);
                        }else if(t.getType().equals("2")){
                            ApprovalUtil.saveApproval(b,11,t.getMaterialNo(),t.getId(),user);
                        }
                        systemService.save(b);
                        ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"采购需求单","orderTask","提交",user);
                        systemService.save(approvalDetailEntity);
                    }
                    t.setState("1");
                    variables.put("inputUser", t.getId());

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

                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("orderTask")) {
                            taskService.complete(task1.getId(), variables);
                            t.setState("1");
                            b.setStatus(1);
                            saveApprvoalDetail(approvalDetail,"重新提交的采购需求单","orderTask",0,"重新提交采购需求单");
                            saveSmsAndEmailForMany("业务员","重新提交的采购需求单","您有【"+b.getCreateName()+"】重新提交的采购需求单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }

                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(35);
                                approvalDetail.setBpmName("业务员审核");
                                t.setState("35");

                                approvalDetail.setApproveStatus(0);

                                bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",map.get("userName").toString());
                                b.setNextBpmSher(map.get("realName"));
                                b.setNextBpmSherId(map.get("userName"));
                                t.setCreateBy(map.get("userName"));
                                t.setCreateName(map.get("realName"));
                                saveSmsAndEmailForOne("业务员审核","您有【"+user.getRealName()+"】审核过的采购需求单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());

                            }else{
                                saveApprvoalDetail(approvalDetail,"业务员审核","checkTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"checkTask","orderTask","采购需求单");
                                t.setState("0");
                                b.setStatus(21);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务员审核","您有【"+user.getRealName()+"】回退的采购需求单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgyjsTask")) {
                            if(map.get("isPass").equals("0")) {
                                EmkMaterialPactEntity materialPactEntity;
                                List<EmkSampleDetailEntity> sampleDetailEntityList = null;
                                try {
                                    materialPactEntity = new EmkMaterialPactEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(t, materialPactEntity);

                                    //生成预采购合同
                                    materialPactEntity.setId(null);
                                    materialPactEntity.setState("0");
                                    materialPactEntity.setPartyA(user.getCurrentDepart().getDepartname());
                                    materialPactEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
                                    materialPactEntity.setCgxqdh(t.getMaterialNo());
                                    materialPactEntity.setMaterialNo(t.getMaterialNo().replace("CGXQ", "YCGHT"));
                                    materialPactEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                    materialPactEntity.setFlag("0");
                                    systemService.save(materialPactEntity);

                                    sampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId=?", t.getId());
                                    if(sampleDetailEntityList != null && sampleDetailEntityList.size() > 0) {
                                        EmkSampleDetailEntity emkSampleDetailEntity = null;
                                        for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                                            emkSampleDetailEntity = new EmkSampleDetailEntity();
                                            MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                            emkSampleDetailEntity.setId(null);
                                            emkSampleDetailEntity.setCgxqdh(t.getMaterialNo());
                                            emkSampleDetailEntity.setSampleId(materialPactEntity.getId());
                                            systemService.save(emkSampleDetailEntity);
                                        }
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(44);
                                approvalDetail.setBpmName("采购员接收");
                                t.setState("44");
                                approvalDetail.setApproveStatus(0);
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购员接收","checkTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cgyjsTask","checkTask","业务员审核");
                                t.setState("21");
                                b.setStatus(21);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购员接收","您有【"+user.getRealName()+"】回退的采购需求单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("cgbjlshTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(15);
                                approvalDetail.setBpmName("采购部经理审核");
                                t.setState("15");
                                approvalDetail.setApproveStatus(0);
                                bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());
                                saveSmsAndEmailForOne("采购部经理审核","您有【"+user.getRealName()+"】审核过的采购需求单正式购销合同，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购部经理审核","cgbjlshTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cgbjlshTask","htTask","采购员接收");
                                t.setState("36");
                                b.setStatus(36);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购部经理审核","您有【"+user.getRealName()+"】回退的采购需求单正式购销合同，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("cgyzxTask")) {
                            if (map.get("isPass").equals("0")) {
                                EmkMaterialContractEntity materialContractEntity;
                                List<EmkSampleDetailEntity> sampleDetailEntityList = null;
                                try {
                                    materialContractEntity = new EmkMaterialContractEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(t, materialContractEntity);

                                    //生成开发费付款申请单
                                    materialContractEntity.setId(null);
                                    materialContractEntity.setCreateBy(map.get("userName"));
                                    materialContractEntity.setCreateName(map.get("realName"));

                                    materialContractEntity.setState("0");
                                    Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(pay_no, 6)),0)+1 AS signed) orderNum from emk_material_contract");
                                    materialContractEntity.setPayNo("P" + DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", Integer.parseInt(orderNum.get("orderNum").toString())));
                                    materialContractEntity.setFormType("1");
                                    materialContractEntity.setCaigouNo(t.getMaterialNo());
                                    materialContractEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                    EmkSampleRequiredEntity emkSampleRequiredEntity = systemService.findUniqueByProperty(EmkSampleRequiredEntity.class,"orderNo",t.getOrderNum());
                                    if(Utils.notEmpty(emkSampleRequiredEntity)){
                                        materialContractEntity.setDyxqdNo(emkSampleRequiredEntity.getRequiredNo());
                                        EmkSampleEntity emkSampleEntity = systemService.findUniqueByProperty(EmkSampleEntity.class,"xqdh",emkSampleRequiredEntity.getRequiredNo());
                                        if(Utils.notEmpty(emkSampleEntity)){
                                            materialContractEntity.setDytzdNo(emkSampleEntity.getSampleNum());
                                        }
                                    }

                                    systemService.save(materialContractEntity);

                                    Map m = systemService.findOneForJdbc("SELECT DISTINCT sample_id  FROM emk_sample_detail t WHERE t.cgxqdh=?" ,t.getMaterialNo());
                                    if(Utils.notEmpty(m)){
                                        sampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId=?",m.get("sample_id").toString());
                                        if(sampleDetailEntityList != null && sampleDetailEntityList.size() > 0) {
                                            EmkSampleDetailEntity emkSampleDetailEntity = null;
                                            for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                                                emkSampleDetailEntity = new EmkSampleDetailEntity();
                                                MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                                emkSampleDetailEntity.setId(null);
                                                emkSampleDetailEntity.setSampleId(materialContractEntity.getId());
                                                systemService.save(emkSampleDetailEntity);
                                            }
                                        }
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                taskService.complete(task1.getId(), variables);
                                b.setStatus(15);
                                approvalDetail.setBpmName("采购员执行");
                                t.setState("15");
                                approvalDetail.setApproveStatus(0);
                                bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());
                                saveSmsAndEmailForOne("采购员执行","您有【"+user.getRealName()+"】审核过的采购需求单正式购销合同，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        systemService.save(approvalDetail);
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("order", "emkMaterialRequired", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);

                        saveSmsAndEmailForMany("业务员","采购需求","您有【"+b.getCreateName()+"】提交的采购需求单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        t.setState("1");
                        b.setStatus(1);
                        b.setBpmStatus("0");
                        b.setProcessName("采购需求");

                    }
                    systemService.saveOrUpdate(t);
                    systemService.saveOrUpdate(b);
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "采购需求单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }



    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialRequired.getId())) {
            emkMaterialRequired = emkMaterialRequiredService.getEntity(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
            req.setAttribute("emkMaterialRequiredPage", emkMaterialRequired);

        }
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequired-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest req, DataGrid dataGrid) {
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkMaterialRequired.getId());
        if(Utils.notEmpty(approvalEntity)){
            List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
            req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
            req.setAttribute("approvalEntity", approvalEntity);
            req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
        }
        return new ModelAndView("com/emk/bill/materialrequired/time");
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest req) {
        TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");
        Map orderNum = systemService.findOneForJdbc("select count(0)+1 orderNum from emk_material_required where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
        req.setAttribute("materialNo","CG"+ DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
        if (StringUtil.isNotEmpty(emkMaterialRequired.getId())) {
            emkMaterialRequired = emkMaterialRequiredService.getEntity(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
            req.setAttribute("emkMaterialRequiredPage", emkMaterialRequired);
        }
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequired-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialRequired.getId())) {
            emkMaterialRequired = emkMaterialRequiredService.getEntity(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
            req.setAttribute("emkMaterialRequiredPage", emkMaterialRequired);

            if(emkMaterialRequired.getType().equals("0")){
                req.setAttribute("pactTypeName", "原料面料");
            }else if(emkMaterialRequired.getType().equals("1")){
                req.setAttribute("pactTypeName", "缝制辅料");
            }else if(emkMaterialRequired.getType().equals("2")){
                req.setAttribute("pactTypeName", "包装辅料");
            }
        }
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequired-update");
    }
    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkMaterialRequired.getId())) {
            emkMaterialRequired = emkMaterialRequiredService.getEntity(EmkMaterialRequiredEntity.class, emkMaterialRequired.getId());
            req.setAttribute("emkMaterialRequiredPage", emkMaterialRequired);

            if(emkMaterialRequired.getType().equals("0")){
                req.setAttribute("pactTypeName", "原料面料");
            }else if(emkMaterialRequired.getType().equals("1")){
                req.setAttribute("pactTypeName", "缝制辅料");
            }else if(emkMaterialRequired.getType().equals("2")){
                req.setAttribute("pactTypeName", "包装辅料");
            }
        }
        return new ModelAndView("com/emk/bill/materialrequired/emkMaterialRequired-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkMaterialRequiredController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkMaterialRequiredEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkMaterialRequired, request.getParameterMap());
        List<EmkMaterialRequiredEntity> emkMaterialRequireds = emkMaterialRequiredService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "面料采购需求单");
        modelMap.put("entity", EmkMaterialRequiredEntity.class);
        modelMap.put("params", new ExportParams("面料采购需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkMaterialRequireds);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkMaterialRequiredEntity emkMaterialRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "面料采购需求单");
        modelMap.put("entity", EmkMaterialRequiredEntity.class);
        modelMap.put("params", new ExportParams("面料采购需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "面料采购需求单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkMaterialRequiredEntity>> list() {
        List<EmkMaterialRequiredEntity> listEmkMaterialRequireds = emkMaterialRequiredService.getList(EmkMaterialRequiredEntity.class);
        return Result.success(listEmkMaterialRequireds);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取面料采购需求单信息", notes = "根据ID获取面料采购需求单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkMaterialRequiredEntity task = emkMaterialRequiredService.get(EmkMaterialRequiredEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取面料采购需求单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建面料采购需求单")
    public ResponseMessage<?> create(@ApiParam(name = "面料采购需求单对象") @RequestBody EmkMaterialRequiredEntity emkMaterialRequired, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkMaterialRequiredEntity>> failures = validator.validate(emkMaterialRequired, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkMaterialRequiredService.save(emkMaterialRequired);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("面料采购需求单信息保存失败");
        }
        return Result.success(emkMaterialRequired);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新面料采购需求单", notes = "更新面料采购需求单")
    public ResponseMessage<?> update(@ApiParam(name = "面料采购需求单对象") @RequestBody EmkMaterialRequiredEntity emkMaterialRequired) {
        Set<ConstraintViolation<EmkMaterialRequiredEntity>> failures = validator.validate(emkMaterialRequired, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkMaterialRequiredService.saveOrUpdate(emkMaterialRequired);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新面料采购需求单信息失败");
        }
        return Result.success("更新面料采购需求单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除面料采购需求单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkMaterialRequiredService.deleteEntityById(EmkMaterialRequiredEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("面料采购需求单删除失败");
        }
        return Result.success();
    }
}
