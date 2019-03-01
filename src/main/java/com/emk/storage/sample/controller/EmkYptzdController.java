package com.emk.storage.sample.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.gl.entity.EmkGlEntity;
import com.emk.storage.pb.entity.EmkPbEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.sample.entity.EmkSampleContentEntity;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.sample.service.EmkSampleServiceI;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.samplegx.entity.EmkSampleGxEntity;
import com.emk.storage.sampleran.entity.EmkSampleRanEntity;
import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;
import com.emk.util.*;
import com.emk.workorder.workorder.entity.EmkWorkOrderEntity;
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

@Api(value = "EmkSample", description = "样品通知单", tags = "emkSampleController")
@Controller
@RequestMapping("/emkYptzdController")
public class EmkYptzdController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkYptzdController.class);
    @Autowired
    private EmkSampleServiceI emkSampleService;
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
        return new ModelAndView("com/emk/storage/sample/emkSampleList");
    }

    @RequestMapping(params = "detailMxList")
    public ModelAndView detailMxList(HttpServletRequest request) {
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        request.setAttribute("colorList", list);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("proOrderId"))) {
            StringBuilder sql = new StringBuilder();
            sql.append("select t.id,t.enquiry_id enquiryId,t.color,t.color_value colorVal,t.size,\n" +
                    " (select t1.total from emk_enquiry_detail t1 where t1.enquiry_id=t.enquiry_id and t1.color=t.color and t1.size='s' limit 0,1) stotal,\n" +
                    " (select t2.total from emk_enquiry_detail t2 where t2.enquiry_id=t.enquiry_id and t2.color=t.color and t2.size='m' limit 0,1) mtotal,\n" +
                    " (select t3.total from emk_enquiry_detail t3 where t3.enquiry_id=t.enquiry_id and t3.color=t.color and t3.size='l' limit 0,1) ltotal,\n" +
                    " (select t4.total from emk_enquiry_detail t4 where t4.enquiry_id=t.enquiry_id and t4.color=t.color and t4.size='xl' limit 0,1) xltotal,\n" +
                    " (select t5.total from emk_enquiry_detail t5 where t5.enquiry_id=t.enquiry_id and t5.color=t.color and t5.size='2xl' limit 0,1) xxltotal\n" +
                    " from emk_enquiry_detail t where t.enquiry_id=? group by t.enquiry_id,t.color,t.color_value  order by t.sort_desc asc \n");
            List<Map<String, Object>> emkProOrderDetailEntities = systemService.findForJdbc(sql.toString(),map.get("proOrderId"));
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/storage/sample/detailMxList");
    }

    @RequestMapping(params = "contentList")
    public ModelAndView contentList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if(Utils.notEmpty(map.get("sampleId"))){
            EmkSampleEntity emkSample = emkSampleService.getEntity(EmkSampleEntity.class, map.get("sampleId").toString());
            request.setAttribute("emkSamplePage", emkSample);
            List<EmkSampleContentEntity> sampleContentEntities = systemService.findHql("from EmkSampleContentEntity where sampleId=? and type=?",map.get("sampleId"),map.get("type"));
            request.setAttribute("sampleContentEntities", sampleContentEntities);
        }
        if (map.get("type").equals("0")) {
            return new ModelAndView("com/emk/storage/sample/contentList");
        }
        if (map.get("type").equals("1")) {
            return new ModelAndView("com/emk/storage/sample/contentList2");
        }
        if (map.get("type").equals("2")) {
            return new ModelAndView("com/emk/storage/sample/contentList3");
        }
        return new ModelAndView("com/emk/storage/sample/contentList");
    }

    @RequestMapping(params = "gxList")
    public ModelAndView gxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleGxEntity> emkSampleGxEntities = systemService.findHql("from EmkSampleGxEntity where sampleId=?", map.get("sampleId"));
            request.setAttribute("emkSampleGxEntities", emkSampleGxEntities);
        }
        return new ModelAndView("com/emk/storage/sample/gxList");
    }
    @RequestMapping(params = "ranList")
    public ModelAndView ranList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='bmzl'");
        request.setAttribute("bmzlList", list);
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleRanEntity> emkSampleRanEntities = systemService.findHql("from EmkSampleRanEntity where sampleId=? and type=0 ", map.get("sampleId"));
            request.setAttribute("emkSampleRanEntities", emkSampleRanEntities);
        }
        return new ModelAndView("com/emk/storage/sample/ranList");
    }
    @RequestMapping(params = "yinList")
    public ModelAndView yinList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='yhgyzl'");
        request.setAttribute("yhgyzlList", list);
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleYinEntity> emkSampleYinEntities = systemService.findHql("from EmkSampleYinEntity where sampleId=?", map.get("sampleId"));
            request.setAttribute("emkSampleYinEntities", emkSampleYinEntities);
        }
        return new ModelAndView("com/emk/storage/sample/yinList");
    }

    /*@RequestMapping(params = "list0")
    public ModelAndView list0(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/sample/emkSampleList0");
    }*/

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkSample, request.getParameterMap());

        cq.add();
        emkSampleService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkSampleEntity emkSample, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSample = systemService.getEntity(EmkSampleEntity.class, emkSample.getId());
        message = "样品通知单删除成功";
        try {
            if (!emkSample.getState().equals("0")) {
                message = "样品通知单已经提交处理，无法删除";
                j.setMsg(message);
                j.setSuccess(false);
                return j;
            }
            emkSampleService.delete(emkSample);
            systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[]{emkSample.getId()});
            systemService.executeSql("delete from emk_sample_total where sample_id=?", new Object[]{emkSample.getId()});

            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单删除失败";
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
        message = "样品通知单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleEntity emkSample = systemService.getEntity(EmkSampleEntity.class, id);
                if (!emkSample.getState().equals("0")) {
                    message = "样品通知单单已经提交处理，无法删除";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }
