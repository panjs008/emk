package com.emk.bill.proorderpackage.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.bill.proorderpackage.entity.EmkProOrderPackageEntity;
import com.emk.bill.proorderpackage.service.EmkProOrderPackageServiceI;
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

@Api(value = "EmkProOrderPackage", description = "包装明细表", tags = {"emkProOrderPackageController"})
@Controller
@RequestMapping({"/emkProOrderPackageController"})
public class EmkProOrderPackageController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkProOrderPackageController.class);
    @Autowired
    private EmkProOrderPackageServiceI emkProOrderPackageService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/bill/proorderpackage/emkProOrderPackageList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkProOrderPackageEntity emkProOrderPackage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderPackageEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkProOrderPackage, request.getParameterMap());


        cq.add();
        this.emkProOrderPackageService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkProOrderPackageEntity emkProOrderPackage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkProOrderPackage = (EmkProOrderPackageEntity) this.systemService.getEntity(EmkProOrderPackageEntity.class, emkProOrderPackage.getId());
        message = "包装明细表删除成功";
        try {
            this.emkProOrderPackageService.delete(emkProOrderPackage);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "包装明细表删除失败";
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
        message = "包装明细表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkProOrderPackageEntity emkProOrderPackage = (EmkProOrderPackageEntity) this.systemService.getEntity(EmkProOrderPackageEntity.class, id);


                this.emkProOrderPackageService.delete(emkProOrderPackage);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "包装明细表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkProOrderPackageEntity emkProOrderPackage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "包装明细表添加成功";
        try {
            this.emkProOrderPackageService.save(emkProOrderPackage);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "包装明细表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkProOrderPackageEntity emkProOrderPackage, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "包装明细表更新成功";
        EmkProOrderPackageEntity t = (EmkProOrderPackageEntity) this.emkProOrderPackageService.get(EmkProOrderPackageEntity.class, emkProOrderPackage.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkProOrderPackage, t);
            this.emkProOrderPackageService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "包装明细表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkProOrderPackageEntity emkProOrderPackage, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrderPackage.getId())) {
            emkProOrderPackage = (EmkProOrderPackageEntity) this.emkProOrderPackageService.getEntity(EmkProOrderPackageEntity.class, emkProOrderPackage.getId());
            req.setAttribute("emkProOrderPackagePage", emkProOrderPackage);
        }
        return new ModelAndView("com/emk/bill/proorderpackage/emkProOrderPackage-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkProOrderPackageEntity emkProOrderPackage, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkProOrderPackage.getId())) {
            emkProOrderPackage = (EmkProOrderPackageEntity) this.emkProOrderPackageService.getEntity(EmkProOrderPackageEntity.class, emkProOrderPackage.getId());
            req.setAttribute("emkProOrderPackagePage", emkProOrderPackage);
        }
        return new ModelAndView("com/emk/bill/proorderpackage/emkProOrderPackage-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkProOrderPackageController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkProOrderPackageEntity emkProOrderPackage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkProOrderPackageEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkProOrderPackage, request.getParameterMap());
        List<EmkProOrderPackageEntity> emkProOrderPackages = this.emkProOrderPackageService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "包装明细表");
        modelMap.put("entity", EmkProOrderPackageEntity.class);
        modelMap.put("params", new ExportParams("包装明细表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkProOrderPackages);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkProOrderPackageEntity emkProOrderPackage, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "包装明细表");
        modelMap.put("entity", EmkProOrderPackageEntity.class);
        modelMap.put("params", new ExportParams("包装明细表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "包装明细表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkProOrderPackageEntity>> list() {
        List<EmkProOrderPackageEntity> listEmkProOrderPackages = this.emkProOrderPackageService.getList(EmkProOrderPackageEntity.class);
        return Result.success(listEmkProOrderPackages);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取包装明细表信息", notes = "根据ID获取包装明细表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkProOrderPackageEntity task = (EmkProOrderPackageEntity) this.emkProOrderPackageService.get(EmkProOrderPackageEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取包装明细表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建包装明细表")
    public ResponseMessage<?> create(@ApiParam(name = "包装明细表对象") @RequestBody EmkProOrderPackageEntity emkProOrderPackage, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkProOrderPackageEntity>> failures = this.validator.validate(emkProOrderPackage, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderPackageService.save(emkProOrderPackage);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("包装明细表信息保存失败");
        }
        return Result.success(emkProOrderPackage);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新包装明细表", notes = "更新包装明细表")
    public ResponseMessage<?> update(@ApiParam(name = "包装明细表对象") @RequestBody EmkProOrderPackageEntity emkProOrderPackage) {
        Set<ConstraintViolation<EmkProOrderPackageEntity>> failures = this.validator.validate(emkProOrderPackage, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkProOrderPackageService.saveOrUpdate(emkProOrderPackage);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新包装明细表信息失败");
        }
        return Result.success("更新包装明细表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除包装明细表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkProOrderPackageService.deleteEntityById(EmkProOrderPackageEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("包装明细表删除失败");
        }
        return Result.success();
    }
}
