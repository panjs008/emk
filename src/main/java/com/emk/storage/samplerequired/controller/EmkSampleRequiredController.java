package com.emk.storage.samplerequired.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.pb.entity.EmkPbEntity;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.samplegx.entity.EmkSampleGxEntity;
import com.emk.storage.sampleprice.entity.EmkSamplePriceEntity;
import com.emk.storage.sampleran.entity.EmkSampleRanEntity;
import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.storage.samplerequired.service.EmkSampleRequiredServiceI;
import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
            List<EmkEnquiryDetailEntity> detailEntities = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", map.get("priceId"));
            request.setAttribute("emkProOrderDetailEntities", detailEntities);
        }
        return new ModelAndView("com/emk/storage/samplerequired/detailMxList");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
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

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkSampleRequiredEntity emkSampleRequired,EmkPbEntity emkPb, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品需求单添加成功";
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(REQUIRED_NO, 3)),0)+1 AS signed) orderNum from emk_sample_required");
            emkSampleRequired.setRequiredNo("YPXP" + emkSampleRequired.getCusNum() + DateUtils.format(new Date(), "yyMMdd") + String.format("%03d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
            emkSampleRequiredService.save(emkSampleRequired);

            EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
            }
            samplePriceEntity.setBz(map.get("pbz").toString());
            samplePriceEntity.setEnquiryId(emkSampleRequired.getId());
            samplePriceEntity.setState(map.get("pstate").toString());
            samplePriceEntity.setState("0");
            systemService.save(samplePriceEntity);

            //保存坯布参数
            emkPb.setPriceId(samplePriceEntity.getId());
            systemService.save(emkPb);

            String dataRows = (String)map.get("orderMxListID");
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
                        emkSampleDetailEntity.setSampleId(samplePriceEntity.getId());
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
                        emkSampleDetailEntity.setSampleId(samplePriceEntity.getId());
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
                        emkSampleDetailEntity.setSampleId(samplePriceEntity.getId());
                        emkSampleDetailEntity.setType("2");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }

            dataRows = (String)map.get("gxListID");
            //保存工序数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleGxEntity emkSampleGxEntity = new EmkSampleGxEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].gxmc"))) {
                        emkSampleGxEntity.setGxmc((String)map.get("orderMxList["+i+"].gxmc"));
                        emkSampleGxEntity.setDjhs(Double.parseDouble(map.get("orderMxList["+i+"].gxdjhs").toString()));
                        emkSampleGxEntity.setUnit((String)map.get("orderMxList["+i+"].gxunit"));
                        emkSampleGxEntity.setProductTotal(Integer.parseInt(map.get("orderMxList["+i+"].gxproductTotal").toString()));
                        emkSampleGxEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].gxchengben").toString()));
                        emkSampleGxEntity.setSampleId(samplePriceEntity.getId());

                        systemService.save(emkSampleGxEntity);
                    }
                }
            }

            dataRows = (String)map.get("ranListID");
            //保存染色数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleRanEntity emkSampleRanEntity = new EmkSampleRanEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bmzl"))) {
                        emkSampleRanEntity.setBuType((String)map.get("orderMxList["+i+"].bmzl"));
                        emkSampleRanEntity.setPrice(Double.parseDouble(map.get("orderMxList["+i+"].rsdj").toString()));
                        emkSampleRanEntity.setOneWeight(Double.parseDouble(map.get("orderMxList["+i+"].rskz").toString()));
                        emkSampleRanEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].rscb").toString()));
                        emkSampleRanEntity.setSampleId(samplePriceEntity.getId());
                        emkSampleRanEntity.setType("0");
                        systemService.save(emkSampleRanEntity);
                    }
                }
            }

            dataRows = (String)map.get("yinListID");
            //保存印花数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleYinEntity emkSampleYinEntity = new EmkSampleYinEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].yhzl"))) {
                        emkSampleYinEntity.setGyzy((String)map.get("orderMxList["+i+"].yhzl"));
                        emkSampleYinEntity.setSize((String)map.get("orderMxList["+i+"].yhdx"));
                        emkSampleYinEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].yhcb").toString()));
                        emkSampleYinEntity.setSampleId(samplePriceEntity.getId());

                        systemService.save(emkSampleYinEntity);
                    }
                }
            }
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
    public AjaxJson doUpdate(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品需求单更新成功";
        EmkSampleRequiredEntity t = emkSampleRequiredService.get(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
        try {
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());

            MyBeanUtils.copyBeanNotNull2Bean(emkSampleRequired, t);
            emkSampleRequiredService.saveOrUpdate(t);

            systemService.executeSql("delete from emk_sample_price where ENQUIRY_ID=?", new Object[]{emkSampleRequired.getId()});
            EmkSamplePriceEntity samplePriceEntity = new EmkSamplePriceEntity();
            if(map.get("money") != null && !map.get("money").equals("")){
                samplePriceEntity.setMoney(Double.valueOf(Double.parseDouble(map.get("money").toString())));
            }
            samplePriceEntity.setBz(map.get("pbz").toString());
            samplePriceEntity.setEnquiryId(emkSampleRequired.getId());
            samplePriceEntity.setState(map.get("pstate").toString());
            systemService.save(samplePriceEntity);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品需求单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
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

    @RequestMapping(params = "goTab")
    public ModelAndView goBtn(EmkSampleEntity emkSample, HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-tab");
    }
    @RequestMapping(params = "goBase")
    public ModelAndView goBase(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkSampleRequired);
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
        return new ModelAndView("com/emk/storage/price/emkPrice-pb");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkSampleRequiredEntity emkSampleRequired, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleRequired.getId())) {
            emkSampleRequired = emkSampleRequiredService.getEntity(EmkSampleRequiredEntity.class, emkSampleRequired.getId());
            req.setAttribute("emkSampleRequiredPage", emkSampleRequired);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkSampleRequired);
                req.setAttribute("countMap", countMap);
                double a=0,b=0;
                a = Double.parseDouble(countMap.get("finishColums").toString());
                b = Double.parseDouble(countMap.get("Colums").toString());
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(org.jeecgframework.core.util.DateUtils.str2Date(emkSampleRequired.getYsDate(), org.jeecgframework.core.util.DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = org.jeecgframework.core.util.DateUtils.dateDiff('d',cal1,cal2);
            req.setAttribute("levelDays",day);
            EmkSamplePriceEntity samplePriceEntity = (EmkSamplePriceEntity) systemService.findUniqueByProperty(EmkSamplePriceEntity.class, "enquiryId", emkSampleRequired.getId());
            req.setAttribute("samplePriceEntity", samplePriceEntity);
        }
        return new ModelAndView("com/emk/storage/samplerequired/emkSampleRequired-update");
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