//                WebFileUtils.delete( request.getRealPath("/")+emkSample.get());
                systemService.executeSql("delete from emk_sample_detail where sample_id=?", emkSample.getId());
                systemService.executeSql("delete from emk_sample_content where sample_id=?", emkSample.getId());
                systemService.executeSql("delete from emk_sample_gx where sample_id=?", emkSample.getId());

                emkSampleService.delete(emkSample);

                /*systemService.executeSql("delete from emk_sample_ran where sample_id=?", emkSample.getId());
                systemService.executeSql("delete from emk_sample_yin where sample_id=?", emkSample.getId());
//                systemService.executeSql("delete from emk_sample_total where sample_id=?", emkSample.getId());*/
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkSampleEntity emkSample, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品通知单添加成功";
        try {
            emkSample.setState("0");
            if(emkSample.getType().equals("ss") || emkSample.getType().equals("cq")){
                /*if(emkSample.getOrderNo() == null || emkSample.getOrderNo().equals("")){
                    j.setSuccess(false);
                    j.setMsg("订单号不能为空");
                    return j;
                }*/
            }
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());

            emkSample.setKdTime(DateUtils.format(new Date(), "yyyy-MM-dd"));
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(SAMPLE_NUM, 2)),0)+1 AS signed) orderNum from emk_sample");
            emkSample.setSampleNum("YPTZD" + DateUtils.format(new Date(), "yyMMdd") + "A" + String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkSample.setXqdh("YPXQ" +emkSample.getCusNum()+ DateUtils.format(new Date(), "yyMMdd")+ String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkSampleService.save(emkSample);

            //保存明细数据
            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                EmkEnquiryDetailEntity orderMxEntity = null;
                EmkEnquiryDetailEntity emkEnquiryDetailEntity = null;
                for (int i = 0; i < rows; i++) {
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].color"))){
                        orderMxEntity = new EmkEnquiryDetailEntity();
                        orderMxEntity.setEnquiryId(emkSample.getId());
                        orderMxEntity.setColor(map.get("orderMxList["+i+"].color").toString());
                        orderMxEntity.setSortDesc(String.valueOf(i+1));
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
                        systemService.save(orderMxEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent");
            //保存说明数据一
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].content00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].content00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("0");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent2");
            //保存说明数据二
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bcontent00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].bcontent00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("1");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent3");
            //保存说明数据三
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].ccontent00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].ccontent00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("2");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListID");
            //保存原料面料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setPrecent((String)map.get("orderMxList["+i+"].precent"));
                        emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].chengben").toString()));
                        emkSampleDetailEntity.setSampleId(emkSample.getId());
                        emkSampleDetailEntity.setType("0");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            dataRows = (String)map.get("orderMxListID2");
            //保存缝制辅料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bproZnName"))) {
                        emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].bproZnName"));
                        emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].bproNum"));
                        emkSampleDetailEntity.setPrecent((String)map.get("orderMxList["+i+"].bprecent"));
                        emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].byongliang").toString()));
                        emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].bgysCode"));
                        emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].bsignPrice"));
                        emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].bsunhaoPrecent").toString()));
                        emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].bchengben").toString()));
                        emkSampleDetailEntity.setSampleId(emkSample.getId());
                        emkSampleDetailEntity.setType("1");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            dataRows = (String)map.get("orderMxListID3");
            //保存包装辅料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].cproZnName"))) {
                        emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].cproZnName"));
                        emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].cproNum"));
                        emkSampleDetailEntity.setPrecent((String)map.get("orderMxList["+i+"].cprecent"));
                        emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].cyongliang").toString()));
                        emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].cgysCode"));
                        emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].csignPrice"));
                        emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].csunhaoPrecent").toString()));
                        emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].cchengben").toString()));
                        emkSampleDetailEntity.setSampleId(emkSample.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleEntity emkSample, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品通知单更新成功";
       /* if(emkSample.getType().equals("ss") || emkSample.getType().equals("cq")){
            if(emkSample.getOrderNo() == null || emkSample.getOrderNo().equals("")){
                j.setSuccess(false);
                j.setMsg("订单号不能为空");
                return j;
            }
        }*/
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());

        EmkSampleEntity t = emkSampleService.get(EmkSampleEntity.class, emkSample.getId());
        try {
            if (!t.getState().equals("0")) {
                message = "存在已提交的打样单，请重新选择在提交！";
                j.setMsg(message);
                j.setSuccess(false);
                return  j;
            }

            MyBeanUtils.copyBeanNotNull2Bean(emkSample, t);
            emkSampleService.saveOrUpdate(t);

            //保存明细数据
            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_enquiry_detail where enquiry_id=?",t.getId());
                int rows = Integer.parseInt(dataRows);
                EmkEnquiryDetailEntity orderMxEntity = null;
                EmkEnquiryDetailEntity emkEnquiryDetailEntity = null;
                for (int i = 0; i < rows; i++) {
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].color"))){
                        orderMxEntity = new EmkEnquiryDetailEntity();
                        orderMxEntity.setEnquiryId(t.getId());
                        orderMxEntity.setSortDesc(String.valueOf(i+1));
                        orderMxEntity.setColor(map.get("orderMxList["+i+"].color").toString());
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
            dataRows = (String)map.get("orderMxListIDContent");
            //保存说明数据一
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_content where sample_id=? and type=0",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].content00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].content00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("0");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent2");
            //保存说明数据二
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_content where sample_id=? and type=1",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bcontent00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].bcontent00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("1");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent3");
            //保存说明数据三
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_content where sample_id=? and type=2",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].ccontent00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].ccontent00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("2");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListID");
            //保存原料面料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=0",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
                        emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].proZnName"));
                        emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].proNum"));
                        emkSampleDetailEntity.setPrecent((String)map.get("orderMxList["+i+"].precent"));
                        emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang").toString()));
                        emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].gysCode"));
                        emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].signPrice"));
                        emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent").toString()));
                        emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].chengben").toString()));
                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("0");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListID2");
            //保存缝制辅料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=1",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bproZnName"))) {
                        emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].bproZnName"));
                        emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].bproNum"));
                        emkSampleDetailEntity.setPrecent((String)map.get("orderMxList["+i+"].bprecent"));
                        emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].byongliang").toString()));
                        emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].bgysCode"));
                        emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].bsignPrice"));
                        emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].bsunhaoPrecent").toString()));
                        emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].bchengben").toString()));
                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("1");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            dataRows = (String)map.get("orderMxListID3");
            //保存包装辅料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=2",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].cproZnName"))) {
                        emkSampleDetailEntity.setProZnName((String)map.get("orderMxList["+i+"].cproZnName"));
                        emkSampleDetailEntity.setProNum((String)map.get("orderMxList["+i+"].cproNum"));
                        emkSampleDetailEntity.setPrecent((String)map.get("orderMxList["+i+"].cprecent"));
                        emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].cyongliang").toString()));
                        emkSampleDetailEntity.setGysCode((String)map.get("orderMxList["+i+"].cgysCode"));
                        emkSampleDetailEntity.setSignPrice((String)map.get("orderMxList["+i+"].csignPrice"));
                        emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].csunhaoPrecent").toString()));
                        emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].cchengben").toString()));
                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkSampleEntity emkSample, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");

        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
        }

        return new ModelAndView("com/emk/storage/sample/emkSample-add");
    }

    @RequestMapping(params = "goTab")
    public ModelAndView goBtn(EmkSampleEntity emkSample, HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/sample/emkSample-tab");
    }
    @RequestMapping(params = "goBase")
    public ModelAndView goBase(EmkSampleEntity emkSample, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkSample);
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
        return new ModelAndView("com/emk/storage/sample/emkSample-base");
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
        return new ModelAndView("com/emk/storage/price/emkPrice-pb");
    }


    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkSampleEntity emkSample, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkSample);
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
        return new ModelAndView("com/emk/storage/sample/emkSample-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkSampleEntity emkSample, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
        }
        return new ModelAndView("com/emk/storage/sample/emkSample-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSample, request.getParameterMap());
        List<EmkSampleEntity> emkSamples = emkSampleService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "样品通知单");
        modelMap.put("entity", EmkSampleEntity.class);
        modelMap.put("params", new ExportParams("样品通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSamples);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "样品通知单");
        modelMap.put("entity", EmkSampleEntity.class);
        modelMap.put("params", new ExportParams("样品通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "样品通知单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleEntity>> list() {
        List<EmkSampleEntity> listEmkSamples = emkSampleService.getList(EmkSampleEntity.class);
        return Result.success(listEmkSamples);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取样品通知单信息", notes = "根据ID获取样品通知单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleEntity task = emkSampleService.get(EmkSampleEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取样品通知单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建样品通知单")
    public ResponseMessage<?> create(@ApiParam(name = "样品通知单对象") @RequestBody EmkSampleEntity emkSample, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleEntity>> failures = validator.validate(emkSample, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleService.save(emkSample);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品通知单信息保存失败");
        }
        return Result.success(emkSample);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新样品通知单", notes = "更新样品通知单")
    public ResponseMessage<?> update(@ApiParam(name = "样品通知单对象") @RequestBody EmkSampleEntity emkSample) {
        Set<ConstraintViolation<EmkSampleEntity>> failures = validator.validate(emkSample, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleService.saveOrUpdate(emkSample);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新样品通知单信息失败");
        }
        return Result.success("更新样品通知单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除样品通知单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkSampleService.deleteEntityById(EmkSampleEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品通知单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkSampleEntity emkSampleEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "打样单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkSampleEntity.getId() == null) || (emkSampleEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkSampleEntity sampleEntity = systemService.getEntity(EmkSampleEntity.class, id);
                    if (!sampleEntity.getState().equals("0")) {
                        message = "存在已提交的打样单，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkSampleEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkSampleEntity t = emkSampleService.get(EmkSampleEntity.class, id);
                    t.setState("1");
                    variables.put("inputUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("sampleTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("leadTask")) {
                          /*  t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkSampleEntity.getLeadAdvice());
                            if (emkSampleEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkSampleEntity.getIsPass());
                                taskService.complete(task1.getId(), variables);
                                t.setState("2");

                                EmkPriceEntity priceEntity = systemService.findUniqueByProperty(EmkPriceEntity.class,"pirceNo",t.getPirceNo());

                                EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"askNo",priceEntity.getXpNo());
                                workOrderEntity.setSampleNum(t.getSampleNum());
                                workOrderEntity.setSampleUserId(user.getUserName());
                                workOrderEntity.setSampleUser(user.getRealName());
                                workOrderEntity.setSampleDate(DateUtil.getCurrentTimeString(null));
                                workOrderEntity.setSampleAdvice(emkSampleEntity.getLeadAdvice());
                                systemService.saveOrUpdate(workOrderEntity);

                                variables.put("inputUser", workOrderEntity.getId());
                                task = taskService.createTaskQuery().taskAssignee(workOrderEntity.getId()).list();
                                task1 = task.get(task.size() - 1);
                                taskService.complete(task1.getId(), variables);


                            } else {
                                List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(t.getId()).list();

                                List<Task> taskList = taskService.createTaskQuery().taskAssignee(t.getId()).list();
                                if (taskList.size() > 0) {
                                    Task taskH = (Task)taskList.get(taskList.size() - 1);
                                    HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
                                    FlowUtil.turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
                                    Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC", t.getId(), historicTaskInstance.getTaskDefinitionKey());
                                    String[] activitIdArr = activityMap.get("ids").toString().split(",");
                                    String[] taskIdArr = activityMap.get("taskids").toString().split(",");
                                    systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(), taskIdArr[1]);
                                    systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", activitIdArr[0], activitIdArr[1] );
                                }
                                t.setState("0");
                            }*/
                        }
                        systemService.saveOrUpdate(t);

                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("sample", "emkSampleEntity", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);
                    }

                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "打样单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkSampleEntity emkSampleEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleEntity.getId())) {
            emkSampleEntity = emkSampleService.getEntity(EmkSampleEntity.class, emkSampleEntity.getId());
            req.setAttribute("emkSample", emkSampleEntity);
        }
        return new ModelAndView("com/emk/storage/sample/emkSample-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkSampleEntity emkSampleEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='sampleTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_sample t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

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
        emkSampleEntity = emkSampleService.getEntity(EmkSampleEntity.class, emkSampleEntity.getId());
        req.setAttribute("emkSample", emkSampleEntity);
        return new ModelAndView("com/emk/storage/sample/time");


    }
}
