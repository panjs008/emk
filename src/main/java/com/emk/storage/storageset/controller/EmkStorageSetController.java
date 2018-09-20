package com.emk.storage.storageset.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.storageset.entity.EmkStorageSetEntity;
import com.emk.storage.storageset.service.EmkStorageSetServiceI;
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

@Api(value = "EmkStorageSet", description = "仓库表", tags = {"emkStorageSetController"})
@Controller
@RequestMapping({"/emkStorageSetController"})
public class EmkStorageSetController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkStorageSetController.class);
    @Autowired
    private EmkStorageSetServiceI emkStorageSetService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = {"list"})
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/storageset/emkStorageSetList");
    }

    @RequestMapping(params = {"datagrid"})
    public void datagrid(EmkStorageSetEntity emkStorageSet, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkStorageSetEntity.class, dataGrid);

        HqlGenerateUtil.installHql(cq, emkStorageSet, request.getParameterMap());


        cq.add();
        this.emkStorageSetService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = {"doDel"})
    @ResponseBody
    public AjaxJson doDel(EmkStorageSetEntity emkStorageSet, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkStorageSet = (EmkStorageSetEntity) this.systemService.getEntity(EmkStorageSetEntity.class, emkStorageSet.getId());
        message = "仓库表删除成功";
        try {
            this.emkStorageSetService.delete(emkStorageSet);
            this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "仓库表删除失败";
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
        message = "仓库表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkStorageSetEntity emkStorageSet = (EmkStorageSetEntity) this.systemService.getEntity(EmkStorageSetEntity.class, id);


                this.emkStorageSetService.delete(emkStorageSet);
                this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "仓库表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doAdd"})
    @ResponseBody
    public AjaxJson doAdd(EmkStorageSetEntity emkStorageSet, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "仓库表添加成功";
        try {
            this.emkStorageSetService.save(emkStorageSet);
            this.systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "仓库表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"doUpdate"})
    @ResponseBody
    public AjaxJson doUpdate(EmkStorageSetEntity emkStorageSet, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "仓库表更新成功";
        EmkStorageSetEntity t = (EmkStorageSetEntity) this.emkStorageSetService.get(EmkStorageSetEntity.class, emkStorageSet.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkStorageSet, t);
            this.emkStorageSetService.saveOrUpdate(t);
            this.systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "仓库表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = {"goAdd"})
    public ModelAndView goAdd(EmkStorageSetEntity emkStorageSet, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkStorageSet.getId())) {
            emkStorageSet = (EmkStorageSetEntity) this.emkStorageSetService.getEntity(EmkStorageSetEntity.class, emkStorageSet.getId());
            req.setAttribute("emkStorageSetPage", emkStorageSet);
        }
        return new ModelAndView("com/emk/storage/storageset/emkStorageSet-add");
    }

    @RequestMapping(params = {"goUpdate"})
    public ModelAndView goUpdate(EmkStorageSetEntity emkStorageSet, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkStorageSet.getId())) {
            emkStorageSet = (EmkStorageSetEntity) this.emkStorageSetService.getEntity(EmkStorageSetEntity.class, emkStorageSet.getId());
            req.setAttribute("emkStorageSetPage", emkStorageSet);
        }
        return new ModelAndView("com/emk/storage/storageset/emkStorageSet-update");
    }

    @RequestMapping(params = {"upload"})
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkStorageSetController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = {"exportXls"})
    public String exportXls(EmkStorageSetEntity emkStorageSet, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkStorageSetEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkStorageSet, request.getParameterMap());
        List<EmkStorageSetEntity> emkStorageSets = this.emkStorageSetService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "仓库表");
        modelMap.put("entity", EmkStorageSetEntity.class);
        modelMap.put("params", new ExportParams("仓库表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkStorageSets);
        return "jeecgExcelView";
    }

    @RequestMapping(params = {"exportXlsByT"})
    public String exportXlsByT(EmkStorageSetEntity emkStorageSet, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "仓库表");
        modelMap.put("entity", EmkStorageSetEntity.class);
        modelMap.put("params", new ExportParams("仓库表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "仓库表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkStorageSetEntity>> list() {
        List<EmkStorageSetEntity> listEmkStorageSets = this.emkStorageSetService.getList(EmkStorageSetEntity.class);
        return Result.success(listEmkStorageSets);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取仓库表信息", notes = "根据ID获取仓库表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkStorageSetEntity task = (EmkStorageSetEntity) this.emkStorageSetService.get(EmkStorageSetEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取仓库表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation("创建仓库表")
    public ResponseMessage<?> create(@ApiParam(name = "仓库表对象") @RequestBody EmkStorageSetEntity emkStorageSet, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkStorageSetEntity>> failures = this.validator.validate(emkStorageSet, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkStorageSetService.save(emkStorageSet);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("仓库表信息保存失败");
        }
        return Result.success(emkStorageSet);
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = {"application/json"})
    @ResponseBody
    @ApiOperation(value = "更新仓库表", notes = "更新仓库表")
    public ResponseMessage<?> update(@ApiParam(name = "仓库表对象") @RequestBody EmkStorageSetEntity emkStorageSet) {
        Set<ConstraintViolation<EmkStorageSetEntity>> failures = this.validator.validate(emkStorageSet, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            this.emkStorageSetService.saveOrUpdate(emkStorageSet);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新仓库表信息失败");
        }
        return Result.success("更新仓库表信息成功");
    }

    @RequestMapping(value = {"/{id}"}, method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除仓库表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            this.emkStorageSetService.deleteEntityById(EmkStorageSetEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("仓库表删除失败");
        }
        return Result.success();
    }
}
