package com.emk.produce.invoices.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.check.sizecheck.entity.EmkSizeEntity;
import com.emk.check.sizecheck.entity.EmkSizeTotalEntity;
import com.emk.produce.invoices.entity.EmkInvoicesDetailEntity;
import com.emk.produce.invoices.entity.EmkInvoicesEntity;
import com.emk.produce.invoices.service.EmkInvoicesServiceI;
import com.emk.produce.packinglist.entity.EmkPackingListDetailEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
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

@Api(value = "EmkInvoices", description = "发票", tags = "emkInvoicesController")
@Controller
@RequestMapping("/emkInvoicesController")
public class EmkInvoicesController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkInvoicesController.class);
    @Autowired
    private EmkInvoicesServiceI emkInvoicesService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/produce/invoices/emkInvoicesList");
    }

    @RequestMapping(params = "orderMxList")
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        request.setAttribute("categoryEntityList", codeList);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", map.get("proOrderId"));
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/produce/invoices/orderMxList");
    }

    @RequestMapping(params = "detailMxList")
    public ModelAndView detailMxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        request.setAttribute("colorList", list);
        list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='size'");
        request.setAttribute("sizeList", list);
        if (Utils.notEmpty(map.get("invoicesId"))) {
            List<EmkInvoicesDetailEntity> emkInvoicesDetailEntities = systemService.findHql("from EmkInvoicesDetailEntity where invoicesId=?", map.get("invoicesId"));
            request.setAttribute("emkInvoicesDetailEntities", emkInvoicesDetailEntities);

            EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",map.get("invoicesId"));
            request.setAttribute("emkSizePage", emkSizeEntity);
        }
        return new ModelAndView("com/emk/produce/invoices/detailMxList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkInvoicesEntity emkInvoices, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkInvoicesEntity.class, dataGrid);
        TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")|| roleMap.get("rolecode").toString().contains("scgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }
        HqlGenerateUtil.installHql(cq, emkInvoices, request.getParameterMap());


        cq.add();
        emkInvoicesService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkInvoicesEntity emkInvoices, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkInvoices = systemService.getEntity(EmkInvoicesEntity.class, emkInvoices.getId());
        message = "发票删除成功";
        try {
            emkInvoicesService.delete(emkInvoices);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "发票删除失败";
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
        message = "发票删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkInvoicesEntity emkInvoices = systemService.getEntity(EmkInvoicesEntity.class, id);
                systemService.executeSql("delete from emk_size_total where FIND_IN_SET(p_id,(SELECT GROUP_CONCAT(id) FROM emk_invoices_detail where invoices_id=?))",id);
                systemService.executeSql("delete from emk_invoices_detail where invoices_id=?",id);
                systemService.executeSql("delete from emk_size where form_id=?", id);

                emkInvoicesService.delete(emkInvoices);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "发票删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkInvoicesEntity emkInvoices, EmkSizeEntity emkSize, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "发票添加成功";
        try {
            emkInvoicesService.save(emkInvoices);
            emkSize.setFormId(emkInvoices.getId());
            systemService.save(emkSize);

            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                EmkInvoicesDetailEntity emkInvoicesDetailEntity = null;
                EmkSizeTotalEntity emkSizeTotalEntity = null;
                for (int i = 0; i < rows; i++) {
                    emkInvoicesDetailEntity = new EmkInvoicesDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))) {
                        emkInvoicesDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
                        emkInvoicesDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
                        emkInvoicesDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));

                        emkInvoicesDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkInvoicesDetailEntity.setSize(map.get("orderMxList["+i+"].size"));

                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal00"))){
                            emkInvoicesDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal00")));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signPrice"))) {
                            emkInvoicesDetailEntity.setPrice(Double.parseDouble(map.get("orderMxList["+i+"].signPrice")));
                        }
                        emkInvoicesDetailEntity.setMoney(map.get("orderMxList["+i+"].money"));
                        emkInvoicesDetailEntity.setSumMoney(map.get("orderMxList["+i+"].sumMoney"));

                        emkInvoicesDetailEntity.setInvoicesId(emkInvoices.getId());
                        systemService.save(emkInvoicesDetailEntity);

                        emkSizeTotalEntity = new EmkSizeTotalEntity();
                        emkSizeTotalEntity.setTotalA(map.get("orderMxList["+i+"].totalA"));
                        emkSizeTotalEntity.setTotalB(map.get("orderMxList["+i+"].totalB"));
                        emkSizeTotalEntity.setTotalC(map.get("orderMxList["+i+"].totalC"));
                        emkSizeTotalEntity.setTotalD(map.get("orderMxList["+i+"].totalD"));
                        emkSizeTotalEntity.setTotalE(map.get("orderMxList["+i+"].totalE"));
                        emkSizeTotalEntity.setTotalF(map.get("orderMxList["+i+"].totalF"));
                        emkSizeTotalEntity.setTotalG(map.get("orderMxList["+i+"].totalG"));
                        emkSizeTotalEntity.setTotalH(map.get("orderMxList["+i+"].totalH"));
                        emkSizeTotalEntity.setTotalI(map.get("orderMxList["+i+"].totalI"));
                        emkSizeTotalEntity.setTotalJ(map.get("orderMxList["+i+"].totalJ"));
                        emkSizeTotalEntity.setTotalK(map.get("orderMxList["+i+"].totalK"));
                        emkSizeTotalEntity.setpId(emkInvoicesDetailEntity.getId());
                        systemService.save(emkSizeTotalEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "发票添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkInvoicesEntity emkInvoices,EmkSizeEntity emkSize, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "发票更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        EmkInvoicesEntity t = emkInvoicesService.get(EmkInvoicesEntity.class, map.get("invoicesId"));
        EmkSizeEntity t2 = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",t.getId());

        try {
            emkInvoices.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkInvoices, t);
            emkInvoicesService.saveOrUpdate(t);

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

            String dataRows = (String) map.get("orderMxListIDSR");
            if (Utils.notEmpty(dataRows)) {
                this.systemService.executeSql("delete from emk_size_total where FIND_IN_SET(p_id,(SELECT GROUP_CONCAT(id) FROM emk_invoices_detail where invoices_id=?))", t.getId());
                systemService.executeSql("delete from emk_invoices_detail where invoices_id = ?",t.getId());
                int rows = Integer.parseInt(dataRows);
                EmkInvoicesDetailEntity emkInvoicesDetailEntity = null;
                EmkSizeTotalEntity emkSizeTotalEntity = null;
                for (int i = 0; i < rows; i++) {
                    emkInvoicesDetailEntity = new EmkInvoicesDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].orderNo00"))) {
                        emkInvoicesDetailEntity.setOrderNo(map.get("orderMxList["+i+"].orderNo00"));
                        emkInvoicesDetailEntity.setSampleNo(map.get("orderMxList["+i+"].sampleNo00"));
                        emkInvoicesDetailEntity.setSampleNoDesc(map.get("orderMxList["+i+"].sampleDesc00"));

                        emkInvoicesDetailEntity.setColor(map.get("orderMxList["+i+"].color"));
                        emkInvoicesDetailEntity.setSize(map.get("orderMxList["+i+"].size"));

                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signTotal00"))){
                            emkInvoicesDetailEntity.setTotal(Integer.parseInt(map.get("orderMxList["+i+"].signTotal00")));
                        }
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].signPrice"))) {
                            emkInvoicesDetailEntity.setPrice(Double.parseDouble(map.get("orderMxList["+i+"].signPrice")));
                        }
                        emkInvoicesDetailEntity.setMoney(map.get("orderMxList["+i+"].money"));
                        emkInvoicesDetailEntity.setSumMoney(map.get("orderMxList["+i+"].sumMoney"));

                        emkInvoicesDetailEntity.setInvoicesId(t.getId());
                        systemService.save(emkInvoicesDetailEntity);

                        emkSizeTotalEntity = new EmkSizeTotalEntity();
                        emkSizeTotalEntity.setTotalA(map.get("orderMxList["+i+"].totalA"));
                        emkSizeTotalEntity.setTotalB(map.get("orderMxList["+i+"].totalB"));
                        emkSizeTotalEntity.setTotalC(map.get("orderMxList["+i+"].totalC"));
                        emkSizeTotalEntity.setTotalD(map.get("orderMxList["+i+"].totalD"));
                        emkSizeTotalEntity.setTotalE(map.get("orderMxList["+i+"].totalE"));
                        emkSizeTotalEntity.setTotalF(map.get("orderMxList["+i+"].totalF"));
                        emkSizeTotalEntity.setTotalG(map.get("orderMxList["+i+"].totalG"));
                        emkSizeTotalEntity.setTotalH(map.get("orderMxList["+i+"].totalH"));
                        emkSizeTotalEntity.setTotalI(map.get("orderMxList["+i+"].totalI"));
                        emkSizeTotalEntity.setTotalJ(map.get("orderMxList["+i+"].totalJ"));
                        emkSizeTotalEntity.setTotalK(map.get("orderMxList["+i+"].totalK"));
                        emkSizeTotalEntity.setpId(emkInvoicesDetailEntity.getId());
                        systemService.save(emkSizeTotalEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "发票更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkInvoicesEntity emkInvoices, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkInvoices.getId())) {
            emkInvoices = emkInvoicesService.getEntity(EmkInvoicesEntity.class, emkInvoices.getId());
            req.setAttribute("emkInvoicesPage", emkInvoices);
        }
        return new ModelAndView("com/emk/produce/invoices/emkInvoices-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkInvoicesEntity emkInvoices, HttpServletRequest req) {
        List<Map<String, Object>> codeList = systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", "A03");
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkInvoices.getId())) {
            emkInvoices = emkInvoicesService.getEntity(EmkInvoicesEntity.class, emkInvoices.getId());
            req.setAttribute("emkInvoicesPage", emkInvoices);
        }
        return new ModelAndView("com/emk/produce/invoices/emkInvoices-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkInvoicesController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkInvoicesEntity emkInvoices, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkInvoicesEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkInvoices, request.getParameterMap());
        List<EmkInvoicesEntity> emkInvoicess = emkInvoicesService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "发票");
        modelMap.put("entity", EmkInvoicesEntity.class);
        modelMap.put("params", new ExportParams("发票列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkInvoicess);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkInvoicesEntity emkInvoices, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "发票");
        modelMap.put("entity", EmkInvoicesEntity.class);
        modelMap.put("params", new ExportParams("发票列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "发票列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkInvoicesEntity>> list() {
        List<EmkInvoicesEntity> listEmkInvoicess = emkInvoicesService.getList(EmkInvoicesEntity.class);
        return Result.success(listEmkInvoicess);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取发票信息", notes = "根据ID获取发票信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkInvoicesEntity task = emkInvoicesService.get(EmkInvoicesEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取发票信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建发票")
    public ResponseMessage<?> create(@ApiParam(name = "发票对象") @RequestBody EmkInvoicesEntity emkInvoices, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkInvoicesEntity>> failures = validator.validate(emkInvoices, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkInvoicesService.save(emkInvoices);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("发票信息保存失败");
        }
        return Result.success(emkInvoices);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新发票", notes = "更新发票")
    public ResponseMessage<?> update(@ApiParam(name = "发票对象") @RequestBody EmkInvoicesEntity emkInvoices) {
        Set<ConstraintViolation<EmkInvoicesEntity>> failures = validator.validate(emkInvoices, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkInvoicesService.saveOrUpdate(emkInvoices);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新发票信息失败");
        }
        return Result.success("更新发票信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除发票")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkInvoicesService.deleteEntityById(EmkInvoicesEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("发票删除失败");
        }
        return Result.success();
    }
}
