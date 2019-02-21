package com.emk.storage.samplegx.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.samplegx.entity.EmkSampleGxEntity;
import com.emk.storage.samplegx.service.EmkSampleGxServiceI;
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

@Api(value = "EmkSampleGx", description = "样品工序", tags = "emkSampleGxController")
@Controller
@RequestMapping("/emkSampleGxController")
public class EmkSampleGxController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSampleGxController.class);
    @Autowired
    private EmkSampleGxServiceI emkSampleGxService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/samplegx/emkSampleGxList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkSampleGxEntity emkSampleGx, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleGxEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleGx, request.getParameterMap());

        cq.add();
        emkSampleGxService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkSampleGxEntity emkSampleGx, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleGx = (EmkSampleGxEntity) systemService.getEntity(EmkSampleGxEntity.class, emkSampleGx.getId());
        message = "样品工序删除成功";
        try {
            emkSampleGxService.delete(emkSampleGx);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品工序删除失败";
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
        message = "样品工序删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleGxEntity emkSampleGx = (EmkSampleGxEntity) systemService.getEntity(EmkSampleGxEntity.class, id);
                emkSampleGxService.delete(emkSampleGx);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品工序删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkSampleGxEntity emkSampleGx, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品工序添加成功";
        try {
            emkSampleGxService.save(emkSampleGx);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品工序添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleGxEntity emkSampleGx, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品工序更新成功";
        EmkSampleGxEntity t = (EmkSampleGxEntity) emkSampleGxService.get(EmkSampleGxEntity.class, emkSampleGx.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkSampleGx, t);
            emkSampleGxService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品工序更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkSampleGxEntity emkSampleGx, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleGx.getId())) {
            emkSampleGx = (EmkSampleGxEntity) emkSampleGxService.getEntity(EmkSampleGxEntity.class, emkSampleGx.getId());
            req.setAttribute("emkSampleGxPage", emkSampleGx);
        }
        return new ModelAndView("com/emk/storage/samplegx/emkSampleGx-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkSampleGxEntity emkSampleGx, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleGx.getId())) {
            emkSampleGx = (EmkSampleGxEntity) emkSampleGxService.getEntity(EmkSampleGxEntity.class, emkSampleGx.getId());
            req.setAttribute("emkSampleGxPage", emkSampleGx);
        }
        return new ModelAndView("com/emk/storage/samplegx/emkSampleGx-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleGxController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkSampleGxEntity emkSampleGx, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleGxEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleGx, request.getParameterMap());
        List<EmkSampleGxEntity> emkSampleGxs = emkSampleGxService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "样品工序");
        modelMap.put("entity", EmkSampleGxEntity.class);
        modelMap.put("params", new ExportParams("样品工序列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSampleGxs);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkSampleGxEntity emkSampleGx, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "样品工序");
        modelMap.put("entity", EmkSampleGxEntity.class);
        modelMap.put("params", new ExportParams("样品工序列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "样品工序列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleGxEntity>> list() {
        List<EmkSampleGxEntity> listEmkSampleGxs = emkSampleGxService.getList(EmkSampleGxEntity.class);
        return Result.success(listEmkSampleGxs);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取样品工序信息", notes = "根据ID获取样品工序信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleGxEntity task = (EmkSampleGxEntity) emkSampleGxService.get(EmkSampleGxEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取样品工序信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建样品工序")
    public ResponseMessage<?> create(@ApiParam(name = "样品工序对象") @RequestBody EmkSampleGxEntity emkSampleGx, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleGxEntity>> failures = validator.validate(emkSampleGx, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleGxService.save(emkSampleGx);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品工序信息保存失败");
        }
        return Result.success(emkSampleGx);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新样品工序", notes = "更新样品工序")
    public ResponseMessage<?> update(@ApiParam(name = "样品工序对象") @RequestBody EmkSampleGxEntity emkSampleGx) {
        Set<ConstraintViolation<EmkSampleGxEntity>> failures = validator.validate(emkSampleGx, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleGxService.saveOrUpdate(emkSampleGx);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新样品工序信息失败");
        }
        return Result.success("更新样品工序信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除样品工序")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkSampleGxService.deleteEntityById(EmkSampleGxEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品工序删除失败");
        }
        return Result.success();
    }
}
