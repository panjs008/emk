package com.emk.storage.enquiry.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntityA;
import com.emk.storage.enquiry.service.EmkEnquiryServiceI;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.sampleprice.entity.EmkSamplePriceEntity;
import com.emk.util.*;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
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
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
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
    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        request.setAttribute("colorList", list);
        list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='colorNum'");
        request.setAttribute("colorNumList", list);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("proOrderId"))) {
            StringBuilder sql = new StringBuilder();
            sql.append("select t.id,t.enquiry_id enquiryId,t.color,t.color_value colorVal,t.size,\n" +
                    " (select t1.total from emk_enquiry_detail t1 where t1.enquiry_id=t.enquiry_id and t1.color=t.color and t1.color_value=t.color_value and t1.size='s' limit 0,1) stotal,\n" +
                    " (select t2.total from emk_enquiry_detail t2 where t2.enquiry_id=t.enquiry_id and t2.color=t.color and t2.color_value=t.color_value and t2.size='m' limit 0,1) mtotal,\n" +
                    " (select t3.total from emk_enquiry_detail t3 where t3.enquiry_id=t.enquiry_id and t3.color=t.color and t3.color_value=t.color_value and t3.size='l' limit 0,1) ltotal,\n" +
                    " (select t4.total from emk_enquiry_detail t4 where t4.enquiry_id=t.enquiry_id and t4.color=t.color and t4.color_value=t.color_value and t4.size='xl' limit 0,1) xltotal,\n" +
                    " (select t5.total from emk_enquiry_detail t5 where t5.enquiry_id=t.enquiry_id and t5.color=t.color and t5.color_value=t.color_value and t5.size='2xl' limit 0,1) xxltotal\n" +
                    " from emk_enquiry_detail t where t.enquiry_id=? group by t.enquiry_id,t.color,t.color_value  order by t.sort_desc asc \n");
            List<Map<String, Object>> emkProOrderDetailEntities = systemService.findForJdbc(sql.toString(),map.get("proOrderId"));
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/storage/enquiry/orderMxList");
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
        emkEnquiryService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkEnquiryEntity emkEnquiry, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkEnquiry = systemService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
        message = "询盘单删除成功";
        try {
            emkEnquiryService.delete(emkEnquiry);
            WebFileUtils.delete( request.getRealPath("/")+emkEnquiry.getDgrImageUrl());
            WebFileUtils.delete( request.getRealPath("/")+emkEnquiry.getCustomSampleUrl());
            WebFileUtils.delete( request.getRealPath("/")+emkEnquiry.getOldImageUrl());
            WebFileUtils.delete( request.getRealPath("/")+emkEnquiry.getSizeImageUrl());
            EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkEnquiry.getId());

            systemService.executeSql("delete from emk_enquiry_detail where enquiry_id=?",emkEnquiry.getId());
            systemService.executeSql("delete from emk_approval where form_id=?",emkEnquiry.getId());
            systemService.executeSql("delete from emk_approval_detail where approval_id=?",approvalEntity.getId());

            emkEnquiryService.delete(emkEnquiry);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
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
                    EmkEnquiryEntity emkEnquiry = systemService.getEntity(EmkEnquiryEntity.class, id);
                    WebFileUtils.delete( request.getRealPath("/")+emkEnquiry.getDgrImageUrl());
                    WebFileUtils.delete( request.getRealPath("/")+emkEnquiry.getCustomSampleUrl());
                    WebFileUtils.delete( request.getRealPath("/")+emkEnquiry.getOldImageUrl());
                    WebFileUtils.delete( request.getRealPath("/")+emkEnquiry.getSizeImageUrl());
                    EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkEnquiry.getId());

                    systemService.executeSql("delete from emk_enquiry_detail where enquiry_id=?",emkEnquiry.getId());
                    systemService.executeSql("delete from emk_approval where form_id=?",emkEnquiry.getId());
                    systemService.executeSql("delete from emk_approval_detail where approval_id=?",approvalEntity.getId());

                    emkEnquiryService.delete(emkEnquiry);
                    systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
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
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(enquiry_no, 3)),0)+1 AS signed) orderNum from emk_enquiry");

            emkEnquiry.setEnquiryNo("YXDD" + emkEnquiry.getCusNum() + DateUtil.format(new Date(), "yyMMdd") + String.format("%03d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkEnquiryService.save(emkEnquiry);
            //type 工单类型，workNum 单号，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
            EmkApprovalEntity approvalEntity = new EmkApprovalEntity();
            EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();

            ApprovalUtil.saveApproval(approvalEntity,0,emkEnquiry.getEnquiryNo(),emkEnquiry.getId(),user);
            systemService.save(approvalEntity);

            ApprovalUtil.saveApprovalDetail(approvalDetailEntity,approvalEntity.getId(),"意向询盘单","instorageTask","提交",user);
            systemService.save(approvalDetailEntity);
            /*EmkApprovalEntity approvalEntity = new EmkApprovalEntity();
            approvalEntity.setType(0);
            approvalEntity.setCommitTime(DateUtil.getCurrentTimeString(null));
            approvalEntity.setCommitId(user.getId());
            approvalEntity.setStatus(0);
            approvalEntity.setWorkNum(emkEnquiry.getEnquiryNo());
            approvalEntity.setFormId(emkEnquiry.getId());
            systemService.save(approvalEntity);

            EmkApprovalDetailEntity approvalDetail = new EmkApprovalDetailEntity();
            approvalDetail.setApprovalId(approvalEntity.getId());
            approvalDetail.setApproveAdvice("提交");
            approvalDetail.setApproveUserId(user.getId());
            approvalDetail.setBpmName("意向询盘单");
            approvalDetail.setBpmNode("instorageTask");
            approvalDetail.setApproveDate(DateUtil.getCurrentTimeString(null));
            systemService.save(approvalDetail);*/

            String dataRows = (String) map.get("dataRowsVal");
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                EmkEnquiryDetailEntity orderMxEntity = null;
                EmkEnquiryDetailEntity emkEnquiryDetailEntity = null;
                for (int i = 0; i < rows; i++) {
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].color"))){
                        orderMxEntity = new EmkEnquiryDetailEntity();
                        orderMxEntity.setEnquiryId(emkEnquiry.getId());
                        orderMxEntity.setColor(map.get("orderMxList["+i+"].color").toString());
                        orderMxEntity.setSortDesc(String.valueOf(i+1));
                        orderMxEntity.setColorValue(map.get("orderMxList["+i+"].colorNum").toString());
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("S");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal01"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("M");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal01").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal02"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("L");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal02").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal03"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("XL");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal03").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal04"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("2XL");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal04").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                    }
                }
            }

            EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
                samplePriceEntity.setBz(map.get("pbz").toString());
                samplePriceEntity.setEnquiryId(emkEnquiry.getId());
                samplePriceEntity.setState(map.get("pstate").toString());
                systemService.save(samplePriceEntity);
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
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
        EmkEnquiryEntity t = emkEnquiryService.get(EmkEnquiryEntity.class, emkEnquiry.getId());
        try {
            if(!t.getState().equals("0")){
                j.setMsg("询盘单已提交，无法进行修改");
                j.setSuccess(false);
                return j;
            }
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            emkEnquiry.setState("0");
            MyBeanUtils.copyBeanNotNull2Bean(emkEnquiry, t);
            emkEnquiryService.saveOrUpdate(t);

            String dataRows = (String) map.get("dataRowsVal");
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_enquiry_detail where enquiry_id=?",t.getId());
                int rows = Integer.parseInt(dataRows);
                EmkEnquiryDetailEntity orderMxEntity = null;
                EmkEnquiryDetailEntity emkEnquiryDetailEntity = null;
                for (int i = 0; i < rows; i++) {
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].color"))){
                        orderMxEntity = new EmkEnquiryDetailEntity();
                        orderMxEntity.setEnquiryId(emkEnquiry.getId());
                        orderMxEntity.setSortDesc(String.valueOf(i+1));
                        orderMxEntity.setColor(map.get("orderMxList["+i+"].color").toString());
                        orderMxEntity.setColorValue(map.get("orderMxList["+i+"].colorNum").toString());
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("S");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal01"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("M");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal01").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal02"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("L");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal02").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal03"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("XL");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal03").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal04"))){
                            emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(orderMxEntity,emkEnquiryDetailEntity);
                            emkEnquiryDetailEntity.setSize("2XL");
                            emkEnquiryDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal04").toString()));
                            systemService.save(emkEnquiryDetailEntity);
                        }
                    }
                }
            }

            systemService.executeSql("delete from emk_sample_price where ENQUIRY_ID=?", emkEnquiry.getId());
            EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
                samplePriceEntity.setBz(map.get("pbz").toString());
                samplePriceEntity.setEnquiryId(emkEnquiry.getId());
                samplePriceEntity.setState(map.get("pstate").toString());
                systemService.save(samplePriceEntity);
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doPrintPDF")
    public String doPrintPDF(String ids,EmkEnquiryEntity emkEnquiry, HttpServletRequest request,HttpServletResponse response) {
        String message = null;

        try {
            for (String id : ids.split(",")) {
                String fileName = "d:\\PDF\\"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
                File file = new File(fileName);
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                emkEnquiry = emkEnquiryService.getEntity(EmkEnquiryEntity.class, id);

                StringBuilder sql = new StringBuilder();
                sql.append("select t.id,t.enquiry_id enquiryId,(select typename from t_s_type a2 left join t_s_typegroup a1 on a1.ID=a2.typegroupid where a1.typegroupcode='color' and a2.typecode=t.color) colorName,t.color_value colorVal,t.size,\n" +
                        " (select t1.total from emk_enquiry_detail t1 where t1.enquiry_id=t.enquiry_id and t1.color=t.color and t1.color_value=t.color_value and t1.size='s' limit 0,1) stotal,\n" +
                        " (select t2.total from emk_enquiry_detail t2 where t2.enquiry_id=t.enquiry_id and t2.color=t.color and t2.color_value=t.color_value and t2.size='m' limit 0,1) mtotal,\n" +
                        " (select t3.total from emk_enquiry_detail t3 where t3.enquiry_id=t.enquiry_id and t3.color=t.color and t3.color_value=t.color_value and t3.size='l' limit 0,1) ltotal,\n" +
                        " (select t4.total from emk_enquiry_detail t4 where t4.enquiry_id=t.enquiry_id and t4.color=t.color and t4.color_value=t.color_value and t4.size='xl' limit 0,1) xltotal,\n" +
                        " (select t5.total from emk_enquiry_detail t5 where t5.enquiry_id=t.enquiry_id and t5.color=t.color and t5.color_value=t.color_value and t5.size='2xl' limit 0,1) xxltotal\n" +
                        " from emk_enquiry_detail t where t.enquiry_id=? group by t.enquiry_id,t.color,t.color_value  order by t.sort_desc asc \n");
                List<Map<String, Object>> emkProOrderDetailEntities = systemService.findForJdbc(sql.toString(),emkEnquiry.getId());

                EmkEnquiryEntityA emkEnquiryEntityA = new EmkEnquiryEntityA();
                MyBeanUtils.copyBeanNotNull2Bean(emkEnquiry,emkEnquiryEntityA);
                if(Utils.notEmpty(emkEnquiry.getCustomSampleUrl())){
                    emkEnquiryEntityA.setCustomSampleUrl(request.getRealPath("/")+emkEnquiry.getCustomSampleUrl());
                }
                if(Utils.notEmpty(emkEnquiry.getOldImageUrl())){
                    emkEnquiryEntityA.setOldImageUrl(request.getRealPath("/")+emkEnquiry.getOldImageUrl());
                }
                if(Utils.notEmpty(emkEnquiry.getDgrImageUrl())){
                    emkEnquiryEntityA.setDgrImageUrl(request.getRealPath("/")+emkEnquiry.getDgrImageUrl());
                }
                if(Utils.notEmpty(emkEnquiry.getSizeImageUrl())){
                    emkEnquiryEntityA.setSizeImageUrl(request.getRealPath("/")+emkEnquiry.getSizeImageUrl());
                }

                Map type = systemService.findOneForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='gylx' and typecode=?",emkEnquiry.getGyzl());
                emkEnquiryEntityA.setGyzl(type.get("typename").toString());
                new createPdf(file).generateEmkEnquiryPDF(emkEnquiryEntityA,emkProOrderDetailEntities);
                String fFileName = "d:\\PDF\\F"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
                WaterMark.waterMark(fileName,fFileName, "意向询盘单");
                file.delete();
                WebFileUtils.downLoad(fFileName,response,false);
                file = new File(fFileName);
                file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "询盘单导出PDF失败";
            throw new BusinessException(e.getMessage());
        }
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkEnquiryEntity emkEnquiry, HttpServletRequest req) {
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        req.setAttribute("colorList", list);
        list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='colorNum'");
        req.setAttribute("colorNumList", list);
        req.setAttribute("kdDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkEnquiry.getId())) {
            emkEnquiry = emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
            req.setAttribute("emkEnquiryPage", emkEnquiry);
        }
        return new ModelAndView("com/emk/storage/enquiry/emkEnquiry-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkEnquiryEntity emkEnquiry, HttpServletRequest req) {
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        req.setAttribute("colorList", list);
        list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='colorNum'");
        req.setAttribute("colorNumList", list);
        if (StringUtil.isNotEmpty(emkEnquiry.getId())) {
            emkEnquiry = emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
            req.setAttribute("emkEnquiryPage", emkEnquiry);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkEnquiry);
                req.setAttribute("countMap", countMap);
                double a=0,b=0;
                a = Double.parseDouble(countMap.get("finishColums").toString());
                b = Double.parseDouble(countMap.get("Colums").toString())-3;
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            EmkSamplePriceEntity samplePriceEntity = systemService.findUniqueByProperty(EmkSamplePriceEntity.class, "enquiryId", emkEnquiry.getId());
            req.setAttribute("samplePriceEntity", samplePriceEntity);
        }
        return new ModelAndView("com/emk/storage/enquiry/emkEnquiry-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkEnquiryEntity emkEnquiry, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkEnquiry.getId())) {
            emkEnquiry = emkEnquiryService.getEntity(EmkEnquiryEntity.class, emkEnquiry.getId());
            req.setAttribute("emkEnquiryPage", emkEnquiry);

           /* Calendar cal1 = Calendar.getInstance();
            cal1.setTime(DateUtils.str2Date(emkEnquiry.getYsDate(),DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = DateUtils.dateDiff('d',cal1,cal2);
            if(day<0){
                day = 0;
            }
            req.setAttribute("levelDays",day);*/
            EmkSamplePriceEntity samplePriceEntity = systemService.findUniqueByProperty(EmkSamplePriceEntity.class, "enquiryId", emkEnquiry.getId());
            req.setAttribute("samplePriceEntity", samplePriceEntity);

            List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
            req.setAttribute("colorList", list);
            list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='colorNum'");
            req.setAttribute("colorNumList", list);
            Map map = ParameterUtil.getParamMaps(req.getParameterMap());
            StringBuilder sql = new StringBuilder();
            sql.append("select t.id,t.enquiry_id enquiryId,t.color,t.color_value colorVal,t.size,\n" +
                    " (select t1.total from emk_enquiry_detail t1 where t1.enquiry_id=t.enquiry_id and t1.color=t.color and t1.color_value=t.color_value and t1.size='s' limit 0,1) stotal,\n" +
                    " (select t2.total from emk_enquiry_detail t2 where t2.enquiry_id=t.enquiry_id and t2.color=t.color and t2.color_value=t.color_value and t2.size='m' limit 0,1) mtotal,\n" +
                    " (select t3.total from emk_enquiry_detail t3 where t3.enquiry_id=t.enquiry_id and t3.color=t.color and t3.color_value=t.color_value and t3.size='l' limit 0,1) ltotal,\n" +
                    " (select t4.total from emk_enquiry_detail t4 where t4.enquiry_id=t.enquiry_id and t4.color=t.color and t4.color_value=t.color_value and t4.size='xl' limit 0,1) xltotal,\n" +
                    " (select t5.total from emk_enquiry_detail t5 where t5.enquiry_id=t.enquiry_id and t5.color=t.color and t5.color_value=t.color_value and t5.size='2xl' limit 0,1) xxltotal\n" +
                    " from emk_enquiry_detail t where t.enquiry_id=? group by t.enquiry_id,t.color,t.color_value  order by t.sort_desc asc \n");
            List<Map<String, Object>> emkProOrderDetailEntities = systemService.findForJdbc(sql.toString(),emkEnquiry.getId());
            req.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
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
        List<EmkEnquiryEntity> emkEnquirys = emkEnquiryService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
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
        List<EmkEnquiryEntity> listEmkEnquirys = emkEnquiryService.getList(EmkEnquiryEntity.class);
        return Result.success(listEmkEnquirys);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取询盘单信息", notes = "根据ID获取询盘单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkEnquiryEntity task = emkEnquiryService.get(EmkEnquiryEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取询盘单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建询盘单")
    public ResponseMessage<?> create(@ApiParam(name = "询盘单对象") @RequestBody EmkEnquiryEntity emkEnquiry, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkEnquiryEntity>> failures = validator.validate(emkEnquiry, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkEnquiryService.save(emkEnquiry);
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
        Set<ConstraintViolation<EmkEnquiryEntity>> failures = validator.validate(emkEnquiry, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkEnquiryService.saveOrUpdate(emkEnquiry);
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
            emkEnquiryService.deleteEntityById(EmkEnquiryEntity.class, id);
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
            if ((Utils.isEmpty(emkEnquiryEntity.getId()))) {
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
                    variables.put("optUser", t.getId());
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("instorageTask")) {
                            taskService.complete(task1.getId(), variables);
                            t.setState("1");
                            b.setStatus(1);
                        }

                        if (!task1.getTaskDefinitionKey().equals("instorageTask")) {
                            //保存审批意见
                            EmkApprovalDetailEntity approvalDetail = ApprovalUtil.saveApprovalDetail(b.getId(),user,b);
                            /*approvalDetail.setApprovalId(b.getId());
                            approvalDetail.setApproveUserId(user.getId());
                            approvalDetail.setApproveDate(DateUtil.getCurrentTimeString(null));
                            approvalDetail.setApproveStatus(0);
                            b.setBpmSher(user.getRealName());
                            b.setBpmSherId(user.getId());
                            b.setBpmDate(DateUtil.getCurrentTimeString(null));*/
                            //HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(task1.getProcessInstanceId()).singleResult();
                            flag = 0;
                            if(task1.getTaskDefinitionKey().equals("ywbCheckTask") || task1.getTaskDefinitionKey().equals("jsbCheckTask") || task1.getTaskDefinitionKey().equals("scbCheckTask")){
                                if (map.get("isPass").equals("0")) {
                                    systemService.executeSql("update act_hi_actinst set END_TIME_=? WHERE PROC_INST_ID_=? AND ACT_ID_=?",new Date(),task1.getProcessInstanceId(),map.get("processNode"));
                                    systemService.executeSql("update ACT_HI_TASKINST set END_TIME_=? WHERE PROC_INST_ID_=? AND TASK_DEF_KEY_=?",new Date(),task1.getProcessInstanceId(),map.get("processNode"));

                                    List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(task1.getProcessInstanceId()).list();
                                    for(HistoricActivityInstance historicActivityInstance : highLightedActivitList){
                                        if(historicActivityInstance.getActivityId().equals("ywbCheckTask") || historicActivityInstance.getActivityId().equals("jsbCheckTask") || historicActivityInstance.getActivityId().equals("scbCheckTask")){
                                            if(Utils.isEmpty(historicActivityInstance.getEndTime())){
                                                flag = 1;
                                            }
                                        }
                                    }
                                    if (map.get("processNode").equals("ywbCheckTask")) {
                                        //taskService.complete(mTask.getId(), variables);
                                        approvalDetail.setApproveAdvice(map.get("pgAdvice").toString());
                                        approvalDetail.setApproveAdvice2(map.get("bzAdvice").toString());
                                        approvalDetail.setBpmName("业务部审核");
                                        approvalDetail.setBpmNode("ywbCheckTask");
                                        systemService.saveOrUpdate(b);
                                        t.setState1("0");
                                /*    //发起工单流程
                                    EmkWorkOrderEntity emkWorkOrderEntity = new EmkWorkOrderEntity();
//                                    emkWorkOrderEntity.setIsPrint(t.getIsPrint());
                                    emkWorkOrderEntity.setAskNo(t.getEnquiryNo());
                                    emkWorkOrderEntity.setState("1");
                                    //emkWorkOrderEntity.setAskWorkAdvice(t.getLeadAdvice());
                                    emkWorkOrderEntity.setAskWorkUserId(user.getUserName());
                                    emkWorkOrderEntity.setAskWorkUser(user.getRealName());
                                    emkWorkOrderEntity.setAskWorkDate(DateUtil.getCurrentTimeString(null));

                                    emkWorkOrderEntity.setCreateName(t.getCreateName());
                                    emkWorkOrderEntity.setKdDate(t.getKdDate());
                                    Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(work_no, 6)),0)+1 AS signed) orderNum from emk_work_order");
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
                                    taskService.complete(task1.getId(), variables);*/
                                    }
                                    if(map.get("processNode").equals("jsbCheckTask")) {
                                        //taskService.complete(mTask.getId(), variables);
                                        approvalDetail.setBpmName("技术部审核");
                                        approvalDetail.setBpmNode("jsbCheckTask");
                                        approvalDetail.setApproveAdvice3(map.get("jsbAdvice").toString());
                                        approvalDetail.setApproveAdvice4(map.get("ranAdvice").toString());
                                        approvalDetail.setApproveAdvice5(map.get("fengAdvice").toString());
                                        approvalDetail.setApproveAdvice6(map.get("biaoAdvice").toString());
                                        systemService.saveOrUpdate(b);
                                        t.setState2("0");
                                    }
                                    if(map.get("processNode").equals("scbCheckTask")) {
                                        //taskService.complete(mTask.getId(), variables);
                                        approvalDetail.setBpmName("生产部审核");
                                        approvalDetail.setBpmNode("scbCheckTask");
                                        approvalDetail.setApproveAdvice7(map.get("caiAdvice").toString());
                                        approvalDetail.setApproveAdvice8(map.get("scjhAdvice").toString());
                                        approvalDetail.setApproveAdvice9(map.get("sczfAdvice").toString());
                                        systemService.saveOrUpdate(b);
                                        t.setState3("0");
                                    }
                                    if(flag == 0){
                                        taskService.complete(task1.getId(), variables);
                                        task = taskService.createTaskQuery().taskAssignee(id).list();
                                        task1 = (Task)task.get(task.size() - 1);
                                        taskService.complete(task1.getId(), variables);
                                        task = taskService.createTaskQuery().taskAssignee(id).list();
                                        task1 = (Task)task.get(task.size() - 1);
                                        taskService.complete(task1.getId(), variables);
                                        task = taskService.createTaskQuery().taskAssignee(id).list();

                                    }

                                }else{
                                    approvalDetail.setBpmName("回退意向询盘单");
                                    approvalDetail.setBpmNode("instorageTask");
                                    approvalDetail.setApproveStatus(1);
                                    approvalDetail.setApproveAdvice("回退");

                                    systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and (act_id_=? or act_id_=? or act_id_=? or act_id_=?) ",task1.getProcessInstanceId(),"ywbCheckTask","scbCheckTask","jsbCheckTask","parallelgateway2");
                                    systemService.executeSql("delete from act_hi_taskinst where proc_inst_id_=? and (task_def_key_=? or task_def_key_=? or task_def_key_=?) ",task1.getProcessInstanceId(),"ywbCheckTask","scbCheckTask","jsbCheckTask");
                                    systemService.executeSql("delete from act_ru_task where proc_inst_id_=? and (task_def_key_=? or task_def_key_=? or task_def_key_=?) ",task1.getProcessInstanceId(),"scbCheckTask","jsbCheckTask","ywbCheckTask");
                                    systemService.executeSql("delete from act_ru_execution where proc_inst_id_=? and (act_id_=? or act_id_=? or act_id_=?) ",task1.getProcessInstanceId(),"scbCheckTask","ywbCheckTask","ywbCheckTask");
                                    systemService.executeSql("update act_ru_task set name_=?,task_def_key_=? where proc_inst_id_=? ","意向询盘单","instorageTask",task1.getProcessInstanceId());
                                    systemService.executeSql("update act_ru_execution set rev_=1,act_id_=? where proc_inst_id_=? and act_id_=?", "instorageTask",task1.getProcessInstanceId(),"parallelgateway2");
                                    systemService.executeSql("insert into act_ru_task (id_,rev_,execution_id_,proc_inst_id_,proc_def_id_,name_,task_def_key_,assignee_,suspension_state_) values (?,?,?,?,?,?,?,?,?) ",task1.getProcessInstanceId(),"1",task1.getProcessInstanceId(),task1.getProcessInstanceId(),"enquiry:8:457504","意向询盘单","instorageTask",t.getId(),"1");
                                    t.setState1("1");
                                    t.setState2("1");
                                    t.setState3("1");

                                    t.setState("0");
                                    b.setStatus(3);
                                }
                            }

                            if (task1.getTaskDefinitionKey().equals("cwTask")) {

                            }
                            systemService.save(approvalDetail);
                        }
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("enquiry", "emkEnquiryEntity", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);
                        t.setState("1");
                        b.setStatus(1);
                    }

                    systemService.saveOrUpdate(t);
                    systemService.saveOrUpdate(b);
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
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkEnquiryEntity.getId());
        List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=?",approvalEntity.getId());
        req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
        req.setAttribute("approvalEntity", approvalEntity);
        req.setAttribute("createDate", DateUtils.date2Str(approvalEntity.getCreateDate(),DateUtils.datetimeFormat));

        return new ModelAndView("com/emk/storage/enquiry/time");
    }
}
