package com.emk.bill.proorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;

import com.emk.bill.bzgyd.entity.EmkBzgydEntity;
import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.bill.proorder.service.EmkProOrderServiceI;
import com.emk.bill.proorderbarcode.entity.EmkProOrderBarcodeEntity;
import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;
import com.emk.bill.proorderdetail.entity.EmkProOrderDetailEntity;

import com.emk.bill.proorderpackage.entity.EmkProOrderPackageEntity;
import com.emk.bill.yjyhtime.entity.EmkYjyhTimeEntity;
import com.emk.check.sizecheck.entity.EmkSizeEntity;

import com.emk.check.sizecheck.entity.EmkSizeTotalEntityD;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.util.ApprovalUtil;
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

@Api(value = "EmkProOrder", description = "生产订单", tags = "emkProOrderController")
@Controller
@RequestMapping("/emkProOrderController")
public class EmkProOrderController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProOrderController.class);
    @Autowired
    private EmkProOrderServiceI emkProOrderService;

    @Autowired
    private Validator validator;


    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/proorder/emkProOrderList");
    }

    @RequestMapping(params = "detailMxList")
    public ModelAndView detailMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        request.setAttribute("colorList", list);

        if (Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkProOrderDetailEntity> emkProOrderDetailEntities = systemService.findHql("from EmkProOrderDetailEntity where proOrderId=?", map.get("proOrderId"));
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);

            EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",map.get("proOrderId"));
            request.setAttribute("emkSizePage", emkSizeEntity);
        }
        return new ModelAndView("com/emk/bill/proorder/detailMxList");
    }

    @RequestMapping(params = "barCodeMxListTab")
    public ModelAndView barCodeMxListTab(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/proorder/barCodeMxListTab");
    }

    @RequestMapping(params = "barCodeMxList")
    public ModelAndView barCodeMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        request.setAttribute("colorList", list);
        list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='size'");
        request.setAttribute("sizeList", list);
        if(Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkProOrderBarcodeEntity> emkProOrderBarcodeEntities = systemService.findHql("from EmkProOrderBarcodeEntity where orderId=? and type=?", map.get("proOrderId"),map.get("type"));
            request.setAttribute("emkProOrderBarcodeEntities", emkProOrderBarcodeEntities);
        }
        if(map.get("type").equals("0")){
            return new ModelAndView("com/emk/bill/proorder/barCodeMxList");
        }else if(map.get("type").equals("1")){
            return new ModelAndView("com/emk/bill/proorder/barCodeMxList1");
        }else if(map.get("type").equals("2")){
            return new ModelAndView("com/emk/bill/proorder/barCodeMxList2");
        }
        return new ModelAndView("com/emk/bill/proorder/barCodeMxList");
    }

    @RequestMapping(params = "boxMxListTab")
    public ModelAndView boxMxListTab(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/proorder/boxMxListTab");
    }

    @RequestMapping(params = "boxMxList")
    public ModelAndView boxMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='size'");
        request.setAttribute("sizeList", list);
        if(Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkProOrderBoxEntity> emkProOrderBoxEntities = systemService.findHql("from EmkProOrderBoxEntity where orderId=? and boxType=?", map.get("proOrderId"),map.get("type"));
            request.setAttribute("emkProOrderBoxEntities", emkProOrderBoxEntities);
        }
        if(map.get("type").equals("0")){
            return new ModelAndView("com/emk/bill/proorder/boxMxList");
        }else if(map.get("type").equals("1")){
            return new ModelAndView("com/emk/bill/proorder/boxMxList1");
        }else if(map.get("type").equals("2")){
            return new ModelAndView("com/emk/bill/proorder/boxMxList2");
        }else if(map.get("type").equals("3")){
            return new ModelAndView("com/emk/bill/proorder/boxMxList3");
        }
        return new ModelAndView("com/emk/bill/proorder/boxMxList");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 order by company_code asc ");
        request.setAttribute("gysList", list);
        if (Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=0", map.get("proOrderId"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/bill/proorder/orderMxList");
    }

    @RequestMapping(params = "orderMxList2")
    public ModelAndView orderMxList2(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 order by company_code asc ");
        request.setAttribute("gysList", list);
        if (Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=1", map.get("proOrderId"));
            request.setAttribute("emkSampleDetailEntities1", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/bill/proorder/orderMxList2");
    }

    @RequestMapping(params = "orderMxList3")
    public ModelAndView orderMxList3(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 order by company_code asc ");
        request.setAttribute("gysList", list);
        if (Utils.notEmpty(map.get("proOrderId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=2", map.get("proOrderId"));
            request.setAttribute("emkSampleDetailEntities2", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/bill/proorder/orderMxList3");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkProOrder, request.getParameterMap());


        cq.add();
        emkProOrderService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkProOrderEntity emkProOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProOrder = systemService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());
        message = "生产订单删除成功";
        try {
            if (!emkProOrder.getState().equals("0")) {
                message = "订单已经提交处理，无法删除";
                j.setMsg(message);
                j.setSuccess(false);
                return j;
            }
            systemService.executeSql("delete from emk_pro_order_detail where PRO_ORDER_ID=?", emkProOrder.getId());
            emkProOrderService.delete(emkProOrder);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单删除失败";
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
        message = "生产订单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProOrderEntity emkProOrder = systemService.getEntity(EmkProOrderEntity.class, id);
                if (!emkProOrder.getState().equals("0")) {
                    message = "存在已提交的订单，请重新选择在提交订单！";
                    j.setSuccess(false);
                    j.setMsg(message);
                    return  j;
                }
                systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", emkProOrder.getId());
                systemService.executeSql("delete from emk_sample_detail where sample_id=?", emkProOrder.getId());
                systemService.executeSql("delete from emk_pro_order_barcode where order_id=?", emkProOrder.getId());
                systemService.executeSql("delete from emk_pro_order_box where order_id=?", emkProOrder.getId());
                systemService.executeSql("delete from emk_pro_order_package where order_id=?", emkProOrder.getId());
                systemService.executeSql("delete from emk_size_total where FIND_IN_SET(p_id,(SELECT GROUP_CONCAT(id) FROM emk_pro_order_detail where PRO_ORDER_ID=?))", emkProOrder.getId());
                systemService.executeSql("delete from emk_pro_order_detail where PRO_ORDER_ID=?",emkProOrder.getId());

                emkProOrderService.delete(emkProOrder);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkProOrderEntity emkProOrder,EmkSizeEntity emkSize, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单添加成功";
        try {
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(ORDER_NO, 6)),0)+1 AS signed) orderNum from emk_pro_order");
            emkProOrder.setOrderNo("D" + DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkProOrder.setState("0");
            emkProOrderService.save(emkProOrder);

            emkSize.setFormId(emkProOrder.getId());
            systemService.save(emkSize);

            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            saveOrderData(emkProOrder,map);
           /* EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",emkProOrder.getWorkNo());
            if(workOrderEntity != null){
                EmkPriceEntity priceEntity = systemService.findUniqueByProperty(EmkPriceEntity.class,"xpNo",workOrderEntity.getAskNo());
                EmkSampleDetailEntity emkSampleDetailEntity = null;

                List<EmkSampleDetailEntity> sampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId=?", priceEntity.getId());
                for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                    emkSampleDetailEntity = new EmkSampleDetailEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                    emkSampleDetailEntity.setId(null);
                    emkSampleDetailEntity.setSampleId(emkProOrder.getId());
                    systemService.save(emkSampleDetailEntity);
                }
            }*/


            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkProOrderEntity emkProOrder,EmkSizeEntity emkSize, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订单更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        EmkProOrderEntity t = emkProOrderService.get(EmkProOrderEntity.class, emkProOrder.getId());
        EmkSizeEntity t2 = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",emkProOrder.getId());
        try {
            if(!t.getState().equals("0")){
                j.setMsg("订单已提交，请重新选择");
                j.setSuccess(false);
                return j;
            }
            MyBeanUtils.copyBeanNotNull2Bean(emkProOrder, t);

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

            emkProOrderService.saveOrUpdate(t);
            saveOrderData(t,map);

          /*  EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"workNo",t.getWorkNo());
            if(workOrderEntity != null){
                EmkPriceEntity priceEntity = systemService.findUniqueByProperty(EmkPriceEntity.class,"xpNo",workOrderEntity.getAskNo());
                EmkSampleDetailEntity emkSampleDetailEntity = null;
                systemService.executeSql("delete from emk_sample_detail where SAMPLE_ID=?",t.getId());
                List<EmkSampleDetailEntity> sampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId=?", t.getId());
                for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                    emkSampleDetailEntity = new EmkSampleDetailEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                    emkSampleDetailEntity.setId(null);
                    emkSampleDetailEntity.setSampleId(t.getId());
                    systemService.save(emkSampleDetailEntity);
                }
            }*/

            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());
            req.setAttribute("emkProOrderPage", emkProOrder);
        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());

           /* Calendar cal1 = Calendar.getInstance();
            cal1.setTime(org.jeecgframework.core.util.DateUtils.str2Date(emkProOrder.getRecevieDate(), org.jeecgframework.core.util.DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = org.jeecgframework.core.util.DateUtils.dateDiff('d',cal1,cal2);
            if(day<0){
                day = 0;
            }
            req.setAttribute("levelDays",day);

            Map infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total,ifnull(ROUND(SUM(t.total*t.price),2),0) sumprice FROM emk_enquiry_detail t WHERE t.enquiry_id=?",emkProOrder.getId());

            emkProOrder.setSumTotal(Integer.parseInt(infoMap.get("total").toString()));
            emkProOrder.setSumMoney(Double.parseDouble(infoMap.get("sumprice").toString()));*/

            req.setAttribute("emkProOrderPage", emkProOrder);

        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(org.jeecgframework.core.util.DateUtils.str2Date(emkProOrder.getRecevieDate(), org.jeecgframework.core.util.DateUtils.date_sdf));
            Calendar cal2 = Calendar.getInstance();
            int day = org.jeecgframework.core.util.DateUtils.dateDiff('d',cal1,cal2);
            if(day<0){
                day = 0;
            }
            req.setAttribute("levelDays",day);

            Map infoMap = systemService.findOneForJdbc("SELECT ifnull(SUM(t.total),0) total,ifnull(ROUND(SUM(t.total*t.price),2),0) sumprice FROM emk_enquiry_detail t WHERE t.enquiry_id=?",emkProOrder.getId());
            emkProOrder.setSumTotal(Integer.parseInt(infoMap.get("total").toString()));
            emkProOrder.setSumMoney(Double.parseDouble(infoMap.get("sumprice").toString()));
            req.setAttribute("emkProOrderPage", emkProOrder);

        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-update2");
    }

    @RequestMapping(params = "goTab")
    public ModelAndView goBtn(EmkProOrderEntity emkProOrderEntity, HttpServletRequest req) {
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-tab");
    }
    @RequestMapping(params = "goBase")
    public ModelAndView goBase(EmkProOrderEntity emkProOrderEntity, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkProOrderEntity.getId())) {
            emkProOrderEntity = systemService.getEntity(EmkProOrderEntity.class, emkProOrderEntity.getId());
            req.setAttribute("emkProOrderPage", emkProOrderEntity);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkProOrderEntity);
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
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-base");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProOrderController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProOrder, request.getParameterMap());
        List<EmkProOrderEntity> emkProOrders = emkProOrderService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "生产订单");
        modelMap.put("entity", EmkProOrderEntity.class);
        modelMap.put("params", new ExportParams("生产订单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProOrders);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "生产订单");
        modelMap.put("entity", EmkProOrderEntity.class);
        modelMap.put("params", new ExportParams("生产订单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "生产订单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkProOrderEntity>> list() {
        List<EmkProOrderEntity> listEmkProOrders = emkProOrderService.getList(EmkProOrderEntity.class);
        return Result.success(listEmkProOrders);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取生产订单信息", notes = "根据ID获取生产订单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProOrderEntity task = emkProOrderService.get(EmkProOrderEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取生产订单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建生产订单")
    public ResponseMessage<?> create(@ApiParam(name = "生产订单对象") @RequestBody EmkProOrderEntity emkProOrder, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProOrderEntity>> failures = validator.validate(emkProOrder, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkProOrderService.save(emkProOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("生产订单信息保存失败");
        }
        return Result.success(emkProOrder);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新生产订单", notes = "更新生产订单")
    public ResponseMessage<?> update(@ApiParam(name = "生产订单对象") @RequestBody EmkProOrderEntity emkProOrder) {
        Set<ConstraintViolation<EmkProOrderEntity>> failures = validator.validate(emkProOrder, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkProOrderService.saveOrUpdate(emkProOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新生产订单信息失败");
        }
        return Result.success("更新生产订单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除生产订单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkProOrderService.deleteEntityById(EmkProOrderEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("生产订单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkProOrderEntity emkProOrderEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "订单表申请提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

            if ((emkProOrderEntity.getId() == null) || (emkProOrderEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkProOrderEntity proOrderEntity = systemService.getEntity(EmkProOrderEntity.class, id);
                    if (!proOrderEntity.getState().equals("0")) {
                        message = "存在已提交的订单表申请，请重新选择在提交订单表申请！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkProOrderEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkProOrderEntity t = emkProOrderService.get(EmkProOrderEntity.class, id);
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
                    if(Utils.isEmpty(b)){
                        b = new EmkApprovalEntity();
                        EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
                        ApprovalUtil.saveApproval(b,8,t.getOrderNo(),t.getId(),user);
                        systemService.save(b);
                        ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"订单表申请单","billTask","提交",user);
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
                        if (task1.getTaskDefinitionKey().equals("billTask")) {
                            taskService.complete(task1.getId(), variables);
                            t.setState("1");
                            b.setStatus(1);
                            saveApprvoalDetail(approvalDetail,"重新提交的订单表","billTask",0,"重新提交订单表");
                            saveSmsAndEmailForMany("业务员","【业务员】订单表","您有【"+b.getCreateName()+"】重新提交的订单表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }
                        if (task1.getTaskDefinitionKey().equals("ywjlshTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(3);
                                approvalDetail.setBpmName("业务经理审核");
                                t.setState("3");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】审核过的订单表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务经理审核","ywyTask",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"ywjlshTask","billTask","订单表");
                                t.setState("0");
                                b.setStatus(0);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的订单表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("ygxhtTask")) {
                            if(map.get("isPass").equals("0")) {
                                //生成采购需求单
                                List<EmkSampleDetailEntity> sampleDetailEntityList = null;
                                EmkMaterialRequiredEntity materialRequiredEntity;
                                for (int i = 0; i < 3; i++) {
                                    materialRequiredEntity = new EmkMaterialRequiredEntity();
                                    try {
                                        MyBeanUtils.copyBeanNotNull2Bean(t, materialRequiredEntity);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    //生成预采购需求单
                                    materialRequiredEntity.setId(null);
                                    materialRequiredEntity.setMaterialNo(t.getOrderNo().replace("D", "CGXQ"));
                                    materialRequiredEntity.setOrderNum(t.getOrderNo());
                                    materialRequiredEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                    materialRequiredEntity.setDhjqDate(t.getRecevieDate());
                                    materialRequiredEntity.setLeaveDhjqDays(t.getLevelDays());
                                    materialRequiredEntity.setType(String.valueOf(i));
                                    materialRequiredEntity.setFormType("1");
                                    materialRequiredEntity.setState("0");
                                    systemService.save(materialRequiredEntity);


                                    sampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", t.getId(), String.valueOf(i));
                                    if ((sampleDetailEntityList != null) && (sampleDetailEntityList.size() > 0)) {
                                        EmkSampleDetailEntity emkSampleDetailEntity = null;
                                        //EmkSampleDetailEntity emkSampleDetailEntity2 = null;

                                        for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                                            emkSampleDetailEntity = new EmkSampleDetailEntity();
                                            MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                            emkSampleDetailEntity.setId(null);
                                            emkSampleDetailEntity.setSampleId(materialRequiredEntity.getId());
                                            systemService.save(emkSampleDetailEntity);

                                        }
                                    }
                                }
                                //生成合同表
                                EmkContractEntity contractEntity = new EmkContractEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t, contractEntity);
                                contractEntity.setId(null);
                                contractEntity.setState("0");
                                contractEntity.setPartyA(user.getCurrentDepart().getDepartname());
                                contractEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
                                contractEntity.setHtNum(t.getOrderNo().replace("D", "HT"));
                                contractEntity.setFormType("1");
                                systemService.save(contractEntity);

                                EmkSizeEntity tse = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",t.getId());
                                EmkSizeEntity emkSizeEntity = new EmkSizeEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(tse, emkSizeEntity);
                                emkSizeEntity.setFormId(contractEntity.getId());
                                systemService.save(emkSizeEntity);

                                EmkProOrderDetailEntity emkProOrderDetailEntity = null;
                                EmkSizeTotalEntityD emkSizeTotalEntity = null;
                                List<EmkProOrderDetailEntity> emkProOrderDetailEntities = systemService.findHql("from EmkProOrderDetailEntity where proOrderId=?", t.getId());
                                for (EmkProOrderDetailEntity proOrderDetailEntity : emkProOrderDetailEntities) {
                                    emkProOrderDetailEntity = new EmkProOrderDetailEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(proOrderDetailEntity, emkProOrderDetailEntity);

                                    emkSizeTotalEntity = new EmkSizeTotalEntityD();
                                    emkProOrderDetailEntity.setId(null);
                                    emkProOrderDetailEntity.setProOrderId(contractEntity.getId());
                                    emkProOrderDetailEntity.setOrderNo(t.getOrderNo());
                                    emkProOrderDetailEntity.setSampleNo(t.getSampleNo());
                                    emkProOrderDetailEntity.setSampleDesc(t.getSampleNoDesc());
                                    emkProOrderDetailEntity.setHqDate(t.getRecevieDate());
                                    MyBeanUtils.copyBeanNotNull2Bean(proOrderDetailEntity.getEmkSizeTotalEntity(), emkSizeTotalEntity);
                                    emkSizeTotalEntity.setId(null);
                                    emkProOrderDetailEntity.setEmkSizeTotalEntity(emkSizeTotalEntity);
                                    systemService.save(emkProOrderDetailEntity);
                                }
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(44);
                                approvalDetail.setBpmName("【业务员】预购销合同");
                                t.setState("44");
                                approvalDetail.setApproveStatus(0);
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("ywjlshTask2")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(3);
                                approvalDetail.setBpmName("业务经理审核");
                                t.setState("3");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("总经理","业务经理审核","您有【"+b.getCreateName()+"】审核过的订单表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务经理审核","ywjlshTask2",1,"回退");
                                backProcess(task1.getProcessInstanceId(),"ywjlshTask2","gxhtTask","订单表");
                                t.setState("49");
                                b.setStatus(49);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的订单表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("zjlpfTask")) {
                            if(map.get("isPass").equals("0")) {
                                //生成包装工艺表
                                EmkBzgydEntity bzgydEntity = new EmkBzgydEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t, bzgydEntity);
                                systemService.save(bzgydEntity);
                                EmkProOrderBarcodeEntity emkProOrderBarcodeEntity = null;
                                EmkProOrderBoxEntity emkProOrderBoxEntity = null;
                                EmkProOrderPackageEntity emkProOrderPackageEntity = null;
                                EmkSampleDetailEntity emkSampleDetailEntity = null;

                                List<EmkProOrderBarcodeEntity> proOrderBarcodeEntityList = systemService.findHql("from EmkProOrderBarcodeEntity where orderId=?",t.getId());
                                for(EmkProOrderBarcodeEntity proOrderBarcodeEntity : proOrderBarcodeEntityList){
                                    emkProOrderBarcodeEntity = new EmkProOrderBarcodeEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(proOrderBarcodeEntity, emkProOrderBarcodeEntity);
                                    emkProOrderBarcodeEntity.setId(null);
                                    emkProOrderBarcodeEntity.setOrderId(bzgydEntity.getId());
                                    systemService.save(emkProOrderBarcodeEntity);
                                }
                                List<EmkProOrderBoxEntity> proOrderBoxEntityList = systemService.findHql("from EmkProOrderBoxEntity where orderId=?",t.getId());
                                for(EmkProOrderBoxEntity proOrderBoxEntity : proOrderBoxEntityList){
                                    emkProOrderBoxEntity = new EmkProOrderBoxEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(proOrderBoxEntity, emkProOrderBoxEntity);
                                    emkProOrderBoxEntity.setId(null);
                                    emkProOrderBoxEntity.setOrderId(bzgydEntity.getId());
                                    systemService.save(emkProOrderBoxEntity);

                                }
                                List<EmkProOrderPackageEntity> proOrderPackageEntityList = systemService.findHql("from EmkProOrderPackageEntity where orderId=?",t.getId());
                                for(EmkProOrderPackageEntity proOrderPackageEntity : proOrderPackageEntityList){
                                    emkProOrderPackageEntity = new EmkProOrderPackageEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(proOrderPackageEntity, emkProOrderPackageEntity);
                                    emkProOrderPackageEntity.setId(null);
                                    emkProOrderPackageEntity.setOrderId(bzgydEntity.getId());
                                    systemService.save(emkProOrderPackageEntity);
                                }
                                List<EmkSampleDetailEntity> sampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=2", t.getId());
                                for(EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList){
                                    emkSampleDetailEntity = new EmkSampleDetailEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                                    emkSampleDetailEntity.setId(null);
                                    emkSampleDetailEntity.setSampleId(bzgydEntity.getId());
                                    systemService.save(emkSampleDetailEntity);
                                }

                                //预计验货时间表
                                EmkYjyhTimeEntity yjyhTimeEntity = new EmkYjyhTimeEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t, yjyhTimeEntity);
                                yjyhTimeEntity.setYjzqyhDate(t.getZqyhDate());
                                yjyhTimeEntity.setYjwqyhDate(t.getWqyhDate());
                                yjyhTimeEntity.setKdTime(DateUtils.format(new Date(), "yyyy-MM-dd"));
                                systemService.save(yjyhTimeEntity);

                                taskService.complete(task1.getId(), variables);
                                b.setStatus(2);
                                approvalDetail.setBpmName("总经理批复");
                                t.setState("2");
                                approvalDetail.setApproveStatus(0);

                                bpmUser = systemService.get(TSUser.class,b.getCommitId());
                                saveSmsAndEmailForOne("总经理批复","您有【"+user.getRealName()+"】审核过的订单表，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        systemService.save(approvalDetail);

                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("bill", "emkProOrderPage", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);

                        t.setState("1");
                        b.setStatus(1);
                        b.setBpmStatus("0");
                        b.setProcessName("【业务员】订单表");

                        saveApprvoalDetail(approvalDetail,"提交的【业务员】订单表","materialTask",0,"提交【业务员】订单表");
                        saveSmsAndEmailForMany("业务经理","【业务员】订单表","您有【"+b.getCreateName()+"】提交的【业务员】订单表，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                    }
                    systemService.saveOrUpdate(t);
                    systemService.saveOrUpdate(b);
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "订单表申请提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkProOrderEntity emkProOrderEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrderEntity.getId())) {
            emkProOrderEntity = emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrderEntity.getId());
            req.setAttribute("emkProOrderPage", emkProOrderEntity);
        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-work");
    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkProOrderEntity emkProOrderEntity, HttpServletRequest req, DataGrid dataGrid) {
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkProOrderEntity.getId());
        List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
        req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
        req.setAttribute("approvalEntity", approvalEntity);
        req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
        return new ModelAndView("com/emk/bill/proorder/time");
    }



}
