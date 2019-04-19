package com.emk.storage.samplerequired.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.check.sizecheck.entity.EmkSizeEntity;
import com.emk.check.sizecheck.entity.EmkSizeTotalEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.gl.entity.EmkGlEntity;
import com.emk.storage.pb.entity.EmkPbEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.samplegx.entity.EmkSampleGxEntity;
import com.emk.storage.sampleprice.entity.EmkSamplePriceEntity;
import com.emk.storage.sampleran.entity.EmkSampleRanEntity;
import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.storage.samplerequired.service.EmkSampleRequiredServiceI;
import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;
import com.emk.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
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

@Api(value = "EmkSampleRequired", description = "样品需求单", tags = "emkSampleRequiredController")
@Controller
@RequestMapping("/emkSampleRequiredController")
public class EmkSampleRequiredController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSampleRequiredController.class);
    @Autowired
    private EmkSampleRequiredServiceI emkSampleRequiredService;
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
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequiredList");
    }

    @RequestMapping(params = "detailMxList")
    public ModelAndView detailMxList(HttpServletRequest request) {
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        request.setAttribute("colorList", list);
        list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='colorNum'");
        request.setAttribute("colorNumList", list);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkEnquiryDetailEntity> detailEntities = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", map.get("proOrderId"));
            request.setAttribute("emkProOrderDetailEntities", detailEntities);

            EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",map.get("proOrderId"));
            request.setAttribute("emkSizePage", emkSizeEntity);
        }
        return new ModelAndView("com/emk/storage/samplerequired/detailMxList");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 order by company_code asc ");
        request.setAttribute("gysList", list);
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=0", map.get("priceId"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/storage/price/orderMxList");
    }

    @RequestMapping(params = "orderMxList2")
    public ModelAndView orderMxList2(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=1", map.get("priceId"));
            request.setAttribute("emkSampleDetailEntities1", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/storage/price/orderMxList2");
    }

    @RequestMapping(params = "orderMxList3")
    public ModelAndView orderMxList3(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=2", map.get("priceId"));
            request.setAttribute("emkSampleDetailEntities2", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/storage/price/orderMxList3");
    }

    @RequestMapping(params = "gxList")
    public ModelAndView gxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleGxEntity> emkSampleGxEntities = systemService.findHql("from EmkSampleGxEntity where sampleId=?", map.get("priceId"));
            request.setAttribute("emkSampleGxEntities", emkSampleGxEntities);
        }
        return new ModelAndView("com/emk/storage/price/gxList");
    }
    @RequestMapping(params = "ranList")
    public ModelAndView ranList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='bmzl'");
        request.setAttribute("bmzlList", list);
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleRanEntity> emkSampleRanEntities = systemService.findHql("from EmkSampleRanEntity where sampleId=? and type=0 ", map.get("priceId"));
            request.setAttribute("emkSampleRanEntities", emkSampleRanEntities);
        }
        return new ModelAndView("com/emk/storage/price/ranList");
    }

    @RequestMapping(params = "yinList")
    public ModelAndView yinList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='yhgyzl'");
        request.setAttribute("yhgyzlList", list);
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleYinEntity> emkSampleYinEntities = systemService.findHql("from EmkSampleYinEntity where sampleId=?", map.get("priceId"));
            request.setAttribute("emkSampleYinEntities", emkSampleYinEntities);
        }
        return new ModelAndView("com/emk/storage/price/yinList");
    }
    
    @RequestMapping(params = "datagrid")
    public void datagrid(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleRequiredEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkSampleRequired, request.getParameterMap());


        cq.add();
        emkSampleRequiredService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleRequired = systemService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
        message = "样品需求单删除成功";
        try {
            emkSampleRequiredService.delete(emkSampleRequired);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单删除失败";
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
        message = "样品需求单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleRequiredEntity emkSampleRequired = systemService.getEntity(EmkSampleRequiredEntity.class, id);

                WebFileUtils.delete( request.getRealPath("/")+emkSampleRequired.getDgrImageUrl());
                WebFileUtils.delete( request.getRealPath("/")+emkSampleRequired.getCustomSampleUrl());
                WebFileUtils.delete( request.getRealPath("/")+emkSampleRequired.getOldImageUrl());
                WebFileUtils.delete( request.getRealPath("/")+emkSampleRequired.getSizeImageUrl());
                EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkSampleRequired.getId());


                systemService.executeSql("delete from emk_size_total where FIND_IN_SET(p_id,(SELECT GROUP_CONCAT(id) FROM emk_sample_detail where sample_id=?))",id);
                systemService.executeSql("delete from emk_size where form_id=?", id);
                systemService.executeSql("delete from emk_sample_detail where sample_id = ?",emkSampleRequired.getId());
                systemService.executeSql("delete from emk_sample_gx where sample_id = ?",emkSampleRequired.getId());
                systemService.executeSql("delete from emk_sample_ran where sample_id = ?",emkSampleRequired.getId());
                systemService.executeSql("delete from emk_sample_yin where sample_id = ?",emkSampleRequired.getId());

                systemService.executeSql("delete from emk_approval where form_id=?",id);
                if(Utils.notEmpty(approvalEntity)){
                    systemService.executeSql("delete from emk_approval_detail where approval_id=?",approvalEntity.getId());
                }
                emkSampleRequiredService.delete(emkSampleRequired);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doCreateYptzd")
    @ResponseBody
    public AjaxJson doCreateYptzd(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleRequired = systemService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
        message = "样品通知单创建成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

        try {
            if(Utils.notEmpty(emkSampleRequired.getState())){
                if(Integer.parseInt(emkSampleRequired.getState())>=1){
                    j.setSuccess(false);
                    j.setMsg("该样品单已经生成通知单了");
                    return j;
                }
            }
            emkSampleRequired.setState("1");
            systemService.saveOrUpdate(emkSampleRequired);

            EmkSampleEntity emkSample = new EmkSampleEntity();
            MyBeanUtils.copyBeanNotNull2Bean(emkSampleRequired,emkSample);
            emkSample.setCreateName(map.get("realName"));
            emkSample.setCreateBy(map.get("userName"));
            emkSample.setXqdh(emkSampleRequired.getRequiredNo());
            emkSample.setDyxqdTime(emkSampleRequired.getKdDate());
            emkSample.setKdTime(DateUtils.format(new Date(), "yyyy-MM-dd"));
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(SAMPLE_NUM, 2)),0)+1 AS signed) orderNum from emk_sample");
            emkSample.setSampleNum("YPTZD" + DateUtils.format(new Date(), "yyMMdd") + "A" + String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));
            //emkSample.setXqdh("YPXQ" +emkSample.getCusNum()+ DateUtils.format(new Date(), "yyMMdd")+ String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));

            /*emkSampleRequiredEntity.setRequiredNo("YPXP" + t.getCusNum() + DateUtil.format(new Date(), "yyMMdd") + String.format("%03d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
            emkSampleRequiredEntity.setId(null);
            emkSampleRequiredEntity.setKdDate(DateUtil.getCurrentTimeString("yyyy-MM-dd"));*/
            emkSample.setFormType("1");
            emkSample.setState("0");
            systemService.save(emkSample);

            //保存坯布参数
            EmkPbEntity emkPbEntity = new EmkPbEntity();
            EmkPbEntity emkPb = systemService.findUniqueByProperty(EmkPbEntity.class,"priceId",emkSampleRequired.getId());
            MyBeanUtils.copyBeanNotNull2Bean(emkPb,emkPbEntity);
            emkPbEntity.setId(null);
            emkPbEntity.setPriceId(emkSample.getId());
            systemService.save(emkPbEntity);

            EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",emkSampleRequired.getId());
            EmkSizeEntity sizeEntity = new EmkSizeEntity();
            MyBeanUtils.copyBeanNotNull2Bean(emkSizeEntity,sizeEntity);
            sizeEntity.setId(null);
            sizeEntity.setFormId(emkSample.getId());
            systemService.save(sizeEntity);

            List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId = ?",emkSampleRequired.getId());
            EmkEnquiryDetailEntity enquiryDetailEntity = null;
            EmkSizeTotalEntity sizeTotalEntity = null;
            for(EmkEnquiryDetailEntity emkEnquiryDetailEntity : emkEnquiryDetailEntityList){
                enquiryDetailEntity = new EmkEnquiryDetailEntity();
                MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetailEntity,enquiryDetailEntity);
                enquiryDetailEntity.setId(null);
                enquiryDetailEntity.setEnquiryId(emkSample.getId());

                systemService.save(enquiryDetailEntity);

                sizeTotalEntity = new EmkSizeTotalEntity();
                MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetailEntity.getEmkSizeTotalEntity(),sizeTotalEntity);
                sizeTotalEntity.setId(null);
                sizeTotalEntity.setpId(enquiryDetailEntity.getId());
                systemService.save(sizeTotalEntity);
            }

            //保存原料辅料包装数据
            List<EmkSampleDetailEntity> emkSampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId = ?",emkSampleRequired.getId());
            EmkSampleDetailEntity emkSampleDetailEntity = null;
            for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntityList){
                emkSampleDetailEntity = new EmkSampleDetailEntity();
                MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkSampleDetailEntity);
                emkSampleDetailEntity.setId(null);
                emkSampleDetailEntity.setSampleId(emkSample.getId());
                systemService.save(emkSampleDetailEntity);
            }
            //保存工序数据
            List<EmkSampleGxEntity> emkSampleGxEntityList = systemService.findHql("from EmkSampleGxEntity where sampleId = ?",emkSampleRequired.getId());
            EmkSampleGxEntity emkSampleGxEntity = null;
            for(EmkSampleGxEntity sampleGxEntity : emkSampleGxEntityList){
                emkSampleGxEntity = new EmkSampleGxEntity();
                MyBeanUtils.copyBeanNotNull2Bean(sampleGxEntity,emkSampleGxEntity);
                emkSampleGxEntity.setId(null);
                emkSampleGxEntity.setSampleId(emkSample.getId());
                systemService.save(emkSampleGxEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单创建失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkSampleRequiredEntity emkSampleRequired,EmkSizeEntity emkSize,EmkPbEntity emkPb, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品需求单添加成功";
        try {
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(REQUIRED_NO, 3)),0)+1 AS signed) orderNum from emk_sample_required");
            emkSampleRequired.setRequiredNo("YPXP" + emkSampleRequired.getCusNum() + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
            emkSampleRequiredService.save(emkSampleRequired);
            emkSize.setFormId(emkSampleRequired.getId());
            emkSampleRequired.setFormType("0");
            systemService.save(emkSize);
            /*EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
            }
            samplePriceEntity.setBz(map.get("pbz").toString());
            samplePriceEntity.setEnquiryId(emkSampleRequired.getId());
            samplePriceEntity.setState(map.get("pstate").toString());
            samplePriceEntity.setState("0");
            systemService.save(samplePriceEntity);*/

            //保存坯布参数
            emkPb.setPriceId(emkSampleRequired.getId());
            systemService.save(emkPb);

            saveSampleDetail(null,null,map,"1",emkSampleRequired.getId());

            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleRequiredEntity emkSampleRequired, EmkSizeEntity emkSize, EmkPbEntity emkPb, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品需求单更新成功";
        EmkSampleRequiredEntity t = emkSampleRequiredService.get(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
        if(Utils.notEmpty(t.getState())){
            if(!t.getState().equals("0")){
                j.setMsg("样品需求单已提交，无法进行修改");
                j.setSuccess(false);
                return j;
            }
        }

        EmkSizeEntity t2 = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",t.getId());

        try {
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            MyBeanUtils.copyBeanNotNull2Bean(emkSampleRequired, t);
            emkSampleRequiredService.saveOrUpdate(t);

            Map pbBean = systemService.findOneForJdbc("select * from emk_pb where price_id=?",t.getId());
            if(Utils.notEmpty(pbBean)){
                if(Utils.notEmpty(map.get("pbid"))){
                    systemService.executeSql("delete from emk_pb where price_id=?",t.getId());
                    emkPb.setPriceId(t.getId());
                    systemService.save(emkPb);
                }
            }else{
                emkPb.setPriceId(t.getId());
                systemService.save(emkPb);
            }

            emkSize.setId(null);
            if(Utils.notEmpty(t2)){
                MyBeanUtils.copyBeanNotNull2Bean(emkSize, t2);
                systemService.saveOrUpdate(t2);
            }else{
                t2 = new EmkSizeEntity();
                MyBeanUtils.copyBeanNotNull2Bean(emkSize, t2);
                t2.setFormId(t.getId());
                systemService.save(t2);
            }


            saveSampleDetail(null,null,map,"1",t.getId());

            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkSampleRequiredEntity emkSampleRequired, EmkGlEntity emkGlEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品需求单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

            if ((emkSampleRequired.getId() == null) || (emkSampleRequired.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkSampleRequiredEntity emkSampleRequiredEntity = systemService.getEntity(EmkSampleRequiredEntity.class, id);
                    if(Utils.notEmpty(emkSampleRequiredEntity.getState())){
                        if (!emkSampleRequiredEntity.getState().equals("0")) {
                            message = "存在已提交的样品需求单，请重新选择在提交样品需求单！";
                            j.setSuccess(false);
                            flag = 1;
                            break;
                        }
                    }
                }
            }else{
                map.put("ids", emkSampleRequired.getId());
            }

            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkSampleRequiredEntity t = emkSampleRequiredService.get(EmkSampleRequiredEntity.class, id);
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
                    if(Utils.isEmpty(b)){
                        //type 工单类型，workNum 单号，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
                        b = new EmkApprovalEntity();
                        EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
                        ApprovalUtil.saveApproval(b,3,t.getRequiredNo(),t.getId(),user);
                        systemService.save(b);
                        ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"样品需求单","sampleTask","提交",user);
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
                        if (task1.getTaskDefinitionKey().equals("sampleTask")) {
                            taskService.complete(task1.getId(), variables);
                            t.setState("1");
                            b.setStatus(1);
                            saveApprvoalDetail(approvalDetail,"重新提交的样品需求单","sampleTask",0,"重新提交样品需求单");
                            saveSmsAndEmailForMany("业务经理","样品需求【业务跟单员】","您有【"+b.getCreateName()+"】重新提交的样品需求单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }
                        if (task1.getTaskDefinitionKey().equals("leadTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(3);
                                approvalDetail.setBpmName("业务经理审核");
                                t.setState("3");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("技术经理","业务经理审核","您有【"+user.getRealName()+"】审核过的样品需求单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务经理审核","leadTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"leadTask","sampleTask","样品需求单");
                                t.setState("0");
                                b.setStatus(0);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的样品需求单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("jsbTask")) {
                            if (map.get("isPass").equals("0")) {
                                b.setNextBpmSher(map.get("realName"));
                                b.setNextBpmSherId(map.get("userName"));

                                taskService.complete(task1.getId(), variables);
                                b.setStatus(5);
                                approvalDetail.setBpmName("技术部审核");
                                t.setState("5");
                                approvalDetail.setApproveStatus(0);

                                bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());
                                saveSmsAndEmailForOne("技术部审核","您有【"+user.getRealName()+"】审核通过的样品需求单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());

                            }else{
                                saveApprvoalDetail(approvalDetail,"技术部审核","jsbTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"jsbTask","leadTask","样品需求单");
                                systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and act_id_=? ",task1.getProcessInstanceId(),"isPass");

                                t.setState("4");
                                b.setStatus(4);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("技术部审核","您有【"+user.getRealName()+"】回退的样品需求单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }


                        if (task1.getTaskDefinitionKey().equals("dytzdTask")) {
                            taskService.complete(task1.getId(), variables);
                            b.setStatus(33);
                            approvalDetail.setBpmName("打样通知单【生产跟单员】");
                            t.setState("33");
                            approvalDetail.setApproveStatus(0);

                            EmkSampleEntity emkSample = new EmkSampleEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(t,emkSample);
                            emkSample.setCreateName(user.getCreateName());
                            emkSample.setCreateBy(user.getUserName());
                            emkSample.setXqdh(t.getRequiredNo());
                            emkSample.setDyxqdTime(t.getKdDate());
                            emkSample.setKdTime(DateUtils.format(new Date(), "yyyy-MM-dd"));
                            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(SAMPLE_NUM, 2)),0)+1 AS signed) orderNum from emk_sample");
                            emkSample.setSampleNum("YPTZD" + DateUtils.format(new Date(), "yyMMdd") + "A" + String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));

                            emkSample.setFormType("1");
                            emkSample.setState("0");
                            systemService.save(emkSample);

                            //保存坯布参数
                            EmkPbEntity emkPbEntity = new EmkPbEntity();
                            EmkPbEntity emkPb = systemService.findUniqueByProperty(EmkPbEntity.class,"priceId",t.getId());
                            MyBeanUtils.copyBeanNotNull2Bean(emkPb,emkPbEntity);
                            emkPbEntity.setId(null);
                            emkPbEntity.setPriceId(emkSample.getId());
                            systemService.save(emkPbEntity);

                            EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",t.getId());
                            EmkSizeEntity sizeEntity = new EmkSizeEntity();
                            MyBeanUtils.copyBeanNotNull2Bean(emkSizeEntity,sizeEntity);
                            sizeEntity.setId(null);
                            sizeEntity.setFormId(emkSample.getId());
                            systemService.save(sizeEntity);

                            List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId = ?",t.getId());
                            EmkEnquiryDetailEntity enquiryDetailEntity = null;
                            EmkSizeTotalEntity sizeTotalEntity = null;
                            for(EmkEnquiryDetailEntity emkEnquiryDetailEntity : emkEnquiryDetailEntityList){
                                enquiryDetailEntity = new EmkEnquiryDetailEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetailEntity,enquiryDetailEntity);
                                enquiryDetailEntity.setId(null);
                                enquiryDetailEntity.setEnquiryId(emkSample.getId());

                                systemService.save(enquiryDetailEntity);

                                sizeTotalEntity = new EmkSizeTotalEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetailEntity.getEmkSizeTotalEntity(),sizeTotalEntity);
                                sizeTotalEntity.setId(null);
                                sizeTotalEntity.setpId(enquiryDetailEntity.getId());
                                systemService.save(sizeTotalEntity);
                            }

                            //保存原料辅料包装数据
                            List<EmkSampleDetailEntity> emkSampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId = ?",t.getId());
                            EmkSampleDetailEntity emkSampleDetailEntity = null;
                            for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntityList){
                                emkSampleDetailEntity = new EmkSampleDetailEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkSampleDetailEntity);
                                emkSampleDetailEntity.setId(null);
                                emkSampleDetailEntity.setSampleId(emkSample.getId());
                                systemService.save(emkSampleDetailEntity);
                            }
                            //保存工序数据
                            List<EmkSampleGxEntity> emkSampleGxEntityList = systemService.findHql("from EmkSampleGxEntity where sampleId = ?",t.getId());
                            EmkSampleGxEntity emkSampleGxEntity = null;
                            for(EmkSampleGxEntity sampleGxEntity : emkSampleGxEntityList){
                                emkSampleGxEntity = new EmkSampleGxEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(sampleGxEntity,emkSampleGxEntity);
                                emkSampleGxEntity.setId(null);
                                emkSampleGxEntity.setSampleId(emkSample.getId());
                                systemService.save(emkSampleGxEntity);
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("gxjdTask")) {
                            taskService.complete(task1.getId(), variables);
                            b.setStatus(2);
                            approvalDetail.setBpmName("更新进度【生产跟单员】");
                            t.setState("2");
                            approvalDetail.setApproveStatus(0);

                            //流转询盘单价格确认环节
                            if(Utils.notEmpty(t.getEnquiryNo())){
                                EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"workNum",t.getEnquiryNo());
                                task = taskService.createTaskQuery().taskAssignee(approvalEntity.getFormId()).list();
                                variables.put("optUser", approvalEntity.getFormId());

                                task1 = task.get(task.size() - 1);
                                taskService.complete(task1.getId(), variables);

                                EmkEnquiryEntity emkEnquiryEntity = systemService.findUniqueByProperty(EmkEnquiryEntity.class,"enquiryNo",t.getEnquiryNo());
                                EmkPriceEntity emkPrice = new EmkPriceEntity();
                                String priceNo = savePrice2(emkEnquiryEntity,emkPrice,approvalEntity.getNextBpmSherId(),approvalEntity.getNextBpmSher(),t.getId());

                                String title = "您有【"+approvalEntity.getCreateName()+"】提交的意向询盘单需要报价，报价单号："+priceNo;
                                bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());
                                saveSmsAndEmailForOne("更新进度【生产跟单员】",title+"，请及时处理。",bpmUser,user.getUserName());

                                EmkApprovalDetailEntity papprovalDetail = ApprovalUtil.saveApprovalDetail(approvalEntity.getId(),user,approvalEntity,"打样完成");
                                papprovalDetail.setBpmName("打样完成");
                                papprovalDetail.setApproveStatus(0);
                                approvalEntity.setStatus(30);

                                systemService.executeSql("update emk_enquiry set state=30 where id=?",approvalEntity.getFormId());
                                makerUser = systemService.findUniqueByProperty(TSUser.class,"userName",approvalEntity.getCreateBy());
                                saveSmsAndEmailForOne("更新进度【生产跟单员】","您有【"+user.getRealName()+"】完成的打样单，单号："+b.getWorkNum()+"，请及时处理询盘单："+approvalEntity.getWorkNum()+",价格确认",makerUser,user.getUserName());
                            }
                        }
                        systemService.save(approvalDetail);
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("sample", "sample", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);

                        saveSmsAndEmailForMany("业务经理","样品需求【业务跟单员】","您有【"+b.getCreateName()+"】提交的样品需求单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        t.setState("1");
                        b.setStatus(1);
                        b.setBpmStatus("0");
                        b.setProcessName("样品需求【业务跟单员】");

                    }
                    systemService.saveOrUpdate(t);
                    systemService.saveOrUpdate(b);
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-work");


    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkPriceEntity emkPrice, HttpServletRequest req, DataGrid dataGrid) {
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkPrice.getId());
        if(Utils.notEmpty(approvalEntity)){
            List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
            req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
            req.setAttribute("approvalEntity", approvalEntity);
            req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
        }

        return new ModelAndView("com/emk/storage/samplerequired/time");
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-add");
    }

    @RequestMapping(params = "goSelectUser")
    public ModelAndView goSelectUser(HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-selectUser");
    }

    @RequestMapping(params = "goTab")
    public ModelAndView goBtn(EmkSampleEntity emkSample, HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-tab");
    }
    @RequestMapping(params = "goTab1")
    public ModelAndView goTab1(EmkSampleEntity emkSample, HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-tab1");
    }
    @RequestMapping(params = "goBase")
    public ModelAndView goBase(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkSampleRequired);
                req.setAttribute("countMap", countMap);
                double a=0,b=0;
                a = Double.parseDouble(countMap.get("finishColums").toString())-5;
                b = Double.parseDouble(countMap.get("Colums").toString())-5;
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-base");
    }

    @RequestMapping(params = "goPb")
    public ModelAndView goPb(EmkPbEntity emkPbEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPbEntity.getPriceId())) {
            emkPbEntity = systemService.findUniqueByProperty(EmkPbEntity.class,"priceId",emkPbEntity.getPriceId());
            req.setAttribute("emkPbPage", emkPbEntity);
            try {
                if(Utils.notEmpty(emkPbEntity)){
                    Map countMap = MyBeanUtils.culBeanCounts(emkPbEntity);
                    req.setAttribute("countMap", countMap);
                    double a=0,b=0;
                    a = Double.parseDouble(countMap.get("finishColums").toString())-4;
                    b = Double.parseDouble(countMap.get("Colums").toString())-4;
                    DecimalFormat df = new DecimalFormat("#.00");
                    req.setAttribute("recent", df.format(a*100/b));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-pb");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);
            /*Calendar cal1 = Calendar.getInstance();
            cal1.setTime(org.jeecgframework.core.util.DateUtils.str2Date(emkSampleRequired.getYsDate(), org.jeecgframework.core.util.DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = org.jeecgframework.core.util.DateUtils.dateDiff('d',cal1,cal2);
            req.setAttribute("levelDays",day);
            EmkSamplePriceEntity samplePriceEntity = (EmkSamplePriceEntity) systemService.findUniqueByProperty(EmkSamplePriceEntity.class, "enquiryId", emkSampleRequired.getId());
            */
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);

           /* Calendar cal1 = Calendar.getInstance();
            cal1.setTime(DateUtils.str2Date(emkEnquiry.getYsDate(),DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = DateUtils.dateDiff('d',cal1,cal2);
            if(day<0){
                day = 0;
            }
            req.setAttribute("levelDays",day);*/

            List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
            req.setAttribute("colorList", list);
            list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='colorNum'");
            req.setAttribute("colorNumList", list);
            List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId = ?",emkSampleRequired.getId());
            req.setAttribute("emkProOrderDetailEntities", emkEnquiryDetailEntityList);
            EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",emkSampleRequired.getId());
            req.setAttribute("emkSizePage", emkSizeEntity);
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleRequiredController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleRequiredEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleRequired, request.getParameterMap());
        List<EmkSampleRequiredEntity> emkSampleRequireds = emkSampleRequiredService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "样品需求单");
        modelMap.put("entity", EmkSampleRequiredEntity.class);
        modelMap.put("params", new ExportParams("样品需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSampleRequireds);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "样品需求单");
        modelMap.put("entity", EmkSampleRequiredEntity.class);
        modelMap.put("params", new ExportParams("样品需求单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "样品需求单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleRequiredEntity>> list() {
        List<EmkSampleRequiredEntity> listEmkSampleRequireds = emkSampleRequiredService.getList(EmkSampleRequiredEntity.class);
        return Result.success(listEmkSampleRequireds);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取样品需求单信息", notes = "根据ID获取样品需求单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleRequiredEntity task = emkSampleRequiredService.get(EmkSampleRequiredEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取样品需求单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建样品需求单")
    public ResponseMessage<?> create(@ApiParam(name = "样品需求单对象") @RequestBody EmkSampleRequiredEntity emkSampleRequired, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleRequiredEntity>> failures = validator.validate(emkSampleRequired, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleRequiredService.save(emkSampleRequired);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品需求单信息保存失败");
        }
        return Result.success(emkSampleRequired);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新样品需求单", notes = "更新样品需求单")
    public ResponseMessage<?> update(@ApiParam(name = "样品需求单对象") @RequestBody EmkSampleRequiredEntity emkSampleRequired) {
        Set<ConstraintViolation<EmkSampleRequiredEntity>> failures = validator.validate(emkSampleRequired, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleRequiredService.saveOrUpdate(emkSampleRequired);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新样品需求单信息失败");
        }
        return Result.success("更新样品需求单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除样品需求单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkSampleRequiredService.deleteEntityById(EmkSampleRequiredEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品需求单删除失败");
        }
        return Result.success();
    }
}
