package com.emk.bill.proorderbarcode.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.proorderbarcode.entity.EmkProOrderBarcodeEntity;
import com.emk.bill.proorderbarcode.service.EmkProOrderBarcodeServiceI;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Api(value = "EmkProOrderBarcode", description = "条码表", tags = {"emkProOrderBarcodeController"})
@Controller
@RequestMapping({"/emkProOrderBarcodeController"})
public class EmkProOrderBarcodeController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProOrderBarcodeController.class);
    @Autowired
    private EmkProOrderBarcodeServiceI emkProOrderBarcodeService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/proorderbarcode/emkProOrderBarcodeList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProOrderBarcodeEntity emkProOrderBarcode, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderBarcodeEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProOrderBarcode, request.getParameterMap());


        cq.add();
        this.emkProOrderBarcodeService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProOrderBarcodeEntity emkProOrderBarcode, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProOrderBarcode = (EmkProOrderBarcodeEntity) this.systemService.getEntity(EmkProOrderBarcodeEntity.class, emkProOrderBarcode.getId());
        message = "条码表删除成功";
        try {
            this.emkProOrderBarcodeService.delete(emkProOrderBarcode);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "条码表删除失败";
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
        message = "条码表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProOrderBarcodeEntity emkProOrderBarcode = (EmkProOrderBarcodeEntity) this.systemService.getEntity(EmkProOrderBarcodeEntity.class, id);


                this.emkProOrderBarcodeService.delete(emkProOrderBarcode);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "条码表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProOrderBarcodeEntity emkProOrderBarcode, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "条码表添加成功";
        try {
            this.emkProOrderBarcodeService.save(emkProOrderBarcode);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "条码表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProOrderBarcodeEntity emkProOrderBarcode, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "条码表更新成功";
        EmkProOrderBarcodeEntity t = (EmkProOrderBarcodeEntity) this.emkProOrderBarcodeService.get(EmkProOrderBarcodeEntity.class, emkProOrderBarcode.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkProOrderBarcode, t);
            this.emkProOrderBarcodeService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "条码表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProOrderBarcodeEntity emkProOrderBarcode, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrderBarcode.getId())) {
            emkProOrderBarcode = (EmkProOrderBarcodeEntity) this.emkProOrderBarcodeService.getEntity(EmkProOrderBarcodeEntity.class, emkProOrderBarcode.getId());
            req.setAttribute("emkProOrderBarcodePage", emkProOrderBarcode);
        }
        return new ModelAndView("com/emk/bill/proorderbarcode/emkProOrderBarcode-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProOrderBarcodeEntity emkProOrderBarcode, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrderBarcode.getId())) {
            emkProOrderBarcode = (EmkProOrderBarcodeEntity) this.emkProOrderBarcodeService.getEntity(EmkProOrderBarcodeEntity.class, emkProOrderBarcode.getId());
            req.setAttribute("emkProOrderBarcodePage", emkProOrderBarcode);
        }
        return new ModelAndView("com/emk/bill/proorderbarcode/emkProOrderBarcode-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProOrderBarcodeController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProOrderBarcodeEntity emkProOrderBarcode, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderBarcodeEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProOrderBarcode, request.getParameterMap());
        List<EmkProOrderBarcodeEntity> emkProOrderBarcodes = this.emkProOrderBarcodeService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "条码表");
        modelMap.put("entity", EmkProOrderBarcodeEntity.class);
        modelMap.put("params", new ExportParams("条码表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProOrderBarcodes);
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "条码表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkProOrderBarcodeEntity>> list() {
        List<EmkProOrderBarcodeEntity> listEmkProOrderBarcodes = this.emkProOrderBarcodeService.getList(EmkProOrderBarcodeEntity.class);
        return Result.success(listEmkProOrderBarcodes);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取条码表信息", notes = "根据ID获取条码表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProOrderBarcodeEntity task = (EmkProOrderBarcodeEntity) this.emkProOrderBarcodeService.get(EmkProOrderBarcodeEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取条码表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建条码表")
    public ResponseMessage<?> create(@ApiParam(name = "条码表对象") @RequestBody EmkProOrderBarcodeEntity emkProOrderBarcode, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProOrderBarcodeEntity>> failures = this.validator.validate(emkProOrderBarcode, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderBarcodeService.save(emkProOrderBarcode);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("条码表信息保存失败");
        }
        return Result.success(emkProOrderBarcode);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新条码表", notes = "更新条码表")
    public ResponseMessage<?> update(@ApiParam(name = "条码表对象") @RequestBody EmkProOrderBarcodeEntity emkProOrderBarcode) {
        Set<ConstraintViolation<EmkProOrderBarcodeEntity>> failures = this.validator.validate(emkProOrderBarcode, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderBarcodeService.saveOrUpdate(emkProOrderBarcode);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新条码表信息失败");
        }
        return Result.success("更新条码表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除条码表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkProOrderBarcodeService.deleteEntityById(EmkProOrderBarcodeEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("条码表删除失败");
        }
        return Result.success();
    }
}
