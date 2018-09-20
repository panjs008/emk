package com.emk.bill.proorder.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.bill.proorder.service.EmkProOrderServiceI;
import com.emk.bill.proorderdetail.entity.EmkProOrderDetailEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.util.ParameterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Api(value = "EmkProOrder", description = "生产订单", tags = {"emkProOrderController"})
@Controller
@RequestMapping({"/emkProOrderController"})
public class EmkProOrderController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProOrderController.class);
    @Autowired
    private EmkProOrderServiceI emkProOrderService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/proorder/emkProOrderList");
    }

    @RequestMapping(params = {"orderMxList"})
    public ModelAndView orderMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkProOrderDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkProOrderDetailEntity where proOrderId=?", new Object[]{map.get("proOrderId")});
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/bill/proorder/orderMxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProOrder, request.getParameterMap());


        cq.add();
        this.emkProOrderService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProOrderEntity emkProOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProOrder = (EmkProOrderEntity) this.systemService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());
        message = "生产订单删除成功";
        try {
            if (!emkProOrder.getState().equals("0")) {
                message = "订单已经提交处理，无法删除";
                j.setMsg(message);
                j.setSuccess(false);
                return j;
            }
            this.systemService.executeSql("delete from emk_pro_order_detail where PRO_ORDER_ID=?", new Object[]{emkProOrder.getId()});
            this.emkProOrderService.delete(emkProOrder);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doBatchDel"})
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProOrderEntity emkProOrder = (EmkProOrderEntity) this.systemService.getEntity(EmkProOrderEntity.class, id);
                if (!emkProOrder.getState().equals("0")) {
                    message = "存在已提交的订单，请重新选择在提交订单！";
                    j.setSuccess(false);
                    break;
                }
                this.systemService.executeSql("delete from emk_pro_order_detail where pro_order_id=?", new Object[]{emkProOrder.getId()});
                this.emkProOrderService.delete(emkProOrder);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProOrderEntity emkProOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单添加成功";
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map orderNum = this.systemService.findOneForJdbc("select count(0)+1 orderNum from emk_pro_order where sys_org_code=?", new Object[]{user.getCurrentDepart().getOrgCode()});
            emkProOrder.setOrderNo("D" + DateUtils.format(new Date(), "yyMMdd") + String.format("%06d", new Object[]{Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))}));
            emkProOrder.setState("0");
            this.emkProOrderService.save(emkProOrder);
            Map price = this.systemService.findOneForJdbc("select * from emk_price where cus_num =? and sample_no=? order by kd_date desc limit 0,1", new Object[]{emkProOrder.getCusNum(), emkProOrder.getSampleNo()});
            List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=?", new Object[]{price.get("id")});
            for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                sampleDetailEntity.setSampleId(price.get("id").toString());
                sampleDetailEntity.setId(null);
                this.systemService.save(sampleDetailEntity);
            }
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doSubmit"})
    @ResponseBody
    public AjaxJson doSubmit(EmkProOrderEntity emkProOrder, HttpServletRequest request, String ids) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单提交成功";
        EmkProOrderEntity t = (EmkProOrderEntity) this.emkProOrderService.get(EmkProOrderEntity.class, emkProOrder.getId());
        try {
            TSUser user = (TSUser) request.getSession().getAttribute("LOCAL_CLINET_USER");

            MyBeanUtils.copyBeanNotNull2Bean(emkProOrder, t);
            this.emkProOrderService.saveOrUpdate(t);
            EmkMaterialRequiredEntity materialRequiredEntity = null;
            EmkMaterialPactEntity materialPactEntity = null;

            EmkContractEntity contractEntity = new EmkContractEntity();
            MyBeanUtils.copyBeanNotNull2Bean(t, contractEntity);


            contractEntity.setId(null);

            contractEntity.setPartyA(user.getCurrentDepart().getDepartname());
            contractEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
            contractEntity.setPartyB(t.getGys());
            contractEntity.setPartyBId(t.getGysCode());
            contractEntity.setHtNum(t.getOrderNo().replace("D", "HT"));

            EmkContractEntity emkContractEntity = (EmkContractEntity) this.systemService.findUniqueByProperty(EmkContractEntity.class, "htNum", contractEntity.getHtNum());
            String contractId = "";
            if (emkContractEntity != null) {
                MyBeanUtils.copyBeanNotNull2Bean(contractEntity, emkContractEntity);
                this.systemService.saveOrUpdate(emkContractEntity);
                this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{emkContractEntity.getId()});
                contractId = emkContractEntity.getId();
            } else {
                this.systemService.save(contractEntity);
                contractId = contractEntity.getId();
            }
            EmkEnquiryDetailEntity emkEnquiryDetailEntity = null;
            List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{emkProOrder.getId()});
            for (EmkEnquiryDetailEntity enquiryDetailEntity : emkEnquiryDetailEntityList) {
                emkEnquiryDetailEntity = new EmkEnquiryDetailEntity();
                MyBeanUtils.copyBeanNotNull2Bean(enquiryDetailEntity, emkEnquiryDetailEntity);
                emkEnquiryDetailEntity.setId(null);
                emkEnquiryDetailEntity.setEnquiryId(contractId);
                this.systemService.save(emkEnquiryDetailEntity);
            }
            EmkSampleDetailEntity emkSampleDetailEntity;
            String materialRequiredId;
            for (int i = 0; i < 3; i++) {
                List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=?", new Object[]{emkProOrder.getId(), String.valueOf(i)});
                if ((sampleDetailEntityList != null) && (sampleDetailEntityList.size() > 0)) {
                    materialRequiredEntity = new EmkMaterialRequiredEntity();
                    materialPactEntity = new EmkMaterialPactEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(t, materialRequiredEntity);
                    MyBeanUtils.copyBeanNotNull2Bean(t, materialPactEntity);


                    materialPactEntity.setId(null);
                    materialPactEntity.setOrderNum(t.getOrderNo());
                    materialPactEntity.setPartyA(user.getCurrentDepart().getDepartname());
                    materialPactEntity.setPartyAId(user.getCurrentDepart().getOrgCode());
                    materialPactEntity.setPartyB(t.getGys());
                    materialPactEntity.setPartyBId(t.getGysCode());
                    materialPactEntity.setMaterialNo(t.getOrderNo().replace("D", "YHT"));
                    materialPactEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                    materialPactEntity.setDhjqDate(t.getRecevieDate());
                    materialPactEntity.setLeaveDhjqDays(t.getLevelDays());
                    materialPactEntity.setType(String.valueOf(i));
                    List<EmkMaterialPactEntity> emkMaterialPactEntityList = this.systemService.findHql("from EmkMaterialPactEntity where materialNo=? and type=?", new Object[]{materialPactEntity.getMaterialNo(), String.valueOf(i)});
                    String materialPactId = "";
                    if ((emkMaterialPactEntityList != null) && (emkMaterialPactEntityList.size() > 0)) {
                        EmkMaterialPactEntity t2 = (EmkMaterialPactEntity) emkMaterialPactEntityList.get(0);
                        MyBeanUtils.copyBeanNotNull2Bean(materialPactEntity, t2);
                        this.systemService.saveOrUpdate(t2);
                        this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[]{t2.getId()});
                        materialPactId = t2.getId();
                    } else {
                        this.systemService.save(materialPactEntity);
                        materialPactId = materialPactEntity.getId();
                    }
                    emkSampleDetailEntity = null;
                    for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                        emkSampleDetailEntity = new EmkSampleDetailEntity();
                        MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                        emkSampleDetailEntity.setId(null);
                        emkSampleDetailEntity.setSampleId(materialPactId);
                        this.systemService.save(emkSampleDetailEntity);
                    }
                    materialRequiredEntity.setId(null);
                    materialRequiredEntity.setMaterialNo(t.getOrderNo().replace("D", "CG"));
                    materialRequiredEntity.setOrderNum(t.getOrderNo());
                    materialRequiredEntity.setHtNum(materialPactEntity.getMaterialNo());
                    materialRequiredEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
                    materialRequiredEntity.setDhjqDate(t.getRecevieDate());
                    materialRequiredEntity.setLeaveDhjqDays(t.getLevelDays());
                    materialRequiredEntity.setType(String.valueOf(i));

                    List<EmkMaterialRequiredEntity> emkMaterialRequiredEntityList = this.systemService.findHql("from EmkMaterialRequiredEntity where materialNo=? and type=?", new Object[]{materialRequiredEntity.getMaterialNo(), String.valueOf(i)});
                    materialRequiredId = "";
                    if ((emkMaterialRequiredEntityList != null) && (emkMaterialRequiredEntityList.size() > 0)) {
                        EmkMaterialRequiredEntity t2 = (EmkMaterialRequiredEntity) emkMaterialRequiredEntityList.get(0);
                        MyBeanUtils.copyBeanNotNull2Bean(materialRequiredEntity, t2);
                        this.systemService.saveOrUpdate(t2);
                        this.systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[]{t2.getId()});
                        materialRequiredId = t2.getId();
                    } else {
                        this.systemService.save(materialRequiredEntity);
                        materialRequiredId = materialRequiredEntity.getId();
                    }
                    for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                        emkSampleDetailEntity = new EmkSampleDetailEntity();
                        MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                        emkSampleDetailEntity.setId(null);
                        emkSampleDetailEntity.setSampleId(materialRequiredId);
                        this.systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProOrderEntity emkProOrder, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "生产订单更新成功";


        EmkProOrderEntity t = (EmkProOrderEntity) this.emkProOrderService.get(EmkProOrderEntity.class, emkProOrder.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkProOrder, t);
            this.emkProOrderService.saveOrUpdate(t);
            Map price = this.systemService.findOneForJdbc("select id from emk_price where cus_num =? and sample_no=? order by kd_date desc limit 0,1", new Object[]{emkProOrder.getCusNum(), emkProOrder.getSampleNo()});
            EmkSampleDetailEntity emkSampleDetailEntity;
            if (price != null) {
                List<EmkSampleDetailEntity> sampleDetailEntityList = this.systemService.findHql("from EmkSampleDetailEntity where sampleId=?", new Object[]{price.get("id")});
                emkSampleDetailEntity = null;
                for (EmkSampleDetailEntity sampleDetailEntity : sampleDetailEntityList) {
                    emkSampleDetailEntity = new EmkSampleDetailEntity();
                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity, emkSampleDetailEntity);
                    emkSampleDetailEntity.setId(null);
                    emkSampleDetailEntity.setSampleId(emkProOrder.getId());
                    this.systemService.save(emkSampleDetailEntity);
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "生产订单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = (EmkProOrderEntity) this.emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());
            req.setAttribute("emkProOrderPage", emkProOrder);
        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProOrderEntity emkProOrder, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrder.getId())) {
            emkProOrder = (EmkProOrderEntity) this.emkProOrderService.getEntity(EmkProOrderEntity.class, emkProOrder.getId());
            req.setAttribute("emkProOrderPage", emkProOrder);
        }
        return new ModelAndView("com/emk/bill/proorder/emkProOrder-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProOrderController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProOrderEntity emkProOrder, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProOrder, request.getParameterMap());
        List<EmkProOrderEntity> emkProOrders = this.emkProOrderService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "生产订单");
        modelMap.put("entity", EmkProOrderEntity.class);
        modelMap.put("params", new ExportParams("生产订单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProOrders);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
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
        List<EmkProOrderEntity> listEmkProOrders = this.emkProOrderService.getList(EmkProOrderEntity.class);
        return Result.success(listEmkProOrders);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取生产订单信息", notes = "根据ID获取生产订单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProOrderEntity task = (EmkProOrderEntity) this.emkProOrderService.get(EmkProOrderEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取生产订单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建生产订单")
    public ResponseMessage<?> create(@ApiParam(name = "生产订单对象") @RequestBody EmkProOrderEntity emkProOrder, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProOrderEntity>> failures = this.validator.validate(emkProOrder, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderService.save(emkProOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("生产订单信息保存失败");
        }
        return Result.success(emkProOrder);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新生产订单", notes = "更新生产订单")
    public ResponseMessage<?> update(@ApiParam(name = "生产订单对象") @RequestBody EmkProOrderEntity emkProOrder) {
        Set<ConstraintViolation<EmkProOrderEntity>> failures = this.validator.validate(emkProOrder, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderService.saveOrUpdate(emkProOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新生产订单信息失败");
        }
        return Result.success("更新生产订单信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除生产订单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkProOrderService.deleteEntityById(EmkProOrderEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("生产订单删除失败");
        }
        return Result.success();
    }
}
