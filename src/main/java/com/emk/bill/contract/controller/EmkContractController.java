package com.emk.bill.contract.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.contract.entity.EmkContractEntity;
import com.emk.bill.contract.service.EmkContractServiceI;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.util.ParameterUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Api(value = "EmkContract", description = "购销合同", tags = {"emkContractController"})
@Controller
@RequestMapping({"/emkContractController"})
public class EmkContractController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkContractController.class);
    @Autowired
    private EmkContractServiceI emkContractService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/contract/emkContractList");
    }

    @RequestMapping(params = {"orderMxList"})
    public ModelAndView orderMxList(HttpServletRequest request) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        request.setAttribute("categoryEntityList", codeList);
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if ((map.get("proOrderId") != null) && (!map.get("proOrderId").equals(""))) {
            List<EmkEnquiryDetailEntity> emkProOrderDetailEntities = this.systemService.findHql("from EmkEnquiryDetailEntity where enquiryId=?", new Object[]{map.get("proOrderId")});
            request.setAttribute("emkProOrderDetailEntities", emkProOrderDetailEntities);
        }
        return new ModelAndView("com/emk/bill/contract/orderMxList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkContractEntity emkContract, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkContractEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkContract, request.getParameterMap());


        cq.add();
        this.emkContractService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkContractEntity emkContract, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkContract = (EmkContractEntity) this.systemService.getEntity(EmkContractEntity.class, emkContract.getId());
        message = "购销合同删除成功";
        try {
            this.emkContractService.delete(emkContract);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "购销合同删除失败";
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
        message = "购销合同删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkContractEntity emkContract = (EmkContractEntity) this.systemService.getEntity(EmkContractEntity.class, id);

                this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", id);

                this.emkContractService.delete(emkContract);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "购销合同删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkContractEntity emkContract, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "购销合同添加成功";
        try {
            this.emkContractService.save(emkContract);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "购销合同添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkContractEntity emkContract, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "购销合同更新成功";
        EmkContractEntity t = (EmkContractEntity) this.emkContractService.get(EmkContractEntity.class, emkContract.getId());
        try {
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            MyBeanUtils.copyBeanNotNull2Bean(emkContract, t);
            this.emkContractService.saveOrUpdate(t);
            this.systemService.executeSql("delete from emk_enquiry_detail where ENQUIRY_ID=?", new Object[]{t.getId()});
            String dataRows = (String) map.get("dataRowsVal");
            if ((dataRows != null) && (!dataRows.isEmpty())) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkEnquiryDetailEntity orderMxEntity = new EmkEnquiryDetailEntity();
                    if ((map.get("orderMxList[" + i + "].color") != null) && !map.get("orderMxList[" + i + "].color").isEmpty()) {
                        orderMxEntity.setEnquiryId(t.getId());
                        orderMxEntity.setColor((String) map.get("orderMxList[" + i + "].color"));
                        orderMxEntity.setSize((String) map.get("orderMxList[" + i + "].size"));
                        orderMxEntity.setTotal(Integer.valueOf(Integer.parseInt((String) map.get("orderMxList[" + i + "].signTotal"))));
                        orderMxEntity.setPrice(Double.valueOf(Double.parseDouble((String) map.get("orderMxList[" + i + "].signPrice"))));
                        this.systemService.save(orderMxEntity);
                    }
                }
            }
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "购销合同更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkContractEntity emkContract, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkContract.getId())) {
            emkContract = (EmkContractEntity) this.emkContractService.getEntity(EmkContractEntity.class, emkContract.getId());
            req.setAttribute("emkContractPage", emkContract);
        }
        return new ModelAndView("com/emk/bill/contract/emkContract-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkContractEntity emkContract, HttpServletRequest req) {
        List<Map<String, Object>> codeList = this.systemService.findForJdbc("select code,name from t_s_category where PARENT_CODE=? order by code asc", new Object[]{"A03"});
        req.setAttribute("categoryEntityList", codeList);
        if (StringUtil.isNotEmpty(emkContract.getId())) {
            emkContract = (EmkContractEntity) this.emkContractService.getEntity(EmkContractEntity.class, emkContract.getId());
            req.setAttribute("emkContractPage", emkContract);
        }
        return new ModelAndView("com/emk/bill/contract/emkContract-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkContractController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkContractEntity emkContract, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkContractEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkContract, request.getParameterMap());
        List<EmkContractEntity> emkContracts = this.emkContractService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "购销合同");
        modelMap.put("entity", EmkContractEntity.class);
        modelMap.put("params", new ExportParams("购销合同列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkContracts);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkContractEntity emkContract, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "购销合同");
        modelMap.put("entity", EmkContractEntity.class);
        modelMap.put("params", new ExportParams("购销合同列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "购销合同列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkContractEntity>> list() {
        List<EmkContractEntity> listEmkContracts = this.emkContractService.getList(EmkContractEntity.class);
        return Result.success(listEmkContracts);
    }

    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取购销合同信息", notes = "根据ID获取购销合同信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkContractEntity task = (EmkContractEntity) this.emkContractService.get(EmkContractEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取购销合同信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建购销合同")
    public ResponseMessage<?> create(@ApiParam(name = "购销合同对象") @RequestBody EmkContractEntity emkContract, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkContractEntity>> failures = this.validator.validate(emkContract, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkContractService.save(emkContract);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("购销合同信息保存失败");
        }
        return Result.success(emkContract);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新购销合同", notes = "更新购销合同")
    public ResponseMessage<?> update(@ApiParam(name = "购销合同对象") @RequestBody EmkContractEntity emkContract) {
        Set<ConstraintViolation<EmkContractEntity>> failures = this.validator.validate(emkContract, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkContractService.saveOrUpdate(emkContract);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新购销合同信息失败");
        }
        return Result.success("更新购销合同信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除购销合同")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkContractService.deleteEntityById(EmkContractEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("购销合同删除失败");
        }
        return Result.success();
    }
}
