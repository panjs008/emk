package com.emk.storage.sampleyin.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;
import com.emk.storage.sampleyin.service.EmkSampleYinServiceI;
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

@Api(value = "EmkSampleYin", description = "样品印花表", tags = "emkSampleYinController")
@Controller
@RequestMapping("/emkSampleYinController")
public class EmkSampleYinController
        extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkSampleYinController.class);
    @Autowired
    private EmkSampleYinServiceI emkSampleYinService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/sampleyin/emkSampleYinList");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkSampleYinEntity emkSampleYin, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleYinEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleYin, request.getParameterMap());

        cq.add();
        emkSampleYinService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkSampleYinEntity emkSampleYin, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleYin = (EmkSampleYinEntity) systemService.getEntity(EmkSampleYinEntity.class, emkSampleYin.getId());
        message = "样品印花表删除成功";
        try {
            emkSampleYinService.delete(emkSampleYin);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品印花表删除失败";
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
        message = "样品印花表删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleYinEntity emkSampleYin = (EmkSampleYinEntity) systemService.getEntity(EmkSampleYinEntity.class, id);
                emkSampleYinService.delete(emkSampleYin);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品印花表删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkSampleYinEntity emkSampleYin, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品印花表添加成功";
        try {
            emkSampleYinService.save(emkSampleYin);
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品印花表添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleYinEntity emkSampleYin, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品印花表更新成功";
        EmkSampleYinEntity t = (EmkSampleYinEntity) emkSampleYinService.get(EmkSampleYinEntity.class, emkSampleYin.getId());
        try {
            MyBeanUtils.copyBeanNotNull2Bean(emkSampleYin, t);
            emkSampleYinService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品印花表更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkSampleYinEntity emkSampleYin, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleYin.getId())) {
            emkSampleYin = (EmkSampleYinEntity) emkSampleYinService.getEntity(EmkSampleYinEntity.class, emkSampleYin.getId());
            req.setAttribute("emkSampleYinPage", emkSampleYin);
        }
        return new ModelAndView("com/emk/storage/sampleyin/emkSampleYin-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkSampleYinEntity emkSampleYin, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleYin.getId())) {
            emkSampleYin = (EmkSampleYinEntity) emkSampleYinService.getEntity(EmkSampleYinEntity.class, emkSampleYin.getId());
            req.setAttribute("emkSampleYinPage", emkSampleYin);
        }
        return new ModelAndView("com/emk/storage/sampleyin/emkSampleYin-update");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleYinController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkSampleYinEntity emkSampleYin, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleYinEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSampleYin, request.getParameterMap());
        List<EmkSampleYinEntity> emkSampleYins = emkSampleYinService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "样品印花表");
        modelMap.put("entity", EmkSampleYinEntity.class);
        modelMap.put("params", new ExportParams("样品印花表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSampleYins);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkSampleYinEntity emkSampleYin, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "样品印花表");
        modelMap.put("entity", EmkSampleYinEntity.class);
        modelMap.put("params", new ExportParams("样品印花表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "样品印花表列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleYinEntity>> list() {
        List<EmkSampleYinEntity> listEmkSampleYins = emkSampleYinService.getList(EmkSampleYinEntity.class);
        return Result.success(listEmkSampleYins);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取样品印花表信息", notes = "根据ID获取样品印花表信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleYinEntity task = (EmkSampleYinEntity) emkSampleYinService.get(EmkSampleYinEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取样品印花表信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建样品印花表")
    public ResponseMessage<?> create(@ApiParam(name = "样品印花表对象") @RequestBody EmkSampleYinEntity emkSampleYin, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleYinEntity>> failures = validator.validate(emkSampleYin, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleYinService.save(emkSampleYin);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品印花表信息保存失败");
        }
        return Result.success(emkSampleYin);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新样品印花表", notes = "更新样品印花表")
    public ResponseMessage<?> update(@ApiParam(name = "样品印花表对象") @RequestBody EmkSampleYinEntity emkSampleYin) {
        Set<ConstraintViolation<EmkSampleYinEntity>> failures = validator.validate(emkSampleYin, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleYinService.saveOrUpdate(emkSampleYin);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新样品印花表信息失败");
        }
        return Result.success("更新样品印花表信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除样品印花表")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkSampleYinService.deleteEntityById(EmkSampleYinEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品印花表删除失败");
        }
        return Result.success();
    }
}
