package com.emk.storage.price.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.storage.gl.entity.EmkGlEntity;
import com.emk.storage.pb.entity.EmkPbEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.price.entity.EmkPriceEntityA;
import com.emk.storage.price.service.EmkPriceServiceI;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.samplegx.entity.EmkSampleGxEntity;
import com.emk.storage.sampleran.entity.EmkSampleRanEntity;
import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;
import com.emk.util.*;
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
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
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
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/price/emkPriceList");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 order by company_code asc ");
        request.setAttribute("gysList", list);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=0", map.get("priceId"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/storage/price/orderMxList");
    }

    @RequestMapping(params = "orderMxList2")
    public ModelAndView orderMxList2(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 order by company_code asc ");
        request.setAttribute("gysList", list);
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=1", map.get("priceId"));
            request.setAttribute("emkSampleDetailEntities1", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/storage/price/orderMxList2");
    }

    @RequestMapping(params = "orderMxList3")
    public ModelAndView orderMxList3(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 order by company_code asc ");
        request.setAttribute("gysList", list);
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
    @RequestMapping(params = "zjList")
    public ModelAndView zjList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='bmzl'");
        request.setAttribute("zjList", list);
        if (Utils.notEmpty(map.get("priceId"))) {
            List<EmkSampleRanEntity> emkSampleRanEntities = systemService.findHql("from EmkSampleRanEntity where sampleId=? and type=1", map.get("priceId"));
            request.setAttribute("emkSampleZjEntities", emkSampleRanEntities);
        }
        return new ModelAndView("com/emk/storage/price/zjList");
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
        emkPriceService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkPriceEntity emkPrice, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkPrice = systemService.getEntity(EmkPriceEntity.class, emkPrice.getId());
        message = "报价单删除成功";
        try {
            emkPriceService.delete(emkPrice);

            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
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
                EmkPriceEntity emkPrice = systemService.getEntity(EmkPriceEntity.class, id);
                WebFileUtils.delete( request.getRealPath("/")+emkPrice.getDgrImageUrl());
                WebFileUtils.delete( request.getRealPath("/")+emkPrice.getCustomSampleUrl());
                WebFileUtils.delete( request.getRealPath("/")+emkPrice.getOldImageUrl());
                WebFileUtils.delete( request.getRealPath("/")+emkPrice.getSizeImageUrl());
                EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkPrice.getId());

                systemService.executeSql("delete from emk_enquiry_detail where enquiry_id=?",emkPrice.getId());
                systemService.executeSql("delete from emk_approval where form_id=?",emkPrice.getId());
                systemService.executeSql("delete from emk_approval_detail where approval_id=?",emkPrice.getId());
                systemService.executeSql("delete from emk_sample_detail where sample_id = ?",emkPrice.getId());
                systemService.executeSql("delete from emk_sample_gx where sample_id = ?",emkPrice.getId());
                systemService.executeSql("delete from emk_sample_ran where sample_id = ?",emkPrice.getId());
                systemService.executeSql("delete from emk_sample_yin where sample_id = ?",emkPrice.getId());

                emkPriceService.delete(emkPrice);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "报价单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doPrintPDF")
    public String doPrintPDF(String ids, EmkPriceEntity emkPriceEntity, HttpServletRequest request, HttpServletResponse response) {
        String message = null;

        try {
            for (String id : ids.split(",")) {
                String fileName = "c:\\PDF\\"+ DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
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
                emkPriceEntity = systemService.getEntity(EmkPriceEntity.class, id);

                StringBuilder sql = new StringBuilder();
                sql.append("select t.id,t.enquiry_id enquiryId,(select typename from t_s_type a2 left join t_s_typegroup a1 on a1.ID=a2.typegroupid where a1.typegroupcode='color' and a2.typecode=t.color) colorName,t.color_value colorVal,t.size,\n" +
                        " (select t1.total from emk_enquiry_detail t1 where t1.enquiry_id=t.enquiry_id and t1.color=t.color and t1.color_value=t.color_value and t1.size='s' limit 0,1) stotal,\n" +
                        " (select t2.total from emk_enquiry_detail t2 where t2.enquiry_id=t.enquiry_id and t2.color=t.color and t2.color_value=t.color_value and t2.size='m' limit 0,1) mtotal,\n" +
                        " (select t3.total from emk_enquiry_detail t3 where t3.enquiry_id=t.enquiry_id and t3.color=t.color and t3.color_value=t.color_value and t3.size='l' limit 0,1) ltotal,\n" +
                        " (select t4.total from emk_enquiry_detail t4 where t4.enquiry_id=t.enquiry_id and t4.color=t.color and t4.color_value=t.color_value and t4.size='xl' limit 0,1) xltotal,\n" +
                        " (select t5.total from emk_enquiry_detail t5 where t5.enquiry_id=t.enquiry_id and t5.color=t.color and t5.color_value=t.color_value and t5.size='2xl' limit 0,1) xxltotal\n" +
                        " from emk_enquiry_detail t where t.enquiry_id=? group by t.enquiry_id,t.color,t.color_value  order by t.sort_desc asc \n");
                List<Map<String, Object>> emkProOrderDetailEntities = systemService.findForJdbc(sql.toString(),emkPriceEntity.getId());

                EmkPriceEntityA emkPriceEntityA = new EmkPriceEntityA();
                MyBeanUtils.copyBeanNotNull2Bean(emkPriceEntity,emkPriceEntityA);
                if(Utils.notEmpty(emkPriceEntity.getCustomSampleUrl())){
                    emkPriceEntityA.setCustomSampleUrl(request.getRealPath("/")+emkPriceEntity.getCustomSampleUrl());
                }
                if(Utils.notEmpty(emkPriceEntity.getOldImageUrl())){
                    emkPriceEntityA.setOldImageUrl(request.getRealPath("/")+emkPriceEntity.getOldImageUrl());
                }
                if(Utils.notEmpty(emkPriceEntity.getDgrImageUrl())){
                    emkPriceEntityA.setDgrImageUrl(request.getRealPath("/")+emkPriceEntity.getDgrImageUrl());
                }
                if(Utils.notEmpty(emkPriceEntity.getSizeImageUrl())){
                    emkPriceEntityA.setSizeImageUrl(request.getRealPath("/")+emkPriceEntity.getSizeImageUrl());
                }

                Map type = systemService.findOneForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='gylx' and typecode=?",emkPriceEntity.getGyzl());
                emkPriceEntityA.setGyzl(type.get("typename").toString());
                new createPdf(file).generatePricePDF(emkPriceEntityA,emkProOrderDetailEntities);
                String fFileName = "c:\\PDF\\F"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
                WaterMark.waterMark(fileName,fFileName, "报价单");
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

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkPriceEntity emkPrice,EmkPbEntity emkPb,EmkGlEntity emkGlEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价单添加成功";
        TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        try {
           /* EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"askNo",emkPrice.getXpNo());
            if(workOrderEntity == null){
                j.setSuccess(false);
                j.setMsg("您输入的询盘单号有误，请核准后在提交");
                return j;
            }*/
            emkPrice.setState("0");
            emkPrice.setFormType("1");
            emkPrice.setPirceNo(emkPrice.getSampleNo() + DateUtils.format(new Date(), "yyMMdd"));
            emkPriceService.save(emkPrice);

            //type 工单类型，workNum 单号，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
            EmkApprovalEntity approvalEntity = new EmkApprovalEntity();
            EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();

            ApprovalUtil.saveApproval(approvalEntity,0,emkPrice.getPirceNo(),emkPrice.getId(),user);
            systemService.save(approvalEntity);

            ApprovalUtil.saveApprovalDetail(approvalDetailEntity,approvalEntity.getId(),"报价单","instorageTask","提交",user);
            systemService.save(approvalDetailEntity);

            //保存坯布参数
            emkPb.setPriceId(emkPrice.getId());
            systemService.save(emkPb);

            //保存管理数据
            /*emkGlEntity.setPriceId(emkPrice.getId());
            systemService.save(emkGlEntity);*/
            //savePrice(emkPrice);
            saveSampleDetail(emkPrice,emkGlEntity,map,"0","");

            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
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
    public AjaxJson doUpdate(EmkPriceEntity emkPrice,EmkPbEntity emkPb,EmkGlEntity emkGlEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价单更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        EmkPriceEntity t = emkPriceService.get(EmkPriceEntity.class, map.get("peid"));
        if(Utils.notEmpty(t.getState())){
            if(!t.getState().equals("0")){
                j.setMsg("报价单已提交，无法进行修改");
                j.setSuccess(false);
                return j;
            }
        }
        try {
            /*if (!t.getState().equals("0")) {
                message = "存在已提交的报价单，请重新选择在提交！";
                j.setMsg(message);
                j.setSuccess(false);
                return  j;
            }*/
            MyBeanUtils.copyBeanNotNull2Bean(emkPrice, t);
            emkPriceService.saveOrUpdate(t);

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
            saveSampleDetail(t,emkGlEntity,map,"0","");
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
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
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-add");
    }
    @RequestMapping(params = "goTab")
    public ModelAndView goBtn(EmkPriceEntity emkPrice, HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/price/emkPrice-tab");
    }
    @RequestMapping(params = "goTab1")
    public ModelAndView goTab1(EmkPriceEntity emkPrice, HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/price/emkPrice-tab1");
    }
    @RequestMapping(params = "goBase")
    public ModelAndView goBase(EmkPriceEntity emkPrice, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkPrice);
                req.setAttribute("countMap", countMap);
                double a=0,b=0;
                a = Double.parseDouble(countMap.get("finishColums").toString());
                b = Double.parseDouble(countMap.get("Colums").toString());
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-base");
    }
    @RequestMapping(params = "goManage")
    public ModelAndView goRan(EmkGlEntity emkGlEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkGlEntity.getPriceId())) {
            emkGlEntity = emkPriceService.findUniqueByProperty(EmkGlEntity.class,"priceId",emkGlEntity.getPriceId());
            req.setAttribute("emkGlPage", emkGlEntity);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkGlEntity);
                req.setAttribute("countMap", countMap);
                double a=0,b=0;
                a = Double.parseDouble(countMap.get("finishColums").toString())-6;
                b = Double.parseDouble(countMap.get("Colums").toString())-6;
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-manage");
    }
    @RequestMapping(params = "goPb")
    public ModelAndView goPb(EmkPbEntity emkPbEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPbEntity.getPriceId())) {
            emkPbEntity = emkPriceService.findUniqueByProperty(EmkPbEntity.class,"priceId",emkPbEntity.getPriceId());
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
        return new ModelAndView("com/emk/storage/price/emkPrice-pb");
    }
    @RequestMapping(params = "goPrice")
    public ModelAndView goPrice(EmkPriceEntity emkPrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-price");
    }
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkPriceEntity emkPrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);

        }
        return new ModelAndView("com/emk/storage/price/emkPrice-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkPriceEntity emkPrice, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPrice.getId())) {
            emkPrice = emkPriceService.getEntity(EmkPriceEntity.class, emkPrice.getId());
            req.setAttribute("emkPricePage", emkPrice);
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
        List<EmkPriceEntity> emkPrices = emkPriceService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
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
        List<EmkPriceEntity> listEmkPrices = emkPriceService.getList(EmkPriceEntity.class);
        return Result.success(listEmkPrices);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取报价单信息", notes = "根据ID获取报价单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkPriceEntity task = emkPriceService.get(EmkPriceEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取报价单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建报价单")
    public ResponseMessage<?> create(@ApiParam(name = "报价单对象") @RequestBody EmkPriceEntity emkPrice, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkPriceEntity>> failures = validator.validate(emkPrice, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkPriceService.save(emkPrice);
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
        Set<ConstraintViolation<EmkPriceEntity>> failures = validator.validate(emkPrice, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkPriceService.saveOrUpdate(emkPrice);
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
            emkPriceService.deleteEntityById(EmkPriceEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("报价单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkPriceEntity emkPrice,EmkGlEntity emkGlEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "报价单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

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
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
                    if(Utils.isEmpty(b)){
                        //type 工单类型，workNum 单号，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
                        b = new EmkApprovalEntity();
                        EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
                        ApprovalUtil.saveApproval(b,2,t.getPirceNo(),t.getId(),user);
                        systemService.save(b);
                        ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"报价单","priceTask","提交",user);
                        systemService.save(approvalDetailEntity);
                    }
                    t.setState("1");
                    variables.put("optUser", t.getId());

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
                        if (task1.getTaskDefinitionKey().equals("priceTask")) {
                            taskService.complete(task1.getId(), variables);
                            t.setState("1");
                            b.setStatus(1);
                            saveApprvoalDetail(approvalDetail,"重新提交的报价单","priceTask",0,"重新提交报价单");
                            saveSmsAndEmailForMany("技术员","技术","您有【"+b.getCreateName()+"】重新提交的报价单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }

                        if (task1.getTaskDefinitionKey().equals("jsTask")) {
                            if (map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(22);
                                approvalDetail.setBpmName("技术【技术员】");
                                t.setState("22");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("采购员","技术【技术员】","您有【"+user.getRealName()+"】审核过的报价单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"技术【技术员】","jsTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"jsTask","priceTask","报价单");
                                t.setState("0");
                                b.setStatus(21);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("技术【技术员】","您有【"+user.getRealName()+"】回退的报价单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgTask")) {
                            if(map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(24);
                                approvalDetail.setBpmName("采购【采购员】");
                                t.setState("24");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("财务","采购【采购员】","您有【"+user.getRealName()+"】审核过的报价单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购【采购员】","cgTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cgTask","jsTask","报价单");
                                t.setState("23");
                                b.setStatus(23);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购【采购员】","您有【"+user.getRealName()+"】回退的报价单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cwTask")) {
                            MyBeanUtils.copyBeanNotNull2Bean(emkPrice, t);
                            t.setState("24");
                            saveSampleDetail(t,emkGlEntity,map,"0","");
                            if(Utils.notEmpty(map.get("isPass"))){
                                if(map.get("isPass").equals("1")){
                                    b.setStatus(25);
                                    t.setState("25");
                                    approvalDetail.setApproveStatus(0);
                                    taskService.complete(task1.getId(), variables);
                                    saveSmsAndEmailForMany("财务经理","财务","您有【"+user.getRealName()+"】审核过的报价单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                                    approvalDetail.setBpmName("财务");
                                    approvalDetail.setApproveAdvice("已核准报价单，提交审核");
                                    systemService.save(approvalDetail);
                                }
                            }

                        }
                        if (task1.getTaskDefinitionKey().equals("cwjlshTask")) {
                            if(map.get("isPass").equals("0")) {
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(26);
                                approvalDetail.setBpmName("财务经理");
                                t.setState("26");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("总经理","财务经理","您有【"+user.getRealName()+"】审核过的报价单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"财务经理","cwjlshTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"cwjlshTask","cwTask","报价单");
                                t.setState("27");
                                b.setStatus(27);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("财务经理","您有【"+user.getRealName()+"】回退的报价单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("zjlpzbjTask")) {
                            t.setState("2");
                            b.setStatus(2);
                            taskService.complete(task1.getId(), variables);

                            saveSmsAndEmailForOne("总经理","您有【"+user.getRealName()+"】审核过的报价单，单号："+b.getWorkNum()+"，请及时处理。",makerUser,user.getUserName());

                            //流转询盘单价格确认环节
                            if(Utils.notEmpty(t.getXpNo())){
                                EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"workNum",t.getXpNo());
                                task = taskService.createTaskQuery().taskAssignee(approvalEntity.getFormId()).list();
                                variables.put("optUser", approvalEntity.getFormId());

                                task1 = task.get(task.size() - 1);
                                if(task1.getTaskDefinitionKey().equals("bjTask")) {
                                    taskService.complete(task1.getId(), variables);
                                }

                                EmkApprovalDetailEntity papprovalDetail = ApprovalUtil.saveApprovalDetail(approvalEntity.getId(),user,approvalEntity,"价格已审核");
                                papprovalDetail.setBpmName("财务通过报价");
                                papprovalDetail.setApproveStatus(0);
                                approvalEntity.setStatus(30);

                                systemService.executeSql("update emk_enquiry set state=30 where id=?",approvalEntity.getFormId());
                                makerUser = systemService.findUniqueByProperty(TSUser.class,"userName",approvalEntity.getCreateBy());
                                saveSmsAndEmailForOne("总经理","您有【"+user.getRealName()+"】审核过的报价单，报价单号："+b.getWorkNum()+"，请及时处理询盘单："+approvalEntity.getWorkNum()+",价格确认",makerUser,user.getUserName());
                            }
                        }
                        if(!task1.getTaskDefinitionKey().equals("cwTask")) {
                            systemService.save(approvalDetail);
                        }
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("price", "emkPriceEntity", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);

                        saveSmsAndEmailForMany("技术员","技术","您有【"+b.getCreateName()+"】提交的报价单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        t.setState("1");
                        b.setStatus(1);
                        b.setBpmStatus("0");
                        b.setProcessName("技术【技术员】");

                    }
                    systemService.saveOrUpdate(t);
                    systemService.saveOrUpdate(b);
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
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkPrice.getId());
        if(Utils.notEmpty(approvalEntity)){
            List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
            req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
            req.setAttribute("approvalEntity", approvalEntity);
            req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
        }

        return new ModelAndView("com/emk/storage/price/time");
    }
}
